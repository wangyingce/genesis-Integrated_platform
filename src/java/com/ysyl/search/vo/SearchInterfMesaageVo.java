package com.ysyl.search.vo;

public class SearchInterfMesaageVo extends SearchVo{
	private String type;
	
	private Long inputDate;
	
	private String requestText;
	
	private String reponseText;

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

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public String getReponseText() {
		return reponseText;
	}

	public void setReponseText(String reponseText) {
		this.reponseText = reponseText;
	}

	
}
