package com.ysyl.backstage.schema.model;

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
@Table(name = "yybsgenus")
public class YybsGenus implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long genusId;
	private String genusType;
	private Long uppergenusId;
	private String name;
	private String genusRemark;
	private String flag;
	private String validate;
	private Integer ocount;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yybsgenus")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "genusId")
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	@Column(name = "uppergenusId")
	public Long getUppergenusId() {
		return uppergenusId;
	}
	public void setUppergenusId(Long uppergenusId) {
		this.uppergenusId = uppergenusId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Column(name = "genusRemark")
	public String getGenusRemark() {
		return genusRemark;
	}
	public void setGenusRemark(String genusRemark) {
		this.genusRemark = genusRemark;
	}
	@Column(name = "genusType")
	public String getGenusType() {
		return genusType;
	}
	public void setGenusType(String genusType) {
		this.genusType = genusType;
	}
	
	@Column(name = "ocount")
	public Integer getOcount() {
		return ocount;
	}
	public void setOcount(Integer ocount) {
		this.ocount = ocount;
	}
}
