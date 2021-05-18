<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 공지사항 </title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${contextPath }/css/main.css">
<link rel="stylesheet" href="${contextPath }/css/noticeList.css">
<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
	<div id = "wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		<!-- @ subHeader Top area -->    
		<div id="subVisual">
		    <section id="subVisu" class="submenu-DOM_00000020100300">
		        <h2 class="tit">공지사항</h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">게시판 > </li>
							<li class="depth2"><a href="${contextPath }/NoticeList.no">공지사항</a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
	    <!--// @ subHeader Top area -->
		<div class="container">
			<div class="innerwrap">
				<hr>
				<table id="bList" class="bbs_default_list">
					<thead>
						<tr height="30">
							<th width="10%">글번호</th>
							<th width="58%">제목</th>
							<th width="10%">작성자</th>
							<th width="12%">작성일</th>
							<th width="10%">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bbs" items="${bbsList}" varStatus="vs">
							<tr>
								<td>${(totalRowCount - pagePerRow * (pageNum -1)) - (vs.index+1) + 1 }</td>
								<td class="subject" ><a href="./NoticeDetail.no?bbsID=${bbs.bbsID }">${bbs.bbs_title}</a></td>
								<td>${bbs.userNickName}</td>
								<td><fmt:formatDate value="${bbs.bbs_date }" dateStyle="medium" /></td>
								<td>${bbs.bbs_readcount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:choose>
				 	<c:when test="${userID eq 'admin' }">
				   		<div id="aa">
							<br>
							<a href="${contextPath}/NoticeWrite.no"> 글쓰기</a>
							<a href="${contextPath}/Main.do"> 메인</a>
				   		</div>
				 	</c:when>
				</c:choose>
			
				<hr>
				<div id="list">
					<p class="nearPaging">
						<c:if test="${pageNum > endPage}">
							<c:set var="page" value="${endPage}" />
						</c:if>
					<a href="<c:url value='NoticeList.no?pageNum=${1}'/>">&lt;&lt;</a>
						<!-- 출력할 페이지 수 계산 -->
						<fmt:formatNumber value="${pageNum/4 - (pageNum/4 % 1)}" type="pattern" pattern="0" var="full" />
										<!-- value = 값 / pattern 속성 : 0 = 수치 / type = 데이터형 / var : 사용할 변수명 -->
										<!--ex) 5 페이지의 경우 : 5 / 4 - (5/4 % 1) -->
						<c:set var="full" value="${full * 4}" />
						<!-- set : 변수 설정 -->
						<!-- prev(전 페이지), next(다음 페이지) 개수 설정 -->
						<c:set var="scope" value="${pageNum%4}" />
						<c:choose>
							<c:when test="${scope == 0}">
								<c:set var="prev" value="4" />
								<c:set var="next" value="1" />
							</c:when>
							<c:when test="${scope < 5}">
								<c:set var="prev" value="${scope}" />
								<c:set var="next" value="${5-scope}" />
							</c:when>
						</c:choose>
						
						<!-- 이전 버튼 --> 
						<c:if test="${pageNum > 4}"> 
							<fmt:formatNumber value="${(pageNum-1)/4 - (((pageNum-1)/4) % 1)}" type="pattern" pattern="0" var="prevb"/> 
							<c:set value="${(prevb-1)*4 + 1}" var="prevb"/> 
							<a href="<c:url value='NoticeList.no?pageNum=${prevb}'/>">이전</a> 
						</c:if>
			
						<!-- 전 페이지 -->
						<c:if test="${pageNum != 1}">
							<c:set var="j" value="${prev}" />
							<c:forEach var="i" begin="1" end="${prev-1}">
								<c:set var="j" value="${j - 1}" />
								<c:if test="${(pageNum - j) > 0}">
									<a href="<c:url value='NoticeList.no?pageNum=${pageNum - j}'/>" class="num">${pageNum - j}</a>
								</c:if>
							</c:forEach>
						</c:if>
						<!-- 현재 페이지 -->
						<a class="select">${pageNum}</a>
						<!-- 다음 페이지 -->
						<c:if test="${pageNum != endPage}">
							<c:forEach var="k" begin="1" end="${next-1}">
								<c:if test="${endPage >= pageNum+k}">
									<a href="<c:url value='NoticeList.no?pageNum=${pageNum + k}'/>" class="num">${pageNum + k}</a>
								</c:if>
							</c:forEach>
						</c:if>
						
						<!-- 다음 버튼 --> 
						<c:if test="${endPage >= pageNum + next}"> 
							<fmt:formatNumber value="${(pageNum-1)/4 - (((pageNum-1)/4) % 1)}" type="pattern" pattern="0" var="nextb"/> 
							<c:set value="${(nextb+1)*4 + 1}" var="nextb"/> 
							<a href="<c:url value='NoticeList.no?pageNum=${nextb}'/>">다음</a> 
						</c:if>
						<a href="<c:url value='NoticeList.no?pageNum=${endPage}'/>">&gt;&gt;</a>
					</p>				
				</div>
		   </div>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<!-- 메인 페이지 -->

	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>