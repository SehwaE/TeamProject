<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GTB 회원가입</title>

<script src="${contextPath }/js/jquery-3.5.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<link rel="stylesheet" href="${contextPath }/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${contextPath }/css/joinForm.css" />

</head>
<body>
	<div id="wrap"> 
	<!-- 네비게이션 -->
	<jsp:include page="../inc/nav.jsp" />
	<!-- 네비게이션 -->
	
	<!-- @ subHeader Top area -->    
	<div id="subVisual">
	    <section id="subVisu" class="submenu-DOM_00000020100600">
	        <h2 class="tit">회원가입</h2>
	    </section>
	    <section id="nav">
	        <div class="navInner boxing">
	            <div class="left">
	                <ul>
	                    <li class="home"><a href="${contextPath }/Main.do" title="홈"><img src="${contextPath }/img/sub/home.jpg" style="height: 22px; margin-top: 15px;"></a></li>
						<li class="home">회원 관리 > </li>
						<li class="depth2"><a href="${contextPath }/UserLogin.do" title="후기로 갑니다">회원가입 </a></li>
	                </ul>
	            </div>
	        </div>
	    </section>
		<script src="${contextPath }/js/subjs.js"></script>
	</div>
    <!--// @ subHeader Top area -->

	<div class ="container"> 
	<script src="${contextPath }/js/join.js"></script>
		<form action="./UserJoinAction.do" method="post" class="fr" name="fr" onsubmit="return check();" enctype="multipart/form-data">
            <!-- 아이디 중복체크, 아이디는 영문자과 숫자 4~10자만 -->
              <div class="incd">
                <h3 class="join_title"><label for="userID">아이디</label></h3>
                    <input type="text" name="userID" id="userID" class="int_id" maxlength=10 onkeydown="inputIdChk()" minlength="4" maxlength="10" placeholder="영문자와 숫자4~10자">
                <span class="id_check">
                    <input type="button" value="중복확인" class="ck_Btn" onclick="openIdChk()">	
                </span>
                <span class="box int_cl"></span>
	  			<input type="hidden" name="idDuplication" value="idUncheck" >
            </div>
            <!-- 비밀번호 일치확인, 값은 8 ~ 16자, 아이디와 비밀번호는 불일치해야함 -->
            <div class="incd">
                <h3 class="join_title"><label for="int_pass">비밀번호</label></h3>
                <input type="password" name="userPass" class="int" id="pwd1" minlength="8" maxlength=16 placeholder="8~16자까지 입력가능">
            </div>
            <div class="incd">
                <h3 class="join_title"><label for="userPass2">비밀번호 재확인</label></h3>
                    <input type="password" name="userPass2" class="int" id="pwd2"  minlength="8" maxlength=16 placeholder="한번 더 입력하세요">
		         <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
		         <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
            </div>

            <!-- 이름에는 한글문자만 입력되어야됨(특수문자,숫자제외), 한글문자 최소 2자이상 제어 -->
            <div class="incd">
                <h3 class="join_title"><label for="userName">이름</label></h3>
                <span class="box int_name">
                    <input type="text" id="userName" name="userName" class="int" maxlength="20">
                </span>
            </div>

            <!-- 이메일 선택사항, 입력시 중복체크  -->
            <div class="incd">
                <h3 class="join_title"><label for="userEmail">본인확인 이메일<span>(선택)</span></label></h3>
                    <input type="email" class="int_id" name="userEmail" id="userEmail" onkeydown="inputEmailChk()">
                <span class="box em_ck">
			        <input type="button" value="중복확인" id="checkBtn" class="ck_Btn" onclick="openEmailChk()" placeholder="선택사항" >	
                </span>
			  		<input type="hidden" name="emailDuplication" value="emailUncheck" >
            </div>

			<!-- 주소API 사용 -->
            <div class="incd">
                <h3 class="join_title"><label for="postcode">주소</label></h3>
                 <div class="addr_box">
                	<input type="text" id="postcode" name="postcode" placeholder="우편번호" maxlength="20" readonly>
                <span class="addr_ck">
                    <input type="button" onclick="execDaumPostcode()" class="ck_Btn" value="우편번호 찾기" ><br>
                </span>
                <span class="box int_cl"></span>
                <span class="add_box">
                    <input type="text" id="add1" name="add1" class="int" placeholder="도로명주소" readonly>
                </span>
                <span class="add_box">
					<input type="text" id="add2" name="add2" class="int" placeholder="지번주소" readonly><br>
                </span>
                <span class="add_box">
					<input type="text" id="add3" name="add3" class="int" placeholder="상세주소">
                </span>
					<input type="hidden" id="add4" name="add4" class="int" placeholder="참고항목" readonly>
					<span id="guide" style="color:#999;display:none"></span>
            </div>
            </div>
			<script src="${contextPath }/js/postCode.js"></script>

            <!-- 전화번호 형식 제어 (숫자만)-->
            <div class="incd">
                <h3 class="join_title"><label for="userPhone">휴대전화</label></h3>
                <span class="box int_ot">
                    <input type="text" name="userPhone" id="userPhone" class="int" placeholder="숫자만 입력하세요">
				</span>
            </div>
            
            <!-- 프로필사진 선택사항 -->
            <div class="incd">
				<h3 class="join_title">
					<label for="userProfile" class="optional">프로필
						<span>(선택)
						</span>
					</label>
					<label class="up_Btn" for="userProfile" >
					  업로드
					</label>
				</h3>
				<input type="file" name="userProfile" id="userProfile" style="display: none;">
			
			<span class="box int_cl"></span>
		</div>

			<!-- 닉네임 -->
            <div>
                <h3 class="join_title"><label for="userNickName">닉네임</label></h3>
                <span class="box int_id">
                    <input type="text" name="userNickName" class="int_id" onkeydown="inputNickChk()">
                </span>
                <span class="box int_ck">
			        <input type="button" value="중복확인" class="ck_Btn" onclick="openNickChk()">	
                </span>
                <span class="box int_cl"></span>
			  	<input type="hidden" name="nickDuplication" value="nickUncheck" >
            </div>
            <br>
            
            <div class="incd">
            	<h3 class="join_title"><label for="provision">회원가입약관</label></h3>
            	<div id="provision">
            		<textarea class="form-control" rows="8" style="resize:none; width:95%" readonly>약관동의
     		제 1 조 (목적)
1. 본 약관은 GTB 사이트가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 이용자와 GTB 사이트의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.
제 2 조 (약관의 효력과 변경)
1. GTB 사이트는 귀하가 본 약관 내용에 동의하는 경우 GTB 사이트의 서비스 제공 행위 및 귀하의 서비스 사용 행위에 본 약관이 우선적으로 적용됩니다.
2. GTB 사이트는 본 약관을 사전 고지 없이 변경할 수 있고 변경된 약관은 GTB 사이트 내에 공지하거나 e-mail을 통해 회원에게 공지하며, 공지와 동시에 그 효력이 발생됩니다.
이용자가 변경된 약관에 동의하지 않는 경우, 이용자는 본인의 회원등록을 취소 (회원탈락)할 수 있으며 계속 사용의 경우는 약관 변경에 대한 동의로 간주 됩니다.
제 3 조 (약관 외 준칙)
1. 본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 정보통신윤리위원회심의규정, 정보통신 윤리강령, 프로그램보호법 및 기타 관련 법령의 규정에 의합니다.
제 4 조 (용어의 정의)
본 약관에서 사용하는 용어의 정의는 다음과 같습니다.

1. 이용자 : 본 약관에 따라 GTB 사이트가 제공하는 서비스를 받는 자.
2. 가입 : GTB 사이트가 제공하는 신청서 양식에 해당 정보를 기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는 행위.
3. 회원 : GTB 사이트에 개인 정보를 제공하여 회원 등록을 한 자로서 GTB 사이트가 제공하는 서비스를 이용할 수 있는 자.
4. 비밀번호 : 이용자와 회원ID가 일치하는지를 확인하고 통신상의 자신의 비밀보호를 위하여 이용자 자신이 선정한 문자와 숫자의 조합.
5. 탈퇴 : 회원이 이용계약을 종료시키는 행위.
            		</textarea>
            		<div class="radio">
            			<label>
            				<input type="radio" id="provisionYn" name="provisionYn" value="Y" checked>
            				동의합니다.
            			</label>
            		</div>
            		<div class="radio">
            			<label>
            				<input type="radio" id="provisionYn" name="provisionYn" value="N">
            				동의하지 않습니다.
            			</label>
            		</div>
            	</div>
            </div>
            
            <div class="incd">
            	<h3 class="join_title"><label for="memberInfo">개인정보취급방침</label></h3>
            	<div id="memberInfo">
            		<textarea rows="8" class="form-control" style="resize:none; width:95%" readonly>개인정보의 항목 및 수집 방법
가. 수집하는 개인정보 항목
1) 필수항목
① 인터넷 회원가입: 이름, 본인확인기관을 통한 본인인증 결과 값(CI, 마이핀, 생년월일, 성별, 내외국인 정보), 아이디, 비밀번호, 휴대폰, 이메일, 14세 미만 아동의 경우 법정대리인 이름, 법정 생년월일, 휴대폰번호 마케팅활용동의
② 호텔서비스 이용 시 : 정산내역, 쿠폰사용내역, 불량 이용 기록
2) 선택항목
① 인터넷 회원가입: 우편물 수령지, 주소, 전화, 직장인 경우(직장명, 직위, 부서), 생년월일, 결혼기념일, 영문명, 닉네임, 사진
② 호텔 방문 시 : 차량번호, 차량종류, 화상정보(카메라, CCTV에 의한 촬영, 실시간 날씨 정보 웹캠)
3) 서비스 이용과정에서 아래와 같은 정보들이 자동으로 생성되어 수집 될 수 있는 항목: IP Address, 쿠키
4) 유료 서비스 이용 과정 시 수집하는 정보: 신용카드(카드사명, 카드번호), 휴대폰(휴대폰번호, 통신사, 결제승인번호), 계좌이체(은행명, 계좌번호)
5) 수집하는 개인정보는 서비스 제공에 필요한 최소한의 정보만으로 한정되며, 회원(고객)의 기본적 인권을 침해할 우려가 있는 민감한 개인정보(인종, 종교, 사상, 출신지, 본적지, 정치적 성향 및 범죄기록, 건강상태 및 성생활 등)는 수집하지 않습니다.
            		</textarea>
            		<div class="radio">
            			<label>
            				<input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" checked>
            				동의합니다.
            			</label>
            		</div>
            		<div class="radio">
            			<label>
            				<input type="radio" id="memberInfoYn" name="memberInfoYn" value="N">
            				동의하지 않습니다.
            			</label>
            		</div>
            	</div>
            </div>

            <!-- 가입 버튼-->
            <div class="btn_area">
                <button type="submit" id="btnJoin"  >
                    <span>가입하기</span>
                </button>
            
            <!-- 취소 버튼-->
                <button type="button" id="btnJoin" onclick="history.back();" >
                    <span>취소하기</span>
                </button>
            </div>
		</form>
	</div>
</div>
<a href="#" class="viewTop" style="background-size: 40px">Top</a>
<!-- footer -->
<jsp:include page="../inc/footer.jsp" />
<!-- footer -->
</body>

	
</html>