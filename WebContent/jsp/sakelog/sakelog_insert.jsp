<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒ログ登録 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>酒ログの登録フォーム</p>
			
			<form id="sakelog_insert" method="post" action="<%=request.getContextPath()%>/sakelog_insert">
				<ul>
					<li>
						<label for="name">酒ログ名：</label>
						<input type="text" id="name" name="sakelog_name">
					</li>
					<li>
						<label for="category">カテゴリ：</label>
						<select id="category" name="category_id">
						  <option value="" disabled selected hidden>選択してください</option>
						  <c:forEach var="category" items="${categoryList}">
						  	<option value="${category.categoryId}">${category.categoryName}</option>
						  </c:forEach>
						</select>
					</li>
					<li>
						<p>評価：</p>
						<c:forEach var="i" begin="1" end="5" step="1">
							<input type="radio" id="r${i}" name="rating" value="${i}">
							<label for="r${i}">${i}</label>
						</c:forEach>
					</li>
					<li>
						<label for="coment">コメント：</label>
						<textarea id="comment" name="sakelog_comment"></textarea>
					</li>
					<li>
						<button type="submit">登録</button>
						<a href="<%=request.getContextPath()%>/sakelog">キャンセル</a>
					</li>
				</ul>
			</form>
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>	
</body>
</html>