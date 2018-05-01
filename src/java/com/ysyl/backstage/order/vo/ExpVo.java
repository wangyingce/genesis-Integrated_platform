package com.ysyl.backstage.order.vo;

import java.util.Date;

/**
 * 后台系统产品VO
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class ExpVo {
	
	private Long expressId;
	private Long expressNo;
	private String expressName;
	private String expressInfo;
	private String inputtime;
	private String flag;
	private String validate;
	public Long getExpressId() {
		return expressId;
	}
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}
	public Long getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(Long expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(String expressInfo) {
		this.expressInfo = expressInfo;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}

	
}
