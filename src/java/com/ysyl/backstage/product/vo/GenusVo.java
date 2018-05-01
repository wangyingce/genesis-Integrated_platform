package com.ysyl.backstage.product.vo;
import java.util.List;

/**
 * 后台系统分类表
 * @author wyc
 * @category Amicus Plato, sed magis amica veritas
 */
public class GenusVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long genusId;
	private Long uppergenusId;
	private String name;
	private Integer ocount;
	private List<GenusVo> children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GenusVo> getChildren() {
		return children;
	}
	public void setChildren(List<GenusVo> children) {
		this.children = children;
	}
	public Long getGenusId() {
		return genusId;
	}
	public void setGenusId(Long genusId) {
		this.genusId = genusId;
	}
	public Long getUppergenusId() {
		return uppergenusId;
	}
	public void setUppergenusId(Long uppergenusId) {
		this.uppergenusId = uppergenusId;
	}
	public Integer getOcount() {
		return ocount;
	}
	public void setOcount(Integer ocount) {
		this.ocount = ocount;
	}

}