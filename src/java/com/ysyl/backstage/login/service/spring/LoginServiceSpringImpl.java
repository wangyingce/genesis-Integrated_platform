package com.ysyl.backstage.login.service.spring;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.weixin.pay.utils.MD5Util;

public class LoginServiceSpringImpl extends GenericDaoHibernate<YyUser, String> implements LoginService {
	private String findUsrByNPSql = "select t from YyUser where t.userName='ObjectN' and t.pswrd = 'ObjectP'";
	/**
	 * 根据username与password获取用户信息
	 * @param username，表中字段
	 * @param userpwd，表中字段
	 * @throws Exception 
	 */
	public YyUser findUserByNameAndPswrd(String username, String userpwd) {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userName", username);
		queryRule.addEqual("pswrd", MD5Util.MD5Encode(userpwd,"UTF-8").toUpperCase());
		List<YyUser> usrList=super.find(YyUser.class, queryRule);
		if(usrList!=null&&usrList.size()>0){
			return usrList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据username与usercode获取用户信息
	 * @param username，表中字段
	 * @param usercode，表中字段
	 * @throws Exception 
	 */
	public YyUser findUserByNameAndCode(String username, String usercode) {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userName", username);
		queryRule.addEqual("userCode", usercode);
		List<YyUser> usrList=super.find(YyUser.class, queryRule);
		if(usrList!=null&&usrList.size()>0){
			return usrList.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 直接更新用户信息表
	 * @param userDto 用户信息表
	 * @throws Exception 
	 */
	public void updateUserInfo(YyUser userDto) {
		super.save(userDto);
	}
}