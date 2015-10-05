package com.bdqn.web.formbean;

import java.io.Serializable;

import com.bdqn.domain.Book;

public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Book book;// 图书
	private int amount;// 数量
	private double price;// 订单项金额

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
		this.price = book.getPrice() * amount;
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
