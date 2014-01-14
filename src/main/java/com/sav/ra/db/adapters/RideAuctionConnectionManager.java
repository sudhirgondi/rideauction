package com.sav.ra.db.adapters;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sav.ra.adapters.RAVendorAdapter;
import com.sav.ra.framework.RAPropertyManager;



public class RideAuctionConnectionManager {
	
	static Logger logger=Logger.getLogger(RAVendorAdapter.class);

//	public static Connection getConnection() throws Exception{
//		try {
//			
//			//registering oracle driver
//			//Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			//registering mysql driver
//			Class.forName("com.mysql.jdbc.Driver");
//
//		    // return  
//		// get the connection object from DriverManager     		 
//		//Connection dbconn=DriverManager.getConnection("jdbc:mysql://localhost/sqa","abuser","abcpassword");
//		    
////		Statement stmt=dbconn.createStatement();
////		
////		stmt.executeUpdate("insert into user (user_name,password) values ('abcuser','abcpass')");
//		//Step 1
//		Class.forName("com.mysql.jdbc.Driver");
//		//Step 2
//		Connection dbconn=DriverManager.getConnection("jdbc:mysql://localhost/sqa","ziffow","ziffow");
//		//Step 3
//		Statement stmt=dbconn.createStatement();
//		//Step 4
//		ResultSet rs=stmt.executeQuery("select user_id , password from test.user " );
//		
//		while(rs.next()){
//			
//			String userName=rs.getString(1);
//			String userPass=rs.getString(2);
//			
//			System.out.println("user ="+userName+ " password="+userPass);
//		}
//		//step 5
//		rs.close();
//		stmt.close();
//		dbconn.close();
//		
//		
//		return dbconn;
//		
//		// Do something with the Connection
//		
//		} catch (SQLException ex) {
//		    // handle any errors
//		    System.out.println("SQLException: " + ex.getMessage());
//		    System.out.println("SQLState: " + ex.getSQLState());
//		    System.out.println("VendorError: " + ex.getErrorCode());
//		}
//		return null;
//		}
//	
//	public static void insertUser() throws Exception{
//		try {
//			
//			//registering oracle driver
//			//Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			//registering mysql driver
//			Class.forName("com.mysql.jdbc.Driver");
//
//		    // return  
//		// get the connection object from DriverManager     		 
//		//Connection dbconn=DriverManager.getConnection("jdbc:mysql://localhost/sqa","abuser","abcpassword");
//		    
////		Statement stmt=dbconn.createStatement();
////		
////		stmt.executeUpdate("insert into user (user_name,password) values ('abcuser','abcpass')");
//		//Step 1
//		Class.forName("com.mysql.jdbc.Driver");
//		//Step 2
//		Connection dbconn=DriverManager.getConnection("jdbc:mysql://localhost/sqa","ziffow","ziffow");
//		//Step 3
//		Statement stmt=dbconn.createStatement();
//		//Step 4
//		stmt.executeUpdate("insert into user values ('abcuser','abcpassword')" );
//
//		//step 5 commit data		
//		dbconn.commit();
//	
//		//close objects
//		stmt.close();
//		dbconn.close();
//		
//		
//		
//		
//		// Do something with the Connection
//		
//		} catch (SQLException ex) {
//		    // handle any errors
//		    System.out.println("SQLException: " + ex.getMessage());
//		    System.out.println("SQLState: " + ex.getSQLState());
//		    System.out.println("VendorError: " + ex.getErrorCode());
//		}
//		
//		}
//
//	
	public static void main(String[] args) throws Exception {
		RideAuctionConnectionManager rideAuctionConnectionManager	=new RideAuctionConnectionManager();
		new RideAuctionConnectionManager().getConnection();
	}
	
	
	public static void closeConnection(Connection connection){
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				logger.error("Database connection not terminated! " + e.getMessage());
				//logger.severe("Database connection not terminated!!");
			}
		}
	}	
public static Connection getConnection() {
		
		try {
			Properties raProperties=RAPropertyManager.getProprties();
			
			// ���������� ������� MySQL
			// Initiating MySQL driver
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName(raProperties.getProperty("DBDriver")).newInstance();		
					
			
			// ������� � ��������� �����������
			// Create and return a connection
			return DriverManager.getConnection(raProperties.getProperty("DBUrl"), raProperties.getProperty("DBUserId"), raProperties.getProperty("DBPassword"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return null;

	}
	
	

}
