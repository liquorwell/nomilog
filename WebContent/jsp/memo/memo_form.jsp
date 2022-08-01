<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒メモ登録 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<p>酒メモの登録フォーム</p>
	<a href="<%=request.getContextPath()%>/drinkmemo_insert">登録ボタンで登録処理を行って酒メモ一覧画面に戻る</a><br>
	<a href="<%=request.getContextPath()%>/drinkmemo">キャンセルボタンで酒メモ一覧画面に戻る</a>
</body>
</html>