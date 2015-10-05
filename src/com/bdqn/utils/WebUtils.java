package com.bdqn.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


/**
 * Web层的工具类
 * 
 * @author Administrator
 * 
 */
public class WebUtils {

	public static <T> T requestToBean(HttpServletRequest request, Class<T> t) {
		try {
			T obj = t.newInstance();
			Map map = request.getParameterMap();// name:键 value：顾客输入
			BeanUtils.populate(obj, map);//要求参数名与java属性名一致，否则，值设置不进去
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
