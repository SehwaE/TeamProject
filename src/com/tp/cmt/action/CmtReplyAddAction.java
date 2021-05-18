package com.tp.cmt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.bean.CmtBean;
import com.tp.bbs.db.BbsDAO;
import com.tp.cmt.db.CmtDAO;
import com.tp.users.db.UserDAO;

public class CmtReplyAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			   System.out.println("M : CmtReplyAdd 호출");
		
		// 세션처리
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
		
		request.setCharacterEncoding("UTF-8");
		
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		int cmtID = Integer.parseInt(request.getParameter("cmtID"));
		int cmtSeq = Integer.parseInt(request.getParameter("cmtSeq"));
		int cmtLev = Integer.parseInt(request.getParameter("cmtLev"));
		int cmtRef = Integer.parseInt(request.getParameter("cmtRef"));
		String cmtReply = request.getParameter("cmtReply");
		
				System.out.println("전달되는 댓댓글 확인 ::::::::::::"+cmtReply);
		
				System.out.println("M :::bbsid:::"+bbsID);
				System.out.println("M :::cmtID:::"+cmtID);
				System.out.println("M :::cmtSeq:::"+cmtSeq);
				System.out.println("M :::cmtLev:::"+cmtLev);
			 
				
		
		CmtBean cb = new CmtBean();
		
		cb.setCmtID(cmtID);
		cb.setUserID(userID);
		cb.setCmt_content(cmtReply);
		cb.setBbsID(bbsID);
		cb.setIp(request.getRemoteAddr());
		cb.setCmt_seq(cmtSeq);
		cb.setCmt_lev(cmtLev);
		
		if(cmtSeq !=0){
			cb.setCmt_ref(cmtRef);
		}else{
			cb.setCmt_ref(cmtID);
		}
			
		
		
		System.out.println("cb ********* ***"+cb);
		
		CmtDAO cdao = new CmtDAO();
		cdao.cmtReAdd(cb);
		
		 
		ActionForward forward = new ActionForward();
		forward.setPath("./ReviewPage.ao?bbsID="+bbsID);
		forward.setRedirect(true);
		return forward;
	}

}
