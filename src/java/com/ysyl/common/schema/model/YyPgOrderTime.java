package com.ysyl.common.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 摄影工作室预约时段表
 * @author ysyl
 */
@Entity
@Table(name = "yypgordertime")
public class YyPgOrderTime implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品大类Id，序列自增长
	 */
	private Long id;
	
	/**
	 * 预约日期表id
	 */
	private YyPgOrderDate yyPgOrderDate;

	/**
	 * city-城市
	 */
	private String city;

	/**
	 * photoType-拍照类型
	 */
	private String photoType;
	
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
	 * 商户Id，对应yywaresowner表id
	 */
	private String owner;

	/**
	 * 总计可预约数
	 */
	private Long totalTimes;
	
	/**
	 * 已预约数
	 */
	private Long inTimes;
	
	/**
	 * 剩余可预约数
	 */
	private Long freeTimes;
	
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
	
	public YyPgOrderTime() {
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

	@Column(name = "freetimes")
	public Long getFreeTimes() {
		return freeTimes;
	}

	public void setFreeTimes(Long freeTimes) {
		this.freeTimes = freeTimes;
	}

	@Column(name = "intimes")
	public Long getInTimes() {
		return inTimes;
	}

	public void setInTimes(Long inTimes) {
		this.inTimes = inTimes;
	}

	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "totaltimes")
	public Long getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(Long totalTimes) {
		this.totalTimes = totalTimes;
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

	@Column(name = "pgbegintime")
	public Integer getPgBeginTime() {
		return pgBeginTime;
	}

	public void setPgBeginTime(Integer pgBeginTime) {
		this.pgBeginTime = pgBeginTime;
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

	/**
	 * yypgorderdate表id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderdateid", nullable = false)
	public YyPgOrderDate getYyPgOrderDate() {
		return yyPgOrderDate;
	}

	public void setYyPgOrderDate(YyPgOrderDate yyPgOrderDate) {
		this.yyPgOrderDate = yyPgOrderDate;
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
