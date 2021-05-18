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

});

function check() {

	var fr = document.fr;

	// 비밀번호 공백제어
	if (fr.userPass2.value == "" || fr.userPass2.value.length < 0) {
		alert("비밀번호를 입력하세요");
		fr.userPass.focus();
		return false;
	}

	// 비밀번호와 비밀번호확인 값 제어
	if (fr.userPass3.value == "" || fr.userPass3.value.length < 0) {
		alert("비밀번호를 다시 입력하세요");
		fr.userPass2.focus();
		return false;
	}

	if (fr.userPass2.value != fr.userPass3.value) {
		alert("비밀번호가 일치하지 않습니다");
		fr.userPass2.focus();
		return false;
	}

}
