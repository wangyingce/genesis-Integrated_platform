package com.ysyl.mobile.orderFood.vo;

import java.math.BigDecimal;
import java.util.Date;

public class YyWaresDetailVo {
	/**
	 * 商品Id，序列自增长
	 */
	private Long id;
	/**
	 * 货物名称
	 */
	private String cargoName;

	/**
	 * 商品大类ID，对应yywaresclass表id
	 */
	private String classId;
	
	/**
	 * 商户id，yywaresowner表的id
	 */
	private String storeId;
	
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;

	/**
	 * 折扣价
	 */
	private BigDecimal discountPrice;
	
	/**
	 * 数量
	 */
	private BigDecimal quantity;
	
	/**
	 * 图标地址
	 */
	private String icoUrl;
	
	/**
	 * 货物状态 对应yydcode表 codetype=CargoStatus
	 */
	private String cargoStatus;
	
	/**
	 * 货物状态名称
	 */
	private String cargoStatusName;
	
	/**
	 * 创建人代码
	 */
	private String createCode;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	/**
	 * 备注说明
	 */
	private String remark;
	
	public YyWaresDetailVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	public String getCargoStatus() {
		return cargoStatus;
	}

	public void setCargoStatus(String cargoStatus) {
		this.cargoStatus = cargoStatus;
	}

	public String getCargoStatusName() {
		return cargoStatusName;
	}

	public void setCargoStatusName(String cargoStatusName) {
		this.cargoStatusName = cargoStatusName;
	}

	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
