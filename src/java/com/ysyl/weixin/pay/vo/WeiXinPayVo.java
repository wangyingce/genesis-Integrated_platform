package com.ysyl.weixin.pay.vo;

import com.ysyl.weixin.schema.model.YywxOrderInfo;

public class WeiXinPayVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String packageStr;
	private String signType;
	private String paySign;
	
	private String money;
	private String openId;
	private String businessNo;
	private String businessType;
	private String sourceType;
	private String ip;
	private String orderId;
	private String userCode;
	private String inKey;
	private String json;
	private String error;
	/**
	 * 微信回调地址
	 */
	private String notify_url;
	private YywxOrderInfo yywxOrderInfo;

	/**
	 * 商户名称
	 */
	private String nickName;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public YywxOrderInfo getYywxOrderInfo() {
		return yywxOrderInfo;
	}

	public void setYywxOrderInfo(YywxOrderInfo yywxOrderInfo) {
		this.yywxOrderInfo = yywxOrderInfo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getInKey() {
		return inKey;
	}

	public void setInKey(String inKey) {
		this.inKey = inKey;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
}
