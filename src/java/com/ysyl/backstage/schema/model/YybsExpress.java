package com.ysyl.backstage.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
@Entity
@Table(name = "YybsExpress")
public class YybsExpress implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long expressId;
	private Long expressNo;
	private String expressName;
	private String expressInfo;
	private Date inputtime;
	private String flag;
	private String validate;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YybsExpress")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "expressId")
	public Long getExpressId() {
		return expressId;
	}
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}
	
	@Column(name = "expressNo")
	public Long getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(Long expressNo) {
		this.expressNo = expressNo;
	}
	
	@Column(name = "expressName")
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	@Column(name = "expressInfo")
	public String getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(String expressInfo) {
		this.expressInfo = expressInfo;
	}
	
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "validate")
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	@Column(name = "inputtime")
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	
	
}
