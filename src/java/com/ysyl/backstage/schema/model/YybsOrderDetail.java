package com.ysyl.backstage.schema.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "YybsOrderDetail")
public class YybsOrderDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
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
	private Date inputtime;
	private String validate;
	private String flag;
	private String prodImgPath;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YybsOrderDetail")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "orderDetailId")
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	@Column(name = "orderId")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@Column(name = "brandId")
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	@Column(name = "brandName")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	@Column(name = "productId")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	@Column(name = "productName")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "genusId")
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	@Column(name = "genusName")
	public String getGenusName() {
		return genusName;
	}
	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}
	@Column(name = "inputtime")
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	@Column(name = "validate")
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "productCount")
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	@Column(name = "productAmount")
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	@Column(name = "productSumAmount")
	public BigDecimal getProductSumAmount() {
		return productSumAmount;
	}
	public void setProductSumAmount(BigDecimal productSumAmount) {
		this.productSumAmount = productSumAmount;
	}
	@Column(name = "prodImgPath")
	public String getProdImgPath() {
		return prodImgPath;
	}
	public void setProdImgPath(String prodImgPath) {
		this.prodImgPath = prodImgPath;
	}
}
