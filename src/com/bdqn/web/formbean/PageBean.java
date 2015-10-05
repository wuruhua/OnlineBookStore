package com.bdqn.web.formbean;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.bdqn.common.CommonData;

public class PageBean<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set<T> entities = new LinkedHashSet<T>();// 集合（书、订单）
	private int previousPage;// 前一页
	private int firstPage;// 首页
	private int[] pageBar;// 页码条
	private int lastPage;// 尾页
	private int nextPage;// 尾页
	private int pageSize;// 一页显示的最多记录条数
	private int totalRecords;// 总记录数
	private int totalPages;// 总页数
	private int currentPage;// 当前页面

	public Set<T> getEntities() {
		return entities;
	}

	public void setEntities(Set<T> entities) {
		this.entities = entities;
	}

	public int getPreviousPage() {
		if (this.currentPage <= 1) {
			this.previousPage = 1;
		} else {
			this.previousPage = this.currentPage - 1;
		}

		return previousPage;
	}

	public int getFirstPage() {
		firstPage = 1;
		return firstPage;
	}

	public int[] getPageBar() {
		int startPageNum = 1;
		int endPageNum = 3;

		if (this.getTotalPages() < CommonData.PAGE_BAR_SIZE) {
			startPageNum = 1;
			endPageNum = this.getTotalPages();
		} else {
			if (currentPage <= CommonData.PAGE_BAR_SIZE / 2 + 1) {
				startPageNum = 1;
				endPageNum = CommonData.PAGE_BAR_SIZE;
			} else {
				startPageNum = currentPage - CommonData.PAGE_BAR_SIZE / 2;
				endPageNum = currentPage + (CommonData.PAGE_BAR_SIZE / 2 - 1);
				// 为了谨慎起见，调整边界
				if (startPageNum <= 0) {
					startPageNum = 1;
				}
				if (endPageNum > this.getTotalPages()) {
					endPageNum = this.getTotalPages();
				}

			}

		}

		int[] pageBarTemp = new int[endPageNum - startPageNum + 1];
		int index = 0;
		for (int i = startPageNum; i <= endPageNum; i++) {
			pageBarTemp[index++] = i;
		}

		pageBar = pageBarTemp;
		return pageBar;
	}

	public int getLastPage() {
		lastPage = getTotalPages();
		return lastPage;
	}

	public int getNextPage() {
		if (currentPage == getLastPage()) {
			this.nextPage = currentPage;
		} else {
			this.nextPage = currentPage + 1;
		}

		return nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		if (this.totalRecords % this.pageSize == 0) {

			this.totalPages = this.totalRecords / this.pageSize;
		} else {
			this.totalPages = this.totalRecords / this.pageSize + 1;
		}

		return totalPages;
	}

}
