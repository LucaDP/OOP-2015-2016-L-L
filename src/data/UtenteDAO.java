package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;





public class UtenteDAO implements UtenzaDAO {
	
	public boolean check(String username){
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		ResultSet rs;
		
		Boolean a=true;
		
		try {
		    pstmt = conn.prepareStatement("SELECT * FROM utenti WHERE username=?");
		    pstmt.setString(1, username);
		    
		    if (pstmt.execute()){
		        rs = pstmt.getResultSet(); 
		    	a= rs.isBeforeFirst();  	
		    }  
		    pstmt.close();
		    conn.close();
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		   
		}
		
		
		
		 return a;
	}

	
	public boolean signin(String username, String password, String email) {
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try {
		    pstmt = conn.prepareStatement("INSERT INTO utenti (username, password, email) VALUES (?,?,?)");
		    pstmt.setString(1,username);
		    pstmt.setString(2,password);
		    pstmt.setString(3,email);
		    if (pstmt.execute()){
		    	return true;
		    }
		    pstmt.close();
		    conn.close();
		}
		catch (SQLException ex){
		    
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
			
	

	
	public Utente access(String username){
		Utente user= new Utente(null,null,null,null);
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		ResultSet rs;
		
		try {
		    pstmt = conn.prepareStatement("SELECT * FROM utenti WHERE username=?");
		    pstmt.setString(1, username);
		    if (pstmt.execute()){
		        rs = pstmt.getResultSet();
		        while (rs.next()) {
		        	 user.setUsername(rs.getString("username"));
		        	 user.setEmail(rs.getString("email"));
		        	 user.setPassword(rs.getString("password"));
		        	 user.setPermessi(rs.getString("gruppo"));
	        	 }
		        rs.close();
		    } 
		    
	        pstmt.close();
	        conn.close();
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	    
		return user;	
		
	}
	
	public boolean creaOperatore(String nomeoperatore, String password, String email, String ruolo){
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		 
		try {
		    pstmt = conn.prepareStatement("INSERT INTO utenti (username, password, email, gruppo) VALUES (?,?,?,?)");
		    pstmt.setString(1, nomeoperatore);
		    pstmt.setString(2, password);
		    pstmt.setString(3, email);
		    pstmt.setString(4, ruolo);
		    if (pstmt.execute()){
		    	pstmt.close();
		    	conn.close();
		    	return true;
		    }  
		    conn.close();
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
    	
		return false;
	} 
	
	@Override
	public boolean promuoviUtente(String username, String permesso){
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try {
			pstmt= conn.prepareStatement("UPDATE utenti SET gruppo=? WHERE username=?");
			pstmt.setString(1, permesso);
			pstmt.setString(2, username);
			 if (pstmt.executeUpdate()>0){
				pstmt.close();
		    	conn.close();
 		    	return true;
 		    }	
			 pstmt.close();
			 conn.close();
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return false;
	}


	@Override
	public boolean cambioemail(String username, String email) {
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try {
			pstmt= conn.prepareStatement("UPDATE utenti SET email=? WHERE username=?");
			pstmt.setString(1, email);
			pstmt.setString(2, username);
			 if (pstmt.executeUpdate()>0){
				pstmt.close();
		    	conn.close();
 		    	return true;
 		    }	
			 pstmt.close();
			 conn.close();
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}


	@Override
	public boolean cambiopwd(String username, String npwd) {
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try {
			pstmt= conn.prepareStatement("UPDATE utenti SET password=? WHERE username=?");
			pstmt.setString(1, npwd);
			pstmt.setString(2, username);
			 if (pstmt.executeUpdate()>0){
				pstmt.close();
		    	conn.close();
 		    	return true;
 		    }	
			 pstmt.close();
			 conn.close();
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
