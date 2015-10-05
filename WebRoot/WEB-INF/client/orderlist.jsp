<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<div id="content" class="wrap">
	<div class="list orderList">
		<table>
			<tr class="title">
				<th class="orderId">订单编号</th>
				<th>订单商品</th>
				<th class="userName">收货人</th>
				<th class="price">订单金额</th>
				<th class="createTime">下单时间</th>
				<th class="status">订单状态</th>
			</tr>

			<c:forEach items="${bean.entities}" var="order" varStatus="status">
				<tr class="${status.index%2==0?'':'odd' }">
					<td>${order.id}</td>

					<!-- 一个订单有多个订单项，所以，订单商品应该有多个 -->
					<td class="thumb">
					<c:forEach items="${order.items}" var="item">
						<img
							src="${pageContext.request.contextPath}/${item.book.image}" />
					</c:forEach>
					</td>

					<td>${order.reciever}</td>
					<td>￥${order.price}</td>
					<td>${order.orderTime}</td>
					<td>${order.status==true?'已完成':'未完成'}</td>
				</tr>
			</c:forEach>

		</table>
	<div class="page-spliter">
				<a href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.previousPage}','ShowOrderlist','&cssStyleOrderList=current','${sessionScope.oneMonthBeforeFlgSql}')">&lt;</a> <a
					href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.firstPage}','ShowOrderlist','&cssStyleOrderList=current','${sessionScope.oneMonthBeforeFlgSql}')">首页</a>


				<c:forEach items="${bean.pageBar}" var="nowPage">
					<span class="${bean.currentPage==nowPage?'current':''}"> <a
						href="javascript:void(0);"
						onclick="changeCurrentPage('${nowPage}','ShowOrderlist','&cssStyleOrderList=current','${sessionScope.oneMonthBeforeFlgSql}')">${nowPage}</a>
					</span>
				</c:forEach>

				<a href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.lastPage}','ShowOrderlist','&cssStyleOrderList=current','${sessionScope.oneMonthBeforeFlgSql}')">尾页</a> <a
					href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.nextPage}','ShowOrderlist','&cssStyleOrderList=current','${sessionScope.oneMonthBeforeFlgSql}')">&gt;</a>
			</div>
		<div class="button">
			<input class="input-gray" type="button" name="oneMonthBeforeFlgSql"
				value="查看一个月前的订单" onclick="changeCurrentPage('1','ShowOrderlist','&cssStyleOrderList=current','1')"/>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
