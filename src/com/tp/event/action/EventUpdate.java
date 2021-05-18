package com.tp.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.FileDAO;
import com.tp.event.db.EventDAO;

public class EventUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : EventUpdateAction_execute() 호출");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		ActionForward forward = new ActionForward();
		if(userID == null || ! userID.equals("admin")) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			
			return forward;
		}
		
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		
		EventDAO evdao = new EventDAO();
		FileDAO fdao = new FileDAO();
		
		BbsBean bbsDetail = evdao.getBbsModi(bbsID);
		
		request.setAttribute("bbsList", bbsDetail);
		request.setAttribute("fileName", fdao.getFile(bbsID));
		
		// 페이지 이동 
		forward.setPath("./event/EventUpdateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
