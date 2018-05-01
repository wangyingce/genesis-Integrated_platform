package com.ysyl.mobile.wxstore.web;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.ysyl.common.schema.model.YyWaresClass;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.mobile.wxstore.StoreConst;
import com.ysyl.mobile.wxstore.service.facade.MobileStoreService;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;

public class MobileStoreAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	//商户Id
	private String storeId;
	
	//微信用户openId
	private String openId;
	
	//类型
	private String initType;
	
	//购物车里的内容，以,号拼起来的
	private String shoppingCart;
	
	//支付金额
	private String sumTotalPay;
	
	private MobileStoreService mobileStoreService;
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	public void initIndex(){
		try {
			//初始化商品大类
			if(StoreConst.InitType.waresClass.equals(initType)){
				//根据商户Id查询该商户的商品大类明细
				List<YyWaresClass> yyWaresClasses = mobileStoreService.getWaresClassByStoreId(storeId);
				//返回ajax
				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), yyWaresClasses);
			}else if(StoreConst.InitType.waresDetail.equals(initType)){
				//初始化商品明细
				List<YyWaresDetail> yyWaresDetails = mobileStoreService.getWaresDetailsByStoreId(storeId);
				//返回ajax
				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), yyWaresDetails);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 点击查看购物车 
	 * @return initType 
	 */
	public String viewCargo(){
		return initType;
	}
	
	/**
	 * 初始化购物车明细
	 */
	public void initShopCast(){
		//初始化所选购物车里的商品信息
		try {
			//判断购物车里相同的物品，相同则数量+1即可
			Map<String,Integer> shopCartMap = new HashMap<String,Integer>();
			String[] shoppingCarts = shoppingCart.split(",");
			for (int i = 0; i < shoppingCarts.length; i++) {
				String key = shoppingCarts[i];
				Integer value = (Integer)shopCartMap.get(key);
				if(value != null){
					shopCartMap.put(key,value + 1);
				}else{
					shopCartMap.put(key,1);
				}
			}
			//查询数据库
			List<YyWaresDetail> yyWaresDetails = mobileStoreService.getWaresDetailsByIdAndStoreId(shoppingCart,storeId);
			//处理购物车，如果点了两次，则更新对象里的数量（持久化对象本来不应该这样的，但是get开头的方法在spring配置中是只读方法，所以不会改变数据库，先这么处理）
			for (YyWaresDetail yyWaresDetail : yyWaresDetails) {
				String key = yyWaresDetail.getId().toString();
				Integer quantity = shopCartMap.get(key);
				if(quantity != null){
					yyWaresDetail.setQuantity(new BigDecimal(quantity));
				}
			}
			
			
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), yyWaresDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 支付
	 */
	public void toPay(){
		String ip = this.getRequest().getRemoteAddr();
		WeiXinPayVo inputVo = new WeiXinPayVo();
		inputVo.setOpenId(openId);
		inputVo.setMoney(sumTotalPay);
		inputVo.setBusinessNo(storeId);
		inputVo.setIp(ip);
		try {
			String json = mobileStoreService.toWeiXinPay(inputVo);
			logger.debug(json);
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ServletActionContext.getResponse().getWriter().print("微信支付失败");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public MobileStoreService getMobileStoreService() {
		return mobileStoreService;
	}

	public void setMobileStoreService(MobileStoreService mobileStoreService) {
		this.mobileStoreService = mobileStoreService;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getInitType() {
		return initType;
	}

	public void setInitType(String initType) {
		this.initType = initType;
	}

	public String getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(String shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSumTotalPay() {
		return sumTotalPay;
	}

	public void setSumTotalPay(String sumTotalPay) {
		this.sumTotalPay = sumTotalPay;
	}


}