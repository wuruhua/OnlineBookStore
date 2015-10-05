package com.bdqn.dao;

import com.bdqn.domain.Order;
import com.bdqn.domain.QueryResult;
import com.bdqn.domain.User;

public interface OrderDao {

/**
 * 分页查询订单(包括根据订单号来精确查询订单信息)
 * @param orderId
 * @param oneMonthBeforeFlg 1：一个月前 0：所有
 * @param startIndex
 * @param endIndex
 * @return
 */
	QueryResult<Order> findAllOrders(String orderId,String oneMonthBeforeFlg, int startIndex, int endIndex,User user);

	/**
	 * 保存订单（顾客下单）
	 * 
	 * @param order
	 * @return >0：保存订单成功；<=0：保存订单失败
	 */
	int saveOrder(Order order, User user);
}
