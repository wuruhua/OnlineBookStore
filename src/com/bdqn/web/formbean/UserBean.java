package com.bdqn.web.formbean;

import java.io.Serializable;

/**
 * 用户类：UserBean <--->formBean
 * @author Administrator
 *
 */
public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String repassword;
	private String email;// 邮箱

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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
