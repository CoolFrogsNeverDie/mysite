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
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
 	<script type="text/javascript" src = "${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<c:import url="/WEB-INF/views/include/nav.jsp" />
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>회원가입</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>회원</li>
						<li class="last">회원가입</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="user">
				<div id="joinForm">
					<form action="${pageContext.request.contextPath}/user/join"
						method="GET">

						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> <input
								type="text" id="input-uid" name="id" value=""
								placeholder="아이디를 입력하세요" required>
							<button type="button" id="btnIdCheck">중복체크</button>
							<p id="idCheckMsg" style="text-align: center"></p>
						</div>

						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> <input
								type="password" id="input-pass" name="password" required
								value="" placeholder="비밀번호를 입력하세요" required>
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> <input
								type="text" id="input-name" name="name" value=""
								placeholder="이름을 입력하세요" required>
						</div>

						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span> <label for="rdo-male">남</label>
							<input type="radio" id="rdo-male" name="gender" value="male"
								required> <label for="rdo-female">여</label> <input
								type="radio" id="rdo-female" name="gender" value="female"
								required>

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> <input type="checkbox"
								id="chk-agree" value="" name="" required> <label
								for="chk-agree">서비스 약관에 동의합니다.</label>
						</div>

						<!-- 버튼영역 -->
						<div class="button-area">
							<button type="submit" id="btn-submit">회원가입</button>
						</div>

					</form>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->


	<script type="text/javascript">
	
	
	

	   $("#btnIdCheck").on("click", function(){
	         
	      
	         var id = $("[name=id]").val()
	         //서버와 통신
	         //$.ajax({})--> 객체 이름 안 정했을 때,$.ajax(userVO)객체를 넣을 수도 있음
	            $.ajax({
	               
	               //요청 세팅
	               url : "${pageContext.request.contextPath}/user/checkId",      
	               type : "post", //어차피 내부 요청이라 주소창에 안 나온다.
	               //contentType : "application/json",  ---> 전송하는 데이터타입 지정
	               data : {id: id},
	               
	               //응답 관련 세팅
	               dataType : "json",
	               success : function(jsonResult){
	               /*성공시 처리해야될 코드 작성*/
	               
	               console.log(jsonResult);
	               
	               
	               if(jsonResult.result == 'success'){ //통신 처리 성공
	            	   //사용 가능한지 불가능한지 표현한다.
	            	   
	            	   if(jsonResult.data == true){  
					   
	           		   	$("#idCheckMsg").css("color", 'blue');
		               	$("#idCheckMsg").html("<b>사용 가능한 아이디입니다.</b>")
	            		   
	            	   }else{ 
	           		   	$("#idCheckMsg").css("color", 'red');
		               	$("#idCheckMsg").html("<b>이미 사용중인 아이디입니다.</b>")
	            	   }//inner if end
	               }else{  
							//통신 실패 -- 메세지 출력
							var msg = jsonResult.failMsg;
							alert(msg);
	               }//outer if end 
	               },
	               error : function(XHR, status, error) {
	               console.error(status + " : " + error);
	               }
	            });

	         
	   });

	</script>

			


</body>

</html>