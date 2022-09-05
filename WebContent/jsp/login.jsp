<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン - のみログ</title>
</head>
<body>

	
	<main>
		<div class="contents">
			<p>ログイン画面とログインフォーム</p>
			
			${userError.userExistErrorMessage}
			
			<form id="loginform" method="post" action="<%=request.getContextPath()%>/login">
				<ul>
					<li>
						<label for="name">ユーザー名*</label>
						<input type="text" id="name" name="user_name">
						${userError.userNameErrorMessage}
					</li>
					<li>
						<label for="pass">パスワード*</label>
						<input type="password" id="pass" name="user_pass">
						${userError.userPassErrorMessage}
					</li>
					<li>
						<button type="submit">ログイン</button>
					</li>
				</ul>
			</form>
			
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>