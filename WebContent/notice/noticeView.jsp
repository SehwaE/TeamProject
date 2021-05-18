<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> 공지사항 </title>

<script src="${contextPath }/js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${contextPath }/css/noticeView.css">
<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
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
		        <h2 class="tit">공지사항</h2>
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
		<div class="container">
			<div class="innerwrap">
				<form action="./NoticeModifyAction.no" method="post">
					<input type="hidden" value="${bbsDetail.bbsID }" name="bbsID">
					<input type="hidden" value="${bbsDetail.userID }" name="userID">
					<table class="bbs_default view">
			  			<colgroup>
			  	   			<col style="width:200px">
			  	   			<col style="width:auto;">
			  			</colgroup>
			     		<tbody>
					   		<tr>
					     		<th>제목</th>
					     		<td>
						    		<h2>${bbsDetail.bbs_title }</h2>  <!-- 제목 -->
						  		</td>
							</tr>
							<tr>
						  		<th>작성자</th>
			    		  		<td>${bbsDetail.userNickName }</td>
			    			</tr>
					   		<tr>
					      		<td colspan="2" title="내용" class="bbs_content">
					       			<c:choose>
			 							<c:when test="${!empty fileName }">
					         				<img id="blah" src="${contextPath }/upload/${fileName}" alt="your image" />
					        			</c:when>
					       			</c:choose>
					  	 	 		<div>${bbsDetail.bbs_content }</div> <!-- //내용 -->
					  	  		</td>
					  		</tr>
					  		<tr>
			              		<th>첨부파일</th>
			              		<td>
			                		<a href="${contextPath }/upload/${fileName}">${fileName}</a>
			              		</td>
			            	</tr>
			       		</tbody>
			   		</table>
		    	</form>
		    	<div class="bbs_btn_wrap clearfix text_center"><!-- @@@@@@@@@추가 -->
		    		<div class="bbs_right">
		    		<c:choose>
	 					<c:when test="${userID eq 'admin' }">
			    			<a href="${contextPath }/NoticeModify.no?bbsID=${bbsDetail.bbsID}">
			    				<img src="./img/btn_list.jpg" alt="수정">
			    			</a>
			    			<a href="javascript:fun();">
			    				<img src="./img/btn_list2.jpg" alt="삭제">
			    			</a>
	 					</c:when>
	 				</c:choose>
		    			<a href="${contextPath }/NoticeList.no">
		    				<img src="https://www.visitbusan.net/images/common/btn_list.jpg" alt="목록">
		    			</a>
		    		</div>
		    	</div><!-- @@@@@@@@@추가 -->
			</div>
		</div>
	    <script type="text/javascript">
	        function readURL(input) {
	            if (input.files && input.files[0]) {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    $('#img').attr('src', e.target.result);
	                }
	                reader.readAsDataURL(input.files[0]);
	            }
	        }
		</script>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>

	<script type="text/javascript">
		function fun(){
			var con = confirm('정말로 삭제하시겠습니까?')
			if(con==true) {
				location.href="./NoticeDelete.no?bbsID="+${bbsDetail.bbsID};
			} else if(con==false) {
				return false;
			}
		}
	</script>
<!-- footer -->
<jsp:include page="../inc/footer.jsp" />
<!-- footer -->
</body>
</html>