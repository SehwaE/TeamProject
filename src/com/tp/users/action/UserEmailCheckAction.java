package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.users.db.UserDAO;

public class UserEmailCheckAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserEmailCheckAction_execute() 호출 ");

		String userEmail = request.getParameter("userEmail");
		UserDAO udao = new UserDAO();

		int result = udao.duplicateEmailCheck(userEmail);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (result == 0) {
			out.println("0");
		} else {
			out.println("1");
		}
		out.close();

		return null;
	}
}
