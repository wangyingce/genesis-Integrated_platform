package com.ysyl.backstage.schema.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 后台系统产品表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
@Entity
@Table(name = "yybsAdvert")
public class YybsAdvert implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long advertId;
	private String advertName;
	private String advertExName;
	private String advertNum;
	private String country;
	private String material;
	private String band;
	private String weight;
	private String unit;
	private String showPrice;
	private String marketPrice;
	private String keyword;
	private String remark;
	private String remarkex;
	private String comment;
	private String validstatus;
	private String imgpath;
	private Date inputtime;
	private Long genusId;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "yybsproduct")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
	@Column(name = "advertId")
	public Long getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}
	@Column(name = "advertName")
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	@Column(name = "advertExName")
	public String getAdvertExName() {
		return advertExName;
	}
	public void setAdvertExName(String advertExName) {
		this.advertExName = advertExName;
	}
	@Column(name = "advertNum")
	public String getAdvertNum() {
		return advertNum;
	}
	public void setAdvertNum(String advertNum) {
		this.advertNum = advertNum;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "material")
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	@Column(name = "band")
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "showPrice")
	public String getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}
	@Column(name = "marketPrice")
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	@Column(name = "keyword")
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "validstatus")
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	@Column(name = "imgpath")
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	@Column(name = "remarkex")
	public String getRemarkex() {
		return remarkex;
	}
	public void setRemarkex(String remarkex) {
		this.remarkex = remarkex;
	}
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name = "inputtime")
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	@Column(name = "genusId")
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
			     
	
}