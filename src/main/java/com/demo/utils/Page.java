package com.demo.utils;
import java.util.List;
public class Page<T>{
	private byte pageSize;//每一页大小,应从配置文件中读取
	private List<T> rs;//每页内容
	private int itemNum;//每一页实际条目数
	private int currentPage;//页面显示当前页数
	private long totalItemNum;//系统中总的纪录条数
	private int totalpages;  //系统中总页数
	
	public byte getPageSize() {
		return pageSize;
	}
	public void setPageSize(byte pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getRs() {
		return  rs;
	}
	public void setRs(List<T> rs) {
		this.rs = rs;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalItemNum() {
		return totalItemNum;
	}
	public void setTotalItemNum(long totalItemNum) {
		this.totalItemNum = totalItemNum;
	}

	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
}
