package com.ysyl.search.vo;
import java.util.List;


public class Hits {
	private int total;
	private String max_score;
	private List<SearchResultVo> hits;
	
	public List<SearchResultVo> getHits() {
		return hits;
	}
	public void setHits(List<SearchResultVo> hits) {
		this.hits = hits;
	}
	public String getMax_score() {
		return max_score;
	}
	public void setMax_score(String max_score) {
		this.max_score = max_score;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
