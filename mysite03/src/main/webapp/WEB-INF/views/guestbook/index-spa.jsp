<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<script>
	// 객체로 만들어서 필요할때마다 르기
	// 여러 리스트를 자기고 오기위한 템플릿
	var listEJS = new EJS({
		url : '${pageContext.request.contextPath}/ejs/list-temlate.ejs'
	});

	var listItemEJS = new EJS({
		url : '${pageContext.request.contextPath}/ejs/listItem-temlate.ejs'
	});

	var messageBox = function(title, message, callback) {

		$('#dialog-message').attr('title', title);
		$('#dialog-message p').text(message);

		$('#dialog-message').dialog({
			modal : true,
			buttons : {
				"환인" : function() {
					$(this).dialog("close");
				}

			},
			close : callback
		});
	}

	var startNo;
	// 글 리스트 불러오기
	var fetch = function() {
		var url = '${pageContext.request.contextPath}/api/gb/list'
				+ (startNo ? ('?sn=' + startNo) : '')
		console.log(startNo)
		$.ajax({
			url : url,
			dataType : 'json',
			type : 'get',
			success : function(response) {

				// 템플릿 안에서 response의 속성들에 접근할수 있따
				var html = listEJS.render(response);
				$("#list-guestbook").append(html);

				startNo = $("#list-guestbook li").last().data('no') || 0;
				//console.log(response.date.length-1);
			}

		})
	};

	$(function() {

		// 삭제 다이알로그 객체 만들기
		var dialogDelete = $('#dialog-delete-form')
				.dialog(
						{
							autoOpen : false, // 바로 안뜨게 만들기
							modal : true,
							buttons : {
								"삭제" : function() {
									// 삭제 ajax 실행
									var no = $('#hidden-no').val();
									var password = $('#password-delete').val();
									// 때ㅔ려야될 url
									var url = '${pageContext.request.contextPath}/api/gb/delete/'
											+ no;

									$
											.ajax({
												url : url,
												type : 'post',
												dataType : 'json',
												data : 'password=' + password,
												success : function(response) {
													console.log(response)

													if (response.data == -1) {
														$('.validateTips.error')
																.show();
														$('#password-delete')
																.val('')
																.focus();
														return;
													}
													$(
															'#list-guestbook li[data-no='
																	+ response.data
																	+ ']')
															.remove();
													$('#password-delete').val(
															'');
													$(".validateTips.error")
															.hide();
													dialogDelete
															.dialog('close');
												}

											});
								},
								"취소" : function() {
									$('#password-delete').val('');
									$(".validateTips.error").hide();
									$(this).dialog("close");
								}
							}
						});

		// dialogDelete.dialog('open');

		// 글삭제 버튼
		$(document).on('click', '#list-guestbook li a', function(event) {
			event.preventDefault();

			var no = $(this).data('no');
			console.log(no);
			$("#hidden-no").val(no)

			dialogDelete.dialog('open');
		});

		// form valiation
		$("#add-form").submit(function(event) {
			// 이름
			event.preventDefault();
			var vo = {};

			vo.name = $('#input-name').val();
			if (!vo.name) {
				messageBox('새글작성', '이름은 반드시 입력해야 합니다', function() {
					$('#input-name').focus();
				});
				return;
			}
			vo.password = $('#input-password').val();
			if (!vo.password) {
				messageBox('새글작성', '비밀번호는 반드시 입력해야 합니다', function() {
					$('#input-password').focus();
				});
				return;
			}
			// 내용
			vo.message = $("#tx-content").val();
			if (!vo.message) {
				messageBox('새글작성', '메세지는 반드시 입력해야 합니다', function() {
					$('#tx-content').focus();
				});
				return;
			}

			$.ajax({
				url : '${pageContext.request.contextPath}/api/gb/add',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(vo),
				success : function(response) {

					if (response.result !== 'success') { // 서버측 에러 내용
						console.log(response.message);
						return;
					}

					// var html = render(response.data);
					var html = listItemEJS.render(response.data);
					$('#list-guestbook').prepend(html);
					$("#add-form")[0].reset();

				},
				error : function(xhr, code, message) { // 통신 에러
					console.error(status + ":" + error);
				}
			});

			console.log("ajax submit");
		});

		// 최초 글 불러오기
		fetch();

		$(window).scroll(function() {
			var $window = $(this);
			var $document = $(document);

			var windowHeight = $window.height();
			var documentHeight = $document.height();
			var scrollTop = $window.scrollTop();

			if (scrollTop + windowHeight + 10 > documentHeight) {
				console.log(startNo)
				fetch();
			}
		})
	});
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> <input
						type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value=""
						class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="새글작성" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>