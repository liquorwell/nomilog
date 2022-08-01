<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ登録 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<p>カテゴリの登録フォーム</p>
	<a href="<%=request.getContextPath()%>/category_insert">登録ボタンで登録処理を行ってカテゴリ一覧画面に戻る</a><br>
	<a href="<%=request.getContextPath()%>/category">キャンセルボタンでカテゴリ一覧画面に戻る</a>
</body>
</html>