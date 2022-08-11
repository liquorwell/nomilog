<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<p>ヘッダー</p>
	<a href="<%=request.getContextPath()%>/sakelog">のみログ</a><br>
	<a href="<%=request.getContextPath()%>/sakelog">酒ログ</a>
	<a href="<%=request.getContextPath()%>/sakememo">酒メモ</a>
	<a href="<%=request.getContextPath()%>/category">カテゴリ編集</a>
	<a href="<%=request.getContextPath()%>/user">アカウント</a>
</header>