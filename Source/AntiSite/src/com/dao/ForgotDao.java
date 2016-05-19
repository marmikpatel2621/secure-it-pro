package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ForgotDao {

	public int forgotpass(Connection con, String id)
			throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "select count(email_id)  from antitheft_login where email_id='" + id +"'";
		int result = 0;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			result = rs.getInt(1);
			System.out.println("Inside forgetpass LOop: " + result);
		}
		if (result == 1) {
			return 1;

		}

		return 0;
	}
	public ResultSet fetchEid(Connection con, String id) throws SQLException {
		// TODO Auto-generated method stub

		String SQL = "select email_id,password from antitheft_login where email_id='" + id +"'";
		Statement st;
		st = con.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		return rs;

	}

}
