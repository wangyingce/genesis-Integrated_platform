﻿package com.ysyl.common.schema.model;

// Generated by Hibernate Tools 3.2.0.b9 (sinosoft version)

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 单号使用占号表
 */
@Entity
@Table(name = "maxuse")
public class MaxUse implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 表名 */
	private String tableName;

	/** 单号代码 */
	private String fieldCode;

	/** 使用编号 */
	private String userNo;

	/** 效力标志 */
	private Date insertTime;
	
	public MaxUse() {
	}

	/**       
	 * 表名
	 */
	@Id
	@Column(name = "tablename")
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserttime")
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "fieldcode")
	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	@Column(name = "userno")
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
}