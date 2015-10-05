package test.junit.biz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bdqn.biz.BookBiz;
import com.bdqn.biz.OrderBiz;
import com.bdqn.biz.UserBiz;
import com.bdqn.domain.Book;
import com.bdqn.domain.Order;
import com.bdqn.domain.User;
import com.bdqn.utils.DaoUtils;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.Cart;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;
import com.bdqn.web.formbean.UserBean;

public class OrderBizImplTest {
	private OrderBiz biz = InstanceFactory.getInstance().createInstance(
			OrderBiz.class);

	private BookBiz bookBiz = InstanceFactory.getInstance().createInstance(
			BookBiz.class);

	private UserBiz userBiz = InstanceFactory.getInstance().createInstance(
			UserBiz.class);

	@Test
	public void testListAllOrders() {
		User u = userLoginDealWith();

		QueryInfo info = new QueryInfo();
		info.setCurrentPage(3);
		PageBean<Order> bookResults = biz.listAllOrders(info, u);
		System.out.println(bookResults);
	}

	// 模拟用户登录
	private User userLoginDealWith() {
		UserBean user = new UserBean();
		user.setName("夏诗涵");
		user.setPassword("xsh");
		User u = userBiz.userLogin(user);
		return u;
	}

	@Test
	public void testAddOrder() {
		User user = userLoginDealWith();

		// 10个订单，每个订单有三个订单项，每个订单项有10本书
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			pages.add(i);
		}

		// 订单项
		for (Integer currentPage : pages) {
			// 购物车
			Cart cart = new Cart();
			QueryInfo info = new QueryInfo();
			info.setCurrentPage(currentPage);
			PageBean<Book> bookResults = bookBiz.listAllBooks(info);

			for (Book book : bookResults.getEntities()) {
				//每个订单项买了同种图书10本
				for(int i=0;i<10;i++){
					cart .addCart(book);
				}
			}
			
			biz.addOrder(cart, user);

			// 提交事务
			commonDealwith();

		}

	}

	private void commonDealwith() {
		DaoUtils.trasactionCommit();
		DaoUtils.releaseConn();
	}
}
