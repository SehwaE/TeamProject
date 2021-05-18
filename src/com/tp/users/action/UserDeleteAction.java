package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;


public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserDeleteAction_execute()실행");
		
		// 로그인 제어 
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		ActionForward forward = new ActionForward();
		if(userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			
			return forward;
		}
		
		// 전달된 비밀번호 저장 
		UserBean ub = new UserBean();
		
		ub.setUserPass(request.getParameter("userPass"));
		ub.setUserID(userID);
		
		System.out.println("M : 삭제회원정보 " +ub);
		
		UserDAO udao = new UserDAO();
		
		int check = udao.deleteUser(ub);
		
		System.out.println(check);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			
			out.print("<script>");
			out.print("alert('비밀번호 오류'); ");
			out.print("history.back(); ");
			out.print("</script>");
			
			out.close();

			return null; 
			
		} else if(check == -1) {
			
			out.print("<script>");
			out.print("alert('회원정보 없음'); ");
			out.print("history.back();");
			out.print("</script>");
			
			out.close();
			
			return null; 
			
		} else { // check == 1 
			session.invalidate();
			
			out.print("<script>");
			out.print("alert('탈퇴가 완료되었습니다.'); ");
			out.print("location.href='./Main.do';");
			out.print("</script>");
			
			out.close();
			
			return null;
			
		}
		
		
	}

	
}
