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
 * 摄影工作室预约订单表
 * @author ysyl
 */
@Entity
@Table(name = "yypgmyorder")
public class YyPgMyOrder implements java.io.Serializable {
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
	 * 商户名称
	 */
	private String ownerName;
	
	/**
	 * 商户注册地址
	 */
	private String ownerAddress;
	
	/**
	 * 商户注册电话
	 */
	private String ownerPhone;
	
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
	 * date-日期
	 */
	private String pgDate;
	
	/**
	 * 星期几
	 */
	private String weekName;
	
	/**
	 * time-开始时段
	 */
	private Integer pgBeginTime;
	
	/**
	 * time-结束时段
	 */
	private Integer pgEndTime;
	
	/**
	 * 预约人数
	 */
	private Integer persons;
	
	/**
	 * yypgchecktime表id
	 */
	private Integer checkTimeId;
	
	/**
	 * 支付状态-01|待支付，11|支付成功,99|支付失败
	 */
	private String payState;
	
	/**
	 * 支付状态名称
	 */
	private String payStateName;
	
	/**
	 * 预约人员代码
	 */
	private String userCode;
	
	/**
	 * 预约人员名称
	 */
	private String userName;
	
	/**
	 * 预约人员联系电话
	 */
	private String userPhone;
	
	/**
	 * 预约渠道，默认wx
	 */
	private String channelType;
	
	/**
	 * 是否已确认 1：是 0：否，默认为0
	 */
	private String isCheck;
	
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
	
	public YyPgMyOrder() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yypgmyorder")
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

	@Column(name = "ownername")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "pgbegintime")
	public Integer getPgBeginTime() {
		return pgBeginTime;
	}

	public void setPgBeginTime(Integer pgBeginTime) {
		this.pgBeginTime = pgBeginTime;
	}

	@Column(name = "pgdate")
	public String getPgDate() {
		return pgDate;
	}

	public void setPgDate(String pgDate) {
		this.pgDate = pgDate;
	}

	@Column(name = "pgendtime")
	public Integer getPgEndTime() {
		return pgEndTime;
	}

	public void setPgEndTime(Integer pgEndTime) {
		this.pgEndTime = pgEndTime;
	}

	@Column(name = "weekname")
	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	@Column(name = "channeltype")
	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	@Column(name = "persons")
	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	@Column(name = "usercode")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "checktimeid")
	public Integer getCheckTimeId() {
		return checkTimeId;
	}

	public void setCheckTimeId(Integer checkTimeId) {
		this.checkTimeId = checkTimeId;
	}

	@Column(name = "paystate")
	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	@Column(name = "owneraddress")
	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	@Column(name = "ownerphone")
	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Column(name = "paystatename")
	public String getPayStateName() {
		return payStateName;
	}

	public void setPayStateName(String payStateName) {
		this.payStateName = payStateName;
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

	@Column(name = "userphone")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "ischeck")
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

}
