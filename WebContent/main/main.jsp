<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${userID}" var ="userID"></c:set>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Go To Busan</title>
<!-- main css-->
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/13.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/6.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath }/css/14.css" />

<!-- common script-->
    <script type="text/javascript" src="${contextPath }/js/10.js"></script>
    <script type="text/javascript" src="${contextPath }/js/12.js"></script>
    <script type="text/javascript" src="${contextPath }/js/jquery.mCustomScrollbar.min.js"></script>

    <!-- main script-->
    <script type="text/javascript" src="${contextPath }/js/7.js"></script> 
    <script type="text/javascript" src="${contextPath }/js/8.js"></script> 
    <script type="text/javascript" src="${contextPath }/js/9.js"></script> 
    <script type="text/javascript" src="${contextPath }/js/15.js"></script>
	<script src="${contextPath }/js/jquery-3.5.1.js"></script>
</head>
<body class="kr"  data-drv-seq="206" data-device-type="web" data-drv-nm="kor" data-main="true">
<div id="accessibility">
    <a href="#cBody">본문 바로가기</a>
</div>

<div id="wrap">

<!-- 네비게이션 -->
<jsp:include page="../inc/nav.jsp" />
<!-- 네비게이션 -->
<!-- 메인 페이지 -->
<c:set value="${userID}" var ="userID"></c:set>  <!-- @@@@@@추가 -->

<!-- content -->
<div id="cBody" class="sp"> 
	<script type="text/javascript" src="${contextPath }/js/comm.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.13"></script>

<!-- MAIN -->
<!-- mainVisual-->
<section id="mainVisual">
    <ul class="visual_wrap">
        <li style="background-image: url('${contextPath }/img/main/b.PNG');" >
            <div class="visual_txt">
                <div class="txt_wrap">
                    <h3>아름다운 야경, 부산의 광안대교</h3>
                    <a href="AtrView.lo?uc_seq=320" target="_self" title="부산 곳곳, 벚꽃이어라">자세히보기</a>
                </div>
            </div>
            <!-- 관리자에서 영상 올리면 여기에 추가 해주시면 됩니다.-->
            <!-- 접근성 설명 노출-->
            <p class="hidden-txt"></p>
        </li>
        
        <li style="background-image: url('${contextPath }/img/main/5.PNG');"  >
            <div class="visual_txt">
                <div class="txt_wrap">
                    <h3>오륙도 스카이워크</h3>
                    <a href="AtrView.lo?uc_seq=310" target="_self" title="부산 유채꽃명소 추천, 노란 꽃들의 절정">자세히보기</a>
                </div>
            </div>
            <!-- 관리자에서 영상 올리면 여기에 추가 해주시면 됩니다.-->
            <!-- 접근성 설명 노출-->
            <p class="hidden-txt"></p>
        </li>
        
        <li style="background-image: url('${contextPath }/img/main/train.jpg');"  >
            <div class="visual_txt">
                <div class="txt_wrap">
                    <h3>해운대 블루라인 파크</h3>
                    <a href="AtrView.lo?uc_seq=512" target="_self" title="부산 드라이브는 즐거워!">자세히보기</a>
                </div>
            </div>
            <!-- 관리자에서 영상 올리면 여기에 추가 해주시면 됩니다.-->
            <!-- 접근성 설명 노출-->
            <p class="hidden-txt"></p>
        </li>
        
        <li style="background-image: url('${contextPath }/img/main/blu.PNG');"  >
            <div class="visual_txt">
                <div class="txt_wrap">
                    <h3>벚꽃 명소, 맥도생태공원</h3>
                    <a href="AtrView.lo?uc_seq=306" target="_self" title="동부산 힐링 가족여행">자세히보기</a>
                </div>
            </div>
            <!-- 관리자에서 영상 올리면 여기에 추가 해주시면 됩니다.-->
            <!-- 접근성 설명 노출-->
            <p class="hidden-txt"></p>
        </li>
        
        <li style="background-image: url('${contextPath }/img/main/y.PNG');"  >
            <div class="visual_txt">
                <div class="txt_wrap">
                    <h3>부산 유채꽃 명소, 대저생태공원</h3>
                    <a href="AtrView.lo?uc_seq=307" target="_self" title="힐링하기 좋은 부산 어촌마을">자세히보기</a>
                </div>
            </div>
            <p class="hidden-txt"></p>
        </li>
    </ul>
    <div id="navBtn">
        <div class="btn act">
          <a href="javascript:void(0);" class="start">재생</a>
          <a href="javascript:void(0);" class="pause">멈춤</a>
        </div>
        <button type="button" class="prev">prev btn</button>
        <button type="button" class="next">next btn</button>
    </div>
    <ul id="indiBtn">
        
        <li><button type="button" class="active">1</button></li>
        
        <li><button type="button">2</button></li>
        
        <li><button type="button">3</button></li>
        
        <li><button type="button">4</button></li>
        
        <li><button type="button">5</button></li>
        
    </ul>
</section>
<!--// mainVisual-->

<section class="sec01">
    <div class="inner">
        <div id="cBody" class="title" style="width:120px">
            <img src="${contextPath }/img/click.png" alt="지금 부산은" width="40">지금 부산은
        </div>
        <div id="foot_banner2">
            <div class="inner">

		<div class="fb_box">
		    <ul id="slider_foot_banner2" id = "text2">
		        <li class="order1" >
		            <a href="./AtrCate.lo?CATE2_NM=축제" title="축제">
		                #축제 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=포토스폿" title="포토스폿">
		                #포토스폿 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=문화" title="문화">
		                #문화 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=이색여행" title="부산이색여행">
		                #부산이색여행 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=일정여행" title="일정여행">
		                #일정여행 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=바다풍경" title="바다풍경">
		                #바다풍경 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=가족여행" title="가족여행">
		                #가족여행 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=관광" title="관광">
		                #관광 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=부산소개" title="부산소개">
		                #부산소개 <span class="it-down">1</span>
		            </a>
		        </li>
		        <li class="order1">
		            <a href="./AtrCate.lo?CATE2_NM=일정여행" title="일정여행">
		                #일정여행 <span class="it-down">1</span>
		            </a>
		        </li>
		    </ul>
		</div>

           		<div class="control2">
                 	<!-- <button type="button" class="ir img_comn btn_prev2" id="fb_tn_prev">이전 배너</button> id="fb_tn_prev"가 중복오류로 뜸 삭제해도 상관없는 영역 같아서 주석처리함  -->
		   		 	<button type="button" class="ir img_comn btn_prev2">이전 배너</button>
                    <button type="button" class="ir img_comn btn_next2">다음 배너</button>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="sec02" id="resultListSortNewList"><!-- 인기키워드-->
    <div class="inner">
        <!-- 이달의 부산 JS -->
		<script type="text/javascript">
		
			var xmlHttp = new XMLHttpRequest(); // 자바스크립트를 이용하여 객체를 생성하겠습니다.
			xmlHttp.onreadystatechange = function() { // 준비되면 실행하겠습니다.
				if (this.status == 200 && this.readyState == this.DONE) { // this.DONE 또는 4 (요청한 데이터의 처리가 완료되어 응답할 준비가 완료됨)
					displayData(xmlHttp); // 해당 메서드를 실행하겠습니다.
				}
			};
			xmlHttp.open("post", "${contextPath }/bs_spot.xml", true); // post 형식으로 web2.xml을 사용하겠습니다.
			xmlHttp.send(); // 전달하겠습니다.
	
			function displayData(xmlHttp) { // xmlHttp를 매개변수로 사용하는 객체
				var xmlObj, cityList, result, idx; // 변수명 지정
				xmlObj = xmlHttp.responseXML; // 요청한 데이터를 XML DOM 객체로 반환함.
				result = "<div class='trvList'><div>"; // result라는 변수에 해당 값을 넣어서 테이블 시작을 알림
				cityList = xmlObj.getElementsByTagName("item"); // item 태그의 내용을 cityList로 사용.
				for (idx = 0; idx < 4; idx++) { // 4개 출력
					result += "<div class='hot-item' style='display: inline-block;'>"
							+ "<div class='box actionImg3'><a href = './AtrView.lo?uc_seq="
							+ cityList[idx].getElementsByTagName("UC_SEQ")[0].childNodes[0].nodeValue
							+ "'><img src="
							+ cityList[idx].getElementsByTagName("MAIN_IMG_NORMAL")[0].childNodes[0].nodeValue
							+ " width='230px' height='230px'></a></div><p class='tit' style='padding: 21px 15px; font-size: 18px;'>"
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
		<!-- 이달의 부산 DIV -->
		<h1 class="section_tit">이달의 부산</h1>
			<div id = "text"></div>

	    <c:set value="${locationCount }" var="ltCnt"/>
	    <c:set value="${ locationList}" var = "ltList"/>
	</div> 
</section>

	<!-- 지도 : DIV / JS -->
	<h1 class="section_tit" style="margin-bottom:30px">부산 어디로 갈까?</h1>
	<div class="container">
		<div class="innerwrap">
			<c:if test = "${not empty userID}">
				<h2 style="text-align: center">지도를 클릭해주세요!</h2><br>
			</c:if>
			<div id="map" style="width:100%; height:500px; margin: 0 0.2%"></div>
			<c:if test = "${not empty userID}">
				<form action="./LocationAdd.lo" method="post" id="clickLatlng"></form>
			</c:if>
		</div>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6f45bc9f1692251de1b145e76f5e2f6"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		mapOption = { 
		    center: new kakao.maps.LatLng(35.179805, 129.074969), // 지도의 중심좌표
		    level: 9 // 지도의 확대 레벨
		};
		
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new kakao.maps.MapTypeControl();
		
		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		//마커를 표시할 위치와 title 객체 배열입니다 
			if ("${userID}"=="") {
				var positions = [
				{title: '대저 생태공원', 
				 latlng: new kakao.maps.LatLng(35.19906, 128.97322)},
				{title: '부산 벚꽃 투어',
				 latlng: new kakao.maps.LatLng(35.170444, 128.97269)},
				{title: '강다니엘 코스',
				 latlng: new kakao.maps.LatLng(35.078865, 129.04428)},
				{title: '근린공원',
				 latlng: new kakao.maps.LatLng(35.07614, 129.0167)},
				{title: '시티투어버스 투어',
				 latlng: new kakao.maps.LatLng(35.114388, 129.03958)},
				{title: '부산바다 당일치기',
				 latlng: new kakao.maps.LatLng(35.15311, 129.11865)},
				{title: '동래역사여행',
				 latlng: new kakao.maps.LatLng(35.201782, 129.08394)},
				{title: '무장애 여행',
				 latlng: new kakao.maps.LatLng(35.07614, 128.94853)},
				{title: '자전거 투어',
				 latlng: new kakao.maps.LatLng(35.11647, 129.0167)},
				{title: '해운대 해수욕장',
				 latlng: new kakao.maps.LatLng(35.15823, 129.15945)}];
				
				} else if("${userID}"!=null) {
					
					if('${ltCnt}'==0){
						var positions = [
	 						{title: '가고싶은 곳을 눌러보세요!',
	 						latlng: new kakao.maps.LatLng(35.19137, 129.14452)}];
					}else if('${ltCnt}'==1){
						var positions = [
	 						{title: '${ltList[0].location_name}',
	 						latlng: new kakao.maps.LatLng('${ ltList[0].location_spot.split(',')[0]}','${ ltList[0].location_spot.split(',')[1]}')}];
					}else if('${ltCnt}'==2){
						var positions = [
	 						{title: '${ltList[0].location_name}',
	 						latlng: new kakao.maps.LatLng('${ ltList[0].location_spot.split(',')[0]}','${ ltList[0].location_spot.split(',')[1]}')},
	 						{title: '${ltList[1].location_name}',
	 	 					latlng: new kakao.maps.LatLng('${ ltList[1].location_spot.split(',')[0]}','${ ltList[1].location_spot.split(',')[1]}')}];
					}else if('${ltCnt}'==3){
						var positions = [
	 						{title: '${ltList[0].location_name}',
	 						latlng: new kakao.maps.LatLng('${ ltList[0].location_spot.split(',')[0]}','${ ltList[0].location_spot.split(',')[1]}')},
	 						{title: '${ltList[1].location_name}',
	 	 					latlng: new kakao.maps.LatLng('${ ltList[1].location_spot.split(',')[0]}','${ ltList[1].location_spot.split(',')[1]}')},
	 	 					{title: '${ltList[2].location_name}',
	 	 	 				latlng: new kakao.maps.LatLng('${ ltList[2].location_spot.split(',')[0]}','${ ltList[2].location_spot.split(',')[1]}')}];
					}else if('${ltCnt}'==4){
						var positions = [
	 						{title: '${ltList[0].location_name}',
	 						latlng: new kakao.maps.LatLng('${ ltList[0].location_spot.split(',')[0]}','${ ltList[0].location_spot.split(',')[1]}')},
	 						{title: '${ltList[1].location_name}',
	 	 					latlng: new kakao.maps.LatLng('${ ltList[1].location_spot.split(',')[0]}','${ ltList[1].location_spot.split(',')[1]}')},
	 	 					{title: '${ltList[2].location_name}',
	 	 	 				latlng: new kakao.maps.LatLng('${ ltList[2].location_spot.split(',')[0]}','${ ltList[2].location_spot.split(',')[1]}')},
	 	 	 				{title: '${ltList[3].location_name}',
	 	 	 	 			latlng: new kakao.maps.LatLng('${ ltList[3].location_spot.split(',')[0]}','${ ltList[3].location_spot.split(',')[1]}')}];
					}else{
						var positions = [
	 						{title: '${ltList[0].location_name}',
	 						latlng: new kakao.maps.LatLng('${ ltList[0].location_spot.split(',')[0]}','${ ltList[0].location_spot.split(',')[1]}')},
	 						{title: '${ltList[1].location_name}',
	 	 					latlng: new kakao.maps.LatLng('${ ltList[1].location_spot.split(',')[0]}','${ ltList[1].location_spot.split(',')[1]}')},
	 	 					{title: '${ltList[2].location_name}',
	 	 	 				latlng: new kakao.maps.LatLng('${ ltList[2].location_spot.split(',')[0]}','${ ltList[2].location_spot.split(',')[1]}')},
	 	 	 				{title: '${ltList[3].location_name}',
	 	 	 	 			latlng: new kakao.maps.LatLng('${ ltList[3].location_spot.split(',')[0]}','${ ltList[3].location_spot.split(',')[1]}')},
	 	 	 	 			{title: '${ltList[4].location_name}',
	 	 	 	 	 		latlng: new kakao.maps.LatLng('${ ltList[4].location_spot.split(',')[0]}','${ ltList[4].location_spot.split(',')[1]}')}];
					}
					
				}
		
		//마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		for (var i = 0; i < positions.length; i ++) {
		
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35); 
		
		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    map: map, // 마커를 표시할 지도
		    position: positions[i].latlng, // 마커를 표시할 위치
		    title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		    image : markerImage // 마커 이미지 
		});
		}
		
		//지도에 클릭 이벤트를 등록합니다
		//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		 
		 // 클릭한 위도, 경도 정보를 가져옵니다 
		 var latlng = mouseEvent.latLng; 
		 
		 // 마커 위치를 클릭한 위치로 옮깁니다
		 marker.setPosition(latlng);
		 
		 var message = '<input type = "hidden" name = "location_spot" value ="' + latlng.getLat() + ',';
		 	 message += latlng.getLng() + '">'
		 	 + '<input type="text" name="location_name" placeholder="저장할 장소의 이름을 입력하세요">'
		 	 + '<input type = "submit" value = "찜하기">';
		 
		 var resultDiv = document.getElementById('clickLatlng'); 
		 	 resultDiv.innerHTML = message;
		 
		});
	
	</script>

<section class="sec04"><!-- 부산 여행 동영상 -->
    <div class="inner">
        <h1 class="section_tit" style="margin:30px">부산여행 간접체험</h1>

        <div class="yout_item">
            <ul class="yout_area">
                
                <li>
                    <a href="https://youtu.be/f-U5idFoQeU" target="_blank" title=" 유튜브 보기(새창)">
                        <img  src="https://img.youtube.com/vi/f-U5idFoQeU/mqdefault.jpg" alt="광안리 핫플만 골라본" />
                        <strong>광안리 핫플만 골라본 부산여행 브이로그 ⛱</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/wHqJSdPQQzE" target="_blank" title="해운대 엘시티">
                        <img  src="https://img.youtube.com/vi/wHqJSdPQQzE/mqdefault.jpg" alt="해운대 엘시티" />
                        <strong>해운대 엘시티 시그니엘 부산 뷔페. 전메뉴 완전정복</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/cNOJladpbo8" target="_blank" title="부산여행에서 백반을">
                        <img  src="https://img.youtube.com/vi/cNOJladpbo8/mqdefault.jpg" alt="부산여행에서 백반을" />
                        <strong>부산여행에서 백반을 먹을 계획이시라면 이 영상을 꼭 보세요!! (안보면 후회함🔥)</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/NoD2WYjFtws" target="_blank" title="부산 가볼만한곳 베스트">
                        <img  src="https://img.youtube.com/vi/NoD2WYjFtws/mqdefault.jpg" alt="부산 가볼만한곳 베스트" />
                        <strong>부산 가볼만한곳 베스트 10 / 부산 여행지 추천, 부산 여행코스, 부산 카페</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/WKmCq6i_dKw" target="_blank" title="[오늘 뭐 먹지?]">
                        <img  src="https://img.youtube.com/vi/WKmCq6i_dKw/mqdefault.jpg" alt="[오늘 뭐 먹지?]" />
                        <strong>[오늘 뭐 먹지?] 12시간동안 부산에서 8끼 먹방하기,,자는시간빼고 먹기만 한 폭식 VLOG</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/hI8JJxJJPEA" target="_blank" title="Driving in Busan">
                        <img  src="https://img.youtube.com/vi/hI8JJxJJPEA/mqdefault.jpg" alt="Driving in Busan" />
                        <strong>Driving in Busan/Korea/부산 기장군 연화리-대변항-월전항/기장해안도로 가이드</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/QiNPzOiO5Y8" target="_blank" title="부산 남포동">
                        <img  src="https://img.youtube.com/vi/QiNPzOiO5Y8/mqdefault.jpg" alt="부산 남포동" />
                        <strong>[오늘 뭐 먹지?] 🔥부산 남포동🔥 털고 온 리얼후기</strong>
                    </a>
                </li>
                
                <li>
                    <a href="https://youtu.be/k_zPhlovBFM" target="_blank" title="최대의 항구">
                        <img  src="https://img.youtube.com/vi/k_zPhlovBFM/mqdefault.jpg" alt="최대의 항구" />
                        <strong>[부산] 대한민국 최대의 항구도시 부산광역시는 어떤곳일까? 자세하게 알아보자!</strong>
                    </a>
                </li>
                
            </ul>
        </div>

        <div class="more_area">
            <a href="/kr/board/list.do?boardId=BBS_0000008&menuCd=DOM_000000204008000000" class="sec_more" title="더보기">더보기</a>
        </div>
    </div>
</section>

<section class="sec06"><!-- 팝업/게시판/퀵메뉴 -->
    <div class="inner">
        <div class="board_area">
            <h2 class="board_tit">공지사항</h2>
            <ul>
                <li><a href="${contextPath }/NoticeDetail.no?bbsID=5" title="동남권 광역관광 발전을 위한 설문조사">동남권 광역관광 발전을 위한 설문조사</a><span class="date">2021-04-12</span></li>
                
                <li><a href="${contextPath }/NoticeDetail.no?bbsID=4" title="움직이는 관광안내소 운영을 재개합니다">움직이는 관광안내소 운영을 재개합니다</a><span class="date">2021-03-15</span></li>
                
                <li><a href="${contextPath }/NoticeDetail.no?bbsID=3" title="[부산여행 상품 프로모션] 부산에서 나를 치유해봄">[부산여행 상품 프로모션] 부산에서 나를 치유해봄</a><span class="date">2021-03-08</span></li>
                
                <li><a href="${contextPath }/NoticeDetail.no?bbsID=2" title="영도 무장애 예술 여행 끄떡없는 여행 참가 모집">영도 무장애 예술 여행 끄떡없는 여행 참가 모집</a><span class="date">2021-02-18</span></li>
                
                <li><a href="${contextPath }/NoticeDetail.no?bbsID=1" title="부산광역시 움직이는 관광안내소 운영 임시휴무">부산광역시 움직이는 관광안내소 운영 임시휴무</a><span class="date">2020-11-30</span></li>
            </ul>
        </div>
	</div>
</section>

<script type="text/javascript">

    $(document).ready(function() {
        // 하단 배너
        var $fb = $('#foot_banner2');
        var $fb_ctl = $fb.find('.control2');
        var slider_foot_banner = $('#slider_foot_banner2').bxSlider({
            auto: true,
            pause: 2000,
            easing: 'easeOutCubic',
            slideWidth: 180,
            minSlides: 1,
            maxSlides: 6,
            moveSlides: 1,
            slideMargin: 24,
            controls: false,
            pager: false,
            autoHover: true
        });
        $fb_ctl.find('.btn_prev2').click(function() {
            slider_foot_banner.goToPrevSlide();
        });
        $fb_ctl.find('.btn_next2').click(function() {
            slider_foot_banner.goToNextSlide();
        });
        $fb_ctl.find('.btn_stop2').click(function() {
            slider_foot_banner.stopAuto();
            $(this).hide().next().show();
        });
        $fb_ctl.find('.btn_play2').click(function() {
            slider_foot_banner.startAuto();
            $(this).hide().prev().show();
        });
        $('#slider_foot_banner2').keydown(function() {
            slider_foot_banner.stopAuto();
            slider_foot_banner.css('transform', 'translate3d(0, 0, 0)');
        });
    });
</script>

<a href="#" class="viewTop" style="background-size: 40px">Top</a>
<!--// MAIN -->
                               
</div>
    <!--// content -->	
<!-- 메인 페이지 -->

<!-- footer -->
<jsp:include page="../inc/footer.jsp" />
<!-- footer -->
</div>
<!--// wrap -->
</body>
</html>