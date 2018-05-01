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
@Table(name = "yybsreport")
public class YybsReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long reportId;
	private String reportName;
	private String reportEleOne;
	private String reportEleTwo;
	private String reportEleThree;
	private String reportEleFour;
	private String reportEleFive;
	private String reportEleSix;
	private String Flag;
	private String validate;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yybsreport")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "reportId")
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	@Column(name = "reportName")
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@Column(name = "reportEleOne")
	public String getReportEleOne() {
		return reportEleOne;
	}
	public void setReportEleOne(String reportEleOne) {
		this.reportEleOne = reportEleOne;
	}
	@Column(name = "reportEleTwo")
	public String getReportEleTwo() {
		return reportEleTwo;
	}
	public void setReportEleTwo(String reportEleTwo) {
		this.reportEleTwo = reportEleTwo;
	}
	@Column(name = "reportEleThree")
	public String getReportEleThree() {
		return reportEleThree;
	}
	public void setReportEleThree(String reportEleThree) {
		this.reportEleThree = reportEleThree;
	}
	@Column(name = "flag")
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
	@Column(name = "reportEleFour")
	public String getReportEleFour() {
		return reportEleFour;
	}
	public void setReportEleFour(String reportEleFour) {
		this.reportEleFour = reportEleFour;
	}
	@Column(name = "reportEleFive")
	public String getReportEleFive() {
		return reportEleFive;
	}
	public void setReportEleFive(String reportEleFive) {
		this.reportEleFive = reportEleFive;
	}
	@Column(name = "reportEleSix")
	public String getReportEleSix() {
		return reportEleSix;
	}
	public void setReportEleSix(String reportEleSix) {
		this.reportEleSix = reportEleSix;
	}
}