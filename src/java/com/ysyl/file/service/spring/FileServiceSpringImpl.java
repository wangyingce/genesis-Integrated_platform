package com.ysyl.file.service.spring;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.user.service.facade.UserService;
import com.ysyl.file.service.facade.FileService;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.schema.model.YywxMerchant;

public class FileServiceSpringImpl extends GenericDaoHibernate<YyImageFile, String> implements FileService {
	private UserService userService;
	 /** 微信消息服务*/
	private WxMessageService wxMessageService;
	
	/** 公共服务*/
	private CommonService commonService;
	/**
	 * 保存服务
	 */
	public YyImageFile saveFile(YyImageFile yyImageFile) throws Exception{
		this.save(yyImageFile);
		return yyImageFile;
	}
	
	/**
	 * 保存服务并且发送消息
	 */
	public void saveFileAndSendWxMessage(List<YyImageFile> yyImageFiles,Map<String,String> sendMessageMap) throws Exception{
		this.saveAll(yyImageFiles);
		//循环完成后根据上传照片的id去发送消息通知
		for (Map.Entry entry : sendMessageMap.entrySet()) {
			String key = entry.getKey().toString();
//			String value = (String)entry.getValue();
			//根据usercode查询微信openid
			List<YyUser> yyUsers = userService.findUserByPhone(key);
			//获取系统域名
			String sysUrl = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "");
			for (YyUser yyUser : yyUsers) {
				StringBuffer sendMessageBuffer = new StringBuffer(10);
				sendMessageBuffer.append("您的电子版照片已上传，赶快下载吧:");
				sendMessageBuffer.append("<a href='"+sysUrl+"/photograph/getMyPhoto.do?phoneNum="+key+"&storeId="+yyUser.getOwner()+"'>");
				sendMessageBuffer.append("【点击此处】下载</a>");
				//发送消息
				wxMessageService.sendTextToUserId(yyUser.getInKey(),sendMessageBuffer.toString());
			}
		}
	}

	/**
	 * 保存后台系统照片并返回存储信息
	 * @param yyImageFiles
	 * @return
	 */
	public String saveBackstageFiles(List<YyImageFile> yyImageFiles) {
		this.saveAll(yyImageFiles);
		String ids = "";
		for(YyImageFile files : yyImageFiles){
			if(ids!=null&&!"".equals(ids)){
				ids = ids+","+files.getId();
			}else{
				ids = files.getId().toString();
			}
		}
		return ids;
	}
	
	/**
	 * 主键查询上传日志表
	 * @param brandPicture
	 * @return
	 */
	public YyImageFile findImageFileById(String brandPicture) {
		QueryRule queryRule = QueryRule.getInstance();
		String id = brandPicture.replaceAll("\"",""); 
		queryRule.addEqual("id", new Long(id));
		List<YyImageFile> imgefiles=super.find(YyImageFile.class, queryRule);
		if(imgefiles!=null && imgefiles.size()>0){
			return imgefiles.get(0);
		}else{
			return null;
		}
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public WxMessageService getWxMessageService() {
		return wxMessageService;
	}

	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

}
