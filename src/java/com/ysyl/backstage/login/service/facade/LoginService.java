package com.ysyl.backstage.login.service.facade;

import com.ysyl.common.schema.model.YyUser;




/**
 * 产品服务接口
 * @author wyc
 *
 */
public interface LoginService{
	/**
	 * 根据username与password获取用户信息
	 * @param username，表中字段
	 * @param userpwd，表中字段
	 * @throws Exception 
	 */
	YyUser findUserByNameAndPswrd(String username, String userpwd);
	/**
	 * 根据username与usercode获取用户信息
	 * @param username，表中字段
	 * @param usercode，表中字段
	 * @throws Exception 
	 */
	YyUser findUserByNameAndCode(String username, String usercode);
	/**
	 * 直接更新用户信息表
	 * @param userDto 用户信息表
	 * @throws Exception 
	 */
	void updateUserInfo(YyUser userDto);
	
}
