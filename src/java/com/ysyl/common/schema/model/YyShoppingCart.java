package com.ysyl.common.schema.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "yyshoppingcart")
public class YyShoppingCart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
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
	
	public YyShoppingCart() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YyShoppingCart")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "orderFormNo")
	public String getOrderFormNo() {
		return orderFormNo;
	}

	public void setOrderFormNo(String orderFormNo) {
		this.orderFormNo = orderFormNo;
	}

	@Column(name = "storeid")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	@Column(name = "waresid")
	public String getWaresID() {
		return waresID;
	}

	public void setWaresID(String waresID) {
		this.waresID = waresID;
	}
	@Column(name = "usercode")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inputdate")
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Column(name = "validstatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
