package com.bdqn.biz;

import com.bdqn.domain.Order;
import com.bdqn.domain.User;
import com.bdqn.web.formbean.Cart;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;


public interface OrderBiz {

	/**
	 * 
	 * @param info
	 * @param user:查询订单列表时，用户必须在db中存在
	 * @return
	 */
	PageBean<Order> listAllOrders(QueryInfo info,User user);

	/**
	 * 顾客下订单
	 * @param cart
	 * @param user:下订单时，用户必须在db中存在
	 * @return
	 */
	int addOrder(Cart cart, User user);
}
