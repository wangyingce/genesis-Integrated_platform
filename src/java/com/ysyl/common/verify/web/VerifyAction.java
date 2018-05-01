package com.ysyl.common.verify.web;

import javax.servlet.http.HttpServletResponse;

import ins.framework.web.Struts2Action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.common.verify.service.facade.VerifyService;

public class VerifyAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(VerifyAction.class);
	//类型，用以区分重定向到哪个页面
	private String type;
	
	//和type对应，不同类型inputId代表不同的值，例：type为微店的时候，inputId内容为商户ID
	private String inputId;
	
	//手机号
	private String mobile;
	
	//验证码
	private String verifyNo;
	
	private VerifyService verifyService;

	
	/***
	 * 获取验短信证码
	 * */
	public String receiveVerify(){
		try {
			VerifyVo verifyVo = new VerifyVo();
			verifyVo.setPhoneNumber(mobile);
			verifyVo.setType(type);
			verifyVo = verifyService.receiveVerify(verifyVo);
			
			Gson g = new Gson();
			String json = g.toJson(verifyVo);
			logger.debug(json);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json; charset=UTF-8"); // 处理中文问题的必须的代码
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 校验验短信证码
	 * */
	public String confirmVerify(){
		try {
			VerifyVo verifyVo = new VerifyVo();
			verifyVo.setPhoneNumber(mobile);
			verifyVo.setType(type);
			verifyVo.setVerifyNo(verifyNo);
			verifyVo.setInputId(inputId);
			verifyVo = verifyService.confirmVerify(verifyVo);
			Gson g = new Gson();
			String json = g.toJson(verifyVo);
			logger.debug(json);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/json; charset=UTF-8"); // 处理中文问题的必须的代码
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}
	
	public VerifyService getVerifyService() {
		return verifyService;
	}

	public void setVerifyService(VerifyService verifyService) {
		this.verifyService = verifyService;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyNo() {
		return verifyNo;
	}

	public void setVerifyNo(String verifyNo) {
		this.verifyNo = verifyNo;
	}

}