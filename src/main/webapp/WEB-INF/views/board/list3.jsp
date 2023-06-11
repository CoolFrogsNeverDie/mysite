<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<c:import url="/WEB-INF/views/include/nav.jsp" />
		<!-- //nav -->
		<c:import url="/WEB-INF/views/include/aside.jsp" />
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath}/board/listPaging">
						<div class="form-group text-right">
							<input type="text" name="keyword" value ="${pMap.keyword}">
							<button type="submit" id=btn_search>검색</button>
							<select name="selector">
								<option value="5">5개</option>
								<option value="10">10개</option>
								<option value="15">15개</option>
							</select>
						</div>
					</form>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pMap.boardList}" var="board">
								<tr>
									<td>${board.no}</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/readBoard/${board.no}">${board.title}</a></td>
									<td>${board.name}</td>
									<td>${board.hit}</td>
									<td>${board.regDate}</td>
									<c:if test="${sessionScope.authUser.no == board.userNo}">
										<td><a href="${pageContext.request.contextPath}/board/delete?no=${board.no}&userNo=${board.userNo}">[삭제]</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>



					<div id="paging">
						<ul>
							<c:if test="${pMap.prev == true}">
								<li><a href="${pageContext.request.contextPath}/board/listPaging?page=${pMap.StartPageBtnNo-1}">◀</a></li>
							</c:if>

							<c:forEach begin ="${pMap.StartPageBtnNo}" end = "${pMap.endPageBtnNo}" step = "1" var = "page">
							<li><a href="${pageContext.request.contextPath}/board/listPaging?page=${page}&keyword=${pMap.keyword}">${page}</a></li>
							</c:forEach>

							<c:if test="${pMap.next == true}">
								<li><a href="${pageContext.request.contextPath}/board/listPaging?page=${pMap.endPageBtnNo+1}">▶</a></li>
							</c:if>
						</ul>
						<div class="clear"></div>
					</div>




					<div class="clear"></div>
				</div>
				<c:if test="${authUser != null}">
					<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
				</c:if>
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

</body>

</html>