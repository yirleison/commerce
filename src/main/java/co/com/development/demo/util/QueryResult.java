package co.com.development.demo.util;

import org.springframework.beans.factory.annotation.Autowired;

import antlr.collections.List;

public class QueryResult {
	
	private int totalRecords;
	@Autowired
	private List list;
	
	
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
}
