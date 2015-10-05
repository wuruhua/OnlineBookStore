package com.bdqn.web.controller.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.BookBiz;
import com.bdqn.domain.Book;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.utils.WebUtils;
import com.bdqn.web.formbean.PageBean;
import com.bdqn.web.formbean.QueryInfo;

/**
 * 图书显示servlet
 * @author Administrator
 *
 */
public class ShowAllBooks extends HttpServlet {

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
		BookBiz biz = InstanceFactory.getInstance().createInstance(
				BookBiz.class);

		QueryInfo info = WebUtils.requestToBean(request, QueryInfo.class);
		
		request.getSession().setAttribute("condition", info.getCondition());
		
		PageBean<Book> bean = biz.listAllBooks(info);
		request.setAttribute("bean", bean);
		request.setAttribute("path", request.getContextPath()+"/client/ShowAllBooks");
		request.setAttribute("cssStyle", request.getParameter("cssStyle"));
		request.getRequestDispatcher("/WEB-INF/client/index.jsp").forward(
				request, response);
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
