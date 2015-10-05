package com.bdqn.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.OrderBiz;
import com.bdqn.domain.Order;
import com.bdqn.domain.User;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.utils.WebUtils;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

/**
 * 客户订单显示servlet
 * 
 * @author Administrator
 * 
 */
public class ShowOrderlist extends HttpServlet {

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
		OrderBiz biz = InstanceFactory.getInstance().createInstance(
				OrderBiz.class);

		QueryInfo info = WebUtils.requestToBean(request, QueryInfo.class);
		
		request.getSession().setAttribute("condition", info.getCondition());
		request.getSession().setAttribute("oneMonthBeforeFlgSql", info.getOneMonthBeforeFlgSql());
		User user=(User)request.getSession().getAttribute("user");
		request.setAttribute("path", request.getContextPath()+"/client/ShowOrderlist");
		
		if(user==null){
			request.setAttribute("info", "请先登录！");
			request.getRequestDispatcher("/WEB-INF/client/login.jsp").forward(
					request, response);
		}else{
			
			PageBean<Order> bean = biz.listAllOrders(info,user );
			request.setAttribute("bean", bean);
			request.setAttribute("cssStyleOrderList", request.getParameter("cssStyleOrderList"));
			request.getRequestDispatcher("/WEB-INF/client/orderlist.jsp").forward(
					request, response);
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
