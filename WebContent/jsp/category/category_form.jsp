<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<li>
	<label for="name">カテゴリ名：</label>
	<input type="text" id="name" name="category_name" value="${category.categoryName}" required maxlength="20" autocomplete="off">
	${categoryError.categoryNameErrorMessage}
</li>