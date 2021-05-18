package com.tp.cmt.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

//원하는 주소 지정
@WebServlet("*.cmt")
public class CmtFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		System.out.println("command : " + command);
		System.out.println("\n 1. 가상주소 계산");

		Action action = null;
		ActionForward forward = null;

		System.out.println("\n 2. 가상 주소 매핑");

		if (command.equals("/CmtAdd.cmt")) {
			System.out.println("CmtAddAction.cmt");
			System.out.println("글 입력 후 리스트 이동");

			action = new CmtAddAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("C : CmtAdd 예외처리 발생"+e);
			}
		} 
		else if (command.equals("/CmtDelete.cmt")) {
			System.out.println("C : /CmtDelete 호출");
			System.out.println(" C : DB가서 삭제하고 화면이동");

			action = new CmtDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/CmtReply.cmt")){
			System.out.println("C : /CmtReply 호출");
			System.out.println("C : action 이동하고 뷰로 이동");
			
			
			action = new CmtReplyAction();
			
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
		else if(command.equals("/CmtReplyAdd.cmt")){
			System.out.println("C : /CmtReplyAdd.cmt 호출");
			System.out.println("C : DB 저장하고 뷰로 이동");
			
			action = new CmtReplyAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		/*

		else if (command.equals("/CmtModify.cmt")) {
			System.out.println("C : /cmtModifyAction 호출");
			System.out.println("C: DB가서 수정하고 화면이동");

			action = new CmtModifyAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		*/

		System.out.println("\n 2. 가상 주소 매핑");

		System.out.println("\n 3. 페이지 이동");

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {

				// request에 담긴 정보를 저장하다가
				// 다음 페이지에서도 보이게 해줌
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
			}
		}
		System.out.println("\n 3. 페이지 이동");

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
