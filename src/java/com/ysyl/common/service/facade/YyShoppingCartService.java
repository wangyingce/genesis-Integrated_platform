package com.ysyl.common.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyShoppingCart;

public interface YyShoppingCartService {
	/**
	 * 添加购物车
	 * @param yyShoppingCart
	 */
	public void addShoppingCart(YyShoppingCart yyShoppingCart);
	/**
	 * 查询客户购物车
	 * @param storeId
	 * @param userCode
	 * @return
	 */
	public List<YyShoppingCart> getShoppingCartByUserCode(String storeId,String userCode);
	/**
	 * 根据商品查询购物车信息
	 * @param storeId
	 * @param userCode
	 * @param waresID
	 * @return
	 */
	public List<YyShoppingCart> queryCartByWaresID(String storeId,String userCode,String waresID);
	/**
	 * 更新购物车商品状态
	 * @param yyShoppingCart
	 */
	public void updateCartById(YyShoppingCart yyShoppingCart);
	/**
	 * 删除购物车信息
	 * @param id
	 */
	public void deleteCartById(String id);
}
