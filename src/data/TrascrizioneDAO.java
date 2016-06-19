package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Classe che permette di effettuare le operazioni sul database per quanto riguarda le Trascrizioni
 * @author loris
 *
 */
public class TrascrizioneDAO implements TeiDAO {
	
	/**
	 * Metodo che pubblica un tei all'interno del sistema 
	 * @param numeropagina intero che rappresenta il numero della pagina
	 * @param nomeopera    Stringa che rappresenta il nome dell'opera a cui pubblicare il tei
	 * @param revisore     Stringa che rappresenta il revisore del tei
	 * @return booleano che ci fa comprendere se se il tei è stato inserito
	 */
	public boolean pubbTei(int numeropagina, String nomeopera, String revisore ){
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try{
		 pstmt = conn.prepareStatement("UPDATE `tei` SET teipubb='1', revisoretei=? WHERE idpagina= (SELECT id FROM pagina Where numpag= ? AND titoloopera= ?)");
		 pstmt.setString(3, nomeopera);
		 pstmt.setString(1, revisore);
		 pstmt.setInt(2, numeropagina);
		 
		 if (!(pstmt.execute())){
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
     * Metodo che inserisce il tei nel database come non pubblicato
     * @param numeropagina  intero che rappresenta il numero della pagina del tei
     * @param nomeopera     Stringa che rappresenta il nome dell'opera del tei da dover inserire
     * @param testo         Stringa che rappresenta il testo del tei di quella determinata pagina
     * @param trascrittore  Stringa che rappresenta il trascrittore 
     * @return              booleano che ci fa comprendere se l'operazione è andata a buon fine
     */
	public boolean uploadTei(int numeropagina, String nomeopera, String testo, String trascrittore){
		
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try{
			 
		 pstmt = conn.prepareStatement("UPDATE tei SET testo= ? , trascrittore=?  where idpagina= (SELECT id FROM pagina Where numpag= ? AND titoloopera= ?)");
		 pstmt.setString(1, testo);
		 pstmt.setString(2, trascrittore);
		 pstmt.setInt(3, numeropagina);
		 pstmt.setString(4, nomeopera);
		    if (!(pstmt.execute())){
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
