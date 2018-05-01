package com.ysyl.common.vo;

import com.ysyl.weixin.schema.model.YywxUserInfo;

/**
 * 微信网页授权跳转相关参数传输类
 * @author ysyl
 *
 */
public class WxFowardVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 微信传过来的code参数
	 */
	private String code;
	
	/**
	 * 微信传过来的state参数
	 */
	private String state;
	
	/**
	 * 类型，用以区分重定向到哪个页面
	 */
	private String type;
	
	/**
	 * 和type对应，不同类型inputId代表不同的值，例：type为微店的时候，inputId内容为商户ID
	 */
	private String inputId;
	
	/**
	 * 和type对应，不同类型inputId代表不同的值，例：type为微店的时候，inputName内容为商户中文名称
	 */
	private String inputName;
	
	/**
	 * 微信用户详细信息
	 */
	private YywxUserInfo yywxUserInfo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public YywxUserInfo getYywxUserInfo() {
		return yywxUserInfo;
	}

	public void setYywxUserInfo(YywxUserInfo yywxUserInfo) {
		this.yywxUserInfo = yywxUserInfo;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	
}
