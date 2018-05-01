package com.ysyl.restFulServlet.alipay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ysyl.common.util.XstreamUtil;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.restFulServlet.weixin.vo.AlipayInputVo;
import com.ysyl.restFulServlet.weixin.vo.WxInputVo;

/**
 * 接收微信post消息公共类，所有接收都从这个入口
 * @author ysyl
 *
 */
public class AliPayPostServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(AliPayPostServlet.class);
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用解析xml公共类
		String postXml = ServletUtil.postXmlByRequest(request);
		AlipayInputVo alipay = null;
		WxInputVo wxInputVo = null;
		try {
//			alipay = XstreamUtil.XMLToVo(postXml,AlipayInputVo.class); 
			wxInputVo = XstreamUtil.XMLToVo(postXml,WxInputVo.class); 
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println(alipay.getIsSuccess());  
        System.out.println(alipay.getRequest().getParam().get(0).getContent());  
	}
}