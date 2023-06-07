<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<!-- 부트스트랩 css -->
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<!-- 부트스트랩 js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
<!-- 모달 팝업 전용  -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>



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
				<li>뱡뱡</li>
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
							<td><input id="input-uname" type="text" name="author"
								required></td>
							<th><label class="form-text" for="input-pass">패스워드</label>
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

					<!-- 방명록 리스트 출력 영역 -->

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


	<!-- 삭제폼 모달창 ------------------------------------------------------------------------------------------------->


	<p>모달창 위치</p>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">삭제 모달</h4>
				</div>
				<div class="modal-body">
					삭제를 원하시면 비밀번호를 입력해주세요.<br>
					<br> password: <input type="password" id="modalPassword" /><br>
					<!-- 입력 비밀번호 -->
					<input type="text" id="modalNum" type = "hidden" />
					<!-- 특정할 게스트보드 넘버 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-danger btn-sm"
						id="modalDelete">Delete</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 삭제폼 모달창 ------------------------------------------------------------------------------------------------->


</body>

<script type="text/javascript">

$(document).ready(function(){
	fetchList();

})

	
	//방명록 저장 버튼 클릭했을 때~
		$("#insertGuestbook").on("click",function() {
			
			
			var author = $("[name='author']").val()
			var password = $("[name='password']").val()
			var content = $("[name='content']").val()
			//ajax 통신 ---> 요청은 같은 기술 응답 데이터만 온다.
			//$.ajax({})--> 객체 이름 안 정했을 때,$.ajax(userVO)객체를 넣을 수도 있음

			var guestbookVO = {
				author : author, //이건 명령어고, json으로 하는 것과는 다르다. 
				password : password,
				content : content
			};  // 컨버트
				
			$.ajax({
				//요청 세팅(보낼 때--!)
				url : "${pageContext.request.contextPath}/api/Guestbook/guestbookWrite2",
				type : "post", //어차피 내부 요청이라 주소창에 안 나온다.
				contentType : "application/json",
				//  ㄴ---> 전송하는 데이터타입 지정 지금은 파라미터로 보내는 거라 사용 X
				data : JSON.stringify(guestbookVO), //json형식으로 변환해서 보냄
				
				
				dataType : "json",
				success : function(result) {

					render(result.data, "up")  //html 요소 추가하는 function
					$("[name='author']").val(""); //추가 후 input 요소 비워주기
					$("[name='password']").val(""); //추가 후 input 요소 비워주기
					$("[name='content']").val(""); //추가 후 input 요소 비워주기

				},
				error : function(XHR, status, error) {
					console.error(status + " : "
							+ error);
				}

			}); //ajax end

				
		});//event end		



					
	function fetchList(){  
		$.ajax({

			//요청 세팅(보낼 때--!)
			url : "${pageContext.request.contextPath}/api/Guestbook/list",
			type : "post",
			//어차피 내부 요청이라 주소창에 안 나온다.

			dataType : "json",
			success : function(result) {
				
				var guestList = result.data;
				
				for(var i =0; i<guestList.length; i++){
					render(guestList[i], "down")											
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		}); //ajax end			
						
						
					}
					
					
					//방명록 리스트 그리기
					function render(guestbookVO,position){

						var str = "";
						str += "<table id = 't"+ guestbookVO.num+"' class='guestRead'>";
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
						str += '<td><button type="button" class="btn btn-danger btn-sm btnModal" data-num ="' + guestbookVO.num + '">삭제</button></td>'
						str += "</tr>"

						str += "<tr>"
						str += "<td colspan=4 class='text-left'>"
								+ guestbookVO.content
								+ "</td>"
						str += "</tr>"
						str += "</table>"

						if(position == 'up'){
						$("#guestbookArea").prepend(str);							
						}else if (position == 'down'){  
						$("#guestbookArea").append(str);							
						}else{
							console.log('오류임!')
						}
						
							
						}	
					
					
</script>
</html>