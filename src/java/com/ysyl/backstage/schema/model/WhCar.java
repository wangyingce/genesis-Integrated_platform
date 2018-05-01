package com.ysyl.backstage.schema.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "whCar")
public class WhCar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long carId;
	private Long carserId;
	private String carName;
	private String carStandPrice;
	private String carImgPath;
	private Date inputtime;
	private String createrName;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "whCar")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "carId")
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	@Column(name = "carserId")
	public Long getCarserId() {
		return carserId;
	}
	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}
	@Column(name = "carName")
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	@Column(name = "carStandPrice")
	public String getCarStandPrice() {
		return carStandPrice;
	}
	public void setCarStandPrice(String carStandPrice) {
		this.carStandPrice = carStandPrice;
	}
	@Column(name = "carImgPath")
	public String getCarImgPath() {
		return carImgPath;
	}
	public void setCarImgPath(String carImgPath) {
		this.carImgPath = carImgPath;
	}
	@Column(name = "inputtime")
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	@Column(name = "createrName")
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
}