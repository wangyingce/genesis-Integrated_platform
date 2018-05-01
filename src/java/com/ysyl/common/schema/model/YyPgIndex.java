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
 * 摄影工作室首页列表
 * @author ysyl
 */
@Entity
@Table(name = "yypgindex")
public class YyPgIndex implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 商品大类Id，序列自增长
	 */
	private Integer id;
	
	/**
	 * 商户Id，对应yywaresowner表id
	 */
	private String owner;

	/**
	 * 排列顺序 按升序排列 1，2，3，4
	 */
	private Integer serialNo;
	
	/**
	 * 图标地址
	 */
	private String logoUrl;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 内容
	 */
	private String context;
	/**
	 * 内容连接
	 */
	private String contextUrl;
	
	/**
	 * 是否显示在首页关注推送上，为1才显示
	 */
	private String isShowSubscribe;
	/**
	 * 首页关注显示的图片地址，这个地址需要全系统地址
	 */
	private String indexImage;
	
	/**
	 * 副标题
	 */
	private String description;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date operateTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;

	
	public YyPgIndex() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yyPgIndex")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "contexturl")
	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

	@Column(name = "logourl")
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "serialno")
	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**       
	 * 修改时间
	 */
    @Column(name = "operatetime", insertable = false)
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "context")
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "indeximage")
	public String getIndexImage() {
		return indexImage;
	}

	public void setIndexImage(String indexImage) {
		this.indexImage = indexImage;
	}

	@Column(name = "isshowsubscribe")
	public String getIsShowSubscribe() {
		return isShowSubscribe;
	}

	public void setIsShowSubscribe(String isShowSubscribe) {
		this.isShowSubscribe = isShowSubscribe;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
