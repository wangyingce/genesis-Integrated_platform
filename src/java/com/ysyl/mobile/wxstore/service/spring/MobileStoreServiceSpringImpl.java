package com.ysyl.mobile.wxstore.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.List;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyWaresClass;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.common.schema.model.YyWaresOwner;
import com.ysyl.common.vo.WxFowardVo;
import com.ysyl.mobile.wxstore.service.facade.MobileStoreService;
import com.ysyl.weixin.pay.service.facade.WeiXinPayService;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class MobileStoreServiceSpringImpl extends GenericDaoHibernate<YyWaresClass, String> implements MobileStoreService {
	
	private WeiXinPayService weiXinPayService;
	/**
	 * 根据商户id获取该商户的商品大类明细
	 * @param storeId 商户id
	 */
	@SuppressWarnings("unchecked")
	public List<YyWaresClass> getWaresClassByStoreId(String storeId) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("validStatus", "1");
		List<YyWaresClass> yyWaresClasses = this.find(YyWaresClass.class,queryRule);
		return yyWaresClasses;
	}
	
	/**
	 * 根据商户id获取该商户的商品明细信息
	 * @param storeId 商户id
	 */
	@SuppressWarnings("unchecked")
	public List<YyWaresDetail> getWaresDetailsByStoreId(String storeId) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("validStatus", "1");
		List<YyWaresDetail> yyWaresDetails = this.find(YyWaresDetail.class,queryRule);
		return yyWaresDetails;
	}
	
	
	/**
	 * 根据商户id获取商户信息
	 * @param storeId 商户id
	 */
	public YyWaresOwner getWaresOwnerById(String id) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id", id);
		queryRule.addEqual("validStatus", "1");
		YyWaresOwner yyWaresOwner = this.findUnique(YyWaresOwner.class,queryRule);
		return yyWaresOwner;
	}
	
	/**
	 * 微信网页统一跳转页面初始化个性化信息
	 * @param wxFowardVo
	 * @throws Exception
	 */
	public WxFowardVo initStore(WxFowardVo wxFowardVo) throws Exception{
		//初始化小店商户信息
		YyWaresOwner yyWaresOwner = getWaresOwnerById(wxFowardVo.getInputId());
		wxFowardVo.setInputName(yyWaresOwner.getOwnerName());
		
		YywxUserInfo yywxUserInfo = wxFowardVo.getYywxUserInfo();
		//过长截串
		if(yywxUserInfo.getNickname()!= null && yywxUserInfo.getNickname().length()>5){
			yywxUserInfo.setNickname(yywxUserInfo.getNickname().substring(0,4)+"..");
		}
		wxFowardVo.setYywxUserInfo(yywxUserInfo);
		
		return wxFowardVo;
	}
	
	/**
	 * 根据商品id，商户id获取该商品明细信息
	 * @param storeId 商户id
	 * @param id 商品id，可以传多个以,拼起来则查询多个 例：1,2,3
	 */
	@SuppressWarnings("unchecked")
	public List<YyWaresDetail> getWaresDetailsByIdAndStoreId(String id,String storeId) throws Exception{
		String[] idStr = id.split(",");
		List<Long> idList = new ArrayList<Long>();
		for (int i = 0; i < idStr.length; i++) {
			String value = idStr[i];
			idList.add(new Long(value));
		}
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("id", idList);
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("validStatus", "1");
		List<YyWaresDetail> yyWaresDetails = this.find(YyWaresDetail.class,queryRule);
		return yyWaresDetails;
	}
	/**
	 * 调用微信支付
	 * @param WeiXinPayVo 微信支付入参对象
	 * @return 返回微信json
	 */
	public String toWeiXinPay(WeiXinPayVo weiXinPayVo) throws Exception{
		//查询商户名称
		YyWaresOwner yyWaresOwner = getWaresOwnerById(weiXinPayVo.getBusinessNo());
		weiXinPayVo.setNickName(yyWaresOwner.getOwnerName());
		weiXinPayVo.setSourceType(CodeConst.SourceType.store);
		String json = weiXinPayService.payWeiXinForStore(weiXinPayVo);
		return json;
	}

	public WeiXinPayService getWeiXinPayService() {
		return weiXinPayService;
	}

	public void setWeiXinPayService(WeiXinPayService weiXinPayService) {
		this.weiXinPayService = weiXinPayService;
	}
	
}
