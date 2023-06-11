<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<!-- 부트스트랩 css -->	
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel = "stylesheet" type="text/css">
<!-- jquery -->	
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<!-- 부트스트랩 js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
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
				

		<c:forEach items="${guestbookList}" var="guestbook">
					<table id="t${guestbook.num}" class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
							<tr>
								<td>${guestbook.num}</td>
								<td>${guestbook.author}</td>
								<td>${guestbook.regDate}</td>
								<td><button type="button" class="btn btn-danger btn-sm btnModal" data-num ="${guestbook.num}">삭제</button></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${guestbook.content}</td>
							</tr>
					</table>
						</c:forEach>

				
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">삭제 모달</h4>
      </div>
      <div class="modal-body">
        삭제를 원하시면 비밀번호를 입력해주세요.<br><br>
       password: <input type = "password"  id ="modalPassword"/><br><!-- 입력 비밀번호 -->
       <input type = "text" id ="modalNum" ><!-- 특정할 게스트보드 넘버 -->
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger btn-sm" id = "modalDelete">Delete</button>
      </div>
    </div>
  </div>
</div>


<!-- 삭제폼 모달창 ------------------------------------------------------------------------------------------------->


</body>

	<script type="text/javascript">
		
	

	
	//삭제 모달창 호출 버튼 ---> 모달창 뜸!
	
	$("#guestbookArea").on("click", ".btnModal" , function(){  

		
		//초기화
		$("#modalPassword").val("");
		$("#modalNum").val("");

		
		var del_num = $(this).data("num"); //정확한 타게팅을 위해 this를 사용
		//== 삭제 클릭된 게시물의 data 이름이 num 인 data를 가져옴
		console.log(del_num)
		//방명록 글번호를 input창에~~ 꺼내쓰기 이거 꺼내려고 삭제 버튼 태그에 data-num이란 이름으로 추가해서 받아옴
		//data("num")
		$("#modalNum").val(del_num);
		//모달창 호출
		$("#myModal").modal('show');
		
	}); //modal open btn 
	

	
	//모달에서 삭제 버튼 클릭하면?
	$("#modalDelete").on("click", function(){
		
		
		var password  = $("#modalPassword").val()
		var num  = $("#modalNum").val()
		
		console.log(password);
		console.log(num);
		
		var guestbookVO = {
				password : password,
				num : num
			};

			$.ajax({

						//요청 세팅(보낼 때--!)
						url : "${pageContext.request.contextPath}/api/Guestbook/guestbookDelete",
						type : "post", //어차피 내부 요청이라 주소창에 안 나온다.
						//contentType : "application/json",
						//  ㄴ---> 전송하는 데이터타입 지정 지금은 파라미터로 보내는 거라 사용 X
						data : guestbookVO,
						
						dataType : "json",
						success : function(result) {
							
							if(result.data > 0){  
								$("#t"+num).remove()
								$('#myModal').modal('hide')//myModal 닫아주기
								Swal.fire({
									  position: 'center',
									  icon: 'success',
									  title: '삭제 성공',
									  showConfirmButton: false,
									  timer: 3000
									})
							}else{ 
								Swal.fire({
									  icon: 'error',
									  title: 'Oops...',
									  text: '비밀번호가 맞지 않습니다!',
									})
									
									$("#modalPassword").val("");
							}

						},
						error : function(XHR, status, error) {
							console.error(status + " : "
									+ error);
						}

					}); //ajax end
			
					
	});  //modal delete button end
	
	
	

	
	
	
	
	
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
		});//event end		
									
									
									
									//html 요소를 처리하려고 function으로 뺐다.
								function render(guestbookVO){

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

									$("#guestbookArea").prepend(str);
										
									}	

	
						

						
	</script>
</html>