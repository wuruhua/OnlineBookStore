package com.bdqn.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用户类：User <--->domain
 * 
 * @author Administrator
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String password;
	private String email;// 邮箱
	private Set<Order> orders = new LinkedHashSet<Order>();// 订单项

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public User(String id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
