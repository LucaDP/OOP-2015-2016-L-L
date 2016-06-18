package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrascrizioneDAO implements TeiDAO {
	
	
	public Boolean pubbTei(int numeropagina, String nomeopera, String revisore ){
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
	
public Boolean uploadTei(int numeropagina, String nomeopera, String testo, String trascrittore){
		
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
