package com.ysyl.common.schema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 摄影工作室预约时段表
 * @author ysyl
 */
@Entity
@Table(name = "yypgorderdate")
public class YyPgOrderDate implements java.io.Serializable {
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
	 * photoType-拍照类型
	 */
	private String photoType;
	
	/**
	 * city-城市名称
	 */
	private String cityName;

	/**
	 * photoType-拍照类型名称
	 */
	private String photoName;
	
	/**
	 * date-日期
	 */
	private String pgDate;
	
	/**
	 * 星期几
	 */
	private String weekName;
	
	/**
	 * 商户Id，对应yywaresowner表id
	 */
	private String owner;

	/**
	 * 总计可预约数
	 */
	private Long sumTotalTimes;
	
	/**
	 * 已预约数
	 */
	private Long sumInTimes;
	
	/**
	 * 剩余可预约数
	 */
	private Long sumFreeTimes;
	
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
	
	private List<YyPgOrderTime> yyPgOrderTimes = new ArrayList<YyPgOrderTime>(0);

	
	public YyPgOrderDate() {
	}

	/**       
	 * Id
	 */
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yypgordertime")
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

	@Column(name = "pgdate")
	public String getPgDate() {
		return pgDate;
	}

	public void setPgDate(String pgDate) {
		this.pgDate = pgDate;
	}

	@Column(name = "phototype")
	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
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

	@Column(name = "sumfreetimes")
	public Long getSumFreeTimes() {
		return sumFreeTimes;
	}

	public void setSumFreeTimes(Long sumFreeTimes) {
		this.sumFreeTimes = sumFreeTimes;
	}

	@Column(name = "sumintimes")
	public Long getSumInTimes() {
		return sumInTimes;
	}

	public void setSumInTimes(Long sumInTimes) {
		this.sumInTimes = sumInTimes;
	}

	@Column(name = "sumtotaltimes")
	public Long getSumTotalTimes() {
		return sumTotalTimes;
	}

	public void setSumTotalTimes(Long sumTotalTimes) {
		this.sumTotalTimes = sumTotalTimes;
	}

	@Column(name = "weekname")
	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yyPgOrderDate")
	public List<YyPgOrderTime> getYyPgOrderTimes() {
		return yyPgOrderTimes;
	}

	public void setYyPgOrderTimes(List<YyPgOrderTime> yyPgOrderTimes) {
		this.yyPgOrderTimes = yyPgOrderTimes;
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
}
