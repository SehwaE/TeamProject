package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserLogoutAction_execute() 호출");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		//페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 완료되었습니다');");
		out.println("location.href='./Main.do';");
		out.println("</script>");
		
		out.close();
		
		return null;
	}

}
