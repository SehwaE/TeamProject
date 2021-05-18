<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GTB 이벤트 게시판</title>

<script src="//code.jquery.com/jquery.min.js"></script>
<script type="${contextPath }/js/jquery-3.5.1.js"></script>

<link href="${contextPath }/css/bootstrap.css" rel="stylesheet">
<link href="${contextPath }/css/eventList.css" rel="stylesheet">


</head>
<body>
	<div id="accessibility">
		<a href="#cBody">본문 바로가기</a>
	</div>

	<div id="wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		<!-- @ subHeader Top area -->
		<div id="subVisual">
			<section id="subVisu" class="submenu-DOM_00000020100400">
				<h2 class="tit">이벤트</h2>
			</section>
			<section id="nav">
				<div class="navInner boxing">
					<div class="left">
						<ul>
						    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">이벤트 게시판 ></li>
							<li class="depth2"><a href="${contextPath }/EventList.bd">이벤트</a></li>
						</ul>
					</div>
				</div>
			</section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<!--// @ subHeader Top area -->
		
		<!-- 테이블 시작 -->
		<div class="container">
			<div class="innerwrap">
				<c:forEach var="bs" items="${bbsList }" varStatus="status">
					<div class="main_table">
		 				<div class="paint">
							<c:forEach var="fl" items="${fileList }" varStatus="vs">
			  					<c:if test="${bs.bbsID eq fl.bbsID }">
			   						<img src="${contextPath }/upload/${fl.fileRealName }" style="width: 250px; height: 250px;">
			  					</c:if>
							</c:forEach>
		  				</div> 
			  			<!-- paint 끝 -->	
						<div class="read">
							조회수 : ${bs.bbs_readcount }
						</div>
			      		<div class="clean"></div>
						<div class="option2">
			 				<hr>
			 				<a>${bs.bbs_title }</a>
			 				<hr>
			      		</div>	
			      		<div class="date">
			      			 등록일 : <fmt:formatDate value="${bs.bbs_date }" dateStyle="medium" />
			      		</div>
			    		<!-- 리스트 끝 -->
		        		<!-- detail -->
		        		<button class="detailbtn" onclick="location.href='EventDetail.bd?bbsID=${bs.bbsID}'">상세보기</button>
		      		</div>
		      	<!-- 옵션 끝  -->
				<!-- 테이블 끝 -->
		      	<span class="clean"></span>
				</c:forEach>
				
				<!-- 글쓰기 버튼 -->	
				<c:if test="${userID eq 'admin' }">
			    	<div class="writeBtn">
				 		<button onclick="location.href='EventWrite.bd'">글쓰기</button>
		   	    	</div>
		   	    <span class="clean"></span>
			    <!-- 글쓰기 버튼 -->	
			    </c:if>
			   
			   
	<div>
		<div class="page">
			<c:if test="${currentPage > endPage}">
				<c:set var="page" value="${endPage}" />
			</c:if>
				<a href="<c:url value='EventList.bd?currentPage=${1}'/>">
					<img class="img_prev" src="${contextPath }/img/prev.png">
				</a>
			<!-- 출력할 페이지 수 계산 -->
			<fmt:formatNumber value="${currentPage/4 - (currentPage/4 % 1)}" type="pattern" pattern="0" var="full" />
			<!-- value = 값 / pattern 속성 : 0 = 수치 / type = 데이터형 / var : 사용할 변수명 -->
			<!--ex) 5 페이지의 경우 : 5 / 4 - (5/4 % 1) -->
			<c:set var="full" value="${full * 4}" />
			<!-- set : 변수 설정 -->
			<!-- prev(전 페이지), next(다음 페이지) 개수 설정 -->
			<c:set var="scope" value="${currentPage%4}" />
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
			<c:if test="${currentPage > 4}"> 
				<fmt:formatNumber value="${(currentPage-1)/4 - (((currentPage-1)/4) % 1)}" type="pattern" pattern="0" var="prevb"/> 
				<c:set value="${(prevb-1)*4 + 1}" var="prevb"/> 
				<a href="<c:url value='EventList.bd?currentPage=${prevb}'/>">이전</a> 
			</c:if>

			<!-- 전 페이지 -->
			<c:if test="${currentPage != 1}">
				<c:set var="j" value="${prev}" />
				<c:forEach var="i" begin="1" end="${prev-1}">
					<c:set var="j" value="${j - 1}" />
					<c:if test="${(currentPage - j) > 0}">
						<a class="num" href="<c:url value='EventList.bd?currentPage=${currentPage - j}'/>">${currentPage - j}</a>
					</c:if>
				</c:forEach>
			</c:if>
			<!-- 현재 페이지 -->
			<span class="num">${currentPage}</span>
			<!-- 다음 페이지 -->
			<c:if test="${currentPage != endPage}">
				<c:forEach var="k" begin="1" end="${next-1}">
					<c:if test="${endPage >= currentPage+k}">
						<a class="num" href="<c:url value='EventList.bd?currentPage=${currentPage + k}'/>">${currentPage + k}</a>
					</c:if>
				</c:forEach>
			</c:if>
			<!-- 다음 버튼 --> 
			<c:if test="${endPage >= currentPage + next}"> 
				<fmt:formatNumber value="${(currentPage-1)/4 - (((currentPage-1)/4) % 1)}" type="pattern" pattern="0" var="nextb"/> 
				<c:set value="${(nextb+1)*4 + 1}" var="nextb"/> 
				<a href="<c:url value='EventList.bd?currentPage=${nextb}'/>">다음</a> 
			</c:if>
			<a href="<c:url value='EventList.bd?currentPage=${endPage}'/>">
				<img class="img_next" src="${contextPath }/img/next.png"></a>
		</div>
		<!-- page 끝 -->
	</div>
			   
			</div>
		</div>
	</div>
	<!-- wrap -->
	<div class="clean"></div>
	<!-- 푸터 -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>