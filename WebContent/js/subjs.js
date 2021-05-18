$(function() {
	// 초기 설정
	var size = 16;
	var currentSize = parseInt($("#container").css("fontSize"), 10);

	var body = $("#container");

	$("#plusFontS").on("click", function() {

		currentSize += 2;
		body.css("font-size", currentSize + "px");

		$("#zoom_slide a").removeClass("on");
	});

	$("#defaultFontS").on("click", function() {
		currentSize = size;
		body.css("font-size", currentSize + "px");
		$("#zoom_slide a").removeClass("on");
	});

	$("#minorFontS").on("click", function() {

		currentSize -= 2;
		body.css("font-size", currentSize + "px");

		$("#zoom_slide a").removeClass("on");
	});
});