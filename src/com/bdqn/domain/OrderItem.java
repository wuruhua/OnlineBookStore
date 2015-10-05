package com.bdqn.domain;

import java.io.Serializable;

/**
 * 订单项类：OrderItem <-->domain 每个订单项对应一种书
 * 
 * @author Administrator
 * 
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;// 订单项编号
	private Book book;// 图书
	private int amount;// 数量
	private double price;// 订单项金额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
