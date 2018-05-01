package com.ysyl.mobile.waresowner.service.facade;

import com.ysyl.common.schema.model.YyWaresOwner;



public interface WaresOwnerService{
	
	/**
	 * 查询商户信息
	 * @param photoGraphVo 传送对象
	 */
	public YyWaresOwner getWaresOnwerById(String id) throws Exception;
	
	
}
