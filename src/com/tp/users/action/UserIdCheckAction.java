package com.tp.users.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.users.db.UserDAO;

public class UserIdCheckAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : UserIdCheckAction_execute() 호출 ");

		String id = request.getParameter("userID");
		UserDAO udao = new UserDAO();

		int result = udao.duplicateIdCheck(id);

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
