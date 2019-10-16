<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<%@ include file="/views/project/jsLoader.jsp" %>
<c:if test="${sessionScope.updateSuccess == false }">
	<script>
		swal.fire({title: '오류발생!',
			text: '패스워드가 빈칸입니다!',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/user/userinfo.do";
			});	
	</script>
</c:if>

<c:if test="${sessionScope.updateSuccess == true }">
	<script>
		swal.fire({title: '정보변경완료!',
				text: '정상적으로 변경 완료되었습니다!',
				type: 'success'}).then(function(){
					window.location.href= "${pageContext.request.contextPath}/project/index.do";
				});
	</script>
</c:if>
</body>