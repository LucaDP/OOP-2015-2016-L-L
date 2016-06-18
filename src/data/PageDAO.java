package data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.imageio.ImageIO;






public class PageDAO implements PagesDAO {

	
	public OperaGen selectPages(String opera) throws Exception{
		
		Connection conn= dbConnect.connect();
		PreparedStatement pstmt;
		ResultSet rs;
		
		OperaGen a= null;
		int count=0;	
		try {
			String sql="SELECT img, acquisitore, revisoreimg, trascrittore, revisoretei, numpag, imgpubb, teipubb, testo  from pagina LEFT JOIN tei ON pagina.id=tei.idpagina WHERE pagina.titoloopera=? ORDER BY numpag ASC";
		    pstmt =conn.prepareStatement(sql);
		    pstmt.setString(1, opera);
		    pstmt.execute();
		    rs = pstmt.getResultSet();
		    if(rs.isBeforeFirst())  {      
			        ArrayList<Page> pagine= new ArrayList<Page>();
			        a= new OperaComp(null, null, null, null, 0, pagine);
			        while(rs.next()){
			        	++count;
			        	Trascrizione tras= new Trascrizione(rs.getString("testo"), rs.getString("trascrittore"), rs.getString("revisoretei"), rs.getBoolean("teipubb"));
			        	Blob blob = rs.getBlob("img");
		                int blobLength = (int) blob.length();
		                byte[] blobAsBytes = blob.getBytes(1, blobLength);
		                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
			        	Immagine img =new Immagine(bufferedImage, rs.getString("acquisitore"), rs.getString("revisoreimg"), rs.getBoolean("imgpubb"));
			        	Page k= new Page(count, img, tras);
			        	pagine.add(k);
			        	
			        }
			        ((OperaComp)a).setPagTot(count);
			        ((OperaComp)a).setPagine(pagine);

					if (pstmt.execute("SELECT * FROM opera WHERE titolo='" + opera + "'")){
						rs = pstmt.getResultSet(); 
						while(rs.next()){
							a.setAutore(rs.getString("autore"));
						    a.setEpoca(rs.getString("epoca"));
						    a.setNomeOpera(rs.getString("titolo"));
						    a.setPubblicata(rs.getBoolean("pubblicato"));
						    }    
					}
					rs.close();
					pstmt.close();
					conn.close();
					return a;
		    }
		    else
		    {
		    	  
		    	a=new OperaGen(null, null, null, null);
		    		
		    	 if (pstmt.execute("SELECT * FROM opera WHERE titolo='" + opera + "'")){
					      rs = pstmt.getResultSet(); 
					      while(rs.next()){
					        	a.setAutore(rs.getString("autore"));
					        	a.setEpoca(rs.getString("epoca"));
					        	a.setNomeOpera(rs.getString("titolo"));
					        	a.setPubblicata(rs.getBoolean("pubblicato"));
					        	
					        }
		    		  }
		    		  rs.close();
		    		  pstmt.close();
		    		  conn.close();
		    		  return a;
		    }
		}
		catch (SQLException ex){
		    
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return a;
	}

}
