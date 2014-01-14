package com.sav.ra.db.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sav.ra.adapters.RAVendorAdapter;
import com.sav.ra.domain.RAConsants;
import com.sav.ra.framework.RAPropertyManager;



/**
 * 
 * ����� ��� ������ � ����� ������ MySQL. ������������ ��� �������� � �������� ����������� � �� � ���������� �������� �������, ������� � ���������� ������ � ��������
 * Class for working with MySQL database. It is used for creation and closing of database connection and performing sample queries, insertion, and updating of the data in tables
 *
 */
public class RADatabaseAdapter {

	/**
	 * ������� � ���������� ����������� � ���� ������
	 * Creates and returns a database connection
	 */
	static Logger logger=Logger.getLogger(RAVendorAdapter.class);
	
	
	
	/**
	 * ��������� ����������� � ���� ������
	 * Close the database connection
	 */

	
	/**
	 * ��������� ������ (�������) � ���� ������, ��������� ��� ���� ���������
	 * Perform a query (selection) to the database and return the result
	 */
	public static ResultSet executeQuery( String query){
		
		try {
			Connection connection=RideAuctionConnectionManager.getConnection();
			// ��������� ������ � ��
			// Perform a query to the database
					
			ResultSet resultQuery = connection.createStatement().executeQuery(query);
			return resultQuery;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// ���������� � ��� ������
			// Recording mistake into log
			System.out.println(e.getMessage());
			
			logger.error("SQL Error: " + e.getMessage() + ". Query: " + query);
			
			return null;
			
		}
		
	}
	
	/**
	 * ��������� ������ (������� ��� ���������� ������) � ��
	 * Perform a query (insert, update) to the database
	 */
	public static int executeUpdate( String query){
		
		int resultQuery;
		Connection connection=RideAuctionConnectionManager.getConnection();
		try {
			
			// Perform a query to the database
			resultQuery = connection.createStatement().executeUpdate(query);
			return resultQuery;
			
		} catch (SQLException e) {
			
			// � ������ ������� ���������� � ��� ������ � ���������� ��� -1
			// In case of failure we recording mistake into log and returning code-1
			logger.error("SQL Error: " + e.getMessage() + ". Query: " + query);
			
			return -1;
			
		}
		
	}

}
