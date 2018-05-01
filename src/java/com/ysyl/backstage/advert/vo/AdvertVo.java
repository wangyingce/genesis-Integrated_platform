package com.ysyl.backstage.advert.vo;


/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class AdvertVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}