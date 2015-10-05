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

<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

<script type="text/javascript">
	function checkUserIsRegist(msg) {
		if (msg == null || msg.trim() == '') {

		} else {
			alert(msg);
		}
	}

	//服务器端的验证，数据库中用户是否存在check
	function checkUserName(name) {
		if (name == null || name.trim() == '') {
			//alert(name);
			document.getElementById("nameMsg").innerHTML = '用户名不能为空!';
			return false;
		} else {
			document.getElementById("nameMsg").innerHTML = '';
			//服务器端的验证时机是：name不为空
			window.location.href = '${pageContext.request.contextPath}/client/UserRegist?name='
					+ name + "&method=checkUser";
			return true;
		}

	}

	//客户端的验证，密码不能为空！且是3~6位的数字
	function checkPwd(pwd) {
		if (pwd == null || pwd.trim() == '') {
			document.getElementById("passwordMsg").innerHTML = '密码不能为空!';
			return false;
		}

		var numRegex = /^\d{3,6}$/;
		reg = new RegExp(numRegex);
		if (!reg.test(pwd)) {
			document.getElementById("passwordMsg").innerHTML = '密码是3～6位的数字!';
			return false;
		} else {
			document.getElementById("passwordMsg").innerHTML = '';
			return true;
		}
	}

	//客户端的验证，重复密码与密码匹配
	function checkRePwd(pwd, rePwd) {
		if (pwd != rePwd) {
			document.getElementById("repasswordMsg").innerHTML = '两次录入密码不一致！';
			return false;
		} else {
			document.getElementById("repasswordMsg").innerHTML = '';
			return true;
		}
	}

	//客户端的验证，验证邮箱
	function checkEmail(email) {
		//janson@sina.com.cn
		//janson@126.com
		var numRegex = /^\w+@\w+(.\w+){1,2}$/;
		reg = new RegExp(numRegex);
		if (!reg.test(email)) {
			document.getElementById("emailMsg").innerHTML = '邮箱格式不正确！';
			return false;
		} else {
			document.getElementById("emailMsg").innerHTML = '';
			return true;
		}
	}

	//客户端的验证，表单提交时触发验证，若checkUserRegistForm函数返回值为true
	//表单提交，若为false，客户端拦截，表单不能提交
	function checkUserRegistForm() {
		name = document.getElementsByName("name")[0].value;
		pwd = document.getElementsByName("password")[0].value;
		rePwd = document.getElementsByName("repassword")[0].value;
		email = document.getElementsByName("email")[0].value;

		//用户客户端验证通过
		//再验证密码
		if (checkPwd(pwd)) {
			//在验证重复密码
			if (checkRePwd(pwd, rePwd)) {
				//验证邮箱
				if (checkEmail(email)) {
					if (checkUserName(name)) {
						return true;

					} else {
						return false;
					}
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}

	}
</script>
</head>
<body onload="checkUserIsRegist('${msg==null?'':msg }');">
	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
	</div>
	<div id="register">
		<div class="title">
			<h2>欢迎注册北大青鸟网上书城</h2>
		</div>
		<div class="steps">
			<ul class="clearfix">
				<li class="current">1.填写注册信息</li>
				<li class="unpass">2.注册成功</li>
			</ul>
		</div>
		<form method="post"
			action="${pageContext.request.contextPath}/client/UserRegist"
			onsubmit="return checkUserRegistForm()">
			<dl>
				<dt>用 户 名：</dt>
				<dd>
					<input class="input-text" type="text" name="name"
						onblur="checkUserName(this.value)" value="${userBean.name}" /><span
						id="nameMsg">${userExsit}</span>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input class="input-text" type="password" name="password"
						onblur="checkPwd(this.value)" id="passwordId" /><span
						id="passwordMsg"></span>
				</dd>
				<dt>确认密码：</dt>
				<dd>
					<input class="input-text" type="password" name="repassword"
						onblur="checkRePwd(this.value,document.getElementById('passwordId').value)" /><span
						id="repasswordMsg"></span>
				</dd>
				<dt>Email地址：</dt>
				<dd>
					<input class="input-text" type="text" name="email"
						onblur="checkEmail(this.value)" /><span id="emailMsg"></span>
				</dd>
				<dt></dt>
				<dd class="button">
					<input class="input-reg" type="submit" name="register" value="" />
				</dd>
			</dl>
		</form>
	</div>
	<%@include file="footer.jsp"%>