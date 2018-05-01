package com.ysyl.mobile.photograph.vo;

import java.util.List;

import com.ysyl.common.schema.model.YyDcode;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyPgIndex;
import com.ysyl.common.schema.model.YyPgMyOrder;
import com.ysyl.common.schema.model.YyPgOrderDate;
import com.ysyl.common.schema.model.YyPgOrderDetail;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.schema.model.YyWaresOwner;
import com.ysyl.common.verify.VerifyVo;

/**
 * 摄影工作室传送vo
 * @author lenovo
 *
 */
public class PhotoGraphVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 电话号码
	 */
	private String phoneNumber;
	
	/**
	 * 商户id，唯一区分各个摄影工作室
	 */
	private String storeId;
	
	/**
	 * 商户名称
	 */
	private String storeName;
	
	/**
	 * 验证对象
	 */
	private VerifyVo verifyVo;
	
	/**
	 * 微信openid
	 */
	private String openId;
	
	/** 
	 * 预约时间表的id
	 */
	private String checkTimeId;
	
	/**
	 * 用户代码
	 */
	private String userCode;
	/**
	 * 用户信息对象
	 */
	private YyUser yyUser;
	/**
	 * 业务入参
	 */
	private String inKey;
	/**
	 * 预约日期
	 */
	private List<YyPgOrderDate> yyPgOrderDates;
	
	/**
	 * 城市
	 */
	private List<YyDcode> yyDcodeCities;
	
	/**
	 * 拍照类型
	 */
	private List<YyDcode> yyDcodePhotoTypes;
	
	/**
	 * 预约明细说明
	 */
	private YyPgOrderDetail yyPgOrderDetail;
	
	/**
	 * 商户信息
	 */
	private YyWaresOwner yyWaresOwner;
	
	/**
	 * 支付金额
	 */
	private String payMoney;
	
	/**
	 * 支付金额
	 */
	private String ip;
	
	/**
	 * json串
	 */
	private String json;
	
	/**支付状态*/
	private String payState;
	
	/**订单id*/
	private String orderId;
	
	/**是否有订单 0:无，1：有*/
	private String isOrder="0";
	
	/**
	 * 我的订单信息
	 */
	private List<YyPgMyOrder> yyPgMyOrders;
	
	/**
	 * 商户首页信息
	 */
	private List<YyPgIndex> yyPgIndexs;
	
	/**
	 * 我的底片信息
	 */
	private List<YyImageFile> yyImageFiles;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public VerifyVo getVerifyVo() {
		return verifyVo;
	}

	public void setVerifyVo(VerifyVo verifyVo) {
		this.verifyVo = verifyVo;
	}

	public YyUser getYyUser() {
		return yyUser;
	}

	public void setYyUser(YyUser yyUser) {
		this.yyUser = yyUser;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public List<YyDcode> getYyDcodePhotoTypes() {
		return yyDcodePhotoTypes;
	}

	public void setYyDcodePhotoTypes(List<YyDcode> yyDcodePhotoTypes) {
		this.yyDcodePhotoTypes = yyDcodePhotoTypes;
	}

	public List<YyDcode> getYyDcodeCities() {
		return yyDcodeCities;
	}

	public void setYyDcodeCities(List<YyDcode> yyDcodeCities) {
		this.yyDcodeCities = yyDcodeCities;
	}

	public YyPgOrderDetail getYyPgOrderDetail() {
		return yyPgOrderDetail;
	}

	public void setYyPgOrderDetail(YyPgOrderDetail yyPgOrderDetail) {
		this.yyPgOrderDetail = yyPgOrderDetail;
	}

	public List<YyPgOrderDate> getYyPgOrderDates() {
		return yyPgOrderDates;
	}

	public void setYyPgOrderDates(List<YyPgOrderDate> yyPgOrderDates) {
		this.yyPgOrderDates = yyPgOrderDates;
	}

	public String getCheckTimeId() {
		return checkTimeId;
	}

	public void setCheckTimeId(String checkTimeId) {
		this.checkTimeId = checkTimeId;
	}

	public YyWaresOwner getYyWaresOwner() {
		return yyWaresOwner;
	}

	public void setYyWaresOwner(YyWaresOwner yyWaresOwner) {
		this.yyWaresOwner = yyWaresOwner;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public List<YyPgMyOrder> getYyPgMyOrders() {
		return yyPgMyOrders;
	}

	public void setYyPgMyOrders(List<YyPgMyOrder> yyPgMyOrders) {
		this.yyPgMyOrders = yyPgMyOrders;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIsOrder() {
		return isOrder;
	}

	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}

	public List<YyPgIndex> getYyPgIndexs() {
		return yyPgIndexs;
	}

	public void setYyPgIndexs(List<YyPgIndex> yyPgIndexs) {
		this.yyPgIndexs = yyPgIndexs;
	}

	public String getInKey() {
		return inKey;
	}

	public void setInKey(String inKey) {
		this.inKey = inKey;
	}

	public List<YyImageFile> getYyImageFiles() {
		return yyImageFiles;
	}

	public void setYyImageFiles(List<YyImageFile> yyImageFiles) {
		this.yyImageFiles = yyImageFiles;
	}

}
