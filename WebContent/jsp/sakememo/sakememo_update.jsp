<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒メモ編集 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>酒メモの編集フォーム</p>
			
			<form id="sakememo_update" method="post" action="<%=request.getContextPath()%>/sakememo_update">
				<ul>
					<li>
						<label for="name">酒メモ名：</label>
						<input type="text" id="name" name="sakememo_name" value="${sakememo.sakememoName}">
					</li>
					<li>
						<label for="category">カテゴリ：</label>
						<select id="category" name="category_id">
						  <c:forEach var="category" items="${categoryList}">
						  	<option value="${category.categoryId}" ${category.categoryId == sakememo.category.categoryId? "selected":""}>
						  		${category.categoryName}
						  	</option>
						  </c:forEach>
						</select>
					</li>
					<li>
						<label for="coment">コメント：</label>
						<textarea id="comment" name="sakememo_comment">${sakememo.sakememoComment}</textarea>
					</li>
					<li>
						<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
					</li>
					<li>
						<button type="submit">更新</button>
						<a href="<%=request.getContextPath()%>/sakememo">キャンセル</a>
					</li>
				</ul>
			</form>
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>	
</body>
</html>