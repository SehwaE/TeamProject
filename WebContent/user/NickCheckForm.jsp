<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>닉네임 중복 체크</title>
	
	<style type="text/css">
  			
		#wrap {
			width: 490px;
			text-align :center;
			margin: 0 auto 0 auto;
		}
		#chk{
			text-align :center;
		}
		#cancelBtn{
			visibility:visible;
		}
		#useBtn{
			 visibility:hidden;
		}
	</style>
	
	<script type="text/javascript">
	
		var httpRequest = null;
		
		// httpRequest 객체 생성
		function getXMLHttpRequest(){
			var httpRequest = null;
		
			if(window.ActiveXObject){
				try{
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
				} catch(e) {
					try{
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) { httpRequest = null; }
				}
			}
			else if(window.XMLHttpRequest){
				httpRequest = new window.XMLHttpRequest();
			}
			return httpRequest;	
		}
		
		
		// 회원가입창의 닉네임 입력란의 값을 가져온다.
		function pValue(){
			document.getElementById("userNickName").value = opener.document.fr.userNickName.value;
		}
		
		// 아이디 중복체크
		function nickCheck(){

			var userNickName = document.getElementById("userNickName").value;

			if (!userNickName) {
				alert("닉네임을 입력하세요");
				return false;
			}else{
				var param="userNickName="+userNickName
				httpRequest = getXMLHttpRequest();
				httpRequest.onreadystatechange = callback;
				httpRequest.open("POST", "../UserNickNameCheckAction.do", true);	
				httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
				httpRequest.send(param);
			}
		}
		
		function callback(){
			if(httpRequest.readyState == 4){
				// 결과값을 가져온다.
				var resultText = httpRequest.responseText;
				if(resultText == 0){
					alert("이미 사용중인 닉네임입니다.");
					document.getElementById("cancelBtn").style.visibility='visible';
					document.getElementById("useBtn").style.visibility='hidden';
					document.getElementById("msg").innerHTML ="";
				}else if(resultText == 1){
					document.getElementById("cancelBtn").style.visibility='hidden';
					document.getElementById("useBtn").style.visibility='visible';
					document.getElementById("msg").innerHTML = "사용 가능한 닉네임입니다.";
				}
			}
		}
		
		// 사용하기 클릭 시 부모창으로 값 전달 
		function sendCheckValue(){
			// 중복체크 결과인 idCheck 값을 전달한다.
			opener.document.fr.nickDuplication.value ="nickCheck";
			// 회원가입 화면의 ID입력란에 값을 전달
			opener.document.fr.userNickName.value = document.getElementById("userNickName").value;
			
			if (opener != null) {
            	opener.chkForm = null;
            	self.close();
			}	
		}	
	</script>
	
</head>
<body onload="pValue()">

<div id="wrap">
	<br>
	<fieldset>
	<b><font size="4" color="gray">닉네임 중복체크</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
	  
		<form id="checkForm">
			<input type="text" name="userNickName" id="userNickName">
			<input type="button" value="중복확인" onclick="nickCheck()">
		</form>
		</fieldset>
		<div id="msg"></div>
		<br>
		<input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
		<input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
	</div>
</div>	
</body>
</html>