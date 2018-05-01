package com.ysyl.backstage.login.web;
import ins.framework.web.Struts2Action;

import java.net.URLDecoder;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ysyl.backstage.login.service.facade.LoginService;
import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.weixin.pay.utils.MD5Util;

public class LoginAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	private String username;
	private String userpwd;
	private String sex;
	private String age;
	private String phone;
	private String email;
	private String wechat;
	private String gradeid;
	private String addr;
	private String validStatus;
	private String realname;
	private String usercode;
	private LoginService loginService;
	private CommonService commonService;
	/**
	 * 验证登录判断
	 * @return
	 * @throws Exception 
	 */
	public String checkLogin() throws Exception{
		YyUser userDto  = loginService.findUserByNameAndPswrd(username,userpwd);
		Gson g = new Gson();
		String json = "";
		if(userDto!=null&&!"".equals(userDto)){
			commonService.setSession(this.getRequest().getSession(),"",userDto.getUserCode(),userDto.getUserName());
			json = g.toJson("1");
		}else{
			json = g.toJson("0");
		}
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	/**
	 * 验证用户权限
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	private YyUser checkPower() throws Exception{
		String usercode = (String) getRequest().getSession().getAttribute("UserCode");
		String username = (String) getRequest().getSession().getAttribute("UserName");
		if(usercode ==null||"".equals(usercode)||username==null||"".equals(username)){
			throw new Exception("用户未登录，请登录");
		}
		YyUser userDto  = loginService.findUserByNameAndCode(username,usercode);
		return userDto;
	}
	/**
	 * 初始化首页
	 * @return
	 * @throws Exception 
	 */
	public String initIndex() throws Exception{
		YyUser userDto  = checkPower();
		Gson g = new Gson();
		String json = "";
		json = g.toJson(userDto);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 初始化首页
	 * @return
	 * @throws Exception 
	 */
	public String quitUser() throws Exception{
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		Gson g = new Gson();
		String json = "";
		json = g.toJson(0);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 初始化用户信息
	 * @return
	 * @throws Exception 
	 */
	public String initUser() throws Exception{
		YyUser userDto  = checkPower();
		Gson g = new Gson();
		String json = "";
		json = g.toJson(userDto);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws Exception 
	 */
	public String updateUser() throws Exception{
		YyUser userDto  = checkPower();
		saveOrUpdateYyuser(userDto);
		Gson g = new Gson();
		String json = "";
		json = g.toJson("1");
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 更新用户信息
	 * @return
	 * @throws Exception 
	 */
	public String exitSys() throws Exception{
		getRequest().getSession().removeAttribute("UserCode");
		getRequest().getSession().removeAttribute("UserName");
		Gson g = new Gson();
		String json = "";
		json = g.toJson("1");
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 修改更新密码
	 * @return
	 * @throws Exception 
	 */
	public String changePas() throws Exception{
		YyUser userDto  = checkPower();
		if(userpwd==null||"".equals(userpwd)){
			throw new Exception ("更新的密码为空");
		}
		saveOrUpdateYyuser(userDto);
		Gson g = new Gson();
		String json = "";
		json = g.toJson("1");
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
	/**
	 * 添加用户
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	public YyUser addUser() throws Exception{
		checkPower();
		YyUser newUserDto  =  new YyUser();
		
		String userNum = commonService.getNoOrder(CodeConst.TableName.yyuser, CodeConst.NoCode.userCode);
		newUserDto.setUserCode(userNum);
		newUserDto.setSource("bs");
		newUserDto.setOwner(username);
		newUserDto.setInType("bs");
		newUserDto.setInKey(userNum);
		newUserDto.setCheckNo("");
		newUserDto.setLastLoginTime(new Date());
		newUserDto.setLoginIp("0.0.0.0");
		newUserDto.setCreateTime(new Date());
		
		saveOrUpdateYyuser(newUserDto);
		Gson g = new Gson();
		String json = "";
		json = g.toJson("1");
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	/**
	 * 保存或更新YYuser
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	private void saveOrUpdateYyuser(YyUser userDto) throws Exception {
		if(usercode!=null&&!"".equals(usercode)){
			userDto.setUserCode(usercode);
		}
		if(username!=null&&!"".equals(username)){
			String um = URLDecoder.decode(username,"UTF-8");
			userDto.setUserName(um);
		}
		if(userpwd!=null&&!"".equals(userpwd)){
			userDto.setPswrd(MD5Util.MD5Encode(userpwd,"UTF-8").toUpperCase());
		}
		if(age!=null&&!"".equals(age)){
			userDto.setAge(age);
		}
		if(sex!=null&&!"".equals(sex)){
			userDto.setSex(sex);
		}
		if(email!=null&&!"".equals(email)){
			userDto.setEmail(email);
		}
		if(phone!=null&&!"".equals(phone)){
			userDto.setPhone(phone);
		}
		if(wechat!=null&&!"".equals(wechat)){
			userDto.setWechat(wechat);
		}
		if(addr!=null&&!"".equals(addr)){
			userDto.setAddr(addr);
		}
		if(realname!=null&&!"".equals(realname)){
			userDto.setRealname(realname);
		}
		if(gradeid!=null&&!"".equals(gradeid)){
			userDto.setGradeid(gradeid);
		}
		if(validStatus!=null&&!"".equals(validStatus)){
			userDto.setValidStatus(validStatus);
		}
		loginService.updateUserInfo(userDto);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
	
	
}