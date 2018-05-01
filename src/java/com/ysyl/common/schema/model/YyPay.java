package com.ysyl.common.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 支付订单表
 * @author ysyl
 */
@Entity
@Table(name = "yypay")
public class YyPay implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private Long id;

	/**
	 * 业务表id，对应businesstype
	 */
	private String businessNo;

	/**
	 * 支付渠道
	 * wx|对应yywxoderinfo表支付id。
	 * aliPay|对应阿里支付表id（暂无）。
	 * none|其它
	 */
	private String businessType;
	
	/**
	 * 业务来源-是什么店
	 * pg|摄影工作室
	 * store|微信商城
	 * pay|微信支付-默认
	 */
	private String sourceType;
	
	/**
	 * 和sourceType对应
	 * pg|摄影工作室 存储yypgordertime表id
	 * store|微信商城 待定
	 * pay|微信支付-默认 待定
	 */
	private String inKey;
	
	/**
	 * 支付用户代码，对应yyuser表usercode，如果该表没有，则可存储openid等唯一标识
	 */
	private String userCode;

	/**
	 * 支付状态-01|待支付，11|支付成功,99|支付失败
	 */
	private String payState;

	/**
	 * 核对状态-00|待核对，01|待支付，11|支付成功，该字段为对账后回写字段，默认为00
	 */
	private String checkState;
	
	/**
	 * 支付金额,单位元
	 */
	private String payFee;
	
	/**
	 * 支付币别 默认为人名币CNY，对应yydcode表codetype=Currency
	 */
	private String currency;

	/**
	 * 支付成功时间
	 */
	private Date paySuccessTime;
	
	/**
	 * 商户表id
	 */
	private String storeId;
	
	/**
	 * 商户名称
	 */
	private String storeName;
	
	/**
	 * 插入时间
	 */
	private Date createTime;
	
	/**
	 * 有效标志:0-无效，1-有效 默认有效
	 */
	private String validStatus;
	
	
	public YyPay() {
	}

	/**       
	 * Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "businessno")
	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	@Column(name = "businesstype")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "checkstate")
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	@Column(name = "currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "payfee")
	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}

	@Column(name = "paystate")
	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "paysuccesstime")
	public Date getPaySuccessTime() {
		return paySuccessTime;
	}

	public void setPaySuccessTime(Date paySuccessTime) {
		this.paySuccessTime = paySuccessTime;
	}

	@Column(name = "storeid")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name = "storename")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "sourcetype")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "usercode")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "inkey")
	public String getInKey() {
		return inKey;
	}

	public void setInKey(String inKey) {
		this.inKey = inKey;
	}
}
