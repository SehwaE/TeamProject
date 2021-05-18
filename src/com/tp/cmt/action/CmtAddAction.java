package com.tp.cmt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.CmtBean;
import com.tp.cmt.db.CmtDAO;

public class CmtAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("C : CmtAddAction_execute() 실행");

		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 세션 확인
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		ActionForward forward = new ActionForward();
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			return forward;
		}

		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		
		CmtBean cb = new CmtBean();
		cb.setBbsID(bbsID);
		cb.setUserID(userID);
		cb.setCmt_content(request.getParameter("cmt_content"));
		cb.setIp(request.getRemoteAddr());

		CmtDAO cmtao = new CmtDAO();
		cmtao.comment(cb);

		// 페이지 이동
		forward.setPath("ReviewPage.ao?bbsID="+bbsID);
		forward.setRedirect(true);
		return forward;
	}
}
