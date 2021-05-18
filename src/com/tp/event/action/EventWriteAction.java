package com.tp.event.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.FileDAO;
import com.tp.event.db.EventDAO;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class EventWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : EventWriteAction_execute() 호출");

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (userID == null || !userID.equals("admin")) {
			out.print("<script>");
			out.print("alert('관리자만 등록가능합니다.');");
			out.print("location.href='./Main.do';");
			out.print("</script>");
			out.close();

			return null;
		}

		request.setCharacterEncoding("UTF-8");

		// 파일 처리
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/upload");
		System.out.println("M : realpath - " + realPath);

		int maxSize = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Enumeration fileNames = multi.getFileNames();

		String parameter = (String) fileNames.nextElement();
		String fileName = multi.getOriginalFileName(parameter);
		String fileRealName = multi.getFilesystemName(parameter);

		FileDAO fdao = new FileDAO();
		fdao.upload(fileName, fileRealName, userID);

		System.out.println("------------파일 처리 완료-------------");
		
		BbsBean bbs = new BbsBean();
		
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getUser(userID);

		bbs.setUserID(userID);
		bbs.setIp(request.getRemoteAddr());
		bbs.setUserNickName(ub.getUserNickName());
		bbs.setBbs_title(multi.getParameter("bbs_title").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setBbs_content(multi.getParameter("bbs_content").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setStart_month(multi.getParameter("start_month"));
		bbs.setEnd_month(multi.getParameter("end_month"));
		bbs.setBbs_category(Integer.parseInt(multi.getParameter("bbs_category")));

		EventDAO evdao = new EventDAO();
		evdao.insertEvent(bbs);

		System.out.println("M : 게시글 등록 완료");
		System.out.println(bbs);

		out.print("<script>");
		out.print("alert('게시글 등록이 완료되었습니다.');");
		out.print("location.href='./EventList.bd';");
		out.print("</script>");

		out.close();
		return null;
	}

}
