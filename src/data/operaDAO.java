package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
/**********************************************************************************************/
public static Boolean checkTei(String testo) throws Exception{
	Connection conn = dbConnect.connect();
	Statement stmt;
	 boolean a=false;
	 ResultSet rs;
	 System.out.println(testo);
	 try {
	    stmt = conn.createStatement();
	    if (stmt.execute("SELECT * FROM tei WHERE testo='" + testo + "'and teipubb='"+1+ "'")){
	    	
	    	rs = stmt.getResultSet(); 
	    	
	    	a= rs.isBeforeFirst(); 
	    	if(a==true){
	    		return true;
	    	}else{
	    		return false;
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






	
	
	
	


