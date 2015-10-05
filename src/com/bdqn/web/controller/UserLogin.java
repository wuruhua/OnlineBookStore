package com.bdqn.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.UserBiz;
import com.bdqn.domain.User;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.utils.WebUtils;
import com.bdqn.web.formbean.UserBean;

/**
 * 用户登录
 * 
 * @author Administrator
 * 
 */
public class UserLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBiz biz = InstanceFactory.getInstance().createInstance(
				UserBiz.class);

		UserBean user = WebUtils.requestToBean(request, UserBean.class);

		request.setAttribute("userBean", user);

		if ("checkUser".equals(request.getParameter("method"))) {
			// 登录用户名check
			boolean isExist = biz.searchUserByName(user);
			if (!isExist) {
				request.setAttribute("msg", "当前用户不存在！");
				request.getRequestDispatcher("/WEB-INF/client/login.jsp")
						.forward(request, response);
			} else {
				request.setAttribute("msg", "恭喜！可以使用当前用户登录！");
				request.getRequestDispatcher("/WEB-INF/client/login.jsp")
						.forward(request, response);
			}
		} else {
			// 用户登录
			User userDomain = biz.userLogin(user);
			if (userDomain != null) {
				request.getSession().setAttribute("user", userDomain);
				// response.sendRedirect后面不能是web-inf下的页面
				response.sendRedirect(request.getContextPath()
						+ "/client/ShowCart");
			} else {
				request.setAttribute("msg", "用户名不存在！请注册！");
				request.getRequestDispatcher("/WEB-INF/client/register.jsp")
						.forward(request, response);
				// response.sendRedirect(request.getContextPath()+"/client/UserRegistUI");
			}
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
