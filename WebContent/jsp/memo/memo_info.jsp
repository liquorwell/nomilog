<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒メモ - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>

	<p>酒メモ画面</p>
	<p>検索、絞り込み、並べ替えが可能</p>
	<p>各ユーザーの酒メモが表示　それぞれのメモから編集と削除と酒ログへの移動を行える</p>
	<a href="<%=request.getContextPath()%>/drinkmemo_create">新規登録</a>

</body>
</html>