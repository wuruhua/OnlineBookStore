package com.bdqn.dao;

import com.bdqn.domain.User;

/**
 * 用户持久层操作
 * 
 * @author Administrator
 * 
 */
public interface UserDao {
	/**
	 * 根据用户名来查询用户(注册、登录)
	 * 
	 * @param name
	 * @return true：表中存在当前用户；false：表中不存在当前用户
	 */
	boolean findUserByName(String name);

	/**
	 * 根据用户名密码来查询用户(登录)
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	User findUser(String name, String password);

	/**
	 * 保存用户（注册）
	 * 
	 * @param name
	 * @return >0：保存用户成功；<=0：保存用户失败
	 */
	int saveUser(User user);

}
