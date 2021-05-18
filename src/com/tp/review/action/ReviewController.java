package com.tp.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 원하는 주소 지정
@WebServlet("*.ao")
public class ReviewController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		System.out.println("command : " + command);
		System.out.println("  1. 가상주소 계산 ");

		Action action = null;
		ActionForward forward = null;

		System.out.println("\n 2. 가상 주소 매핑");
		if (command.equals("/ReviewList.ao")) {
			System.out.println("C : ReviewList.ao호출");
			System.out.println("C : view 로 이동");

			action = new reviewListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/ReviewAdd.ao")) {
			System.out.println("C : ReviewAdd.ao호출");
			System.out.println("C : view 로 이동");

			action = new reviewAdd();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/ReviewAddAction.ao")) {
			System.out.println("C : ReviewAddAction.ao호출");
			System.out.println("C : DB 등록후 Veiw");

			action = new reviewAddAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/ReviewPage.ao")) {
			System.out.println("C : ReviewPage.ao 호출");
			System.out.println("c: DB에서 가져와서 view가기");

			action = new reviewPageAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/ReviewModify.ao")) {
			System.out.println("C : ReviewModify.ao 호출");
			System.out.println("C : DB 가져와  view");

			action = new reviewModifyAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/ReviewModifyEnd.ao")) {
			System.out.println("C: bbsModifyEndAction 호출");
			System.out.println("C : DB 가서 수정하고 ReviewPage화면으로");

			action = new reviewModifyEndAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/ReviewDelete.ao")) {
			System.out.println("C: reviewDeleteAction 호출");
			System.out.println("C : DB 가서 삭제하고 리뷰 리스트 화면으로");

			action = new reviewDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/like.ao")) {
			System.out.println("C : like.bbs 호출");
			System.out.println("C: DB가서  정보 업뎃하기");

			action = new BbsLikeAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
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
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
