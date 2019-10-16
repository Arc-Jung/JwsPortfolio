<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Travel - 여행지정보</title>
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
					<h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">
						${travelInfo.infoName}
					</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section ftco-degree-bg">
		<div class="container">
			<div class="row">
					
					<!-- 제목 출력 부분 -->
						<div class="col-sm-12 col-md-6 hotel-single mt-4 mb-5 ftco-animate">
							<h1>${travelInfo.infoName}</h1>
							<p class="rate mb-5">
								<span class="loc"><i class="icon-map"></i> ${travelInfo.location}</span>
							</p>
							<p>${travelInfo.infoText}</p>
						</div>
					
					
					<!-- 이미지 출력 부분 캐러셀-->
						<div class="col-sm-12 col-md-6 ftco-animate">
							<div class="single-slider owl-carousel">
								<c:choose>
									<c:when test="${empty infoFiles}">
										<tr>이미지 없음</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="infoFile" items="${infoFiles}">
											<div class="item">
												<div class="hotel-img" style="background-image: url(${infoFile.filePath});"></div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
							<br><br>
						</div>
						
				</div>
						
						
					<div class="row">
						<div class="col-sm-12 col-md-4 hotel-single ftco-animate mb-5 mt-4">
							<h3 style="color:#6c757d;"><span class="icon-thumbs-o-up"></span>&nbsp;&nbsp;현재 여행정보를 추천</h3>
							<br>
							<div class="form-group">
							    <div class="form-field">
									<input type="button" id="like" name="like" value="추천하기" class="form-control btn btn-primary">
								</div>
							</div>
							<div class="text-center">
								<div>현재추천 수 &nbsp;<b id="voteCount"></b>개</div>
							</div>
						</div>
						
						<div class="col-sm-12 col-md-8">
							<div id="map"></div>
						</div>
						<br><br>
						
						
						<!-- 같은지역 다른 여행지정보출력 -->
						<div class="col-md-12 hotel-single ftco-animate mb-5 mt-5">
							<h3 style="color:#6c757d;"><span class="icon-map-marker"></span>&nbsp;&nbsp;같은 지역 다른 여행지</h3>
							<div class="row">
								<c:forEach var="info" items="${infos }">
					    			<div class="col-sm col-md-6 col-lg ftco-animate">
					    				<div class="destination">
					    				
					    					<!-- 썸네일 이미지 출력하는 부분 -->
					    					<a href="${pageContext.request.contextPath}/info/find.do?infoId=${info.infoId}" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url(${info.thumbImg});">
					    						<div class="icon d-flex justify-content-center align-items-center">
					    							<span class="icon-link"></span>
					    						</div>
					    					</a>
					    					
					    					
					    					<div class="text p-4">
					    						<div class="d-flex">
				
					    							<div class="one">
							    						<h3><a href="${pageContext.request.contextPath}/info/find.do?infoId=${info.infoId}">${info.infoName}</a></h3>
							    						
						    						</div>
						    						<div class="two">
						    							<span class="price">추천 ${info.voteCount}</span>
					    							</div>
					    						</div>
					               				 <hr>
					    						<p class="bottom-area d-flex">
					    							<span><i class="icon-map-o"></i>&nbsp; ${info.location}</span>
					    							<span class="ml-auto"><a href="${pageContext.request.contextPath}/info/find.do?infoId=${info.infoId}">보러가기</a></span>
					    						</p>
					    					</div>
					    				</div>
					    			</div>
				    			</c:forEach>
							</div>
						</div>



					</div>
				</div>
	</section>
	<!-- .section -->


	<%@ include file="/views/project/footer.jsp"%>
	
	
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<!-- 자바스크립트 -->
	<%@ include file="/views/project/jsLoader.jsp"%>
	
	<script>
	$(function(){
		  var count=${travelInfo.voteCount };
		  
		  $("#voteCount").html(count); 
		  
	 	  $('#like').click(function(){
			  var infoID = ${travelInfo.infoId};
	 		  
	 		  
	 		 $.ajax({
	 	   		  type : 'GET',
	 	   		  url : "${pageContext.request.contextPath}/info/like.do",
	 	   		  crossOrigin : false,
	 	   		  data : {infoID: infoID},
	 	   		  dataType: "json",
	 	   	      contentType : "application/json; charset=UTF-8",
	 	   		  success : function(data){
	 	   			  if(data==true){
	 	   				swal.fire({
	 	   						position: 'top-end',
	 	   					  	type: 'success',
	 	   				  		title: '추천되었습니다!',
	 	   				  		showConfirmButton: false,
	 	   				  		timer: 2000});
	 	   				count++;
	 	   				$("#voteCount").html(count); 

	 	   			  }
	 	   			  else{
	 	   				swal.fire({
	 	   					position: 'top-end',
 	   					  	type: 'error',
 	   				  		title: '에러가 발생하였습니다!',
 	   				  		showConfirmButton: false,
 	   				  		timer: 2000});
	 	   			  }
	 	   		}		  
	 	   	  });
	 		    
	 	  });

	  });
	</script>
	
	<script>

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		    mapOption = { 
		        center: new daum.maps.LatLng(parseFloat( ${travelInfo.latitude}), parseFloat(${travelInfo.longitude})), // 지도의 중심좌표
		        level: 4 // 지도의 확대 레벨
		    };
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var markers = [];
		
		addMarker(new daum.maps.LatLng(parseFloat( ${travelInfo.latitude}), parseFloat(${travelInfo.longitude})));
				
				// 마커를 생성하고 지도위에 표시하는 함수입니다
				function addMarker(position) {
				    
				    // 마커를 생성합니다
				    var marker = new daum.maps.Marker({
				        position: position
				    });
				
				    // 마커가 지도 위에 표시되도록 설정합니다
				    marker.setMap(map);
				}
				
		
		</script>


</body>
</html>