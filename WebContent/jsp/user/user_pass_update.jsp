<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="パスワード変更" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<p>パスワード変更フォーム</p>
			
			<form id="user_pass_update" method="post" action="<%=request.getContextPath()%>/user_pass_update">
				<ul>
					<li>
						<label for="curr">現在のパスワード：</label>
						<input type="password" id="curr" name="curr_pass" required>
						${userError.userPassErrorMessage}
					</li>
					<li>
						<label for="new">新しいパスワード：</label>
						<input type="password" id="new" name="new_pass" required minlength="8" maxlength="20">
						${userError.userNewPassErrorMessage}
					</li>
					<li>
						<label for="check">新しいパスワード(確認)：</label>
						<input type="password" id="check" name="check_pass" required>
					</li>
					<li>
						<button type="submit">変更</button>
						<a href="<%=request.getContextPath()%>/user">キャンセル</a>
					</li>
				</ul>
			</form>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>