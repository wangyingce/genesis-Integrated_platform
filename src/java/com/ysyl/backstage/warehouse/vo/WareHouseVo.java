package com.ysyl.backstage.warehouse.vo;


public class WareHouseVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long carserId;
	private String carserName;
	private String carserMinPrice;
	private String carserMaxPrice;
	private String inputtime;
	private String createrName;
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
	
}