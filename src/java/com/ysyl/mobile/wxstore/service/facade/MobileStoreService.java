package com.ysyl.mobile.wxstore.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyWaresClass;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.common.schema.model.YyWaresOwner;
import com.ysyl.common.vo.WxFowardVo;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;



public interface MobileStoreService{
	
	/**
	 * 根据商户id获取该商户的商品大类明细
	 * @param storeId 商户id
	 */
	public List<YyWaresClass> getWaresClassByStoreId(String storeId) throws Exception;
	
	/**
	 * 根据商户id获取该商户的商品明细信息
	 * @param storeId 商户id
	 */
	public List<YyWaresDetail> getWaresDetailsByStoreId(String storeId) throws Exception;
	
	/**
	 * 根据商户id获取商户信息
	 * @param storeId 商户id
	 */
	public YyWaresOwner getWaresOwnerById(String id) throws Exception;
	
	
	/**
	 * 微信网页统一跳转页面初始化个性化信息
	 * @param wxFowardVo
	 * @throws Exception
	 */
	public WxFowardVo initStore(WxFowardVo wxFowardVo) throws Exception;
	
	/**
	 * 根据商品id，商户id获取该商品明细信息
	 * @param storeId 商户id
	 * @param id 商品id，可以传多个以,拼起来则查询多个 例：1,2,3
	 */
	public List<YyWaresDetail> getWaresDetailsByIdAndStoreId(String id,String storeId) throws Exception;
	
	/**
	 * 调用微信支付
	 * @param WeiXinPayVo 微信支付入参对象
	 * @return 返回微信json
	 */
	public String toWeiXinPay(WeiXinPayVo inputVo) throws Exception;
}
