package com.ysyl.mobile.waresowner.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.schema.model.YyWaresOwner;
import com.ysyl.mobile.waresowner.service.facade.WaresOwnerService;

public class WaresOwnerServiceSpringImpl extends GenericDaoHibernate<YyWaresOwner, String> implements WaresOwnerService {
	/**
	 * 查询商户信息
	 * @param photoGraphVo 传送对象
	 */
	public YyWaresOwner getWaresOnwerById(String id) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id",id);
		YyWaresOwner yyWaresOwner = super.findUnique(queryRule);
		return yyWaresOwner;
	}
}
