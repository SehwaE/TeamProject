package com.tp.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.BbsDAO;

public class reviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewDeleteAction_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		System.out.println(userID);
		ActionForward forward = new ActionForward();
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(false);
			return forward;
		}

		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		BbsBean bbs = new BbsDAO().getBbs(bbsID);
		System.out.println(bbs);

		ReDAO rdao = new ReDAO();
		int result = rdao.deleteBbs(bbs, userID);

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
			out.print("alert('리뷰가 삭제되었습니다');");
			out.print("location.href='ReviewList.ao'");
			out.print("</script>");

			out.close();
			return null;
		}

	}

}
