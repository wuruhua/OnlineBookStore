package test.junit.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.bdqn.dao.BookDao;
import com.bdqn.dao.OrderDao;
import com.bdqn.dao.UserDao;
import com.bdqn.domain.Book;
import com.bdqn.domain.Order;
import com.bdqn.domain.OrderItem;
import com.bdqn.domain.QueryResult;
import com.bdqn.domain.User;
import com.bdqn.utils.BizUtils;
import com.bdqn.utils.DaoUtils;
import com.bdqn.utils.InstanceFactory;

public class OrderDaoImplTest {
	private OrderDao orderDao = InstanceFactory.getInstance().createInstance(
			OrderDao.class);

	private BookDao bookDao = InstanceFactory.getInstance().createInstance(
			BookDao.class);

	private UserDao userDao = InstanceFactory.getInstance().createInstance(
			UserDao.class);

	@Test
	public void testFindAllOrders() {
		String orderId="10007";
		String oneMonthBeforeFlg="0";
		int startIndex=1;
		int endIndex=3;
		User user = userDao.findUser("胡鑫鑫", BizUtils.getMd5EncodePwd("hxx"));
		QueryResult<Order> results=orderDao.findAllOrders(orderId, oneMonthBeforeFlg, startIndex, endIndex,user);
		System.out.println(results);
		
	}

	@Test
	public void testSaveOrder() {
		// pageSize:3
		// 准备10个订单--准备10对startIndex, endIndex
		// 每个订单有3本图书,每中型号的图书买了6本
		Map<Integer, Integer> indexes = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= 30; i += 3) {
			indexes.put(i, i + 2);// 4 6
		}
		User user = userDao.findUser("胡鑫鑫", BizUtils.getMd5EncodePwd("hxx"));

		// 订单项
		for (Map.Entry<Integer, Integer> entry : indexes.entrySet()) {
			// 准备订单
			Order order = new Order();
			double total = 0;
			QueryResult<Book> books = bookDao.findAllBooks("", entry.getKey(),
					entry.getValue());
			for (Book book : books.getResults()) {
				OrderItem item = new OrderItem();
				item.setId(UUID.randomUUID().toString());
				item.setBook(book);
				item.setAmount(6);
				item.setPrice(item.getAmount() * book.getPrice());
				order.getItems().add(item);
				total += item.getPrice();
			}
			order.setPrice(total);
			order.setReciever(user.getName());

			orderDao.saveOrder(order, user);

			// 提交事务
			commonDealwith();
		}
	}

	private void commonDealwith() {
		DaoUtils.trasactionCommit();
		DaoUtils.releaseConn();
	}
}
