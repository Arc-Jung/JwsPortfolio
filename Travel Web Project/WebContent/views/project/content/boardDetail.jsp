<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="UTF-8">
<head>

<!-- 여기까지 -->
<title>Travel-커뮤니티</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- CSS호출 -->
<%@ include file="/views/project/cssLoader.jsp"%>

</head>


<body>
	<!-- 네비게이션바 -->
	<%@ include file="/views/project/navbar.jsp"%>




	<div class="hero-wrap js-fullheight" style="background-image: url('${pageContext.request.contextPath}/images/boardbg.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
				<div class="col-md-9 ftco-animate mb-5 pb-5 text-center text-md-left" data-scrollax=" properties: { translateY: '70%' }">
					<h1 class="mb-4" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">커뮤니티</h1>
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
							<h5>${content.title}</h5>
						</div>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-bordered table-hover" id="board">
					<tr>
						<th style="width: 25%" class="text-center">작성자</th>
						<td style="width: 25%" class="text-center">${content.creatorName}</td>
						<th style="width: 25%" class="text-center">추천수</th>
						<td style="width: 25%" class="text-center"><b id="voteCount"></b></td>
					</tr>
					<tr>
						<th style="width: 25%" class="text-center">조회수</th>
						<td style="width: 25%" class="text-center">${content.viewCount}</td>
						<th style="width: 25%" class="text-center">작성일</th>
						<td style="width: 25%" class="text-center">${content.dateCreate}</td>
					</tr>
					<tr>
						<td colspan="4">
							<p style="padding: 20px">${content.contentText}</p>
						</td>
					</tr>


					<c:forEach var="comment" items="${content.comments}">
						<tr>
							<th style="width: 25%" class="text-center"><strong>${comment.creatorName}</strong></th>

							<td colspan="3">${comment.commentText}<br> 
								<c:if test="${sessionScope.loginID eq content.creatorName or sessionScope.isAdmin eq 1}">
									<div class="text-right">
										<a href="${pageContext.request.contextPath}/comment/remove.do?contentId=${content.contentId}&commentId=${comment.commentId}"> <span class="icon-trash-o"></span>
										</a>
									</div>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="row">
				<div class="col-md-12">
			
					<div class="form-group">
							<div class="form-field">
								<input type="button" id="like" name="like" value="추천하기" class="form-control btn btn-primary">
							</div>
					</div>
				</div>
			</div>

			<br>

			<c:if test="${sessionScope.loginID ne null }">
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">

							<form action="${pageContext.request.contextPath}/comment/regist.do">
								<table class="table table-bordered table-hover" id="board">
									<tr>
										<th style="width: 25%" class="text-center">댓글을 입력하세요!</th>

										<td style="width: 70%" colspan="3" class="text-center"><input type="hidden" name="contentId" value="${content.contentId}"> <textarea name="commentText" rows="3" style="width: 100%; border: 0; resize: none;" placeholder="입력해주세요"></textarea></td>
									</tr>
									<tr>
										<th colspan="4" class="text-center">
											<button type="submit" class="btn btn-warning pull-right">댓글달기</button>
										</th>
									</tr>
								</table>
							</form>

						</div>
					</div>
				</div>
			</c:if>
			
			
					


			
			<div class="text-left">
				<a href="${pageContext.request.contextPath}/content/main.do">
					<button type="button" class="btn btn-default">목록으로 돌아가기</button>
				</a>
			</div>
			<c:if test="${sessionScope.loginID eq content.creatorName or sessionScope.isAdmin eq 1}">
				<div class="text-right">
					<a href="${pageContext.request.contextPath}/content/modify.do?contentId=${content.contentId}">
						<button type="button" class="btn btn-warning pull-right">수정하기</button>
					</a> <a href="${pageContext.request.contextPath}/content/remove.do?contentId=${content.contentId}">
						<button type="button" class="btn btn-danger pull-right">삭제하기</button>
					</a>
				</div>
			</c:if>

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
	<%@ include file="/views/project/jsLoader.jsp"%>
	
	<!-- 추천수 발생에 대한 JQuery -->
	<script>
	
	$(function(){
		  var count=${content.voteCount };
		  
		  $("#voteCount").html(count); 
		  
	 	  $('#like').click(function(){
			  var contentID = ${content.contentId};
	 		  
	 		  
	 		 $.ajax({
	 	   		  type : 'GET',
	 	   		  url : "${pageContext.request.contextPath}/content/like.do",
	 	   		  crossOrigin : false,
	 	   		  data : {contentID: contentID},
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
</body>

</html>