package com.eduu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static String driver;
	public static String url;
	public static String un;
	public static String pass;
	
	private static Connection conn=null;
	public static Connection getConnection() {
		driver="com.mysql.cj.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/studentmanagementsystem";
		un="root";
		pass="root";
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,un,pass);
		}
		catch(Exception e)
		{
			System.out.println("Connection is not available");
			e.printStackTrace();
			
		}
		
			
		return conn;
		
	}

}
