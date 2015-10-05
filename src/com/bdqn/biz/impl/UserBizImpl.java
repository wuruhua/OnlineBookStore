package com.bdqn.biz.impl;

import java.util.UUID;

import com.bdqn.biz.UserBiz;
import com.bdqn.dao.UserDao;
import com.bdqn.domain.User;
import com.bdqn.utils.BizUtils;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.UserBean;

public class UserBizImpl implements UserBiz {
	private UserDao dao = InstanceFactory.getInstance().createInstance(
			UserDao.class);

	public boolean searchUserByName(UserBean user) {
		return dao.findUserByName(user.getName());
	}

	public User userLogin(UserBean user) {
		user.setPassword(BizUtils.getMd5EncodePwd(user.getPassword()));
		return dao.findUser(user.getName(), user.getPassword());
	}

	public int userRegist(UserBean user) {
		user.setPassword(BizUtils.getMd5EncodePwd(user.getPassword()));
		return dao.saveUser(new User(UUID.randomUUID().toString(), user
				.getName(), user.getPassword(), user.getEmail()));
	}

}
