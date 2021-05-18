package com.tp.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.db.FileDAO;
import com.tp.event.db.EventDAO;

public class EventDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : EventDetailAction_execute()호출");

		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		EventDAO evdao = new EventDAO();
		evdao.updateReadCount(bbsID);
		
		FileDAO fdao = new FileDAO();
		
		request.setAttribute("bbsList", evdao.getBbs(bbsID));
		request.setAttribute("fileName", fdao.getFile(bbsID));

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./event/EventDetail.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
