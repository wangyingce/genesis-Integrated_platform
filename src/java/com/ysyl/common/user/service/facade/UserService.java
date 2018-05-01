package com.ysyl.common.user.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyUser;



public interface UserService{
	
	/**
	 * 新增一个用户信息
	 * @param yyUser 用户对象
	 */
	public YyUser addUser(YyUser yyUser) throws Exception;
	
	/**
	 * 更改用户信息
	 * @param yyUser 用户对象
	 */
	public YyUser updateUser(YyUser yyUser) throws Exception;
	
	/**
	 * 查询用户信息
	 * @param inType 渠道
	 * @param inKye 渠道ID
	 * @param owner 商户id
	 */
	public YyUser findUserByInTypeAndInKeyAnyOwner(String inType ,String inKye,String owner) throws Exception;
	
	/**
	 * 查询用户信息
	 * @param userCode 用户id
	 */
	public YyUser findUserByUserCode(String userCode) throws Exception;
	
	/**
	 * 查询用户信息
	 * @param userCode 用户id
	 */
	public List<YyUser> findUserByPhone(String phone) throws Exception;
}
