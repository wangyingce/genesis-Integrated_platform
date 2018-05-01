package com.ysyl.weixin.pay.web;
import ins.framework.web.Struts2Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ysyl.common.CodeConst;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.search.service.facade.SearchService;
import com.ysyl.weixin.order.service.facade.WeiXinOrderInfoService;
import com.ysyl.weixin.pay.service.facade.WeiXinPayService;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;

public class WeiXinPayAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private String money;
	private String openId;
	private String businessNo;
	private Logger logger = Logger.getLogger(WeiXinPayAction.class);
	private WeiXinOrderInfoService weiXinOrderInfoService;
	private WeiXinPayService weiXinPayService;
	private SearchService searchService;
	
	

	
	
	public String payWeiXin(){
		logger.debug(openId);
		logger.debug(money);
		logger.debug(businessNo);
		String ip = this.getRequest().getRemoteAddr();
		WeiXinPayVo inputVo = new WeiXinPayVo();
		inputVo.setOpenId(openId);
		inputVo.setMoney(money);
		inputVo.setBusinessNo(businessNo);
		inputVo.setIp(ip);
		try {
			String json = weiXinPayService.payWeiXinForMerchant(inputVo);
			logger.debug(json);
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			searchService.saveErrorMessage(e,"", "WeiXinPayAction.payWeiXin");
			try {
				ServletActionContext.getResponse().getWriter().print("微信支付失败");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String payDonRef(){
		//根据request转换xml
		String postXml = ServletUtil.postXmlByRequest(this.getRequest());
//		searchService.saveInterfMessage(CodeConst.InterfType.weixinpay, null, postXml);
		logger.debug("微信支付成功后回调postXml="+postXml);
		String returnXml = "";
		try {
			//保存微信支付成功后回调
			weiXinOrderInfoService.saveUserInfoByXml(postXml);
			returnXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			ServletUtil.backResponse(this.getResponse(),returnXml);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			searchService.saveErrorMessage(e1,"", "WeiXinPayAction.payDonRef");
		}
		return null;
	}
	


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	public WeiXinOrderInfoService getWeiXinOrderInfoService() {
		return weiXinOrderInfoService;
	}

	public void setWeiXinOrderInfoService(
			WeiXinOrderInfoService weiXinOrderInfoService) {
		this.weiXinOrderInfoService = weiXinOrderInfoService;
	}
	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public WeiXinPayService getWeiXinPayService() {
		return weiXinPayService;
	}

	public void setWeiXinPayService(WeiXinPayService weiXinPayService) {
		this.weiXinPayService = weiXinPayService;
	}
	
	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}
