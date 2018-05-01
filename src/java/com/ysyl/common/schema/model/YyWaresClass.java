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
 * 商品大类实体类
 * @author ysyl
 */
@Entity
@Table(name = "yywaresclass")
public class YyWaresClass implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private Long id;

	/**
	 * 商品大类名称
	 */
	private String className;

	/**
	 * 商城商户Id
	 */
	private String storeId;

	/**
	 * 图标地址
	 */
	private String imageUrl;

	/**
	 * 创建人代码
	 */
	private String createCode;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	public YyWaresClass() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "YyWaresClass")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "classname")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "imageurl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "storeid")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
