package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class UserUpdateAction implements Action {

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
		
		request.setCharacterEncoding("UTF-8");
		
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload");
		
		System.out.println("M : realPath -> " +realpath);
		
		int maxSize = 10 * 1024 * 1024; 
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realpath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		System.out.println("M : 파일업로드 완료");

		// 전달 정보를 저장 
		UserBean ub = new UserBean();
		
		String img = multi.getFilesystemName("userProfile");
		ub.setUserProfile(img);
		
		ub.setUserID(multi.getParameter("userID"));
		ub.setUserPass(multi.getParameter("userPass"));
		ub.setUserEmail(multi.getParameter("userEmail"));
		ub.setUserName(multi.getParameter("userName"));
		ub.setUserPhone(multi.getParameter("userPhone"));
		
		String postcode = multi.getParameter("postcode");
		String add1 = multi.getParameter("add1");
		String add2 = multi.getParameter("add2");
		String add3 = multi.getParameter("add3");
		String add4 = multi.getParameter("add4");
		String userAddr = postcode + "," + add1 + "," + add2 + "," + add3 + "," + add4;

		System.out.println("M : userAddr " + userAddr);
		
		ub.setUserAddr(userAddr);
		
		
		ub.setUserNickName(multi.getParameter("userNickName"));
		
		System.out.println("M : 수정할 정보" +ub);
		
		UserDAO udao = new UserDAO();
		
		int check = udao.getUpdateUser(ub);
		
		System.out.println("M : 수정 결과 >> " +check);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			out.print("<script>");
			out.print("alert('비밀번호 오류'); ");
			out.print("history.back()");
			out.print("</script>");
		
			out.close();
			
			return null;
			
		} else if(check == -1) {
			out.print("<script>");
			out.print("alert('회원정보 없음'); ");
			out.print("history.back()");
			out.print("</script>");
			
			out.close();
			
			return null; 
			
		} else { // check ==1  
			out.print("<script>");
			out.print("alert('회원정보 수정완료');");
			out.print("location.href='./Main.do';");
			out.print("</script>");
			
			out.close();
			
			return null;
		}
		

	}

}
