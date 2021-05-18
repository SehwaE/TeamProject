<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GTB 맛집 리스트</title>

<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="${contextPath }/js/bootstrap.js"></script>
<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">

</head>
<body>
	<!-- 네비게이션 -->
	<jsp:include page="../inc/nav.jsp" />
	<!-- 네비게이션 -->
	<!-- @ subHeader Top area -->    
	<div id="subVisual">
	    <section id="subVisu" class="submenu-DOM_00000020100800">
	        <h2 class="tit">부산에가면</h2>
	    </section>
	    <section id="nav">
	        <div class="navInner boxing">
	            <div class="left">
	                <ul>
	                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
						<li class="home">여행지 추천 > </li>
						<li class="depth2"><a href="${contextPath }/Restaurant.lo" title="음식으로 갑니다">음식 </a></li>
	                </ul>
	            </div>
	        </div>
	    </section>
		<script src="${contextPath }/js/subjs.js"></script>
	</div>                                                                                                                                                            
	<hr style = "margin: 2.5% 5% ">
    <!--// @ subHeader Top area -->
	<!-- 메인 페이지 -->
	<script type="text/javascript">
		var xmlHttp = new XMLHttpRequest(); // 자바스크립트를 이용하여 객체를 생성하겠습니다.
		xmlHttp.onreadystatechange = function() { // 준비되면 실행하겠습니다.
			if (this.status == 200 && this.readyState == this.DONE) { // this.DONE 또는 4 (요청한 데이터의 처리가 완료되어 응답할 준비가 완료됨)
				displayData(xmlHttp); // 해당 메서드를 실행하겠습니다.
			}
		};
		xmlHttp.open("post", "./bs_resaurant.xml", true); // post 형식으로 web2.xml을 사용하겠습니다.
		xmlHttp.send(); // 전달하겠습니다.

		function displayData(xmlHttp) { // xmlHttp를 매개변수로 사용하는 객체
			var xmlObj, cityList, result, idx; // 변수명 지정
			xmlObj = xmlHttp.responseXML; // 요청한 데이터를 XML DOM 객체로 반환함.
			result = "<div class='trvList'><div>"; // result라는 변수에 해당 값을 넣어서 테이블 시작을 알림
			cityList = xmlObj.getElementsByTagName("item"); // item 태그의 내용을 cityList로 사용.
			for (idx = 0; idx < cityList.length; idx++) { // 4개 출력
				result += "<div class='hot-item' style='display: inline-block;'>"
						+ "<div class='box actionImg3'><a href = './FoodView.lo?uc_seq="
						+ cityList[idx].getElementsByTagName("UC_SEQ")[0].childNodes[0].nodeValue
						+ "'><img src="
						+ cityList[idx].getElementsByTagName("MAIN_IMG_NORMAL")[0].childNodes[0].nodeValue
						+ " width='230px' height='230px'></a></div><p class='tit' style='padding: 21px 15px; font-size: 19px; font-weight: bold;'>"
						+ cityList[idx].getElementsByTagName("MAIN_TITLE")[0].childNodes[0].nodeValue
						+ "</p><span><img src='${contextPath }/img/sub/read.PNG' style='height: 95%;' alt='read'></span>";
				if ((idx + 1) % 4 == 0) { // 4개 출력
					result += "</div></div><div>";
				} else {
					result += "</div>"; // 4칸이 되었기 때문에 다음 줄로 넘기겠습니다.
				}
			}
			result += "</div>"; // 테이블을 닫겠습니다.

			document.getElementById("text").innerHTML = result; // text라는 id 값에 result 값을 넣겠습니다.
		}
	</script>
	<div class="container">
		<div class="row">
			<h1 class="h1_head" style="font-weight: bold;">음식</h1>
			<hr>
			<div id="text"></div>
		</div>
	</div>
	<a href="#" class="viewTop" style="background-size: 40px">Top</a>
	<!-- 메인 페이지 -->

	<!-- footer -->
	<jsp:include page="../inc/footer.jsp" />
	<!-- footer -->
</body>
</html>