package com.bdqn.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.UserBiz;
import com.bdqn.domain.Book;
import com.bdqn.domain.User;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.utils.WebUtils;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.UserBean;

/**
 * 用户注册
 * 
 * @author Administrator
 * 
 */
public class UserRegist extends HttpServlet {

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
				request.setAttribute("userExsit", "恭喜！可以使用当前用户注册！ ");
			} else {
				request.setAttribute("userExsit", "抱歉，当前用户已经存在，不能注册！");
			}
			request.getRequestDispatcher("/WEB-INF/client/register.jsp")
					.forward(request, response);
		} else {
			int cnt = biz.userRegist(user);
			if (cnt > 0) {
				request.getRequestDispatcher(
						"/WEB-INF/client/register_success.jsp").forward(
						request, response);
			} else {
				request.setAttribute("msg", "注册失败！");
				request.getRequestDispatcher("/WEB-INF/client/register.jsp")
						.forward(request, response);
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
