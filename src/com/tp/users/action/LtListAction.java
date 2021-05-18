package com.tp.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.location.action.LocationDAO;

public class LtListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : LtListAction_execute() 호출");

		// 한글데이터 처리 
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		
		// 로케이션 정보 출력
		LocationDAO ldao = new LocationDAO();
		
		// 로케이션 정보
        request.setAttribute("locationList", ldao.getLocationList(userID));
		
        // 로케이션 카운트  //@@@@@@@@@@@추가필요
        request.setAttribute("locationCount", ldao.getLocationCount(userID));
        
        // 로케이션 정보 확인
        System.out.println("LtListAction : " + ldao.getLocationList(userID));
        
		// 페이지 이동
		ActionForward forward = new ActionForward();
		
		forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		
		forward.setRedirect(false);
		// true - sendRedirect() 방식으로 이동 (주소,화면 변경O)
		// false - forward() 방식으로 이동 (주소 변경x, 화면 변경 O)
		
		return forward;
	}

}
