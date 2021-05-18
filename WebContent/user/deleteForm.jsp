<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="userID" value="${userID }"></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${userID }님 회원 탈퇴 페이지</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="${contextPath }/js/bootstrap.js"></script>
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
		    <section id="subVisu" class="submenu-DOM_00000020100600">
		        <h2 class="tit">회원 삭제</h2>
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
			<form action="./UserDeleteAction.do" method="post">
				<h3 class="join_title"><label for="userID">아이디</label></h3>
				<span class="box int_ot">
					<input type="text" class="int" placeholder="아이디" name="userID" value="${userID }" readonly>
				</span>
				
		       	<h3 class="join_title"><label for="userPass">비밀번호</label></h3>
	           	<input type="password" name="userPass" class="form-control" maxlength=16 placeholder="8~16자까지 입력가능">
				
				<div class="btn_area">
		             <button type="submit" id="btnJoin" onclick="return confirm('정말 삭제하시겠습니까?')">
		                 <span>탈퇴하기</span>
		             </button>
					<button type="button" id="btnJoin" onclick="history.back();" >
						<span>취소하기</span>
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