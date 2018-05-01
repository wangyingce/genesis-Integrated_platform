package com.ysyl.common.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.schema.model.YyWaresClass;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.common.service.facade.YyWaresDetailService;

public class YyWaresDetailServiceSpringImpl extends GenericDaoHibernate<YyWaresDetail, String> implements YyWaresDetailService {
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
	public List<YyWaresDetail> getWaresDetailsByStoreIdAndCargoStatus(String storeId,String cargoStatus) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("cargoStatus",cargoStatus);
		queryRule.addEqual("validStatus", "1");
		List<YyWaresDetail> yyWaresDetails = this.find(YyWaresDetail.class,queryRule);
		return yyWaresDetails;
	}
	
	public YyWaresDetail getWaresDetailsById(Long Id) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id", Id);
		queryRule.addEqual("validStatus", "1");
		YyWaresDetail yyWaresDetail = this.findUnique(queryRule);
		return yyWaresDetail;
	}

}
