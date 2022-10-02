<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="カテゴリ登録" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container mt-5">
				<h1>カテゴリ登録</h1>
				
				<div class="container">
					<form id="category_insert" method="post" action="<%=request.getContextPath()%>/category_insert">
						<%@include file="/jsp/category/category_form.jsp" %>
						
						<button class="btn btn-primary m-2" type="submit">登録</button>
						<a class="btn btn-outline-secondary m-2" href="<%=request.getContextPath()%>/category" role="button">キャンセル</a>
					</form>
				</div>
			
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>