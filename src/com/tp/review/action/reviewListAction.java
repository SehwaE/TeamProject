package com.tp.review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.bbs.db.FileDAO;

public class reviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : reviewListAction_execute() 호출");

		// 한글데이터 처리
		request.setCharacterEncoding("UTF-8");

		ReDAO rdao = new ReDAO();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) { // 만약 페이지번호가 null일 경우 페이지번호는 1
			pageNum = "1";
		}

		// 파일 처리
		FileDAO fdao = new FileDAO();
		ArrayList fileName = fdao.getFileList();

		String sc_ck = request.getParameter("sc_ck");
		String search = request.getParameter("search");
		System.out.println("sc_ck : " + sc_ck);
		System.out.println("search : " + search);

		int currentPage = Integer.parseInt(pageNum);
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		int pagePerCol = 3;
		int totalRowCount = 0;
		if (sc_ck == null) {
			totalRowCount = rdao.getBbsCount(); // 전체 컬럼 수
		} else if (sc_ck.equals("null")) {
			totalRowCount = rdao.getBbsCount(); // 전체 컬럼 수
		} else {
			totalRowCount = rdao.getBbsCount(sc_ck, search); // 검색 후 컬럼 수
		}
		int pagePerRow = 6; // 한 페이지에 출력할 컬럼 수
		int startRow = (currentPage - 1) * pagePerRow; // 시작 페이지
		int endPage = totalRowCount / pagePerRow; // 마지막 페이지
		if (totalRowCount % pagePerRow != 0) { // 전체 페이지 / 컬럼을 나눴을 때 0이 아니면 +1
			endPage++;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pagePerRow", pagePerRow);
		request.setAttribute("pagePerCol", pagePerCol);
		request.setAttribute("endPage", endPage);

		if (sc_ck == null) {
			request.setAttribute("totalRowCount", totalRowCount);
			request.setAttribute("bbsList", rdao.getBbsList(startRow, pagePerRow));
		} else if (sc_ck.equals("null")) {
			request.setAttribute("totalRowCount", totalRowCount);
			request.setAttribute("bbsList", rdao.getBbsList(startRow, pagePerRow));
		} else {
			request.setAttribute("sc_ck", sc_ck);
			request.setAttribute("search", search);
			request.setAttribute("totalRowCount", totalRowCount);
			request.setAttribute("bbsList2", rdao.getBbsList(startRow, pagePerRow, sc_ck, search));
		}

		// 담아가지고
		request.setAttribute("fileName", fileName);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./review/reviewListPage.jsp");

		forward.setRedirect(false);
		// true - sendRedirect() 방식으로 이동 (주소,화면 변경O)
		// false - forward() 방식으로 이동 (주소 변경x, 화면 변경 O)

		return forward;
	}

}
