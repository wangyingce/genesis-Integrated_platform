package com.ysyl.weixin.order.service.spring;

import ins.framework.common.HqlQueryRule;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.exception.BusinessException;
import ins.framework.utils.DataUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyPay;
import com.ysyl.common.schema.model.YyPgMyOrder;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.service.facade.CodeService;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.user.service.facade.UserService;
import com.ysyl.common.util.DateUtil;
import com.ysyl.mobile.pay.service.facade.PayService;
import com.ysyl.mobile.photograph.service.facade.PhotoGraphService;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.common.util.CommonUtils;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.message.vo.WxSendTemlateDetail2Vo;
import com.ysyl.weixin.message.vo.WxSendTemlateDetailVo;
import com.ysyl.weixin.message.vo.WxSendTemplateVo;
import com.ysyl.weixin.order.service.facade.WeiXinOrderInfoService;
import com.ysyl.weixin.order.vo.OrderInfoDto;
import com.ysyl.weixin.order.vo.OrderQueryVo;
import com.ysyl.weixin.pay.utils.SignUtil;
import com.ysyl.weixin.schema.model.YywxMerchant;
import com.ysyl.weixin.schema.model.YywxOrderInfo;

public class WeiXinOrderInfoServiceSpringImpl extends GenericDaoHibernate<YywxOrderInfo, String> implements WeiXinOrderInfoService {
	private Logger logger = Logger.getLogger(WeiXinOrderInfoServiceSpringImpl.class);
	private CommonService commonService;
	private WxMessageService wxMessageService;
	private CodeService codeService;
	private MerchantService merchantService;
	private PayService payService;
	private UserService userService;


	/**保存订单业务数据*/
	public YywxOrderInfo saveOrderInfo(YywxOrderInfo orderInfo) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("orderid", orderInfo.getOrderid());
		YywxOrderInfo order = super.findUnique(qr);
		if(order!=null){
			DataUtils.copySimpleObject(orderInfo, order, false);
			super.save(order);
			return order;
		}else{
			super.save(orderInfo);
			return orderInfo;
		}
	}

	/**根据XML解析订单并保存*/
	public YywxOrderInfo saveUserInfoByXml(String xml) throws Exception{
		YywxOrderInfo orderInfoOld = this.pasOrderInfo(xml);
		//需调整
		String partnerkey = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.PARTNERKEY).getConfigValue();
		OrderInfoDto orderDto = new OrderInfoDto();
		orderDto.setAppid(orderInfoOld.getAppid());
		orderDto.setMch_id(orderInfoOld.getMch_id());
		orderDto.setTransaction_id(orderInfoOld.getTransaction_id());
		orderDto.setNonce_str(SignUtil.getRandom12());
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("appid", orderDto.getAppid());  
		map.put("mch_id", orderDto.getMch_id());  
		map.put("transaction_id", orderDto.getTransaction_id());  
		map.put("nonce_str", orderDto.getNonce_str());  
		String sign = SignUtil.getSign(map, partnerkey);
		orderDto.setSign(sign);
		//调用查询微信订单接口更新微信订单最新状态
		YywxOrderInfo orderInfoNew = this.updateWeiXinOrderInfoToWeiXin(orderDto);
		//保存完orderinfo后再保存yypay表
		YyPay yyPay = payService.updateYyPayByYyOrderInfo(orderInfoNew);
		
		String nickName = "";
		YyPgMyOrder yyPgMyOrder = null;
		//回写不同的事
		if("SUCCESS".equals(orderInfoNew.getTrade_state())){
			//根据不同商户id做不同的事以及不同的回写
			Yydconfig yydconfig = codeService.getYydconfig(CodeConst.ConfigCode.weixinpayback, orderInfoNew.getAttach());
			if(yydconfig!=null && CodeConst.SourceType.pg.equals(yydconfig.getConfigValue())){
				//如果是摄影工作室则回调摄影工作室相关service并获取商户名称
				PhotoGraphService photoGraphService = (PhotoGraphService) applicationContext.getBean("photoGraphService");
				yyPgMyOrder = photoGraphService.PayBack(yyPay);
				nickName = orderInfoNew.getBody();

			}else{
				YywxMerchant merchar = merchantService.queryMerchantByMerchantId(orderInfoNew.getAttach());
				nickName = merchar.getNickName();
			}
		}
		
		//发送消息
		if("0".equals(orderInfoNew.getFlag())&&"SUCCESS".equals(orderInfoNew.getTrade_state())){
			//发送模板消息给顾客
			this.sendWxTemplateToBuyer(orderInfoNew, nickName);
			//发送模板消息给商家
			this.sendWxTemplateToOwner(yyPgMyOrder);
			//业务数据处理成功后flag改为1
			orderInfoNew.setFlag("1");
		}
		//flag='1'时说明之前通知过，不需要再处理业务数据
		orderInfoNew= this.saveOrderInfo(orderInfoNew);
		return orderInfoNew;
	}
	
	/**
	 * 发送消息给顾客
	 * @return
	 */
	private WxSendTemplateVo sendWxTemplateToBuyer(YywxOrderInfo orderInfoNew,String nickName) throws Exception{
//		flag='0'时说明之前没有通知过，此处预留支付成功后的业务数据
		WxSendTemplateVo wxSendTemplateVo = new WxSendTemplateVo();
		wxSendTemplateVo.setTouser(orderInfoNew.getOpenid());
		wxSendTemplateVo.setUrl(commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "")+"/cm/cf.do?inputId="+orderInfoNew.getOrderid()+"&type=wo");
		WxSendTemlateDetailVo detailVo = new WxSendTemlateDetailVo();
		String firstMes = codeService.translateName(CodeConst.CodeType.Message, CodeConst.Message.Message_1);
		WxSendTemlateDetail2Vo dvFrist = new WxSendTemlateDetail2Vo();
		dvFrist.setValue(firstMes);
		dvFrist.setColor("#173177");
		detailVo.setFirst(dvFrist);
		
		WxSendTemlateDetail2Vo dv1 = new WxSendTemlateDetail2Vo();
		
		dv1.setValue(nickName);
		dv1.setColor("#173177");
		detailVo.setKeyword1(dv1);
		
		WxSendTemlateDetail2Vo dv2 = new WxSendTemlateDetail2Vo();
		dv2.setValue("￥"+String.valueOf(Float.parseFloat(String.valueOf(orderInfoNew.getTotal_fee()))/100));
		dv2.setColor("#173177");
		detailVo.setKeyword2(dv2);
		
		WxSendTemlateDetail2Vo dv3 = new WxSendTemlateDetail2Vo();
		Date d = DateUtil.stringToDate(String.valueOf(orderInfoNew.getTime_end()), 2);
		dv3.setValue(DateUtil.dateToString(d, 3));
		dv3.setColor("#173177");
		detailVo.setKeyword3(dv3);
		
		WxSendTemlateDetail2Vo dv4 = new WxSendTemlateDetail2Vo();
		dv4.setValue(orderInfoNew.getOrderid());
		dv4.setColor("#173177");
		detailVo.setKeyword4(dv4);
		
		WxSendTemlateDetail2Vo dvRemark = new WxSendTemlateDetail2Vo();
		String lastMes = codeService.translateName(CodeConst.CodeType.Message, CodeConst.Message.Message_2);
		dvRemark.setValue(lastMes);
		dvRemark.setColor("#173177");
		detailVo.setRemark(dvRemark);
		wxSendTemplateVo.setData(detailVo);
		wxMessageService.sendTemplateMessage(WxCodeConst.WxTemplateId.WxTemplateId_1, wxSendTemplateVo);
		return wxSendTemplateVo;
	}
	
	/**
	 * 发送消息给商家
	 * @return
	 */
	private void sendWxTemplateToOwner(YyPgMyOrder yyPgMyOrder) throws Exception{
		//根据商户id查询需要通知的商户微信id
		Yydconfig yydconfig = codeService.getYydconfig(CodeConst.ConfigCode.wxtemplatesendid, yyPgMyOrder.getOwner());
		//如果有多条，需要循环通知
		String[] openIds = yydconfig.getConfigValue().split(",");
		for (int i = 0; i < openIds.length; i++) {
			String openId = openIds[i];
			WxSendTemplateVo wxSendTemplateVo = new WxSendTemplateVo();
			wxSendTemplateVo.setTouser(openId);
			wxSendTemplateVo.setUrl(commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "")+"/photograph/sofo.do?inKey="+yyPgMyOrder.getId());
			WxSendTemlateDetailVo detailVo = new WxSendTemlateDetailVo();
			//String firstMes = codeService.translateName(CodeConst.CodeType.Message, CodeConst.Message.Message_1);
			WxSendTemlateDetail2Vo dvFrist = new WxSendTemlateDetail2Vo();
			dvFrist.setValue("您好，您有新的预约信息");
			dvFrist.setColor("#173177");
			detailVo.setFirst(dvFrist);
			
			WxSendTemlateDetail2Vo dv1 = new WxSendTemlateDetail2Vo();
			
			dv1.setValue(yyPgMyOrder.getUserName());
			dv1.setColor("#173177");
			detailVo.setKeyword1(dv1);
			
			WxSendTemlateDetail2Vo dv2 = new WxSendTemlateDetail2Vo();
			dv2.setValue(yyPgMyOrder.getUserPhone());
			dv2.setColor("#173177");
			detailVo.setKeyword2(dv2);
			
			WxSendTemlateDetail2Vo dv3 = new WxSendTemlateDetail2Vo();
			dv3.setValue(yyPgMyOrder.getCityName() +" "+yyPgMyOrder.getPhotoName());
			dv3.setColor("#173177");
			detailVo.setKeyword3(dv3);
			
			WxSendTemlateDetail2Vo dv4 = new WxSendTemlateDetail2Vo();
			dv4.setValue("1");
			dv4.setColor("#173177");
			detailVo.setKeyword4(dv4);
			
			WxSendTemlateDetail2Vo dvRemark = new WxSendTemlateDetail2Vo();
			dvRemark.setValue(yyPgMyOrder.getPgDate()+" "+yyPgMyOrder.getPgBeginTime()+"点-"+yyPgMyOrder.getPgEndTime()+"点");
			dvRemark.setColor("#173177");
			detailVo.setRemark(dvRemark);
			wxSendTemplateVo.setData(detailVo);
			wxMessageService.sendTemplateMessage(WxCodeConst.WxTemplateId.WxTemplateId_2, wxSendTemplateVo);
		}
		
	}

	/** 根据XML解析订单 */
	public YywxOrderInfo pasOrderInfo(String xml) {
		XStream xs = new XStream();
		xs.alias("xml", OrderInfoDto.class);
		xs.aliasField("out_trade_no", OrderInfoDto.class, "orderid");
		OrderInfoDto order = (OrderInfoDto) xs.fromXML(xml);
		if(!WxCodeConst.WxState.success.equals(order.getResult_code())){
			throw new BusinessException("微信订单查询失败",false);
		}
		
		YywxOrderInfo orderInfo = new YywxOrderInfo();
		DataUtils.copySimpleObject(order, orderInfo, false);
		return orderInfo;
	}

	/** 根据商户订单号查询订单 */
	public YywxOrderInfo findWeiXinOrderInfoByPk(String orderId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("orderid", orderId);
		YywxOrderInfo orderInfo = super.findUnique(qr);
		return orderInfo;
	}
	
	/** 调用微信订单查询接口更新询订单 */
	public YywxOrderInfo updateWeiXinOrderInfoToWeiXin(OrderInfoDto orderInfoDto) throws Exception{
		XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));  
		xs.alias("xml", OrderInfoDto.class);
		if(orderInfoDto.getTransaction_id()!=null&&!"".equals(orderInfoDto.getTransaction_id())){
			orderInfoDto.setOrderid(null);
		}else{
			xs.aliasField("out_trade_no", OrderInfoDto.class, "orderid");
		}
		String xml = xs.toXML(orderInfoDto);
		String url=commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WXorderquery, "");
		String rXml = CommonUtils.sendForHttp(xml, url);
		YywxOrderInfo wxOrderInfo = this.pasOrderInfo(rXml);
		wxOrderInfo= this.saveOrderInfo(wxOrderInfo);
		return wxOrderInfo;
	}

	public Page saveAndfindOrderList(OrderQueryVo orderQueryVo) {
		HqlQueryRule hqlQueryRule = new HqlQueryRule();
		if(orderQueryVo.getPayDate1()!=null&&!"".equals(orderQueryVo.getPayDate1())){
			orderQueryVo.setPayDate1(orderQueryVo.getPayDate1().replace("T", " "));
			Date date = DateUtil.stringToDate(orderQueryVo.getPayDate1(), 9);
			String payDate = DateUtil.dateToString(date, 2); 
			logger.debug(payDate);
			hqlQueryRule.addGreaterEqual("order.time_end", Long.parseLong(payDate));
		}
		if(orderQueryVo.getPayDate2()!=null&&!"".equals(orderQueryVo.getPayDate2())){
			orderQueryVo.setPayDate2(orderQueryVo.getPayDate2().replace("T", " "));
			Date date = DateUtil.stringToDate(orderQueryVo.getPayDate2(), 9);
			String payDate = DateUtil.dateToString(date, 2); 
			hqlQueryRule.addLessEqual("order.time_end", Long.parseLong(payDate));
		}
		StringBuffer hql = new StringBuffer();
		hql.append("select order from YywxOrderInfo order where order.valid='1' and order.flag='1' ");
		String con = hqlQueryRule.getHql();
		if(con.length()>0){
			hql.append(" and ");
		}
		hql.append(con);
		logger.debug(hql.toString());
		Page page = super.findByHql(hql.toString(), orderQueryVo.getPageNo(), orderQueryVo.getPageSize(), hqlQueryRule.getValues());
		//需调整
		String partnerkey = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.PARTNERKEY).getConfigValue();
		List<OrderInfoDto> dtoList = new ArrayList<OrderInfoDto>();
		if(page!=null){
			List<YywxOrderInfo> list = page.getResult();
			if(list.size()>0){
				for (YywxOrderInfo order : list) {
					OrderInfoDto orderDto = new OrderInfoDto();
					if(!"SUCCESS".equals(order.getTrade_state())){
						orderDto.setAppid(order.getAppid());
						orderDto.setMch_id(order.getMch_id());
						orderDto.setTransaction_id(order.getTransaction_id());
						orderDto.setNonce_str(SignUtil.getRandom12());
						SortedMap<String, String> map = new TreeMap<String, String>();
						map.put("appid", orderDto.getAppid());  
						map.put("mch_id", orderDto.getMch_id());  
						map.put("transaction_id", orderDto.getTransaction_id());  
						map.put("nonce_str", orderDto.getNonce_str());  
						String sign = SignUtil.getSign(map, partnerkey);
						orderDto.setSign(sign);
						try{
							YywxOrderInfo orderInfo = this.updateWeiXinOrderInfoToWeiXin(orderDto);
							DataUtils.copySimpleObject(orderInfo, orderDto);
							dtoList.add(orderDto);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						DataUtils.copySimpleObject(order, orderDto);
					}
					float f = Float.parseFloat(String.valueOf(orderDto.getTotal_fee()))/100;
					orderDto.setTotal_fee1(f);
					Date d = DateUtil.stringToDate(String.valueOf(orderDto.getTime_end()), 2);
					orderDto.setTime_end1(DateUtil.dateToString(d, 3));
					dtoList.add(orderDto);
				}
			}
			
		}
		return new Page(orderQueryVo.getPageNo(), page.getTotalCount(), orderQueryVo.getPageSize(), dtoList);
	}
	
	/** 根据商户订单号查询订单 明细*/
	public OrderInfoDto findWeiXinOrderDetailByPk(String orderId) throws Exception{
		YywxOrderInfo orderInfo =this.findWeiXinOrderInfoByPk(orderId);
		//YywxMerchant merchant = merchantService.queryMerchantByMerchantId(orderInfo.getAttach());
		OrderInfoDto info = new OrderInfoDto();
		DataUtils.copySimpleObject(orderInfo, info);
		info.setNickName(orderInfo.getBody());
		float f = Float.parseFloat(String.valueOf(info.getTotal_fee()))/100;
		info.setTotal_fee1(f);
		Date d = DateUtil.stringToDate(String.valueOf(info.getTime_end()), 2);
		info.setTime_end1(DateUtil.dateToString(d, 3));
		String bankType = codeService.translateName(CodeConst.CodeType.BankType,info.getBank_type().replaceAll("_", ""));
		info.setBank_type(bankType);
		if("SUCCESS".equals(info.getTrade_state())){
			info.setTrade_state("支付成功");
		}
		return info;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public WxMessageService getWxMessageService() {
		return wxMessageService;
	}

	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}


	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	public MerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	
	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
