<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="トップ" />
 	</jsp:include>
	
	<body>
		<%@include file="/jsp/common/header_before_login.jsp"%>
		
		<main>
		    <div class="p-5 bg-light rounded-3" style="height:100vh;">
		      <div class="container-fluid py-5">
		        <h1 class="display-5 fw-bold">のみログ</h1>
		        <p class="col-md-8 fs-4">飲んだお酒や飲みたいお酒を記録しよう</p>
		        <a class="btn btn-primary btn-lg m-2" href="<%=request.getContextPath()%>/signup" role="button">新規ユーザー登録</a>
				<a class="btn btn-success btn-lg m-2" href="<%=request.getContextPath()%>/login" role="button">ログイン</a>
		      </div>
		    </div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp"%>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>