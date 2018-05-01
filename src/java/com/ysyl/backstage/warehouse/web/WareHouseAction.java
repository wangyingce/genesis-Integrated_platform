package com.ysyl.backstage.warehouse.web;
import ins.framework.utils.DataUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.schema.model.RetotheSituationInfo;
import com.ysyl.backstage.schema.model.WhAccessory;
import com.ysyl.backstage.schema.model.WhCar;
import com.ysyl.backstage.schema.model.WhCarSeries;
import com.ysyl.backstage.warehouse.service.facade.WareHouseService;
import com.ysyl.backstage.warehouse.vo.RetotheSituationVo;
import com.ysyl.backstage.warehouse.vo.WareHouseVo;
import com.ysyl.backstage.warehouse.vo.WhAccessoryVo;
import com.ysyl.backstage.warehouse.vo.WhCarVo;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.file.service.facade.FileService;

public class WareHouseAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	/**common services-------start*/
	private FileService fileService;
	private LoginService loginService;
	
	/**------carseries---start-----------**/
	private Long carserId;
	private String carserName;
	private String carserMinPrice;
	private String carserMaxPrice;
	private WareHouseService warehouseService;
	private WhCarSeries whCarSeries;
	/**------carseries---end-----------**/
	
	/**------car---start-----------**/
	private Long carId;
	private String carName;
	private String carStandPrice;
	private String carImgPath;
	private WhCar whCar;
	/**------car---end-----------**/
	
	/**------Accessory---start-----------**/
	private Long accessoryId;
	private String accessoryName;
	private String accessoryStandPrice;
	private WhAccessory accessory;
	/**------Accessory---end-----------**/
	
	/**------RetotheSituationInfo---start-----------**/
	private String ReportedNumber; 
	/**------RetotheSituationInfo---end-----------**/

	/** 封装上传文件*/
	private String realPath;

	/**
	 * 验证用户权限
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	private YyUser checkPower() throws Exception{
		String usercode = (String) getRequest().getSession().getAttribute("UserCode");
		String username = (String) getRequest().getSession().getAttribute("UserName");
		if(usercode ==null||"".equals(usercode)||username==null||"".equals(username)){
			throw new Exception("用户未登录，请登录");
		}
		YyUser userDto  = loginService.findUserByNameAndCode(username,usercode);
		return userDto;
	}
	
	/**
	 * 查询车系列表
	 * @return
	 * @throws Exception
	 */
	public String queryCarCeries() throws Exception{
		checkPower();
		if(this.carserId!=null&&!"".equals(this.carserId)){
			WhCarSeries cs = warehouseService.findCarSeriesById(this.carserId);
			if(cs!=null&&!"".equals(cs)){
				RnMsg("queryOne",cs);
			}
		}else{
			List<WhCarSeries> carserieses = warehouseService.findCarSeriesByAll(this.carserId,this.carserName,this.carserMinPrice,this.carserMaxPrice);
			if(carserieses!=null&&carserieses.size()>0){
				List<WareHouseVo> wareHouseVos =  new ArrayList<WareHouseVo>(0);
				SimpleDateFormat dteformat = new SimpleDateFormat("yyyy-MM-dd");
				for(WhCarSeries whc : carserieses){
					WareHouseVo whvo =  new WareHouseVo();
					DataUtils.copySimpleObject(whc, whvo);
					whvo.setInputtime(dteformat.format(whc.getInputtime()));
					wareHouseVos.add(whvo);
				}
				JSONArray json = JSONArray.fromObject(wareHouseVos);
				RnList(json,carserieses.size());
			}
		}
		
		return null;
	}
	
	/**
	 * 查询车型列表
	 * @return
	 * @throws Exception
	 */
	public String queryCars() throws Exception{
		checkPower();
		SimpleDateFormat dteformat = new SimpleDateFormat("yyyy-MM-dd");
		if(this.carId!=null&&!"".equals(this.carId)){
			WhCar cr = warehouseService.findCarById(this.carId);
			WhCarSeries cs = warehouseService.findCarSeriesById(cr.getCarserId());
			if(cr!=null&&!"".equals(cr)){
				WhCarVo cvo =  new WhCarVo();
				DataUtils.copySimpleObject(cr,cvo);
				if(cs!=null){
					cvo.setCarserName(cs.getCarserName());
				}
				cvo.setInputtime(dteformat.format(cr.getInputtime()));
				if(cvo.getCarImgPath()!=null&&!"".equals(cvo.getCarImgPath())){
					YyImageFile imgfl = fileService.findImageFileById(cvo.getCarImgPath());
					if(imgfl!=null){
						cvo.setImgPath(imgfl.getFilepath());//将图片id转化成路径
					}
				}	
				RnMsg("queryOne",cvo);
			}
		}else{
			List<WhCar> crs = warehouseService.findCarByAll(this.carId,this.carName,this.carStandPrice,this.carserId);
			if(crs!=null&&crs.size()>0){
				List<WhCarVo> cvos =  new ArrayList<WhCarVo>(0);
				for(WhCar cr : crs){
					WhCarVo cvo =  new WhCarVo();
					DataUtils.copySimpleObject(cr,cvo);
					cvo.setInputtime(dteformat.format(cr.getInputtime()));
					if(cvo.getCarImgPath()!=null&&!"".equals(cvo.getCarImgPath())){
						YyImageFile imgfl = fileService.findImageFileById(cvo.getCarImgPath());
						if(imgfl!=null){
							cvo.setCarImgPath(imgfl.getFilepath());//将图片id转化成路径
						}
					}
					cvos.add(cvo);
				}
				JSONArray json = JSONArray.fromObject(cvos);
				RnList(json,crs.size());
			}
		}
		return null;
	}
	
	/**
	 * 查询报备库列表
	 * @return
	 * @throws Exception
	 */
	public String queryDataEntrys() throws Exception{
		checkPower();
		SimpleDateFormat dteformat = new SimpleDateFormat("yyyy-MM-dd");
		if(this.ReportedNumber!=null&&!"".equals(this.ReportedNumber)){
			RetotheSituationInfo rsi = warehouseService.findRetotheSituationInfoById(this.ReportedNumber);
			if(rsi!=null&&!"".equals(rsi)){
				RetotheSituationVo rsivo =  new RetotheSituationVo();
				DataUtils.copySimpleObject(rsi,rsivo);
				RnMsg("queryOne",rsivo);
			}
		}else{
			List<RetotheSituationInfo> rsis = warehouseService.findRetotheSituationInfoByAll(this.ReportedNumber,null,null,null);
			if(rsis!=null&&rsis.size()>0){
				List<RetotheSituationVo> rsisvo =  new ArrayList<RetotheSituationVo>(0);
				for(RetotheSituationInfo rsi : rsis){
					RetotheSituationVo rsivo =  new RetotheSituationVo();
					DataUtils.copySimpleObject(rsi,rsivo);
					rsivo.setInputtime(dteformat.format(rsi.getInputtime()));
					rsisvo.add(rsivo);
				}
				JSONArray json = JSONArray.fromObject(rsisvo);
				RnList(json,rsis.size());
			}
		}
		return null;
	}
	
	/**
	 * 查询配件列表
	 * @return
	 * @throws Exception
	 */
	public String queryAccessorys() throws Exception{
		checkPower();
		SimpleDateFormat dteformat = new SimpleDateFormat("yyyy-MM-dd");
		if(this.accessoryId!=null&&!"".equals(this.accessoryId)){
			WhAccessory acs = warehouseService.findAccessoryById(this.accessoryId);
			WhCarSeries cs = warehouseService.findCarSeriesById(acs.getCarserId());
			if(acs!=null&&!"".equals(acs)){
				WhAccessoryVo acssvo =  new WhAccessoryVo();
				DataUtils.copySimpleObject(acs,acssvo);
				if(cs!=null){
					acssvo.setCarserName(cs.getCarserName());
				}
				RnMsg("queryOne",acssvo);
			}
		}else{
			List<WhAccessory> acss = warehouseService.findAccessoryByAll(this.accessoryId,this.accessoryName,this.accessoryStandPrice,this.carserId);
			if(acss!=null&&acss.size()>0){
				List<WhAccessoryVo> acssvos =  new ArrayList<WhAccessoryVo>(0);
				for(WhAccessory acs : acss){
					WhAccessoryVo acssvo =  new WhAccessoryVo();
					WhCarSeries carser = warehouseService.findCarSeriesById(acs.getCarserId());
					acssvo.setCarserName(carser.getCarserName());
					DataUtils.copySimpleObject(acs,acssvo);
					acssvo.setInputtime(dteformat.format(acs.getInputtime()));
					acssvos.add(acssvo);
				}
				JSONArray json = JSONArray.fromObject(acssvos);
				RnList(json,acss.size());
			}
		}
		
		return null;
	}
	
	/**
	 * 新增或更新车系
	 * @return
	 * @throws Exception
	 */
	public String addCarCeries() throws Exception{
		/**检查用户权限*/
		checkPower();
		if(this.carserId!=null&&!"".equals(this.carserId)){
			whCarSeries = warehouseService.findCarSeriesById(this.carserId);
		}else{
			whCarSeries = new WhCarSeries();
			whCarSeries.setInputtime(new Date());
			whCarSeries.setCreaterName((String) getRequest().getSession().getAttribute("UserName"));
		}
		whCarSeries.setCarserName(URLDecoder.decode(this.carserName,"UTF-8"));
		whCarSeries.setCarserMinPrice(this.carserMinPrice);
		whCarSeries.setCarserMaxPrice(this.carserMaxPrice);
		warehouseService.saveCarSeries(whCarSeries);	
		/**返回结果*/
		RnMsg("saveOrDel","sus");
		return null;
	}
	
	/**
	 * 新增或更新车型
	 * @return
	 * @throws Exception
	 */
	public String addCar() throws Exception{
		/**检查用户权限*/
		checkPower();
		if(this.carId!=null&&!"".equals(this.carId)){
			whCar = warehouseService.findCarById(this.carId);
		}else{
			whCar = new WhCar();
			whCar.setInputtime(new Date());
			whCar.setCreaterName((String) getRequest().getSession().getAttribute("UserName"));
		}
		whCar.setCarserId(this.carserId);
		whCar.setCarName(URLDecoder.decode(this.carName,"UTF-8"));
		whCar.setCarStandPrice(this.carStandPrice);
		whCar.setCarImgPath(this.carImgPath);
		warehouseService.saveCar(whCar);	
		/**返回结果*/
		RnMsg("saveOrDel","sus");
		return null;
	}
	
	/**
	 * 新增配件
	 * @return
	 * @throws Exception
	 */
	public String addAccessory() throws Exception{
		/**检查用户权限*/
		checkPower();
		if(this.accessoryId!=null&&!"".equals(this.accessoryId)){
			accessory = warehouseService.findAccessoryById(accessoryId);
		}else{
			accessory = new WhAccessory();
			accessory.setInputtime(new Date());
			accessory.setCreaterName((String) getRequest().getSession().getAttribute("UserName"));
		}
		accessory.setCarserId(carserId);
		accessory.setAccessoryName(transEncode(accessoryName));
		accessory.setAccessoryStandPrice(accessoryStandPrice);
		warehouseService.saveAccessory(accessory);	
		/**返回结果*/
		RnMsg("saveOrDel","sus");
		return null;
	}
	/**
	 * 删除车系
	 * @return
	 * @throws Exception
	 */
	public String delCarCeries() throws Exception{
		checkPower();
		WhCarSeries cs = warehouseService.findCarSeriesById(this.carserId);
		Object standObject = cs;
		warehouseService.defWhObject(standObject);
		RnMsg("saveOrDel","sus");
		return null;
	}
	/**
	 * 删除车型
	 * @return
	 * @throws Exception
	 */
	public String delCar() throws Exception{
		checkPower();
		WhCar cr = warehouseService.findCarById(this.carId);
		Object standObject = cr;
		warehouseService.defWhObject(standObject);
		RnMsg("saveOrDel","sus");
		return null;
	}
	/**
	 * 删除配件
	 * @return
	 * @throws Exception
	 */
	public String delAccessory() throws Exception{
		checkPower();
		WhAccessory acc = warehouseService.findAccessoryById(accessoryId);
		Object standObject = acc;
		warehouseService.defWhObject(standObject);
		RnMsg("saveOrDel","sus");
		return null;
	}
	
	/**
	 * 转换编码格式
	 * @param productExName2
	 * @return
	 */
	private String transEncode(String stringOne) {
		if(stringOne!=null&&!"".equals(stringOne)){
			try {
				stringOne = new String(stringOne.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return stringOne;
		}else{
			return stringOne;
		}
	}
	/**
	 * 返回单条结果或信息
	 * @param type
	 * @param msg
	 * @throws Exception
	 */
	private void RnMsg(String type,Object msg) throws Exception{
		Gson g = new Gson();
		String json = "";
		if("saveOrDel"==type||"saveOrDel".equals(type)||"importXls"==type||"importXls".equals(type)){
			 json = (String) msg;
   			 json = g.toJson(json);
		}
		if("queryOne"==type||"queryOne".equals(type)){
			if(msg instanceof WhAccessoryVo){
				WhAccessoryVo acsvo = (WhAccessoryVo) msg;
				json = g.toJson(acsvo);
			}
			if(msg instanceof WhCarVo){
				WhCarVo cvo = (WhCarVo) msg;
				json = g.toJson(cvo);
			}
			if(msg instanceof WhCarSeries){
				WhCarSeries cs = (WhCarSeries) msg;
				json = g.toJson(cs);
			}
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
	}
	/**
	 * 返回查询列表集
	 * @param json
	 * @param size
	 */
	private void RnList(JSONArray json, int size) {
		JSONObject alljson = new JSONObject();
		alljson.put("jsondata", json);
		alljson.put("record", size);// 总条数
		logger.debug(alljson);
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			out = this.getResponse().getWriter();
			out.print(alljson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * excel数据库倒入功能 
	 * @return
	 * @throws Exception 
	 */
	public String dataEntry() throws Exception{
		//检查权限
		checkPower();
		//组合全局路径
		realPath = getRequest().getSession().getServletContext().getRealPath("/")+transEncode(realPath);
		File file = new File(realPath);     
		//excel导入
	    warehouseService.setBasicDataByExcel(file,(String) getRequest().getSession().getAttribute("UserName"));
	    RnMsg("importXls","1");
		return null;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public WareHouseService getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WareHouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	public Long getCarserId() {
		return carserId;
	}

	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}

	public String getCarserName() {
		return carserName;
	}

	public void setCarserName(String carserName) {
		this.carserName = carserName;
	}

	public String getCarserMinPrice() {
		return carserMinPrice;
	}

	public void setCarserMinPrice(String carserMinPrice) {
		this.carserMinPrice = carserMinPrice;
	}

	public String getCarserMaxPrice() {
		return carserMaxPrice;
	}

	public void setCarserMaxPrice(String carserMaxPrice) {
		this.carserMaxPrice = carserMaxPrice;
	}
	
	public WhCarSeries getWhCarSeries() {
		return whCarSeries;
	}

	public void setWhCarSeries(WhCarSeries whCarSeries) {
		this.whCarSeries = whCarSeries;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarStandPrice() {
		return carStandPrice;
	}

	public void setCarStandPrice(String carStandPrice) {
		this.carStandPrice = carStandPrice;
	}

	public String getCarImgPath() {
		return carImgPath;
	}

	public void setCarImgPath(String carImgPath) {
		this.carImgPath = carImgPath;
	}

	public WhCar getWhCar() {
		return whCar;
	}

	public void setWhCar(WhCar whCar) {
		this.whCar = whCar;
	}

	public Long getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(Long accessoryId) {
		this.accessoryId = accessoryId;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getAccessoryStandPrice() {
		return accessoryStandPrice;
	}

	public void setAccessoryStandPrice(String accessoryStandPrice) {
		this.accessoryStandPrice = accessoryStandPrice;
	}

	public WhAccessory getAccessory() {
		return accessory;
	}

	public void setAccessory(WhAccessory accessory) {
		this.accessory = accessory;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}


}