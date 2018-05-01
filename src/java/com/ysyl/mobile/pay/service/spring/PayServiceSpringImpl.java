package com.ysyl.mobile.pay.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyPay;
import com.ysyl.common.util.DateUtil;
import com.ysyl.mobile.pay.service.facade.PayService;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;
import com.ysyl.weixin.schema.model.YywxMerchant;
import com.ysyl.weixin.schema.model.YywxOrderInfo;

public class PayServiceSpringImpl extends GenericDaoHibernate<YyPay, String> implements PayService {
	private MerchantService merchantService;
	
	/**
	 * 根据yyorderinfo表保存yypay表
	 */
	public void saveYyPayByYyOrderInfo(YywxOrderInfo yywxOrderInfo,String souceType,String userCode,String inKey) throws Exception{
		YyPay yyPay = new YyPay();
		yyPay.setBusinessNo(yywxOrderInfo.getOrderid());
		yyPay.setBusinessType(CodeConst.ChannelType.wx);
		yyPay.setPayState(CodeConst.PayState.waitPay);
		yyPay.setCheckState(CodeConst.PayCheckState.waitCheck);
		int totleFee = yywxOrderInfo.getTotal_fee();
		BigDecimal payFee = new BigDecimal(totleFee).divide(new BigDecimal(100));
		yyPay.setPayFee(payFee.toString());
		yyPay.setCurrency(CodeConst.Currency.CNY);
		yyPay.setStoreId(yywxOrderInfo.getAttach());
		yyPay.setUserCode(userCode);
		yyPay.setInKey(inKey);
		YywxMerchant merchant = merchantService.queryMerchantByMerchantId(yywxOrderInfo.getAttach());
		yyPay.setStoreName(merchant.getNickName());
		yyPay.setValidStatus(CodeConst.Valid.valid);
		super.save(yyPay);
	}
	
	/**
	 * 根据yyorderinfo表保存yypay表
	 */
	public void saveYyPayByYyOrderInfoByStore(YywxOrderInfo yywxOrderInfo,WeiXinPayVo weiXinPayVo) throws Exception{
		YyPay yyPay = new YyPay();
		yyPay.setBusinessNo(yywxOrderInfo.getOrderid());
		yyPay.setBusinessType(CodeConst.ChannelType.wx);
		yyPay.setPayState(CodeConst.PayState.waitPay);
		yyPay.setCheckState(CodeConst.PayCheckState.waitCheck);
		int totleFee = yywxOrderInfo.getTotal_fee();
		BigDecimal payFee = new BigDecimal(totleFee).divide(new BigDecimal(100));
		yyPay.setPayFee(payFee.toString());
		yyPay.setCurrency(CodeConst.Currency.CNY);
		yyPay.setStoreId(weiXinPayVo.getOrderId());
		yyPay.setUserCode(weiXinPayVo.getUserCode());
		yyPay.setInKey(weiXinPayVo.getInKey());
		yyPay.setSourceType(weiXinPayVo.getSourceType());
		yyPay.setStoreName(weiXinPayVo.getNickName());
		yyPay.setValidStatus(CodeConst.Valid.valid);
		super.save(yyPay);
	}
	
	
	/**
	 * 根据yyorderinfo表更新yypay表状态
	 */
	public YyPay updateYyPayByYyOrderInfo(YywxOrderInfo yywxOrderInfo) throws Exception{
		YyPay yyPay = this.findYYPayByBusinessNoAndBusinessType(yywxOrderInfo.getOrderid(), CodeConst.ChannelType.wx);
		int totleFee = yywxOrderInfo.getTotal_fee();
		BigDecimal payFee = new BigDecimal(totleFee).divide(new BigDecimal(100));
		yyPay.setPayFee(payFee.toString());
		yyPay.setPayState(WxCodeConst.WxPayStateMap.get(yywxOrderInfo.getTrade_state()));
		Date paySuccessTime = DateUtil.stringToDate(yywxOrderInfo.getTime_end().toString(), 2);
		yyPay.setPaySuccessTime(paySuccessTime);
		super.save(yyPay);
		return yyPay;
	}
	
	/**
	 * 根据业务号及类型及 查询订单
	 * */
	public YyPay findYYPayByBusinessNoAndBusinessType(String businessNo,String businessType){
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("businessNo", businessNo);
		qr.addEqual("businessType", businessType);
		YyPay yyPay = super.findUnique(qr);
		return yyPay;
	}
	
	/**
	 * 根据用户代码，商户id，支付状态查询 payState=ALL则表示全查
	 */
	public List<YyPay> findYyPayByUserCodeAndStoreIdAndPayState(String userCode,String streoId,String payState) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("userCode", userCode);
		queryRule.addEqual("storeId", streoId);
		//传ALL代表全查，则不组条件
		if(!"ALL".equals(payState)){
			queryRule.addEqual("payState",payState);
		}
		return this.find(queryRule);
	}
	
	public MerchantService getMerchantService() {
		return merchantService;
	}
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
}
