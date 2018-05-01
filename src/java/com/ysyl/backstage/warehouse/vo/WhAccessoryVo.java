package com.ysyl.backstage.warehouse.vo;

public class WhAccessoryVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long accessoryId;
	private Long carserId;
	private String carserName;
	private String accessoryName;
	private String accessoryStandPrice;
	private String inputtime;
	private String createrName;
	public Long getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Long accessoryId) {
		this.accessoryId = accessoryId;
	}
	public Long getCarserId() {
		return carserId;
	}
	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	public String getAccessoryStandPrice() {
		return accessoryStandPrice;
	}
	public void setAccessoryStandPrice(String accessoryStandPrice) {
		this.accessoryStandPrice = accessoryStandPrice;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getCarserName() {
		return carserName;
	}
	public void setCarserName(String carserName) {
		this.carserName = carserName;
	}
}