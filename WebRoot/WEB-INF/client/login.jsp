<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>用户登陆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

<script type="text/javascript">
	function checkUserIsLogin(msg) {
		if (msg == null || msg.trim() == '') {

		} else {
			alert(msg);
		}
	}

	//服务器端的验证，数据库中用户是否存在check
	function checkUserName(name) {
		window.location.href = '${pageContext.request.contextPath}/client/UserLogin?name='
				+ name + "&method=checkUser";

	}

	//用户密码验证
	function checkUserLogin(form) {
		//用户名check
		name = document.getElementById("userNameID").value;
		nameSpan = document.getElementById("nameSpan");
		if (name == null || name.trim() == '') {
			nameSpan.innerHTML = '用户名不能为空！';
			return false;
		} else {
			nameSpan.value = '';
		}

		//密码check
		pwd = document.getElementById("userPasswordID").value;
		pwdSpan = document.getElementById("pwdSpan");
		if (pwd == null || pwd.trim() == '') {
			pwdSpan.innerHTML = '密码不能为空！';
			return false;
		} else {
			pwdSpan.value = '';
		}
		//window.location.href = '${pageContext.request.contextPath}/client/UserLogin?name='+name+"&password="+pwd;
		//document.getElementById("formRegist").submit();
	}
</script>
</head>
<body onload="checkUserIsLogin('${info==null?'':info }');">
	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
	</div>
	<div id="login">
		<h2>用户登陆</h2>
		<!-- 			action="javascript:void(0);"
		
		onsubmit="return checkUserLogin();":
	 	含义：checkUserLogin()若返回true --》执行action
	 	   false ---》客户端拦截，不调用后台servlet
		 -->
		<form method="post"
			action="${pageContext.request.contextPath}/client/UserLogin"
			onsubmit="return checkUserLogin();" id="formRegist">
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input class="input-text" type="text" name="name"
						onblur="checkUserName(this.value);" value="${userBean.name}"
						id="userNameID" /> <span id="nameSpan">${msg}</span>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input class="input-text" type="password" name="password"
						id="userPasswordID" /><span id="pwdSpan"></span>
				</dd>
				<dt></dt>
				<dd class="button">
					<input class="input-btn" type="submit" name="submit" value="" /><input
						class="input-reg" type="button" name="register" value=""
						onclick="window.location='${pageContext.request.contextPath}/client/UserRegistUI';" />
				</dd>
			</dl>
		</form>
	</div>
	<%@include file="footer.jsp"%>