package com.bdqn.domain;

import java.io.Serializable;

/**
 * 图书类
 * 
 * @author Administrator
 * 
 */
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private double price;
	private int stock;// 库存
	private String image;// 图片

	public Book() {
		super();
	}

	public Book(String id, String name, double price, String image, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
