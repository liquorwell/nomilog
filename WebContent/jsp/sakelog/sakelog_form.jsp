<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="mb-3">
	<label for="name" class="form-label">酒ログ名*</label>
	<input type="text" class="form-control" id="name" area-describedby="nameHelp" name="sakelog_name" value="${sakelog.sakelogName}" required maxlength="20" autocomplete="off">
	<div id="nameHelp" class="form-text">${sakelogError.sakelogNameErrorMessage}</div>
</div>

<div class="mb-3">
	<label for="category" class="form-label">カテゴリ*</label>
	<select class="form-select" id="category" name="category_id" area-describedby="categoryHelp" required>
	  <option value="" disabled ${sakelog.category.categoryId == null? "selected":""} hidden>選択</option>
	  <c:forEach var="category" items="${categoryList}">
	  	<option value="${category.categoryId}" ${category.categoryId == sakelog.category.categoryId? "selected":""}>
	  		${category.categoryName}
	  	</option>
	  </c:forEach>
	</select>
	<div id="categoryHelp" class="form-text">${sakelogError.categoryErrorMessage}</div>
</div>

<div class="mb-3">
	<p class="form-label">評価*</p>
	<c:forEach var="i" begin="1" end="5" step="1">
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="r${i}" name="rating" value="${i}" <c:if test="${i == sakelog.rating}">checked</c:if> required>
			<label class="form-check-label" for="r${i}">${i}</label>
		</div>
	</c:forEach>
	<div id="ratingHelp" class="form-text">${sakelogError.ratingErrorMessage}</div>
</div>

<div class="mb-3">
	<label for="comment" class="form-label">コメント*</label>
	<textarea id="comment" class="form-control" area-describedby="commentHelp" name="sakelog_comment" required maxlength="100">${sakelog.sakelogComment}</textarea>
	<div id="commentHelp" class="form-text">${sakelogError.sakelogCommentErrorMessage}</div>
</div>