package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangepwDao {
	public int changepass(Connection con, String pass,int id)
			throws SQLException {
		// TODO Auto-generated method stub
		String SQL = "update antitheft_login set password='"+pass+"' where id="+id;
		int result = 0;
		Statement stmt = con.createStatement();
		result = stmt.executeUpdate(SQL);
		return result;
	}
	public ResultSet fetchPid(Connection con, int id) throws SQLException {
		// TODO Auto-generated method stub

		String SQL = "select password from antitheft_login where id='" + id +"'";
		Statement st;
		st = con.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		return rs;

	}


}
