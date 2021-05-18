<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>cmtReply.jsp</h1>

<form action="./CmtReplyAdd.cmt" method="post">
 <input type = "hidden" value="${bbsID }" name = "bbsID"  >
 <input type = "hidden" value="${cmtSeq }" name = "cmtSeq"   >
 <input type = "hidden" value="${cmtRef }" name = "cmtRef"   >
 <input type = "hidden" value="${cmtLev }" name = "cmtLev"   >
 <input type = "hidden" value="${cmtID  }" name = "cmtID"   >
<textarea name="cmtReply" rows="20" cols="130" style="  border-radius: 5px; width: 100%;"></textarea>
<input type="submit" value="등록">
</form>



</body>
</html>