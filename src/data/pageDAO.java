package data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.jdbc.PreparedStatement;




public class pageDAO implements pagesDAO {
	
	//deve prendere solo le pagine delle immagini non revisionate se boolean è true
	//altrimenti deve prendere tutto
	
	public OperaGen selectPages(String opera) throws Exception{
		System.out.println("INIZIOE");
		Connection conn= dbConnect.connect();
		PreparedStatement stmt;
		ResultSet rs;
		
		OperaGen a= null;
		int count=0;
		
		/*OperaGen a;
		 * se la query trova le pagine allora a= new OperaComp() e vado a riempire
		 * altrimenti a= new OperaGen();
		 * */
		
		
		try {
			String sql="SELECT img, acquisitore, revisoreimg, trascrittore, revisoretei, numpag, imgpubb, teipubb, testo  from pagina LEFT JOIN tei ON pagina.id=tei.idpagina WHERE pagina.titoloopera=? ORDER BY numpag ASC";
		    stmt = (PreparedStatement) conn.prepareStatement(sql);
		    stmt.setString(1, opera);
		    stmt.execute();
		    rs = stmt.getResultSet();
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
			        	System.out.println("arrivato1");
			        }
			        ((OperaComp)a).setPagTot(count);
			        ((OperaComp)a).setPagine(pagine);
			        
					
					   
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
					System.out.println("ARRIVO QUI");
					return a;
		    }
		    else{
		    	  
		    		  a=new OperaGen(null, null, null, null);
		    		  System.out.println("arrivato2");
		    		  if (stmt.execute("SELECT * FROM opera WHERE titolo='" + opera + "'")){
					        rs = stmt.getResultSet(); 
					        while(rs.next()){
					        	a.setAutore(rs.getString("autore"));
					        	a.setEpoca(rs.getString("epoca"));
					        	a.setNomeOpera(rs.getString("titolo"));
					        	a.setPubblicata(rs.getBoolean("pubblicato"));
					        	System.out.println("arrivato3");
					        }
		    		  }
		    		  return a;
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

}
