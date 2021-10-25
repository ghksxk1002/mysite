<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<!-- 제이쿼리 함수 소환 -->
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	/* setTimeout(function() {
	
	 $.ajax({
	 url:"${pageContext.request.contextPath }/msg02", 
	 type:"get", 
	 dataType:"json", 
	 success:function(response){
	 console.log(response);
	 p = $("#test");
	 p.html("<strong>"+ response.message +"</strong>");
	 }
	 });
	 }, 3000);
	 */

	// jquery로 중복체크 만들기
	// check 하는 id를 가진 id를 찾는다
	// 브라우저 돔이 바디를 다 읽고 로딩이 끝나면
	// id를 찾는다
	/*  window.onload=function(){
	 console.log("loaded");

	 btnCheckEmail = $("#check")
	 btnCheckEmail.click(function() {
	 console.log("click");
	 });
	 };
	 */
	// jquery로 중복체크 만들기
	$(function() {
		$("#check").click(function() {
			// 값을 빼올때 쓰는 val() -> email이 블록 안에서만 쓰게 할수 있따.. 변수의 번위를 지정할수 있다
			var email = $("#email").val();
			console.log(email);
			if (email == '') {
				return;
			}
			console.log(email);
			$.ajax({
				url : "${pageContext.request.contextPath }/user/checkemail?email="+email,
				type:"get",
				dataType:"json",
				successs:function(response) {
					console.log(response)
				}
			})
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value=""> <input id="check"
						type="button" value="중복체크"> <label class="block-label">패스워드</label>
					<input name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<p id="test"></p>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>