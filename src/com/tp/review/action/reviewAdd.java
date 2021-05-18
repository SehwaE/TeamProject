package com.tp.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class reviewAdd implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewAdd_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");

		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		ActionForward forward = new ActionForward();
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			return forward;
		}

		forward = new ActionForward();
		forward.setPath("./review/reviewWrite.jsp");
		forward.setRedirect(false);

		return forward;

	}

}
