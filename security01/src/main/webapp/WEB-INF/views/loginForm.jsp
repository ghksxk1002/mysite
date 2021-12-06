<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<hr />
	<form action="/login" method="post">
		<input type="text" name="username" placeholder="아이디를 입력하세요"/><br />
		<input type="password" name="password" placeholder="비밀번호를 입력하세요"/><br />
		<!-- <input type="submit" /><br /> -->
		<button>로그인</button>
	</form>
	<a href="${pageContext.request.contextPath }/joinForm">회원가입을 아직 하지 않으셨나요?</a>
</body>
</html>