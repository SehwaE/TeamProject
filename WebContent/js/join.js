//비밀번호 일치 확인
$(function() {
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input").keyup(function() {
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#submit").attr("disabled", "disabled");
			}
		}
	});

	$("#userEmail").on("keyup", function() {
		if ($(this).val().length == 0)
			$("#checkBtn").hide();
		else
			$("#checkBtn").show();
	});

});
// 아이디 중복체크 화면open
function openIdChk() {

	window.name = "parentForm";
	window.open("user/IdCheckForm.jsp", "chkForm",
			"width=500, height=300, resizable = no, scrollbars = no");
}

// 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
// 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
// 다시 중복체크를 하도록 한다.
function inputIdChk() {
	document.fr.idDuplication.value = "idUncheck";
}

// 이메일 중복체크 화면open
function openEmailChk() {

	window.name = "parentForm";
	window.open("user/EmailCheckForm.jsp", "chkForm",
			"width=500, height=300, resizable = no, scrollbars = no");
}

// 이메일 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
function inputEmailChk() {
	document.fr.emailDuplication.value = "emailUncheck";
}

// 닉네임 중복체크 화면open
function openNickChk() {

	window.name = "parentForm";
	window.open("user/NickCheckForm.jsp", "chkForm",
			"width=500, height=300, resizable = no, scrollbars = no");
}

// 닉네임 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
function inputNickChk() {
	document.fr.nickDuplication.value = "nickUncheck";
}

function check() {

	var fr = document.fr;

	// 아이디 공백제어
	if (fr.userID.value == "" || fr.userID.value.length < 0) {
		alert("아이디를 입력하세요");
		fr.userID.focus();
		return false;
	}

	if (fr.idDuplication.value != "idCheck") {
		alert("아이디 중복체크를 해주세요");
		return false;
	}

	// 비밀번호 공백제어
	if (fr.userPass.value == "" || fr.userPass.value.length < 0) {
		alert("비밀번호를 입력하세요");
		fr.userPass.focus();
		return false;
	}

	// 비밀번호와 비밀번호확인 값 제어
	if (fr.userPass2.value == "" || fr.userPass2.value.length < 0) {
		alert("비밀번호를 다시 입력하세요");
		fr.userPass2.focus();
		return false;
	}

	if (fr.userPass.value != fr.userPass2.value) {
		alert("비밀번호가 일치하지 않습니다");
		fr.userPass2.focus();
		return false;
	}

	// 아이디와 비밀번호 중복 제어
	if (fr.userID.value == fr.userPass.value) {
		alert("아이디와 비밀번호는 같을 수 없습니다");
		fr.userPass.value = "";
		fr.userPass2.value = "";
		return false;
	}

	// 이름 공백제어
	if (fr.userName.value == "" || fr.userName.value.length < 0) {
		alert("이름을 입력하세요");
		fr.userName.focus();
		return false;
	}
	if ($("#userName").val().length <= 1) {
		alert("이름을 정확히 입력하세요");
		return false;
	}
	// 숫자 및 영문, 자음, 특수문자 사용제어
	for (var i = 0; i < $("#userName").val().length; i++) {
		var chk = $("#userName").val().substring(i, i + 1);

		if (chk.match(/[0-9]|[a-z]|[A-Z]/)) {
			alert("이름을 정확히 입력하세요");
			return false;
		}
		if (chk.match(/([^가-힣\x20])/i)) {
			alert("이름을 정확히 입력하세요");
			return false;
		}
		if ($("#userName").val() == " ") {
			alert("이름을 정확히 입력하세요");
			return false;
		}
	}

	if (fr.userEmail.value != "" && fr.emailDuplication.value == "emailUncheck") {
		alert("이메일 중복체크를 해주세요");
		return false;
	}

	// 이메일 형식제어
	var email = $('#userEmail').val();
	if (fr.userEmail.value != "") {
		var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		if (regex.test(email) === false) {
			alert('잘못된 이메일 형식입니다.');
			$('#userEmail').focus();
			return false;
		}
	}

	// 주소 공백제어
	if (fr.postcode.value == "" || fr.postcode.value.length < 0) {
		alert("주소를 입력하세요");
		fr.postcode.focus();
		return false;
	}

	if (fr.add1.value == "" || fr.add1.value.length < 0) {
		alert("주소를 입력하세요");
		fr.add1.focus();
		return false;
	}

	if (fr.add3.value == "" || fr.add3.value.length < 0) {
		alert("상세주소를 입력하세요");
		fr.add3.focus();
		return false;
	}

	// 전화번호 공백제어
	if (fr.userPhone.value == "" || fr.userPhone.value.length < 0) {
		alert("전화번호를 입력하세요");
		fr.userPhone.focus();
		return false;
	}
	// 전화번호 번호만 입력
	var phonenum = $('#userPhone').val();
	var regPhone = /(01[0|1|6|9|7])(\d{3}|\d{4})(\d{4}$)/g;
	if (!regPhone.test(phonenum)) {
		alert('전화번호를 정확히 입력하세요');
		$('#userPhone').focus();
		return false;
	}

	// 닉네임 공백제어
	if (fr.userNickName.value == "" || fr.userNickName.value.length < 0) {
		alert("닉네임을 입력하세요");
		fr.userNickName.focus();
		return false;
	}

	if (fr.nickDuplication.value != "nickCheck") {
		alert("닉네임 중복체크를 해주세요");
		return false;
	}

	if ($('#provisionYn:checked').val() == "N") {
		alert("회원가입약관에 동의하여 주시기 바랍니다.");
		return false;
	}

	if ($('#memberInfoYn:checked').val() == "N") {
		alert("개인정보취급방침에 동의하여 주시기 바랍니다.");
		return false;
	}
}
