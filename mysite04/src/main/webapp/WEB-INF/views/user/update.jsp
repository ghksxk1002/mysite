<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/update">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }"/>

					<label class="block-label" for="email">이메일</label>
					<h3>${userVo.email }</h3>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<c:choose>
							<c:when test="${userVo.gender eq 'male' }">
								<fieldset>
									<legend>성별</legend>
									<label>여</label> <input type="radio" name="gender" value="female" >
									<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
								</fieldset>
							</c:when>
							<c:when test="${userVo.gender eq 'female' }">
								<fieldset>
									<legend>성별</legend>
									<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
									<label>남</label> <input type="radio" name="gender" value="male" >
								</fieldset>
							</c:when>
						</c:choose>
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>