<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- //header -->
		<c:import url="/WEB-INF/views/include/nav.jsp" />
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>일반방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">일반방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<table id="guestAdd">
					<colgroup>
						<col style="width: 70px;">
						<col>
						<col style="width: 70px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th><label class="form-text" for="input-uname">이름</label>
							</td>
							<td><input id="input-uname" type="text" name="author"
								required></td>
							<th><label class="form-text" for="input-pass">패스워드</label>
							</td>
							<td><input id="input-pass" type="password" name="password"
								required></td>
						</tr>
						<tr>
							<td colspan="4"><textarea name="content" cols="72" rows="5"
									required></textarea></td>
						</tr>
						<tr class="button-area">
							<td colspan="4"><button type="button" id="insertGuestbook">등록</button></td>
						</tr>
					</tbody>

				</table>
				<!-- //guestWrite -->
				<input type="hidden" name="action" value="add">

				<div id="guestbookArea">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<c:forEach items="${guestbookList}" var="guestbook">
							<tr>
								<td>${guestbook.num}</td>
								<td>${guestbook.author}</td>
								<td>${guestbook.regDate}</td>
								<td><a
									href="../Guestbook/guestbookDeleteForm/${guestbook.num}">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${guestbook.content}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //guestRead -->

				<table class="guestRead">
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 40%;">
						<col style="width: 40%;">
						<col style="width: 10%;">
					</colgroup>
				</table>
				<!-- //guestRead -->

			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<script type="text/javascript">
		//방명록 저장 버튼 클릭했을 때~
		$("#insertGuestbook").on("click",function() {

							var author = $("[name='author']").val()
							var password = $("[name='password']").val()
							var content = $("[name='content']").val()
							//ajax 통신 ---> 요청은 같은 기술 응답 데이터만 온다.
							//$.ajax({})--> 객체 이름 안 정했을 때,$.ajax(userVO)객체를 넣을 수도 있음

							var guestbookVO = {
								author : author,
								password : password,
								content : content
							};

							$.ajax({

										//요청 세팅(보낼 때--!)
										url : "${pageContext.request.contextPath}/api/Guestbook/guestbookWrite",
										type : "post", //어차피 내부 요청이라 주소창에 안 나온다.
										//contentType : "application/json",
										//  ㄴ---> 전송하는 데이터타입 지정 지금은 파라미터로 보내는 거라 사용 X
										data : guestbookVO,
										
										
										dataType : "json",
										success : function(result) {

											render(result.data)  //html 요소 추가하는 function
											$("[name='author']").val(""); //추가 후 input 요소 비워주기
											$("[name='password']").val(""); //추가 후 input 요소 비워주기
											$("[name='content']").val(""); //추가 후 input 요소 비워주기

										},
										error : function(XHR, status, error) {
											console.error(status + " : "
													+ error);
										}

									}); //ajax end
									
									//html 요소를 처리하려고 function으로 뺐다.
								function render(guestbookVO){

									var str = "";
									str += "<table class='guestRead'>";
									str += "<colgroup>"
									str += '<col style="width: 10%;">';
									str += '<col style="width: 40%;">';
									str += '<col style="width: 40%;">';
									str += '<col style="width: 10%;">';
									str += "</colgroup>"

									str += "<tr>"
									str += "<td>" + guestbookVO.num
											+ "</td>"
									str += "<td>" + guestbookVO.author
											+ "</td>"
									str += "<td>" + guestbookVO.regDate
											+ "</td>"
									str += '<td><a href="../Guestbook/guestbookDeleteForm/${guestbook.num}">[삭제]</a></td>'
									str += "</tr>"

									str += "<tr>"
									str += "<td colspan=4 class='text-left'>"
											+ guestbookVO.content
											+ "</td>"
									str += "</tr>"
									str += "</table>"

									$("#guestbookArea").prepend(str);
										
									}	

						});//event end
	</script>

</body>

</html>