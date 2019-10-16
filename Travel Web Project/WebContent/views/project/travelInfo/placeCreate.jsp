<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Travel - 새 여행지</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSS호출 -->
 	<%@ include file="/views/project/cssLoader.jsp" %>
  
  
	</head>
<body>
	<%@ include file="/views/project/navbar.jsp"%>

	<div class="hero-wrap js-fullheight"
		style="background-image: url('${pageContext.request.contextPath}/images/info.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text js-fullheight align-items-center justify-content-start"
				data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left"
					data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행지등록</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="list-group panel panel-success hidden">
						<div class="panel-heading list-group-item text-center hidden-xs">
							<h4>여행정보 등록하기</h4>
						</div>
					</div>
				</div>
			</div>


				<div class="list-group panel panel-success hidden list-group-item">
					<form action="${pageContext.request.contextPath}/info/regist.do" class="bs-example form-horizontal" method="POST" enctype="multipart/form-data">
						<div class="row">
								<div class="col-sm12 col-md-3">
									<label for="#">여행지명</label>
								</div>
								<div class="col-sm12 col-md-9">
									<div class="form-group">
										<div class="form-field">
											<input type="text" name="infoName" class="form-control">
										</div>
									</div>
								</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12 col-md-3">
								<label for="#">여행지역</label>
							</div>

							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<select class="form-control" id="location" name="location">
											<option value="A">수도권</option>
											<option value="B">강원권</option>
											<option value="C">경상권</option>
											<option value="D">전라권</option>
											<option value="E">제주권</option>		
										</select>
									</div>
								</div>
							</div>
						</div>
							
							
							
						<div class="row">
							<div class="col-sm-12 col-md-3">
								<label for="#">내용</label>
							</div>

							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<textarea class="form-control" name="infoText" rows="3" id="textArea"></textarea>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
								<div class="col-sm-12 col-md-3">
									<label for="#">위도</label>
								</div>
	
								<div class="col-sm-12 col-md-9">
									<div class="form-group">
										<div class="form-field">
											<input type="text" name="latitude" class="form-control" placeholder="소숫점 포함 숫자만 입력하세요!!">
										</div>
									</div>
								</div>
						</div>
							
						<div class="row">
								<div class="col-sm-12 col-md-3">
									<label for="#">경도</label>
								</div>
	
								<div class="col-sm-12 col-md-9">
									<div class="form-group">
										<div class="form-field">
											<input type="text" name="longitude" class="form-control" placeholder="소숫점 포함 숫자만 입력하세요!!">
										</div>
									</div>
								</div>	
						</div>
						
						
						<div class="row">
							<div class="col-sm-12 col-md-3">
								<label for="#">이미지 추가</label>
							</div>

							<div class="col-sm-12 col-md-9">
								<div class="form-group">
									<div class="form-field">
										<input type="file" name="uploadFile" multiple="multiple">
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



	<!-- 하단Footer태그 -->
	<%@ include file="/views/project/footer.jsp"%>




	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>




  <!-- 자바스크립트 -->	
  <%@ include file="/views/project/jsLoader.jsp" %>

</body>

</html>