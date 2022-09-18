<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒メモ移動 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>酒ログ→酒メモの移動フォーム</p>
			
			<form id="sakememo_move" method="post" action="<%=request.getContextPath()%>/sakememo_move">
				<ul>
					<%@include file="/jsp/sakelog/sakelog_form.jsp" %>
					<li>
						<input type="hidden" name="sakememo_id" value="${sakememoId}">
					</li>
					<li>
						<button type="submit">登録</button>
						<a href="<%=request.getContextPath()%>/sakememo">キャンセル</a>
					</li>
				</ul>
			</form>
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>	
</body>
</html>