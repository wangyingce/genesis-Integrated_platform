package com.ysyl.restFulServlet.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class WxInputVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 开发者微信号*/
	@XStreamAlias("ToUserName")
	private String toUserName;
	
	/** 发送方帐号（一个OpenID）*/
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	/** 消息创建时间 （整型）*/
	@XStreamAlias("CreateTime")
	private String createTime;
	
	/** 消息类型*/
	@XStreamAlias("MsgType")
	private String msgType;
	
	/** 开发者微信号*/
	@XStreamAlias("Content")
	private String context;
	
	/** 开发者微信号*/
	@XStreamAlias("MsgId")
	private String msgId;
	
	/** 事件类型*/
	@XStreamAlias("Event")
	private String event;
	
	/** 开发者微信号*/
	@XStreamAlias("EventKey")
	private String eventKey;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
