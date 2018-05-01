package com.ysyl.common.user.service.spring;

import java.util.Date;
import java.util.List;

import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.user.service.facade.UserService;

public class UserServiceSpringImpl extends GenericDaoHibernate<YyUser, String> implements UserService {
	
	/** 公共服务*/
	private CommonService commonService;
	/**
	 * 新增一个用户信息
	 * @param yyUser 用户对象
	 */
	public YyUser addUser(YyUser yyUser) throws Exception {
		// 先生成userCode
		String userCode = commonService.getNoOrder(CodeConst.TableName.yyuser, CodeConst.NoCode.userCode);
		yyUser.setUserCode(userCode);
		yyUser.setValidStatus("1");
		if("".equals(yyUser.getUserName()) || yyUser.getUserName() ==null){
			yyUser.setUserName(userCode);
		}
		if(yyUser.getInKey() == null || "".equals(yyUser.getInKey())){
			yyUser.setInType(CodeConst.ChannelType.none);
			yyUser.setInKey(userCode);
		}
			
		this.save(yyUser);
		return yyUser;
	}
	
	/**
	 * 更改用户信息
	 * @param yyUser 用户对象
	 */
	public YyUser updateUser(YyUser yyUser) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 查询用户信息
	 * @param inType 渠道
	 * @param inKye 渠道ID
	 * @param owner 商户id
	 */
	public YyUser findUserByInTypeAndInKeyAnyOwner(String inType ,String inKye,String owner) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("inType", inType);
		queryRule.addEqual("inKey", inKye);
		queryRule.addEqual("owner", owner);
		queryRule.addEqual("validStatus", "1");
		YyUser yyUser = this.findUnique(queryRule);
		if(yyUser != null){
			yyUser.setLastLoginTime(new Date());
		}
		return yyUser;
	}
	
	/**
	 * 查询用户信息
	 * @param userCode 用户id
	 */
	public YyUser findUserByUserCode(String userCode) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("userCode", userCode);
		queryRule.addEqual("validStatus", "1");
		YyUser yyUser = this.findUnique(queryRule);
		return yyUser;
	}
	
	/**
	 * 查询用户信息
	 * @param phone 电话号码
	 */
	public List<YyUser> findUserByPhone(String phone) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("phone", phone);
		queryRule.addEqual("validStatus", "1");
		 List<YyUser> yyUsers = this.find(queryRule);
		return yyUsers;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
}
