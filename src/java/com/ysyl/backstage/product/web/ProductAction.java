package com.ysyl.backstage.product.web;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.backstage.report.service.facade.ReportService;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsProduct;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;

public class ProductAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private String username;
	private String usercode;
	private String productName;
	private String productExName;
	private String productNum;
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
	private Integer count;
	private LoginService loginService;
	private CommonService commonService;
	private ProductService productService;
	private ReportService reportService;
	private Integer pageQueryNo;
	private Integer pageStart;
	private String inputtime;
	private String rqsttype;
	private Long genusId;
	private Long brandId;
	private Long productId;
	private YybsProduct yybsProduct;

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
	public String delProduct() throws Exception{
		checkPower();
		YybsProduct prod = productService.findProdById(productId);
		if(prod!=null){
			prod.setValidstatus("9");
			productService.saveProduct(prod);
			Gson g = new Gson();
			String json = "";
			json = g.toJson("1");
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}
		return null;
	}
		
	/**
	 * 保存或更新产品信息
	 * @return
	 * @throws Exception 
	 */
	public String addProduct() throws Exception{
		checkPower();
		saveOrUpdateProduct(rqsttype);
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
	 * @throws Exception 
	 */
	private void saveOrUpdateProduct(String rqsttype) throws Exception {
		YybsProduct prod = new YybsProduct();
		/**如果是更新，先查询原始数据*/
		if(rqsttype=="m"||"m".equals(rqsttype)){
			prod = productService.findProdById(productId);
		}
		if(productName!=null&&!"".equals(productName)){
			prod.setProductName(transEncode(productName));
		}
		if(productExName!=null&&!"".equals(productExName)){
			prod.setProductExName(transEncode(productExName));
		}
		if(productNum!=null&&!"".equals(productNum)){
			prod.setProductNum(transEncode(productNum));
		}
		if(country!=null&&!"".equals(country)){
			prod.setCountry(transEncode(country));
		}
		if(count!=null&&!"".equals(count)){
			prod.setCount(count);
		}
		if(material!=null&&!"".equals(material)){
			prod.setMaterial(transEncode(material));
		}
		if(band!=null&&!"".equals(band)){
			prod.setBand(band);
		}
		if(weight!=null&&!"".equals(weight)){
			prod.setWeight(weight);
		}
		if(unit!=null&&!"".equals(unit)){
			prod.setUnit(transEncode(unit));
		}
		if(showPrice!=null&&!"".equals(showPrice)){
			prod.setShowPrice(showPrice);
		}
		if(marketPrice!=null&&!"".equals(marketPrice)){
			prod.setMarketPrice(marketPrice);
		}
		if(keyword!=null&&!"".equals(keyword)){
			prod.setKeyword(transEncode(keyword));
		}
		if(remark!=null&&!"".equals(remark)){
			prod.setRemark(transEncode(remark));
		}
		if(imgpath!=null&&!"".equals(imgpath)){
			prod.setImgpath(imgpath);
		}
		if(remarkex!=null&&!"".equals(remarkex)){
			prod.setRemarkex(transEncode(remarkex));
		}
		if(comment!=null&&!"".equals(comment)){
			prod.setComment(transEncode(comment));
		}
		if(genusId!=null&&!"".equals(genusId)){
			prod.setGenusId(genusId);
		}
		prod.setValidstatus("1");
		productService.saveProduct(prod);
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
	 * 查询id对应的产品信息
	 * @return
	 * @throws Exception
	 */
	public String queryProduct() throws Exception{
		checkPower();
		YybsProduct prod = productService.findProdById(this.productId);
		if(prod!=null&&!"".equals(prod)){
			Gson g = new Gson();
			String json = "";
			json = g.toJson(prod);
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}
		return null;
	}
	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部
	 * @param productName
	 * @param date
	 * @return
	 */
	public String queryProductList() throws Exception{
		checkPower();
		//默认查10条
//		if(this.pageQueryNo==null||"".equals(this.pageQueryNo)){
//			pageQueryNo = 10;
//		}
//		if(this.pageStart==null||"".equals(this.pageStart)){
//			pageStart = 1;
//		}
//		Page page = productService.findProductsByNameOrTime(productName, new Date(),pageStart,pageQueryNo);
		List<YybsProduct> prods= productService.findProdByNameOrTime(rqsttype,productName,brandId,new Date());
//		List list = page.getResult();
		if(prods!=null&&prods.size()>0){
			JSONObject alljson = new JSONObject();
			JSONArray json = JSONArray.fromObject(prods);
			alljson.put("jsondata", json);
//			alljson.put("page", pageStart);// 当前页
//			alljson.put("total", page.getTotalPageCount());// 总页数
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
	 * 返回得到所属类型树
	 * @return
	 * @throws Exception
	 */
	public String queryGenusTree() throws Exception{
		JSONObject alljson = new JSONObject();
		alljson = reportService.addJsonData(alljson,"genus");
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
		return null;
	}
	
	/**
	 * id查询产品类型表
	 * @param genusId
	 * @return
	 */
	public String queryGenusData() throws Exception{
		YybsGenus genus = reportService.findGenusDataById(genusId);
		if(genus!=null){
			Gson g = new Gson();
			String json = "";
			json = g.toJson(genus);
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}
		return null;
	}
	
	/**
	 * 更新产品类型操作
	 * @param genusId
	 * @return
	 */
	public String updateGenusData() throws Exception{
		Gson g = new Gson();
		String json = "";
		if(genusId!=null&&!"".equals(genusId)){
			YybsGenus genus = reportService.findGenusDataById(genusId);
			if(genus!=null){
				if(operation=="add"||"add".equals(operation)){
					if(!genusId.toString().endsWith("0")){
						json = g.toJson("error:已经是子节点，不能再新增类型了");
					}else{
						YybsGenus addgenus = new YybsGenus();
						addgenus.setUppergenusId(genus.getGenusId());
						addgenus.setFlag("1");
						addgenus.setValidate("1");
						addgenus.setName(new String(name.getBytes("ISO-8859-1"), "utf-8"));
						addgenus.setGenusRemark(new String(genusRemark.getBytes("ISO-8859-1"), "utf-8"));
						reportService.updateGenus(addgenus,operation);
					}
				}else if(operation=="mod"||"mod".equals(operation)){
					genus.setName(new String(name.getBytes("ISO-8859-1"), "utf-8"));
					genus.setGenusRemark(new String(genusRemark.getBytes("ISO-8859-1"), "utf-8"));
					reportService.updateGenus(genus,operation);
				}else if(operation=="del"||"del".equals(operation)){
					reportService.updateGenus(genus,operation);
				}
			}
			if(json==null||"".equals(json)){
				json = g.toJson(operation+":success");
			}
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
				
		}
		return null;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductExName() {
		return productExName;
	}
	public void setProductExName(String productExName) {
		this.productExName = productExName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
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
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
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
	public YybsProduct getYybsProduct() {
		return yybsProduct;
	}
	public void setYybsProduct(YybsProduct yybsProduct) {
		this.yybsProduct = yybsProduct;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}