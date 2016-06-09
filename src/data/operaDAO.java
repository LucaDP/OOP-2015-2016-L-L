package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class operaDAO implements titleDAO<OperaGen>{
	
	public ArrayList<OperaGen> findOpera(String opera){
		ArrayList<OperaGen> listaopere=new ArrayList<OperaGen>();
		Connection conn = null;
		Statement stmt;
		ResultSet rs;
		int a=1;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ppo?useSSL=false","root","");
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		try {
		    stmt = conn.createStatement();
		   
		    
		    if (stmt.execute("SELECT * FROM opera WHERE titolo LIKE '%" + opera + "%'")){
		        rs = stmt.getResultSet(); 
		        while(rs.next()){
		        	System.out.println(rs.getString("autore"));
		        	System.out.println(rs.getString("titolo"));
		        	System.out.println(rs.getString("epoca"));
		        	OperaGen k= new OperaGen(null, null, null);
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
	

}
