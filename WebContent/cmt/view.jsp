<%@page import="java.util.List"%>
<%@page import="com.tp.bbs.db.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath }/css/main.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>

</head>
<body>
	<!-- 네비게이션 -->
	<%-- <jsp:include page="../inc/nav.jsp" /> --%>
	<!-- 네비게이션 -->

	<!-- 메인 페이지 -->
	
	<input type="hidden" value="${cmt.userID }" name="userNickName">
	<input type="hidden" value="${cmt.cmt_date }" name="cmt_date">
	
<fieldset>
	<table>
	
		<tr>
			<td align="center"><b>번호</b></td>
			<td align="center"><b>별명</b></td>
			<td align="center"><b>글내용</b></td>
			<td align="center"><b>작성일</b></td>
			<td align="center"><b>수정,삭제</b></td>
			
		</tr>
	 
		<c:forEach items="${cmtList }" var="cmt"  > 
		 
		 <tr>
			<td width="20">${cmt.cmtID}</td>
			   
			 <td align="center">${cmt.userID }
			 </td>
			
		     <td align="center" >${cmt.cmt_content }</td>
			
			
			<td>
			<fmt:formatDate value="${cmt.cmt_date }" type="both" /> <!-- // 작성일 -->
		  	</td>     
		  	<td><a href="./CmtModify.cmt?num=${cmt.cmtID }">수정</a>
		      <a href="./CmtDelete.cmt?num=${cmt.cmtID }">삭제</a>
		      </td>
		 </tr>
		 
		 </c:forEach>
		 
	</table>


</fieldset>
	<br>
	<a href="${contextPath}/Cmt.cmt"> 글쓰기</a>
	<a href="${contextPath}/Main.do"> 메인</a>	
<c:choose>
<c:when test="${userNickName eq 'userID' }">
	<a href="${contextPath}/CmtModify.cmt">수정</a>
	<a href="${contextPath}/CmtDelete.cmt">삭제</a>
	<!-- 메인 페이지 -->
</c:when>
</c:choose>
</body>
</html>