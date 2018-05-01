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
@Table(name = "yybsbrand")
public class YybsBrand implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long brandId;
	private String brandName;
	private String brandSeriaNo;
	private String brandArea;
	private String brandPicture;
	private String brandRemark;
	private String brandStatus;
	private String Flag;
	private String validate;
	private Date inputtime;
	private String nation;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yybsbrand")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "brandId")
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	@Column(name = "brandName")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	@Column(name = "brandSeriaNo")
	public String getBrandSeriaNo() {
		return brandSeriaNo;
	}
	public void setBrandSeriaNo(String brandSeriaNo) {
		this.brandSeriaNo = brandSeriaNo;
	}
	@Column(name = "brandArea")
	public String getBrandArea() {
		return brandArea;
	}
	public void setBrandArea(String brandArea) {
		this.brandArea = brandArea;
	}
	@Column(name = "brandPicture")
	public String getBrandPicture() {
		return brandPicture;
	}
	public void setBrandPicture(String brandPicture) {
		this.brandPicture = brandPicture;
	}
	@Column(name = "brandRemark")
	public String getBrandRemark() {
		return brandRemark;
	}
	public void setBrandRemark(String brandRemark) {
		this.brandRemark = brandRemark;
	}
	@Column(name = "brandStatus")
	public String getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}
	@Column(name = "Flag")
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
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
	@Column(name = "nation")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
}