<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="UTF-8">

<head>

<title>Travel-커뮤니티</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSS호출 -->
  <%@ include file="/views/project/cssLoader.jsp" %>
</head>


<body>
<!-- 네비게이션바 -->
	<%@ include file="/views/project/navbar.jsp" %>


	<div class="hero-wrap js-fullheight"
		style="background-image: url('${pageContext.request.contextPath}/images/boardbg.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text js-fullheight align-items-center justify-content-start"
				data-scrollax-parent="true">
				<div
					class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left"
					data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-4"
						data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">커뮤니티</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">

		<div class="container">
			<div class="row">

				<div style="z-index: 1020" class="col-md-12">
					<div class="list-group panel panel-success hidden">
						<div class="panel-heading list-group-item text-center hidden-xs">
							<h4>게시판</h4>
						</div>


					</div>
				</div>

				<div class="col-md-12">

					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<colgroup>
								<col width="*" />
								<col width="*" />
								<col width="*" />
								<col width="*" />
								<col width="*" />
								<col width="*" />
							</colgroup>
							<thead>
								<tr>
									<th class="text-center">번호</th>
									<th class="text-center">카테고리</th>
									<th class="text-center">제목</th>
									<th class="text-center">작성일</th>
									<th class="text-center">작성자</th>
									<th class="text-center">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty contents}">
										<tr>
											<th colspan="6" class="text-center">게시물이 존재하지 않습니다.</th>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="content" items="${contents}">
											<tr>
												<td class="text-center">${content.contentId}</td>
												<td class="text-center">${content.category}</td>
												<td><a
													href="${pageContext.request.contextPath}/content/find.do?contentId=${content.contentId}">${content.title}</a>
												</td>
												<td class="text-center">
												<fmt:formatDate value="${content.dateCreate}" pattern="yyyy-MM-dd" /></td>
												<td class="text-center">${content.creatorName}</td>
												<td class="text-center">${content.viewCount}</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					
					
					<c:if test="${sessionScope.loginID ne null }">
						<div class="text-right">
							<a href="${pageContext.request.contextPath}/content/regist.do">
								<button type="button" class="btn btn btn-warning">작성하기</button>
							</a> <br>
						</div>
					</c:if>
					
				</div>
			</div>
		</div>
	</section>

	<br>



	<!-- 하단Footer태그 -->
    <%@ include file="/views/project/footer.jsp" %>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


  <!-- 자바스크립트 -->	
  <%@ include file="/views/project/jsLoader.jsp" %>


</body>

</html>