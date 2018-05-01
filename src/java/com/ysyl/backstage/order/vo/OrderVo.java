package com.ysyl.backstage.order.vo;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class OrderVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Integer value;
	
	
	private Long orderId;
	private String orderContent;
	private BigDecimal orderAmount;
	private BigDecimal orderDiscount;
	private String orderGenus;
	private Integer orderCount;
	private String orderStatus;
	private String orderUser;
	private String inputtime;
	private String validate;
	private String flag;
	private String orderSeries;
	private String imgId;
	private String prodId;
	private String genusId;
	private String expressName;
	private Long expressNo;
	private String cod;
	private String payType;
	private String expressStartDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(BigDecimal orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public String getOrderGenus() {
		return orderGenus;
	}
	public void setOrderGenus(String orderGenus) {
		this.orderGenus = orderGenus;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
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
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getOrderSeries() {
		return orderSeries;
	}
	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getGenusId() {
		return genusId;
	}
	public void setGenusId(String genusId) {
		this.genusId = genusId;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public Long getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(Long expressNo) {
		this.expressNo = expressNo;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getExpressStartDate() {
		return expressStartDate;
	}
	public void setExpressStartDate(String expressStartDate) {
		this.expressStartDate = expressStartDate;
	}
	
}