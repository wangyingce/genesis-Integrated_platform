package com.ysyl.weixin.focusUser.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import com.google.gson.Gson;
import com.ysyl.weixin.focusUser.service.facade.WeiXinUserInfoService;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class WeiXinUserInfoServiceSpringImpl extends GenericDaoHibernate<YywxUserInfo, String> implements WeiXinUserInfoService {

	public YywxUserInfo saveUserInfoByJson(String json){
		YywxUserInfo userInfo = this.pasUserInfo(json);
		userInfo.setValid("1");
		userInfo = this.saveUserInfo(userInfo);
		return userInfo;
	}
	
	public YywxUserInfo pasUserInfo(String json){
		Gson g = new Gson();
		YywxUserInfo userInfo = g.fromJson(json, YywxUserInfo.class);
		return userInfo;
	}

	public YywxUserInfo saveUserInfo(YywxUserInfo userInfo) {
		super.save(userInfo);
		return userInfo;
	}

}
