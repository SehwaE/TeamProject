package com.tp.notice.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tp.bbs.bean.BbsBean;
import com.tp.notice.db.NoticeDAO;
import com.tp.notice.db.NoticeFileDAO;
import com.tp.users.db.UserBean;
import com.tp.users.db.UserDAO;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : NoticeWriteAction_execute 호출");

		// 로그인 세션 확인
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		ActionForward forward = new ActionForward();
		if (userID == null || !userID.equals("admin")) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);
			return forward;
		}

		System.out.println("M : NoticeWriteAction - userID : " + userID);

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");

		// 파일 처리
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload").replace("\\\\", "/");
		System.out.println(" M : realpath -> " + realpath);

		NoticeFileDAO fdao = new NoticeFileDAO();

		int maxSize = 10 * 1024 * 1024; // 10 MB

		MultipartRequest multi = new MultipartRequest(request, realpath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Enumeration fileNames = multi.getFileNames();

		String parameter = (String) fileNames.nextElement();
		String fileName = multi.getOriginalFileName(parameter);
		String fileRealName = multi.getFilesystemName(parameter);

		fdao.upload(fileName, fileRealName, userID);

		System.out.println("BbsAddAction parameter : " + parameter);
		System.out.println("BbsAddAction fileName : " + fileName);
		System.out.println("BbsAddAction fileRealName : " + fileRealName);

		System.out.println("fileName : " + fileName);
		System.out.println("fileRealName : " + fileRealName);
		System.out.println("userID : " + userID); // 세션 아이디로 추후 변경

		BbsBean bbs = new BbsBean();
		NoticeDAO bdao = new NoticeDAO();
		UserDAO udao = new UserDAO();
		UserBean ub = udao.getUser(userID);

		bbs.setUserID(userID);
		bbs.setIp(request.getRemoteAddr());
		bbs.setUserNickName(ub.getUserNickName());
		bbs.setBbs_title(multi.getParameter("bbs_title").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setBbs_content(multi.getParameter("bbs_content").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
		bbs.setBbs_category(Integer.parseInt(multi.getParameter("bbs_category")));

		System.out.println("M : 전달된 값 저장완료" + bbs);

		bdao.insertBbs(bbs);

		// 페이지 이동
		forward.setPath("./NoticeList.no");
		forward.setRedirect(true);

		return forward;
	}

}
