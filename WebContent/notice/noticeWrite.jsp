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
<title> 공지사항 작성 </title>

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
		        <h2 class="tit">공지사항 글쓰기</h2>
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

  		<c:if test="${userID ne 'admin' }">
    		<c:redirect url="/UserLogin.do"></c:redirect>
		</c:if>

		<div id="container">
			<div id="content">
				<div class="innerwrap">
					<h1 class="h1_head"> 공지 작성 </h1>
					<hr>
					<form action="./NoticeWriteAction.no" method="post" name="fr" enctype="multipart/form-data" onsubmit="return fun();">
						<input type="hidden" name="bbs_category" value="1">
						<input type="hidden" name="userID" value="${sessionScope.userID }">
						<table class="bbs_default view">
							<tbody>
								<tr>
									<th>글제목</th>
									<td><input type="text" name="bbs_title" placeholder="글 제목을 입력하세요" style="width: 100%;" ></td><!-- @@@@@@@@@변경 -->
								</tr>
								<tr>
									<th>글내용</th>
									<td><textarea name="bbs_content" cols="130" rows="25" placeholder="내용을 입력하세요"></textarea></td><!-- @@@@@@@@@변경 -->
								</tr>
								<tr>
									<th>첨부파일</th>
									<td><input type="file" name="fileName"></td>
								</tr>
							</tbody>
						</table>
						<c:choose>
							<c:when test="${userID eq 'admin' }">
								<div class="bbs_btn_wrap">
									<div class="bbs_right">
										<input type="submit" value=" 작 성 ">
										<input type="button" value=" 취 소 " onclick="location.href='./NoticeList.no'">
									</div>
								</div>
							</c:when>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<script type="text/javascript">
		function fun(){
			var fr = document.fr;
			if(fr.bbs_title.value==""){
				alert("글 제목을 입력하세요");
				return false;
			}
			if(fr.bbs_content.value==""){
				alert("글 내용을 입력하세요");
				return false;
			}
		}
	</script>
<!-- footer -->
<jsp:include page="../inc/footer.jsp" />
<!-- footer -->
</body>
</html>