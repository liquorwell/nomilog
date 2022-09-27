<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="カテゴリ編集" />
 	</jsp:include>
	 
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="contents">
				<p>カテゴリの編集フォーム</p>
				
				<form id="category_update" method="post" action="<%=request.getContextPath()%>/category_update">
					<ul>
						<%@include file="/jsp/category/category_form.jsp" %>
						<li>
							<input type="hidden" name="category_id" value="${category.categoryId}">
						</li>
						<li>
							<button type="submit">更新</button>
							<a href="<%=request.getContextPath()%>/category">キャンセル</a>
						</li>
					</ul>
				</form>
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>	
	</body>
</html>