package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImmagineDAO implements ScanDAO {
	public Boolean pubbImg(int numeropagina, String nomeopera, String username ){
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
	
	
	
	public Boolean uploadImmagine(String nomeopera, InputStream inputStream, int numpag, String username ) {
		Connection conn= dbConnect.connect();
		PreparedStatement pstmt;
		PreparedStatement pstmt1;
		PreparedStatement pstmt2;
		ResultSet rs;
		int id=0;
		
		
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
				 String teiinizio= "<TEI xmlns=\"http://www.tei-c.org/ns/1.0\">\r<teiHeader>\r</teiHeader>\r<text>\r\r";
				 String teititolocapitolo= "<body>\r<p><!--INSERIRE TITOLO-->\r\rTITOLO\r\r<!--FINE TITOLO--></p>\r<p><!--INSERIRE CAPITOLO-->\r\rCAPITOLO\r\r<!--FINE CAPITOLO--></p>\r\r";
				 String aperturaparagrafo="<p><!--PRIMO PARAGRAFO-->\r\r";
				 String paragrafo="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vitae egestas tortor.\r\r";
				 String chiusuraparagrafo="<!--FINE PRIMO PARAGRAFO--></p>\r\r";
				 String inizionote="<p>Note:<hi><!--NOTE-->\r\r";
				 String note="Vestibulum vel est sit amet nisl facilisis elementum. Donec scelerisque quis purus id egestas.\r\r";
				 String finenote="<!--FINE NOTE--></hi></p>\r\r";
				 String finetei="</body>\r</text>\r</TEI>";
				 String TEIdefault=teiinizio.concat(teititolocapitolo).concat(aperturaparagrafo).concat(paragrafo).concat(chiusuraparagrafo).concat(inizionote).concat(note).concat(finenote).concat(finetei);
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
