package com.ysyl.weixin.pay.service.facade;

import com.ysyl.weixin.pay.vo.WeiXinPayVo;

public interface WeiXinPayService {
	
	/**
	 * 微信支付-商家扫码支付
	 * @param weiXinPayVo
	 * @return
	 * @throws Exception
	 */
	public String payWeiXinForMerchant(WeiXinPayVo weiXinPayVo) throws Exception;
	
	/**
	 * 微信支付-商城实付
	 * @param weiXinPayVo
	 * @return
	 * @throws Exception
	 */
	public String payWeiXinForStore(WeiXinPayVo weiXinPayVo) throws Exception;
}
