package com.tp.qna.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.qo")
public class QnaFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : QnaFrontController-doProcess() 호출");

		System.out.println("\n\n\n		1. 가상주소 계산			");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);

		System.out.println("		1. 가상주소 계산			");

		System.out.println("\n\n\n		2. 가상주소 매핑(연결)			");

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/QnaWrite.qo")) {
			System.out.println("C : /QnaWrite.qo 호출");

			forward = new ActionForward();
			forward.setPath("./qna/qnaWrite.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/QnaSend.qo")) {
			System.out.println("C : /QnaSend.qo 호출");

			action = new MailSend();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
		System.out.println("C : QnaFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : QnaFrontController-doPost() 호출");
		doProcess(request, response);
	}

}
