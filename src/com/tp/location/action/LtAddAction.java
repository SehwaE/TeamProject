package com.tp.location.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.location.bean.LocationBean;

public class LtAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : LtAddAction_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		
		// 장소 정보 저장
		LocationBean lb = new LocationBean();
		LocationDAO ldao = new LocationDAO();
		
		lb.setLocation_name(request.getParameter("location_name"));
		lb.setLocation_spot(request.getParameter("location_spot"));
		lb.setUserID(userID);

		ldao.insertLocation(lb);

		System.out.println("LtAddAction : " + lb);

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.do");
		/* forward.setPath("./BbsList.bo"); */
		// 메인 아닌 다른 장소로 보내는 게 어떨까 생각합니다.

		forward.setRedirect(true);
		// true - sendRedirect() 방식으로 이동 (주소,화면 변경O)
		// false - forward() 방식으로 이동 (주소 변경x, 화면 변경 O)

		return forward;

	}

}
