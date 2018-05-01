package com.ysyl.weixin.pay.service.spring;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.ysyl.common.CodeConst;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.mobile.pay.service.facade.PayService;
import com.ysyl.search.service.facade.SearchService;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.order.service.facade.WeiXinOrderInfoService;
import com.ysyl.weixin.pay.service.facade.WeiXinPayService;
import com.ysyl.weixin.pay.utils.GetWxOrderno;
import com.ysyl.weixin.pay.utils.SignUtil;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;
import com.ysyl.weixin.schema.model.YywxMerchant;
import com.ysyl.weixin.schema.model.YywxOrderInfo;

public class WeiXinPayServiceSpringImpl implements WeiXinPayService {
	private Logger logger = Logger.getLogger(WeiXinPayServiceSpringImpl.class);
	private CommonService commonService;
	private WeiXinOrderInfoService weiXinOrderInfoService; 
	private MerchantService merchantService;
	private PayService payService;
	private SearchService searchService;
	

	public static void main(String []args){
		String strReq = SignUtil.getRandom12();
		System.out.println("appId=wx8d56592033704971");
		//9YJqjoSAHjuD-nABm0HiHHX60y9NGbcFctPgHRusPfbLcsKeS--ZuxRiPqeMt3ovIpEZlz-vCmbWqV2EDtBg9nQliqr6PKVxSaofbCFEBwURQ6Pgw801znPbh2h0kub6SDLeAIAOZH
		System.out.println("timestamp="+String.valueOf(System.currentTimeMillis() / 1000));
		System.out.println("nonceStr="+strReq);
		String ss="jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VI1ZvrBrgsxv1rNKX4Zp7ZKtG38ixlCgHp2CLXAjFfdtCXR3HYDnEgGUr3Sd7hRFlg&noncestr=109086138295&timestamp=1461849604&url=http://www.lmeij.com/ysyl/pages/weixinMobile/test/config.html";
		 MessageDigest md = null;
	        String tmpStr = null;
	 
	        try {
	            md = MessageDigest.getInstance("SHA-1");
	            // 将三个参数字符串拼接成一个字符串进行sha1加密
	            byte[] digest = md.digest(ss.getBytes());
	            tmpStr = SignUtil.byteToStr(digest);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        System.out.println("sha1="+tmpStr);
	}

	private WeiXinPayVo payWeiXin(WeiXinPayVo weiXinPayVo) throws Exception{
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String strReq = SignUtil.getRandom12();
		// 商户号
		String mch_id = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.MCH_ID).getConfigValue();
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;

//		// 商户号
		String attach = weiXinPayVo.getBusinessNo();

//		// 商品描述根据情况修改
//		YywxMerchant merchant = merchantService.queryMerchantByMerchantId(attach);
		String body = weiXinPayVo.getNickName();
		// 商户订单号
		String appid = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.APPID).getConfigValue();
		String appsecret = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.APPSECRET).getConfigValue();
		String partnerkey = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.PARTNERKEY).getConfigValue();
//		String numberType = commonService.getYydconfig(CodeConst.ConfigCode.NumberType,CodeConst.ConfigCode.yywxorderinfo).getConfigValue();
		// 需调整先写成随即数
//		Random rand = new Random();
//		int tmp = Math.abs(rand.nextInt());
//		int num = tmp % (999999 - 100000 + 1) + 100000;
//		String orderNo = numberType + DateUtil.dateToString(new Date(), 4)+ num;
		String orderNo = commonService.getNoRandom(CodeConst.TableName.yypay, CodeConst.NoCode.payNo);
		
		String out_trade_no = orderNo;
		float sessionmoney = Float.parseFloat(String.valueOf(weiXinPayVo.getMoney()));
		//获取系统环境变量
		String environmenTypeCode = System.getProperty("environmenTypeCode");
		////如果是开发环境，则只收1分钱
		if(CodeConst.EnvironmenTypeCode.dev.equals(environmenTypeCode)){
			sessionmoney = Float.parseFloat("0.01");
		}
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = weiXinPayVo.getIp();
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";

		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "") + "/wxp/pdr.do";
		logger.debug("notify_rul=" + notify_url);

		String trade_type = "JSAPI";
		String openid = weiXinPayVo.getOpenId();
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		packageParams.put("total_fee", String.valueOf(total_fee));
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);
		logger.debug(packageParams);
		String sign = SignUtil.getSign(packageParams, partnerkey);
		logger.debug(sign);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" + "<attach>" + attach
				+ "</attach>" + "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<total_fee>" + total_fee
				+ "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openid + "</openid>"
				+ "</xml>";
		logger.debug("XML out:\n" + xml);

		
		String createOrderURL = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WXunifiedorder, "");
		String prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
		//统一下单接口数据保存
		//searchService.saveInterfMessage(CodeConst.InterfType.unifiedorder, xml, prepay_id);
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		logger.debug(finalpackage);
		String finalsign = SignUtil.getSign(finalpackage, partnerkey);
		logger.debug(finalsign);
		WeiXinPayVo payVo = new WeiXinPayVo();
		payVo.setAppId(appid2);
		payVo.setTimeStamp(timestamp);
		payVo.setPackageStr(packages);
		payVo.setSignType("MD5");
		payVo.setNonceStr(nonceStr2);
		payVo.setPaySign(finalsign);
		payVo.setOrderId(out_trade_no);
		//支付接口保存 此时未回调故没有返回值 回调时保存返回值
		XStream x = new XStream();
		String payVoString = x.toXML(payVo);
		//searchService.saveInterfMessage(CodeConst.InterfType.weixinpay, payVoString, null);
		
		Gson g = new Gson();
		String json = g.toJson(payVo);
		YywxOrderInfo orderInfo = new YywxOrderInfo();
		orderInfo.setOrderid(out_trade_no);
		orderInfo.setAppid(appid2);
		orderInfo.setMch_id(mch_id);
		orderInfo.setNonce_str(nonce_str);
		orderInfo.setBody(body);
		orderInfo.setAttach(attach);
		orderInfo.setTotal_fee(total_fee);
		orderInfo.setSpbill_create_ip(spbill_create_ip);
		orderInfo.setTrade_type(trade_type);
		orderInfo.setOpenid(openid);
		orderInfo.setSign(sign);
		orderInfo.setPrepay_id(prepay_id);
		orderInfo.setTimeStamp(Long.parseLong(timestamp));
		orderInfo.setSign(finalsign);
		orderInfo.setValid("1");
		//在微信支付未支付通知成功前flag为0 0未通知 1已通知
		orderInfo.setFlag("0");
		YywxOrderInfo yywxOrderInfo = weiXinOrderInfoService.saveOrderInfo(orderInfo);
		weiXinPayVo.setJson(json);
		weiXinPayVo.setYywxOrderInfo(yywxOrderInfo);
		return weiXinPayVo;
	}
	
	/**
	 * 微信支付-商家扫码支付
	 * @param weiXinPayVo
	 * @return
	 * @throws Exception
	 */
	public String payWeiXinForMerchant(WeiXinPayVo weiXinPayVo) throws Exception{
		//商户号
		String attach = weiXinPayVo.getBusinessNo();
		// 商品描述根据情况修改
		YywxMerchant merchant = merchantService.queryMerchantByMerchantId(attach);
		weiXinPayVo.setNickName(merchant.getNickName());
		String json = this.payWeiXin(weiXinPayVo).getJson();
		//保存支付订单表
		Gson g = new Gson();
		WeiXinPayVo weixinPayVo = g.fromJson(json, WeiXinPayVo.class);
		YywxOrderInfo yywxOrderInfo = weiXinOrderInfoService.findWeiXinOrderInfoByPk(weixinPayVo.getOrderId());
		payService.saveYyPayByYyOrderInfo(yywxOrderInfo,CodeConst.SourceType.wxpay,weiXinPayVo.getOpenId(),"");
		return json;
	}
	
	/**
	 * 微信支付-微信商城支付
	 * @param weiXinPayVo
	 * @return
	 * @throws Exception
	 */
	public String payWeiXinForStore(WeiXinPayVo weiXinPayVo) throws Exception {
		// TODO Auto-generated method stub
		weiXinPayVo = this.payWeiXin(weiXinPayVo);
		String json = weiXinPayVo.getJson();
		payService.saveYyPayByYyOrderInfoByStore(weiXinPayVo.getYywxOrderInfo(),weiXinPayVo);
		return json;
	}

	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public WeiXinOrderInfoService getWeiXinOrderInfoService() {
		return weiXinOrderInfoService;
	}

	public void setWeiXinOrderInfoService(
			WeiXinOrderInfoService weiXinOrderInfoService) {
		this.weiXinOrderInfoService = weiXinOrderInfoService;
	}
	public MerchantService getMerchantService() {
		return merchantService;
	}


	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}
	
	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

}
