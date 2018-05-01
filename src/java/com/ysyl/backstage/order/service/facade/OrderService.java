package com.ysyl.backstage.order.service.facade;

import java.util.List;

import com.ysyl.backstage.schema.model.YybsExpress;
import com.ysyl.backstage.schema.model.YybsOrder;
import com.ysyl.backstage.schema.model.YybsOrderDetail;

public interface OrderService {

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
	List<YybsOrder> findOrderFormByItems(Long orderId, String orderContent, String inputtime, String orderGenus, String orderSeries) throws Exception;

	/**
	 * 主键查询Order
	 * @param orderId
	 * @return
	 */
	YybsOrder findOrderById(Long orderId);

	/**
	 * 更新订单信息
	 * @param order
	 */
	void saveOrUpdateOrder(YybsOrder order);

	/**
	 * 查询订单详细
	 * @param orderId
	 * @return
	 */
	List<YybsOrderDetail> findOrderDetailById(Long orderId);

	/**
	 * 查询订单物流信息
	 * @param orderId
	 * @return
	 */
	List<YybsExpress> findExpressByExpressNo(Long expressNo);

}
