<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GTB 로그인</title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath }/css/loginForm.css" />

</head>
<body>
	<div id = "wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		
		<!-- @ subHeader Top area -->    
		<div id="subVisual">
		    <section id="subVisu" class="submenu-DOM_00000020100300">
		        <h2 class="tit">로그인</h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                	<li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">회원 관리 > </li>
							<li class="depth2"><a href="${contextPath }/UserLogin.do" title="후기로 갑니다">로그인 </a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
	    <!--// @ subHeader Top area -->
	    <script src="${contextPath}/js/cookie.js"></script>
		<div class="container">
			<form action="./UserLoginAction.do" method="post">
			  <fieldset class="field">
			    <legend>로그인</legend>
			     <div class="login">
				  <h3 class="join_title"><label for="userID">아이디</label></h3>
						<input type="text" class="int" id="userId" placeholder="아이디" name="userID" maxlength="20">
			     </div>
			     

	       		<h3 class="join_title"><label for="userPass">비밀번호</label></h3>
	           		<input type="password" class="pass" name="userPass" id="userPwd" value="" onkeydown="if(event.keyCode==13) javascript:chkEnter();"
	           				maxlength=16 placeholder="8~16자까지 입력가능">
			
				<div class="check">
				 <input type="checkbox" id="idSaveCheck"> 아이디 저장
				 <input type="checkbox" disabled id="pwdSaveCheck" class="no_act"> 비밀번호 저장			 
				</div>
	             	 <button type="button" id="joinBtn" onclick="location.href='UserJoin.do';">회원가입</button>
	            	 <button type="submit" id="loginBtn">로그인</button>
	       	  </fieldset>	
			</form>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>