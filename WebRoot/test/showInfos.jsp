<%@ page language="java"
	import="java.util.*,com.bdqn.web.formbean.UserBean,com.bdqn.utils.WebUtils"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>显示学生信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<%
			//1、原始的封装JavaBean的方式
			/*String name = request.getParameter("name");
			String introduction = request.getParameter("introduction");

			UserBean user = new UserBean(name,introduction);
			
			pageContext.setAttribute("user",user);*/
			//2、使用BeanUtils来封装JavaBean的方式
			UserBean user = WebUtils.requestToBean(request, UserBean.class);
			pageContext.setAttribute("user", user);
		%>


		<!--<p>
			姓名：${param.name}
		</p>
		<p>
			简介：${param.introduction}
		</p>
		-->
		<p>
			姓名：${user.name}
		</p>
		<p>
			简介：${user.introduction}
		</p>
	</body>
</html>
