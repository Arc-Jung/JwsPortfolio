<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<title>Travel</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- CSS호출 -->
<%@ include file="/views/project/cssLoader.jsp"%>



</head>
<body>

	<h2 class="mb-4">서버에 직접 업로드</h2>
	<form method="post"
		action="${pageContext.request.contextPath}/file/upload.do"
		enctype="multipart/form-data">
		<label>파일:</label> <input multiple="multiple" type="file" name="file1">
		<input type="submit" value="upload">
	</form>
	
		<h2 class="mb-4">S3 업로드</h2>
	<form method="post"
		action="${pageContext.request.contextPath}/file/uploads3.do"
		enctype="multipart/form-data">
		<label>파일:</label> <input multiple="multiple" type="file" name="file1">
		<input type="submit" value="upload">
	</form>



</body>
</html>