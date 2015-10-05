package com.bdqn.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.OrderBiz;
import com.bdqn.domain.User;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.Cart;

/**
 * 处理客户订单servlet
 * 
 * @author Administrator
 * 
 */
public class OrderOperator extends HttpServlet {

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
		// 1、从session中查询用户，若没有登录，跳转到登录页面
		// 用户登录时
		User user = (User) request.getSession().getAttribute("user");

		// 1)登录成功，跳转到购物车页面
		// 2)若不成功，跳转到注册页面
		if(user==null){
			request.getRequestDispatcher("/WEB-INF/client/login.jsp").forward(
					request, response);
		}else{
			// a)注册成功后，跳到注册成功页面，再跳到用户首页
			OrderBiz biz = InstanceFactory.getInstance().createInstance(
					OrderBiz.class);
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			biz.addOrder(cart, user);
			request.getSession().removeAttribute("cart");
			//System.out.println("购物成功！");
			request.getRequestDispatcher("/WEB-INF/client/shopping-success.jsp").forward(request, response);
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
