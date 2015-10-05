package com.bdqn.dao;

import java.util.List;

import com.bdqn.domain.Book;
import com.bdqn.domain.QueryResult;

/**
 * 图书持久层操作
 * 
 * @author Administrator
 * 
 */
public interface BookDao {
	/**
	 * 根据用户名来查询用户(管理员添加图书时要查询一次)
	 * 
	 * @param name
	 * @return true：表中存在当前图书；false：表中不存在当前图书
	 */
	boolean findBookByName(String name);

	/**
	 * 分页查询图书(包括模糊查询图书)
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	QueryResult<Book> findAllBooks(String bookName, int startIndex, int endIndex);

	/**
	 * 保存图书（管理员给在后台给网站添加图书）
	 * 
	 * @param name
	 * @return >0：保存图书成功；<=0：保存图书失败
	 */
	int saveBook(Book book);

	/**
	 * 根据id查询图书
	 * @param ids
	 * @return
	 */
	List<Book> findBooksByIds(String[] ids);

}
