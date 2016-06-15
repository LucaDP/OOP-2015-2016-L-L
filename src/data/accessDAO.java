package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class accessDAO implements authDAO {
	
	/**************************************************************************************/
	public Boolean check(String username, String email) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		ResultSet rs;
		
		Boolean a=true;
		
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT * FROM utenti WHERE username='" + username + "'or email='" + email + "'")){
		        rs = stmt.getResultSet(); 
		        /* rs.last();
		        a=rs.getRow();*/
		    	a= rs.isBeforeFirst();   
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		   
		}
		 return a;
	}
	/***********************************************************************************/
	
	public Boolean signin(String username, String password, String email) throws Exception{ /**/
		Connection conn = dbConnect.connect();
		Statement stmt;
		try {
		    stmt = conn.createStatement();
		    if (stmt.execute("INSERT INTO utenti (username, password, email, gruppo) VALUES ('"+username+"','"+password+"','"+email+"','ua')")){
		    	return true;
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
	/****************************************************************************************/		
	
	
	/**
	 * @throws Exception 
	 * 
	 */
	public Utente access(String username) throws Exception{
		Utente user= new Utente(null,null,null,null);
		Connection conn = dbConnect.connect();
		Statement stmt;
		ResultSet rs;
		
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT * FROM utenti WHERE username='"+username+"'")){
		        rs = stmt.getResultSet();
		        
		    
		        while (rs.next()) {
		        	 user.setUsername(rs.getString("username"));
		        	 user.setEmail(rs.getString("email"));
		        	 user.setPassword(rs.getString("password"));
		        	 user.setPermessi(rs.getString("gruppo"));

	        	 }
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		return user;	
		
	}
	
	
	
	public Boolean signin(String nomeoperatore, String password, String email, String ruolo) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		 
		try {
		    stmt = conn.createStatement();
		    if (stmt.execute("INSERT INTO utenti (username, password, email, gruppo) VALUES ('"+nomeoperatore+"','"+password+"','"+email+"','"+ruolo+"')")){
		    	/****NON LO FA IL DAO***/
		    	JOptionPane.showMessageDialog (null, "operatore  inserito");
		    	return true;
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	} 
	/**********************************************************************************************/
	public Boolean promutenza(String usernameutente,String permesso) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		 
		try {
		    stmt = conn.createStatement();
		    
		     System.out.println(usernameutente);
		     System.out.println(permesso);
            	
		     if (stmt.execute("UPDATE utenti"
        				+ " SET gruppo = '"+permesso+ 
        				 "' WHERE username = '"+usernameutente+"'")){
		    	 
		    	 /***NON LO FA IL DAO***/
    		    	JOptionPane.showMessageDialog (null, "operatore  inserito");
    		    	return true;
    		    }
            
		
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;



}
	
}
