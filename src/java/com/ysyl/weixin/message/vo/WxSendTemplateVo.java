package com.ysyl.weixin.message.vo;

/**
 * 微信发送模板对象
 * @author ysyl
 *
 */
public class WxSendTemplateVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 用户 open_id*/
	private String touser;
	
	/** 微信模板ID*/
	private String template_id;
	
	/** 模板点击跳转地址*/
	private String url;
	
	/** 发送明细数据*/
	private WxSendTemlateDetailVo data;

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WxSendTemlateDetailVo getData() {
		return data;
	}

	public void setData(WxSendTemlateDetailVo data) {
		this.data = data;
	}
}
