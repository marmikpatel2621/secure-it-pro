package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ChangepwDao;
import com.model.DbConnection;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class changepwd
 */
public class changepwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changepwd() {
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

		String opwd = request.getParameter("opwd");
		String npwd = request.getParameter("npwd");
		String cpwd = request.getParameter("cpwd");
		String uid = request.getParameter("id");
		String flag = request.getParameter("flag");
		HttpSession ses = request.getSession();
		System.out.println("opwd="+opwd);
		int id = Integer.parseInt(uid);
		DbConnection db = new DbConnection();
		Connection c = (Connection) db.getCon();
		ChangepwDao cpd = new ChangepwDao();
		ResultSet rs = null;
		String pwd = null;
		int result = 0;
		try {
			rs = cpd.fetchPid(c, id);
			while(rs.next())
			{
			pwd = rs.getString(1);
			System.out.println("pwd="+pwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (opwd.equals(pwd)) {
			try {
				result = cpd.changepass(c, npwd, id);
				if ( result != 0 )
				{
					System.out.println("Password Change Successfully");
					request.setAttribute("Success", "Password Change Successfully.");
					RequestDispatcher rd = request.getRequestDispatcher("setting.jsp?id="+id+"&flag="+flag);
					rd.include(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Password Change Failed");
			request.setAttribute("ERROR", "Password Change Failed. \n Old Password Mismatched");
			RequestDispatcher rd = request.getRequestDispatcher("changepwd.jsp?id="+id);
			rd.include(request, response);
		}
	}

}
