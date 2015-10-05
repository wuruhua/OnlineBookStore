package com.bdqn.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 订单类：Order <-->domain
 * 
 * @author Administrator
 * 
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;// 订单编号
	private Set<OrderItem> items = new LinkedHashSet<OrderItem>();// 订单项
	private String reciever;// 收货员
	private double price;// 订单金额
	private Date orderTime;// 下单时间
	private boolean status;// true:已完成；false：未完成

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
