package com.ysyl.common.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.schema.model.YyWaresClass;
import com.ysyl.common.service.facade.YyWaresClassService;

public class YyWaresClassServiceSpringImpl extends GenericDaoHibernate<YyWaresClass, String>  implements YyWaresClassService {
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

}
