<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String no = request.getParameter("no");
%>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form method="post"
					action="<%=request.getContextPath()%>/gb?a=delete">
					<input type='hidden' name="no" value="<%=no%>"> 
					<input type="password" name="password"> <input type="submit" value="확인">
				</form>
				<br /> <a href="<%=request.getContextPath()%>/gb?a=index">메인으로돌아가기</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>