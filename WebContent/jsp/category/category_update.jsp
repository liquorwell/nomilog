<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ編集 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>カテゴリの編集フォーム</p>
			
			<form id="category_update" method="post" action="<%=request.getContextPath()%>/category_update">
				<ul>
					<li>
						<label for="name">カテゴリ名：</label>
						<input type="text" id="name" name="category_name" value="${category.categoryName}">
					</li>
					<li>
						<input type="hidden" name="category_id" value="${category.categoryId}">
					</li>
					<li>
						<button type="submit">更新</button>
						<a href="<%=request.getContextPath()%>/category">キャンセル</a>
					</li>
				</ul>
			</form>
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>	
</body>
</html>