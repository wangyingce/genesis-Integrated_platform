package com.ysyl.mobile.pay.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyPay;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;
import com.ysyl.weixin.schema.model.YywxOrderInfo;




/**
 * 支付订单服务
 * @author ysyl
 */
public interface PayService{
	/**
	 * 根据yyorderinfo表保存yypay表
	 */
	public void saveYyPayByYyOrderInfo(YywxOrderInfo yywxOrderInfo,String souceType,String userCode,String inKey) throws Exception;
	
	/**
	 * 根据yyorderinfo表更新yypay表状态
	 */
	public YyPay updateYyPayByYyOrderInfo(YywxOrderInfo yywxOrderInfo) throws Exception;
	
	/**
	 * 根据业务号及类型及 查询订单
	 * */
	public YyPay findYYPayByBusinessNoAndBusinessType(String businessNo,String businessType);
	
	/**
	 * 根据yyorderinfo表保存yypay表
	 */
	public void saveYyPayByYyOrderInfoByStore(YywxOrderInfo yywxOrderInfo,WeiXinPayVo weiXinPayVo) throws Exception;
	
	/**
	 * 根据用户代码，商户id，支付状态查询 payState=ALL则表示全查
	 */
	public List<YyPay> findYyPayByUserCodeAndStoreIdAndPayState(String userCode,String streoId,String payState) throws Exception;
}
