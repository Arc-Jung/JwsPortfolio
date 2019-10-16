<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<%@ include file="/views/project/jsLoader.jsp" %>
<script>
		swal.fire({title: '오류발생!',
			text: 'Message\n ${pageContext.exception.message}',
			type: 'error'}).then(function(){
				window.location.href= "${pageContext.request.contextPath}/project/index.do";
			});	
	</script>
</body>