package com.ysyl.search.vo;

public class Must_not {
	private Wildcard wildcard;
	
	private Query_string query_string;

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
