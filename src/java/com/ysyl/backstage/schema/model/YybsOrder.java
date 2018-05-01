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
@Table(name = "yybsorder")
public class YybsOrder implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private String orderContent;
	private BigDecimal orderAmount;
	private BigDecimal orderDiscount;
	private String orderGenus;
	private Integer orderCount;//default_1
	private String orderStatus;
	private String orderUser;
	private String orderAddress;
	private String orderMobile;
	private String orderPostcode;
	private Date inputtime;
	private String validate;
	private String flag;
	private String orderSeries;
	private String imgId;
	private String prodId;//del
	private String genusId;//del
	private String expressName;
	private Long expressNo;
	private String cod;
	private String payType;
	private Date expressStartDate;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yybsorder")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "orderId")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "orderContent")
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	
	@Column(name = "orderAmount")
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Column(name = "orderDiscount")
	public BigDecimal getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(BigDecimal orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	@Column(name = "orderGenus")
	public String getOrderGenus() {
		return orderGenus;
	}
	public void setOrderGenus(String orderGenus) {
		this.orderGenus = orderGenus;
	}
	@Column(name = "orderCount")
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	@Column(name = "orderStatus")
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Column(name = "orderUser")
	public String getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
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
	@Column(name = "orderSeries")
	public String getOrderSeries() {
		return orderSeries;
	}
	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}
	@Column(name = "imgId")
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	@Column(name = "prodId")
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	@Column(name = "genusId")
	public String getGenusId() {
		return genusId;
	}
	public void setGenusId(String genusId) {
		this.genusId = genusId;
	}
	@Column(name = "expressName")
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	@Column(name = "cod")
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	@Column(name = "expressNo")
	public Long getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(Long expressNo) {
		this.expressNo = expressNo;
	}
	@Column(name = "orderAddress")
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	@Column(name = "orderMobile")
	public String getOrderMobile() {
		return orderMobile;
	}
	public void setOrderMobile(String orderMobile) {
		this.orderMobile = orderMobile;
	}
	@Column(name = "orderPostcode")
	public String getOrderPostcode() {
		return orderPostcode;
	}
	public void setOrderPostcode(String orderPostcode) {
		this.orderPostcode = orderPostcode;
	}
	@Column(name = "payType")
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	@Column(name = " expressStartDate")
	public Date getExpressStartDate() {
		return expressStartDate;
	}
	public void setExpressStartDate(Date expressStartDate) {
		this.expressStartDate = expressStartDate;
	}
	
	
}
