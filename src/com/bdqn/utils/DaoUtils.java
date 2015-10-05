package com.bdqn.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 持久层工具类
 * 
 * @author Administrator
 * 
 */
public class DaoUtils {
	private static ThreadLocal<Connection> localThread = new ThreadLocal<Connection>();
	private static ComboPooledDataSource dataSouce = null;

	static {
		dataSouce = new ComboPooledDataSource();
	}

	/**
	 * 从数据源获得连接的实例
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = localThread.get();// 获取
		if (conn == null) {
			try {
				conn = dataSouce.getConnection();
				localThread.set(conn);// 绑定
				conn.setAutoCommit(false);// 自动提交设置为false，等价于在此开启了事务
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return conn;
	}

	/**
	 * 事务回滚
	 */
	public static void trasactionRollback() {
		Connection conn = localThread.get();// 从本地线程获取连接的实例
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 提交事务
	 */
	public static void trasactionCommit() {
		Connection conn = localThread.get();
		if (conn != null) {
			try {
				conn.commit();// 提交事务
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		/*
		 * else{ System.out.println("sadfsadfsadfa"); }
		 */

	}

	/**
	 * 释放资源(D)
	 */
	public static void releaseConn() {
		Connection conn = localThread.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				localThread.remove();
			}
		}

	}

}
