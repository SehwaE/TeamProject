package com.tp.cmt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.cmt.action.Action;
import com.tp.cmt.action.ActionForward;

public class CmtReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : CmtReplyAction 호출");
		
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		int cmtID = Integer.parseInt(request.getParameter("cmtID"));
		int cmtSeq = Integer.parseInt(request.getParameter("cmt_seq"));
		int cmtLev = Integer.parseInt(request.getParameter("cmt_lev"));
		int cmtRef = Integer.parseInt(request.getParameter("cmt_ref"));
		System.out.println("RERERERERERERERE :::::::::::::: "+bbsID);
		System.out.println("RERERERERERERERE :::::::::::::: "+cmtID);
		System.out.println("RERERERERERERERE :::::::::::::: "+cmtSeq);
		System.out.println("RERERERERERERERE :::::::::::::: "+cmtLev);
		System.out.println("RERERERERERERERE :::::::::::::: "+cmtRef);
		
		System.out.println("C 잘넘어오나 확인히기:::: :::"+bbsID+cmtID+cmtSeq+cmtLev+cmtRef);
		
		request.setAttribute("bbsID", bbsID);
		request.setAttribute("cmtID", cmtID);
		request.setAttribute("cmtSeq", cmtSeq);
		request.setAttribute("cmtLev", cmtLev);
		request.setAttribute("cmtRef", cmtRef);
		
		
		ActionForward forward = new ActionForward();
		forward = new ActionForward();
		forward.setPath("./cmt/cmtReply.jsp");		
		forward.setRedirect(false);
		
	  
		
		return forward;
	}

}
