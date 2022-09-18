<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒ログ編集 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>酒ログの編集フォーム</p>
			
			<form id="sakelog_update" method="post" action="<%=request.getContextPath()%>/sakelog_update">
				<ul>
					<%@include file="/jsp/sakelog/sakelog_form.jsp" %>
					<li>
						<input type="hidden" name="sakelog_id" value="${sakelog.sakelogId}">
					</li>
					<li>
						<button type="submit">更新</button>
						<a href="<%=request.getContextPath()%>/sakelog">キャンセル</a>
					</li>
				</ul>
			</form>
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>	
</body>
</html>