<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userID" value="${userID }"></c:set>
<c:set var="user" value="${ub }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내정보</title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${contextPath }/css/navBlock.css" />
<link rel="stylesheet" type="text/css" href="${contextPath }/css/joinForm.css" />

</head>
<body>
	<div id = "wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		<!-- @ subHeader Top area -->    
		<div id="subVisual">
		    <section id="subVisu" class="submenu-DOM_00000020100600">
		        <h2 class="tit">마이 페이지</h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">회원 관리 > </li>
							<li class="depth2"><a href="${contextPath }/MyPage.do">마이 페이지 </a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
	    <!--// @ subHeader Top area -->
	    
		<div class="container">
			<h2 style="text-align: center">💗  ${userID }님  환영합니다 💗</h2>
			<hr>
			<br>
			<h2 style="text-align: center;">
				<span class="box int_mp">
					<a href="./UserUpdate.do">회원 정보 수정</a>
				</span>
			</h2>
			<br>
			
			<h2 style="text-align: center;">
				<span class="box int_mp">
					<a href="./PwUpdate.do">비밀번호 변경</a>
				</span>
			</h2>
			<br>
			<h2 style="text-align: center;">
				<span class="box int_mp">
					<a href="./UserDelete.do">회원 정보 삭제</a>
				</span>
			</h2>	
			<br>	
			
			<span class="box int_cl"></span>
			<hr>
			<h2 style="text-align: center;">
				<span class="box int_mp2">
					<a href="./Main.do">메인 페이지로</a>
				</span>
			</h2>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>