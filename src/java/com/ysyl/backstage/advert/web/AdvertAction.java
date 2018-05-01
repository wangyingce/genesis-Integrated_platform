package com.ysyl.backstage.advert.web;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.backstage.advert.service.facade.AdvertService;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.report.service.facade.ReportService;
import com.ysyl.backstage.schema.model.YybsAdvert;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsProduct;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;

public class AdvertAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private String username;
	private String usercode;
	private String advertName;
	private String advertExName;
	private String advertNum;
	private String country;
	private String material;
	private String band;
	private String weight;
	private String unit;
	private String showPrice;
	private String marketPrice;
	private String keyword;
	private String remark;
	private String imgpath;
	private String remarkex;
	private String comment;
	private String name;
	private String genusRemark;
	private String operation;
	private LoginService loginService;
	private CommonService commonService;
	private AdvertService advertService;
	private ReportService reportService;
	private Integer pageQueryNo;
	private Integer pageStart;
	private String inputtime;
	private String rqsttype;
	private Long genusId;
	private String genusType;
	private Long brandId;
	private Long productId;
	private YybsAdvert yybsAdvert;

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
	 * 动态菜单查询
	 * @param productName
	 * @param date
	 * @return
	 */
	public String queryNavList() throws Exception{
		checkPower();
		List<YybsGenus> genus = reportService.findGenusDataByGenusType(genusType);
		if(genus!=null&&genus.size()>0){
			JSONObject alljson = new JSONObject();
			JSONArray json = JSONArray.fromObject(genus);
			alljson.put("jsondata", json);
			alljson.put("record", genus.size());// 总条数
			logger.debug(json);
			logger.debug(alljson);
			PrintWriter out = null;
			try {
				this.getResponse().setCharacterEncoding("UTF-8");
				this.getResponse().getWriter().print(alljson);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(out!=null){
					out.close();
				}
			}
		}else{
		}
		return null;
	}
	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部
	 * @param productName
	 * @param date
	 * @return
	 */
	public String queryAdvertising() throws Exception{
		checkPower();
		List<YybsAdvert> prods= advertService.findAdvertByNameOrTime(rqsttype,advertName,brandId,new Date());
		if(prods!=null&&prods.size()>0){
			JSONObject alljson = new JSONObject();
			JSONArray json = JSONArray.fromObject(prods);
			alljson.put("jsondata", json);
			alljson.put("record", prods.size());// 总条数
			logger.debug(json);
			logger.debug(alljson);
			PrintWriter out = null;
			try {
				if(rqsttype=="addband"||"addband".equals(rqsttype)||rqsttype=="modband"||"modband".equals(rqsttype)){
					this.getResponse().setCharacterEncoding("UTF-8");
					this.getResponse().getWriter().print(json);
				}else{
					this.getResponse().setCharacterEncoding("UTF-8");
					this.getResponse().getWriter().print(alljson);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(out!=null){
					out.close();
				}
			}
		}else{
		}
		return null;
	}
	/**
	 * 保存功能
	 * @return
	 * @throws Exception 
	 */
	public String addAdvert() throws Exception{
		checkPower();
		saveOrUpdateAdvert(rqsttype);
		Gson g = new Gson();
		String json = "";
		json = g.toJson("1");
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}

	/**
	 * 保存更新商品信息
	 * @param rqsttype 
	 */
	private void saveOrUpdateAdvert(String rqsttype) {
		YybsProduct prod = new YybsProduct();
		/**如果是更新，先查询原始数据*/
//		if(rqsttype=="m"||"m".equals(rqsttype)){
//			prod = advertService.findProdById(productId);
//		}
		if(advertName!=null&&!"".equals(advertName)){
			prod.setProductName(advertName);
		}
		if(advertExName!=null&&!"".equals(advertExName)){
			prod.setProductExName(advertExName);
		}
		if(advertNum!=null&&!"".equals(advertNum)){
			prod.setProductNum(advertNum);
		}
		if(country!=null&&!"".equals(country)){
			prod.setCountry(country);
		}
		if(material!=null&&!"".equals(material)){
			prod.setMaterial(material);
		}
		if(band!=null&&!"".equals(band)){
			prod.setBand(band);
		}
		if(weight!=null&&!"".equals(weight)){
			prod.setWeight(weight);
		}
		if(unit!=null&&!"".equals(unit)){
			prod.setUnit(unit);
		}
		if(showPrice!=null&&!"".equals(showPrice)){
			prod.setShowPrice(showPrice);
		}
		if(marketPrice!=null&&!"".equals(marketPrice)){
			prod.setMarketPrice(marketPrice);
		}
		if(keyword!=null&&!"".equals(keyword)){
			prod.setKeyword(keyword);
		}
		if(remark!=null&&!"".equals(remark)){
			prod.setRemark(remark);
		}
		if(imgpath!=null&&!"".equals(imgpath)){
			prod.setImgpath(imgpath);
		}
		if(remarkex!=null&&!"".equals(remarkex)){
			prod.setRemarkex(remarkex);
		}
		if(comment!=null&&!"".equals(comment)){
			prod.setComment(comment);
		}
		if(genusId!=null&&!"".equals(genusId)){
			prod.setGenusId(genusId);
		}
		prod.setValidstatus("1");
//		advertService.saveProduct(prod);
	}
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	public String getAdvertExName() {
		return advertExName;
	}
	public void setAdvertExName(String advertExName) {
		this.advertExName = advertExName;
	}
	public String getAdvertNum() {
		return advertNum;
	}
	public void setAdvertNum(String advertNum) {
		this.advertNum = advertNum;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getRemarkex() {
		return remarkex;
	}
	public void setRemarkex(String remarkex) {
		this.remarkex = remarkex;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public AdvertService getAdvertService() {
		return advertService;
	}
	public void setAdvertService(AdvertService advertService) {
		this.advertService = advertService;
	}
	public Integer getPageQueryNo() {
		return pageQueryNo;
	}
	public void setPageQueryNo(Integer pageQueryNo) {
		this.pageQueryNo = pageQueryNo;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public Integer getPageStart() {
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public String getRqsttype() {
		return rqsttype;
	}
	public void setRqsttype(String rqsttype) {
		this.rqsttype = rqsttype;
	}
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenusRemark() {
		return genusRemark;
	}
	public void setGenusRemark(String genusRemark) {
		this.genusRemark = genusRemark;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public YybsAdvert getYybsAdvert() {
		return yybsAdvert;
	}
	public void setYybsAdvert(YybsAdvert yybsAdvert) {
		this.yybsAdvert = yybsAdvert;
	}

	public String getGenusType() {
		return genusType;
	}


	public void setGenusType(String genusType) {
		this.genusType = genusType;
	}
	
}