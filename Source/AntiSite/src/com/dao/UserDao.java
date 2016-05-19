package com.dao;

import java.sql.SQLException;

import com.bean.ProfileBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class UserDao {
	public int specific_search(String email, String pw, Connection con)
			throws SQLException {
		int result = 0;
		System.out.println("E:" + email + " p:" + pw);
		String sqlAuthemticate = "select id from antitheft_login where email_id='"
				+ email + "' and password='" + pw + "'";
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = (ResultSet) stmt.executeQuery(sqlAuthemticate);
		while (rs.next()) {
			result = rs.getInt(1);
			System.out.println("Inside Authentication LOop: " + result);
		}
		if (result != 0) {
			return result;

		}
		return 0;
	}

	public ProfileBean getDetailById(int id, Connection con)
			throws SQLException {

		ProfileBean profile = null;
		String idd = id + "";
		String sqlDetail = " select * from antitheft_profile_details where id ="
				+ id;
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = (ResultSet) stmt.executeQuery(sqlDetail);
		while (rs.next()) {
			profile = new ProfileBean();

			profile.setId(rs.getInt(1));

			profile.setName(rs.getString(2));

			profile.setEmail(rs.getString(3));

			profile.setMobile(rs.getString(4));

			profile.setSim(rs.getString(5));

			profile.setImei(rs.getString(6));

		}
		return profile;
	}

}
