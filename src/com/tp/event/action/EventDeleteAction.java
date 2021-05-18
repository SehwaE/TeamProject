package com.tp.event.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.event.db.EventDAO;

public class EventDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventDeletAction_execute() 호출");

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		ActionForward forward = new ActionForward();
		if (userID == null || !userID.equals("admin")) {
			forward.setPath("./EventList.bd");
			forward.setRedirect(true);

			return forward;
		}

		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		BbsBean bbs = new BbsBean();

		bbs.setBbsID(Integer.parseInt(request.getParameter("bbsID")));

		System.out.println(bbs);

		EventDAO evdao = new EventDAO();
		int result = evdao.deleteEvent(bbs, userID);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>");
			out.print("alert('권한이 없습니다');");
			out.print("history.back();");
			out.print("</script>");

			out.close();
			return null;
		} else if (result == -1) {
			out.print("<script>");
			out.print("alert('관리자에게 문의하세요');");
			out.print("history.back();");
			out.print("</script>");

			out.close();
			return null;
		} else {
			out.print("<script>");
			out.print("alert('이벤트가 성공적으로 삭제되었습니다');");
			out.print("location.href='EventList.bd'");
			out.print("</script>");

			out.close();
			return null;
		}
	}

}
