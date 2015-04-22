package net.social;

import java.sql.*;

public class ConnectDB {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/SocialProject";

	//  Database credentials
	static final String user = "root";
	static final String pass = "root";
	
	public static Connection ConnectDB() throws ClassNotFoundException {
		Connection con = null;
		String encode = "?useUnicode=yes&characterEncoding=UTF-8";
		String url = host+encode;
		try {
			Class.forName("com.mysql.jdbc.Driver");
				
			System.out.println("Connecting database...");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Database connected!");
			    
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		return con;
		}
		
	public static void InsertDB(Connection con,String Fid,String Fname){
		System.setProperty("file.encoding", "UTF-8");
		Statement stmt = null;
		try{
		 System.out.println("Creating statement...");
			stmt = con.createStatement();
			String sql = "INSERT INTO FollowingList(FollowingID,FollowingName) VALUES ("+Fid+", '"+Fname+"')";
			stmt.executeUpdate(sql);
		    System.out.println("Inserted records into the table...");
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		
	}
	   
	public static void main(String[] args) throws ClassNotFoundException { 
		Connection con = null;
		
		con = ConnectDB();
		InsertDB(con,"333","ชื่อ");
	}

}