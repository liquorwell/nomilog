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
			<div class="contents">
				<p>ユーザー登録フォーム</p>
				<p>${userError.userExistErrorMessage}</p>
				
				<form id="signup_form" method="post" action="<%=request.getContextPath()%>/signup_sakelog">
					<ul>
						<li>
							<label for="name">ユーザー名：</label>
							<input type="text" id="name" name="user_name" value="${userName}" required maxlength="20" autocomplete="off">
							${userError.userNameErrorMessage}
						</li>
						<li>
							<label for="pass">パスワード：</label>
							<input type="password" id="pass" name="user_pass" required minlength="8" maxlength="20">
							${userError.userPassErrorMessage}
						</li>
						<li>
							<label for="checkpass">パスワード（確認用）：</label>
							<input type="password" id="checkpass" name="check_pass" required>
						</li>
						<li>
							<button type="submit">ユーザー登録</button>
						</li>
					</ul>
				</form>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp"%>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>