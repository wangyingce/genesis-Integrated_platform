package com.ysyl.mobile.orderFood.service.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sinosoft.sinoiaci.util.SuperBeanTools;
import com.ysyl.common.schema.model.YyWaresDetail;
import com.ysyl.common.schema.model.YyShoppingCart;
import com.ysyl.common.service.facade.YyShoppingCartService;
import com.ysyl.common.service.facade.YyWaresClassService;
import com.ysyl.common.service.facade.YyWaresDetailService;
import com.ysyl.mobile.orderFood.service.facade.OrderFoodService;
import com.ysyl.mobile.orderFood.vo.CartVo;
import com.ysyl.mobile.orderFood.vo.YyShoppingCartVo;
import com.ysyl.mobile.orderFood.vo.YyWaresDetailVo;

public class OrderFoodServiceSpringImpl implements OrderFoodService {

	private YyWaresClassService yyWaresClassService;//商品分类
	private YyWaresDetailService yyWaresDetailService;//商品分类明细
	private YyShoppingCartService yyShoppingCartService;//购物车
	
	public List<YyWaresDetailVo> getYyWaresDetailByStoreIdAndCargoStatus(String storeId,String cargoStatus){
		List<YyWaresDetailVo> yyWaresDeatilVos=new ArrayList<YyWaresDetailVo>();
		List<YyWaresDetail> yyWaresDeatils=new ArrayList<YyWaresDetail>();
		try{
			yyWaresDeatils=yyWaresDetailService.getWaresDetailsByStoreIdAndCargoStatus(storeId, cargoStatus);
			for(YyWaresDetail yyWaresDeatil:yyWaresDeatils){
				YyWaresDetailVo yyWaresDetailVo=new YyWaresDetailVo();
				SuperBeanTools.simpleCopy(yyWaresDeatil,yyWaresDetailVo,true);
				yyWaresDeatilVos.add(yyWaresDetailVo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return yyWaresDeatilVos;
	}
	public CartVo getYyWaresDetailInitByCargoStatus(String storeId,String cargoStatus,String userCode){
		CartVo cartVo=new CartVo();
		List<YyWaresDetailVo> yyWaresDeatilVos=this.getYyWaresDetailByStoreIdAndCargoStatus(storeId, cargoStatus);
		cartVo.setYyWaresDetailVos(yyWaresDeatilVos);
		List<YyShoppingCartVo> yyShoppingCartVos=this.getCart(storeId, userCode);
		BigDecimal sumQuantity=new BigDecimal("0");
		for(YyShoppingCartVo yyShoppingCartVo:yyShoppingCartVos){
			BigDecimal quantity=new BigDecimal(yyShoppingCartVo.getQuantity());
			sumQuantity=sumQuantity.add(quantity);
		}
		cartVo.setSumNumber(sumQuantity.toString());
		return cartVo;
	}
	public YyWaresDetailVo getYyWaresDetailById(Long Id){
		YyWaresDetailVo yyWaresDeatilVo=new YyWaresDetailVo();
		YyWaresDetail yyWaresDeatil=new YyWaresDetail();
		try{
			yyWaresDeatil=yyWaresDetailService.getWaresDetailsById(Id);
			SuperBeanTools.simpleCopy(yyWaresDeatil,yyWaresDeatilVo,true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return yyWaresDeatilVo;
	}
	public List<YyShoppingCartVo> getCart(String storeId,String userCode){
		List<YyShoppingCartVo> yyShoppingCartVos=new ArrayList<YyShoppingCartVo>();
		try{
			List<YyShoppingCart> yyShoppingCarts =yyShoppingCartService.getShoppingCartByUserCode(storeId,userCode);
			for(YyShoppingCart yyShoppingCart:yyShoppingCarts){
				YyShoppingCartVo yyShoppingCartVo=new YyShoppingCartVo();
				SuperBeanTools.simpleCopy(yyShoppingCart,yyShoppingCartVo,true);
				yyShoppingCartVos.add(yyShoppingCartVo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return yyShoppingCartVos;
	}
	public List<YyShoppingCartVo> getCartDetil(String storeId,String userCode){
		List<YyShoppingCartVo> yyShoppingCartVos=new ArrayList<YyShoppingCartVo>();
		try{
			List<YyShoppingCart> yyShoppingCarts =yyShoppingCartService.getShoppingCartByUserCode(storeId,userCode);
			for(YyShoppingCart yyShoppingCart:yyShoppingCarts){
				YyShoppingCartVo yyShoppingCartVo=new YyShoppingCartVo();
				YyWaresDetailVo yyWaresDeatilVo=new YyWaresDetailVo();
				SuperBeanTools.simpleCopy(yyShoppingCart,yyShoppingCartVo,true);
				yyWaresDeatilVo=this.getYyWaresDetailById(Long.parseLong(yyShoppingCart.getWaresID()));//获取商品详细信息
				yyShoppingCartVo.setYyWaresDetailVo(yyWaresDeatilVo);
				yyShoppingCartVos.add(yyShoppingCartVo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return yyShoppingCartVos;
	}
	
	public CartVo getCartVo(String storeId,String userCode){
		CartVo cartVo=new CartVo();
		List<YyShoppingCartVo> yyShoppingCartVos=new ArrayList<YyShoppingCartVo>();
		try{
			List<YyShoppingCart> yyShoppingCarts =yyShoppingCartService.getShoppingCartByUserCode(storeId,userCode);
			BigDecimal bigSumPrice=new BigDecimal("0");
			for(YyShoppingCart yyShoppingCart:yyShoppingCarts){
				YyShoppingCartVo yyShoppingCartVo=new YyShoppingCartVo();
				YyWaresDetailVo yyWaresDeatilVo=null;
				SuperBeanTools.simpleCopy(yyShoppingCart,yyShoppingCartVo,true);
				yyWaresDeatilVo=this.getYyWaresDetailById(Long.parseLong(yyShoppingCart.getWaresID()));//获取商品详细信息
				if(yyWaresDeatilVo!=null){
					if(yyWaresDeatilVo.getCargoName().length()>4){
						yyWaresDeatilVo.setCargoName(yyWaresDeatilVo.getCargoName().substring(0,5)+"...");
					}
					bigSumPrice=bigSumPrice.add(yyWaresDeatilVo.getDiscountPrice().multiply(yyWaresDeatilVo.getQuantity()));
					yyShoppingCartVo.setYyWaresDetailVo(yyWaresDeatilVo);
				}
				yyShoppingCartVos.add(yyShoppingCartVo);
			}
			cartVo.setSumPrice(bigSumPrice);
			cartVo.setYyShoppingCartVos(yyShoppingCartVos);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cartVo;
	}
	public void saveShoppingCart(String storeId,String userCode,String waresID){
		YyShoppingCart yyShoppingCart=new YyShoppingCart();
		List<YyShoppingCart> yyShoppingCarts=yyShoppingCartService.queryCartByWaresID(storeId,userCode,waresID);
		if(yyShoppingCarts!=null&&yyShoppingCarts.size()>0){
			yyShoppingCart=yyShoppingCarts.get(0);
			yyShoppingCart.setQuantity(yyShoppingCarts.get(0).getQuantity()+1);
		}else{
			yyShoppingCart.setQuantity(1);
		}
		yyShoppingCart.setStoreId(storeId);
		yyShoppingCart.setUserCode(userCode);
		yyShoppingCart.setWaresID(waresID);
		yyShoppingCart.setValidStatus("00");
		yyShoppingCart.setInputDate(new Date());
		yyShoppingCartService.addShoppingCart(yyShoppingCart);//保存购物车数据
	}
	
	public void deleteShoppingCart(String id){
		yyShoppingCartService.deleteCartById(id);
	}
	
	public YyWaresClassService getYyWaresClassService() {
		return yyWaresClassService;
	}
	public void setYyWaresClassService(YyWaresClassService yyWaresClassService) {
		this.yyWaresClassService = yyWaresClassService;
	}
	public YyWaresDetailService getYyWaresDetailService() {
		return yyWaresDetailService;
	}
	public void setYyWaresDetailService(YyWaresDetailService yyWaresDetailService) {
		this.yyWaresDetailService = yyWaresDetailService;
	}

	public YyShoppingCartService getYyShoppingCartService() {
		return yyShoppingCartService;
	}

	public void setYyShoppingCartService(YyShoppingCartService yyShoppingCartService) {
		this.yyShoppingCartService = yyShoppingCartService;
	}
	
}
