<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>

	<p>カテゴリ編集画面</p>
	<p>検索、並べ替えが可能</p>
	<p>各ユーザーのカテゴリが表示　それぞれのカテゴリから編集と削除を行える</p>
	<a href="<%=request.getContextPath()%>/category_create">新規登録</a>

</body>
</html>