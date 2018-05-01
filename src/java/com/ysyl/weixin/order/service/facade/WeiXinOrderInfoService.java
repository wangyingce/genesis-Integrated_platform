package com.ysyl.weixin.order.service.facade;

import ins.framework.common.Page;

import com.ysyl.weixin.order.vo.OrderInfoDto;
import com.ysyl.weixin.order.vo.OrderQueryVo;
import com.ysyl.weixin.schema.model.YywxOrderInfo;

public interface WeiXinOrderInfoService {
	/**保存订单业务数据*/
	public YywxOrderInfo saveOrderInfo(YywxOrderInfo orderInfo);
	/**根据XML解析订单并保存*/
	public YywxOrderInfo saveUserInfoByXml(String xml) throws Exception;
	/** 根据XML解析订单 */
	public YywxOrderInfo pasOrderInfo(String xml);
	/** 根据商户订单号查询订单 */
	public YywxOrderInfo findWeiXinOrderInfoByPk(String orderId);
	/** 根据商户订单号查询订单 明细*/
	public OrderInfoDto findWeiXinOrderDetailByPk(String orderId) throws Exception;
	/** 调用微信订单查询接口更新询订单 */
	public YywxOrderInfo updateWeiXinOrderInfoToWeiXin(OrderInfoDto orderInfoDto) throws Exception;
	/** 订单查询 */
	public Page saveAndfindOrderList(OrderQueryVo orderQueryVo);
}
