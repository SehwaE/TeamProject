<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<title>Insert title here</title>
</head>
<body>
<!-- 네비게이션 -->
<%-- <jsp:include page="../inc/nav.jsp" /> --%>
<!-- 네비게이션 -->
<!-- 메인 페이지 -->
<h1>댓글</h1>
<article>
	<form action="./CmtAdd.cmt" method="post" >
		<input type = "hidden" value = "1" name = "bbs_category">
		<input type="text" name="userID" maxlength="50" placeholder="제목을 입력해 주세요."><br>
		<textarea name="cmt_content" cols="40" rows="13" placeholder="내용을 입력하세요."></textarea><br>
		<button type="submit" >글 쓰기</button>
		<button type="button" onclick="history.back()">돌아가기</button>
	</form>
</article>
<!-- 메인 페이지 -->

<!-- footer -->
<%-- <jsp:include page="../inc/footer.jsp" /> --%>
<!-- footer -->
</body>

</html>