package com.sav.ra.db.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DemoJDBC {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		 //1.Register Driver
//		Class.forName("com.mysql.jdbc.Driver");
//		//2. Get Connection
//		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test", "sqa", "sqa");
//		
//		//3. 
//		Statement stmt=conn.createStatement();
//		
//		stmt.executeUpdate("insert into user values('xxx','yyyy')");
//		conn.commit();
//		//Close the Statements and Connection
//		stmt.close();
//		conn.close();
//
//	}
	//Query data
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 //1.Register Driver
		Class.forName("com.mysql.jdbc.Driver");
		//2. Get Connection
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/test", "sqa", "sqa");
		
		//3. 
		Statement stmt=conn.createStatement();
		//4
		ResultSet rs=stmt.executeQuery("select * from user");
		while(rs.next()){
			String name=rs.getString("user_id");
			
			String password=rs.getString("password");
			
			System.out.println(" User Name:"+name+" password="+password);
		}
		rs.close();
		//Close the Statements and Connection
		stmt.close();
		conn.close();

	}


}
