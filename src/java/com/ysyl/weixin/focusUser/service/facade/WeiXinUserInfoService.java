package com.ysyl.weixin.focusUser.service.facade;

import com.ysyl.weixin.schema.model.YywxUserInfo;

public interface WeiXinUserInfoService {
	public YywxUserInfo pasUserInfo(String json);
	public YywxUserInfo saveUserInfo(YywxUserInfo userInfo);
	public YywxUserInfo saveUserInfoByJson(String json);
}
