package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe per le azioni sul database riguardo le immagini dell'opera
 * @author Luca
 *
 */
public class ImmagineDAO implements ScanDAO {
	
	
	/**
	 * Metodo per settare su true l'attributo imgpubb della tabella pagina relativo all'immagine che si vuole pubblicare 
	 * @param numeropagina numero della pagina
	 * @param nomeopera nome dell'opera
	 * @param username username del revisore
	 * @return boolean true in caso di successo altrimenti false
	 */
	public boolean pubbImg(int numeropagina, String nomeopera, String username ){
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		
		
		try{
		 pstmt = conn.prepareStatement("UPDATE pagina " + " SET imgpubb='1',revisoreimg=? WHERE numpag=? AND titoloopera=?");
		 pstmt.setString(1, username);
		 pstmt.setInt(2, numeropagina);
		 pstmt.setString(3, nomeopera);
		 if (!(pstmt.execute())) {
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
	 * Metodo per caricare un Immagine nel database. Questi crea una entry nella tabella pagina relativa alla nuova immagine solo 
	 * se la entry non esiste. Nel caso la entry esista, questo metodo esegue solo l'update. Oltre a caricare l'immagine questo metodo 
	 * crea una entry (sempre se non è stata già creata) nella tabella tei, relativa al tei collegato all'immagine, inserendo anche un testo di default
	 * @param nomeopera nome dell'opera a cui appartiene l'immagine
	 * @param inputStream immagine
	 * @param numpag numero di pagina dell'immagine
	 * @param username username dell'acquisiotore
	 * @return boolean true se é stata eseguita la insert o l'update, altrimenti false
	 */
	public boolean uploadImmagine(String nomeopera, InputStream inputStream, int numpag, String username ) {
		Connection conn= dbConnect.connect();
		PreparedStatement pstmt;
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		ResultSet rs;
		int id=0;
		final String teiinizio= "<TEI xmlns=\"http://www.tei-c.org/ns/1.0\">\r<teiHeader>\r</teiHeader>\r<text>\r\r";
		final String teititolocapitolo= "<body>\r<p><!--INSERIRE TITOLO-->\r\rTITOLO\r\r<!--FINE TITOLO--></p>\r<p><!--INSERIRE CAPITOLO-->\r\rCAPITOLO\r\r<!--FINE CAPITOLO--></p>\r\r";
		final String aperturaparagrafo="<p><!--PRIMO PARAGRAFO-->\r\r";
		final String paragrafo="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vitae egestas tortor.\r\r";
		final String chiusuraparagrafo="<!--FINE PRIMO PARAGRAFO--></p>\r\r";
		final String inizionote="<p>Note:<hi><!--NOTE-->\r\r";
		final String note="Vestibulum vel est sit amet nisl facilisis elementum. Donec scelerisque quis purus id egestas.\r\r";
		final String finenote="<!--FINE NOTE--></hi></p>\r\r";
		final String finetei="</body>\r</text>\r</TEI>";
		final String TEIdefault=teiinizio.concat(teititolocapitolo).concat(aperturaparagrafo).concat(paragrafo).concat(chiusuraparagrafo).concat(inizionote).concat(note).concat(finenote).concat(finetei);
		
		
		try{
			pstmt = (PreparedStatement)conn.prepareStatement("SELECT id FROM pagina WHERE titoloopera=? AND numpag=?");
			pstmt.setString(1,nomeopera);
			pstmt.setInt(2, numpag);
			pstmt.execute();
			rs=pstmt.getResultSet();
			
			
			if(!(rs.isBeforeFirst())){
				
				 pstmt1 = (PreparedStatement)conn.prepareStatement("INSERT INTO pagina (titoloopera, numpag, img, acquisitore) VALUES( ?, ?, ?, ?) ");
				 pstmt1.setString(1, nomeopera);
				 pstmt1.setInt(2, numpag);
				 pstmt1.setBlob(3, inputStream);
				 pstmt1.setString(4, username);
				 pstmt1.execute();
				 pstmt1=(PreparedStatement)conn.prepareStatement("SELECT id FROM pagina WHERE titoloopera=? AND numpag=?");
				 pstmt1.setString(1,nomeopera);
				 pstmt1.setInt(2, numpag);
				 pstmt1.execute();
				 rs=pstmt1.getResultSet();
				 while(rs.next()){
					 id=rs.getInt("id");
				 }
				 pstmt2 = (PreparedStatement)conn.prepareStatement("INSERT INTO tei (idpagina, testo) VALUES (?,?)");
				 
				 pstmt2.setInt(1, id);
				 pstmt2.setString(2, TEIdefault);
				 if(pstmt2.execute()){
					 rs.close();
					conn.close();
					 return false;
				 }
				 else{
					 rs.close();
					 conn.close();
					 return true;
				 }
			}
			else{
				while(rs.next()){
					id=rs.getInt("id");
				
				}
				//System.out.println("UPDATE IMG");
				pstmt = (PreparedStatement)conn.prepareStatement("UPDATE pagina SET img= ? WHERE id=?");
				pstmt.setBlob(1, inputStream);
				pstmt.setInt(2, id);
				
				if(pstmt.execute()){
					rs.close();
					conn.close();
					return false;
				}else{
					rs.close();
					conn.close();
					return true;
				}
				 
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
