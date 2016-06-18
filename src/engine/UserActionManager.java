/**
 * engine
 */
package engine;

import UIPackage.*;

import java.util.ArrayList;



import javax.swing.*;
import data.*;

public class UserActionManager {
	
	
	protected boolean LogIn(String username, String password) throws Exception{
		Utente user= new Utente(null, null, null, null);
		UtenzaDAO b= new UtenteDAO();
		user= b.access(username);

		if(password.equals(user.getPassword())){
			Ricerca Frame=new Ricerca(user.getUsername(), user.getPermessi());
			
			switch(user.getPermessi()){
			case "ad":	 
						Frame.setVisible(true);
						Frame.hellouser.setText("Salve amministratore "+ ""+user.getUsername());
						
						break;
			case "ri":
			case "ac":	Frame.setVisible(true);
						Frame.btnAdmin.setVisible(false);
						Frame.hellouser.setText("Salve "+ ""+user.getUsername());
						
						break;
			
			default: 
						Frame.setVisible(true);
						Frame.btnAdmin.setVisible(false);
						Frame.operenonpubb.setVisible(false);
						Frame.hellouser.setText("Salve "+ ""+user.getUsername());
						
						break;
			}
			return true;
				
			}	
		else{
			
			return false;
		
		}
		
	}
	
	protected boolean CreaUtente(String username, String password, String email) throws Exception{
		UtenzaDAO b= new UtenteDAO();
		Boolean reg = b.check(username);
		if(!reg){
			if(b.signin(username, password, email)){
				JOptionPane.showMessageDialog (null, "Operazione non effettuata, riprovare", "Title", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else{
				JOptionPane.showMessageDialog (null, "Registrazione effettuata", "Title", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		}
		else{
			JOptionPane.showMessageDialog (null, "Username già esistente, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}

	public ArrayList<OperaGen> searchOpera(String Ricerca, Boolean pubblicata) throws Exception{
			TitoliDAO b= new OperaGenDAO();
			ArrayList<OperaGen> listaopere=b.selectOpera(Ricerca, pubblicata);
			return listaopere;	
		}
	
	public void AccessoUtenteBase(){
		Ricerca Frame= new Ricerca(null, null);
		Frame.setVisible(true);
		Frame.btnAdmin.setEnabled(false);
		Frame.btnConsulta.setEnabled(false);
		Frame.btnProfilo.setEnabled(false);
	}
	
	public void apriGestioneProfilo(String username){
		GestioneProfilo Frame= new GestioneProfilo(username);
		Frame.setVisible(true);
	}
	
	public void cambioEmail(String username, String email){
		UtenzaDAO dao= new UtenteDAO();
		if(dao.cambioemail(username, email)){
			JOptionPane.showMessageDialog (null, "Email cambiata", "Title", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog (null, "Cambio email non riuscito, riprovare", "Title", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void cambioPwd(String username, String vpwd, String npwd){
		UtenzaDAO dao= new UtenteDAO();
		if(dao.access(username).getPassword().equals(vpwd)){
			if(dao.cambiopwd(username, npwd)){
				JOptionPane.showMessageDialog (null, "Email cambiata", "Title", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog (null, "Cambio password non riuscito, riprovare", "Title", JOptionPane.INFORMATION_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog (null, "Vecchia password errata, riprovare", "Title", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
			

			
			
		
	
	

}
