package com.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {

	Connection con;

	public Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hello");
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/secureitpro", "root", "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}
	
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = con.createStatement();
		return sta.executeQuery(sql);
	}
	public static void main(String args[])
	{
		DbConnection con = new DbConnection();
		if(con.getCon()!=null)
		{
			System.out.println("succeed");
		}
	}

}
