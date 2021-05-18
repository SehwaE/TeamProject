package com.tp.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.FileDAO;

public class reviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewModifyAction_execute 호출");

		ActionForward forward = new ActionForward();
		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		// form 정보 저장하고
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		// DAO 하고
		ReDAO rdao = new ReDAO();
		BbsBean bbsDetail = rdao.getBbsModi(bbsID);

		// 파일 처리
		FileDAO fdao = new FileDAO();
		String fileName = fdao.getFile(bbsID);

		// 담아가지고
		request.setAttribute("bbsDetail", bbsDetail);
		request.setAttribute("fileName", fileName);

		// 페이지이동
		forward.setPath("./review/reviewModify.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
