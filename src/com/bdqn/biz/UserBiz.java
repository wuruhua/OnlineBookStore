package com.bdqn.biz;

import com.bdqn.domain.User;
import com.bdqn.web.formbean.UserBean;

/**
 * 用户管理模块业务逻辑层操作
 * 
 * @author Administrator
 * 
 */
public interface UserBiz {
	/**
	 * 根据用户名来查询用户(注册、登录)
	 * 
	 * @param user
	 * @return
	 */
	boolean searchUserByName(UserBean user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	User userLogin(UserBean user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	int userRegist(UserBean user);
}
