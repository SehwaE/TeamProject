package com.tp.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.bbs.bean.BbsBean;
import com.tp.notice.db.NoticeDAO;
import com.tp.notice.db.NoticeFileDAO;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : /NoticeDetailAction_execute() 호출");
		
		// 정보저장
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		
		//DAO 생성
		NoticeDAO bbdao = new NoticeDAO();
		bbdao.updateReadCount(bbsID);
		BbsBean bbsDetail = bbdao.getBbs(bbsID);
		
		// 파일 처리
		NoticeFileDAO fdao = new NoticeFileDAO();
		String fileName = fdao.getFile(bbsID);
		
		
		// request영역에 저장
		request.setAttribute("bbsDetail", bbsDetail);
		request.setAttribute("fileName", fileName);
		
		System.out.println("M : bbsDetail "+bbsDetail);
		System.out.println(")))))))))))))))))))))))))))))"+fileName);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/noticeView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
