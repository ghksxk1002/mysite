<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var='count' value='${fn:length(list)}' />
					
					<c:forEach items='${list }' var='vo' varStatus='status' >
							<tr>
								<td>${count*pg-status.index }</td>
								<td style="text-align:left; padding-left:${20*vo.depthNu}px">
								<c:if test="${vo.depthNu > 0 }">
									<a
										href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}">
										<img
										src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
									</a>
								</c:if> <a href="${pageContext.servletContext.contextPath }/bd?a=view&no=${vo.no }&hit=${vo.hit }">${vo.title }
												</a>
								
								</td>
								<td>${vo.userName }</td>
								<td>${vo.hit }</td>
								<td>${vo.regDate }</td>
								<c:if test="${authUser.no == vo.userNu }">
								<td><a href="${pageContext.servletContext.contextPath }/bd?a=del&no=${vo.no }" class="del"></a></td>
								</c:if>
							</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:forEach begin="1" end="5" var="pager" step="1">
							<li><a href="${pageContext.servletContext.contextPath }/bd?pg=${pager}" >${pager }</a></li>
						</c:forEach>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/bd?a=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>