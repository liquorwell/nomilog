<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="mb-3">
	<label for="name" class="form-label">カテゴリ名*</label>
	<input type="text" class="form-control" id="name" area-describedby="nameHelp" name="category_name" value="${category.categoryName}" required maxlength="20" autocomplete="off">
	<div id="nameHelp" class="form-text">${categoryError.categoryNameErrorMessage}</div>
</div>