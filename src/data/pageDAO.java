package data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;




public class pageDAO implements pagesDAO {
	
	//deve prendere solo le pagine delle immagini non revisionate se boolean è true
	//altrimenti deve prendere tutto
	
	public OperaComp selectPages(String opera) throws Exception{
		Connection conn= dbConnect.connect();
		Statement stmt;
		ResultSet rs;
		ArrayList<Page> pagine= new ArrayList();
		OperaComp a= new OperaComp(null, null, null, null, 0, pagine);
		int count=0;
		
		
		try {
		    stmt = conn.createStatement();
		    	  if(stmt.execute("SELECT img, acquisitore, revisoreimg, trascrittore, revisoretei, numpag, imgpubb, teipubb, testo  from pagina LEFT JOIN tei ON pagina.id=tei.idpagina WHERE pagina.titoloopera='"+opera+"'ORDER BY numpag ASC"))  {     
			        rs = stmt.getResultSet();
			        
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
		          
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		a.setPagTot(count);
		try {
		    stmt = conn.createStatement();
		   
		    //riempio le info
			    if (stmt.execute("SELECT * FROM opera WHERE titolo='" + opera + "'")){
			        rs = stmt.getResultSet(); 
			        while(rs.next()){
			        	a.setAutore(rs.getString("autore"));
			        	a.setEpoca(rs.getString("epoca"));
			        	a.setNomeOpera(rs.getString("titolo"));
			        	a.setPubblicata(rs.getBoolean("pubblicato"));
		        }
		          
		    }  
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		a.setPagine(pagine);
		return a;
		
		
	}

}
