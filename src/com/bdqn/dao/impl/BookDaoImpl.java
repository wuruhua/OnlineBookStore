package com.bdqn.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bdqn.dao.BookDao;
import com.bdqn.domain.Book;
import com.bdqn.domain.QueryResult;
import com.bdqn.utils.DaoUtils;

/**
 * 图书持久层操作实现类
 * 
 * @author Administrator
 * 
 */
public class BookDaoImpl implements BookDao {
	private QueryRunner runner = new QueryRunner();

	public boolean findBookByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public QueryResult<Book> findAllBooks(String bookName, int startIndex,
			int endIndex) {
		QueryResult<Book> bookResults = new QueryResult<Book>();

		// 准备模糊查询的sql b.name like '%钱%' and
		String likeSql = "";

		if (bookName == null || "".equals(bookName.trim())) {
			likeSql = "";
		} else {
			likeSql = " b.name like '%".concat(bookName).concat("%' and");
		}

		String sql = "select * from (select rownum r,b.* from tb_book b where"
				.concat(likeSql).concat(" rownum<=?) where r>=?");
		try {
			// 1、页面分页显示的数据
			Connection conn = DaoUtils.getConnection();
			@SuppressWarnings("unchecked")
			List<Book> bs = (List<Book>) runner.query(conn, sql,
					new BeanListHandler(Book.class), new Object[] { endIndex,
							startIndex });

			bookResults.setResults(bs);

			// 2、总记录数
			// b.name like '%钱%' and
			sql = "select count(*) from tb_book b where ".concat(likeSql)
					.concat(" 1=1");

			bookResults.setTotalRecoreds(((BigDecimal) runner.query(conn, sql,
					new ScalarHandler())).intValue());
			return bookResults;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public int saveBook(Book book) {
		String sql = "insert into tb_book values(?,?,?,?,?)";
		try {
			Object[] params = { book.getId(), book.getName(), book.getPrice(),
					book.getStock(), book.getImage() };
			return runner.update(DaoUtils.getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooksByIds(String[] ids) {
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			if (id != null && id.equals(ids[ids.length - 1]))
				sb.append("'").append(id).append("'");
			else
				sb.append("'").append(id).append("',");
		}

		String sql = "select * from tb_book where id in(" + sb.toString() + ")";
		try {

			return (List<Book>) runner.query(DaoUtils.getConnection(), sql,
					new BeanListHandler(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
