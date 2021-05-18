<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이벤트 글쓰기</title>

<script type="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="${contextPath }/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">

 .main_table {	
	width: 800px;
	margin: auto;
}

 .content {
 	margin: 10px;
 }
 
 .e_title, .month {
 	margin-left: 5px;
 }

 
 .content textarea {
  	width: 100%;
  	resize: none;
  	line-height: 1.6;
  	overflow-y: hidden;
 }
 
 .ReviewTitle, .ContentTitle, .MonthTitle {
 	font-size: 17px;
 	font: bold;
 	margin-top: 10px;
 	padding: 10px;
 	margin-right: 5px;
 	margin-left: 5px;
 }
 
 .addBtn, .backBtn {
 	border-radius: 12px;
 	border: none;
 	background-color: #1442a1;
 	color: white;
 	font-size: 18px;
 	padding: 10px;
 }
 
 .btn1 {
 	float: right;
  	margin-top: 5px;
 }
 
 .btn2 {
 	float: right;
    width: 18%;
    margin-top: 5px;
    margin-left: 3px;
 }
	
</style>
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
				<h2 class="tit">이벤트 글쓰기</h2>
			</section>
			<section id="nav">
				<div class="navInner boxing">
			    <div class="left">
			    	<ul>
			    	    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
						<li class="home">게시판 > </li>
						<li class="depth2"><a href="${contextPath }/EventList.bd">이벤트</a></li>
			        </ul>
			    </div>
			    </div>
			</section>
				<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<hr style = "margin: 2.5% 5% ">
		<!--// @ subHeader Top area -->

		<!-- 메인 페이지 -->
		<div class="container">
			<div class="innerwrap">
				<form name="form" action="./EventWriteAction.bd" method="post" enctype="multipart/form-data">
					<input type="hidden" name="bbs_category" value="2">
					<table border="1" class="main_table"> 
						<tr>
							<td>  
								<div class="e_title">
									<label class="ReviewTitle">이벤트 제목</label>
		 	    					<input type="text" name="bbs_title" size="98px"  class="form-group" required placeholder="이벤트 목적이나 프로모션에 대해 언급하세요">
								</div>
								<div class="month">
									<label class="MonthTitle">이벤트 시기는 언제인가요?</label> 
									<div class="month_content">
										<select name="start_month" required>
								 	    	<option selected="selected">선택하세요</option>
											<%
											  Calendar cal = Calendar.getInstance();
										  
										  	  int year = cal.get(Calendar.YEAR);
										  	  int month = cal.get(Calendar.MONTH)+1;
										  		
										  	  for(int i=year; i<year+1; i++) {
										  		  for(int m=month; m<=12; m++) {
										  			  if( i <= year) {
										  			  	out.print("<option value = '"+i+"년 "+m+"월"+"'>" +i+"년 "+m+"월"+ "</option>");  
										  			  }
										  			  else if (m <= month) {
										  				 out.print("<option value = '"+i+"년 "+m+"월"+"'>" +i+"년 "+m+"월"+ "</option>");  
										  			}
										  		  }
										  		}
											%>
										</select> ~
	 	   								<select name="end_month" required>
	 	    								<option selected="selected">선택하세요</option>
											<%
												Calendar cal2 = Calendar.getInstance();
											  	int year2 = cal2.get(Calendar.YEAR);
											  	int month2 = cal2.get(Calendar.MONTH)+1;
											  		
											  	int m = 0;
												for(int i=year2; i<year2+2; i++) {
													if(i == 2021) {
														for( m=month2; m<=12; m++) {
											  		  		out.print("<option value = '"+i+"년 "+m+"월"+"'>" +i+"년 "+m+"월"+ "</option>");  
											  		  	}
													} else {
													for(m=1; m<=12; m++){
														out.print("<option value = '"+i+"년 "+m+"월"+"'>" +i+"년 "+m+"월"+ "</option>");  
													}
												}
											}
										%>
		 	   						</select>
		 	   						</div>	
		 	   						<!-- month_content -->
		 	   					</div>
		 	   					<!-- month -->	
			 	   				<div class="content">
			 	  					<label class="ContentTitle">이벤트 내용</label>
			 	   					<textarea name="bbs_content" required></textarea>
			 	   				</div>			 
			 	  				<div class="addFile">
			 	  					<input type="file" name="fileName">
			 	  				</div>	
							</td>	
						</tr>
					</table>	 
					<div class="btn2">
						<button class="backBtn" onclick="history.back();">취소</button>
					</div>
					<div class="btn1">
						<button class="addBtn">등록하기</button>
					</div>
				</form>	
			</div>
		</div>
	</div>
	<!-- container -->
	<!-- wrap -->	

	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
	
 <script type="text/javascript">
	 $(document).ready(function() {
		$('.content').on('keyup', 'textarea', function (e) {
			$(this).css('height', 'auto');
			$(this).height(this.scrollHeight);
		});
		$('.content').find('textarea').keyup();
	});
 </script>
	
</body>
</html>