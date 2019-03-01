package com.shida.cn.utils;

import java.util.List;

public class Page<T> {
	private int currentPage; //当前页数
	private int totalPage;   //总页数
	private int count;       //总记录数
	private int pageSize;    //每页显示数
	private List<T> lists;   //每页详细内容
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		}else {
			totalPage = count / pageSize + 1;
		}
		return totalPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public Page() {
		super();
	}
	public Page(int currentPage, int count, int pageSize, List<T> lists) {
		super();
		this.currentPage = currentPage;
		this.count = count;
		this.pageSize = pageSize;
		this.lists = lists;
	}
	
}
