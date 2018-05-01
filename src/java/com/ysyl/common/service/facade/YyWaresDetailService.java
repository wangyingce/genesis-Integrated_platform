package com.ysyl.common.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyWaresDetail;

public interface YyWaresDetailService {
	/**
	 * 根据商户id获取该商户的商品明细信息
	 * @param storeId 商户id
	 */
	public List<YyWaresDetail> getWaresDetailsByStoreId(String storeId) throws Exception;
	/**
	 * 根据商户ID和商品状态获取产品明细
	 * @param storeId
	 * @param cargoStatus
	 * @return
	 * @throws Exception
	 */
	public List<YyWaresDetail> getWaresDetailsByStoreIdAndCargoStatus(String storeId,String cargoStatus) throws Exception;
	/**
	 * 根据商户id查询信息
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public YyWaresDetail getWaresDetailsById(Long Id) throws Exception;
}
