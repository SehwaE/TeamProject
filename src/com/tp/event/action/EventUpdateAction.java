package com.tp.event.action;

import java.io.File;
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

public class EventUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventUpdateAction_execute() 호출");

		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		if (userID == null || !userID.equals("admin")) {
			ActionForward forward = new ActionForward();
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		request.setCharacterEncoding("UTF-8");

		// 파일 처리
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/upload");

		System.out.println("M : CTX - " + ctx);
		System.out.println("M : realpath - " + realPath);

		int maxSize = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		BbsBean bbs = new BbsBean();

		bbs.setBbsID(Integer.parseInt(multi.getParameter("bbsID")));
		bbs.setStart_month(multi.getParameter("start_month"));
		bbs.setEnd_month(multi.getParameter("end_month"));
		bbs.setBbs_title(multi.getParameter("bbs_title").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setBbs_content(multi.getParameter("bbs_content").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));

		System.out.println("BbsBean : " + bbs);

		EventDAO evdao = new EventDAO();
		evdao.eventUpdate(bbs, userID);

		File file = multi.getFile("fileName");
		System.out.println("file : " + file);

		Enumeration<String> fileNames = multi.getFileNames();
		int bbsID = Integer.parseInt(multi.getParameter("bbsID"));
		if (file != null) {
			String prev = new FileDAO().getFile(bbsID);
			File prevFile = new File(realPath + "/" + prev);
			if (prevFile.exists()) {
				prevFile.delete();
			}
			String parameter = (String) fileNames.nextElement();
			String fileName = multi.getOriginalFileName(parameter);
			String fileRealName = multi.getFilesystemName(parameter);

			System.out.println("EventUpdateAction - fileName: " + fileRealName);
			new FileDAO().updateFile(fileName, fileRealName, bbsID);
		}

		// 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('수정 완료되었습니다.');");
		out.println("location.href='./EventDetail.bd?bbsID=" + bbs.getBbsID()+"';");
		out.println("</script>");

		out.close();

		return null;
	}

}
