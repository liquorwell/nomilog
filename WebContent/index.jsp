<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="トップ" />
 	</jsp:include>
	
	<body>
		<%@include file="/jsp/common/header_before_login.jsp"%>
		
		<h1>トップページ</h1>
		<p>華やかな画像とキャッチーなコンセプト</p>
		<a href="<%=request.getContextPath()%>/signup">新規登録はこちら</a>
		<a href="<%=request.getContextPath()%>/login">すでに会員の方はこちら</a>
		
		<%@include file="/jsp/common/footer.jsp"%>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>