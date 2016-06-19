package data;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Classe che permette di effettuare le operazioni sul database relative alle informazioni di un opera
 * @author Luca
 *
 */

public class OperaGenDAO implements TitoliDAO{
	/**
	 * Metodo che ritorna una lista di opere che corrispondono a una determianta ricerca
	 * @param opera opera ricercata
	 * @param pubblicata per determinare se prendere opere pubblicate o meno
	 * @return ArrayList<OperaGen>
	 */
	public ArrayList<OperaGen> selectOpera(String opera, boolean pubblicata){
		ArrayList<OperaGen> listaopere=new ArrayList<OperaGen>();
		Connection conn =  dbConnect.connect();;
		PreparedStatement pstmt;
		ResultSet rs;
		int a= pubblicata ? 1:0;
		
		try {
		    pstmt = conn.prepareStatement("SELECT * FROM opera WHERE titolo LIKE ? AND pubblicato = ? ");
		   pstmt.setString(1, "%"+opera+"%");
		   pstmt.setInt(2, a);
		    
		    if (pstmt.execute()){
		        rs = pstmt.getResultSet(); 
		        while(rs.next()){
		        	
		        	OperaGen k= new OperaGen(null, null, null,null);
		        	k.setAutore(rs.getString("autore")); k.setNomeOpera(rs.getString("titolo")); k.setEpoca(rs.getString("epoca"));
		        	listaopere.add(k);
		        }
		        rs.close();
		        conn.close();
		          
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		return listaopere;
	}

	/**
	 * Metodo per la creazione del database di una entry nella tabella opera riguardante la nuova opera
	 * @param opera nome dell'opera da creare
	 * @param autore autore dell'opera
	 * @param epoca epoca dell'opera
	 * @return boolean  true in caso di successo, false altrimenti
	 * 
	 */
	public boolean creaNuovaOpera(String opera, String autore, String epoca) {
		
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
	
		try {
				pstmt = conn.prepareStatement("SELECT * FROM opera WHERE titolo= ?");
		    	pstmt.setString(1, opera);
		    	if(!(pstmt.execute())){
		    		pstmt.close();
		    		conn.close();
		    		return false;
		    	}
		    	else{
		    		pstmt=(PreparedStatement) conn.prepareStatement("INSERT INTO opera (titolo, autore, epoca, pubblicato) VALUES ( ?, ?, ?, ?)");
		    		pstmt.setString(1, opera);
		    		pstmt.setString(2, autore);
		    		pstmt.setString(3, epoca);
		    		pstmt.setBoolean(4, false);
		    		pstmt.execute();
		    		pstmt.close();
		    		conn.close();
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
	
	
	/**
	 * Metodo che setta a true il campo pubblicato della entry nella tabella opera relativo all'opera selezionata
	 * @param opera nome dell'opera da pubblicare
	 * @return boolan true in caso di successo, false altrimenti
	 */
	public boolean pubblicaOpera(String opera) {
		
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		 
		try {
		    pstmt = conn.prepareStatement("UPDATE opera SET pubblicato = 1  WHERE titolo = ?");
		    pstmt.setString(1, opera);
		    if (pstmt.execute()){
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

	
	
	
	


