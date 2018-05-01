package com.ysyl.backstage.order.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.backstage.order.service.facade.OrderService;
import com.ysyl.backstage.order.vo.ExpVo;
import com.ysyl.backstage.order.vo.OrderDetailVo;
import com.ysyl.backstage.order.vo.OrderVo;
import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.backstage.report.service.facade.ReportService;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.file.service.facade.FileService;

import ins.framework.utils.DataUtils;
import ins.framework.web.Struts2Action;

import com.ysyl.backstage.schema.model.YybsExpress;
import com.ysyl.backstage.schema.model.YybsOrder;
import com.ysyl.backstage.schema.model.YybsOrder;
import com.ysyl.backstage.schema.model.YybsOrderDetail;
import com.ysyl.backstage.schema.model.YybsProduct;

public class OrderAction  extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	private ReportService reportService;
	private OrderService orderService;
	private FileService fileService;
	private ProductService productService;
	/**model of yybsorder*/
	private Long orderId;
	private String orderContent;
	private String inputtime;
	private String orderSeries;
	private String orderGenus;
	private String expressNo;
	private String expressName;
	private String cod;

	/**
	 * 验证用户权限
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	private YyUser checkPower() throws Exception{
		String usercode = (String) getRequest().getSession().getAttribute("UserCode");
		String username = (String) getRequest().getSession().getAttribute("UserName");
		if(usercode ==null||"".equals(usercode)||username==null||"".equals(username)){
			throw new Exception("用户未登录，请登录");
		}
		YyUser userDto  = loginService.findUserByNameAndCode(username,usercode);
		return userDto;
	}
	
	
	public String queryOrderDetail() throws Exception{
		JSONObject alljson = new JSONObject();
		alljson = reportService.addJsonData(alljson,"order");
		alljson = reportService.addJsonData(alljson,"order_amount");
		logger.debug(alljson);
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			out = this.getResponse().getWriter();
			out.print(alljson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		return null;
	}
	
	public String queryOrderMap() throws Exception{
		JSONObject alljson = new JSONObject();
		alljson = reportService.addJsonData(alljson,"order_map");
		logger.debug(alljson);
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			out = this.getResponse().getWriter();
			out.print(alljson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		return null;
	}

	public String queryOrderForm() throws Exception{
		JSONObject alljson = new JSONObject();
		this.orderGenus = new String(orderGenus.getBytes("ISO-8859-1"), "utf-8");
		List<YybsOrder> orders = orderService.findOrderFormByItems(this.orderId,this.orderContent,this.inputtime,this.orderGenus,this.orderSeries);
		List<OrderVo> orderVos = new ArrayList<OrderVo>(0);
		if(orders!=null&&orders.size()>0){
			SimpleDateFormat orderdteformat = new SimpleDateFormat("yyyy-MM-dd");
			for(YybsOrder order : orders){
				OrderVo ordervo = new OrderVo();
				DataUtils.copySimpleObject(order, ordervo);
				ordervo.setInputtime(orderdteformat.format(order.getInputtime()));
//				if(order.getImgId()!=null&&!"".equals(order.getImgId())){
//					YyImageFile imgfl = fileService.findImageFileById(order.getImgId());
//					if(imgfl!=null){
//						ordervo.setImgId(imgfl.getFilepath());//将图片id转化成路径
//					}
//				}
				orderVos.add(ordervo);
			}
		}
		JSONArray ordersJson = JSONArray.fromObject(orderVos);
		alljson.put("ordersJson", ordersJson);
		alljson.put("record", ordersJson.size());// 总条数
		alljson = reportService.addJsonData(alljson,"order_percent");
		logger.debug(alljson);
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			out = this.getResponse().getWriter();
			out.print(alljson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		return null;
	}
	
	public String updateOrder() throws Exception{
		YybsOrder order = orderService.findOrderById(this.orderId);
		order.setExpressNo(new Long(expressNo));
		order.setExpressName(new String(expressName.getBytes("ISO-8859-1"), "utf-8"));
		if(cod=="true"||"true".equals(cod)){
			order.setCod("1");
		}else{
			order.setCod("0");
		}
		orderService.saveOrUpdateOrder(order);
		Gson g = new Gson();
		String json = "";
		json = g.toJson(1);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}

	/**
	 * 查询订单详细信息
	 * @return
	 * @throws Exception
	 */
	public String queryOrderDetailInfo() throws Exception{
		/**查询订单信息数据*/
		YybsOrder order = orderService.findOrderById(this.orderId);
		OrderVo ordervo = new OrderVo();
		DataUtils.copySimpleObject(order, ordervo);
		SimpleDateFormat orderdteformat = new SimpleDateFormat("yyyy-MM-dd");
		ordervo.setInputtime(orderdteformat.format(order.getInputtime()));
		ordervo.setExpressStartDate(orderdteformat.format(order.getExpressStartDate()));
		List<YybsOrderDetail> ods = orderService.findOrderDetailById(orderId);
		List<OrderDetailVo> orderdetailvolist = new ArrayList<OrderDetailVo>(0);
		if(ods!=null&&ods.size()>0){
			for(YybsOrderDetail dtls :ods){
				OrderDetailVo orderdetailvo = new OrderDetailVo();
				DataUtils.copySimpleObject(dtls, orderdetailvo);
				orderdetailvo.setOrderInputtime(dtls.getInputtime());
				YyImageFile imgfl = fileService.findImageFileById(dtls.getProdImgPath());
				if(imgfl!=null){
					orderdetailvo.setProdImgPath(imgfl.getFilepath());//将图片id转化成路径
				}
				YybsProduct prod = productService.findProdById(orderdetailvo.getProductId());
				if(prod!=null){
					DataUtils.copySimpleObject(prod, orderdetailvo);
					orderdetailvo.setProdInputtime(prod.getInputtime());
				}
				orderdetailvolist.add(orderdetailvo);
			}
		}
		List<YybsExpress> exps = orderService.findExpressByExpressNo(order.getExpressNo());
		List<ExpVo> expVoList = new ArrayList<ExpVo>(0);
		if(exps!=null&&exps.size()>0){
			for(YybsExpress exp :exps){
				ExpVo expvo = new ExpVo();
				DataUtils.copySimpleObject(exp, expvo);
				expvo.setInputtime(orderdteformat.format(exp.getInputtime()));
				expVoList.add(expvo);
			}
		}
		/**赋值返回json*/
//		JSONArray expsJson = JSONArray.fromObject(expVoList);
		JSONObject aJson = new JSONObject();
		aJson.put("order", ordervo);
		aJson.put("orderdetail", orderdetailvolist);
		aJson.put("exps", expVoList);
		logger.debug(aJson);
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			out = this.getResponse().getWriter();
			out.print(aJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		return null;
	}
		
	

	public LoginService getLoginService() {
		return loginService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	public ReportService getReportService() {
		return reportService;
	}


	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}


	public OrderService getOrderService() {
		return orderService;
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public String getOrderContent() {
		return orderContent;
	}


	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}


	public String getInputtime() {
		return inputtime;
	}


	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}


	public FileService getFileService() {
		return fileService;
	}


	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}


	public String getOrderSeries() {
		return orderSeries;
	}


	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}


	public String getOrderGenus() {
		return orderGenus;
	}


	public void setOrderGenus(String orderGenus) {
		this.orderGenus = orderGenus;
	}


	public String getExpressNo() {
		return expressNo;
	}


	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}


	public String getExpressName() {
		return expressName;
	}


	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}


	public String getCod() {
		return cod;
	}


	public void setCod(String cod) {
		this.cod = cod;
	}


	public ProductService getProductService() {
		return productService;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
}
