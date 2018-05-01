package com.ysyl.weixin.message.vo;

/**
 * 微信发送消息Vo
 * @author ysyl
 */
public class WxSendMessageVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String touser;
	
	private String msgtype;
	
	private WxSendMessageDetailtVo text;
	
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
	public WxSendMessageDetailtVo getText() {
		return text;
	}
	public void setText(WxSendMessageDetailtVo text) {
		this.text = text;
	}
}
