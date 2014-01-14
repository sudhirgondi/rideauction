package com.sav.ra.db.adapters;

import java.sql.*;
import java.util.ArrayList;

import com.sav.ra.domain.RAUser;

public class TestJDBC {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/rideauction", "root", "root");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into user(user_id, password) values ('testuser', 'yyy')");
//			conn.commit();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/rideauction", "root", "root");
			Statement stmt = conn.createStatement();
//			stmt.executeUpdate("insert into user(user_id, password) values ('testuser', 'yyy')");
			ArrayList<RAUser> userList = new ArrayList<RAUser>();
			ResultSet rs = stmt.executeQuery("select * from user");
			while(rs.next()){
				String userName = rs.getString("user_id");
				String password = rs.getString("password");
//				System.out.println(rs.getString("user_id"));
//				System.out.println(rs.getString("password"));
				RAUser user = new RAUser(userName, password);
				userList.add(user);
				userList.
			}
//			conn.commit(); //auto commits.
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
