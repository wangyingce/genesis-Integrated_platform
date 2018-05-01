package com.ysyl.backstage.product.vo;

/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class ProductVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long productId;
	private String productName;
	private String validstatus;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}

}