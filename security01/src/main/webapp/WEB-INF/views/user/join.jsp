<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf_parameter" content="${_csrf.parameterName}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf" content="${_csrf.token}" />
<title>JBlog</title>
<Link rel="stylesheet" href="/assets/css/jblog.css">
<!-- 제이쿼리 함수 소환 -->
<script src="/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        if(token && header) {
            xhr.setRequestHeader(header, token);
        }
    });
});

$(function(){
	// 아이디 중복 확인 이벤트
	$("#btn-check-id").click(function() {
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
           
	
		id = $("#userid").val();
		if(id == '') {
			return;
		}
	
		
		$.ajax({
			url: "/user/api/checkemail?id="+id,
			/* data : {"id": id}, */
			type: "post",
			dataType: "json",
			contentType : 'application/json',
			error:function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response) {
				console.log(response);
				if(response.result !== "success"){
					console.error(response.message);
					return;
				}
				if(response.data) {
					alert("존재하는 아이디입니다. 다른 아이디을 사용하세요.");
					$("#userid")
						.val("")
						.focus();
					return;
				}
				
				$("#btn-check-id").hide();
				$("#img-check-id").show();
			}
		});		
	});	
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form class="join-form" id="join-form" method="post" action="/join">
			<label class="block-label" for="id">아이디</label>
			<input id="userid" name="userid" type="text" value="" placeholder="아이디를 입력해주세요">

			<input id="btn-check-id" type="button" value="중복체크">
			<img id="img-check-id" style="display: none;" src="/assets/images/check.png">
			
			<label class="block-label" for="name">이름</label>
			<input id="name" name="username" type="text" value="" placeholder="이름을 입력해주세요">
			
			<label class="block-label" for="blog-id">이메일</label>
			<input id="useremail" name="useremail" type="text" placeholder="이메일을 입력해주세요"> 
			
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" placeholder="비밀번호를 입력해주세요"/>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<input type="submit" value="가입하기" />
		</form>
		
	</div>
<!-- 	<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
			<p class="validateTips normal">해당 카테고리의 포스트가 모두 삭제될수 있습니다.</p>
			<input type="hidden" id="hidden-no" value=""> 
		</div>
		<div id="dialog-message" title="새글작성" style="display: none">
			<p></p>
	</div> -->
</body>
</html>
