package com.tp.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.bbs.bean.BbsBean;
import com.tp.bbs.bean.LikeBean;
import com.tp.bbs.db.BbsDAO;
import com.tp.like.action.LikeDAO;

public class BbsLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : BbsLikeAction 호출");
		
		// 세션처리
		// 로그인한 아이디를 해야함
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		// 가져온거 저장
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		System.out.println("M : likeAction bbsID 확인 :" + bbsID);

		// BbsBean에꺼 LikeBean에 저장
		BbsDAO bdao = new BbsDAO();
		BbsBean bb = bdao.getBbs(bbsID);

		LikeBean lb = new LikeBean();
		LikeDAO ldao = new LikeDAO();

		lb.setUserID(userID);
		lb.setBbsID(bbsID);

		if (userID != null) {

			// likID있는지확인하고 있으면 삭제 없으면 생성
			String likeCheck = Integer.toString(ldao.CreatlikeID(lb));
			System.out.println("M : likeResult 확인   /// 1: 좋아요, 0: 좋아요 취소///  결과값 :" + likeCheck);

			if (likeCheck.equals("1")) {
				// 최종 좋아요 숫자
				String result = Integer.toString(ldao.totalLike(lb));
				System.out.println("M :최종 좋아요 수 ::::" + result);

				PrintWriter out = response.getWriter().append(likeCheck).append(",").append(result);
				out.flush();
				out.close();
			} else {
				String result = Integer.toString(ldao.totalLike(lb));
				PrintWriter out = response.getWriter().append(likeCheck).append(",").append(result);
				out.flush();
				out.close();

			}

		} else { // ID가 없을때
			System.out.println("M: 좋아요 반영 안됨 /// userID 없음");

			response.setContentType("text/html; charset=UTF-8");

			String err = "로그인시 이용가능합니다.";
			PrintWriter out = response.getWriter().append(err);

			out.flush();
			out.close();

		}

		return null;
	}

}
