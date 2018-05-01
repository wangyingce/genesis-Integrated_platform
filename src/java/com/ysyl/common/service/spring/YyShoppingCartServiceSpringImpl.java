package com.ysyl.common.service.spring;

import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.schema.model.YyShoppingCart;
import com.ysyl.common.service.facade.YyShoppingCartService;

public class YyShoppingCartServiceSpringImpl extends GenericDaoHibernate<YyShoppingCart,String> 
	implements YyShoppingCartService {
	
	public void addShoppingCart(YyShoppingCart yyShoppingCart){
		this.save(yyShoppingCart);
	}
	
	public List<YyShoppingCart> getShoppingCartByUserCode(String storeId,String userCode){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("userCode", userCode);
		queryRule.addNotEqual("validStatus", "03");//不查询删除的数据
		List<YyShoppingCart> yyShoppingCarts= super.find(YyShoppingCart.class,queryRule);
		return yyShoppingCarts;
	}
	
	public List<YyShoppingCart> queryCartByWaresID(String storeId,String userCode,String waresID){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("storeId", storeId);
		queryRule.addEqual("userCode", userCode);
		queryRule.addEqual("waresID", waresID);
		queryRule.addEqual("validStatus", "00");//未下单的购物车数据，下单后客户不能删除
		queryRule.addNotEqual("validStatus", "03");
		List<YyShoppingCart> yyShoppingCarts= super.find(YyShoppingCart.class,queryRule);
		return yyShoppingCarts;
	}
	//更新购物车
	public void updateCartById(YyShoppingCart yyShoppingCart){
		super.update(yyShoppingCart);
	}
	public void deleteCartById(String id){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id", Long.parseLong(id));
		this.delete(this.findUnique(queryRule));
	}
}
