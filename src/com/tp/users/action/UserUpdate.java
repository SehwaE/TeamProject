package com.tp.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.users.db.UserDAO;

public class UserUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("M : UserUpadate_execute() 호출 ");

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		ActionForward forward = new ActionForward();
		if (userID == null) {
			forward.setPath("./UserLogin.do");
			forward.setRedirect(true);

			return forward;
		}

		UserDAO udao = new UserDAO();
		request.setAttribute("ub", udao.getUser(userID));

		forward.setPath("./user/updateForm.jsp");
		forward.setRedirect(false);

		return forward;
	
	}
}
