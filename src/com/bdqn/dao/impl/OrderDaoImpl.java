package com.bdqn.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bdqn.dao.OrderDao;
import com.bdqn.domain.Book;
import com.bdqn.domain.Order;
import com.bdqn.domain.OrderItem;
import com.bdqn.domain.QueryResult;
import com.bdqn.domain.User;
import com.bdqn.utils.DaoUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner runner = new QueryRunner();

	@SuppressWarnings("unchecked")
	public QueryResult<Order> findAllOrders(String orderId,
			String oneMonthBeforeFlg, int startIndex, int endIndex,User user) {
		QueryResult<Order> bookResults = new QueryResult<Order>();

		// 准备精确查询订单号的sql o.id = 10007 and
		String orderIdSql = "";
		if (orderId == null || "".equals(orderId.trim())) {
			orderIdSql = "";
		} else {
			orderIdSql = " o.id=".concat(orderId).concat(" and");
		}

		// 准备查询一个月前的订单sql o.id = 10007 and
		// (ordertime between add_months(sysdate,-1) and sysdate ) and
		String oneMonthBeforeFlgSql = "";
		if ( "0".equals(oneMonthBeforeFlg.trim())) {
			oneMonthBeforeFlgSql = "";
		} else {
			oneMonthBeforeFlgSql = " (ordertime between add_months(sysdate,-1) and sysdate ) and ";
		}

		String sql = "select * from (select rownum r,o.* from tb_order o where userid=? and "
				.concat(oneMonthBeforeFlgSql).concat(orderIdSql)
				.concat(" rownum<=?) where r>=?");
		try {
			// 1、页面分页显示的数据
			Connection conn = DaoUtils.getConnection();

			List<Order> os = (List<Order>) runner.query(conn, sql,
					new BeanListHandler(Order.class), new Object[] {user.getId(), endIndex,
							startIndex });

			bookResults.setResults(os);

			// 2、总记录数
			// b.name like '%钱%' and
			sql = "select count(*) from tb_order o where userid=? and "
					.concat(oneMonthBeforeFlgSql).concat(orderIdSql)
					.concat(" 1=1");

			bookResults.setTotalRecoreds(((BigDecimal) runner.query(conn, sql,
					new ScalarHandler(),new Object[]{user.getId()})).intValue());

			// 3、查询出该订单包含的所有订单项
			for (Order order : os) {
				sql = "select * from tb_OrderItem where orderid=?";
				// 把订单与订单项关联起来
				order.getItems().addAll(
						(List<OrderItem>) runner.query(conn, sql,
								new BeanListHandler(OrderItem.class),
								new Object[] { order.getId() }));

				// 把订单项与书建立关联
				for (OrderItem item : order.getItems()) {
					sql = "select b.* from tb_book b,tb_OrderItem item where b.id=item.booid and item.id=?";
					// 把书与订单项关联起来
					item.setBook((Book) runner.query(conn, sql,
							new BeanHandler(Book.class),
							new Object[] { item.getId() }));
				}

			}

			return bookResults;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int saveOrder(Order order, User user) {

		// 连接的实例
		Connection conn = DaoUtils.getConnection();

		String sql = "";

		// 0、查询出order的id（序列）
		try {
			sql = "select tb_order_seq.nextVal from dual";
			order.setId(((BigDecimal) runner.query(conn, sql,
					new ScalarHandler())).intValue());

			// 1、往订单表中插入一条记录
			sql = "insert into tb_order(id,reciever,price,userid,status) values(?,?,?,?,?)";
			Object[] params = { order.getId(), order.getReciever(),
					order.getPrice(), user.getId(),
					order.isStatus() == true ? "1" : "0" };
			int cnt = runner.update(conn, sql, params);

			// 2、往订单表项表中插入多条记录
			for (OrderItem item : order.getItems()) {
				sql = "insert into tb_OrderItem values(?,?,?,?,?)";
				params = new Object[] { item.getId(), item.getBook().getId(),
						item.getAmount(), item.getPrice(), order.getId() };
				runner.update(conn, sql, params);

			}

			// 3、xxx更新图书表中的库存数量(管理员的活！客户若更新book表，就犯忌了！)
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
