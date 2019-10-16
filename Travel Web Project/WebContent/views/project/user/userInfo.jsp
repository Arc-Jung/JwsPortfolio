<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>Travel-나의계정정보</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- CSS호출 -->
<%@ include file="/views/project/cssLoader.jsp"%>


</head>


<body>
	<!-- 네비게이션바 -->
	<%@ include file="/views/project/navbar.jsp"%>


	<div class="hero-wrap js-fullheight"
		style="background-image: url('${pageContext.request.contextPath}/images/signup.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text js-fullheight align-items-center justify-content-start"
				data-scrollax-parent="true">
				<div
					class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left"
					data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-4"
						data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">마이페이지</h1>
				</div>
			</div>
		</div>
	</div>



	<section class="ftco-section">
		<div class="container">
			<form name="info" action="${pageContext.request.contextPath}/user/update.do" method="post" class="search-destination">
				<div class="row">
	                        <div class="col-md-12">
	                            <div class="list-group panel panel-success hidden">
	                                <div class="panel-heading list-group-item text-center hidden-xs">
	                                    <h3>나의 회원정보</h3>
	                                    <p>회원님은 ${sessionScope.userOrder}번째로 가입하셨습니다!</p>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
				
				
					<div class="list-group panel panel-success hidden list-group-item">
						<div class="row">
							<div class="col-sm-12 col-md-3">
				                  <span class="icon-tag"></span><label for="#">&nbsp;회원ID</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="text" name="inputid" class="form-control" value="${sessionScope.loginID}" readonly="readonly">
								        </div>	
			              		</div>
		              		</div>
		              	</div>
	              		
	              		
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-lock2"></span><label for="#">&nbsp;패스워드</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="password" name="inputpassword" class="form-control" placeholder="회원정보 수정 완료시 패스워드 재입력" readonly="readonly">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              	
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-tag_faces"></span><label for="#">&nbsp;이름</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="text" name="inputname" id="inputname" class="form-control" value="${sessionScope.userName}" readonly="readonly">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-phone2"></span><label for="#">&nbsp;전화번호</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="text" name="inputtel" class="form-control" value="${sessionScope.userTel}" readonly="readonly">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-mail-forward"></span><label for="#">&nbsp;이메일</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="text" name="inputmail" class="form-control" value="${sessionScope.userMail}" readonly="readonly">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-smile-o"></span><label for="#">&nbsp;별명</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="text" name="inputnname" class="form-control" value="${sessionScope.userNName}" readonly="readonly">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
	              			<div class="col-md-12 align-self-end">
							     <div class="form-group">
							            <div class="form-field">
											 <input type="button" value="회원정보 수정하기" id="modifyInfo" onclick="modifyClick()" class="form-control btn btn-primary">
											 
											 <input type="submit" value="회원정보 수정완료" id="modifyFinish" class="form-control btn btn-primary" hidden="true">
											 <div class="text-right">
											 	 <br>
											 	 
											 	 <input type="button" value="회원탈퇴하기" id="deleteUser" data-toggle="modal" data-target="#deleteModal" class="btn btn-danger pull-right" hidden="true">
											 </div>
											
										</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
	</section>
	
	<!-- 회원탈퇴 버튼누르면 출력될 모달 -->
	<div class="modal fade" id="deleteModal" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        	<form action="${pageContext.request.contextPath}/user/deleteConfirm.do" method="post" class="search-destination">
	            <div class="modal-content">
	                <div class="modal-header ">
	                    <h4 class="modal-title" id="deleteModalLabel">회원탈퇴</h4>
	                </div>
	                <div class="modal-body">
	                    <div class="form-field">
	                        <div class="icon"><span class="icon-compass"></span>&nbsp; 패스워드 재입력</div>
	                        <input type="password" name="inputpassword" class="form-control" placeholder="패스워드를 입력해주세요">
	                            </div>
	                    <br>

	                </div>
	                <div class="modal-footer">
	                	
	                 	   <button type="button" class="btn btn-default" data-dismiss="modal">창 닫기</button>
	                 
	                    	<button type="submit" class="btn btn-primary">회원탈퇴</button>

	                </div>
	            </div>
	            </form>
        </div>
    </div>
	
	
	

			
		<!-- 나의 여행정보 -->	
		<section class="ftco-section">
			<div class="container">	
			  <div class="col-md-12">
				 <div class="row">
		             <div class="col-md-12">
		                  <div class="list-group panel panel-success hidden">
		                        <div class="panel-heading list-group-item text-center hidden-xs">
		                            <h3>나의 여행계획</h3>
		                         </div>
		                  </div>
		             </div>
		       </div>
		     </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="planlist">
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
                                        	<th class="text-center">순번</th>
                                            <th class="text-center">여행지</th>
                                            <th class="text-center">여행날짜</th>
                                            <th class="text-center">소요시간</th>
                                            <th class="text-center">교통수단</th>
                                            <th class="text-center">삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:choose>
				                    		<c:when test="${empty plans}">
						                        <tr>
						                            <th colspan="6" class="text-center">여행계획이 존재하지 않습니다.</th>
						                        </tr>
				                    		</c:when>
				                    		<c:otherwise>
				                    			<c:forEach var="plan" items="${plans}">
							                        <tr>
							                            <td class="text-center">${plan.no }</td>
							                           	<td class="text-center">${plan.location}</td>
							                           	<td class="text-center">${plan.date}</td>
							                           	<td class="text-center">${plan.startTime} ~ ${plan.endTime}</td>
							                           	<td class="text-center">${plan.vehicle}</td>
							                           	<td class="text-center"><button type="button" id="deletePlan${plan.planID}" class="btn btn-danger">삭제</button></td>
							                        </tr>
						                        </c:forEach>
				                    		</c:otherwise>
				                    	</c:choose>
										   
                                    </tbody>
                                </table>
                            </div>
                            
                            <div id="map"></div>
                            
                            
                            
                        </div>
                </section>

                    
                    
	    



	<!-- 하단Footer -->
	<%@ include file="/views/project/footer.jsp"%>


    <!-- 회원정보 수정하기 버튼 누르면 바뀌는 JQuery -->
    <script>
    function modifyClick() {
    	document.info.inputname.readOnly = false;
    	document.info.inputpassword.readOnly = false;
    	document.info.inputmail.readOnly = false;
    	document.info.inputtel.readOnly = false;
    	document.info.inputnname.readOnly = false;
		document.info.modifyFinish.hidden = false;
		document.info.deleteUser.hidden = false;
		document.info.modifyInfo.hidden = true;
		
    }
    </script>
    
    <!--플래너 삭제에 대한 JQuery -->
    <Script>
    <c:forEach var="plan" items="${plans}">
	    $("#deletePlan${plan.planID}").click(function(){
			  
	    	var planID = ${plan.planID};
	    	
	    	console.log(planID+"번 삭제요청!!");
			 $.ajax({
	 		  type : 'GET',
	 		  url : "${pageContext.request.contextPath}/project/deletePlan.do",
	 		  crossOrigin : false,
	 		  data : {planID : planID},
	 		  dataType: "json",
	 	      contentType : "application/json; charset=UTF-8",
	 		  success : function(data){
	 			  if(data==true){
		   				  swal.fire({
			   					  	type: 'success',
			   				  		title: '삭제되었습니다!',
			   				  		showConfirmButton: false,
			   				  		timer: 1500});
		   				  
		   					location.href = "${pageContext.request.contextPath}/user/userinfo.do";
	 			  }
				  else{
	 				  swal.fire({
	 					  type: 'error',
		   				  	  title: '에러가 발생하였습니다!',
		   				      showConfirmButton: false,
		   				      timer: 1500});
	 				  
	 				 location.href = "${pageContext.request.contextPath}/user/userinfo.do";
	 			  }
	 		  },
	 	 	  failure : function(error){
	 	 		 swal.fire({
						  type: 'error',
					  	  title: '에러가 발생하였습니다!',
					      showConfirmButton: false,
					      timer: 1500});
	 	 		location.href = "${pageContext.request.contextPath}/user/userinfo.do";
				   }
	 	  });
	
		  });
		  
    
    </c:forEach>
    </Script>
    

  <!-- 자바스크립트 -->	
  
  <%@ include file="/views/project/jsLoader.jsp" %>



	
  <!-- Select Option변경에 대한 좌표값을 넘겨받기위한 JQuery -->
  <script>
	  var defaultLatitude = 37.565316;
	  var defaultLongitude = 126.976776;
	  
	  var longitude = "";
	  var latitude = "";
	  
	  $('#location').change(function() {
		  var locname = $('#location option:selected').val();
		  
	      longitude = $('#long'+locname.toString()).val();
	      latitude = $('#lat'+locname.toString()).val();
  
	      console.log(longitude, latitude);
	  });

 
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		    mapOption = { 
		        center: new daum.maps.LatLng(parseFloat(defaultLatitude), parseFloat(defaultLongitude)), // 지도의 중심좌표
		        level: 12 // 지도의 확대 레벨
		    };
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		 
		// 마커를 표시할 위치와 title 객체 배열입니다 
		var positions = [
			<c:forEach var="plan" items="${plans}">
			    {
			        title: '${plan.location}', 
			        latlng: new daum.maps.LatLng(parseFloat(${plan.latitude}), parseFloat(${plan.longitude}))
			    },
			</c:forEach>
		    {
		        title: 'NULL',
		        latlng: new daum.maps.LatLng(0, 0)
		    }
		];

		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new daum.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new daum.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		}
		
	</script>



</body>
</html>
