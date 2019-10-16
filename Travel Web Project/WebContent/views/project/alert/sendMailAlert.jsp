<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<%@ include file="/views/project/jsLoader.jsp" %>
<c:if test="${sessionScope.success == 3 }">
	<script>
		swal.fire({title: '오류발생!',
			text: '오류가 발생하였습니다!',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/user/userinfo.do";
			});	
	</script>
</c:if>

<c:if test="${sessionScope.success == 2 }">
	<script>
		swal.fire({title: '오류발생!',
			text: '정보가 일치하지않거나 없습니다!!',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/user/userinfo.do";
			});	
	</script>
</c:if>

<c:if test="${sessionScope.success == 1 }">
	<script>
		swal.fire({title: '메일발송 완료!',
				text: '메일이 정상적으로 발송되었습니다!',
				type: 'success'}).then(function(){
					window.location.href= "${pageContext.request.contextPath}/project/index.do";
				});
	</script>
</c:if>
</body>