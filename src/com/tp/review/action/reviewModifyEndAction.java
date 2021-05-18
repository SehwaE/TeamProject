package com.tp.review.action;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.FileDAO;

public class reviewModifyEndAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewModifyEndAction_execute() 호출");

		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		// 파일 경로 설정
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload");
		System.out.println(" M : ctx :::: " + ctx);
		System.out.println(" M : realpath :::: " + realpath);

		int maxSize = 10 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, realpath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// BbsBean 정보 저장하고
		BbsBean bb = new BbsBean();

		bb.setBbsID(Integer.parseInt(multi.getParameter("bbsID")));
		bb.setBbs_category(Integer.parseInt(multi.getParameter("bbs_category")));
		bb.setBbs_title(multi.getParameter("bbs_title").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bb.setBbs_content(multi.getParameter("bbs_content").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));

		System.out.println("))))))))))))))))))))))) BbsBean : " + bb);

		// 게시판 DAO 처리하고

		ReDAO rdao = new ReDAO();
		rdao.updateBbs(bb);

		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ bbsID : " + bb.getBbsID());

		System.out.println("-------------- 파일 처리 ----------------");

		File file = multi.getFile("fileName");
		System.out.println("file : " + file);

		Enumeration<String> fileNames = multi.getFileNames();

		int bbsID = Integer.parseInt(multi.getParameter("bbsID"));

		if (file != null) {
			String prev = new FileDAO().getFile(bbsID);
			File prevFile = new File(realpath + "/" + prev);

			if (prevFile.exists()) {
				prevFile.delete();
			}
			String parameter = (String) fileNames.nextElement();
			String fileName = multi.getOriginalFileName(parameter);
			String fileRealName = multi.getFilesystemName(parameter);

			System.out.println(" BbsModifyEndAction - fileName : " + fileRealName);
			new FileDAO().updateFile(fileName, fileRealName, bbsID);
		}

		// 페이지 이동

		forward.setPath("/ReviewPage.ao?bbsID=" + bb.getBbsID());
		/* forward.setPath("/Main.do"); */
		forward.setRedirect(false);

		return forward;

	}

}
