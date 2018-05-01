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
@Table(name = "WhAccessory")
public class WhAccessory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long accessoryId;
	private Long carserId;
	private String accessoryName;
	private String accessoryStandPrice;
	private Date inputtime;
	private String createrName;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "WhAccessory")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "accessoryId")
	public Long getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Long accessoryId) {
		this.accessoryId = accessoryId;
	}
	@Column(name = "carserId")
	public Long getCarserId() {
		return carserId;
	}
	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}
	@Column(name = "accessoryName")
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	@Column(name = "accessoryStandPrice")
	public String getAccessoryStandPrice() {
		return accessoryStandPrice;
	}
	public void setAccessoryStandPrice(String accessoryStandPrice) {
		this.accessoryStandPrice = accessoryStandPrice;
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