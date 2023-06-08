<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel = "stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">
	
		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/include/nav.jsp" />
		<!-- //nav -->

		<!-- aside -->
		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
				
					<!-- 이미지 업로드 (로그인 안 되어 있을 시 노출 X) -->
						<c:if test ="${authUser != null}">
					<button id="btnImgUpload">이미지올리기</button>
						</c:if>
					<!-- //이미지 업로드 (로그인 안 되어 있을 시 노출 X) -->
					
					<div class="clear"></div>


					<ul id="viewArea">

						<!-- 이미지반복영역 -->
						<c:forEach items ="${list}" var = "gallery" >
						<li>
							<div class="view" data-img ="${gallery.saveName}" data-writernum ="${gallery.userNum}" data-user="${authUser.no}" data-postnum ="${gallery.no}" id ="p${gallery.no}">							
								<img class ="imgItem" src="${pageContext.request.contextPath}/upload/${gallery.saveName}" name = "fileName">
								<div class="imgWriter">
									작성자: <strong>${gallery.userName}</strong>
								</div>
							</div>
						</li>
						</c:forEach>
						<!-- 이미지반복영역 -->


					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form action="${pageContext.request.contextPath }/gallery/upload" method="POST" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="uploadPicture" value="">
						</div>
					</div>
					<div class="modal-footer">
					<input type="text" value = "${authUser.no}" name ="userNum">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<input type ="hidden" id = "del_num">
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	
	//이미지 올리기 모달 버튼 클릭 시  ---> 해당 기능은 form 으로 처리
	$('#btnImgUpload').on("click", function(){
		//입력창 초기화
		$('#addModalContent').val("")
		$('#file').val("")
		//이미지 올리기 모달 show
		$('#addModal').modal('show')
	
	})
	
	
	//------------------------------------------------------------------
	
	
	//사진 보기 모달 버튼 클릭 시
	$( ".view").on("click", function(){ 
		  var saveName = $(this).data("img"); // saveName 가져오기 --> 주소 불러오려고
		  var writerNum = $(this).data("writernum"); // 작성자 번호 가져오기 --> 삭제 시 대조하려고.
		  var postNum = $(this).data("postnum"); // 게시물 번호 가져오기 --> 삭제할 때 쓰려고
		  var LoginUNum = $(this).data("user"); // 로그인한 유저 넘버
		  
		  $("#del_num").val(postNum); //삭제용 포스트 넘버 넘김
		  
		  if(writerNum != LoginUNum){ 
			$("#btnDel").css("display", "none");
		  }
		  
		  //test
		console.log(saveName)
		console.log(writerNum)
		console.log(postNum)
		
		// 이미지 주소
		var url = "${pageContext.request.contextPath}/upload/" + saveName
		
		
		$('#viewModelImg').attr("src",url) //주소 속성 추가
		$('#viewModal').modal('show') //사진 보기 모달 show
		
	});
	
	
	//--------------------------------------------------------------------
	
	//사진 보기 모달 버튼에서 [삭제] 버튼 클릭 시	
	$('#btnDel').on("click", function(){ 
		$("#btnDel").css("display", "block");
		console.log('postnum 확인용' + $(this).data("postnum"))
		
		var no = $("#del_num").val();
		
		var uploadFileVO = {  
			no : no
		};
		
		console.log(uploadFileVO)
		
		$.ajax({
			//요청 세팅(보낼 때--!)
			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post", //어차피 내부 요청이라 주소창에 안 나온다.
			//  ㄴ---> 전송하는 데이터타입 지정 지금은 파라미터로 보내는 거라 사용 X
			data : uploadFileVO, //json형식으로 변환해서 보냄
			
			
			dataType : "json",
			success : function(result) {
				console.log('삭제하고 왔습니다.' + result.data)
				$('#viewModal').modal('hide')
				$('#p'+no).remove();
				$("#del_num").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : "
						+ error);
			}
		}); //ajax end	
	});//event end
	

</script>




</html>

