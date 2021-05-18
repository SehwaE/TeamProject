<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <!-- footer -->
<div id="footer">
	<div class="foot_menu">
		<div class="inner">
			<ul>
				<li class="dep">
					<a href="" class="dep01">여행지 추천</a>
					<ul class="dep02">
						<li>
							<a href="${contextPath }/AtrList.lo" target="_self">명소</a>
						</li>
						<li>
					    	<a href="${contextPath }/Restaurant.lo" target="_self">음식</a>
						</li>
					</ul>
				</li>
				<li class="dep">
					<a href="" class="dep01">커뮤니티</a>
					<ul class="dep02">
						<li>
							<a href="${contextPath }/ReviewList.ao" target="_self">후기 게시판</a>
						</li>
						<li>
							<a href="${contextPath }/EventList.bd" target="_self">이벤트</a>
						</li>
					</ul>
				</li>
				<li class="dep">
					<a href="" class="dep01">유용한정보</a>
					<ul class="dep02">
						<li>
							<a href="${contextPath }/NoticeList.no" target="_self">공지</a>
						</li>
						<li>
							<a href="${contextPath }/QnaWrite.qo" target="_self">QnA</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div class="foot_info">
		<div class="inner">
			<a class="footLogo" href="#">GTB</a>
			<ul class="footLink">
				<li>
					<a href="https://www.busan.go.kr/vprivacy1" target="_blank" title="새창열기"><b>개인정보보호방침</b></a>
				</li>
				<li>
					<a href="https://www.busan.go.kr/cright" target="_blank" title="새창열기">저작권보호정책</a>
				</li>
			</ul>
			<p class="copyright">
				<span class="address">부산관광안내 1330<br />Copyright © Busan Metropolitan City. All rights reserved.</span>
			</p>
			<div class="wa">
				<img src="https://www.visitbusan.net/upload_data/board_data/BBS_0000014/160747731387971.png" alt="웹표준">
			</div>
		</div>
	</div>
</div>
<!--// footer -->