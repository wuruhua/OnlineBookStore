package com.bdqn.biz;

import java.util.List;

import com.bdqn.domain.Book;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

/**
 * 图书管理模块业务逻辑层操作
 * 
 * @author Administrator
 * 
 */
public interface BookBiz {

	/**
	 * 管理员添加图书时查询图书
	 * 
	 * @param name
	 * @return
	 */
	boolean searchBookByName(String name);

	/**
	 * 图书列表
	 * 
	 * @param info
	 * @return
	 */
	PageBean<Book> listAllBooks(QueryInfo info);
	
	/**
	 * 根据id来查询图书
	 * @param ids
	 * @return
	 */
	List<Book> searchBooksByIds(String[] ids);

	/**
	 * 管理员给在后台给网站添加图书
	 * 
	 * @param book
	 * @return
	 */
	int addBook(Book book);
	
	
}
