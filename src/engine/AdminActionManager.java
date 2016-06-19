package engine;

import javax.swing.JOptionPane;

import GUI.BackOffice;
import data.OperaGenDAO;
import data.TitoliDAO;

import data.UtenteDAO;
import data.UtenzaDAO;


/**
 * 
 * @author loris
 *
 */
public class AdminActionManager extends UserActionManager {
	
	/**
	 * Metodo che crea un utente all'interno del database con un username, una password ed un permesso
	 */
	@Override
	protected boolean CreaUtente(String username, String password, String permesso){
		UtenzaDAO b= new UtenteDAO();
		Boolean reg = b.check(username);
		if(!reg){
			if(b.creaOperatore(username, password, "", permesso)){
				JOptionPane.showMessageDialog (null, "Operazione non effettuata, riprovare", "Title", JOptionPane.ERROR_MESSAGE);
				
			}
			else{
				JOptionPane.showMessageDialog (null, "Registrazione effettuata", "Title", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		}
		else{
			JOptionPane.showMessageDialog (null, "Username già esistente, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
			
		}
		return false;
	}
	
	/**
	 * Metodo che crea un utente all'interno di un database
	 * @param username  Strinha che rappresenta l'username da dover inserire nel database
	 * @param password  Stringa che rappresenta la password da dover inserire nel database
	 * @param email     Stringa che rappresenta l'email da dover inserire nel database
	 * @param ruolo     Stringa che rappresenta il ruolo da dover inserire nel database , da dare ad un utente
	 * @return ritorna un booleano che ci permette di capire se un utente è stato creato o meno
	 */
	protected Boolean CreaUtente(String username, String password, String email, String ruolo){
		UtenzaDAO b= new UtenteDAO();
		Boolean reg = b.check(username);
		if(!reg){
			if(b.creaOperatore(username, password, email, ruolo)){
				JOptionPane.showMessageDialog (null, "Operazione non effettuata, riprovare", "Title", JOptionPane.ERROR_MESSAGE);
				
			}
			else{
				JOptionPane.showMessageDialog (null, "Registrazione effettuata", "Title", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		}
		else{
			JOptionPane.showMessageDialog (null, "Username già esistente, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
			
		}
		return false;
		
	}
	/**
	 * Metodo che permette di promuovere un utente all'interno del sistema
	 * @param username Stringa che rappresenta l'username dell'utente da dover promovuere
	 * @param permesso Stringa che rappresenta il permesso da dover dare ad un utente 
	 * @return boolean che ci fa capire se l'utente è stato promosso o meno attraverso un alert
	 */
	protected Boolean PromozioneUtente(String username, String permesso){
		UtenzaDAO b = new UtenteDAO();
		Boolean check = b.check(username);
		if(!check){
			JOptionPane.showMessageDialog (null, "Non esiste un utente con questo username", "Title", JOptionPane.ERROR_MESSAGE);
		}
		else{
			if(b.promuoviUtente(username, permesso)){
				JOptionPane.showMessageDialog (null, "Utente promosso/retrocesso", "Title", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
		}
		
		return false;
		
	}
	/**
	 * Metodo che crea una nuova opera nel database 
	 * @param nomeopera Stringa che rappresenta il nome dell'opera da dover inserire nel database
	 * @param autore    Stringa che rappresenta l'autore dell'opera da dover inserire nel database
	 * @param epoca     Stringa che rappresenta l'epoca dell'opera da dover inserire nel database
	 */
	public void creaNuovaOpera(String nomeopera, String autore, String epoca){
		TitoliDAO b= new OperaGenDAO();
	     if(b.creaNuovaOpera(nomeopera,autore,epoca)){
	    	 JOptionPane.showMessageDialog (null, "Opera inserita nel database");
	     }else{
	    	 JOptionPane.showMessageDialog (null, "Creazione opera non riuscita");
	     }	
	}
	/**
	 * Metodo che permette di pubblicare un opera all'interno del database
	 * @param nomeopera  Stringa che rappresenta il nome dell'opera da dover pubblicare
	 */
	public void pubblicaOpera(String nomeopera){
		TitoliDAO b= new OperaGenDAO();
	     if(!(b.pubblicaOpera(nomeopera))){
	    	 JOptionPane.showMessageDialog (null, "Opera pubblicata");
	     }else{
	    	 JOptionPane.showMessageDialog (null, "Opera non pubblicata, riprovare");
	     }
	}
	/**
	 * Metodo che ci mostra il pannello del backoffice
	 */
	public void backOffice(){
		BackOffice frame = new BackOffice();
		frame.setVisible(true);
	}

}
