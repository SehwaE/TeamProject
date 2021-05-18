<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set value="${bbsDetail }" var="bbsDetail" ></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>후기 게시판 수정</title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath }/css/noticeWrite.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

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
		<div class="row">
	  		<fieldset>
				<form action="./ReviewModifyEnd.ao" method="post" enctype="multipart/form-data">
			 		<input type ="hidden" value="${bbsDetail.bbsID }" name="bbsID">
			 		<input type ="hidden" value = "3" name = "bbs_category">
					<table class="bbs_default view">
		  				<tbody>
   	    					<tr>
    							<th>글제목</th>
    							<td>
  									<input type="text" name="bbs_title" placeholder="제목을 입력해 주세요." value="${bbsDetail.bbs_title }"  style="width:1000px;">
  	  							</td>
  							</tr>
  							<tr>
  								<th>글내용</th>
  								<td>
  									<textarea name="bbs_content" cols="121" rows="30" placeholder="내용을 입력하세요.">${bbsDetail.bbs_content }</textarea>
  									<img src="${contextPath }/upload/${fileName}" alt="your image" />
  								</td>
  							</tr>
  							<tr>
  								<th>첨부파일</th>
  								<td>
  									<input type="file" name="fileName" >
  								</td>
  							</tr>
  						</tbody>
		  			</table>
					<div class="bbs_btn_wrap">
						<div class="bbs_right">
	  						<input type="submit" value=" 수 정 ">
	  						<input type="button" value=" 취 소 " onclick="location.href='./ReviewPage.ao?bbsID=${bbsDetail.bbsID }'">
	  					</div>
					</div>
				</form>
			</fieldset>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>