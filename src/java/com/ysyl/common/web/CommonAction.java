package com.ysyl.common.web;

import ins.framework.cache.CacheManager;
import ins.framework.web.Struts2Action;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.ysyl.common.CodeConst;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.vo.WxFowardVo;
import com.ysyl.mobile.wxstore.service.facade.MobileStoreService;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.schema.model.YywxMerchant;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class CommonAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CommonAction.class);
	//微信网页授权code
	private String code;
	
	//微信网页授权state
	private String state;
	
	//类型，用以区分重定向到哪个页面
	private String type;
	
	//和type对应，不同类型inputId代表不同的值，例：type为微店的时候，inputId内容为商户ID
	private String inputId;
	
	private CommonService commonService;
	
	private MobileStoreService mobileStoreService;
	
	private WxFowardVo wxFowardVo;
	
	private MerchantService merchantService;
	
	//校验是否微信浏览器
	public boolean isWeiXin(){
		//获取系统环境变量
		String environmenTypeCode = System.getProperty("environmenTypeCode");
		//如果不是开发环境或者测试环境那么校验，否则不校验
		if(!CodeConst.EnvironmenTypeCode.dev.equals(environmenTypeCode) && !CodeConst.EnvironmenTypeCode.test.equals(environmenTypeCode)){
			String ua = this.getRequest().getHeader("user-agent").toLowerCase();
			if (ua.indexOf("micromessenger") ==-1) {//不是微信浏览器
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public String authorizeWeb(){
		if (this.isWeiXin()==false) {// 不是微信浏览器
			return "notweixin";
		}
		
		try {
			String ip = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "");
		
			StringBuffer bakUrlsb = new StringBuffer();
			bakUrlsb.append(ip);
			bakUrlsb.append("/cm/cf.do?inputId=");
			bakUrlsb.append(inputId);
			bakUrlsb.append("&type=");
			bakUrlsb.append(type);
			logger.debug(bakUrlsb.toString());
			String backUrl = URLEncoder.encode(bakUrlsb.toString());
			logger.debug(backUrl);
			//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
			String appid = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.APPID).getConfigValue();
			StringBuffer wxAuthorizesb = new StringBuffer();
			wxAuthorizesb.append(commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WXauthorize, ""));
			wxAuthorizesb.append("?appid=");
			wxAuthorizesb.append(appid);
			wxAuthorizesb.append("&redirect_uri=");
			wxAuthorizesb.append(backUrl);
			wxAuthorizesb.append("&response_type=code&scope=snsapi_userinfo&state=");
			if(state!=null&&!"".equals(state)){
				wxAuthorizesb.append(state);
			}else{
				wxAuthorizesb.append("STATE");
			}
			wxAuthorizesb.append("#wechat_redirect");
			logger.debug(wxAuthorizesb.toString());
			this.getResponse().sendRedirect(wxAuthorizesb.toString());
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String init(){
		if (this.isWeiXin()==false) {// 不是微信浏览器
			return "notweixin";
		}
		return type;
	}
	
	
	/**
	 * 微信网页授权跳转URL
	 * @return 跳转路径
	 */
	public String fowardUrl(){
		if (this.isWeiXin()==false) {// 不是微信浏览器
			return "notweixin";
		}
		//调用业务逻辑层
		wxFowardVo = new WxFowardVo();
		YywxUserInfo yywxUserInfo = null;
		wxFowardVo.setYywxUserInfo(yywxUserInfo);
		wxFowardVo.setCode(code);
		wxFowardVo.setState(state);
		wxFowardVo.setInputId(inputId);
		wxFowardVo.setType(type);
		try {
			//先根据具体的类型调用不同的方法
			//如果是点击的微信小店菜单
			if(CodeConst.UrlType.ws.equals(wxFowardVo.getType()) || CodeConst.UrlType.wp.equals(wxFowardVo.getType())
					|| CodeConst.UrlType.wpg.equals(wxFowardVo.getType()) || CodeConst.UrlType.wpgp.equals(wxFowardVo.getType())){
				//通过微信授权获取用户信息
				wxFowardVo = commonService.fowardUrlForWx(wxFowardVo,true);
				//如果是小店再调用小店初始化方法
				if(CodeConst.UrlType.ws.equals(wxFowardVo.getType())){
					wxFowardVo = mobileStoreService.initStore(wxFowardVo);
				}else if(CodeConst.UrlType.wp.equals(wxFowardVo.getType())){//微信支付校验商户审核状态
					YywxMerchant merchant = merchantService.queryMerchantByMerchantId(inputId);
					//未审核通过不能支付 跳转未审核通过提示页面
					if(CodeConst.Uwflag.wait.equals(merchant.getUwflag())){
						String url = "/pages/weixin/merchant/merchantWaiting.html";
						this.getResponse().sendRedirect(url);
					}else if(CodeConst.Uwflag.check.equals(merchant.getUwflag())){//未短信验证跳转短信验证页面
						String url = "/cm/cf.do?inputId="+inputId+"&type=mv";
						this.getResponse().sendRedirect(url);
					}
				}
				//登录后保存session
				if(wxFowardVo.getYywxUserInfo()!= null){
					commonService.setSession(this.getRequest().getSession(),wxFowardVo.getYywxUserInfo().getOpenid(),"","");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//根据类型跳转到不同页面
		return type;
	}
	
	
	/**
	 * 微信网页授权跳转URL
	 * @return 跳转路径
	 */
	public String pgForwardUrl(){
		if (this.isWeiXin()==false) {// 不是微信浏览器
			return "notweixin";
		}
		
		return type;
	}
	
	
	public void clearMem(){
		CacheManager.clearAllCacheManager();
		System.out.println("clear Memery!");
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

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

	public WxFowardVo getWxFowardVo() {
		return wxFowardVo;
	}

	public void setWxFowardVo(WxFowardVo wxFowardVo) {
		this.wxFowardVo = wxFowardVo;
	}

	public MobileStoreService getMobileStoreService() {
		return mobileStoreService;
	}

	public void setMobileStoreService(MobileStoreService mobileStoreService) {
		this.mobileStoreService = mobileStoreService;
	}

	public MerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

}