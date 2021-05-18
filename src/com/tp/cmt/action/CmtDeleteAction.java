package com.tp.cmt.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.CmtBean;
import com.tp.cmt.db.CmtDAO;

public class CmtDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("C : CmtDeleteAction_execute() 실행");

		// 세션 확인
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		ActionForward forward = new ActionForward();
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			return forward;
		}

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");

		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		int cmtID = Integer.parseInt(request.getParameter("cmtID"));

		
		
		// DAO 객체 생성
		CmtBean cmt = new CmtBean();
		CmtDAO cmtao = new CmtDAO();
		cmt = cmtao.getcmt(cmtID);
		int result = cmtao.deleteCmt(cmt, userID);

	 
		
	 
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
			out.print("location.href='ReviewPage.ao?bbsID="+bbsID+"'");
			out.print("</script>");

			out.close();
			return null;
		}

		 
	}
}
