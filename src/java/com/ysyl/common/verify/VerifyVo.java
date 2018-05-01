package com.ysyl.common.verify;

public class VerifyVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 发送成功:success 发送失败:fail*/
	private String code;
	/** 发送结果消息*/
	private String message;
	
	private String type;
	
	/** 短信模板 详见CodeConst.java-ShortMessageTemplate*/
	private String messageTemplate;
	
	/** 用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用*/
	/** 根据商户id获取短信签名*/
	private String inputId;
	
	/** 验证码*/
	private String verifyNo;
	
	/** 发送的内容*/
	private String product;
	
	/** 手机号码，多个手机号码的话131111111,132111111,1331111111组合*/
	private String phoneNumber;
	

	
	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVerifyNo() {
		return verifyNo;
	}

	public void setVerifyNo(String verifyNo) {
		this.verifyNo = verifyNo;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
