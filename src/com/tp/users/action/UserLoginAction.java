package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserLoginAction_execute() 호출");

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		// 전달되는 파라미터 정보 저장
		// 자바빈 객체 생성후 파라미터 저장
		UserBean ub = new UserBean();
		ub.setUserID(request.getParameter("userID"));
		ub.setUserPass(request.getParameter("userPass"));

		System.out.println("M : 전달정보" + ub);

		// DAO 객체 생성 후 처리메서드 생성
		UserDAO udao = new UserDAO();
		// 로그인 체크 메서드 // 메모리 효율을 위해 자바빈 사용해서 가져감
		int result = udao.loginCheck(ub);

		System.out.println("M : 로그인 체크 결과 >>" + result);

		// 페이지 이동을 자바스크립트를 사용(일반적으로 자바파일에서 js사용 불가)
		response.setContentType("text/html; charset=UTF-8");
		// out객체는 데이터를 html형태로 쓸수있게 만들어줌, html페이지 -> js사용가능
		PrintWriter out = response.getWriter();

		// 결과에 따라서 페이지 이동
		if (result == 0) { // 비밀번호 오류
			out.print("<script>");
			out.print("alert('아이디나 비밀번호를 확인하세요');");
			out.print("history.back();");
			out.print("</script>");

			out.close(); // 자원해제

			// **return으로 다시 controller에게 알려줘야 하는데 controller로 js의 결과값이 다시 가는것을
			// 방지하기 위해
			return null; // js 이동시 컨트롤러의 이동정보는 null
		} else if (result == -1) { // 아이디 없음
			out.print("<script>");
			out.print("alert('회원 정보가 없습니다');");
			out.print("history.back();");
			out.print("</script>");

			out.close(); // 자원해제

			return null;
		} else { // result == 1 (로그인 성공)
			System.out.println("M : 로그인 성공! 메인페이지로 이동합니다");
			
			HttpSession session = request.getSession();
			session.setAttribute("userID", ub.getUserID());
			System.out.println(ub.getUserID());
			// 1. 액션포워드 사용시 return null; 삭제
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.do");
			forward.setRedirect(true); // 가상주소(MemberLogin.me)에서 가상주소로 이동 -> true
			
			// true - sendRedirect() 방식으로 이동 (주소,화면 변경O)
			// false - forward() 방식으로 이동 (주소 변경x, 화면 변경 O)				
			return forward;

			// //2.
			// out.print("<script>");
			// out.print("alert('로그인 성공!');");
			// out.print("location.href='./Main.me';");
			// out.print("</script>");
			//
			// out.close();
			//
			// return null;
		}

	}

}
