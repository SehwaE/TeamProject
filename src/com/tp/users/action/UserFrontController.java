package com.tp.users.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class UserFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : UserFrontController-doProcess 호출");
		System.out.println("\n\n\n		1. 가상주소 계산			");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		System.out.println("command : " + command);
		System.out.println("		1. 가상주소 계산			");
		System.out.println("\n\n\n		2. 가상주소 매핑(연결)			");

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/UserJoin.do")) {
			System.out.println("C: /UserJoin.do 호출");
			System.out.println("C : DB사용없이 view페이지 이동");

			forward = new ActionForward();
			forward.setPath("./user/user_join.jsp");
			forward.setRedirect(false);

		} 
		else if (command.equals("/UserIdCheckAction.do")) {
			System.out.println("C : /UserIdCheckAction.do");

			action = new UserIdCheckAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/UserEmailCheckAction.do")) {
			System.out.println("C : /UserEmailCheckAction.do 호출");

			action = new UserEmailCheckAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/UserNickNameCheckAction.do")) {
			System.out.println("C : /UserNickNameCheckAction.do");

			action = new UserNickNameCheckAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/UserJoinAction.do")) {
			System.out.println(" C : /UserJoinAction.do 호출 ");
			System.out.println(" C : 정보를 가지고 DB에 처리후 페이지 이동 ");

			action = new UserJoinAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/UserLogin.do")) {
			System.out.println("C : /UserLogin.do 호출");
			System.out.println("C : DB사용없이 view페이지로 이동");

			forward = new ActionForward();
			forward.setPath("./user/user_login.jsp");
			forward.setRedirect(false);

		} 
		else if (command.equals("/UserLoginAction.do")) {
			System.out.println("C : /UserLoginAction.do 호출");
			System.out.println("C : DB에 정보 처리 후 페이지 이동");

			action = new UserLoginAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/Main.do")) {
			System.out.println("C : /Main.do 호출");

			action = new LtListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/UserLogout.do")) {
			System.out.println("C : /UserLogout.do 호출");

			action = new UserLogoutAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/UserUpdate.do")) {
			System.out.println("C : /UserUpdate.do 호출");

			action = new UserUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/PwUpdate.do")) {
			System.out.println("C : /PwUpdate.do 호출");
			
			forward = new ActionForward();
			
			forward.setPath("./user/pwUpdate.jsp");
			forward.setRedirect(false);
			
		} 
		else if (command.equals("/PwUpdateAction.do")) {
			System.out.println("C : /PwUpdate.do 호출");
			
			action = new PwUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		else if (command.equals("/UserUpdateAction.do")) {
			System.out.println("C : /UserUpdateAction.do 호출");

			action = new UserUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		else if(command.equals("/UserDelete.do")) {
			System.out.println("C : /UserDelete.do 호출");

			forward = new ActionForward();
			
			forward.setPath("./user/deleteForm.jsp");
			forward.setRedirect(false);
			
		} 
		else if(command.equals("/UserDeleteAction.do")) {
			System.out.println("C : /UserDeleteAction.do 호출");

			action = new UserDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if(command.equals("/UserEmailCheckAction.do")) {
			System.out.println("C : /UserEmailCheckAction.do 호출");

			action = new UserEmailCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/UserNickNameCheckAction.do")) {
			System.out.println("C : /UserNickNameCheckAction.do 호출");

			action = new UserNickNameCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
		
			}
				
		} 
		else if(command.equals("/MyPage.do")) {
			System.out.println("C : /MyPage.do 호출");
			
			forward = new ActionForward();
			
			forward.setPath("./user/myPage.jsp");
			forward.setRedirect(false);
			
		}
		
		System.out.println("		2. 가상주소 매핑(연결)			");

		System.out.println("\n\n\n		3. 가상주소 페이지 이동			");
		if (forward != null) {
			if (forward.isRedirect()) {
				System.out.println(" C : sendRedirect 이동 -> " + forward.getPath());
				response.sendRedirect(forward.getPath());
			} else {
				// false
				System.out.println(" C : forward 이동 -> " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println("		3. 가상주소 페이지 이동			");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : UserFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : UserFrontController-doPost() 호출");
		doProcess(request, response);
	}

}
