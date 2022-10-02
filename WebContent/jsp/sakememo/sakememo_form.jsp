<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="mb-3">
	<label for="name" class="form-label">酒メモ名*</label>
	<input type="text" class="form-control" id="name" area-describedby="nameHelp" name="sakememo_name" value="${sakememo.sakememoName}" required maxlength="20" autocomplete="off">
	<div id="nameHelp" class="form-text">${sakememoError.sakememoNameErrorMessage}</div>
</div>

<div class="mb-3">
	<label for="category" class="form-label">カテゴリ*</label>
	<select class="form-select" id="category" name="category_id" area-describedby="categoryHelp" required>
	  <option value="" disabled ${sakememo.category.categoryId == null? "selected":""} hidden>選択</option>
	  <c:forEach var="category" items="${categoryList}">
	  	<option value="${category.categoryId}" ${category.categoryId == sakememo.category.categoryId? "selected":""}>
	  		${category.categoryName}
	  	</option>
	  </c:forEach>
	</select>
	<div id="categoryHelp" class="form-text">${sakememoError.categoryErrorMessage}</div>
</div>

<div class="mb-3">
	<label for="comment" class="form-label">コメント*</label>
	<textarea id="comment" class="form-control" area-describedby="commentHelp" name="sakememo_comment" required maxlength="100">${sakememo.sakememoComment}</textarea>
	<div id="commentHelp" class="form-text">${sakememoError.sakememoCommentErrorMessage}</div>
</div>