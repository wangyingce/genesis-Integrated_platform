package com.ysyl.backstage.brand.web;
import ins.framework.utils.DataUtils;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.backstage.brand.service.facade.BrandService;
import com.ysyl.backstage.brand.vo.BrandVo;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.report.service.facade.ReportService;
import com.ysyl.backstage.schema.model.YybsBrand;
import com.ysyl.backstage.schema.model.YybsProduct;
import com.ysyl.backstage.schema.model.YybsReport;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.file.service.facade.FileService;

public class BrandAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private String productExName;
	private String productNum;
	private String country;
	private String material;
	private String band;
	private String weight;
	private String unit;
	private String showPrice;
	private String markerPrice;
	private String keyword;
	private String remark;
	private String imgpath;
	private String remarkex;
	private String comment;
	/**common data-----------start*/
	private Integer pageQueryNo;
	private Integer pageStart;
	private String prodids;
	private String username;
	private String usercode;
	private Long genusId;
	/**about:yybsbrand-------start*/
	private Long brandId;
	private String brandName;
	private String brandSeriaNo;
	private String brandPicture;
	private String brandArea;
	private String brandRemark;
	private String brandStatus;
	private String inputtime;
	private String nation;
	/**common services-------start*/
	private FileService fileService;
	private LoginService loginService;
	private CommonService commonService;
	private BrandService brandService;
	private ProductService productService;
	private ReportService reportService;

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
	 * 根据品牌名称或者时间查询品牌列表，如果参数为空，查询全部
	 * @param brandName
	 * @param date
	 * @return
	 */
	public String queryBrandList() throws Exception{
		checkPower();
		//默认查10条
		if(this.pageQueryNo==null||"".equals(this.pageQueryNo)){
			pageQueryNo = 10;
		}
		if(this.pageStart==null||"".equals(this.pageStart)){
			pageStart = 1;
		}
//		List<YybsBrand> brands = brandService.findBrandsByNameOrTime(brandName, new Date(),pageStart,pageQueryNo);
		List<YybsBrand> brands = brandService.findBdByNameOrTime(brandId,brandName, inputtime,nation);
		if(brands!=null&&brands.size()>0){
			List<BrandVo> brandvos =  new ArrayList<BrandVo>(0);
			SimpleDateFormat branddteformat = new SimpleDateFormat("yyyy-MM-dd");
			for(YybsBrand band : brands){
				BrandVo brandvo = new BrandVo();
				DataUtils.copySimpleObject(band, brandvo);
				brandvo.setInputtime(branddteformat.format(band.getInputtime()));
				if(band.getBrandPicture()!=null&&!"".equals(band.getBrandPicture())){
					YyImageFile imgfl = fileService.findImageFileById(band.getBrandPicture());
					if(imgfl!=null){
						brandvo.setBrandPicture(imgfl.getFilepath());//将图片id转化成路径
					}
				}
				brandvos.add(brandvo);
			}
			JSONObject alljson = new JSONObject();
			JSONArray json = JSONArray.fromObject(brandvos);
			alljson.put("jsondata", json);
			alljson.put("record", brands.size());// 总条数
			alljson = reportService.addJsonData(alljson,"brand");
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
		}else{
		}
		return null;
	}
	
	public String findBrandImage() throws Exception{
		YyImageFile imgefile = fileService.findImageFileById(brandPicture);
		if(imgefile!=null){
			Gson g = new Gson();
			String json = "";
			json = g.toJson(imgefile);
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}
		return null;
	}
	
	public String addbrand() throws Exception{
		/**检查用户权限*/
		checkPower();
		/**保存更新品牌信息*/
		Long brandid = saveOrUpdateBrand();
		/**更新统计分析表数据*/
		reportService.saveReportCount("brand",nation);
		/**简历品牌选择的商品关联关系*/
		prodids = prodids.substring(0, prodids.length()-1);//去除最后一位逗号
		List<YybsProduct> prds = productService.findProdByIds(prodids);
		if(prds!=null&&prds.size()>0){
			for(YybsProduct prod: prds){
				prod.setBand(brandid.toString());
			}
		}
		productService.saveAllProduct(prds);
		/**返回结果*/
		Gson g = new Gson();
		String json = "sus";
		json = g.toJson(json);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	private Long saveOrUpdateBrand() throws Exception {
		YybsBrand bd = new YybsBrand();
		if(brandId!=null&&!"".equals(brandId)){
			bd.setBrandId(brandId);
		}
		bd.setBrandArea(URLDecoder.decode(brandArea,"UTF-8"));
		bd.setBrandName(URLDecoder.decode(brandName,"UTF-8"));
		if(brandPicture!=null&&!"".equals(brandPicture)){
			bd.setBrandPicture(brandPicture);
		}
		bd.setBrandRemark(URLDecoder.decode(brandRemark,"UTF-8"));
		bd.setBrandSeriaNo(brandSeriaNo);
		bd.setBrandStatus(brandStatus);
		bd.setNation(nation);
		bd.setFlag("1");
		bd.setValidate("1");
		Long brandid = brandService.saveBrand(bd);
		return brandid;
	}

	/**
	 * 查询品牌及其项下的产品信息
	 * @return
	 * @throws Exception
	 */
	public String queryBrandDetail() throws Exception{
		checkPower();
		if(brandId==null||"".equals(brandId)){
			throw new Exception("任务过期，请重试");
		}
		YybsBrand brand = brandService.findBdById(brandId);
		SimpleDateFormat branddteformat = new SimpleDateFormat("yyyy-MM-dd");
		BrandVo brandvo = new BrandVo();
		DataUtils.copySimpleObject(brand, brandvo);
		brandvo.setInputtime(branddteformat.format(brand.getInputtime()));
		brandvo.setBrandPictureId(brand.getBrandPicture());
		if(brand.getBrandPicture()!=null&&!"".equals(brand.getBrandPicture())){
			YyImageFile imgfl = fileService.findImageFileById(brand.getBrandPicture());
			if(imgfl!=null){
				brandvo.setBrandPicture(imgfl.getFilepath());//将图片id转化成路径
			}
		}
		List<YybsProduct> prods = new ArrayList<YybsProduct>(0);
		if(brand!=null){
			prods = productService.findProdByBrandId(brandId,genusId);
		}
		/**开始组装返回信息 -----------------------------------start*/
		JSONObject alljson = new JSONObject();
		/**添加品牌信息*/
		alljson.put("brand", brandvo);
		/**添加品牌下的产品信息*/
		JSONArray json = JSONArray.fromObject(prods);
		alljson.put("prods", json);
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
		/**开始组装返回信息 -----------------------------------end*/
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
	public String getMarkerPrice() {
		return markerPrice;
	}
	public void setMarkerPrice(String markerPrice) {
		this.markerPrice = markerPrice;
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

	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandPicture() {
		return brandPicture;
	}

	public void setBrandPicture(String brandPicture) {
		this.brandPicture = brandPicture;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getProdids() {
		return prodids;
	}

	public void setProdids(String prodids) {
		this.prodids = prodids;
	}

	public String getBrandSeriaNo() {
		return brandSeriaNo;
	}

	public void setBrandSeriaNo(String brandSeriaNo) {
		this.brandSeriaNo = brandSeriaNo;
	}

	public String getBrandArea() {
		return brandArea;
	}

	public void setBrandArea(String brandArea) {
		this.brandArea = brandArea;
	}

	public String getBrandRemark() {
		return brandRemark;
	}

	public void setBrandRemark(String brandRemark) {
		this.brandRemark = brandRemark;
	}

	public String getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getGenusId() {
		return genusId;
	}

	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	
}