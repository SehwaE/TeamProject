<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- main css-->
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/13.css" /> <!--common.css-->
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/6.css" /> <!--main.css-->
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/14.css" /> <!--swiper.min.css-->
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/16.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/17.css" />
<!-- common script-->
    <script type="text/javascript" src="${contextPath }/js/10.js"></script> <!-- jquery-1.8.2.min.js -->
    <script type="text/javascript" src="${contextPath }/js/12.js"></script> <!-- TweenMax.js -->
    <script type="text/javascript" src="${contextPath }/js/jquery.mCustomScrollbar.min.js"></script>
<!-- main script-->
    <script type="text/javascript" src="${contextPath }/js/7.js"></script> <!-- design_common.js -->
    <script type="text/javascript" src="${contextPath }/js/8.js"></script> <!-- header.js -->
    <script type="text/javascript" src="${contextPath }/js/9.js"></script> <!-- bxslider.min.js //mainSlider 사용 -->
    <script type="text/javascript" src="${contextPath }/js/15.js"></script> <!-- swiper.min.js //popup 사용 -->

<div class="header main" >
    <h1><a href="./Main.do" class="logo"><span class="blind">VISIT BUSAN</span></a></h1>
    <a href="javascript:" class="mGnbOpen"><img src="${contextPath }/img/main/gnb_btn.png" alt="전체메뉴열기" width="21" /></a>

    <!-- pc gnb -->
    <ul id="gnb">
	    <li class="dep">
	        <a href="javascript:" class="oneD">여행지 추천</a>
	        <ul class="twoD">
				<li>
				    <a href="${contextPath }/AtrList.lo" target="_self">명소</a>
				</li>
				<li>
				    <a href="${contextPath }/Restaurant.lo" target="_self">음식</a>
				</li>
	    	</ul>
	    </li>
	    <li class="dep">
	        <a href="javascript:" class="oneD">커뮤니티</a>
	        <ul class="twoD">
				<li>
					<a href="${contextPath }/ReviewList.ao" target="_self">후기 게시판</a>
				</li>
				<li>
					<a href="${contextPath }/EventList.bd" target="_self">이벤트</a>
				</li>
	        </ul>
	    </li>
    	<li class="dep">
	            <a href="javascript:" class="oneD">유용한정보</a>
	            <ul class="twoD">
					<li>
						<a href="${contextPath }/NoticeList.no" target="_self">공지</a>
					</li>
					<li>
						<a href="${contextPath }/QnaWrite.qo" target="_self">QnA</a>
					</li>
	            </ul>
	        </li>
	    <li class="dep">
	    	<a href="javascript:" class="oneD">회원 관리</a>
    		<ul class="twoD">
		    <c:choose>
		    	<c:when test="${userID eq null }">
				    <li>
			    		<a href="${contextPath }/UserLogin.do">로그인</a>
				    </li>
				    <li>
	                    <a href="${contextPath }/UserJoin.do">회원가입</a>
	                </li>
			    </c:when>
		        <c:otherwise>
			        <li>
						<a href="${contextPath }/UserLogout.do">로그아웃</a>
					</li>
					<li>
						<a href="${contextPath }/MyPage.do">마이페이지</a>
					</li>
				</c:otherwise>
	    	</c:choose>
	    	</ul>
	    </li>
   	</ul>
   	<ul id="user">
   	  <c:choose>
	     <c:when test="${userID eq null }">
   	        <li>🧡 로그인이 필요합니다.</li>
   	     </c:when>
		 <c:otherwise>
		    <li>💗${userID}님 환영합니다.</li>
		 </c:otherwise>
	  </c:choose>
		        
   	</ul>
  
    <!-- mobile gnb -->
    <div id="mGnb">
    <c:choose>
    	<c:when test="${userID eq null }">
	        <div class="mo-btn-area">
	            <ul>
	                <li class="bt-one">
	                    <a href="${contextPath }/UserLogin.do">로그인</a>
	                </li>
	                <li class="bt-two">
	                    <a href="${contextPath }/UserJoin.do" class="my">회원가입</a>
	                </li>
	            </ul>
	        </div>
        </c:when>
        <c:otherwise>
	        <div class="mo-btn-area">
	            <ul>
	                <li class="bt-one">
	                     <a href="${contextPath }/UserLogout.do" class="my">로그아웃</a>
	                </li>
	                <li class="bt-two">
	                    <a href="${contextPath }/MyPage.do">마이페이지</a>
	                </li>
	            </ul>
	        </div>
        </c:otherwise>
    </c:choose>
    
        <ul>
	        <li class="dep">
	            <a href="javascript:" class="oneD">부산에가면</a>
	            <ul class="twoD">
	            	<li>
                    	<a href="${contextPath }/AtrList.lo" target="_self">명소</a>
					</li>
					<li>
					    <a href="${contextPath }/Restaurant.lo" target="_self">음식</a>
					</li>
	        	</ul>
	        </li>
	        
	        <li class="dep">
		        <a href="javascript:" class="oneD">커뮤니티</a>
		        <ul class="twoD">
					<li>
			           <a href="${contextPath }/ReviewList.ao" target="_self">후기 게시판</a>
			         </li>
					<li>
						<a href="${contextPath }/EventList.bd" target="_self">이벤트</a>
					</li>
		        </ul>
		    </li>
	        
	        <li class="dep">
	            <a href="javascript:" class="oneD">유용한정보</a>
	            <ul class="twoD">
					<li>
						<a href="${contextPath }/NoticeList.no" target="_self">공지</a>
					</li>
					<li>
						<a href="${contextPath }/QnaWrite.qo" target="_self">QnA</a>
					</li>
	            </ul>
	        </li>
        </ul>
        <a href="javascript:" class="mClose"><img src="${contextPath }/img/main/x-mark.png" alt="닫기" width="20" /></a>
    </div>
    <p class="twoDBg"></p>
</div>
<p class="mobileBg"></p>

<script>
    // html dom 이 다 로딩된 후 실행된다.
    $(document).ready(function(){
        // menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
        $(".utill>.lng").click(function(){
            var submenu = $(this).next("ul");

            // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
            if( submenu.is(":visible") ){
                submenu.slideUp();
            }else{
                submenu.slideDown();
            }
        });
    });
</script>
