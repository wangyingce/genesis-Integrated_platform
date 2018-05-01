package com.ysyl.search.vo; 
public class QueryVO { 
	private String title; 
	
	public QueryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryVO(String title) {
		super();
		this.title = title;
	}

	public String getTitle() { 
	return title; 
	} 
	
	public void setTitle(String title) { 
	this.title = title; 
	} 
}