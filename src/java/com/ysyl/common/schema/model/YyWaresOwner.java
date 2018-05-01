package com.ysyl.common.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商户信息表
 * @author ysyl
 */
@Entity
@Table(name = "yywaresowner")
public class YyWaresOwner implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private String id;

	/**
	 * 商户中文名称
	 */
	private String ownerName;

	/**
	 * 商户注册地址
	 */
	private String registAddress;

	/**
	 * 商户注册人姓名
	 */
	private String registUserName;

	/**
	 * 商户注册人联系电话
	 */
	private String registPhone;
	
	/**
	 * 商户logoUrl图片地址（必须上传服务器，不允许使用外网地址）
	 */
	private String logoUrl;
	
	/**
	 * 商户logoUrl图片点击链接地址
	 */
	private String logoHref;
	
	/**
	 * 商户logoUrl图片地址（必须上传服务器，不允许使用外网地址）
	 */
	private String logoUrl1;
	
	/**
	 * 商户logoUrl图片点击链接地址
	 */
	private String logoHref1;
	
	/**
	 * 商户logoUrl图片地址（必须上传服务器，不允许使用外网地址）
	 */
	private String logoUrl2;
	
	/**
	 * 商户logoUrl图片点击链接地址
	 */
	private String logoHref2;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	public YyWaresOwner() {
	}

	/**       
	 * Id
	 */
	@Id
	@Column(name = "id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ownername")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "registaddress")
	public String getRegistAddress() {
		return registAddress;
	}

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	@Column(name = "registphone")
	public String getRegistPhone() {
		return registPhone;
	}

	public void setRegistPhone(String registPhone) {
		this.registPhone = registPhone;
	}

	@Column(name = "registusername")
	public String getRegistUserName() {
		return registUserName;
	}

	public void setRegistUserName(String registUserName) {
		this.registUserName = registUserName;
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

	@Column(name = "logourl")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "logohref")
	public String getLogoHref() {
		return logoHref;
	}

	public void setLogoHref(String logoHref) {
		this.logoHref = logoHref;
	}

	@Column(name = "logohref1")
	public String getLogoHref1() {
		return logoHref1;
	}

	public void setLogoHref1(String logoHref1) {
		this.logoHref1 = logoHref1;
	}

	@Column(name = "logohref2")
	public String getLogoHref2() {
		return logoHref2;
	}

	public void setLogoHref2(String logoHref2) {
		this.logoHref2 = logoHref2;
	}

	@Column(name = "logourl1")
	public String getLogoUrl1() {
		return logoUrl1;
	}

	public void setLogoUrl1(String logoUrl1) {
		this.logoUrl1 = logoUrl1;
	}

	@Column(name = "logourl2")
	public String getLogoUrl2() {
		return logoUrl2;
	}

	public void setLogoUrl2(String logoUrl2) {
		this.logoUrl2 = logoUrl2;
	}

}
