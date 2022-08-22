<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー - のみログ</title>
</head>
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<p>ユーザー画面</p>
			<p>${user.userName}</p>
			<a href="<%=request.getContextPath()%>/user_name_edit">ユーザー名変更</a>
			<a href="<%=request.getContextPath()%>/user_pass_edit">パスワード変更</a>
			<a href="<%=request.getContextPath()%>/user_delete_check">ユーザー削除</a>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
	</body>
</html>