package com.ysyl.search.vo;

public class Enddate {
	private String gte;
	
	private String lte;
	
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLte() {
		return lte;
	}

	public void setLte(String lte) {
		this.lte = lte;
	}

	public String getGte() {
		return gte;
	}

	public void setGte(String gte) {
		this.gte = gte;
	}
}
