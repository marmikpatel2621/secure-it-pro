package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.DbConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LoginUser
 */
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String uName = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String flag = request.getParameter("flag");
		request.setAttribute("uName", uName);
		request.setAttribute("pwd", pwd);
		request.setAttribute("flag",flag);
		int id = 0;
		UserDao user = new UserDao();
		DbConnection d = new DbConnection();
		Connection con = (Connection) d.getCon();
		if (d.getCon() != null) {
			System.out.println("succeed");
		}
		try {
			id = user.specific_search(uName, pwd, con);
			System.out.println("helllo " + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (id != 0) {
			HttpSession ses = request.getSession();
			ses.setAttribute("session",id);
			RequestDispatcher rd = request
					.getRequestDispatcher("selectuser.do?id="+id+"&flag="+flag);
			rd.include(request, response);

		} else {
			System.out.println("Error Login Called");
			request.setAttribute("ERROR", "Invalid Username or Password.");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

}
