package com.ysyl.search.vo;

public class Shards {
	private int total;
	private int successful;
	private int failed;
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	public int getSuccessful() {
		return successful;
	}
	public void setSuccessful(int successful) {
		this.successful = successful;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
