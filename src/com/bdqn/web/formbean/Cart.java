package com.bdqn.web.formbean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bdqn.domain.Book;

/**
 * 购物车项：CartItem <-->FormBean
 * 
 * @author Administrator
 * 
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	// String: 指的是书的id
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();// 订单项
	private double totalPrice;// 订单总价
	private String reciever;// 收货员

	// 用户购物，书本添加到购物车
	public void addCart(Book book) {
		CartItem item = map.get(book.getId());
		// 购物车中没有当前选择的图书,用户选中一本图书的情况
		if (item == null) {
			item = new CartItem();
			item.setBook(book);
			item.setAmount(1);
			item.setPrice(book.getPrice());
			//把订单项放入购物车
			map.put(book.getId(), item);
		} else {
			//item.setAmount(item.getAmount() + 1);
			item.setPrice(book.getPrice() * item.getAmount());
		}
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public double getTotalPrice() {
		double tmpTotalPrice = 0;
		for (Map.Entry<String, CartItem> entry : map.entrySet()) {
			tmpTotalPrice += entry.getValue().getPrice();
		}

		this.totalPrice = tmpTotalPrice;
		return totalPrice;
	}

	// 本类中能够计算出来的，就无需set
	// public void setTotalPrice(double totalPrice) {
	// this.totalPrice = totalPrice;
	// }

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

}
