package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;



public class dbConnect {
	
	protected static String url = "jdbc:mysql://127.0.0.1:3306/digitallibrary?useSSL=false";
	protected static String user = "root";
	protected static String psw = "";
	
	
	public static Connection db;
	
	 public static Connection connect() throws Exception{
		 try {
			   
			 db = DriverManager.getConnection(url,user,psw);
			 System.out.println("CONNESSO"); 
			 
			 
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		
		 return db;
	
		 
	 }
	
}

