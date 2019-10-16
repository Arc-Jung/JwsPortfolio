<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <title>Travel-나의 여행계획</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

                
    <!-- CSS호출 -->
  <%@ include file="/views/project/cssLoader.jsp" %>
  
  
        </head>


        <body>

            <%@ include file="/views/project/navbar.jsp" %>
            
            
           <div class="hero-wrap js-fullheight" style="background-image: url('${pageContext.request.contextPath}/images/myplan.jpg');">
      			<div class="overlay"></div>
    		 	<div class="container">
       			 	<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
        		  		<div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left" data-scrollax=" properties: { translateY: '70%' }">
        		   			 <h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">나의 여행계획</h1>
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
	                                    <h4>${sessionScope.userName}님의 여행계획</h4>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
        			<br>

        			
        			<div class="row">
        			<div class="col-sm-12 col-lg-9">
        				<div class="list-group panel panel-success">
		                        	<div id="map"></div>
	                        	</div>
	                        </div>
                       
	                        <div class="col-sm-12 col-lg-3">
	                        	<div class="list-group panel panel-success">
	                        		<div class="form-group form-destination">
	              							<span class="icon-map-marker"></span><label for="#">&nbsp;여행지명</label>
	              								<div class="form-field">
				              						<div class="select-wrap">
							                     		 <select id="location" class="form-control" align="center" name="location" >
							                     		 	
							                     		 	<option>------선택해주세요------</option>
							                     		 	
									                      	<option disabled>----수도권----</option>
									                      	
									                      	<c:choose>
																<c:when test="${empty travelInfosA}">
																	<option disabled>--해당지역정보 미존재--</option>
																</c:when>
																<c:otherwise>
																	<c:forEach var="infoA" items="${travelInfosA}">
																		<option value="${infoA.infoName}" id="${infoA.infoName}" name="${infoA.infoId}">${infoA.infoName}</option>
																		<option value="${infoA.longitude}" id="long${infoA.infoId}" style="display:none"></option>
																		<option value="${infoA.latitude}" id="lat${infoA.infoId}" style="display:none"></option>
																	</c:forEach>
																</c:otherwise>
															</c:choose>
															
															
															<option disabled>----강원권----</option>
									                      	
									                      	<c:choose>
																<c:when test="${empty travelInfosB}">
																	<option disabled>--해당지역정보 미존재--</option>
																</c:when>
																<c:otherwise>
																	<c:forEach var="infoB" items="${travelInfosB}">
																		<option value="${infoB.infoName}" id="${infoB.infoName}" name="${infoB.infoId}">${infoB.infoName}</option>
																		<option value="${infoB.longitude}" id="long${infoB.infoId}" style="display:none"></option>
																		<option value="${infoB.latitude}" id="lat${infoB.infoId}" style="display:none"></option>
																	</c:forEach>
																</c:otherwise>
															</c:choose>
															
															
															
															<option disabled>----경상권----</option>
									                      	
									                      	<c:choose>
																<c:when test="${empty travelInfosC}">
																	<option disabled>--해당지역정보 미존재--</option>
																</c:when>
																<c:otherwise>
																	<c:forEach var="infoC" items="${travelInfosC}">
																		<option value="${infoC.infoName}" id="${infoC.infoName}" name="${infoC.infoId}">${infoC.infoName}</option>
																		<option value="${infoC.longitude}" id="long${infoC.infoId}" style="display:none"></option>
																		<option value="${infoC.latitude}" id="lat${infoC.infoId}" style="display:none"></option>
																	</c:forEach>
																</c:otherwise>
															</c:choose>
															
															
															
															<option disabled>----전라권----</option>
									                      	
									                      	<c:choose>
																<c:when test="${empty travelInfosD}">
																	<option disabled>--해당지역정보 미존재--</option>
																</c:when>
																<c:otherwise>
																	<c:forEach var="infoD" items="${travelInfosD}">
																		<option value="${infoD.infoName}" id="${infoD.infoName}" name="${infoD.infoId}">${infoD.infoName}</option>
																		<option value="${infoD.longitude}" id="long${infoD.infoId}" style="display:none"></option>
																		<option value="${infoD.latitude}" id="lat${infoD.infoId}" style="display:none"></option>
																	</c:forEach>
																</c:otherwise>
															</c:choose>
															
															<option disabled>----제주권----</option>
									                      	
									                      	<c:choose>
																<c:when test="${empty travelInfosE}">
																	<option disabled>--해당지역정보 미존재--</option>
																</c:when>
																<c:otherwise>
																	<c:forEach var="infoE" items="${travelInfosE}">
																		<option value="${infoE.infoName}" id="${infoE.infoName}" name="${infoE.infoId}">${infoE.infoName}</option>
																		<option value="${infoE.longitude}" id="long${infoE.infoId}" style="display:none"></option>
																		<option value="${infoE.latitude}" id="lat${infoE.infoId}" style="display:none"></option>
																	</c:forEach>
																</c:otherwise>
															</c:choose>         	
									                      	
							                      		</select>
						                    		</div>
								              </div>
	              					</div>
	              					
	              					<div class="form-group">
			              					<span class="icon-calendar"></span><label for="#">&nbsp;날짜</label>
	              							<div class="form-field">					
								                <input type="text" name="date" id="datepicker" class="form-control" placeholder="날짜선택 ">
								              </div>
									</div>
	                        	
	                        		<div class="form-group">
			              					<span class="icon-clock-o"></span><label for="#">&nbsp;시간</label>
	              							<div class="form-field">
	              								<div class="input-group">
												    <input type="text" name="startTime" id="start" class="form-control timepicker" placeholder="시작 "
												    data-placement="left" data-align="top" data-autoclose="true">
									                <input type="text" name="endTime" id="end" class="form-control timepicker" placeholder="종료 "
									                data-placement="left" data-align="top" data-autoclose="true">
									            </div>
									        </div>
					              	</div>
					              	
       		
	                        		<div class="form-group">
	                        		<span class="icon-directions"></span><label for="#">&nbsp;교통수단</label>
	              						<div class="form-field">
		              						<div class="select-wrap">
					                     		 <select id="vehicle" class="form-control" name="vehicle" >
							                      	<option value="도보">도보</option>
							                        <option value="기차">기차</option>
							                        <option value="버스">버스</option>
							                        <option value="택시">택시</option>
							                        <option value="지하철">지하철</option>
							                        <option value="기타">기타</option>
					                      		</select>
				                    		</div>
				                    		<br>
						              </div>
						              </div>
						              
						              <div class="col-md-12 align-self-end">
				              				<div class="form-group">
				              					<div class="form-field col-xs-12">
									                <input type="button" id="addPlan" value="일정에 추가하기" class="form-control btn btn-primary">
									              </div>
								              </div>
              							</div>
								</div>
	                        </div>
	                        
                        </div>
                        </div>
                        
                       
                       

                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="planlist">
                                    <colgroup>
                                        <col width="*" />
                                        <col width="*" />
                                        <col width="*" />
                                        <col width="*" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th class="text-center">여행지</th>
                                            <th class="text-center">여행날짜</th>
                                            <th class="text-center">소요시간</th>
                                            <th class="text-center">교통수단</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        
                                    </tbody>
                                </table>
                            </div>

                            <div class="text-center">
                                    <button type="button" class="btn btn-warning" id="goMyPages">마이페이지로 가서 확인하기</button>                      
                                <br>
                            </div>
                        </div>
                    </section>
                    
                    <br>
	
	<!-- 하단Footer태그 -->
    <%@ include file="/views/project/footer.jsp" %>

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <!-- 자바스크립트 -->	
  <%@ include file="/views/project/jsLoader.jsp" %>
 
 
   
  <!-- Select Option변경에 대한 좌표값을 넘겨받기위한 JQuery -->
  <script>
	  var defaultLatitude = 37.565316;
	  var defaultLongitude = 126.976776;
	  
	  var longitude = "";
	  var latitude = "";
	  
	  $('#location').change(function() {
		  var locname = $('#location option:selected').attr('name');
		  
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
		
		
		var mapTypeControl = new daum.maps.MapTypeControl();
		
           // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
           // daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

           // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        var zoomControl = new daum.maps.ZoomControl();
        map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		   
		
		// 지도를 클릭했을때 클릭한 위치에 마커를 추가하도록 지도에 클릭이벤트를 등록합니다
		//daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
		// 클릭한 위치에 마커를 표시합니다 
		//  addMarker(mouseEvent.latLng);             
		//});
		
		// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
		var markers = [];
		
		// 마커 하나를 지도위에 표시합니다 
		addMarker(new daum.maps.LatLng(latitude, longitude));
		
		// 마커를 생성하고 지도위에 표시하는 함수입니다
		function addMarker(position) {
		    
		    // 마커를 생성합니다
		    var marker = new daum.maps.Marker({
		        position: position
		    });
		
		    // 마커가 지도 위에 표시되도록 설정합니다
		    marker.setMap(map);
		    
		    // 생성된 마커를 배열에 추가합니다
		    markers.push(marker);
		}
		
		// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
		function setMarkers(map) {
		    for (var i = 0; i < markers.length; i++) {
		        markers[i].setMap(map);
		    }
		}
		
		// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
		function showMarkers() {
		    setMarkers(map)    
		}
		
		// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
		function hideMarkers() {
		    setMarkers(null);
		}
		
		function removeMarkers() {
		    setMarkers();
		
		}
		
		function add(){
				  MapMarker = new daum.maps.LatLng(parseFloat(latitude), parseFloat(longitude));
				  addMarker(MapMarker);
			  	
				  console.log('지도추가완료!');
		}
		
	</script>
 
 
 
  

   <script>
   $(function(){

 	  $('#addPlan').click(function(){
		  var no = '1';
 		  var location = $('#location').val();
 		  var date = $('#datepicker').val();
 		  var start = $('#start').val();
 		  var end = $('#end').val();
 		  var vehicle = $('#vehicle').val();	
 		  var destinationID = 1;
 		  
 		 $.ajax({
   		  type : 'GET',
   		  url : "${pageContext.request.contextPath}/project/insert.do",
   		  crossOrigin : false,
   		  data : {location: location, date: date, start: start, end: end, vehicle: vehicle, destinationID: destinationID, longitude: longitude, latitude:latitude},
   		  dataType: "json",
   	      contentType : "application/json; charset=UTF-8",
   		  success : function(data){
   			  if(data==true){
	   				  swal.fire({
		   					  	type: 'success',
		   				  		title: '저장되었습니다!',
		   				  		showConfirmButton: false,
		   				  		timer: 1500});
	   				  
		   			  $('#planlist tbody:last-child').append(
		   	 				  '<tr>'+
		   	                   	'<td class="text-center">'+location+'</td>'+
		   	                   	'<td class="text-center">'+date+'</td>'+
		   	                   	'<td class="text-center">'+start+' ~ '+end+'</td>'+
		   	                   	'<td class="text-center">'+vehicle+'</td>'+
		   	               	'</tr>' 
		   	 		  );
		   			  
		   			  add();
		   			  
		   	 		  
		   	     	  // 날짜 선택창은 초기화 안함
		   	     	  //$('#datepicker').val('');
					  //$('#start').val('');
		   	     	  //$('#end').val('');
	
		   	 		  $('#no').val('');
		   	     	  $('#location').find("option:eq(0)").attr('selected', 'selected');  
		   	     	  $('#vehicle').find("option:eq(0)").attr('selected', 'selected');  
   				  }
   			  else{
   				  swal.fire({
   					  type: 'error',
	   				  	  title: '에러가 발생하였습니다!',
	   				      showConfirmButton: false,
	   				      timer: 1500});
   			  }
   		  },
   	 	  failure : function(error){
   	 		 swal.fire({
					  type: 'error',
  				  	  title: '에러가 발생하였습니다!',
  				      showConfirmButton: false,
  				      timer: 1500});
			   }
   	  });

 	  });
 	  
 	  
 	 //마이페이지로 가기 액션 
 	 $('#goMyPages').click(function(){
	 		Swal.fire({
	 			  text: "마이페이지로 가시겠습니까?",
	 			  type: 'warning',
	 			  showCancelButton: true,
	 			  confirmButtonColor: '#3085d6',
	 			  cancelButtonColor: '#d33',
	 			  confirmButtonText: '네!',
	 		      cancelButtonText: '아니오! 계획 더 짤게요!',
	 			}).then((result) => {
	 			  if (result.value) {
	 				 window.location.href= "${pageContext.request.contextPath}/user/userinfo.do";
	 			  }
	 			});
 	 });
 	  
   }); 
    </script>

  
 
									
<!-- DatePicker 출력에 관한 JQuery -->
<script>
		//Project간 기능구현에 사용할 JQuery중
		//1. 초기값을 오늘로 설정
		//2. 날짜 표기법을 국내 표준 yy-mm-dd의 형식으로 변경
		//3. 날짜 선택시 자동으로 창이 꺼지게 구현하였다.
        $(function() {
            $("#datepicker").datepicker({
                dateFormat: 'yy-mm-dd'
            	,autoclose: true
            });                    
            

            $('#datepicker').datepicker('setDate', 'today');       
        });
    </script>
    
  <!-- TimePicker출력에 대한 JQuery -->
  <script>

  $(document).ready(function(){
	  $('.timepicker').timepicker({
		    timeFormat: 'H:mm',
		    interval: 15,
		    minTime: '6',
		    maxTime: '11:00pm',
		    defaultTime: 'now',
		    startTime: '6',
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true
		});
  });
  </script>

        </body>

        </html>