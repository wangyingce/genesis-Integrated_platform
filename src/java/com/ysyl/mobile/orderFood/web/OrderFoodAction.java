package com.ysyl.mobile.orderFood.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ysyl.common.schema.model.YyShoppingCart;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.common.service.facade.YyShoppingCartService;
import com.ysyl.mobile.orderFood.service.facade.OrderFoodService;
import com.ysyl.mobile.orderFood.vo.CartVo;
import com.ysyl.mobile.orderFood.vo.YyShoppingCartVo;
import com.ysyl.mobile.orderFood.vo.YyWaresDetailVo;
import com.ysyl.restFulServlet.util.ServletUtil;

import ins.framework.web.Struts2Action;

public class OrderFoodAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	//商户Id
	private String storeId;
	//商品类型
	private String cargoStatus;
	//客户id
	private String userCode;
	//商品ID
	private Long productId;
	//购物车ID
	private String cartId;
	
	private OrderFoodService orderFoodService;
	private YyWaresDetailVo yyWaresDetailVo;
	private List<YyWaresDetailVo> yyWaresDetailVos;
	private CartVo cartVo;//购物车信息
	//扫二维码初始化
	public String initIndex(){
		try {
			//初始化商品明细
			cartVo = orderFoodService.getYyWaresDetailInitByCargoStatus(storeId, cargoStatus,userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//切换产品
	public void initCargoStatus(){
		try {
			//初始化商品信息
			yyWaresDetailVos = orderFoodService.getYyWaresDetailByStoreIdAndCargoStatus(storeId, cargoStatus);
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),yyWaresDetailVos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//查询商品的详细信息
	public String getProductDetil(){
		try {
			//初始化商品详细信息
			yyWaresDetailVo = orderFoodService.getYyWaresDetailById(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//查询购物车信息
	public String getShoppingCartByUserCode(){
		cartVo=orderFoodService.getCartVo(storeId,userCode);
		return SUCCESS;
	}
	//保存购物车数据
	public void saveShoppingCart(){
		try {
			orderFoodService.saveShoppingCart(storeId, userCode, productId.toString());
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),"成功");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteShoppingCart(){
		try {
			orderFoodService.deleteShoppingCart(cartId);
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),"成功");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public OrderFoodService getOrderFoodService() {
		return orderFoodService;
	}

	public void setOrderFoodService(OrderFoodService orderFoodService) {
		this.orderFoodService = orderFoodService;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCargoStatus() {
		return cargoStatus;
	}

	public void setCargoStatus(String cargoStatus) {
		this.cargoStatus = cargoStatus;
	}

	public List<YyWaresDetailVo> getYyWaresDetailVos() {
		return yyWaresDetailVos;
	}

	public void setYyWaresDetailVos(List<YyWaresDetailVo> yyWaresDetailVos) {
		this.yyWaresDetailVos = yyWaresDetailVos;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public YyWaresDetailVo getYyWaresDetailVo() {
		return yyWaresDetailVo;
	}
	public void setYyWaresDetailVo(YyWaresDetailVo yyWaresDetailVo) {
		this.yyWaresDetailVo = yyWaresDetailVo;
	}
	public CartVo getCartVo() {
		return cartVo;
	}
	public void setCartVo(CartVo cartVo) {
		this.cartVo = cartVo;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	
}
