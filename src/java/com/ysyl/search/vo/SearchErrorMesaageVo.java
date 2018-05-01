package com.ysyl.search.vo;

public class SearchErrorMesaageVo extends SearchVo{
	private String type;
	
	private Long inputDate;
	
	private String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getInputDate() {
		return inputDate;
	}

	public void setInputDate(Long inputDate) {
		this.inputDate = inputDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
