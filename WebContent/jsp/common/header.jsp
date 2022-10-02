<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			
			<a class="navbar-brand" href="<%=request.getContextPath()%>">のみログ</a>
			
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbar">
				<div class="navbar-nav">
					<a class="nav-link" href="<%=request.getContextPath()%>/sakelog">酒ログ</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/sakememo">酒メモ</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/category">カテゴリ</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/user">${user.userName}</a>
					<a class="nav-link" href="<%=request.getContextPath()%>/logout">ログアウト</a>
				</div>
			</div>
			
		</div>
	</nav>
</header>