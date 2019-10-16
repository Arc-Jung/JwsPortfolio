<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Travel-회원가입</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        
    <!-- CSS호출 -->
  <%@ include file="/views/project/cssLoader.jsp" %>
  
  
  </head>
    
  
  <body>
	<%@ include file="/views/project/navbar.jsp" %>
    

    <div class="hero-wrap js-fullheight" style="background-image: url('${pageContext.request.contextPath}/images/signup.jpg');">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left" data-scrollax=" properties: { translateY: '70%' }">
            <h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }" >회원가입</h1>
            <p data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Travel에 오신것을 환영합니다!</p>
          </div>
        </div>
      </div>
    </div>
    </div>



     <section class="ftco-section">
		<div class="container">
				<form action="${pageContext.request.contextPath}/user/signup.do" method="post" class="search-destination">
					<div class="row">
	                        <div class="col-md-12">
	                            <div class="list-group panel panel-success hidden">
	                                <div class="panel-heading list-group-item text-center hidden-xs">
	                                    <h3>회원가입</h3>
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
								            <input type="text" name="inputid" class="form-control" placeholder="아이디를 입력해주세요">
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
								            <input type="password" name="inputpassword" class="form-control" placeholder="패스워드를 입력해주세요">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
		              		<div class="col-sm-12 col-md-3">
				                  <span class="icon-lock_open"></span><label for="#">&nbsp;패스워드 확인</label>      
				            </div>
	                        
		            		<div class="col-sm-12 col-md-9">
								<div class="form-group">
			              				<div class="form-field">
								            <input type="password" name="inputpasswordconfirm" class="form-control" placeholder="패스워드를 한번 더 입력해주세요">	
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
								            <input type="text" name="inputname" class="form-control" placeholder="이름을 입력해주세요">	
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
								            <input type="text" name="inputtel" class="form-control" placeholder="전화번호를 입력해주세요">	
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
								            <input type="text" name="inputmail" class="form-control" placeholder="메일주소를 입력해주세요">	
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
								            <input type="text" name="inputnname" class="form-control" placeholder="별명을 입력해주세요">	
								        </div>	
			              		</div>
		              		</div>
	              		</div>
	              		
	              		<div class="row">
	              			<div class="col-md-12 align-self-end">
							     <div class="form-group">
							            <div class="form-field">
											 <input type="submit" value="회원가입" class="form-control btn btn-primary">
										</div>
								</div>
	              			</div>
						</div>
						
						
					</div>
				</form>
			</div>              
      </section>
                    
    
	<!-- 하단Footer -->
    <%@ include file="/views/project/footer.jsp" %>
 

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

  <%@ include file="/views/project/jsLoader.jsp" %>

  </body>
</html>
