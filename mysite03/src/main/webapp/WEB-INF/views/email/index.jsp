<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<h1>이메일 보내기</h1>
					<form method="post" action="${pageContext.request.contextPath }/email/send">
					발신자 이름: <input name="senderName"><br />
					발신자 이메일 주소: <input name="senderMail"><br/>
					수신자 이메일 주소: <input name="receiveMail"><br/>
					제목 : <input name="subject"><br />
					내용 : <textarea rows="5" cols="80" name="message"></textarea><br/>
					<input type="submit" value="전송">
					</form>
					<span style="color:red">${message}</span>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>