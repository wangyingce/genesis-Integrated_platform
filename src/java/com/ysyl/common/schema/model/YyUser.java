package com.ysyl.common.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息表
 * @author ysyl
 */
@Entity
@Table(name = "yyuser")
public class YyUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户代码
	 */
	private String userCode;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 邮箱地址
	 */
	private String email;
	
	/**
	 * 注册来源 pg-摄影工作室
	 */
	private String source;
	
	/**
	 * 注册类型 wx-微信，alipay 阿里 none-其它，默认为none
	 */
	private String inType;
	
	/**
	 * 注册key 如果inType是wx 则存储用户的openid
	 */
	private String inKey;
	
	/**
	 * 所属商家id，多个商家都注册的话以,号拼接起来,对应yywaresowner表id
	 */
	private String owner;
	
	/**
	 * 登录验证码	
	 */
	private String checkNo;
	
	/**
	 * 用户logoUrl图片地址（必须上传服务器，不允许使用外网地址）
	 */
	private String logoUrl;
	
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	

	/**
	 * 用户最后一次登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 最后一次登录ip
	 */
	private String loginIp;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	/**
	 * 密码，MD5加密
	 */
	private String pswrd;
	/**
	 * 性别，1-男，2-女
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 微信号
	 */
	private String wechat;
	/**
	 * 岗位id
	 */
	private String gradeid;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 地址
	 */
	private String addr;
	
	public YyUser() {
	}

	/**       
	 * Id
	 */
	@Id
	@Column(name = "usercode")
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Column(name = "checkno")
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Column(name = "createtime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "lastlogintime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "loginip")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "logourl")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "validstatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name = "inkey")
	public String getInKey() {
		return inKey;
	}

	public void setInKey(String inKey) {
		this.inKey = inKey;
	}

	@Column(name = "intype")
	public String getInType() {
		return inType;
	}

	public void setInType(String inType) {
		this.inType = inType;
	}
	@Column(name = "pswrd")
	public String getPswrd() {
		return pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "age")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	@Column(name = "wechat")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	@Column(name = "gradeid")
	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	@Column(name = "realname")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	@Column(name = "addr")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
