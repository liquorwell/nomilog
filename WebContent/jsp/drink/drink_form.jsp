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
	
	<p>酒ログの登録フォーム</p>
	<a href="<%=request.getContextPath()%>/drinklog_insert">登録ボタンで登録処理を行って酒ログ一覧画面に戻る</a><br>
	<a href="<%=request.getContextPath()%>/drinklog">キャンセルボタンで酒ログ一覧画面に戻る</a>
</body>
</html>