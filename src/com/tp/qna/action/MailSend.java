package com.tp.qna.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailSend implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		Authenticator auth = new MailAuth(); // 메일 경로 설정, 수정할 사항 없음

		Session session = Session.getDefaultInstance(prop, auth);
		MimeMessage msg = new MimeMessage(session); 

		try {
			msg.setSentDate(new Date()); // 날짜 설정
			msg.setFrom(new InternetAddress(sender));
											// 보내는 아이디, 작성될 이름
			InternetAddress to = new InternetAddress(receiver);
														// 받는 사람 아이디
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject(subject, "UTF-8");
							// 말 그대로 제목
			msg.setText(content, "UTF-8");
						// 우리가 전달할 내용, 여기에 랜덤 값 부여해서 작성하면 될 것 같습니다.
						// 이외에도 다른 용도로도 사용할 수 있지 않을까 합니다.
			Transport.send(msg);
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('전송 완료!');");
			out.println("location.href='./Main.do';");
			out.println("</script>");
			out.close();
			
			return null;
			
		} catch (Exception e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('전송에 실패했습니다 관리자에게 문의하세요!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			e.printStackTrace();
			
			return null;
		}
		
	}

}
