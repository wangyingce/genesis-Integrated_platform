package com.ysyl.weixin;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信入参基础代码类
 * @author ysyl
 *
 */
public class WxCodeConst {
	
	/**
	 * 消息类型
	 */
	public static final class MsgType {
		/** 事件推送 */
		public static final String event = "event";
		/** 消息 */
		public static final String text = "text";
		/** 图文消息 */
		public static final String news = "news";
	}
	
	/**
	 * 事件类型
	 */
	public static final class Event {
		/** 订阅 */
		public static final String Subscribe = "subscribe";
		/** 取消订阅 */
		public static final String Unsubscribe = "unsubscribe";
		/** 取消订阅 */
		public static final String Click = "CLICK";
	}
	
	/**
	 * 事件类型
	 */
	public static final class EventKey {
		/** 联系我们按钮 */
		public static final String CallUs = "CallUs";
	}
	
	/**
	 * 消息模板内部Id,对应yydtype表WxTemplateId
	 */
	public static final class WxTemplateId {
		/** 微信支付成功通知模板-IT科技 */
		public static final String WxTemplateId_1 = "1";
		/** 顾客预约提醒-IT科技 */
		public static final String WxTemplateId_2 = "2";
		/** 预约取消通知-IT科技 */
		public static final String WxTemplateId_3 = "3";
	}
	
	/**
	 * 微信通讯返回状态
	 * */
	public static final class WxState{
		/** 成功 */
		public static final String success = "SUCCESS";
		/** 失败 */
		public static final String fail = "fail";
	}
	
	/**
	 * 微信支付交易状态
	 * */
	public static final class WxPayState{
		/** SUCCESS—支付成功 */
		public static final String SUCCESS = "SUCCESS";
		/** REFUND—转入退款  */
		public static final String REFUND = "REFUND";
		/** NOTPAY—未支付 */
		public static final String NOTPAY = "NOTPAY";
		/** CLOSED—已关闭 */
		public static final String CLOSED = "CLOSED";
		/** REVOKED—已撤销（刷卡支付） */
		public static final String REVOKED = "REVOKED";
		/** USERPAYING--用户支付中 */
		public static final String USERPAYING = "USERPAYING";
		/** PAYERROR--支付失败(其他原因，如银行返回失败) */
		public static final String PAYERROR = "PAYERROR";
	}
	
	/**
	 * 微信支付交易状态与交易状态对照
	 * */
	public static Map<String,String> WxPayStateMap = new HashMap<String,String>();
	static {
		/** SUCCESS—支付成功 */
		WxPayStateMap.put("SUCCESS",payState.payState11);
		/** REFUND—转入退款  */
		WxPayStateMap.put("REFUND",payState.payState12);
		/** NOTPAY—未支付 */
		WxPayStateMap.put("NOTPAY",payState.payState01);
		/** CLOSED—已关闭 */
		WxPayStateMap.put("CLOSED",payState.payState13);
		/** REVOKED—已撤销（刷卡支付） */
		WxPayStateMap.put("REVOKED",payState.payState14);
		/** USERPAYING--用户支付中 */
		WxPayStateMap.put("USERPAYING",payState.payState15);
		/** PAYERROR--支付失败(其他原因，如银行返回失败) */
		WxPayStateMap.put("PAYERROR",payState.payState99);
		
	}
	
	/**
	 * 微信js-sdk接口列表
	 * */
	public static final class payState{
		/** SUCCESS—支付成功 */
		public static final String payState11 = "11";
		/** REFUND—转入退款  */
		public static final String payState12 = "12";
		/** NOTPAY—未支付 */
		public static final String payState01 = "01";
		/** CLOSED—已关闭 */
		public static final String payState13 = "13";
		/** REVOKED—已撤销（刷卡支付） */
		public static final String payState14 = "14";
		/** USERPAYING--用户支付中 */
		public static final String payState15 = "15";
		/** PAYERROR--支付失败(其他原因，如银行返回失败) */
		public static final String payState99 = "99";
	}
	
	/**
	 * 微信js-sdk接口列表
	 * */
	public static final class jsSdkName{
		/** 图片选择 */
		public static final String chooseImage = "chooseImage";
		/** 图片预览 */
		public static final String previewImage = "previewImage";
		/** 图片上传 */
		public static final String uploadImage = "uploadImage";
		/** 图片下载 */
		public static final String downloadImage = "downloadImage";
	}
}
