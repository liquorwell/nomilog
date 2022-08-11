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
	<p>それぞれのログから編集と削除を行える</p>
	<table>
		<tr>
			<th>酒ログ名</th>
			<th>評価</th>
			<th>コメント</th>
			<th>カテゴリ</th>
		</tr>
		<c:forEach var="sakelog" items="${sakelogList}">
			<tr>
				<td>${sakelog.sakelogName}</td>
				<td>${sakelog.rating}</td>
				<td>${sakelog.sakelogComment}</td>
				<td>${sakelog.category.categoryName}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/sakelog_create">新規登録</a>
</body>
</html>