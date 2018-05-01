package com.ysyl.weixin.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "yywxuserinfo")
public class YywxUserInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户的唯一标识
	private String openid;
	// 用户所在的分组ID
	private String groupid;
	// 用户昵称
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String sex;
	// 用户个人资料填写的省份
	private String province;
	// 普通用户个人资料填写的城市
	private String city;
	// 国家，如中国为CN
	private String country;
	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String headimgurl;
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	private String unionid;
	// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private String remark;
	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String subscribe;
	// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private Date subscribe_time;
	// 用户取消关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private Date unSubscribe_time;
	// 用户的语言，简体中文为zh_CN
	private String language;
	// 备用标志
	private String flag;
	// 有效标志
	private String valid;

	@Id
	@Column(name = "OPENID")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "GROUPID")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Column(name = "NICKNAME")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "HEADIMGURL")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "UNIONID")
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "SUBSCRIBE")
	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBSCRIBE_TIME")
	public Date getSubscribe_time() {
		return subscribe_time;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UNSUBSCRIBE_TIME")
	public Date getUnSubscribe_time() {
		return unSubscribe_time;
	}
	
	public void setUnSubscribe_time(Date unSubscribe_time) {
		this.unSubscribe_time = unSubscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "VALID")
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}
