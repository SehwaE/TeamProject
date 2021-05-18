package com.tp.users.action;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserJoinAction_execute() 호출");

		// 파일이 저장되는 위치 (이전의 방법보다 조금 더 권장하는 방법)
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload");

		System.out.println(" M : realpath - " + realpath);
		// 파일의 용량
		int maxSize = 10 * 1024 * 1024; // 10MB

		MultipartRequest multi = new MultipartRequest(request, realpath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		System.out.println("M : 파일업로드 완료!");
		// 파라미터(입력값) 저장
		UserBean ub = new UserBean();

		ub.setUserID(multi.getParameter("userID"));
		ub.setUserPass(multi.getParameter("userPass"));
		ub.setUserName(multi.getParameter("userName"));
		ub.setUserEmail(multi.getParameter("userEmail"));
		String postcode = multi.getParameter("postcode");
		String add1 = multi.getParameter("add1");
		String add2 = multi.getParameter("add2");
		String add3 = multi.getParameter("add3");
		String add4 = multi.getParameter("add4");
		String userAddr = postcode + "," + add1 + "," + add2 + "," + add3 + "," + add4;

		System.out.println("M : userAddr " + userAddr);

		ub.setUserAddr(userAddr);
		ub.setUserPhone(multi.getParameter("userPhone"));

		String userProfile = multi.getFilesystemName("userProfile");
		
		ub.setUserProfile(userProfile);
		ub.setUserNickName(multi.getParameter("userNickName"));

		ub.setIsAdmin(0);
		if (ub.getUserID().equals("admin")) {
			ub.setIsAdmin(1);
		} else {
			ub.setIsAdmin(0);
		}

		System.out.println("M : 전달된 회원정보 " + ub);

		UserDAO udao = new UserDAO();
		udao.insertUser(ub);

		System.out.println("M : 회원가입 성공!");

		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./UserLogin.do");
		forward.setRedirect(true);

		return forward;
	}

}
