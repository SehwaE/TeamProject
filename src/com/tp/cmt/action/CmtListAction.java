package com.tp.cmt.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.cmt.db.CmtDAO;



/*Action 페이지란?

1) view(.jsp)에서 가져온 정보 DAO 로 전달하기 위해 정보  담기 기능

2) DAO에서 담아온 정보 View페이지로 보내는 역할*/

public class CmtListAction implements Action {

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
	
      System.out.println("M : CmtListAction_execute() 호출");
      
      request.setCharacterEncoding("UTF-8");

      // 세션 정보 처리 -> 아이디 정보가 없을 경우 로그인페이지로 이동
      // userLoginAction에서 session처리함
      
/*       HttpSession session = request.getSession();		
	   String userID = (String) session.getAttribute("userID");
		
		CmtDAO cmtao = new CmtDAO();
		
		// 페이지 처리방식
		int currentPage = 1;
        if(request.getParameter("currentPage") != null) { 
        	currentPage = Integer.parseInt(request.getParameter("currentPage")); 
        }
        
        int pagePerCol = 3;
        int totalRowCount = cmtao.getCmtCount(bbsID); // 전체 컬럼 수
        int pagePerRow = 6; // 한 페이지에 출력할 컬럼 수
        int startRow = (currentPage-1) * pagePerRow; // 시작 페이지
        int endPage = totalRowCount / pagePerRow; // 마지막 페이지
        if(totalRowCount % pagePerRow != 0) { // 전체 페이지 / 컬럼을 나눴을 때 0이 아니면 +1
        	endPage++;
        }
        
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalRowCount", totalRowCount);
        request.setAttribute("pagePerRow", pagePerRow);
        request.setAttribute("pagePerCol", pagePerCol);
        request.setAttribute("endPage", endPage);
        request.setAttribute("cmtList", cmtao.getCmtList(startRow, pagePerRow));
        
        
		
		
	    // 페이지 이동("./cmt/cmtWrite.jsp")
*/        ActionForward forward = new ActionForward();
        forward.setPath("./cmt/view.jsp");
		forward.setRedirect(false);
	
		
		return forward;
	}
}
