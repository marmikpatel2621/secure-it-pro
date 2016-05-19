package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ForgotDao;
import com.model.DbConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class ForgotController
 */
public class ForgotController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		DbConnection d = new DbConnection();
		Connection con = (Connection) d.getCon();
		ForgotDao fd = new ForgotDao();
		try {
			int Result = fd.forgotpass(con, email);
			if (Result == 0) {
				request.setAttribute("ERROR",
						"Your id is wrong.");
				RequestDispatcher rd2 = request
						.getRequestDispatcher("forgot.jsp");
				System.out.println("hello");
				rd2.include(request, response);
			} else {
				ResultSet rs = fd.fetchEid(con, email);
				String emailid = "";
				String pass="";
				while (rs.next()) {
					emailid = rs.getString(1);
					pass = rs.getString(2);
					}
								mailtoEmp(emailid,pass);

				RequestDispatcher rd = request
						.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mailtoEmp(String emailid,String pass) {
		// TODO Auto-generated method stub
		Properties pros = new Properties();
		
		pros.put("mail.smtp.user", "marmikpatel262@gmail.com");
		pros.put("mail.smtp.host", "smtp.gmail.com");
		pros.put("mail.smtp.port", "465");
		pros.put("mail.smtp.starttls.enable", "true");
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.debug", "true");
		pros.put("mail.smtp.socketFactory.port", "465");
		pros.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		pros.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(pros, null);
		session.setDebug(true);
		try {
			MimeMessage msg = new MimeMessage(session);
			String message;
			message = " Your request has been successfully accepted for forgot password. \n Your forgot password is "
					+ pass;

			msg.setText(message);
			System.out.println("124 "+message);
			msg.setSubject("Password for Secure It Pro");
			msg.setFrom(new InternetAddress("marmikpatel262@gmail.com"));
			
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailid));

			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "marmikpatel262@gmail.com",
					"manjulaben");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (Exception e) {

		}

	}

}
