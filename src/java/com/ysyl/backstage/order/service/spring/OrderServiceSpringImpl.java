package com.ysyl.backstage.order.service.spring;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ysyl.backstage.order.service.facade.OrderService;
import com.ysyl.backstage.schema.model.YybsExpress;
import com.ysyl.backstage.schema.model.YybsOrder;
import com.ysyl.backstage.schema.model.YybsOrderDetail;
public class OrderServiceSpringImpl extends GenericDaoHibernate<YybsOrder, String> implements OrderService{
	/**
	 * 查询orderList
	 * @param orderId
	 * @param orderContent
	 * @param inputtime
	 * @param orderGenus
	 * @param orderSeries
	 * @return
	 * @throws Exception
	 */
	public List<YybsOrder> findOrderFormByItems(Long orderId,String orderContent, String inputtime,  String orderGenus, String orderSeries) throws Exception {
		QueryRule qr = QueryRule.getInstance();
		if(orderId!=null&&!"".equals(orderId)){
			qr.addEqual("orderId", new Long(orderId));
		}
		if(orderSeries!=null&&!"".equals(orderSeries)){
			qr.addEqual("orderSeries",orderSeries);
		}
		if(orderGenus!=null&&!"".equals(orderGenus)){
			qr.addEqual("orderGenus",orderGenus);
		}
		if(orderContent!=null&&!"".equals(orderContent)){
			qr.addLike("orderContent", orderContent+"%");
		}
		if(inputtime!=null&&!"".equals(inputtime)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Date ipttm = sdf.parse(inputtime);
			qr.addGreaterThan("inputtime", ipttm);
		}
		
		List<YybsOrder> list  = super.find(YybsOrder.class,qr);
		return list;
	}

	/**
	 * 主键查询Order
	 * @param orderId
	 * @return
	 */
	public YybsOrder findOrderById(Long orderId) {
		QueryRule qr = QueryRule.getInstance();
		if(orderId!=null&&!"".equals(orderId)){
			qr.addEqual("orderId", new Long(orderId));
		}	
		List<YybsOrder> list  = super.find(YybsOrder.class,qr);

		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 更新订单信息
	 * @param order
	 */
	public void saveOrUpdateOrder(YybsOrder order) {
		super.update(order);		
	}

	/**
	 * 查询订单详细
	 */
	public List<YybsOrderDetail> findOrderDetailById(Long orderId) {
		QueryRule qr = QueryRule.getInstance();
		if(orderId!=null&&!"".equals(orderId)){
			qr.addEqual("orderId", new Long(orderId));
		}	
		List<YybsOrderDetail> list  = super.find(YybsOrderDetail.class,qr);
		return list;
	}

	/**
	 * 查询订单物流信息
	 */
	public List<YybsExpress> findExpressByExpressNo(Long expressNo) {
		QueryRule qr = QueryRule.getInstance();
		if(expressNo!=null&&!"".equals(expressNo)){
			qr.addEqual("expressNo", new Long(expressNo));
		}	
		List<YybsExpress> list  = super.find(YybsExpress.class,qr);
		return list;
	}

}
