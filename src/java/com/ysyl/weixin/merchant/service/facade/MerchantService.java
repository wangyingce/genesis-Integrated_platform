package com.ysyl.weixin.merchant.service.facade;


import java.util.List;

import com.ysyl.weixin.merchant.vo.MerchantVo;
import com.ysyl.weixin.schema.model.YywxMerchant;

import ins.framework.common.Page;
/**
 * 
 * 店铺功能interface
 * @author Scorpio
 * @category Amicus Plato, sed magis amica veritas
 */
public interface MerchantService {

	/**
	 * 分页查询店铺信息
	 * @param merchantVo:店铺信息vo
	 * @return （merchantVo）page
	 */
	Page queryMerchantByMerchantVo(MerchantVo merchantVo);

	/**
	 * 根据merchantId查询一个店铺信息
	 * @param merchantId：YywxMerchant主键
	 * @return YywxMerchant：店铺信息schema
	 */
	YywxMerchant queryMerchantByMerchantId(String merchantId);

	/**
	 * 根据店铺名称查询一个店铺信息
	 * @param nickName：店铺名称
	 * @return YywxMerchant：店铺信息schema
	 */
	YywxMerchant queryMerchantByNickName(String nickName);

	/**
	 * 保存或更新店铺
	 * @param merchant
	 */
	void saveOrUpdateMerchant(YywxMerchant merchant);

	/**
	 * 根据店铺名称查询所有店铺信息
	 * @param nickName:店铺名称
	 * @return List<YywxMerchant> ：店铺信息schema》list
	 */
	List<YywxMerchant> queryMerchantListByNickName(String nickName);

	/**
	 * 根据电话号查询所有店铺信息
	 * @param registermobile
	 * @return List<YywxMerchant>：店铺信息schema》list
	 */
	List<YywxMerchant> queryMerchantListByReportMobile(String registermobile);
}
