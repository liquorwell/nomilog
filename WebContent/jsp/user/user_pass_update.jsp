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
			<div class="container mt-5">
				<h1>パスワード編集</h1>
				
				<div class="container">
					<form id="user_pass_update" method="post" action="<%=request.getContextPath()%>/user_pass_update">
						<div class="mb-3">
							<label for="curr" class="form-label">現在のパスワード*</label>
							<input type="password" class="form-control" id="curr" area-describedby="currHelp" name="curr_pass" required>
							<div id="currHelp" class="form-text">${userError.userPassErrorMessage}</div>
						</div>
						
						<div class="mb-3">
							<label for="new" class="form-label">新しいパスワード*</label>
							<input type="password" class="form-control" id="new" area-describedby="newHelp" name="new_pass" required minlength="8" maxlength="20">
							<div id="newHelp" class="form-text">${userError.userNewPassErrorMessage}</div>
						</div>
							
						<div class="mb-3">
							<label for="check" class="form-label">新しいパスワード(確認)*</label>
							<input type="password" class="form-control" id="check" name="check_pass" required>
						</div>
							
						<button class="btn btn-primary m-2" type="submit">変更</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>/user" role="button">キャンセル</a>
					</form>
				</div>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>