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
	<p>表示形式の変更</p>
	<p>${sakelogError.filterErrorMessage}</p>
	<form method="post" action="<%=request.getContextPath()%>/sakelog_filter">
		<input type="radio" id="name" name="filter_type" value="name" ${filterType == null || filterType.equals('name')? "checked":""}>
			<label for="name">酒ログ名：</label>
			<input type="text" name="sakelog_name" value="${nameFilterValue}">
		<input type="radio" id="category" name="filter_type" value="category" ${filterType.equals('category')? "checked":""}>
			<label for="category">カテゴリ：</label>
			<select id="category_select" name="category_id">
			<option value="" disabled ${categoryFilterValue == null? "selected":""} hidden>選択</option>
			  <c:forEach var="category" items="${categoryList}">
			  	<option value="${category.categoryId}" ${categoryFilterValue == category.categoryId? "selected":""}>${category.categoryName}</option>
			  </c:forEach>
			</select>
		<input type="radio" id="rating" name="filter_type" value="rating" ${filterType.equals('rating')? "checked":""}>
			<label for="rating">評価：</label>
			<select id="rating_select" name="rating">
				<option value="" disabled ${ratingFilterValue == null? "selected":""} hidden>選択</option>
				<c:forEach var="i" begin="1" end="5" step="1">
					<option value="${i}" ${ratingFilterValue == i? "selected":""}>${i}</option>
				</c:forEach>
			</select>
		<input type="radio" id="ins_date" name="filter_type" value="ins_date" ${filterType.equals('ins_date')? "checked":""}>
			<label for="ins_date">登録日：</label>
			<input type="date" name="ins_date_old" value="${insDateOldFilterValue}">（古） ～ <input type="date" name="ins_date_new" value="${insDateNewFilterValue}"> （新）
		<button type="submit">絞り込み</button>
		<a href="<%=request.getContextPath()%>/sakelog">絞り込み解除</a>
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/sakelog_sort">
		<select id="sort" name="sort_type">
			<option value="ins_date_desc" ${sortType.equals('ins_date_desc') || sortType == null? "selected":""}>登録日の新しい順</option>
			<option value="ins_date_asc" ${sortType.equals('ins_date_asc')? "selected":""}>登録日の古い順</option>
			<option value="rating_desc" ${sortType.equals('rating_desc')? "selected":""}>評価の高い順</option>
			<option value="rating_asc" ${sortType.equals('rating_asc')? "selected":""}>評価の低い順</option>
			<option value="category" ${sortType.equals('category')? "selected":""}>カテゴリ順</option>
		</select>
		<button type="submit">並び替え</button>
	</form>
	
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
				<td>${sakelog.category.categoryName == null? "-":sakelog.category.categoryName}</td>
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