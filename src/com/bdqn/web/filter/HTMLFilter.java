package com.bdqn.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 转义字符过滤器
 * 
 * @author Administrator
 * 
 */
public class HTMLFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		arg2.doFilter(new MyRequest(request), response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	/**
	 * 内部类(成员内部类)
	 * 
	 * @author Administrator
	 * 
	 */
	class MyRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;

		public MyRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			// <font color='red'>红色</font>
			// --> &it;font color='red'>红色</font>
			return filter(value);
		}

		@Override
		public Map getParameterMap() {
			Map<String, String[]> inputs = super.getParameterMap();
			for (Map.Entry<String, String[]> entry : inputs.entrySet()) {
				String[] value = entry.getValue();

				for (int i = 0; i < value.length; i++) {
					value[i] = filter(value[i]);
				}
			}
			return inputs;
		}

		/**
		 * 要过滤的字符串
		 * 
		 * @param message
		 * @return
		 */
		public String filter(String message) {

			if (message == null)
				return (null);

			char content[] = new char[message.length()];
			message.getChars(0, message.length(), content, 0);
			StringBuilder result = new StringBuilder(content.length + 50);
			for (int i = 0; i < content.length; i++) {
				switch (content[i]) {
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot;");
					break;
				default:
					result.append(content[i]);
				}
			}
			return (result.toString());

		}

	}
}
