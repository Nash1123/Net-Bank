package com.netbank.entity;

public class Pager {
	private int curPage;
	private int perPageRows ;
	private int rowCount;   
	private int pageCount; 	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int currentPage) {
		this.curPage = currentPage;
	}
	public int getPerPageRows() {
		return perPageRows;
	}
	public void setPerPageRows(int perPageRows) {
		this.perPageRows = perPageRows;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return (rowCount+perPageRows-1)/perPageRows;
	}
}
