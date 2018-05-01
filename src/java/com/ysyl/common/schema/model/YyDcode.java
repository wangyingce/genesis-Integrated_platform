﻿package com.ysyl.common.schema.model;

// Generated by Hibernate Tools 3.2.0.b9 (sinosoft version)

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PrpDcode generated by Tools.Don't edit.
 */
@Entity
@Table(name = "yydcode")
public class YyDcode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 业务代码 */
	private YyDcodeId id;

	/** 微信业务代码 */
	private String wxCodeCode;
	
	/** 代码类型中文含义 */
	private String codeCName;

	/** 效力状态(0失效/1有效) */
	private String validStatus;

	

	public YyDcode() {
	}

	/**       
	 * 业务代码
	 */
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "codeType", column = @Column(name = "codetype")),
			@AttributeOverride(name = "codeCode", column = @Column(name = "codecode")) })
	public YyDcodeId getId() {
		return this.id;
	}

	public void setId(YyDcodeId id) {
		this.id = id;
	}

	/**       
	 * 业务代码中文含义
	 */

	@Column(name = "codecname")
	public String getCodeCName() {
		return this.codeCName;
	}

	public void setCodeCName(String codeCName) {
		this.codeCName = codeCName;
	}

	/**       
	 * 效力状态(0失效/1有效)
	 */

	@Column(name = "validstatus")
	public String getValidStatus() {
		return this.validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name = "wxcodecode")
	public String getWxCodeCode() {
		return wxCodeCode;
	}

	public void setWxCodeCode(String wxCodeCode) {
		this.wxCodeCode = wxCodeCode;
	}
}