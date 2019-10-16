<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<nav class="navbar navbar-expand-lg ftco_navbar ftco-navbar-light"
	id="ftco-navbar">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/project/index.do">Travel</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#ftco-nav" aria-controls="ftco-nav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="oi oi-menu"></span> 메뉴
		</button>

		<div class="collapse navbar-collapse" id="ftco-nav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a href="${pageContext.request.contextPath}/content/main.do"
					class="nav-link">Home</a></li>


				<li class="nav-item">
				<a href="${pageContext.request.contextPath}/info/main.do" class="nav-link">여행정보</a>
				</li>


				<li class="nav-item">
				<a href="${pageContext.request.contextPath}/content/main.do" class="nav-link">커뮤니티</a>
				</li>

				<c:choose>
					<c:when test="${null eq sessionScope.loginID}">
						<button type="button" id="loginButton" class="btn btn-info btn-lg"
							data-toggle="modal" data-target="#loginModal">로그인</button>

					</c:when>
					<c:otherwise>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/project/myplan.do"
							class="nav-link">나의여행계획</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/user/userinfo.do"
							class="nav-link">마이페이지</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/user/logout.do"
							class="nav-link">로그아웃</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav>
<!-- END nav -->




<!-- 모달 출력부분입니다~ -->

<div class="modal fade" id="loginModal" tabindex="1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form action="${pageContext.request.contextPath}/user/login.do"
			method="post" class="search-destination" onsubmit="return check();">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="loginModalLabel">회원 로그인</h4>
					<a href="${pageContext.request.contextPath}/user/signup.do">
						<button type="button" class="btn btn-secondary">회원가입</button>
					</a>
				</div>
				<div class="modal-body">
					<div class="form-field">
						<div class="icon">
							<span class="icon-id-badge"></span>&nbsp; 아이디
						</div>
						<input type="text" name="loginid" class="form-control"
							placeholder="아아디를 입력해주세요">
					</div>
					<br>
					<div class="form-field">
						<div class="icon">
							<span class="icon-compass"></span>&nbsp; 패스워드
						</div>
						<input type="password" name="loginpw" class="form-control"
							placeholder="패스워드를 입력해주세요">
					</div>
					<br>
					<div class="g-recaptcha" data-sitekey="6LcvOaUUAAAAAPtKq4J9QyuI3F_ZodKRNF6p6btV"></div>
					<br> 
					<a href="${pageContext.request.contextPath}/user/lost.do"
						class="" style="text-align: center">아이디 및 비밀번호를 잊으셨나요?</a>

				</div>
				<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">로그인 창 닫기</button>
						<button type="submit" class="btn btn-primary">로그인</button>
				</div>
			</div>
		</form>
	</div>
</div>







<!-- 구글 리캡차 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

<script>
	function check()
	{
	    var response = grecaptcha.getResponse();
	    if(response.length == 0)
		{
			swal.fire({title: 'reCaptcha 오류!',
				text: 'reCaptcha를 확인하세요!!',
				type: 'error'
				});	
			return false;
		}
	    else
		{
			return true;	
		}
	}

</script>


