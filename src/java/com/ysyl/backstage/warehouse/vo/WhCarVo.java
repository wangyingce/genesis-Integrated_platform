package com.ysyl.backstage.warehouse.vo;

public class WhCarVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long carId;
	private Long carserId;
	private String carserName;
	private String carName;
	private String carStandPrice;
	private String carImgPath;
	private String imgPath;
	private String inputtime;
	private String createrName;
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Long getCarserId() {
		return carserId;
	}
	public void setCarserId(Long carserId) {
		this.carserId = carserId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarStandPrice() {
		return carStandPrice;
	}
	public void setCarStandPrice(String carStandPrice) {
		this.carStandPrice = carStandPrice;
	}
	public String getCarImgPath() {
		return carImgPath;
	}
	public void setCarImgPath(String carImgPath) {
		this.carImgPath = carImgPath;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getCarserName() {
		return carserName;
	}
	public void setCarserName(String carserName) {
		this.carserName = carserName;
	}
}