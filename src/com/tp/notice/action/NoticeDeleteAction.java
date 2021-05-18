package com.tp.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.notice.db.NoticeDAO;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : NoticeDeleteAction_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		ActionForward forward = new ActionForward();
		if(userID==null || !userID.equals("admin")){
			forward.setPath("./UserLogin.do");
			forward.setRedirect(false);
			return forward;
		}
		
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		BbsBean bbs = new NoticeDAO().getBbs(bbsID);
		System.out.println(bbs);
		// out객체는 데이터를 html형태로 쓸수있게 만들어줌, html페이지 -> js사용가능
		NoticeDAO bdao = new NoticeDAO();
		int result = bdao.deleteBbs(bbs, userID);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result==0){
			out.print("<script>");
			out.print("alert('권한이 없습니다');");
			out.print("history.back();");
			out.print("</script>");
			
			out.close();
			return null;
		}else if(result==-1){
			out.print("<script>");
			out.print("alert('관리자에게 문의하세요');");
			out.print("history.back();");
			out.print("</script>");
			
			out.close();
			return null;
		}else{
			out.print("<script>");
			out.print("alert('공지가 성공적으로 삭제되었습니다');");
			out.print("location.href='NoticeList.no'");
			out.print("</script>");
			
			out.close();
			return null;
		}
	}

}
