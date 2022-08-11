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
	<div id="contents">
		<%@include file="/jsp/common/header.jsp"%>
		
		<main>
			<div class="contents">
				<p>ログイン画面とログインフォーム</p>
				
				<form id="loginform" method="post" action="<%=request.getContextPath()%>/LoginServlet">
					<ul>
						<li>
							<label for="name">ユーザー名：</label>
							<input type="text" id="name" name="user_name">
						</li>
						<li>
							<label for="pass">パスワード：</label>
							<input type="password" id="pass" name="user_pass">
						</li>
						<li>
							<button type="submit">ログイン</button>
						</li>
					</ul>
				</form>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp"%>
	
	</div>
</body>
</html>