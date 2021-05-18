package com.tp.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.bean.LikeBean;
import com.tp.bbs.db.FileDAO;
import com.tp.cmt.db.CmtDAO;
import com.tp.like.action.LikeDAO;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class reviewPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewPageAction_execute() 호출");

		ActionForward forward = new ActionForward();
		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		// 정보저장
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));

		// DAO 생성
		ReDAO rdao = new ReDAO();
		rdao.updateReadCount(bbsID);
		BbsBean bbsDetail = rdao.getBbs(bbsID);

		// 파일 처리
		FileDAO fdao = new FileDAO();
		String fileName = fdao.getFile(bbsID);

		// 좋아요 처리
		LikeDAO ldao = new LikeDAO();
		LikeBean lb = new LikeBean();
		lb.setUserID(userID);
		lb.setBbsID(bbsID);
		lb.setBbs_category(bbsDetail.getBbs_category());
		int likeCheck = ldao.likeCheck(userID, bbsID);
		int likeSum = ldao.totalLike(lb);

		// 댓글 처리
		UserDAO udao = new UserDAO();
		CmtDAO cmtao = new CmtDAO();
		
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		int totalRowCount = cmtao.getCmtCount(bbsID); // 전체 컬럼 수
		int pagePerRow = 10; // 한 페이지에 출력할 컬럼 수
		int startRow = (currentPage - 1) * pagePerRow; // 시작 페이지
		int endPage = totalRowCount / pagePerRow; // 마지막 페이지
		if (totalRowCount % pagePerRow != 0) { // 전체 페이지 / 컬럼을 나눴을 때 0이 아니면 +1
			endPage++;
		}

		// request영역에 저장
		request.setAttribute("bbsDetail", bbsDetail);
		request.setAttribute("fileName", fileName);
		request.setAttribute("likeCheck", likeCheck);
		request.setAttribute("likeSum", likeSum);
		request.setAttribute("cmtList", cmtao.getCmtList(bbsID, startRow, pagePerRow));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalRowCount", totalRowCount);
		request.setAttribute("pagePerRow", pagePerRow);
		request.setAttribute("endPage", endPage);
		/*request.setAttribute("user_nk", udao.getUser(userID));*/

		System.out.println("))))))))))))))))))))))))))))) BbsViewAction - bbsDetail : " + bbsDetail);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ BbsViewAction - fileName : " + fileName);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ BbsViewAction - likeCheck : " + likeCheck);
		System.out.println("M : likeSum ::::" + likeSum);

		// 페이지 이동
		forward.setPath("./review/reviewPage.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
