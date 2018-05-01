package com.ysyl.search.vo;

public class Must {
	private Wildcard wildcard;
	
	private Query_string query_string;
	
	private Range range;

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public Wildcard getWildcard() {
		return wildcard;
	}

	public void setWildcard(Wildcard wildcard) {
		this.wildcard = wildcard;
	}
	
	

	public Query_string getQuery_string() {
		return query_string;
	}

	public void setQuery_string(Query_string query_string) {
		this.query_string = query_string;
	}
}
