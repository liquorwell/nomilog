<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー削除 - のみログ</title>
</head>
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<p>ユーザー削除</p>
			<p>本当にユーザーを削除しますか？</p>
			<p>ユーザーを削除すると、酒ログ・酒メモなどの情報もすべて削除されます。</p>
			
			<p>${errorMessage}</p>
			
			<form id="user_delete" method="post" action="<%=request.getContextPath()%>/user_delete">
				<ul>
					<li>
						<label for="pass">パスワードを入力：</label>
						<input type="password" id="pass" name="user_pass">
					</li>
					<li>
						<button type="submit">削除</button>
						<a href="<%=request.getContextPath()%>/user">キャンセル</a>
					</li>
				</ul>
			</form>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
	</body>
</html>