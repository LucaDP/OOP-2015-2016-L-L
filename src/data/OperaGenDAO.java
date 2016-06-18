package data;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;



public class OperaGenDAO implements TitoliDAO{
	
	public ArrayList<OperaGen> selectOpera(String opera, Boolean pubblicata) throws Exception{
		ArrayList<OperaGen> listaopere=new ArrayList<OperaGen>();
		Connection conn =  dbConnect.connect();;
		PreparedStatement pstmt;
		ResultSet rs;
		int a= pubblicata ? 1:0;
		
		try {
		    pstmt = conn.prepareStatement("SELECT * FROM opera WHERE titolo LIKE ? AND pubblicato = ? ");
		   pstmt.setString(1, "%"+opera+"%");
		   pstmt.setInt(2, a);
		    
		    if (pstmt.execute()){
		        rs = pstmt.getResultSet(); 
		        while(rs.next()){
		        	
		        	OperaGen k= new OperaGen(null, null, null,null);
		        	k.setAutore(rs.getString("autore")); k.setNomeOpera(rs.getString("titolo")); k.setEpoca(rs.getString("epoca"));
		        	listaopere.add(k);
		        }
		        rs.close();
		        conn.close();
		          
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

	@Override
	public Boolean creaNuovaOpera(String opera, String autore, String epoca) {
		
		Connection conn = dbConnect.connect();
		java.sql.PreparedStatement pstmt;
	
		try {
				pstmt = conn.prepareStatement("SELECT * FROM opera WHERE titolo= ?");
		    	pstmt.setString(1, opera);
		    	if(!(pstmt.execute())){
		    		conn.close();
		    		return false;
		    	}
		    	else{
		    		pstmt=(PreparedStatement) conn.prepareStatement("INSERT INTO opera (titolo, autore, epoca, pubblicato) VALUES ( ?, ?, ?, ?)");
		    		pstmt.setString(1, opera);
		    		pstmt.setString(2, autore);
		    		pstmt.setString(3, epoca);
		    		pstmt.setBoolean(4, false);
		    		pstmt.execute();
		    		conn.close();
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

	@Override
	public Boolean pubblicaOpera(String opera) {
		
		Connection conn = dbConnect.connect();
		PreparedStatement pstmt;
		 
		try {
		    pstmt = conn.prepareStatement("UPDATE opera SET pubblicato = 1  WHERE titolo = ?");
		    pstmt.setString(1, opera);
		    if (pstmt.execute()){
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

	
	
	
	


