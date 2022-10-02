<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="ユーザー名編集" />
 	</jsp:include>
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container mt-5">
				<h1>ユーザー名編集</h1>
				
				<div class="container">
					<form id="user_name_update" method="post" action="<%=request.getContextPath()%>/user_name_update">
						<div class="mb-3">
							<label for="name" class="form-label">ユーザー名*</label>
							<input type="text" class="form-control" id="name" area-describedby="nameHelp" name="user_name" value="${userName == null? user.userName:userName}" required maxlength="20" autocomplete="off">
							<div id="nameHelp" class="form-text">${userError.userNameErrorMessage}</div>
						</div>
						
						<button class="btn btn-primary m-2" type="submit">更新</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>/user" role="button">キャンセル</a>
					</form>
				</div>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>