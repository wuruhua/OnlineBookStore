package com.bdqn.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.utils.DaoUtils;

/**
 * 事务提交过滤器
 * 
 * @author Administrator
 * 
 */
public class TransactionFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		arg2.doFilter(request, response);
		// 目标代码执行完后，再执行提交
		try {
			DaoUtils.trasactionCommit();
		} catch (Exception e) {
			DaoUtils.trasactionRollback();
		} finally {
			DaoUtils.releaseConn();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
