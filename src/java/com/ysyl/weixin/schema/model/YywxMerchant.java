package com.ysyl.weixin.schema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户model
 * @author Scorpiō
 * @category Amicus Plato, sed magis amica veritas
 */
@Entity
@Table(name = "yywxmerchant")
public class YywxMerchant implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 商户的唯一标识
	private String merchantId;
	// 商户昵称
	private String nickName;
	// 商户
	private String corporation;
	// 商户证件号码（身份证）
	private String identifyNumber;
	// 商户银行
	private String bankCname;
	//银行账号
	private String account;
	// 商户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），商户没有头像时该项为空。若商户更换头像，原有头像URL将失效。
	private String headimgurl;
	// 备用标志
	private String flag;
	// 有效标志
	private String valid;
	//录入时间
	private Long inputTime;
	//二维码地址
	private String qrcodePath;
	//店铺注册电话
	private String registermobile;
	//密码（备用）
	private String password;
	//extend person wx-openid
	private String exopenid;
	//审核通过标识，1pass，3waiting
	private String uwflag;
	private String address;

	@Id
	@Column(name = "MERCHANTID")
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name = "NICKNAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	@Column(name = "HEADIMGURL")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}


	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "VALID")
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "CORPORATION")
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	@Column(name = "identifyNumber")
	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	@Column(name = "bankCname")
	public String getBankCname() {
		return bankCname;
	}

	public void setBankCname(String bankCname) {
		this.bankCname = bankCname;
	}

	@Column(name = "inputTime")
	public Long getInputTime() {
		return inputTime;
	}

	public void setInputTime(Long inputTime) {
		this.inputTime = inputTime;
	}

	@Column(name = "qrcodePath")
	public String getQrcodePath() {
		return qrcodePath;
	}

	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}

	@Column(name = "registermobile")
	public String getRegistermobile() {
		return registermobile;
	}

	public void setRegistermobile(String registermobile) {
		this.registermobile = registermobile;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "exopenid")
	public String getExopenid() {
		return exopenid;
	}

	public void setExopenid(String exopenid) {
		this.exopenid = exopenid;
	}

	@Column(name = "uwflag")
	public String getUwflag() {
		return uwflag;
	}

	public void setUwflag(String uwflag) {
		this.uwflag = uwflag;
	}
	
	@Column(name = "account")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}