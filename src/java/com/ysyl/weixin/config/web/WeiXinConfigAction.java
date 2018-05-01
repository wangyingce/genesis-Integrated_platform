package com.ysyl.weixin.config.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.common.util.CommonUtils;
import com.ysyl.weixin.config.ConfigVo;
import com.ysyl.weixin.pay.utils.SignUtil;

import ins.framework.web.Struts2Action;

public class WeiXinConfigAction extends Struts2Action{
	private String url;
	private CommonService commonService;
	
	

	public String configWeiXin(){
		try{
			Map<String, String> map = new HashMap<String, String>();
			map.put("url", url);
			//获取js-Ticket
			String appId = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.APPID).getConfigValue();
			String secret = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.APPSECRET).getConfigValue();
			String jsapi_ticket = CommonUtils.jsapiTicketGet(appId,secret);
			map.put("jsapi_ticket", jsapi_ticket);
			//随机数
			String noncestr = SignUtil.getRandom12();
			map.put("noncestr", noncestr);
			String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
			map.put("timestamp", timestamp);
			String sign = CommonUtils.getSign(map);
			
			ConfigVo vo  = new ConfigVo();
			vo.setAppId(appId);
			vo.setTimestamp(timestamp);
			vo.setNonceStr(noncestr);
			vo.setSignature(sign);
			vo.setDebug(false);
			List<String> jsList = new ArrayList<String>();
			jsList.add(WxCodeConst.jsSdkName.chooseImage);
			jsList.add(WxCodeConst.jsSdkName.previewImage);
			jsList.add(WxCodeConst.jsSdkName.uploadImage);
			jsList.add(WxCodeConst.jsSdkName.downloadImage);
			vo.setJsApiList(jsList);
			Gson g = new Gson();
			String json = g.toJson(vo);
			ServletActionContext.getResponse().getWriter().print(json);
		}catch(Exception e){
			e.printStackTrace();
			//微信配置失败
		}
		return null;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
