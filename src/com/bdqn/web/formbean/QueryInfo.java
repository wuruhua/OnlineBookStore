package com.bdqn.web.formbean;

import java.io.Serializable;

import com.bdqn.common.CommonData;

/**
 * 用户点击页码条以及首页等等查询热连接，封装一个formbean：QueryInfo
 * 
 * @author Administrator
 * 
 */
public class QueryInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int currentPage = 1;// 当前页(给一个默认值)
	private int pageSize = CommonData.PAGE_SIZE;// 一页显示的最多记录条数
	private String condition;// 查询条件(书：书名;订单：订单号)
	private String oneMonthBeforeFlgSql = "0";// 查询条件(一个月前的订单flg:0 所有 1：一个月前)
	private int startIndex;
	private int endIndex;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOneMonthBeforeFlgSql() {
		return oneMonthBeforeFlgSql;
	}

	public void setOneMonthBeforeFlgSql(String oneMonthBeforeFlgSql) {
		this.oneMonthBeforeFlgSql = oneMonthBeforeFlgSql;
	}

	public int getStartIndex() {
		this.startIndex = (currentPage - 1) * pageSize + 1;
		return startIndex;
	}

	public int getEndIndex() {
		this.endIndex = getStartIndex() + pageSize - 1;
		return endIndex;
	}

	/**
	 * set方法可以省略，原因是可以通过计算得到，不需要外面设置值
	 * 
	 * @param endIndex
	 */
	// public void setEndIndex(int endIndex) {
	// this.endIndex = endIndex;
	// }

}
