package com.ysyl.backstage.order.vo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class OrderDetailVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	//order
	private Long orderDetailId;
	private Long orderId;
	private Long brandId;
	private String brandName;
	private Long productId;
	private String productName;
	private Integer productCount;
	private BigDecimal productAmount;
	private BigDecimal productSumAmount;
	private Long genusId;
	private String genusName;
	private Date orderInputtime;
	private String validate;
	private String flag;
	private String prodImgPath;
	private String payType;
	private Date expressStartDate;
	//prod
	private String country;
	private String material;
	private String band;
	private String weight;
	private String unit;
	private String showPrice;
	private String marketPrice;
	private String prodstatus;
	private String remark;
	private Integer count;
	private Date prodInputtime;
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getProductSumAmount() {
		return productSumAmount;
	}
	public void setProductSumAmount(BigDecimal productSumAmount) {
		this.productSumAmount = productSumAmount;
	}
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	public String getGenusName() {
		return genusName;
	}
	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProdImgPath() {
		return prodImgPath;
	}
	public void setProdImgPath(String prodImgPath) {
		this.prodImgPath = prodImgPath;
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
	public String getProdstatus() {
		return prodstatus;
	}
	public void setProdstatus(String prodstatus) {
		this.prodstatus = prodstatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getExpressStartDate() {
		return expressStartDate;
	}
	public void setExpressStartDate(Date expressStartDate) {
		this.expressStartDate = expressStartDate;
	}
	public Date getOrderInputtime() {
		return orderInputtime;
	}
	public void setOrderInputtime(Date orderInputtime) {
		this.orderInputtime = orderInputtime;
	}
	public Date getProdInputtime() {
		return prodInputtime;
	}
	public void setProdInputtime(Date prodInputtime) {
		this.prodInputtime = prodInputtime;
	}
	
}