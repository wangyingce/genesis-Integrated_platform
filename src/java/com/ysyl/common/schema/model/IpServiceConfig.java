package com.ysyl.common.schema.model;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ipserviceconfig")
public class IpServiceConfig implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private IpServiceConfigId id;

	private String serverName;

	private String proteclType;

	private String serverIP;

	private String serverPort;

	private String serverAppName;

	private String appUserCode;

	private String appPassword;

	private String createCode;
	
	private Date createTime;

	private String validStatus;
	
	private String charSet;
	
	@Column(name = "charset")
	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public IpServiceConfig() {
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "serverCode", column = @Column(name = "servercode")),
			@AttributeOverride(name = "environmentCode", column = @Column(name = "environmentcode")) })
	public IpServiceConfigId getId() {
		return this.id;
	}

	public void setId(IpServiceConfigId id) {
		this.id = id;
	}


	@Column(name = "servername")
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	@Column(name = "protecltype")
	public String getProteclType() {
		return this.proteclType;
	}

	public void setProteclType(String proteclType) {
		this.proteclType = proteclType;
	}

	@Column(name = "serverip")
	public String getServerIP() {
		return this.serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}


	@Column(name = "serverport")
	public String getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}


	@Column(name = "serverappname")
	public String getServerAppName() {
		return this.serverAppName;
	}

	public void setServerAppName(String serverAppName) {
		this.serverAppName = serverAppName;
	}



	@Column(name = "appusercode")
	public String getAppUserCode() {
		return this.appUserCode;
	}

	public void setAppUserCode(String appUserCode) {
		this.appUserCode = appUserCode;
	}


	@Column(name = "apppassword")
	public String getAppPassword() {
		return this.appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}


	@Column(name = "createcode")
	public String getCreateCode() {
		return this.createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "validstatus")
	public String getValidStatus() {
		return this.validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
}
