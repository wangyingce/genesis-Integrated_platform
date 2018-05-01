package com.ysyl.common.vo;

/**
 * 发送消息vo，通用支持短信，rtx，钉钉等
 * @author lenovo
 *
 */
public class SendMessageVobak implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 短信模板 详见CodeConst.java-ShortMessageTemplate*/
	private String messageTemplate;
	
	/** 用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用*/
	/** 根据商户id获取短信签名*/
	private String inputId;
	
	/** 验证码*/
	private String verifyNo;
	
	/** 内容*/
	private String product;
	
	/** 手机号码，多个手机号码的话131111111,132111111,1331111111组合*/
	private String phoneNumber;
	
	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getVerifyNo() {
		return verifyNo;
	}

	public void setVerifyNo(String verifyNo) {
		this.verifyNo = verifyNo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
