<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒ログ - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>

	<p>酒ログ画面</p>
	<p>検索、絞り込み、並べ替え、表示形式の変更が可能</p>
	<table>
		<tr>
			<th>酒ログ名</th>
			<th>カテゴリ</th>
			<th>評価</th>
			<th>コメント</th>
		</tr>
		<c:forEach var="sakelog" items="${sakelogList}">
			<tr>
				<td>${sakelog.sakelogName}</td>
				<td>${sakelog.category.categoryName}</td>
				<td>${sakelog.rating}</td>
				<td>${sakelog.sakelogComment}</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/sakelog_edit">
						<input type="hidden" name="sakelog_id" value="${sakelog.sakelogId}">
						<button type="submit">編集</button>
					</form>
				</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/sakelog_delete">
						<input type="hidden" name="sakelog_id" value="${sakelog.sakelogId}">
						<button type="submit">削除</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/sakelog_create">新規登録</a>
	
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>