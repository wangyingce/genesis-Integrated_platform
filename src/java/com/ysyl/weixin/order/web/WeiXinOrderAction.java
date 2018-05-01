package com.ysyl.weixin.order.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.weixin.order.service.facade.WeiXinOrderInfoService;
import com.ysyl.weixin.order.vo.OrderInfoDto;
import com.ysyl.weixin.order.vo.OrderQueryVo;

public class WeiXinOrderAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(WeiXinOrderAction.class);
	private OrderQueryVo orderQueryVo;
	private WeiXinOrderInfoService weiXinOrderInfoService;
	private String orderid; 


	public String orderQuery(){
		if(orderQueryVo!=null){
			Page page = weiXinOrderInfoService.saveAndfindOrderList(orderQueryVo);
			List list = page.getResult();
			if(list!=null&&list.size()>0){
				JSONObject alljson = new JSONObject();
				JSONArray json = JSONArray.fromObject(page.getResult());
				alljson.put("jsondata", json);
				alljson.put("page", orderQueryVo.getPageNo());//第几页
				alljson.put("total", page.getTotalPageCount());//总页数
				alljson.put("record", page.getTotalCount());//总数
				logger.debug(alljson);
				PrintWriter out = null;
				try {
					out = this.getResponse().getWriter();
					out.print(alljson);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(out!=null){
						out.close();
					}
				}
			}else{
			}
		}
		return null;
	}
	
	public String initOrder() throws Exception{
		OrderInfoDto info = weiXinOrderInfoService.findWeiXinOrderDetailByPk(orderid);
		Gson g = new Gson();
		String json = g.toJson(info);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
		
	}

	public OrderQueryVo getOrderQueryVo() {
		return orderQueryVo;
	}

	public void setOrderQueryVo(OrderQueryVo orderQueryVo) {
		this.orderQueryVo = orderQueryVo;
	}
	
	public WeiXinOrderInfoService getWeiXinOrderInfoService() {
		return weiXinOrderInfoService;
	}

	public void setWeiXinOrderInfoService(
			WeiXinOrderInfoService weiXinOrderInfoService) {
		this.weiXinOrderInfoService = weiXinOrderInfoService;
	}
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
