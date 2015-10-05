package com.bdqn.web.controller.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.biz.BookBiz;
import com.bdqn.domain.Book;
import com.bdqn.utils.InstanceFactory;
import com.bdqn.web.formbean.Cart;

/**
 * 购物车显示servlet
 * 
 * @author Administrator
 * 
 */
public class ShowCart extends HttpServlet {
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

		String amount = request.getParameter("amount");
		String id = request.getParameter("idTmp");

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// 在购物车页面，修改购物项数量时的业务
		if (amount != null) {
			List<Book> books = biz.searchBooksByIds(new String[] { id });
			cart.getMap().get(books.get(0).getId())
					.setAmount(Integer.parseInt(amount));

			// 若amount是0，就从购物车中清除
			if (Integer.parseInt(amount) == 0) {
				cart.getMap().remove(books.get(0).getId());
			}

			//暂时不改
			for (int i = 0; i < Integer.parseInt(amount) - 1; i++) {
				Book book = books.get(0);
				cart.addCart(book);
			}
		}

		if (cart == null) {
			cart = new Cart();
		}

		// 在用户页面，复选框选中相应图书时的业务
		String[] ids = request.getParameterValues("id");
		if (ids != null) {
			List<Book> books = biz.searchBooksByIds(ids);
			for (Book book : books) {
				cart.addCart(book);
			}
		}

		request.getSession().setAttribute("cart", cart);

		request.setAttribute("css", request.getParameter("css"));
		request.getRequestDispatcher("/WEB-INF/client/shopping.jsp").forward(
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
