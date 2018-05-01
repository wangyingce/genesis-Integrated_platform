package com.ysyl.weixin.message.web;

import com.ysyl.restFulServlet.weixin.vo.WxInputVo;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.message.vo.WxSendTemlateDetail2Vo;
import com.ysyl.weixin.message.vo.WxSendTemlateDetailVo;
import com.ysyl.weixin.message.vo.WxSendTemplateVo;

import ins.framework.web.Struts2Action;

public class WxMessageAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	private WxMessageService wxMessageService;
	
	/**
	 * 微信关注取消关注事件
	 * @param wxInputVo
	 * @return
	 */
	public String getWxMessage(WxInputVo wxInputVo){
		try {
			if(WxCodeConst.MsgType.event.equals(wxInputVo.getMsgType())){
				//如果是事件推送或者普通消息则调用事件相关service
				wxMessageService.subOrUnSubScribe(wxInputVo);
			}else if(WxCodeConst.MsgType.text.equals(wxInputVo.getMsgType())){
//				//如果是普通消息则调用普通消息service
				String sendMessage = "客服📞：13669291037\n门店地址：西安市碑林区仁厚庄南路星币传说小区3号楼1单元";
				wxMessageService.sendTextToUserId(wxInputVo.getFromUserName(),sendMessage);
				
//				WxSendTemplateVo wxSendTemplateVo = new WxSendTemplateVo();
//				WxSendTemlateDetailVo wxSendTemlateDetailVo = new WxSendTemlateDetailVo();
//				wxSendTemplateVo.setTouser(wxInputVo.getFromUserName());
//				wxSendTemplateVo.setUrl("http://www.lmeij.com/ysyl");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2VoFirst = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2VoFirst.setValue("恭喜你付款成功!");
//				wxSendTemlateDetail2VoFirst.setColor("#173177");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2Vo1 = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2Vo1.setValue("中科软科技");
//				wxSendTemlateDetail2Vo1.setColor("#173177");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2Vo2 = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2Vo2.setValue("￥120.23");
//				wxSendTemlateDetail2Vo2.setColor("#173177");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2Vo3 = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2Vo3.setValue("2016-1-31 22:31:32");
//				wxSendTemlateDetail2Vo3.setColor("#173177");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2Vo4 = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2Vo4.setValue("3520");
//				wxSendTemlateDetail2Vo4.setColor("#173177");
//				
//				WxSendTemlateDetail2Vo wxSendTemlateDetail2VoRe = new WxSendTemlateDetail2Vo();
//				wxSendTemlateDetail2VoRe.setValue("欢迎再次光临!");
//				wxSendTemlateDetail2VoRe.setColor("#173177");
//				
//				wxSendTemlateDetailVo.setFirst(wxSendTemlateDetail2VoFirst);
//				wxSendTemlateDetailVo.setKeyword1(wxSendTemlateDetail2Vo1);
//				wxSendTemlateDetailVo.setKeyword2(wxSendTemlateDetail2Vo2);
//				wxSendTemlateDetailVo.setKeyword3(wxSendTemlateDetail2Vo3);
//				wxSendTemlateDetailVo.setKeyword4(wxSendTemlateDetail2Vo4);
//				wxSendTemlateDetailVo.setRemark(wxSendTemlateDetail2VoRe);
//				wxSendTemplateVo.setData(wxSendTemlateDetailVo);
//				
//				wxMessageService.sendTemplateMessage(WxCodeConst.WxTemplateId.WxTemplateId_1,wxSendTemplateVo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public WxMessageService getWxMessageService() {
		return wxMessageService;
	}
	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}
}