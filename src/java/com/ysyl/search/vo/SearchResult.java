package com.ysyl.search.vo;

public class SearchResult {
	private int took;
	private boolean timed_out;
	private Shards _shards;
	private Hits hits;
	public Hits getHits() {
		return hits;
	}
	public void setHits(Hits hits) {
		this.hits = hits;
	}
	public Shards get_shards() {
		return _shards;
	}
	public void set_shards(Shards _shards) {
		this._shards = _shards;
	}
	public boolean isTimed_out() {
		return timed_out;
	}
	public void setTimed_out(boolean timed_out) {
		this.timed_out = timed_out;
	}
	public int getTook() {
		return took;
	}
	public void setTook(int took) {
		this.took = took;
	}
}
