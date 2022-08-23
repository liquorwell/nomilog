<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>のみログ</title>
</head>
<body>
	<p>のみログのトップページ</p>
	<a href="<%=request.getContextPath()%>/tranlogin">ログイン</a>
	<a href="<%=request.getContextPath()%>/transignup">サインアップ</a>
	<p>機能説明とかコンタクトにもこの時点からヘッダー通して飛べる</p>
	
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>