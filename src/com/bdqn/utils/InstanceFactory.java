package com.bdqn.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 实例工厂（制造实例）
 * 
 * @author Administrator
 * 
 */
public class InstanceFactory {
	// 工厂本身使用单例模式
	private static InstanceFactory instance = new InstanceFactory();

	private InstanceFactory() {
	}

	public static InstanceFactory getInstance() {
		return instance;
	}

	private static Properties prop = null;
	static {
		prop = new Properties();
		try {
			prop.load(InstanceFactory.class.getClassLoader()
					.getResourceAsStream("instanceFactory.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 工厂模式
	 * @param <T>
	 * @param t
	 * @return
	 */
	public <T> T createInstance(Class<T> t) {
		String key = t.getSimpleName();
		String value = prop.getProperty(key);
		try {
			return (T) Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
