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

<title>User首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

<script type="text/javascript">
	//图书分页的时候
	function changeCurrentPage(currentPage, servletName, cssStyle,oneMonthBeforeFlgSql) {
		//alert(servletName);
		window.location.href = '${pageContext.request.contextPath}/client/'
				+ servletName + '?currentPage=' + currentPage
				+ '&condition=${sessionScope.condition}' + cssStyle+oneMonthBeforeFlgSql;
	}

	//整数check
	function checkNum(amount) {
		var numRegex = /^-?\d+$/;
		reg = new RegExp(numRegex);
		if (!reg.test(amount.value)) {
			//amount.focus();
			return false;
		} else {
			return true;
		}
	}
	//购物车check
	function checkCart(totalPrice) {
		//alert(totalPrice);
		if (totalPrice == 0) {
			//amount.focus();
			alert("购物车不能为空！");
			return false;
		} else {
			window.location.href = '${pageContext.request.contextPath}/client/OrderOperator';
		}
	}

	//修改数量
	function changeAmount(amount, id, stock) {

		if (!checkNum(amount)) {
			alert("请录入数字！");
			return;
		} else if (parseInt(amount.value) < 0) {
			alert("请录入正数！");
			return;
		} else if (parseInt(amount.value) > stock) {
			alert("数字不能大于图书库存（" + stock + "）！");
			return;
		}

		//alert("amount=" + amount);
		//alert("id=" + id);
		window.location.href = '${pageContext.request.contextPath}/client/ShowCart?amount='
				+ amount.value + '&idTmp=' + id;
	}
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<li class="${cssStyle}"><a
						href="${pageContext.request.contextPath}/client/ShowAllBooks?cssStyle=current">User首页</a></li>
					<li class="${cssStyleOrderList}"><a
						href="${pageContext.request.contextPath}/client/ShowOrderlist?cssStyleOrderList=current">我的订单</a></li>
					<li class="${cssStyleCart}"><a
						href="${pageContext.request.contextPath}/client/ShowCart?css=current">购物车</a></li>
					<li><a
						href="${pageContext.request.contextPath}/client/UserLoginOut">注销</a></li>
				</ul>
			</div>
			<form method="post" name="search"
				action="${path}">
				搜索：<input class="input-text" type="text" name="condition"
					id="condition" value="${sessionScope.condition}" /><input
					class="input-btn" type="submit" name="submit" value="" />
			</form>
		</div>
	</div>