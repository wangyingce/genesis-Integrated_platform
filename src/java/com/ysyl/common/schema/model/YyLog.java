package com.ysyl.common.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 日志记录表
 * @author ysyl
 */
@Entity
@Table(name = "yylog")
public class YyLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID，序列自增长
	 */
	private Long id;

	/**
	 * 输入id,可以是商户id,也可以是用户id,根据类型不同传入
	 */
	private String inputId;

	/**
	 * 业务号
	 */
	private String certiNo;
	
	/**
	 * 业务类型名称
	 */
	private String certiName;

	/**
	 * 发送报文
	 */
	private String sendXml;

	/**
	 * 返回报文
	 */
	private String reslutXml;
	
	/**
	 * 发送地址
	 */
	private String url;
	
	/**
	 * 失败原因
	 */
	private String errorReason;
	
	/**
	 * 发送地址
	 */
	private Date createTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	/**
	 * 备注
	 */
	private String remark;
	
	public YyLog() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YyLog")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "certino")
	public String getCertiNo() {
		return certiNo;
	}

	public void setCertiNo(String certiNo) {
		this.certiNo = certiNo;
	}

	@Column(name = "errorreason")
	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	@Column(name = "inputid")
	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "reslutxml")
	public String getReslutXml() {
		return reslutXml;
	}

	public void setReslutXml(String reslutXml) {
		this.reslutXml = reslutXml;
	}

	@Column(name = "sendxml")
	public String getSendXml() {
		return sendXml;
	}

	public void setSendXml(String sendXml) {
		this.sendXml = sendXml;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "certiname")
	public String getCertiName() {
		return certiName;
	}

	public void setCertiName(String certiName) {
		this.certiName = certiName;
	}

}
