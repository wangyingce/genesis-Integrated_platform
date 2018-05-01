package com.ysyl.common.schema.model;

import java.math.BigDecimal;
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

/**
 * 商品大类实体类
 * @author ysyl
 */
@Entity
@Table(name = "yywaresdetail")
public class YyWaresDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

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
	
	public YyWaresDetail() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YyWaresDetail")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "createcode")
	public String getCreateCode() {
		return this.createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "validstatus")
	public String getValidStatus() {
		return this.validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name = "cargoname")
	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	@Column(name = "cargostatus")
	public String getCargoStatus() {
		return cargoStatus;
	}

	public void setCargoStatus(String cargoStatus) {
		this.cargoStatus = cargoStatus;
	}

	@Column(name = "classid")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Column(name = "discountprice")
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Column(name = "icourl")
	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	@Column(name = "quantity")
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "unitprice")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "storeid")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name = "cargostatusname")
	public String getCargoStatusName() {
		return cargoStatusName;
	}

	public void setCargoStatusName(String cargoStatusName) {
		this.cargoStatusName = cargoStatusName;
	}
}
