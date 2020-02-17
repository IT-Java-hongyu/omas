package com.fosustu.omas.pojo;

/**
 *  分页实体类
 * @author Administrator
 *
 */
public class Pagination {
	private int page;   //页数
	
	private int rows;   //每页记录数
	
	private int currIndex;
	
	public int getCurrIndex() {
		return currIndex;
	}
	public void setCurrIndex(int currIndex) {
		this.currIndex = currIndex;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
