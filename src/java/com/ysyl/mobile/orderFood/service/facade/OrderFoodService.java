package com.ysyl.mobile.orderFood.service.facade;

import java.util.List;

import com.ysyl.mobile.orderFood.vo.CartVo;
import com.ysyl.mobile.orderFood.vo.YyShoppingCartVo;
import com.ysyl.mobile.orderFood.vo.YyWaresDetailVo;

public interface OrderFoodService {
	/**
	 * 根据商户号和商品状态获取商品列表
	 * @param storeId 商户号
	 * @param cargoStatus 商品状态
	 * @return
	 */
	public List<YyWaresDetailVo> getYyWaresDetailByStoreIdAndCargoStatus(String storeId,String cargoStatus);
	/**
	 * 获取产品信息及购物车情况
	 * @param storeId
	 * @param cargoStatus
	 * @param userCode
	 * @return
	 */
	public CartVo getYyWaresDetailInitByCargoStatus(String storeId,String cargoStatus,String userCode);
	/**
	 * 查询产品详细信息
	 * @param Id
	 * @return
	 */
	public YyWaresDetailVo getYyWaresDetailById(Long Id);
	/**
	 * 获取客户购物车信息
	 * @param storeId
	 * @param userCode
	 * @return
	 */
	public List<YyShoppingCartVo> getCart(String storeId,String userCode);
	/**
	 * 获取购物车详细信息
	 * @param storeId
	 * @param userCode
	 * @return
	 */
	public List<YyShoppingCartVo> getCartDetil(String storeId,String userCode);
	/**
	 * 查询购物车信息
	 * @param storeId
	 * @param userCode
	 * @return
	 */
	public CartVo getCartVo(String storeId,String userCode);
	/**
	 * 增加购物车数据
	 * @param storeId
	 * @param userCode
	 * @param waresID
	 */
	public void saveShoppingCart(String storeId,String userCode,String waresID);
	/**
	 * 删除产品
	 * @param id
	 */
	public void deleteShoppingCart(String id);
}
