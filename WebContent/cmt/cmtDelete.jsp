<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
<c:set value="${cmtDelete }" var="cmtDelete"></c:set>

<fieldset>
<form action="./CmtDelete.cmt" method="post">
<input type="hidden" value="${cmtDelete.cmtID }" name="cmtID">

<textarea name="cmt_content"></textarea>
<button type="submit"> 삭제 완료 </button>
<input type="reset" value="취소" onclick="history.back();">
</form>
</fieldset>
</body>
</html>