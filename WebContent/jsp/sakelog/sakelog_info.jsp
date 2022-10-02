<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="酒ログ" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container my-5">
				<div class="row">
					<div class="col">
						<h1>酒ログ</h1>
					</div>
					<div class="col">
						<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/sakelog_create" role="button">新規登録</a>
					</div>
				</div>
				
				<div class="container">
					<form method="post" action="<%=request.getContextPath()%>/sakelog_filter">
						<div class="row my-3 border p-2">
							<p>${sakelogError.filterErrorMessage}</p>
							<div class="col-xl-2 col-sm-4">
								<input type="radio" id="name" name="filter_type" value="name" ${filterType == null || filterType.equals('name')? "checked":""}>
								<label for="name" class="form-label">酒ログ名</label>
								<input type="text" class="form-control" name="sakelog_name" value="${nameFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-4">
								<input type="radio" id="category" name="filter_type" value="category" ${filterType.equals('category')? "checked":""}>
								<label for="category" class="form-label">カテゴリ</label>
								<select class="form-select" id="category_select" name="category_id">
								<option value="" disabled ${categoryFilterValue == null? "selected":""} hidden>選択</option>
								  <c:forEach var="category" items="${categoryList}">
								  	<option value="${category.categoryId}" ${categoryFilterValue == category.categoryId? "selected":""}>${category.categoryName}</option>
								  </c:forEach>
								</select>
							</div>
							<div class="col-xl-2 col-sm-4">
								<input type="radio" id="rating" name="filter_type" value="rating" ${filterType.equals('rating')? "checked":""}>
								<label for="rating" class="form-label">評価</label>
								<select class="form-select" id="rating_select" name="rating">
									<option value="" disabled ${ratingFilterValue == null? "selected":""} hidden>選択</option>
									<c:forEach var="i" begin="1" end="5" step="1">
										<option value="${i}" ${ratingFilterValue == i? "selected":""}>${i}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xl-2 col-sm-4">
								<input type="radio" id="ins_date" name="filter_type" value="ins_date" ${filterType.equals('ins_date')? "checked":""} >
								<label for="ins_date_old" class="form-label">登録日(古)～</label>
								<input type="date" class="form-control" name="ins_date_old" value="${insDateOldFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-4">
								<label for="ins_date_new" class="form-label">登録日(新)</label>
								<input type="date" class="form-control" name="ins_date_new" value="${insDateNewFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-4">
								<button class="btn btn-success btn-sm m-2" type="submit">絞り込み</button>
								<a class="btn btn-secondary btn-sm m-2" href="<%=request.getContextPath()%>/sakelog" role="button">絞り込み解除</a>
							</div>
						</div>
					</form>
				</div>
				
				
				<div class="container">
					<form method="post" action="<%=request.getContextPath()%>/sakelog_sort">
						<div class="row my-3 border p-2">
							<div class="col">
								<select class="form-select" id="sort" name="sort_type">
									<option value="ins_date_desc" ${sortType.equals('ins_date_desc') || sortType == null? "selected":""}>登録日の新しい順</option>
									<option value="ins_date_asc" ${sortType.equals('ins_date_asc')? "selected":""}>登録日の古い順</option>
									<option value="rating_desc" ${sortType.equals('rating_desc')? "selected":""}>評価の高い順</option>
									<option value="rating_asc" ${sortType.equals('rating_asc')? "selected":""}>評価の低い順</option>
									<option value="category" ${sortType.equals('category')? "selected":""}>カテゴリ順</option>
								</select>
							</div>
							<div class="col">
								<button class="btn btn-success btn-sm" type="submit">並び替え</button>
							</div>
						</div>
					</form>
				</div>
				
				
				<div class="container pt-3">
					<div class="table-responsive">
						<table class="table text-nowrap">
							<thead class="table-light">
								<tr>
									<th scope="col">酒ログ名</th>
									<th scope="col">カテゴリ</th>
									<th scope="col">評価</th>
									<th scope="col">コメント</th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sakelog" items="${sakelogList}">
									<tr>
										<td>${sakelog.sakelogName}</td>
										<td>${sakelog.category.categoryName == null? "-":sakelog.category.categoryName}</td>
										<td>${sakelog.rating}</td>
										<td>${sakelog.sakelogComment}</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/sakelog_edit">
												<input type="hidden" name="sakelog_id" value="${sakelog.sakelogId}">
												<button class="btn btn-outline-success btn-sm" type="submit">編集</button>
											</form>
										</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/sakelog_delete">
												<input type="hidden" name="sakelog_id" value="${sakelog.sakelogId}">
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
		
		<%@include file="/jsp/common/footer.jsp"%>
		<%@include file="/jsp/common/js.jsp"%>
	</body>
</html>