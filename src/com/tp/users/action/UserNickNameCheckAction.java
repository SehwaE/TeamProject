package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.users.db.UserDAO;

public class UserNickNameCheckAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserNickNameCheckAction_execute() 호출 ");

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		String userNickName = request.getParameter("userNickName");
		UserDAO udao = new UserDAO();

		int result = udao.duplicateNickCheck(userNickName);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (result == 0) {
			out.println("0");
		} else if (result == 1) {
			out.println("1");
		}

		out.close();

		return null;
	}
}
