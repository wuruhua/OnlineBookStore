<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div id="content" class="wrap">
	<div class="list bookList">
		<form method="post" name="shoping"
			action="${pageContext.request.contextPath}/client/ShowCart">
			<table>
				<tr class="title">
					<th class="checker"></th>
					<th>书名</th>
					<th class="price">价格</th>
					<th class="store">库存</th>
					<th class="view">图片预览</th>
				</tr>
				<c:forEach items="${bean.entities}" var="book" varStatus="status">
					<tr class="${status.index%2==0?'':'odd' }">
						<td><input type="checkbox" name="id" value="${book.id}" /></td>
						<td class="title">${book.name}</td>
						<td>￥${book.price }</td>
						<td>${book.stock}</td>
						<td class="thumb"><img
							src="${pageContext.request.contextPath}/${book.image}" /></td>
					</tr>
				</c:forEach>

				<tr>
				</tr>
			</table>
			<div class="page-spliter">
				<a href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.previousPage}','ShowAllBooks','&cssStyle=current')">&lt;</a> <a
					href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.firstPage}','ShowAllBooks','&cssStyle=current')">首页</a>


				<c:forEach items="${bean.pageBar}" var="nowPage">
					<span class="${bean.currentPage==nowPage?'current':''}"> <a
						href="javascript:void(0);"
						onclick="changeCurrentPage('${nowPage}','ShowAllBooks','&cssStyle=current')">${nowPage}</a>
					</span>
				</c:forEach>

				<a href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.lastPage}','ShowAllBooks','&cssStyle=current')">尾页</a> <a
					href="javascript:void(0);"
					onclick="changeCurrentPage('${bean.nextPage}','ShowAllBooks','&cssStyle=current')">&gt;</a>
			</div>
			<div class="button">
				<input class="input-btn" type="submit" name="submit" value="" />
			</div>
		</form>
	</div>
</div>
<%@include file="footer.jsp"%>