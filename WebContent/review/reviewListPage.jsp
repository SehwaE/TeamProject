<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>후기 게시판 리스트</title>

<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="${contextPath }/js/bootstrap.js"></script>
<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">

 <style type="text/css">
	.sc_fr {
		padding: 2px;
 	}
  .writeBtn {
  	border: none;
  	border-radius: 12px;
  	padding: 11px;
    background-color: #1442a1;
    color: #fff;
    margin-left: 95%;
  }
  
 .scin {
 	width:700px;
 	margin:0 auto;
 	height:50px;
 	background:#fff;
 	border-radius:50px;
 	border: 4px solid #1442a1;
 	margin-left	:20%;
 }
 
.st {
    border-radius: 50px;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    float: left;
    /* color: #fff; */
    /* background-color: #1442a1; */
    /* padding:10px 0; */
    height:40px;
    text-align: center;
}

 .scin input[type="text"]{
	 padding:10px 0;
	 width: 70%;
	 font-size: 17px;
	 height: 30px;
	 border: 0px;
	 margin: 6px 0 0 2%;
	 float: left;
 } 
  
  .scBtn {
  	border: none;
  	border-radius: 50px;
  	padding: 11px;
    background-color: #1442a1;
    color: #fff;
    float: right;
    font-size: 17px;
    height: 40px;
  }
 </style>

</head>
<body>
	<!-- 네비게이션 -->
	<jsp:include page="../inc/nav.jsp" />
	<!-- 네비게이션 -->
	
	<!-- @ subHeader Top area -->    
	<div id="subVisual">
	    <section id="subVisu" class="submenu-DOM_00000020100700">
	        <h2 class="tit">게시판</h2>
	    </section>
	    <section id="nav">
	        <div class="navInner boxing">
	            <div class="left">
	                <ul>
	                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
						<li class="home">게시판 > </li>
						<li class="depth2"><a href="${contextPath }/ReviewList.ao" title="후기로 갑니다">후기 게시판 </a></li>
	                </ul>
	            </div>
	        </div>
	    </section>
		<script src="${contextPath }/js/subjs.js"></script>
	</div>
	<hr style = "margin: 2.5% 5% ">
    <!--// @ subHeader Top area -->
	<div class="container">
		<div class="row"><br>
			<!-- 검색 기능 -->
			<div>
				<div class="scin">
					<form action="./ReviewList.ao" method="post" class="sc_fr">
						<select name = "sc_ck" class= "st">
							<option value = "null">전체목록</option>
							<option value = "userID">아이디</option>
							<option value = "userNickName">닉네임</option>
							<option value = "bbs_title">글제목</option>
							<option value = "bbs_content">글내용</option>
						</select>
						<input type="text" name = "search" value="" placeholder="검색어를 입력해주세요" >
			      		<input type="submit" class="scBtn" value = "검색">
					</form>	   		
				</div>
	      		<span style = "float: none;"></span>
			</div>
			
			<c:if test="${sc_ck ne null}">
				<div><br>
					<h3 style="text-align: center;"> 검색 결과는 총 ${totalRowCount }개 입니다. </h3>
				</div>
			</c:if>
			<!-- 검색 기능 -->
		
		
		    <button class="writeBtn" onclick="location.href='${contextPath}/ReviewAdd.ao'">글쓰기</button>
		<hr>
			<div class="_2z6nI">
				<article class="ySN3v">
					<div style="-webkit-box-orient: horizontal; -webkit-box-direction: normal; -webkit-flex-direction: row; -ms-flex-direction: row; flex-direction: row;">
						<c:choose>
							<c:when test="${sc_ck eq null}">
								<c:forEach var="bbs" items="${bbsList}" varStatus="status"> 
									<div style="display: inline-block;">
										<c:forEach items="${fileName }" var="fn" varStatus="vs">
											<c:if test="${bbs.bbsID eq fn.bbsID}">
												<a href="./ReviewPage.ao?bbsID=${bbs.bbsID }">
												<img src="${contextPath}/upload/${fn.fileRealName}" width="380" height="380" style="margin-bottom: 5px">
												</a>
											</c:if>
										</c:forEach>
									</div>
									<div style="display: inline-block; "></div>
									<c:if test="${((status.index+1) mod pagePerCol) == 0 }">
					</div>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="bbs" items="${bbsList2}" varStatus="status">
									<div style="display: inline-block;">
										<c:forEach items="${fileName }" var="fn" varStatus="vs">
											<c:if test="${bbs.bbsID eq fn.bbsID}">
												<a href="./ReviewPage.ao?bbsID=${bbs.bbsID }">
													<img src="${contextPath}/upload/${fn.fileRealName}" width="380" height="380" style="margin-bottom: 5px">
												</a>
											</c:if>
										</c:forEach>
									</div>
									<div style="display: inline-block; "></div>
									<c:if test="${((status.index+1) mod pagePerCol) == 0 }">
					</div>
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</article>
			</div>
			<hr>
			<div id="list">
				<p class="nearPaging">
				<c:choose>
					<c:when test="${sc_ck eq null}">
						<c:if test="${pageNum > endPage}">
							<c:set var="page" value="${endPage}" />
						</c:if>
						<a href="<c:url value='ReviewList.ao'/>">&lt;&lt;</a>
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
							<a href="<c:url value='ReviewList.ao?pageNum=${prevb}'/>">이전</a> 
						</c:if>
			
						<!-- 전 페이지 -->
						<c:if test="${pageNum != 1}">
							<c:set var="j" value="${prev}" />
							<c:forEach var="i" begin="1" end="${prev-1}">
								<c:set var="j" value="${j - 1}" />
								<c:if test="${(pageNum - j) > 0}">
									<a href="<c:url value='ReviewList.ao?pageNum=${pageNum - j}'/>" class="num">${pageNum - j}</a>
								</c:if>
							</c:forEach>
						</c:if>
						<!-- 현재 페이지 -->
						<a class="select">${pageNum}</a>
						<!-- 다음 페이지 -->
						<c:if test="${pageNum != endPage}">
							<c:forEach var="k" begin="1" end="${next-1}">
								<c:if test="${endPage >= pageNum+k}">
									<a href="<c:url value='ReviewList.ao?pageNum=${pageNum + k}'/>" class="num">${pageNum + k}</a>
								</c:if>
							</c:forEach>
						</c:if>
						
						<!-- 다음 버튼 --> 
						<c:if test="${endPage >= pageNum + next}"> 
							<fmt:formatNumber value="${(pageNum-1)/4 - (((pageNum-1)/4) % 1)}" type="pattern" pattern="0" var="nextb"/> 
							<c:set value="${(nextb+1)*4 + 1}" var="nextb"/> 
							<a href="<c:url value='ReviewList.ao?pageNum=${nextb}'/>">다음</a> 
						</c:if>
						<a href="<c:url value='ReviewList.ao?pageNum=${endPage}'/>">&gt;&gt;</a>
						</c:when>
						
						<c:otherwise>
							<c:if test="${pageNum > endPage}">
								<c:set var="page" value="${endPage}" />
							</c:if>
							<a href="<c:url value='ReviewList.ao'/>">&lt;&lt;</a>
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
								<a href="<c:url value='ReviewList.ao?pageNum=${prevb}&sc_ck=${sc_ck}&search=${search }'/>">이전</a> 
							</c:if>
				
							<!-- 전 페이지 -->
							<c:if test="${pageNum != 1}">
								<c:set var="j" value="${prev}" />
								<c:forEach var="i" begin="1" end="${prev-1}">
									<c:set var="j" value="${j - 1}" />
									<c:if test="${(pageNum - j) > 0}">
										<a href="<c:url value='ReviewList.ao?pageNum=${pageNum - j}&sc_ck=${sc_ck}&search=${search }'/>" class="num">${pageNum - j}</a>
									</c:if>
								</c:forEach>
							</c:if>
							<!-- 현재 페이지 -->
							<a class="select">${pageNum}</a>
							<!-- 다음 페이지 -->
							<c:if test="${pageNum != endPage}">
								<c:forEach var="k" begin="1" end="${next-1}">
									<c:if test="${endPage >= pageNum+k}">
										<a href="<c:url value='ReviewList.ao?pageNum=${pageNum + k}&sc_ck=${sc_ck}&search=${search }'/>" class="num">${pageNum + k}</a>
									</c:if>
								</c:forEach>
							</c:if>
							
							<!-- 다음 버튼 --> 
							<c:if test="${endPage >= pageNum + next}"> 
								<fmt:formatNumber value="${(pageNum-1)/4 - (((pageNum-1)/4) % 1)}" type="pattern" pattern="0" var="nextb"/> 
								<c:set value="${(nextb+1)*4 + 1}" var="nextb"/> 
								<a href="<c:url value='ReviewList.ao?pageNum=${nextb}&&sc_ck=${sc_ck}&search=${search }'/>">다음</a> 
							</c:if>
							<a href="<c:url value='ReviewList.ao?pageNum=${endPage}&sc_ck=${sc_ck}&search=${search }'/>">&gt;&gt;</a>
						</c:otherwise>
					</c:choose>
				</p>				
			</div>
		</div>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
<!-- footer -->
<jsp:include page="../inc/footer.jsp" />
<!-- footer -->
</body>
</html>