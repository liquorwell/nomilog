<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="酒メモ編集" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container mt-5">
				<h1>酒メモ編集</h1>
				
				<div class="container">
					<form id="sakememo_update" method="post" action="<%=request.getContextPath()%>/sakememo_update">
						<%@include file="/jsp/sakememo/sakememo_form.jsp" %>
						<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
						
						<button class="btn btn-primary m-2" type="submit">更新</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>/sakememo" role="button">キャンセル</a>
					</form>
				</div>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>	
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>