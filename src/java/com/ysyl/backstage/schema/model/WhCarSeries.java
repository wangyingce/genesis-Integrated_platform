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
@Table(name = "whCarSeries")
public class WhCarSeries implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long carserId;
	private String carserName;
	private String carserMinPrice;
	private String carserMaxPrice;
	private Date inputtime;
	private String createrName;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "whCarSeries")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "carserId")
	public Long getCarserId() {
		return carserId;
	}
	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}
	public String getCarserName() {
		return carserName;
	}
	public void setCarserName(String carserName) {
		this.carserName = carserName;
	}
	public String getCarserMinPrice() {
		return carserMinPrice;
	}
	public void setCarserMinPrice(String carserMinPrice) {
		this.carserMinPrice = carserMinPrice;
	}
	public String getCarserMaxPrice() {
		return carserMaxPrice;
	}
	public void setCarserMaxPrice(String carserMaxPrice) {
		this.carserMaxPrice = carserMaxPrice;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
}