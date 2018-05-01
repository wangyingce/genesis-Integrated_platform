package com.ysyl.weixin.message.service.facade;

import com.ysyl.restFulServlet.weixin.vo.WxInputVo;
import com.ysyl.weixin.message.vo.WxSendTemplateVo;

public interface WxMessageService{
	/**
	 * 微信关注及取消关注事件
	 * @param wxInputVo 微信入参
	 */
	public void subOrUnSubScribe(WxInputVo wxInputVo) throws Exception;
	
	/**
	 * 微信通用发送普通消息接口
	 * @param content 要发送的话
	 * @param toUserOpenId 要发送的客户Id
	 */
	public void sendTextToUserId(String toUserOpenId,String content) throws Exception;
	
	/**
	 * 发送微信模板消息
	 * @param templateId 模板Id
	 * @param templateMap 入参map，key-value
	 * first-DATA
	   keynote1-value
	   ......
	   keynote3-value
	   remark-value
	 */
	public void sendTemplateMessage(String templateId,WxSendTemplateVo wxSendTemplateVo) throws Exception;
}
