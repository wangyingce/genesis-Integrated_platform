package com.ysyl.mobile.pay.web;

import ins.framework.web.Struts2Action;

import com.ysyl.mobile.pay.service.facade.PayService;

public class PayAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	private PayService payService;
	
	
	
	
	
	
	
	
	

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

}