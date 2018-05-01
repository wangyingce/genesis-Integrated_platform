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
 * 摄影工作室预约订单表
 * @author ysyl
 */
@Entity
@Table(name = "yyimagefile")
public class YyImageFile implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private Long id;

	/**
	 * 商户Id，对应yywaresowner表id
	 */
	private String owner;
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 文件索引
	 */
	private String fileIndex;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件路径
	 */
	private String filepath;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date operateTime;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	public YyImageFile() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yyimagefile")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "fileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**       
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operatetime", insertable = false)
	public Date getOperateTime() {
	    return operateTime;
	}

	public void setOperateTime(Date operateTime) {
	    this.operateTime = operateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "validStatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	@Column(name = "fileIndex")
	public String getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
