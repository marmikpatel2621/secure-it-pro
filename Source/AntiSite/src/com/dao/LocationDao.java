package com.dao;

import java.sql.SQLException;

import com.bean.LocationBean;
import com.bean.ProfileBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class LocationDao {
	public LocationBean getLocationById(int id,Connection con) throws SQLException
	{
		int result =0;
		LocationBean location=null;
		String sqlDetail = " select * from antitheft_location where id ="+ id; 
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = (ResultSet) stmt.executeQuery(sqlDetail);
		while(rs.next())
		{
			location = new LocationBean();
			location.setId(rs.getInt(1));
			location.setLongitude(rs.getString(2));
			location.setLatitude(rs.getString(3));
			location.setTime(rs.getDate(4));
			
		}
		return location;
	}
}
