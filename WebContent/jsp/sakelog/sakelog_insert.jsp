<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="酒ログ登録" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="contents">
				<p>酒ログの登録フォーム</p>
				
				<form id="sakelog_insert" method="post" action="<%=request.getContextPath()%>/sakelog_insert">
					<ul>
						<%@include file="/jsp/sakelog/sakelog_form.jsp" %>
						<li>
							<button type="submit">登録</button>
							<a href="<%=request.getContextPath()%>/sakelog">キャンセル</a>
						</li>
					</ul>
				</form>
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>	
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>