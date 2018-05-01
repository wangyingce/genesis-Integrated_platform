package com.ysyl.mobile.orderFood.vo;

import java.util.Date;

public class YyShoppingCartVo {
	/**
	 * 购物车Id，序列自增长
	 */
	private Long id;
	
	/**
	 * 订单编号
	 */
	private String orderFormNo;
	/**
	 * 商户id，yywaresowner表的id
	 */
	private String storeId;

	/**
	 * 商品id，yywaresdetail表的id
	 */
	private String waresID;
	
	/**
	 * 客户编号
	 */
	private String userCode;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 插入时间
	 */
	private Date inputDate;
	
	/**
	 * 购物车状态：00-未下单、01-已下单未支付、02-已支付、03-已删除
	 */
	private String validStatus;
	
	/**
	 * 备注说明
	 */
	private String remark;
	
	private YyWaresDetailVo yyWaresDetailVo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getWaresID() {
		return waresID;
	}

	public void setWaresID(String waresID) {
		this.waresID = waresID;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public YyWaresDetailVo getYyWaresDetailVo() {
		return yyWaresDetailVo;
	}

	public void setYyWaresDetailVo(YyWaresDetailVo yyWaresDetailVo) {
		this.yyWaresDetailVo = yyWaresDetailVo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderFormNo() {
		return orderFormNo;
	}

	public void setOrderFormNo(String orderFormNo) {
		this.orderFormNo = orderFormNo;
	}
	
}
