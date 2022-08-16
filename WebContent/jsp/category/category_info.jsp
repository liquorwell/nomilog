<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>

	<p>カテゴリ編集画面</p>
	<p>検索、並べ替えが可能</p>
	
	<table>
		<tr>
			<th>カテゴリ</th>
		</tr>
		<c:forEach var="category" items="${categoryList}">
			<tr>
				<td>${category.categoryName}</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/category_edit">
						<input type="hidden" name="category_id" value="${category.categoryId}">
						<button type="submit">編集</button>
					</form>
				</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/category_delete">
						<input type="hidden" name="category_id" value="${category.categoryId}">
						<button type="submit">削除</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/category_create">新規登録</a>

	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>