package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;




/**
 *
 * @author loris
 *
 */
public class UtenteDAO implements UtenzaDAO {
	
	/**
	 * Metodo che verifica all'interno del database l'esistenza di un username 
	 * @param username   Stringa che rappresenta l'username da convalidare
	 * @return           booleano che ci fa capire se l'username esiste o meno
	 */
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

	/**
	 * Metodo che registra un utente con i suoi parametri nel database
	 * @param username  Stringa che rappresenta l'username da dover inserire nel database
	 * @param password  Stringa che rappresenta la password da dover inserire nel database
	 * @param email     Stringa che rappresenta l'email da dover inserire nel database
	 * @return          booleano che ci fa capire se la registrazione è andata a buon fine
	 */
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
			
	

	/**
	 * Metodo che tramite l'username ci permette di conoscere i dati di un utente dal suo username
	 * @param username    Stringa che rappresenta l'username da esaminare
	 * @return            Oggetto che contiene i dati di un utente
	 */
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
	/**
	 * Metodo che creare un utente collaboratore all'interno del database
	 * @param nomeperatore Stringa che rappresenta il nome dell'operatore da dover creare
	 * @param password     Stringa che rappresenta il password dell'operatore da dover creare
	 * @param email        Stringa che rappresenta il email dell'operatore da dover creare
	 * @param ruolo        Stringa che rappresenta il ruolo dell'operatore da dover creare
	 * @return             booleano che ci permette di capire se l'operatore è statto creato o meno
	 */
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
	
	/**
	 * Metodo che permette di modificare il permesso ad un utente
	 * @param username  Stringa che rappresenta l'utente a cui modificare il permesso
	 * @param permesso  Stringa che rappresenta il permesso da modificare ad un utente
	 * @return          booleano che ci fa capire se l'operazione è andata a buon fine o meno
	 */
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

    /**
     * Metodo per il cambiamento di un email verso un utente
     * @param username Stringa che rappresenta l'utente a cui cambiare l'email
     * @param email    Stringa che rappresenta l'email da modificare
     * @return         booleano che ci fa capire se l'operazione è andata a buon fine o meno
     */
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

    /**
     * Metodo per cambiare la password ad un dato utente
     * @param username  Stringa che rappresenta l'utente a cui cambiare la password
     * @param npwd      Stringa che rappresenta la nuova password per l'utente
     * @return          booleano che ci fa capire se l'operazione è andata a buon fine o meno
     */
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
