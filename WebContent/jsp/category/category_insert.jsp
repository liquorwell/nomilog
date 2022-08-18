<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ登録 - のみログ</title>
</head>
<body>
	<%@include file="/jsp/common/header.jsp" %>
	
	<main>
		<div class="contents">
			<p>カテゴリの登録フォーム</p>
			
			<form id="category_insert" method="post" action="<%=request.getContextPath()%>/category_insert">
				<ul>
					<li>
						<label for="name">カテゴリ名：</label>
						<input type="text" id="name" name="category_name">
					</li>
					<li>
						<button type="submit">登録</button>
						<a href="<%=request.getContextPath()%>/category">キャンセル</a>
					</li>
				</ul>
			</form>
		
		</div>
	</main>
	
	<%@include file="/jsp/common/footer.jsp" %>
</body>
</html>