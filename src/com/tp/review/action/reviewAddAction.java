package com.tp.review.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.db.FileDAO;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class reviewAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewListAction_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();

		// 세션 제어
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
		}

		// 파일 처리
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload").replace("\\\\", "/");
		System.out.println(" M : ctx :::: " + ctx);
		System.out.println(" M : realpath :::: " + realpath);

		FileDAO fdao = new FileDAO();

		int maxSize = 10 * 1024 * 1024; // 10 MB

		MultipartRequest multi = new MultipartRequest(request, realpath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Enumeration fileNames = multi.getFileNames();

		String parameter = (String) fileNames.nextElement();
		String fileName = multi.getOriginalFileName(parameter);
		String fileRealName = multi.getFilesystemName(parameter);

		System.out.println("BbsAddAction parameter : " + parameter);
		System.out.println("BbsAddAction fileName : " + fileName);
		System.out.println("BbsAddAction fileRealName : " + fileRealName);

		fdao.upload(fileName, fileRealName, userID);

		// 게시글 작성
		BbsBean bbs = new BbsBean();
		ReDAO rdao = new ReDAO();
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getUser(userID);

		bbs.setBbs_category(Integer.parseInt(multi.getParameter("bbs_category")));
		bbs.setBbs_title(multi.getParameter("bbs_title").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setBbs_content(multi.getParameter("bbs_content").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setUserID(multi.getParameter("userID"));
		bbs.setUserNickName(ub.getUserNickName());
		bbs.setIp(request.getRemoteAddr());

		rdao.insertBbs(bbs);

		System.out.println("BbsAddAction : " + bbs);

		// 페이지 이동
		forward.setPath("./ReviewList.ao");
		forward.setRedirect(true);
		// true - sendRedirect() 방식으로 이동 (주소,화면 변경O)
		// false - forward() 방식으로 이동 (주소 변경x, 화면 변경 O)

		return forward;
	}

}
