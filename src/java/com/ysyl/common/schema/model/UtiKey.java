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
 * 单号生成配置表
 * UtiKey generated by Tools.Don't edit.
 */
@Entity
@Table(name = "utikey")
public class UtiKey implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 表名 */
	private String tableName;

	/** 单号代码 */
	private String fieldCode;

	/** 单号名称 */
	private String fieldName;

	/** 单号长度 */
	private Integer colLength;

	/** 单号首字符 */
	private String headId;
	
	/** 单号类型：1:纯数字 2:数字+字母*/
	private String type;

	/** 效力标志 */
	private String validStatus;
	
	/** 创建时间*/
	private Date createTime;
	
	public UtiKey() {
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

	/**       
	 * 字段名
	 */

	@Column(name = "fieldname")
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**       
	 * 单号长度
	 */

	@Column(name = "collength")
	public Integer getColLength() {
		return this.colLength;
	}

	public void setColLength(Integer colLength) {
		this.colLength = colLength;
	}

	/**       
	 * 单号首字符
	 */

	@Column(name = "headid")
	public String getHeadId() {
		return this.headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "fieldcode")
	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "validStatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

}
