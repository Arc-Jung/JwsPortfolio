<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<%@ include file="/views/project/jsLoader.jsp" %>


<c:choose>
	<c:when test="${empty sessionScope.findPW }">
		<script>
		swal.fire({title: '계정찾기 오류발생!',
			text: '알맞는 계정정보가 없습니다!',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/user/lost.do";
			});	
		</script>
	</c:when>
	
	<c:otherwise> 
		<script>
			swal.fire({title: '계정찾기 완료!',
					text: '회원님의 비밀번호는 ${sessionScope.findPW }입니다!',
					type: 'success'}).then(function(){
						window.location.href= "${pageContext.request.contextPath}/project/index.do";
					});
		</script>
	</c:otherwise>

</c:choose>



</body>