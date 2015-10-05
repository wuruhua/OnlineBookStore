package com.bdqn.biz.impl;

import java.util.List;

import com.bdqn.biz.BookBiz;
import com.bdqn.dao.BookDao;
import com.bdqn.domain.Book;
import com.bdqn.domain.QueryResult;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

public class BookBizImpl implements BookBiz {
	private BookDao dao = InstanceFactory.getInstance().createInstance(
			BookDao.class);

	public boolean searchBookByName(String name) {
		return false;
	}

	public PageBean<Book> listAllBooks(QueryInfo info) {

		QueryResult<Book> results = dao.findAllBooks(info.getCondition(),
				info.getStartIndex(), info.getEndIndex());

		PageBean<Book> bean = new PageBean<Book>();
		bean.getEntities().addAll(results.getResults());
		bean.setTotalRecords(results.getTotalRecoreds());
		bean.setPageSize(info.getPageSize());
		bean.setCurrentPage(info.getCurrentPage());
		return bean;
	}

	public int addBook(Book book) {
		return 0;
	}

	
	public List<Book> searchBooksByIds(String[] ids) {
		return dao.findBooksByIds(ids);
	}

}
