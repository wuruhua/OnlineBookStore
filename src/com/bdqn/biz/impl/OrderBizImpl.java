package com.bdqn.biz.impl;

import java.util.Map;
import java.util.UUID;

import com.bdqn.biz.OrderBiz;
import com.bdqn.dao.OrderDao;
import com.bdqn.domain.Order;
import com.bdqn.domain.OrderItem;
import com.bdqn.domain.QueryResult;
import com.bdqn.domain.User;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.Cart;
import com.bdqn.web.formbean.CartItem;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

public class OrderBizImpl implements OrderBiz {
	private OrderDao dao = InstanceFactory.getInstance().createInstance(
			OrderDao.class);

	/**
	 * 
	 * @param info
	 * @param user
	 *            :domain中的user，web层从数据库中查询出来，通过web层传递过来
	 * @return
	 */
	public PageBean<Order> listAllOrders(QueryInfo info, User user) {
		QueryResult<Order> results = dao.findAllOrders(info.getCondition(),
				info.getOneMonthBeforeFlgSql(), info.getStartIndex(),
				info.getEndIndex(), user);

		PageBean<Order> bean = new PageBean<Order>();
		bean.getEntities().addAll(results.getResults());
		bean.setTotalRecords(results.getTotalRecoreds());
		bean.setPageSize(info.getPageSize());
		bean.setCurrentPage(info.getCurrentPage());
		return bean;
	}

	public int addOrder(Cart cart, User user) {
		// 1、通过购物车Cart构造订单Order
		Order order = new Order();

		// 2、通过购物车项CartItem构造订单OrderItem
		for (Map.Entry<String, CartItem> entry : cart.getMap().entrySet()) {
			CartItem item = entry.getValue();
			OrderItem orderItem = new OrderItem();
			orderItem.setAmount(item.getAmount());
			orderItem.setBook(item.getBook());
			orderItem.setId(UUID.randomUUID().toString());
			orderItem.setPrice(item.getPrice());
			order.getItems().add(orderItem);
		}

		order.setPrice(cart.getTotalPrice());
		// reciever收货员不一定是下单人，可以在表单中添加一个文本框来手动设置
		if (cart.getReciever() == null || "".equals(cart.getReciever())) {
			order.setReciever(user.getName());
		} else {
			order.setReciever(cart.getReciever());
		}
		
		order.setPrice(cart.getTotalPrice());
		
		return dao.saveOrder(order, user);
	}
}
