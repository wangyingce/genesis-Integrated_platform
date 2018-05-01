package com.ysyl.weixin.order.vo;

import com.ysyl.common.vo.PageVo;

public class OrderQueryVo extends PageVo{
	private String payDate1;
	private String payDate2;

	public String getPayDate1() {
		return payDate1;
	}

	public void setPayDate1(String payDate1) {
		this.payDate1 = payDate1;
	}

	public String getPayDate2() {
		return payDate2;
	}

	public void setPayDate2(String payDate2) {
		this.payDate2 = payDate2;
	}
}
