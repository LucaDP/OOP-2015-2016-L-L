package data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class operaDAO implements titleDAO<OperaGen>{
	
	public ArrayList<OperaGen> selectOpera(String opera, Boolean pubblicata) throws Exception{
		ArrayList<OperaGen> listaopere=new ArrayList<OperaGen>();
		Connection conn =  dbConnect.connect();;
		Statement stmt;
		ResultSet rs;
		int a= pubblicata ? 1:0;
		
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT * FROM opera WHERE titolo LIKE '%" + opera + "%'"+"AND pubblicato ="+a )){
		        rs = stmt.getResultSet(); 
		        while(rs.next()){
		        	
		        	OperaGen k= new OperaGen(null, null, null,null);
		        	k.setAutore(rs.getString("autore")); k.setNomeOpera(rs.getString("titolo")); k.setEpoca(rs.getString("epoca"));
		        	listaopere.add(k);
		        }
		          
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
	
	
	
	public Boolean creareopera(String nomeopera, String autore, String epoca) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		 int a=0;
		try {
		    stmt = conn.createStatement();
		    if (stmt.execute("INSERT INTO opera (titolo, autore, epoca, pubblicato) VALUES ('"+nomeopera+"','"+autore+"','"+epoca+"','"+a+"')")){
		    	/***********ATTENZIONE****************/
		    	JOptionPane.showMessageDialog (null, "opera inserita");
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
/**********************************************************************************************/
	public Boolean pubbopera(String nomeopera) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		 int a=0;
		try {
		    stmt = conn.createStatement();
		    if (stmt.execute("UPDATE opera"
    				+ " SET pubblicato = '"+1+ 
    				 "' WHERE titolo = '"+nomeopera+"'")){
		    	
		    	/************************ATTTTTTENZIONE*********/
		    	JOptionPane.showMessageDialog (null, "opera inserita");
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
	
	public Boolean pubbTei(int numeropagina, String nomeopera ) throws Exception{
		Connection conn = dbConnect.connect();
		Statement stmt;
		try{
		 stmt = conn.createStatement();
		    if (!(stmt.execute("UPDATE `tei` SET teipubb='1' where idpagina= (SELECT id FROM pagina Where numpag='"+numeropagina+"' AND titoloopera='"+nomeopera+"')"))){
		    	
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
	
	public Boolean uploadTei(int numeropagina, String nomeopera, String testo) throws Exception{
		
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		try{
			 
		 pstmt = (PreparedStatement)conn.prepareStatement("UPDATE tei SET testo= ?  where idpagina= (SELECT id FROM pagina Where numpag= ? AND titoloopera= ?)");
		 pstmt.setString(1, testo);
		 pstmt.setInt(2, numeropagina);
		 pstmt.setString(3, nomeopera);
		    if (!(pstmt.execute())){
		    	
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
	public Boolean uploadScan(String nomeopera, InputStream inputStream, int numpag ) throws Exception{
		Connection conn= dbConnect.connect();
		PreparedStatement pstmt;
		
		
		try{
			 
			 pstmt = (PreparedStatement)conn.prepareStatement("INSERT INTO pagina (titoloopera, numpag, img, acquisitore, revisoreimg, imgpubb) VALUES(?, ?, ?, ?, ?, ?) ");
			 pstmt.setString(1,nomeopera);
			 pstmt.setInt(2, numpag);
			 pstmt.setBlob(3, inputStream);
			 pstmt.setString(4, "Giorgio");
			 pstmt.setString(5, "Anacleto");
			 pstmt.setBoolean(6, false);
			 
			    if (!(pstmt.execute())){
			    	
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
	
	
}

	
	
	
	


