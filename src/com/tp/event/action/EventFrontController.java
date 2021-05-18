package com.tp.event.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 원하는 주소 지정
@WebServlet("*.bd")
public class EventFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		System.out.println(command);

		Action action = null;
		ActionForward forward = null;

		System.out.println("\n 2. 가상 주소 매핑");

		if (command.equals("/EventWrite.bd")) {
			System.out.println("C : /EventWrite.bd 호출");
			System.out.println("C : view페이지로 이동");

			forward = new ActionForward();
			forward.setPath("./event/EventWriteForm.jsp");
			forward.setRedirect(false);

		} 
		else if (command.equals("/EventWriteAction.bd")) {
			System.out.println("C : /EventWriteAction.bd 호출");
			System.out.println("C : DB에 정보를 저장");

			action = new EventWriteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/EventList.bd")) {
			System.out.println("/EventList.bd");
			System.out.println("C : DB 정보를 가져와서 view에 호출");

			action = new EventListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/EventUpdate.bd")) {
			System.out.println("C : /EventUpdate.bd 호출");
			System.out.println("DB 정보를 가지고와서 수정 후 저장");

			action = new EventUpdate();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/EventUpdateAction.bd")) {
			System.out.println("C : /EventUpdateAction.bd 호출");
			System.out.println("C : 수정한 정보를 DB에 저장");

			action = new EventUpdateAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/EventDelete.bd")) {
			System.out.println("C : /EventDelete.bd 호출");

			action = new EventDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/EventDetail.bd")) {
			System.out.println("C : /EventDetail.bd 호출");

			action = new EventDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("\n 3. 페이지 이동");
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : EventFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("C : EventFrontController-doPost() 호출");
		doProcess(request, response);
	}

}
