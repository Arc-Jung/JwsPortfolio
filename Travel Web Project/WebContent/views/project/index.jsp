<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Travel</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- CSS호출 -->
  <%@ include file="/views/project/cssLoader.jsp" %>
  
  
  
  </head>
  
  
  <body>

	  
 	<%@ include file="/views/project/navbar.jsp" %>

    <div class="hero-wrap js-fullheight" style="background-image: url('${pageContext.request.contextPath}/images/bg.jpg');">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left" data-scrollax=" properties: { translateY: '70%' }">
            <h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }" >TRAVEL</h1>
            <p data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">여행, 더 나은 계획</p>
          </div>
        </div>
      </div>
    </div>


    <section class="ftco-section justify-content-end ftco-search">
          <div class="col-md-12 tab-wrap">
            <div class="tab-content p-4 px-5" id="v-pills-tabContent">
              <div class="tab-pane fade show active" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-nextgen-tab">
              	<form action="${pageContext.request.contextPath}/info/search.do" class="search-destination">
              		<div class="row">
              			<div class="col-md align-items-end">
              				<div class="form-group">
	              				<div class="form-field col-sm-12 col-md-12">
					                	<input type="text" class="form-control" name ="infoName" id="infoName" placeholder="관광지를 입력해주세요">
					              </div>
				              </div>
              			</div>

              			<div class="col-sm-12 col-md-3 align-self-end text-center">
              				<div class="form-group">
	              					<div class="form-field">
						                <input type="submit" value="검색하기" class="form-control btn btn-primary">
						            </div>
				              </div>
              			</div>
              		</div>
              	</form>
              </div>
            </div>
          </div>
    </section>



    <section class="ftco-section">
    	<div class="container">
				<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section text-center ftco-animate">
              <br><br><br>
            <h2 class="mb-4">추천이 많은 여행지</h2>
          </div>
        </div>
    	</div>
    	<div class="container-fluid">
    		<div class="row">
    		
    		<!-- 여행정보 출력하는 스크립트 -->
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
    </section>
    



    <section class="ftco-section testimony-section">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
            <h2 class="mb-4">많은 리뷰가 있습니다</h2>
            
          </div>
        </div>
        <div class="row ftco-animate">
          <div class="col-md-12">
            <div class="carousel-testimony owl-carousel ftco-owl">
            
            
            <!-- 리뷰 출력하는 부분 -->
            <c:forEach var="content" items="${contents }">
	              <div class="item">
	              	<a href="${pageContext.request.contextPath}/content/find.do?contentId=${content.contentId}">
	                <div class="testimony-wrap p-4 pb-5">
	                  <div class="text">
	                    <p class="mb-5">${content.title }</p>
	                    <p class="name">${content.creatorName }</p>
	                    <span class="position">조회&nbsp;${content.viewCount }</span>
	                  </div>
	                </div>
	                </a>        
	              </div>

			</c:forEach>
             
              
            </div>
          </div>
        </div>
      </div>
    </section>




	<!-- 신문으로부터 RSS 파싱하여 출력 -->
    <section class="ftco-section bg-light">
      <div class="parallax-img d-flex align-items-center">
        <div class="container">
          <div class="row d-flex justify-content-center">
            <div class="col-md-7 text-center heading-section heading-section-black ftco-animate">
              <h2>신문으로 보는 여행</h2>
              <br>
            </div>
            <div class="container">
                <div class="card text-center">
                    <div class="card-body">
                        <div class="pre-scrollable">

                        	<!-- 뉴스 출력해주는 JSTL -->
	                        <c:choose>
	                        <c:when test="${empty news }">
	                    			<a class="list-group-item hidden-xs">피드를 불러올수 없습니다.</a>
	                			</c:when>
	                			<c:otherwise>
	                				<c:forEach var="newses" items="${news }">
				                    	<!--  a href="${pageContext.request.contextPath}/board/modify.do?boardId=${board.boardId}" class="list-group-item hidden-xs">${board.name}</a-->
				                    	
				                    		<a href="${newses.link }">
				                    			<h6 class="card-title" style="color:gray;">${newses.title }</h6>
	
				                    			<!--p class="card-text">${newses.description }</p-->
				                    			<br>
				                    		</a>
				                    	
				                    </c:forEach>
	                			</c:otherwise>
	                		</c:choose>
                		
                		
                       </div>
                    </div>
                    <div class="card-footer text-muted text-right">출처: 동아일보</div>
                </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
	<!-- 하단Footer -->
    <%@ include file="/views/project/footer.jsp" %>


  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
	
  <!-- 자바스크립트 -->	
  <%@ include file="/views/project/jsLoader.jsp" %>

  </body>
</html>
