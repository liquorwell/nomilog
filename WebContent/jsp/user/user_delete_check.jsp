<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="ユーザー削除" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container mt-5">
				<h1>ユーザー削除</h1>
				<p>本当にユーザーを削除しますか？</p>
				<p>ユーザーを削除すると、酒ログ・酒メモなどの情報もすべて削除されます。</p>
			
				<div class="container">
					<form id="user_delete" method="post" action="<%=request.getContextPath()%>/user_delete">
						<div class="mb-3">
							<label for="pass" class="form-label">パスワード*</label>
							<input type="password" class="form-control" id="pass" area-describedby="passHelp" name="user_pass" required>
							<div id="passHelp" class="form-text">${userError.userPassErrorMessage}</div>
						</div>
						
						<button class="btn btn-primary m-2" type="submit">削除</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>/user" role="button">キャンセル</a>
					</form>
				</div>
			
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>