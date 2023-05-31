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
<link href="${pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">

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
					<form action="${pageContext.request.contextPath}/board/search">
						<div class="form-group text-right">
							<input type="text" name="keyword">
							<button type="submit" id=btn_search>검색</button>
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
							<c:forEach items="${boardList}" var="board">
								<tr>
									<td>${board.no}</td>
									<td class="text-left"><a
										href="${pageContext.request.contextPath}/board/readBoard/${board.no}">${board.title}</a></td>
									<td>${board.name}</td>
									<td>${board.hit}</td>
									<td>${board.regDate}</td>
									<c:if test="${sessionScope.authUser.no == board.userNo}">
										<td><a
											href="${pageContext.request.contextPath}/board/delete?no=${board.no}&userNo=${board.userNo}">[삭제]</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>



					<div id="paging">
						<ul>

							<li><c:if test="${paging.startPageNum != 1 }">
									<!-- 시작 페이지가 1이 아니면 -->
									<a href="${pageContext.request.contextPath}/board/list/${paging.startPageNum-1}">◀</a>
								</c:if> 
								<!-- 보여질 시작페이지값부터 끝페이지 값까지 'p'로 반복하게 -->
								<c:forEach begin="${paging.startPageNum}" end="${paging.endPageNum}" var="p">
									<c:choose>
										<c:when test="${p == paging.selectPage}">
											<li><b>${p }</b></li>
										</c:when>
										<c:when test="${p != paging.selectPage}">
											<li>
												<a href="${pageContext.request.contextPath}/board/list/${p}">${p}</a>
											</li>
										</c:when>
									</c:choose>
								</c:forEach></li>

					<c:if test="${paging.endPageNum != paging.finalPage}">
						<li>
							<a href="${pageContext.request.contextPath}/board/list/${paging.endPageNum+1 }">▶</a>
						</li>
					</c:if>
						</ul>


						<div class="clear"></div>
					</div>
					<c:if test="${authUser != null}">
						<a id="btn_write"
							href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
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