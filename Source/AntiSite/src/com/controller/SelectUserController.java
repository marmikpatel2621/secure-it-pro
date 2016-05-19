package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ProfileBean;
import com.dao.UserDao;
import com.model.DbConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class SelectUserController
 */
public class SelectUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectUserController() {
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
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession();
		String uid = request.getParameter("id");
		String flag = request.getParameter("flag");
		int id = Integer.parseInt(uid);
		System.out.println("My Id:" + id);
		DbConnection d = new DbConnection();
		Connection con = (Connection) d.getCon();
		UserDao user = new UserDao();
		ProfileBean profile = null;
		try {
			profile = user.getDetailById(id, con);
			System.out.println("id=" + profile.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("profile", profile);
		if (profile != null) {
			System.out.println("id=" + id);
			RequestDispatcher rd = request
					.getRequestDispatcher("profile.jsp?id = "+id+"&flag="+flag);
			rd.forward(request, response);
		}

	}

}
