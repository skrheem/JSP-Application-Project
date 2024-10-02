<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<!-- 만약 메서드형식으로 따로 html에서 URL주소에 login.do를 안 치고 싶다면
<form action="${pageContext.request.contextPath}/login.do" method="post">
이러한 형식으로 쓰면 된다. -->
<form action="login.do" method="post">
	<c:if test="${errors.idOrPwNotMatch }">
	아이디와 암호가 일치하지 않습니다.
	</c:if>
	<p>
		아이디:<br><input type="text" name="id" value="${param.id }">
		<c:if test="${errors.id }">ID를 입력하세요.</c:if>
	</p>
	<p>
		암호:<br><input type="password" name="password">
		<c:if test="${errors.password }">암호를 입력하세요.</c:if>
	</p>
	<input type="submit" value="로그인">	
</form>
</body>
</html>