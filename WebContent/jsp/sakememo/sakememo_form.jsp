<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<li>
	<label for="name">酒メモ名：</label>
	<input type="text" id="name" name="sakememo_name" value="${sakememo.sakememoName}" required maxlength="20" autocomplete="off">
	${sakememoError.sakememoNameErrorMessage}
</li>

<li>
	<label for="category">カテゴリ：</label>
	<select id="category" name="category_id" required>
	  <option value="" disabled ${sakememo.category.categoryId == null? "selected":""} hidden>選択</option>
	  <c:forEach var="category" items="${categoryList}">
	  	<option value="${category.categoryId}" ${category.categoryId == sakememo.category.categoryId? "selected":""}>
	  		${category.categoryName}
	  	</option>
	  </c:forEach>
	</select>
	${sakememoError.categoryErrorMessage}
</li>

<li>
	<label for="coment">コメント：</label>
	<textarea id="comment" name="sakememo_comment" required maxlength="100">${sakememo.sakememoComment}</textarea>
	${sakememoError.sakememoCommentErrorMessage}
</li>