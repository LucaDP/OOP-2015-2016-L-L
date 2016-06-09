package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accessDAO implements DAO {
	
	public Boolean registration(String username, String password, String email){
		Connection conn = null;
		Statement stmt;
		ResultSet rs;
		int a=1;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ppo?useSSL=false","root","");
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT * FROM utenti WHERE username='" + username + "'or email='" + email + "'")){
		        rs = stmt.getResultSet(); 
		        rs.last();
		        a=rs.getRow();
		        
		       
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		if(a==0){
			
			try {
			    stmt = conn.createStatement();
			   
			    
			    if (stmt.execute("INSERT INTO utenti (username, password, email) VALUES ('"+username+"','"+password+"','"+email+"')"));
			}
			catch (SQLException ex){
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			return true;
			
			
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * 
	 */
	public String access(String username){
		String pwd = null;
		Connection conn = null;
		Statement stmt;
		ResultSet rs;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ppo?useSSL=false","root","");
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT password FROM utenti WHERE username='"+username+"'")){
		        rs = stmt.getResultSet();
		        
		    
		        while (rs.next()) {
		        	
		        	pwd = rs.getString("password");
		        
		        	
	        	 }
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		return pwd;	
		
	}

	
}
