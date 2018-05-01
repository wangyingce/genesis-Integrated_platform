package com.ysyl.backstage.brand.vo;


/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class BrandVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long brandId;
	private String brandName;
	private String brandSeriaNo;
	private String brandArea;
	private String brandPicture;
	private String brandPictureId;
	private String brandRemark;
	private String brandStatus;
	private String Flag;
	private String validate;
	private String inputtime;
	private String nation;
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandSeriaNo() {
		return brandSeriaNo;
	}
	public void setBrandSeriaNo(String brandSeriaNo) {
		this.brandSeriaNo = brandSeriaNo;
	}
	public String getBrandArea() {
		return brandArea;
	}
	public void setBrandArea(String brandArea) {
		this.brandArea = brandArea;
	}
	public String getBrandPicture() {
		return brandPicture;
	}
	public void setBrandPicture(String brandPicture) {
		this.brandPicture = brandPicture;
	}
	public String getBrandRemark() {
		return brandRemark;
	}
	public void setBrandRemark(String brandRemark) {
		this.brandRemark = brandRemark;
	}
	public String getBrandStatus() {
		return brandStatus;
	}
	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBrandPictureId() {
		return brandPictureId;
	}
	public void setBrandPictureId(String brandPictureId) {
		this.brandPictureId = brandPictureId;
	}
	
}