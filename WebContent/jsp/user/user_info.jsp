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
			<div class="container mt-5">
				<h1>${user.userName}</h1>
				
				<a class="btn btn-primary m-2" href="<%=request.getContextPath()%>/user_name_edit" role="button">ユーザー名変更</a>
				<a class="btn btn-primary m-2" href="<%=request.getContextPath()%>/user_pass_edit" role="button">パスワード変更</a><br>
				<a class="btn btn-outline-danger btn-sm m-2" href="<%=request.getContextPath()%>/user_delete_check" role="button">ユーザー削除</a>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>