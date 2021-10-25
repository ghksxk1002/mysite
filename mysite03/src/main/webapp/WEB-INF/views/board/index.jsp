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
				<form id="search_form" action="${pageContext.servletContext.contextPath }/search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호 ${map.nowPage }</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var='count' value='${map.count}' />
					<c:forEach items='${map.list }' var='vo' varStatus='status' >
							<tr>
								<td>${(count-status.index)-(map.nowPage-1)*5 }</td>
								<td style="text-align:left; padding-left:${20*vo.depth}px">
								<c:if test="${vo.depth > 0 }">
									<a href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}">
										<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
									</a>
								</c:if> <a href="${pageContext.servletContext.contextPath }/board/view/${vo.no }/${vo.hit }">${vo.title } 
												</a>
								
								</td>
								<td>${vo.userName }</td>
								<td>${vo.hit }</td>
								<td>${vo.regDate }</td>
								<c:if test="${authUser.no == vo.userNo }">
								<td><a href="${pageContext.servletContext.contextPath }/board/delete${vo.no }" class="delete"></a></td>
								</c:if>
							</tr>
					</c:forEach>
				</table>
				
				
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${map.nowPage != 1 }">
							<li><a href="${pageContext.servletContext.contextPath }/board/${map.nowPage-1}">◀</a></li>
						</c:if>
							<c:forEach begin="${map.start }" end="${map.end }" var="pager" step="1">
								<c:choose>
									<c:when test="${map.nowPage == pager }" >
										<li class="selected">${pager }</li>
									</c:when>
										<c:when test="${pager <= map.lastPage}">
											<li><a href="${pageContext.servletContext.contextPath }/board?pager=${pager}" >${pager }</a></li>						
										</c:when>
									<c:otherwise>
										<li>${pager }</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:if test="${map.nowPage < map.lastPage }">
							<li><a href="${pageContext.servletContext.contextPath }/board?pager=${map.nowPage+1}">▶</a></li>
						</c:if>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
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