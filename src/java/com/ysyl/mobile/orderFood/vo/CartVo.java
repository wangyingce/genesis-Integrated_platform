package com.ysyl.mobile.orderFood.vo;

import java.math.BigDecimal;
import java.util.List;

public class CartVo {
	private List<YyShoppingCartVo> yyShoppingCartVos;//购物车产品
	private List<YyWaresDetailVo> yyWaresDetailVos;//商品列表
	private BigDecimal sumPrice;//合计
	private String sumNumber;//购物车数量合计
	
	public List<YyShoppingCartVo> getYyShoppingCartVos() {
		return yyShoppingCartVos;
	}
	public void setYyShoppingCartVos(List<YyShoppingCartVo> yyShoppingCartVos) {
		this.yyShoppingCartVos = yyShoppingCartVos;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	public List<YyWaresDetailVo> getYyWaresDetailVos() {
		return yyWaresDetailVos;
	}
	public void setYyWaresDetailVos(List<YyWaresDetailVo> yyWaresDetailVos) {
		this.yyWaresDetailVos = yyWaresDetailVos;
	}
	public String getSumNumber() {
		return sumNumber;
	}
	public void setSumNumber(String sumNumber) {
		this.sumNumber = sumNumber;
	}
	
}
