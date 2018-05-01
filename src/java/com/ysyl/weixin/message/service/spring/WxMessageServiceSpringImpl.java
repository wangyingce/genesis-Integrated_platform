package com.ysyl.weixin.message.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyPgIndex;
import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.service.facade.CodeService;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.util.JsonUtil;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.restFulServlet.weixin.vo.WxInputVo;
import com.ysyl.weixin.WxCodeConst;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.message.vo.WxSendMessageDetailtVo;
import com.ysyl.weixin.message.vo.WxSendMessageVo;
import com.ysyl.weixin.message.vo.WxSendPhotoMessageListDetailVo;
import com.ysyl.weixin.message.vo.WxSendPhotoMessageListVo;
import com.ysyl.weixin.message.vo.WxSendPhotoMessageVo;
import com.ysyl.weixin.message.vo.WxSendTemplateVo;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class WxMessageServiceSpringImpl extends GenericDaoHibernate<YywxUserInfo, String> implements WxMessageService {
	
	private CommonService commonService;
	
	private CodeService codeService;
	private Logger logger = Logger.getLogger(WxMessageServiceSpringImpl.class);
	/**
	 * 微信关注及取消关注事件
	 * @param wxInputVo 微信入参
	 * @throws Exception 
	 */
	public void subOrUnSubScribe(WxInputVo wxInputVo) throws Exception {
		//先判断是关注还是取消关注，如果是关注则要调用模板消息欢迎关注者，如果是取消关注则更新数据库
		if(WxCodeConst.Event.Subscribe.equals(wxInputVo.getEvent())){
			//如果是关注事件发送欢迎消息给客户并保存数据库
			QueryRule queryRule1 = QueryRule.getInstance();
			queryRule1.addEqual("isShowSubscribe", "1");
			queryRule1.addAscOrder("serialNo");
			List<YyPgIndex> yyPgIndexs = this.find(YyPgIndex.class,queryRule1);
			String jsonStr = "";
			if(yyPgIndexs!=null && yyPgIndexs.size()>0){
				List<WxSendPhotoMessageListDetailVo> WxSendPhotoMessageListDetailVos = new ArrayList<WxSendPhotoMessageListDetailVo>();
				for (YyPgIndex yyPgIndex : yyPgIndexs) {
					WxSendPhotoMessageListDetailVo wxSendPhotoMessageListDetailVo = new WxSendPhotoMessageListDetailVo();
					wxSendPhotoMessageListDetailVo.setTitle(yyPgIndex.getContext());
					wxSendPhotoMessageListDetailVo.setDescription(yyPgIndex.getDescription());
					wxSendPhotoMessageListDetailVo.setUrl(yyPgIndex.getContextUrl());
					wxSendPhotoMessageListDetailVo.setPicurl(yyPgIndex.getIndexImage());
					WxSendPhotoMessageListDetailVos.add(wxSendPhotoMessageListDetailVo);
				}
				
				WxSendPhotoMessageListVo wxSendPhotoMessageListVo = new WxSendPhotoMessageListVo();
				wxSendPhotoMessageListVo.setArticles(WxSendPhotoMessageListDetailVos);
				
				WxSendPhotoMessageVo wxSendPhotoMessageVo = new WxSendPhotoMessageVo();
				wxSendPhotoMessageVo.setTouser(wxInputVo.getFromUserName());
				wxSendPhotoMessageVo.setMsgtype(WxCodeConst.MsgType.news);
				wxSendPhotoMessageVo.setNews(wxSendPhotoMessageListVo);
				//转换json
				jsonStr = JsonUtil.toJson(wxSendPhotoMessageVo); 
			}else{
				WxSendMessageDetailtVo wxTextContextVo = new WxSendMessageDetailtVo();
				//从数据库中获取配置好的微信关注欢迎消息
				Yydconfig yydconfigSecret = commonService.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.SubScribeSendText);
				wxTextContextVo.setContent(yydconfigSecret.getConfigValue());
				WxSendMessageVo wxSendMessageVo = new WxSendMessageVo();
				wxSendMessageVo.setTouser(wxInputVo.getFromUserName());
				wxSendMessageVo.setMsgtype(WxCodeConst.MsgType.text);
				wxSendMessageVo.setText(wxTextContextVo);
				//转换json
				jsonStr = JsonUtil.toJson(wxSendMessageVo); 
			}
			
			//获取微信消息发送接口地址
			String sendUrl = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WxMessageSendUrl,CodeConst.ChannelType.wx);
			
			try {
				ServletUtil.post(sendUrl, jsonStr, "utf-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//先查一下该用户是否曾经关注过，如果曾经关注过，则只更新状态即可
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("openid", wxInputVo.getFromUserName());
			YywxUserInfo yyWxUserInfo = this.findUnique(queryRule);
			if(yyWxUserInfo != null){
				yyWxUserInfo.setSubscribe_time(df.parse(wxInputVo.getCreateTime()));
				yyWxUserInfo.setValid("1");
			}else{
				//保存关注者信息
				yyWxUserInfo = new YywxUserInfo();
				yyWxUserInfo.setOpenid(wxInputVo.getFromUserName());
				yyWxUserInfo.setGroupid(wxInputVo.getToUserName());
				yyWxUserInfo.setSubscribe_time(df.parse(wxInputVo.getCreateTime()));
				yyWxUserInfo.setValid("1");
				super.save(yyWxUserInfo);
			}
		}else if(WxCodeConst.Event.Unsubscribe.equals(wxInputVo.getEvent())){
			//先查一下该用户是否曾经关注过，如果曾经关注过，则只更新状态即可
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("openid", wxInputVo.getFromUserName());
			YywxUserInfo yyWxUserInfo = this.findUnique(queryRule);
			if(yyWxUserInfo != null){
				yyWxUserInfo.setUnSubscribe_time(new Date());
				yyWxUserInfo.setValid("0");
			}
		}else if(WxCodeConst.Event.Click.equals(wxInputVo.getEvent())){
			if(WxCodeConst.EventKey.CallUs.equals(wxInputVo.getEventKey())){
				//如果是普通消息则调用普通消息service
				String sendMessage = "客服📞：13259850076,13669291037\n门店地址：西安市碑林区仁厚庄南路星币传说小区3号楼1单元";
				this.sendTextToUserId(wxInputVo.getFromUserName(),sendMessage);
			}
		}
	}
	
	/**
	 * 微信发送普通消息
	 * @param wxInputVo 微信入参
	 */
	public void sendTextToUserId(String toUserOpenId,String content) throws Exception{
		//如果是发送消息则按如下回复
		WxSendMessageDetailtVo wxTextContextVo = new WxSendMessageDetailtVo();
//		wxTextContextVo.setContent("友友正在努力智能化，目前无法理解您的意思，请谅解:(");
		wxTextContextVo.setContent(content);
		
		WxSendMessageVo wxSendMessageVo = new WxSendMessageVo();
		wxSendMessageVo.setTouser(toUserOpenId);
		wxSendMessageVo.setMsgtype(WxCodeConst.MsgType.text);
		wxSendMessageVo.setText(wxTextContextVo);
		
		//转换json
		String jsonStr = JsonUtil.toJson(wxSendMessageVo);  
		//获取微信消息发送接口地址
		String sendUrl = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WxMessageSendUrl,CodeConst.ChannelType.wx);
		try {
			ServletUtil.post(sendUrl, jsonStr, "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送微信模板消息（模板ID要和入参层级对应）
	 * @param templateId 内部模板Id 对于yydcode表CodeConst.CodeType
	 * @param wxSendTemplateVo 发送模板Vo
	 * first-DATA
	   keynote1-value
	   ......
	   keynote3-value
	   remark-value
	 */
	public void sendTemplateMessage(String templateId,WxSendTemplateVo wxSendTemplateVo) throws Exception{
		//首先根据内部模板id获取微信模板id
		String wxTemplateId = codeService.translateWxCode(CodeConst.CodeType.WxTemplateId, templateId);
		if(!"".equals(wxTemplateId)){
			//获取模板ID后根据入参调用接口发送
			wxSendTemplateVo.setTemplate_id(wxTemplateId);
		}else{
			wxSendTemplateVo.setTemplate_id(templateId);
		}
		//转换json
		String jsonStr = JsonUtil.toJson(wxSendTemplateVo);  
		//获取微信消息发送接口地址
		String sendUrl = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.WxTemplateMessageSendUrl,CodeConst.ChannelType.wx);
		//发送
		ServletUtil.post(sendUrl, jsonStr, "utf-8");
		
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
}
