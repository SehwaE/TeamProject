package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class PwUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("M : UserUpadateAction_execute()호출");
		
		// 로그인 제어 
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		ActionForward forward = new ActionForward();
		if(userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			
			return forward;
		}
		
		String userPass = request.getParameter("userPass");
		String userPass2 = request.getParameter("userPass2");
		// 전달 정보를 저장 
		UserBean ub = new UserBean();
		ub.setUserID(userID);
		ub.setUserPass(userPass);
		System.out.println(userPass);
		System.out.println(userPass2);
		
		UserDAO udao = new UserDAO();
		
		int result = udao.updatePassword(ub, userPass2);
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// 결과에 따라서 페이지 이동
		if (result == 0) {
			out.print("<script>");
			out.print("alert('아이디나 비밀번호를 확인하세요');");
			out.print("history.back();");
			out.print("</script>");
			out.close(); 

			return null; 
			
		} else if (result == -1) { 
			out.print("<script>");
			out.print("alert('회원 정보가 없습니다');");
			out.print("history.back();");
			out.print("</script>");

			out.close();

			return null;
			
		} else { 
			out.print("<script>");
			out.print("alert('비밀번호가 수정되었습니다');");
			out.print("location.href='./MyPage.do';");
			out.print("</script>");

			out.close();
			
			return null;
		}
		
	}

}
