/**
 * engine
 */
package engine;

import GUI.*;

import java.util.ArrayList;



import javax.swing.*;

import GUI.GestioneProfilo;
import GUI.Ricerca;
import data.*;
/**
 * 
 * @author  loris
 *
 */
public class UserActionManager {
	
	/**
	 * Metodo che permette di accedere all'interno del sistema con delle credenziali da verificare
	 * @param username Stringa che rappresenta l'username dell'utente da dover loggare
	 * @param password Stringa che rappresenta la password dell'utente da dover loggare
	 * @return Finestra di ricerca 
	 */
	protected Boolean LogIn(String username, String password){
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
	/**
	 * Metodo che permette la creazione di utente , in base ad alcuni parametri , e verifica se i campi
	 * sono stati compilati correttamente
	 * @param username Stringa che rappresenta l'username dell'utente da dover creare
	 * @param password Stringa che rappresenta la password dell'utente da dover creare
	 * @param email    Stringa che rappresenta l'email dell'utente da dover creare
	 * @return alert che ci permettono di capire come è andata la creazione di un utente
	 */
	protected boolean CreaUtente(String username, String password, String email){
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
    /**
     * Metodo che ricerca un opera all'interno del database. 
     * @param Ricerca Stringa che rappresenta la ricerca effettuata
     * @param pubblicata booleano che differenzia le opere pubblicate o no
     * @return  rtorna un arraylist di opere
     */
	public ArrayList<OperaGen> searchOpera(String Ricerca, Boolean pubblicata){
			TitoliDAO b= new OperaGenDAO();
			ArrayList<OperaGen> listaopere=b.selectOpera(Ricerca, pubblicata);
			return listaopere;	
		}
	/**
	 * Metodo che permette ad un utente non registrato di accedere al sistema con limitate funzionalità
	 */
	public void AccessoUtenteBase(){
		Ricerca Frame= new Ricerca(null, null);
		Frame.setVisible(true);
		Frame.btnAdmin.setEnabled(false);
		Frame.btnConsulta.setEnabled(false);
		Frame.btnProfilo.setEnabled(false);
	}
	/**
	 * Metodo che permette ad un utente di modificare le sue credenziali
	 * @param username Stringa che rappresenta l'utente che vuole cambiare le sue credenziali
	 */
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
