<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 	<jsp:include page="/jsp/common/head.jsp">
 		<jsp:param name="title" value="酒メモ" />
 	</jsp:include>
 	
	<body>
		<%@include file="/jsp/common/header.jsp" %>
		
		<main>
			<div class="container my-5">
				<div class="row">
					<div class="col">
						<h1>酒メモ</h1>
					</div>
					<div class="col">
						<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/sakememo_create" role="button">新規登録</a>
					</div>
				</div>
				
				<div class="container">
					<form method="post" action="<%=request.getContextPath()%>/sakememo_filter">
						<div class="row my-3 border p-2">
							<p>${sakememoError.filterErrorMessage}</p>
							<div class="col-xl-2 col-sm-6">
								<input type="radio" id="name" name="filter_type" value="name" ${filterType == null || filterType.equals('name')? "checked":""}>
								<label for="name" class="form-label">酒メモ名</label>
								<input type="text" class="form-control" name="sakememo_name" value="${nameFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-6">
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
								<input type="radio" id="ins_date" name="filter_type" value="ins_date" ${filterType.equals('ins_date')? "checked":""}>
								<label for="ins_date_old" class="form-label">登録日(古)～</label>
								<input type="date" class="form-control" name="ins_date_old" value="${insDateOldFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-4">
								<label for="ins_date_new"class="form-label">登録日(新)</label>
								 <input type="date" class="form-control" name="ins_date_new" value="${insDateNewFilterValue}">
							</div>
							<div class="col-xl-2 col-sm-4">
								<button class="btn btn-success btn-sm m-2" type="submit">絞り込み</button>
								<a class="btn btn-secondary btn-sm m-2" href="<%=request.getContextPath()%>/sakememo" role="button">絞り込み解除</a>
							</div>
						</div>
					</form>
				</div>
				
				
				<div class="container">
					<form method="post" action="<%=request.getContextPath()%>/sakememo_sort">
						<div class="row my-3 border p-2">
							<div class="col">
								<select class="form-select" id="sort" name="sort_type">
									<option value="ins_date_desc" ${sortType.equals('ins_date_desc') || sortType == null? "selected":""}>登録日の新しい順</option>
									<option value="ins_date_asc" ${sortType.equals('ins_date_asc')? "selected":""}>登録日の古い順</option>
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
									<th scope="col">酒メモ名</th>
									<th scope="col">カテゴリ</th>
									<th scope="col">コメント</th>
									<th scope="col"></th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sakememo" items="${sakememoList}">
									<tr>
										<td>${sakememo.sakememoName}</td>
										<td>${sakememo.category.categoryName == null? "-":sakememo.category.categoryName}</td>
										<td>${sakememo.sakememoComment}</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/sakememo_tranmove">
												<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
												<button class="btn btn-outline-success btn-sm" type="submit">酒ログに移動</button>
											</form>
										</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/sakememo_edit">
												<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
												<button class="btn btn-outline-success btn-sm" type="submit">編集</button>
											</form>
										</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/sakememo_delete">
												<input type="hidden" name="sakememo_id" value="${sakememo.sakememoId}">
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