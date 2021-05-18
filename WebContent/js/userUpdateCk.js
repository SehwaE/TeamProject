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

//이메일 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
function inputEmailChk() {
	document.fr.emailDuplication.value = "emailUncheck";
}

function nick_check() {
	// 닉네임
	window.name = "NickCheckForm";
	window.open("user/NickCheckForm.jsp", "nckForm",
			"width=400, height=350, resizable = no")
}

function check_value() {

	if (fr.userPass.value == "" || fr.userPass.value.length < 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}

	if (fr.userPass2.value = "" || fr.userPass2.length < 0) {
		alert("비밀번호를 다시 입력하세요.");
		return false;
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

	// 주소 제어
	if (fr.postcode.value == "" || fr.postcode.value.length < 0) {
		alert("주소를 입력하세요");
		return false;
	}

	if (fr.addr1.value = "" || fr.addr1.value.length < 0) {
		alert("주소를 입력하세요");
		return false;
	}
	
	if (fr.add3.value == "" || fr.add3.value.length < 0) {
		alert("상세주소를 입력하세요");
		fr.add3.focus();
		return false;
	}

	if (fr.userPhone.value == "" || fr.userPhone.value.length < 0) {
		alert("전화번호를 입력하세요");
		return false;
	}

	// 전화번호 번호만 입력
	var phoneNum = $('userPhone').val();
	var regPhone = /(01[0|1|6|9|7])(\d{3}|\d{4})(\d{4}$)/g;

	if (!regPhone.test(phoneNum)) {
		alert('전화번호는 숫자만 입력 가능합니다.');
		return false;
	}

	if (fr.userNickName.value = "" || fr.userNickName.length < 0) {
		alert("닉네임을 입력하세요.");
		return false;
	}

	if (fr.nickDuplication.value != "nick_check") {
		alert("닉네임 중복체크를 해주세요");
		return false;
	}

}
