<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping" action="javascript:void(0);" onsubmit="checkCart('${cart.totalPrice}')">
			<table>
				<tr class="title">
					<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th class="price">价格</th>
				</tr>
				<c:forEach items="${cart.map}" var="entry" varStatus="status">
				<tr class="${status.index%2==0?'':'odd' }">
				<td class="thumb"><img src="${pageContext.request.contextPath}/${entry.value.book.image}" /></td>
					<td class="title">${entry.value.book.name}</td>
					<td><input class="input-text" type="text" name="amount" value="${entry.value.amount}"  onchange="changeAmount(this,'${entry.value.book.id}','${entry.value.book.stock}')" /></td>
					<td>￥<span>${entry.value.price}</span></td>
				</tr>
				
				</c:forEach>
			</table>
			<div class="button">
				<h4>总价：￥<span>${cart.totalPrice}</span>元</h4>
				<input class="input-chart" type="submit" name="submit" value="" />
			</div>
		</form>
	</div>
</div>
<%@include file="footer.jsp" %>
