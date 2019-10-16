<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="UTF-8">

<head>


<!-- 여기까지 -->
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
			<div class="list-group panel panel-success hidden">
	              <div class="panel-heading list-group-item text-center hidden-xs">
						<h3>게시글 수정하기</h3>
	              </div>
	        </div>

			<div class="list-group panel panel-success hidden list-group-item">
					<form action="${pageContext.request.contextPath}/content/modify.do" class="bs-example form-horizontal" method="POST">
						
						<div class="row">	
							<div class="col-sm-12 col-md-3">
								<label for="#">카테고리 변경</label>
							</div>
							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<select class="form-control" name="category" id="category">
											<option value="A" id="A">여행후기 & 팁</option>
											<option value="B" id="B">여행지 Q&A</option>
											<option value="C" id="C">자유게시판</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">	
							<div class="col-sm-12 col-md-3">
								<label for="#">게시글 제목</label>
							</div>

							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<input type="text" name="title" class="form-control" value="${content.title}">
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12 col-md-3">
								<label for="#">게시글 내용</label>
							</div>
							
							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<textarea class="form-control" name="contentText" rows="10" id="textArea">${content.contentText}</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 align-self-end">
								<div class="form-group">
									<div class="form-field">
											<button type="submit" class="form-control btn btn-primary">확인</button>
											<br><br>
											<div class="text-right">
												<button type="reset" class="btn btn-danger pull-right">취소하기</button>
											</div>
										</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
	</section>

	<br>


	<!-- 하단Footer -->
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
  
  <!-- 게시글 이동에 대한 기본값 JQUERY -->
  <script>
  $(document).ready(function() {
	  $("#category").val("${content.category}").attr("selected", "selected");
	});
 
  </script>
</body>

</html>