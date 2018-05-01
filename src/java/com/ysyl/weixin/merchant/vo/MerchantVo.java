package com.ysyl.weixin.merchant.vo;

import java.util.Date;

import com.ysyl.common.vo.PageVo;

public class MerchantVo extends PageVo{

	private String merchantId;
	// 商户昵称
	private String nickName;
	// 商户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String sex;
	// 国家，如中国为CN
	private String country;
	// 商户个人资料填写的省份
	private String province;
	// 普通商户个人资料填写的城市
	private String city;
	// 商户具体位置信息
	private String address;
	// 商户
	private String corporation;
	// 商户证件号码（身份证）
	private String identifyNumber;
	// 商户类别
	private String customerNature;
	// 商户银行
	private String bankCname;
	// 商户银行类别
	private String bankType;
	// 商户银行账号
	private String account;
	// 商户银行信任标志
	private String enTrustFlag;
	// 商户资产额度
	private String amount;
	// 商户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），商户没有头像时该项为空。若商户更换头像，原有头像URL将失效。
	private String headimgurl;
	// 只有在商户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取商户个人信息（UnionID机制）
	private String unionid;
	// 商户备注
	private String remark;
	// 商户是否订阅该公众号标识，值为0时，代表此商户没有关注该公众号，拉取不到其余信息。
	// private String subscribe;
	// 商户关注时间，为时间戳。如果商户曾多次关注，则取最后关注时间
	// private String subscribe_time;
	// 商户的语言，简体中文为zh_CN
	private String language;
	// 备用标志
	private String flag;
	// 有效标志
	private String valid;
	//查询时间st
	private String queryDate1;
	//查询时间ed
	private String queryDate2;
	//数据生成时间
	private String inputTime;
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
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getIdentifyNumber() {
		return identifyNumber;
	}
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}
	public String getCustomerNature() {
		return customerNature;
	}
	public void setCustomerNature(String customerNature) {
		this.customerNature = customerNature;
	}
	public String getBankCname() {
		return bankCname;
	}
	public void setBankCname(String bankCname) {
		this.bankCname = bankCname;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEnTrustFlag() {
		return enTrustFlag;
	}
	public void setEnTrustFlag(String enTrustFlag) {
		this.enTrustFlag = enTrustFlag;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getQueryDate1() {
		return queryDate1;
	}
	public void setQueryDate1(String queryDate1) {
		this.queryDate1 = queryDate1;
	}
	public String getQueryDate2() {
		return queryDate2;
	}
	public void setQueryDate2(String queryDate2) {
		this.queryDate2 = queryDate2;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	public String getQrcodePath() {
		return qrcodePath;
	}
	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}
	public String getRegistermobile() {
		return registermobile;
	}
	public void setRegistermobile(String registermobile) {
		this.registermobile = registermobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExopenid() {
		return exopenid;
	}
	public void setExopenid(String exopenid) {
		this.exopenid = exopenid;
	}
	public String getUwflag() {
		return uwflag;
	}
	public void setUwflag(String uwflag) {
		this.uwflag = uwflag;
	}
}
