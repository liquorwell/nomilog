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
	<p>日付絞り込み</p>
	
		<form method="post" action="<%=request.getContextPath()%>/sakememo_filter">
		<input type="radio" id="name" name="filter_type" value="name" checked>
			<label for="name">酒メモ名：</label>
			<input type="text" name="sakememo_name">
		<input type="radio" id="category" name="filter_type" value="category">
			<label for="category">カテゴリ：</label>
			<select id="category_select" name="category_id">
			<option value="" disabled selected hidden>選択</option>
			  <c:forEach var="category" items="${categoryList}">
			  	<option value="${category.categoryId}">${category.categoryName}</option>
			  </c:forEach>
			</select>
		<input type="radio" id="ins_date" name="filter_type" value="ins_date">
			<label for="ins_date">登録日：</label>
			<input type="date" name="ins_date_old"> ～ <input type="date" name="ins_date_new"> 
		<button type="submit">絞り込み</button>
		<a href="<%=request.getContextPath()%>/sakememo">絞り込み解除</a>
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/sakememo_sort">
		<select id="sort" name="sort_type">
			<option value="ins_date_desc" selected>登録日の新しい順</option>
			<option value="ins_date_asc">登録日の古い順</option>
			<option value="category">カテゴリ順</option>
		</select>
		<button type="submit">並び替え</button>
	</form>
	
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