<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<c:set value="${cmtDetail }" var="cmtDetail"></c:set>

	<fieldset>
		<form action="./CmtModify.cmt" method="post">

			<input type="hidden" value="${cmtDetail.cmtID }" name="cmtID">

			<input type="text" name="cmt_content" maxlength="50"><br>
			<textarea name="cmt_content"></textarea>
			<button type="submit">수정 완료</button>
			<input type="button" value="취 소 "
				onclick="location.href='./CmtView.cmt?cmtID=${cmtDetail.cmtID }'">

		</form>


	</fieldset>



</body>
</html>