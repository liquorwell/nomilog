<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBテスト</title>
</head>
<body>
	<table>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.age}</td>
				<td>${user.address}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>