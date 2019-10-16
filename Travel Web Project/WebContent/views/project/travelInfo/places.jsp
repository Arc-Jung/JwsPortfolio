<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Travel - 여행지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- CSS호출 -->
<%@ include file="/views/project/cssLoader.jsp"%>


</head>
<body>
	<%@ include file="/views/project/navbar.jsp"%>


	<div class="hero-wrap js-fullheight" style="background-image: url('${pageContext.request.contextPath}/images/info.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left" data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행정보</h1>
					<p data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">각 지역별 여행정보를 확인합니다</p>
				</div>
			</div>
		</div>
	</div>


	<section class="ftco-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 sidebar order-md-last ftco-animate">
					<div class="sidebar-wrap ftco-animate">
						<h3 class="heading mb-4">여행지 검색</h3>
						<form action="${pageContext.request.contextPath}/info/search.do">
							<div class="fields">
								<div class="form-group">
									<input type="text" name="infoName" class="form-control" placeholder="여행지 입력">
								</div>
								<div class="form-group">
									<div class="select-wrap one-third">
										<div class="icon">
											<span class="ion-ios-arrow-down"></span>
										</div>
										
									</div>
								</div>
								<div class="form-group">
									<input type="submit" value="검색하기" class="btn btn-primary py-3 px-5">
								</div>
							</div>
						</form>
						<c:if test="${sessionScope.isAdmin == 1 }">
							<div class="form-group">
								<a href="${pageContext.request.contextPath}/info/regist.do" class="btn btn-primary py-3 px-5"> CREATE </a>
							</div>
						</c:if>
					</div>

				</div>
				<!-- END-->
				<div class="col-lg-9">
					<div class="row">
						<!-- 여행정보  -->
						<c:choose>
							<c:when test="${empty travelInfos}">
								<tr>
									<h2>여행정보가 없습니다</h2>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="travelInfo" items="${travelInfos}">
									<div class="col-sm col-md-6 col-lg-4 ftco-animate">
										<div class="destination">
											<a href="${pageContext.request.contextPath}/info/find.do?infoId=${travelInfo.infoId}" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url(${travelInfo.thumbImg});">
												<div class="icon d-flex justify-content-center align-items-center">
													<span class="icon-link"></span>
												</div>
											</a>
											<div class="text p-3">
												<div class="d-flex">
													<div class="one">
														<h3>
															<a href="${pageContext.request.contextPath}/info/find.do?infoId=${travelInfo.infoId}">${travelInfo.infoName}</a>
														</h3>
													</div>
												</div>
												<p>추천&nbsp;${travelInfo.voteCount}</p>
												<hr>
												<p class="bottom-area d-flex">
													<span><i class="icon-map-o"></i>&nbsp;${travelInfo.location}</span>
													<span class="ml-auto"><a href="#">정보보기</a></span>
													<c:if test="${sessionScope.isAdmin == 1 }">
														<span class="ml-auto"><a href="${pageContext.request.contextPath}/info/remove.do?infoId=${travelInfo.infoId}">삭제하기</a></span>
													</c:if>
												</p>
											</div>
											<!-- 여행정보  end -->
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<script>
				function fn_prev(page, range, rangeSize) {
					var page = ((range - 2) * rangeSize) + 1;
					var range = range - 1;

					var url = "${pageContext.request.contextPath}/info/main.do";
					url = url + "?page=" + page;
					url = url + "&range=" + range;
					location.href = url;

				}

				//페이지 번호 클릭

				function fn_pagination(page, range, rangeSize, searchType,
						keyword) {

					var url = "${pageContext.request.contextPath}/info/main.do";
					url = url + "?page=" + page;
					url = url + "&range=" + range;
					location.href = url;

					var url = "${pageContext.request.contextPath}/info/search.do";
					url = url + "?infoName=" + infoName;
					url = url + "&page=" + page;
					url = url + "&range=" + range;
					location.href = url;

				}

				//다음 버튼 이벤트

				function fn_next(page, range, rangeSize) {
					var page = parseInt((range * rangeSize)) + 1;
					var range = parseInt(range) + 1;

					var url = "${pageContext.request.contextPath}/info/main.do";
					url = url + "?page=" + page;
					url = url + "&range=" + range;
					location.href = url;

				}
			</script>
			<div class="row mt-5">
				<div class="col text-center">
					<div class="block-27">
						<ul>
							<c:if test="${pagination.prev}">
								<li><a href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">&lt;</a></li>
							</c:if>
							<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
								<li class="<c:out value="${pagination.page == idx ? 'active' : ''}"/>"><a href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></li>
							</c:forEach>
							<c:if test="${pagination.next}">
								<li><a href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')">&gt;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			<!-- .col-md-8 -->
		</div>
	</section>

	<!-- Footer -->
	<%@ include file="/views/project/footer.jsp"%>


	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>

	<!-- 자바스크립트 -->
	<%@ include file="/views/project/jsLoader.jsp"%>


</body>
</html>