package com.ysyl.weixin.message.vo;

/**
 * 微信发送图文消息Vo
 * @author ysyl
 */
public class WxSendPhotoMessageVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String touser;
	
	private String msgtype;
	
	private WxSendPhotoMessageListVo news;
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public WxSendPhotoMessageListVo getNews() {
		return news;
	}
	public void setNews(WxSendPhotoMessageListVo news) {
		this.news = news;
	}
	
}
