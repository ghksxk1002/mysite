<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/gb" method="post">
					<input type="hidden" name="a" value="add">
					<table border=1 width=500>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<br>
				<ul>
					<c:set var='count' value='${fn:length(list)}' />
					<c:forEach items='${list }' var='vo' varStatus='status' >
						<table>
							<tr>
								<td>[${count-status.index }]</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a
									href="${pageContext.request.contextPath }/gb?a=deleteform&no=${vo.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
									${fn:replace(vo.text, newline, "<br/>") }
								</td>
							</tr>
						</table>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>