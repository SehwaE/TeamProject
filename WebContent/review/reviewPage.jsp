<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>후기 게시판</title>

<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="${contextPath }/js/subjs.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${contextPath }/js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath }/css/noticeWrite.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 


<script type="text/javascript">
	$(function(){
		$('#likeBtn').click(function() {
			var bbsID = $('#BBSID').attr('value');
			$.ajax({
				url: "${pageContext.request.contextPath}/like.ao?bbsID="+bbsID,
				type: "get" ,		 			
				success: function(data) {
					var data2 = data.split(",")[0];
					var data3 = data.split(",")[1];
					if(data2 == 1){
						$('#likeImg').attr('src','./img/heart.png');
						$('#likeResult').text(data3);  
					} else if(data2 == 0) {
						$('#likeImg').attr('src','./img/like.png');
						$('#likeResult').text(data3);  
					} else {
						alert(data);
					}
				 },
		 		error: function() {
		 			alert("error");	
		 		}
		 	});
		});
	});
</script> 
</head>
<body>
	<!-- 네비게이션 -->
	<jsp:include page="../inc/nav.jsp" />
	<!-- 네비게이션 -->
	<!-- @ subHeader Top area -->    
	<div id="subVisual">
	    <section id="subVisu" class="submenu-DOM_00000020100300">
	        <h2 class="tit">게시판</h2>
	    </section>
	    <section id="nav">
	        <div class="navInner boxing">
	            <div class="left">
	                <ul>
						<li class="home">게시판 > </li>
						<li class="depth2"><a href="${contextPath }/ReviewList.ao" title="후기로 갑니다">후기 게시판 </a></li>
	                </ul>
	            </div>
	        </div>
	    </section>
	</div>
	<hr style = "margin: 2.5% 5% ">
    <!--// @ subHeader Top area -->
	<div class="container">
		<div id="content">
	  		<div class="innerwrap">
	    		<!-- <h1 class="h1_head"> 리뷰 </h1> -->
		 		<form action="./BbsModifyEnd.bo" method="post">
				 	<c:set value="${bbsDetail }" var="bbsDetail" />  
					<c:set value="${likeCheck }" var="likeCheck" />
					<c:set value="${likeSum }" var="likeSum" />
					 
					<input type="hidden" value="${bbsDetail.bbsID }" name="bbsID" id="BBSID">
					<input type="hidden" value="${bbsDetail.userID }" name="userID">
					<input type = "hidden" value = "3" name = "bbs_category">
					<table class="bbs_default view">
						<colgroup>
				   			<col style="width:200px">
				   			<col style="width:auto;">
						</colgroup>
						<tr>
							<td colspan="6">
								<div><h4 class="tit">  ${bbsDetail.bbs_title }</h4></div>   
							</td>
						</tr>	 
						<tr>
							<td style="text-align: center;"><span  style="font-weight: bold;">닉네임</span>  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  ${bbsDetail.userNickName } </td>
							<td style="text-align: center;"><span  style="font-weight: bold;">작성일</span>   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <fmt:formatDate value="${bbsDetail.bbs_date }"  pattern="yyyy.MM.dd  HH:mm" /></td>
							<td style="text-align: center;"><span  style="font-weight: bold;">조회</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${bbsDetail.bbs_readcount }</td>
							<td style="text-align: right;">
								<c:if test="${userID eq bbsDetail.userID}">
			        				<input type="button"  style="padding: 3px 7px;  border-radius: 5px;" value="수 정" onclick="location.href='./ReviewModify.ao?bbsID=${bbsDetail.bbsID}'">
									<input type="button"    style="padding: 3px 7px;  border-radius: 5px;" onclick="return fun();" value="삭 제">
								</c:if>
							</td>
	 					</tr>
				<!--  -------------------내용부분-------------------- -->
	 		 			<tr>
							<td colspan="6">
				   				<br><img id="blah" src="${contextPath }/upload/${fileName}" style = "height : 500px; " alt="your image" /><br><br><br>
	 			  				<div >${bbsDetail.bbs_content }</div> <!-- //내용 -->
		  	 	   				<br><br><br>
		  	 	   			</td>
	 		 			</tr>
		  	 	   				
 						<tr style="border-top: 10px solid white; ">	
							<td colspan="6">
			  	   				<a href="${contextPath }/ReviewList.ao?sc_ck=userNickName&search=${bbsDetail.userNickName}"><b style="font-size: 17px; font-weight: bold;">${bbsDetail.userNickName }</b> 님의 게시글 더보기</a><br><br>
 						
 						<!--  좋아요---------------------------------------- -->		  	
 						
 							</td>
 						</tr>
 						<tr style="border-top: 10px solid white; ">	
 							<td colspan="6" >
				  				<c:choose>
									<c:when test="${likeCheck ==0 }">
				 						 
				 						<button type="button" id="likeBtn" style="margin-left: 50%;  outline-style: none; "  >
				 							<img id="likeImg" alt="Like" src="./img/like.png" width="20" height="20">
				 							<div id="likeResult" >${likeSum }</div>
				 						</button>
				 						 
				 					</c:when>
				 					<c:when test="${likeCheck ==1 }">
				 						<button type="button" id="likeBtn" style="margin-left: 50% ; outline-style: none;  " >
				 							<img id="likeImg" alt="Like" src="./img/heart.png" width="20" height="20">
				 							<div id="likeResult">${likeSum }</div> 
				 						</button>
				 					</c:when>
				   				</c:choose>
				   				<br><br>
							<!--  좋아요 ------------------------------------------------->	
							</td>
	 		 			</tr>
					</table>
				</form>
				
				<br>
		
		
	<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& -->	
	<!-- &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& -->	
				
				
	 	<!-- ---------댓글 리스트-------------------------------------------- -->
				   
				   
				   <table class="bbs_default view">
			 		  <h3> 댓글 </h3>
			 			<br>
 			 			<c:forEach items="${cmtList }" var="ct"  >
 			 			
 			 			
 			 			
 			 	 <!------------- 대댓글 수정/삭제 버튼----------------------------------->
 			 			  <tr class="'cmt${ct.cmtID }'">
							<td width="50%">
 			 			  	<c:if test="${ct.cmt_seq != 0 }">
 			 			  		 ┗ &nbsp;
 			 			  	</c:if>
 			 			  	${ct.cmt_content }
 			 			  	</td>
	 			 			<td width="10%" >${ct.userID}</td>
							<td width="15%" style="text-align: center;"><fmt:formatDate  value="${ct.cmt_date }"  pattern="yyyy.MM.dd  HH:mm" /></td>  
							<td width="25%"  style="text-align: right;">
		<!------------- 대댓글 수정/삭제 버튼----------------------------------->
								<c:if test="${userID eq ct.userID}">
			        				<div>
			        				 <input type = "hidden" value="${bbsDetail.bbsID }" name = "bbsID" id="bbsID" >
			        				 <input type = "hidden" value="${ct.cmtID }" name = "cmtID" id="cmtID"  >
			        				 <input type = "hidden" value="${ct.cmt_seq }" name = "cmt_seq"  >
			        				 <input type = "hidden" value="${ct.cmt_lev }" name = "cmt_lev"   >
			        				 <input type = "hidden" value="${ct.cmt_ref }" name = "cmt_ref"  >
			        				 <input   type="button" style="padding: 3px 7px; border-radius: 5px; "  value="수 정" onclick="location.href='./CmtModify.cmt?bbsID=${bbsDetail.bbsID}'">
									 <input   type="button" style="padding: 3px 7px;  border-radius: 5px;"  value="삭 제" onclick="location.href='./CmtDelete.cmt?cmtID=${ct.cmtID}&bbsID=${bbsDetail.bbsID}'">
								</c:if>  
									<!-- 로그인 안되어있으면 제어 -->
								<c:if test="${not empty userID}">
				<!-------------  이거 윗줄 봐라----------------------------------->		  
				<!----------------여기버튼이 테이블안에있는 댓글 버튼--------------------->		  
								   <input class="BTNBTN" id="${ct.cmtID }" type="button"  style="padding: 3px 7px;  border-radius: 5px;" value="댓 글" >
								</c:if>
							 </td>
						  </tr>  
						  
				<!-------------  대댓----------------------------------->		  
				<!-------------  ----------------------------------->		  
						   
						    <tr id="RERERE${ct.cmtID }" class="REPLYREPLY">
						      <td colspan="6"  >
						      		<form action="./CmtReplyAdd.cmt" method="post">
										 <input type = "hidden" value="${bbsDetail.bbsID }" name = "bbsID"  >
										 <input type = "hidden" value="${ct.cmt_seq }" name = "cmtSeq"   >
										 <input type = "hidden" value="${ct.cmt_ref }" name = "cmtRef"   >
										 <input type = "hidden" value="${ct.cmt_lev }" name = "cmtLev"   >
										 <input type = "hidden" value="${ct.cmtID   }" name = "cmtID"   >
										<textarea  placeholder="댓글의 댓글을 입력하세요." name="cmtReply" rows="5" cols="145" style="  border-radius: 5px; width: 100%;"></textarea>
									    	<div class="bbs_btn_wrap" style="margin: 10px 10px;">
												<div class="bbs_right"> 
													<input type="submit" style="border-radius: 5px; font-size: 1em" value="등 록">
												</div>	
										    </div>			
									</form>
						      </td>
						    </tr>
						    
 			 			</c:forEach>
					</table>
				
				<br> 
	<!------------- 일단댓글 버튼  창  -------------------------------------------->		
		  		<div class="bbs_btn_wrap" style="margin: 10px 10px;">
						<div class="bbs_right"> 
							<input id="cmtReP" type="button"  style="padding: 3px 7px;  border-radius: 5px; padding: 7px;" value="댓글작성"  > 
				 		</div>	
				</div>
			 
			<br>
		<!-- 일반 댓글창 00000000000000000000000000000000000000000000000 -->
				<div  id="RERE">
					<c:if test="${not empty userID}">
						<form  action="./CmtAdd.cmt" method="post" name="fr" onsubmit="return writeCk();">
							 <input type = "hidden" value="${bbsDetail.bbsID }" name = "bbsID"  id="CBBSID"> 
							<textarea  style="width: 100%; "  name="cmt_content" cols="145" rows="5" placeholder="댓글을 입력하세요."></textarea><br>
							 <div id="cmtBtn" class="bbs_btn_wrap" style="margin: 10px 10px;">
								<div class="bbs_right"> 
									<input  type="submit"  style="padding: 3px 7px; border-radius: 5px;" value="등 록" >
							   </div>
							 </div>  
						</form>
				   	</c:if>
				   	
				   	
				</div>
				   	
					 <div class="bbs_btn_wrap">
						<div class="bbs_right">
							<input   type="button" style="border-radius: 5px;" onclick="location.href='./ReviewList.ao'" value="목 록">
	       				</div>
					</div>
		<!-- 일반댓글창 00000000000000000000000000000000000000000000000 -->
			  
				</div> 
			</div>
		</div>
	 		 				
	 		 				
								
	<script type="text/javascript">
	        function fun(){
				var con = confirm('정말로 삭제하시겠습니까?')
				if(con==true) {
					location.href="./ReviewDelete.ao?bbsID="+${bbsDetail.bbsID};
				} else if(con==false) {
					return false;
				}
			}
	        
	        function writeCk(){
				var fr = document.fr;
				if(fr.cmt_content.value==""){
					alert("글 내용을 입력하세요");
					return false;
				}
			}
	        
	 //////////////////////////////////////////////////////////////////////    
	        // 댓글을 div로 전달..대댓글
	          
	         $(function(){
	        	$(".REPLYREPLY").hide();
	        	
 
        	   $('.BTNBTN').click(function(){
  	        	  var cmtRe = $(".BTNBTN").attr('id');
	        	     $("#RERERE"+cmtRe+"").toggle();
	        	});
	         }); 
        	   
     
	       //cmtRe1----일반댓글
	         $(function(){
	        	$("#RERE").hide();
	        	
	        	$(document).on("click", "#cmtReP", function(){
	     		$("#RERE").toggle();
	         });
	       });
	       
	       
	       
	        
	        
	        
	        
	        
	        
	</script>
	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>