<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="ユーザー" />
 	</jsp:include>
 	
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
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>