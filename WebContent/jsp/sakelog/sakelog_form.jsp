<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<li>
	<label for="name">酒ログ名：</label>
	<input type="text" id="name" name="sakelog_name" value="${sakelog.sakelogName}" required maxlength="20" autocomplete="off">
	${sakelogError.sakelogNameErrorMessage}
</li>

<li>
	<label for="category">カテゴリ：</label>
	<select id="category" name="category_id" required>
	  <option value="" disabled ${sakelog.category.categoryId == null? "selected":""} hidden>選択</option>
	  <c:forEach var="category" items="${categoryList}">
	  	<option value="${category.categoryId}" ${category.categoryId == sakelog.category.categoryId? "selected":""}>
	  		${category.categoryName}
	  	</option>
	  </c:forEach>
	</select>
	${sakelogError.categoryErrorMessage}
</li>

<li>
	<p>評価：</p>
	<c:forEach var="i" begin="1" end="5" step="1">
		<input type="radio" id="r${i}" name="rating" value="${i}" <c:if test="${i == sakelog.rating}">checked</c:if> required>
		<label for="r${i}">${i}</label>
	</c:forEach>
	${sakelogError.ratingErrorMessage}
</li>

<li>
	<label for="coment">コメント：</label>
	<textarea id="comment" name="sakelog_comment" required maxlength="100">${sakelog.sakelogComment}</textarea>
	${sakelogError.sakelogCommentErrorMessage}
</li>