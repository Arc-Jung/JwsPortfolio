<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<%@ include file="/views/project/jsLoader.jsp" %>
<c:if test="${sessionScope.success == false }">
	<script>
		swal.fire({title: '로그인 오류발생!',
			text: '로그인 중 오류가 발생하였습니다!',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/project/index.do";
			});	
	</script>
</c:if>

<c:if test="${sessionScope.success == true }">
	<script>
		swal.fire({title: '로그인 완료!',
				text: '로그인이 완료되었습니다!',
				type: 'success'}).then(function(){
					window.location.href= "${pageContext.request.contextPath}/project/index.do";
				});
	</script>
</c:if>
</body>