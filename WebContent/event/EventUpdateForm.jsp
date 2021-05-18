<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이벤트 게시판 수정</title>

<script src="${contextPath }/js/bootstrap.js"></script>
<link href="${contextPath }/css/bootstrap.css" rel="stylesheet">
<link href="${contextPath }/css/eventUpdate.css" rel="stylesheet">

</head>
<body>

	<div id="wrap">
		<!-- 네비게이션 -->
		<jsp:include page="../inc/nav.jsp" />
		<!-- 네비게이션 -->
	
		<!-- @ subHeader Top area -->
		<div id="subVisual">
			<section id="subVisu" class="submenu-DOM_00000020100300">
				<h2 class="tit">이벤트</h2>
			</section>
			<section id="nav">
				<div class="navInner boxing">
					<div class="left">
						<ul>
							<li class="home">이벤트 게시판 ></li>
							<li class="depth2"><a href="${contextPath }/EventList.bd">이벤트
								리스트</a></li>
						</ul>
					</div>
				</div>
			</section>
				<script src="${contextPath }/js/subjs.js"></script>
		</div>
		<!--// @ subHeader Top area -->
	
		<c:set var="bs" value="${bbsList }"></c:set>

		<div class="container">
			<div class="innerwrap">
				<form name="form" action="./EventUpdateAction.bd" method="post" enctype="multipart/form-data">
					<input type="hidden" name="bbsID" value="${bs.bbsID }">
					<input type="hidden" name="bbs_category" value="${bs.bbs_category }">
					<table border="1" class="main_table">
						<tr>
							<td>
								<div class="e_title">
									<label class="ReviewTitle">이벤트 제목 : </label>
									<input type="text" name="bbs_title" size="60px" value="${bs.bbs_title }">
								</div>
								<div class="month">
									<label class="MonthTitle">이벤트 진행 시기 : </label>
									<select name="start_month" required="required">
										<option>선택하세요</option>
										<option selected>${bs.start_month }</option>
											<%
												Calendar cal = Calendar.getInstance();
				
												int year = cal.get(Calendar.YEAR);
												int month = cal.get(Calendar.MONTH) + 1;
				
												for (int i = year; i < year + 1; i++) {
													for (int m = month; m <= 12; m++) {
														if (i <= year) {
															out.print("<option value = '"+i+"년 "+m+"월"+"'>" + i + "년 " + m + "월" + "</option>");
														} else if (m <= month) {
															out.print("<option value = '"+i+"년 "+m+"월"+"'>" + i + "년 " + m + "월" + "</option>");
														}
													}
												}
											%>
										</select> ~ 
										<select name="end_month" required="required" value="${bs.end_month }">
											<option>선택하세요</option>
											<option selected>${bs.end_month }</option>
											<%
												Calendar cal2 = Calendar.getInstance();
				
												int year2 = cal2.get(Calendar.YEAR);
												int month2 = cal2.get(Calendar.MONTH) + 1;
				
												int m = 0;
				
												for (int i = year2; i < year2 + 2; i++) {
													if (i == 2021) {
														for (m = month2; m <= 12; m++) {
															out.print("<option>" + i + "년 " + m + "월" + "</option>");
														}
													} else {
														for (m = 1; m <= 12; m++) {
															out.print("<option>" + i + "년 " + m + "월" + "</option>");
														}
													}
												}
											%>
										</select> 
								</div>
								<div class="img">
									<img  width="600px" src="${contextPath }/upload/${fileName}">
								</div>
								<div class="content">
									<label class="ContentTitie">이벤트 내용</label> 
									<textarea name="bbs_content">${bs.bbs_content }</textarea>
								</div>
								<input type="file" name="fileName">
							</td>
						</tr>
					</table>
					  <div class="btn1">
					  <button class="delBtn" onclick="return fun();"><a href="EventDelete.bd?bbsID=${bs.bbsID}">삭제하기</a></button>
					  </div>
					 <div class="btn2">
					  <button class="editBtn">수정하기</button>
					  </div>
				</form>
			</div>
		</div>
	</div>
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
 
 <script type="text/javascript">
		function fun(){
			var con = confirm('정말로 삭제하시겠습니까?')
			if(con==true) {
				location.href="./EventDelete.bd?bbsID="+${bs.bbsID};
			} else if(con==false) {
				return false;
			}
		}
	</script>
	
</body>
</html>