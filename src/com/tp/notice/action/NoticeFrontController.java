package com.tp.notice.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.no")
public class NoticeFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : NoticeFrontController-doProcess() 호출");
		
		System.out.println("\n\n\n		1. 가상주소 계산			");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		System.out.println("		1. 가상주소 계산			");
		
		System.out.println("\n\n\n		2. 가상주소 매핑(연결)			");
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/NoticeWrite.no")){
			System.out.println("C : /NoticeWrite.no 호출");
			
			forward = new ActionForward();
			forward.setPath("./notice/noticeWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/NoticeWriteAction.no")){
			System.out.println("C : /NoticeWriteAction.no 호출");
			
			action = new NoticeWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/NoticeList.no")){
			System.out.println("C : /NoticeList.no 호출");
			
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/NoticeDetail.no")){
			System.out.println("C : /NoticeDetail.no 호출");
			
			action = new NoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/NoticeModify.no")){
			System.out.println("C : /NoticeModify.no 호출");
			
			action = new NoticeModify();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/NoticeModifyAction.no")){
			System.out.println("C : /NoticeModifyAction.no 호출");
			
			action = new NoticeModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/NoticeDelete.no")){
			System.out.println("C : /NoticeDelete.no 호출");
			
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("		2. 가상주소 매핑(연결)			");
		
		System.out.println("\n\n\n		3. 가상주소 페이지 이동			");
		if(forward!=null){
			if(forward.isRedirect()){
				System.out.println(" C : sendRedirect 이동 -> "+forward.getPath());
				response.sendRedirect(forward.getPath());
			}else{
				//false
				System.out.println(" C : forward 이동 -> "+forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println("		3. 가상주소 페이지 이동			");

	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : NoticeFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : NoticeFrontController-doPost() 호출");
		doProcess(request, response);
	}
		
	
}
