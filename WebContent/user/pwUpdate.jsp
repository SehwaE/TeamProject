<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="userID" value="${userID }"></c:set>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${userID }님 비밀번호 변경</title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${contextPath }/css/joinForm.css" />

</head>
<body>
	<div id = "wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		<!-- @ subHeader Top area -->    
		<div id="subVisual">
		    <section id="subVisu" class="submenu-DOM_00000020100500">
		        <h2 class="tit">비밀번호 수정</h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">회원 관리 > </li>
							<li class="depth2"><a href="${contextPath }/MyPage.do">마이 페이지 수정 </a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
	    <!--// @ subHeader Top area -->
	    
		<script src="${contextPath }/js/pwcheck.js"></script>
		<div class="container">
			<form method="post" action="./PwUpdateAction.do" onsubmit="return check();" name = "fr">
				<input type="hidden" name="userID" value = "${userID }">
				
				<h3 style="text-align: center;">${userID }님 비밀번호 수정</h3>
				<br>
	
		       	<h3 class="join_title"><label for="userPass">기존 비밀번호</label></h3>
		           	<input type="password" name="userPass" class="form-control" minlength="8" maxlength=16 placeholder="8~16자까지 입력가능" required>
		        <br>
		       	<h3 class="join_title"><label for="userPass2">새로운 비밀번호</label></h3>
		           	<input type="password" id = "pwd1" name="userPass2" class="form-control" minlength="8" maxlength=16 placeholder="8~16자까지 입력가능" required>
		        <br>
		       	<h3 class="join_title"><label for="userPass3">새로운 비밀번호 확인</label></h3>
		           	<input type="password" id = "pwd2" name="userPass3" class="form-control" minlength="8" maxlength=16 placeholder="한번 더 입력하세요" required>
	           	<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
		        <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
				<div class="btn_area">
		             <button type="submit" id="loginBtn">
		                 <span>비밀번호 수정하기</span>
		             </button>
       			</div>
			</form>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>