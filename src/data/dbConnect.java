package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**
 * Classe per la connessione al database
 * @author Luca
 *
 */
public class dbConnect {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/digitallibrary1?useSSL=false";
	private static String user = "root";
	private static String psw = "";
	
	
	private static Connection db;
	/**
	 * Metodo per la connessione al database
	 * @return Connection
	 */
	protected static Connection connect(){
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

