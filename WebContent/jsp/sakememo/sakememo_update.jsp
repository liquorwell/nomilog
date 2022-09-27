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
			<div class="contents">
				<p>酒メモの編集フォーム</p>
				
				<form id="sakememo_update" method="post" action="<%=request.getContextPath()%>/sakememo_update">
					<ul>
						<%@include file="/jsp/sakememo/sakememo_form.jsp" %>
						<li>
							<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
						</li>
						<li>
							<button type="submit">更新</button>
							<a href="<%=request.getContextPath()%>/sakememo">キャンセル</a>
						</li>
					</ul>
				</form>
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>	
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>