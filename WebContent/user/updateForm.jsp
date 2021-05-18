<%@page import="com.tp.users.db.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${userID }님 회원 정보 수정</title>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 
<script src="${contextPath }/js/bootstrap.js"></script>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${contextPath }/css/updateForm.css" />

</head>
<body>
	<div id = "wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
		<!-- @ subHeader Top area -->    
		<div id="subVisual">
		    <section id="subVisu" class="submenu-DOM_00000020100500">
		        <h2 class="tit">회원 정보 수정</h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">회원 관리 > </li>
							<li class="depth2"><a href="${contextPath }/MyPage.do">마이 페이지 수정</a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
	    <!--// @ subHeader Top area -->
		<div class = "container">
			<c:set var="user" value="${ub }"></c:set>
			<h1 align="center">
				<c:choose>
					<c:when test="${user.userProfile eq null }">
						<img style="width: 50px;" id="preview-image" src="${contextPath }/img/profile.png">
					</c:when>
					<c:otherwise>
						<img style="width: 50px;" id="preview-image" src="${contextPath }/upload/${user.userProfile}">
					</c:otherwise>
				</c:choose>
			${user.userID }님 정보 수정</h1>
			<br>
			<script src="${contextPath }/js/userUpdateCk.js"></script>
			<form action="./UserUpdateAction.do" method="post" name="fr" enctype="multipart/form-data" onsubmit="return check_value()">
				
		    	<input type="hidden" name="userID" class="int" value="${user.userID }" >
				
				<div class="incd">
					<h3 class="join_title"><label for="int_pass">비밀번호</label></h3>
					<input type="password" name="userPass" id="pwd1" class="int" minlength="8" maxlength="16" placeholder="비밀번호를 입력하세요">
				</div>
				
				<div class="incd">
				    <h3 class="join_title"><label for="userPass2">비밀번호 재확인</label></h3>
				    <input type="password" name="userPass2" id="pwd2" class="int" minlength="8" maxlength="16" placeholder="8~16글자 입력 가능">
			    	<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
					<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
				</div>
				
			    <div class="incd">
					<h3 class="join_title"><label for="userName">이름</label></h3>
					<span class="box int_ot">
						<input type="text" name="userName"  class="int" readonly value="${user.userName }">
					</span>
				</div>
				
				<div class="incd">
					<h3 class="join_title"><label for="userEmail">본인확인 이메일<span>(선택)</span></label></h3>
					<span class="box int_id">
				    	<input type="email" name="userEmail"  id="userEmail" class="int_id" onkeydown="inputEmailCheck()" value="${user.userEmail }">	
					</span>
					<span class="box int_ck">
				    	<input type="button" value="중복확인" id="checkBtn" class="ck_Btn" onclick="openEmailChk()" >
				    </span>
				    <span class="box int_cl"></span>
				    <input type="hidden" name="emailDuplication" value="emailUnCheck">
				</div>
				
				<div class="incd">
					<h3 class="join_title"><label for="postcode">주소</label></h3>
	                 <div class="addr_box">
	                	<input type="text" id="postcode" name="postcode" value = "${user.userAddr.split(',')[0] }" readonly placeholder="우편번호" maxlength="20">
	                <span class="addr_ck">
	                    <input type="button" onclick="execDaumPostcode()" class="ck_Btn" value="우편번호 찾기"><br>
	                </span>
						<span class="box int_cl"></span>
						<span class="box int_ot">
							<input type="text" id="add1" name="add1" class="int" placeholder="도로명주소" readonly value="${user.userAddr.split(',')[1] }">
						</span>
						<span class="box int_ot">
							<input type="text" id="add2" name="add2" class="int" placeholder="지번주소" readonly value="${user.userAddr.split(',')[2] }">
						</span>
						<span class="box int_ot">
							<input type="text" id="add3" name="add3" class="int" placeholder="상세주소" value="${user.userAddr.split(',')[3] }">
						</span>
						<input type="hidden" id="add4" name="add4" placeholder="참고항목" value="${user.userAddr.split(',')[4] }">
						<span id="guide" style="color:#999;display:none"></span>
						<script src="${contextPath }/js/postCode.js"></script>
					</div>
				</div>		  
					  
				<div class="incd">
					<h3 class="join_title"><label for="userPhone">휴대전화</label></h3>
					<span class="box int_ot">
						<input type="text" name="userPhone" id="userPhone" class="int" value="${user.userPhone }">
					</span>
				</div>
				
				<div>
	                <h3 class="join_title"><label for="userNickName">닉네임</label></h3>
	                <span class="box int_id">
	                    <input type="text" name="userNickName" class="int_id" value="${user.userNickName }" onkeydown="inputNickChk()">
	                </span>
	                <span class="box int_ck">
				        <input type="button" value="중복확인" class="ck_Btn" onclick="nick_check()">	
	                </span>
	                <span class="box int_cl"></span>
				  		<input type="hidden" name="nickDuplication" value="nickUncheck" >
	            </div>
	            
	            <div class="fmr67">
					<span class="box int_id">
						<label class="up_Btn" for="userProfile">
						  유저 프로필 수정
						</label>
						<input type="file" name="userProfile" id="userProfile" style="display: none;">
					</span>
					<span class="box int_cl"></span>
				</div>
				
				<div class="btn_area">
					<button type="submit" id="btnJoin"  >
						<span>수정하기</span>
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

