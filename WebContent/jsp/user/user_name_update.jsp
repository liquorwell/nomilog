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
			<p>ユーザー名編集フォーム</p>
			
			<form id="user_name_update" method="post" action="<%=request.getContextPath()%>/user_name_update">
				<ul>
					<li>
						<label for="name">ユーザー名：</label>
						<input type="text" id="name" name="user_name" value="${userName == null? user.userName:userName}" required maxlength="20" autocomplete="off">
						${userError.userNameErrorMessage}
					</li>
					<li>
						<button type="submit">更新</button>
						<a href="<%=request.getContextPath()%>/user">キャンセル</a>
					</li>
				</ul>
			</form>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>