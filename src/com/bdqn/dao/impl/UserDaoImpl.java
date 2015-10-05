package com.bdqn.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bdqn.dao.UserDao;
import com.bdqn.domain.User;
import com.bdqn.utils.DaoUtils;

/**
 * 用户持久层操作实现类
 * 
 * @author Administrator
 * 
 */
public class UserDaoImpl implements UserDao {
	private QueryRunner runner = new QueryRunner();

	public boolean findUserByName(String name) {
		String sql = "select * from tb_user where name=?";
		try {
			User user = (User) runner.query(DaoUtils.getConnection(), sql,
					new BeanHandler(User.class), new Object[] { name });
			if (user == null) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public User findUser(String name, String password) {
		String sql = "select * from tb_user where name=? and password=?";
		try {
			return (User) runner.query(DaoUtils.getConnection(), sql,

			new BeanHandler(User.class), new Object[] { name, password });
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int saveUser(User user) {
		String sql = "insert into tb_user values(?,?,?,?)";
		try {
			Object[] params = { user.getId(), user.getName(),
					user.getPassword(), user.getEmail() };
			return runner.update(DaoUtils.getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
