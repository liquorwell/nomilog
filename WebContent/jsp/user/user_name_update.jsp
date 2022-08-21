<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名編集 - のみログ</title>
</head>
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<p>ユーザー名編集フォーム</p>
			
			<form id="user_name_update" method="post" action="<%=request.getContextPath()%>/user_name_update">
				<ul>
					<li>
						<label for="name">ユーザー名：</label>
						<input type="text" id="name" name="user_name" value="${user.userName}">
					</li>
					<li>
						<button type="submit">更新</button>
						<a href="<%=request.getContextPath()%>/user">キャンセル</a>
					</li>
				</ul>
			</form>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
	</body>
</html>