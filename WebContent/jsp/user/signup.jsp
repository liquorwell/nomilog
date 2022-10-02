<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="サインアップ" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header_before_login.jsp" %>
		
		<main>
			<div class="container mt-5">
				<h1>新規ユーザー登録</h1>
				<p>${userError.userExistErrorMessage}</p>
				
				<div class="container">
					<form id="signup_form" method="post" action="<%=request.getContextPath()%>/signup_sakelog">
						<div class="mb-3">
							<label for="name" class="form-label">ユーザー名*</label>
							<input type="text" class="form-control" id="name" area-describedby="nameHelp" name="user_name" value="${userName}" required maxlength="20" autocomplete="off">
							<div id="nameHelp" class="form-text">${userError.userNameErrorMessage}</div>
						</div>
						
						<div class="mb-3">
							<label for="pass" class="form-label">パスワード*</label>
							<input type="password" class="form-control" id="pass" area-describedby="passHelp" name="user_pass" required minlength="8" maxlength="20">
							<div id="passHelp" class="form-text">${userError.userPassErrorMessage}</div>
						</div>
						
						<div class="mb-3">
							<label for="checkpass" class="form-label">パスワード（確認用）*</label>
							<input type="password" class="form-control" id="checkpass" name="check_pass" required>
						</div>
						
						<button class="btn btn-primary m-2" type="submit">登録</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>" role="button">キャンセル</a>
					</form>
				</div>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp"%>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>