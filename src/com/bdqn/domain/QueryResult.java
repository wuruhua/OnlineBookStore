package com.bdqn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层操作的实体类，放在domain包中
 * 查询结果类：QueryResult<T><-->domain,对数据库中根据条件查询出来的总记录数，以及记录本身的封装
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public class QueryResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> results = new ArrayList<T>();// 分页显示的数据
	private int totalRecoreds = 0;// 总记录数

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getTotalRecoreds() {
		return totalRecoreds;
	}

	public void setTotalRecoreds(int totalRecoreds) {
		this.totalRecoreds = totalRecoreds;
	}

}
