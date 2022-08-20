<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒メモ - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>

	<p>酒メモ画面</p>
	<p>検索、絞り込み、並べ替えが可能</p>
	<p>酒ログへの移動を行える</p>
	<table>
		<tr>
			<th>酒メモ名</th>
			<th>カテゴリ</th>
			<th>コメント</th>
		</tr>
		<c:forEach var="sakememo" items="${sakememoList}">
			<tr>
				<td>${sakememo.sakememoName}</td>
				<td>${sakememo.category.categoryName}</td>
				<td>${sakememo.sakememoComment}</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/sakememo_tranmove">
						<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
						<button type="submit">酒ログに移動</button>
					</form>
				</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/sakememo_edit">
						<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
						<button type="submit">編集</button>
					</form>
				</td>
				<td>
					<form method="get" action="<%=request.getContextPath()%>/sakememo_delete">
						<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
						<button type="submit">削除</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/sakememo_create">新規登録</a>

	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>