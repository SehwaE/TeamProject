<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set value="${userID}" var ="userID"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>GTB - QnA </title>

<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="${contextPath }/js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath }/css/noticeWrite.css">
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
		        <h2 class="tit"> QnA </h2>
		    </section>
		    <section id="nav">
		        <div class="navInner boxing">
		            <div class="left">
		                <ul>
		                	<li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
							<li class="home">게시판 > </li>
							<li class="depth2"><a href="${contextPath }/NoticeList.no">QnA</a></li>
		                </ul>
		            </div>
		        </div>
		    </section>
			<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
	    <!--// @ subHeader Top area -->
	    <c:if test="${userID eq null}"><!-- @@@@@@@@@추가 -->
    		<c:redirect url="/UserLogin.do"></c:redirect>
		</c:if>
	    
		<div id="container">
			<div id="content">
				<div class="innerwrap">
					<h1 class="h1_head"> QnA 작성 </h1>
					<form action="./QnaSend.qo" method="post" name="fr" onsubmit="return fun();">
						<input type="hidden" name="receiver" value="sweets1327@naver.com" ><!-- @@@@메일주소 변경해야하지않나요? --><!-- @@@@@@@@@추가 -->
 						<input type="hidden" name="subject" value="Going To Busan - ${sessionScope.userID }님의 문의입니다 " >
						<table class="bbs_default view">
							<tbody>
								<tr>
									<th> Email </th><!-- @@@@@@@@@추가 -->
									<td><input type="text" name="sender" style="width: 100%;" placeholder="회신받을 이메일 주소를 작성하세요"></td><!-- @@@@@@@@@추가 -->
								</tr>
								<tr>
									<th> 내용 </th>
									<td><textarea name="content" cols="133" rows="20" placeholder="내용을 입력하세요"></textarea></td>
								</tr>
							</tbody>
						</table>
							<div class="bbs_btn_wrap">
								<div class="bbs_right">
									<input type="submit" value=" 작 성 ">
									<input type="button" value=" 취 소 " onclick="location.href='./Main.do'">
								</div>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<script type="text/javascript">
		function fun(){
			var fr = document.fr;
			if(fr.sender.value==""){
				alert("보내는 사람 메일 주소를 입력하세요");
				return false;
			}
			if(fr.content.value==""){
				alert("내용을 입력하세요");
				return false;
			}
		}
	</script>
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>