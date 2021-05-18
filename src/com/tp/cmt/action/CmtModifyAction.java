package com.tp.cmt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.cmt.db.CmtDAO;




/*Action 페이지란?

1) view(.jsp)에서 가져온 정보 DAO 로 전달하기 위해 정보  담기 기능

2) DAO에서 담아온 정보 View페이지로 보내는 역할*/



public class CmtModifyAction implements Action {

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
		System.out.println("C : CmtModifyAction_execute() 실행");

		// view정보를 저장
		int cmtID = Integer.parseInt(request.getParameter("cmtID"));
		
		// DAO 객체 생성 - > cmtID에 해당하는 댓글정보를 가져옴
		CmtDAO cmtao  = new CmtDAO();
		
		
		
		// request영역에 저장
		request.setAttribute("cmtbean", cmtao.getcmt(cmtID));
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./cmt/cmtModify.jsp");
		forward.setRedirect(false);
		
		return forward;
}
}