<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>오류발생!!</title>
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
				<div class="row">
	                        <div class="col-md-12">
	                            <div class="list-group panel panel-success hidden">
	                                <div class="panel-heading list-group-item text-center hidden-xs">
	                                    <h3>오류발생!</h3>
	                                    <h4>${pageContext.exception.message}</h4>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
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
    

  <!-- 자바스크립트 -->	
  <%@ include file="/views/project/jsLoader.jsp" %>
</body>
</html>
