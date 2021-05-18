package com.tp.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.notice.db.NoticeDAO;
import com.tp.notice.db.NoticeFileDAO;

public class NoticeModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : NoticeModify_execute 호출");

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		ActionForward forward = new ActionForward();
		if (userID == null || !userID.equals("admin")) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		// form 정보 저장하고
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		// DAO 하고
		NoticeDAO bbdao = new NoticeDAO();
		BbsBean bbsDetail = bbdao.getBbsModi(bbsID);

		// 파일 처리
		NoticeFileDAO fdao = new NoticeFileDAO();
		String fileName = fdao.getFile(bbsID);

		// 담아가지고
		request.setAttribute("bbsDetail", bbsDetail);
		request.setAttribute("fileName", fileName);

		// 페이지이동
		forward.setPath("./notice/noticeModify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
