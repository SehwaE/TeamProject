package com.tp.location.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 원하는 주소 지정
@WebServlet("*.lo")
public class LocationFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		System.out.println("		1. 가상주소 계산			");
		
		Action action = null;
		ActionForward forward = null;
		
		System.out.println("\n 2. 가상 주소 매핑");
		
		if(command.equals("/AtrList.lo")){
			System.out.println("C: /AtrList.lo 호출");
			System.out.println("C : DB사용없이 view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./location/AtrList.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/AtrView.lo")){
			System.out.println("C: /AtrView.lo 호출");
			System.out.println("C : DB사용없이 view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./location/AtrView.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/AtrCate.lo")){
			System.out.println("C: /AtrCate.lo 호출");
			System.out.println("C : DB사용없이 view페이지 이동");
			
			request.setCharacterEncoding("UTF-8");
			
			forward = new ActionForward();
			forward.setPath("./location/AtrCate.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/Restaurant.lo")){
			System.out.println("C: /Restaurant.lo 호출");
			System.out.println("C : DB사용없이 view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./location/Restaurant.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/FoodView.lo")){
			System.out.println("C: /Restaurant.lo 호출");
			System.out.println("C : DB사용없이 view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./location/FoodView.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/LocationAdd.lo")) {
			
			action = new LtAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("\n 3. 페이지 이동");
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
