package com.tp.event.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.bbs.db.FileDAO;
import com.tp.event.db.EventDAO;

public class EventListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : EventListAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		EventDAO evdao = new EventDAO();	
		
		FileDAO fdao = new FileDAO();
		ArrayList fileList = fdao.getFileList();
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int pagePerCol = 3; 
		int totalRowCount = evdao.getBbsCount();
		int pagePerRow = 6;
		int startRow = (currentPage-1) * pagePerRow;
		int endPage = totalRowCount / pagePerRow;
		
		if(totalRowCount % pagePerRow != 0) {
			endPage ++;
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalRowCounte", totalRowCount);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pagePerRow", pagePerRow);
		request.setAttribute("pagePercol", pagePerCol);
		request.setAttribute("bbsList", evdao.getBbsList(startRow, pagePerRow));
		request.setAttribute("fileList", fdao.getFileList());
		
		ActionForward forward = new ActionForward();
		forward.setPath("./event/EventList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
