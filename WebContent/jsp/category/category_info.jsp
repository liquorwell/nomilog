<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="カテゴリ" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container my-5">
				<div class="row">
					<div class="col">
						<h1>カテゴリ</h1>
					</div>
					<div class="col">
						<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/category_create" role="button">新規登録</a>
					</div>
				</div>
				
				
				<div class="container pt-3">
					<div class="table-responsive">
						<table class="table text-nowrap">
							<thead class="table-light">
								<tr>
									<th scope="col">カテゴリ</th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="category" items="${categoryList}">
									<tr>
										<td>${category.categoryName}</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/category_edit">
												<input type="hidden" name="category_id" value="${category.categoryId}">
												<button class="btn btn-outline-success btn-sm" type="submit">編集</button>
											</form>
										</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/category_delete">
												<input type="hidden" name="category_id" value="${category.categoryId}">
												<button class="btn btn-outline-danger btn-sm" type="submit">削除</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
		</main>
		
		<%@include file="/jsp/common/footer.jsp" %>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>