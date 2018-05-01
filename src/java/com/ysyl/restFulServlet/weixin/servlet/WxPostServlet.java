package com.ysyl.restFulServlet.weixin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ysyl.common.util.XstreamUtil;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.restFulServlet.weixin.vo.WxInputVo;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.message.web.WxMessageAction;
import com.ysyl.weixin.pay.utils.SignUtil;

/**
 * 接收微信post消息公共类，所有接收都从这个入口
 * @author ysyl
 *
 */
public class WxPostServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(WxPostServlet.class);
	private static final long serialVersionUID = 1L;
	private WxMessageAction wxMessageAction;
	private WxMessageService wxMessageService;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用解析xml公共类
		String responseMessage = "";
		WxInputVo wxInputVo = null;
		try {
			//根据request转换xml
			String postXml = ServletUtil.postXmlByRequest(request);
			System.out.println("postXml="+postXml);
			if(!"".equals(postXml)){
				//将xml转换为对象
				wxInputVo = XstreamUtil.XMLToVo(postXml,WxInputVo.class);
				//格式化微信整型时间，微信入参时间类型是一个长整型，需要转换
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(wxInputVo.getCreateTime()!=null && !"".equals(wxInputVo.getCreateTime())){
					String createTime = sf.format(new Date(new Long(wxInputVo.getCreateTime())*1000L));
					wxInputVo.setCreateTime(createTime);
				}
				//先判断消息类型
				if(WxCodeConst.MsgType.event.equals(wxInputVo.getMsgType()) || WxCodeConst.MsgType.text.equals(wxInputVo.getMsgType())){
					//如果是事件推送或者普通消息则调用事件相关Action
					wxMessageAction.getWxMessage(wxInputVo);
				}
			}else{
				//如果是空看看是不是微信验证，微信加密签名
				String msg_signature = request.getParameter("signature");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				 // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		        if (SignUtil.checkSignature(msg_signature, timestamp, nonce)) {
		        	responseMessage = echostr;
		            logger.debug(echostr);
		        }else {
		        	logger.debug("不是微信服务器发来的请求,请小心!");
		        }
				
			}
		} catch (IOException e) {
			try {
				wxMessageService.sendTextToUserId(wxInputVo.getFromUserName(),"抱歉，系统异常未能识别您的操作！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            e.printStackTrace();  
        } finally{
        	try {
				ServletUtil.backResponse(response,responseMessage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();   
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
		this.wxMessageAction = (WxMessageAction) ctx.getBean("wxMessageAction");
		this.wxMessageService = (WxMessageService) ctx.getBean("wxMessageService");
	}
	
}