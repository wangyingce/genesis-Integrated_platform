package com.ysyl.common.schema.model;

import java.math.BigDecimal;
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
 * 摄影工作室预约明细说明表
 * @author ysyl
 */
@Entity
@Table(name = "yypgorderdetail")
public class YyPgOrderDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private Long id;

	/**
	 * city-城市
	 */
	private String city;
	
	/**
	 * city-城市名称
	 */
	private String cityName;

	/**
	 * photoType-拍照类型
	 */
	private String photoType;
	
	/**
	 * photoType-拍照类型名称
	 */
	private String photoName;
	
	/**
	 * 商户Id，对应yywaresowner表id
	 */
	private String owner;
	
	/**
	 * 原价
	 */
	private BigDecimal price;
	
	/**
	 * 折扣价
	 */
	private BigDecimal disPrice;
	
	/**
	 * 包含项目
	 */
	private String projects;
	
	/**
	 * 图片地址
	 */
	private String imageUrl;
	
	/**
	 * 服务时长
	 */
	private String serverTimes;
	
	/**
	 * 预约价格
	 */
	private BigDecimal orderPrice;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 效力状态 1|有效，0|无效
	 */
	private String validStatus;
	
	public YyPgOrderDetail() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yypgorderdetail")
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


	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "phototype")
	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	@Column(name = "disprice")
	public BigDecimal getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(BigDecimal disPrice) {
		this.disPrice = disPrice;
	}

	@Column(name = "orderprice")
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Column(name = "price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "projects")
	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	@Column(name = "servertimes")
	public String getServerTimes() {
		return serverTimes;
	}

	public void setServerTimes(String serverTimes) {
		this.serverTimes = serverTimes;
	}

	@Column(name = "cityname")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "photoname")
	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Column(name = "imageurl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
