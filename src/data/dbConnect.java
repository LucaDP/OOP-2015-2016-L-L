package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;



public class dbConnect {
	
	final static String url = "jdbc:mysql://127.0.0.1:3306/digitallibrary1?useSSL=false";
	final static String user = "root";
	final static String psw = "";
	
	
	public static Connection db;
	
	 public static Connection connect() throws Exception{
		 try {
			   
			 db = DriverManager.getConnection(url,user,psw);
			 
			 
			 
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		
		 return db;
	
		 
	 }
	
}

