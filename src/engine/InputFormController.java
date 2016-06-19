package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import Gui.BackOffice;

import Gui.Login;
import Gui.Ricerca;
import data.OperaGen;
/**
 * Classe che gestisce gli input da form da parte dell'utenza e ne determina la correttezza
 * @author  loris
 *
 */
public class InputFormController {
	/**
	 * Metodo che verifica se le form sono state compilate correttamente , nel caso sono state compilate correttamente richiama la classe UserActionManager
	 * @param finestra form che contiene i campi per la login
	 * @param username valore che rappresenta la username per la login 
	 * @param password valore che rappresenta la password per la login
	 */
	public void LogInInput(Login finestra, String username, String password){
		
		
		if(username.length()==0 || password.length()==0){
			JOptionPane.showMessageDialog (null, "Riempire tutti i campi per effettuare l'accesso");
		}
		else
		{
			UserActionManager controller=new UserActionManager();
			if(controller.LogIn(username, password)){
				finestra.dispose();	
			}
			else{
				JOptionPane.showMessageDialog (null, "Dati non corretti, riprovare", "Title", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	/**
	 * Metodo che verifica se la form per la registrazione è stata compilata correttamente , nel caso sono state compilate correttamente richiama UserActionManager
	 * @param finestra  l'interfaccia grafica della pagina addetta alla registrazione
	 * @param username  Stringa che l'utente inserisce nella form di registrazione per la sua username
	 * @param password  Stringa che l'utente inserisce nella form di registrazione che rappresenta la password
	 * @param email     Stringa che l'utente inserisce nella form di registrazione che rappresenta l'email
	 */
	public void SignInInput(Login finestra, String username, String password, String email){
		
		
		if(username.length()==0 || password.length()==0 || email.length()==0){
			JOptionPane.showMessageDialog (null, "Campi vuoti");
		}
		else{
			if(InputFormController.emailValidator(email)){
				JOptionPane.showMessageDialog (null, "Formato email non corretto");
			}
			else{
				UserActionManager controller= new UserActionManager();
				if(controller.CreaUtente(username, password, email)){
					
					finestra.textField.setText(username);
					finestra.textField_1.setText(null);
					finestra.textField_2.setText(null);
					finestra.passwordField_1.setText(null);
				}
				
			}
		
		}
		
	}
	
	/**
	 * Metodo che verifica se la barra di ricerca è stata riempita correttamente , nel caso di un corretto utilizzo utilizzeremo la UserActionManager
	 * @param finestra Rappresenta la finestra dell'interfaccia ricerca
	 * @param ricerca Rappresenta la stringa che l'utente inserisce nella barra di ricerca
	 * @param pubblicata Rappresenta la checkbox per differenziare se un opera è pubblicata o meno
	 */
	public void RicercaUtentiInputOutput(Ricerca finestra, String ricerca, Boolean pubblicata){
		if(finestra.comboBox.getItemCount()!=0){
			finestra.comboBox.removeAllItems();
		}
		if("".equals(ricerca)){
			JOptionPane.showMessageDialog (null, "inserisci qualcosa prima di fare la ricerca");	
		}
		else{
			UserActionManager controller= new UserActionManager();
			ArrayList<OperaGen> listaopere= new ArrayList<OperaGen>();
			listaopere=controller.searchOpera(ricerca, pubblicata);
			if(listaopere.size()==0){
				JOptionPane.showMessageDialog (null, "la ricerca non ha prodotto alcun risultato");
			}else{
				Iterator<OperaGen> iteratore=listaopere.iterator();
				while(iteratore.hasNext()){
					OperaGen opera= iteratore.next();
					finestra.comboBox.addItem(opera.getNomeOpera());	
				}
				
			}
		}
	}
	
	/**
	 * Metodo che verifica che i campi per la ricerca sono stati compilati correttamente , se ci troviamo nel caso corretto andremo a creare un oggetto operagen
	 * @param finestra interfaccia grafica del backoffice
	 * @param ricerca rappresenta la stringa che viene inserito nella barra di ricerca
	 */
	public void RicercaAdminInputOutput(BackOffice finestra, String ricerca){
		if(finestra.elencooperedapubb.getItemCount()!=0){
			finestra.elencooperedapubb.removeAllItems();
		}
		if("".equals(ricerca)){
			JOptionPane.showMessageDialog (null, "inserisci qualcosa prima di fare la ricerca");	
		}
		else{
			UserActionManager controller= new UserActionManager();
			ArrayList<OperaGen> listaopere= new ArrayList<OperaGen>();
			listaopere=controller.searchOpera(ricerca, false);
			if(listaopere.size()==0){
				JOptionPane.showMessageDialog (null, "la ricerca non ha prodotto alcun risultato");
			}else{
				Iterator<OperaGen> iteratore=listaopere.iterator();
				while(iteratore.hasNext()){
					OperaGen opera= iteratore.next();
					finestra.elencooperedapubb.addItem(opera.getNomeOpera());	
				}
				
			}
		}
	}
	
	/**
	 * Metod che controlla se l'email è stata digitata correttamente 
	 * @param email Stringa che l'utente inserisce che rappresenta l'email
	 * @return
	 */
	private static Boolean emailValidator(String email){
		String emailinserita = email;
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(emailinserita);
		if (matcher.find()) {
			return false;
		} else {
		    return true;
		}
	}
	
	/**
	 * Metodo che verifica che i campi inseriti nella form per la creazione di un operatore che si trova nel backoffice ;
	 * @param nomeoperatore Stringa che rappresenta il nome dell'operatore da creare
	 * @param password      Stringa che rappresenta la password da creare
	 * @param email         Stringa che rappresenta l'email da creare
	 * @param ruolo         Stringa che rappresenta il ruolo creare
	 */
	public void CreaOperatoreInput(String nomeoperatore, String password, String email, String ruolo){
		if("".equals(email)){
			if("".equals(nomeoperatore) || "".equals(password)|| "".equals(ruolo)){
				JOptionPane.showMessageDialog (null, "I campi Username Operatore, password e ruolo sono obbligatori, inseriscili");
			}
			else{
				UserActionManager controller= new  AdminActionManager();
				controller.CreaUtente(nomeoperatore, password, ruolo);	
			}
			
		}
		else{
			if(!("".equals(nomeoperatore) || "".equals(password)|| "".equals(ruolo) || "".equals(email))){
				if(!(InputFormController.emailValidator(email))){
					AdminActionManager controller= new AdminActionManager();
					controller.CreaUtente(nomeoperatore, password, email, ruolo);		
				}else
				{
					JOptionPane.showMessageDialog (null, "Formato Email non corretto");
					
				}
				
			}else{
				JOptionPane.showMessageDialog (null, "I campi Username Operatore, password e ruolo sono obbligatori, inseriscili");
			}
			 
	
		}
		
		
	}
	/**
	 * Metodo che verifica se i campi per la form per la promozione di un utenza sono stati inseriti correttamente;
	 * se sono stati inseriti correttamente si va a richiamare la classe AdminActionManager 
	 * @param username Stringa che rappresenta username da dover promovuere
	 * @param ruolo    Stringa che rappresenta il ruolo da dover inserire 
	 */
	public void PromozioneOperatoreInput(String username, String ruolo){
		
		if("".equals(username)){
			JOptionPane.showMessageDialog (null, "Inserisci il nome dell utente da promuovere/retrocedere");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.PromozioneUtente(username, ruolo);
		}
		
		
	}
	/**
	 * Metodo che verifica  se il valore dell'opera è stata inserita correttamente ; se si
	 * si va a richiamare la classe AdminAcionManager
	 * @param opera Stringa che rappresenta l'opera da dover promovuere
	 */
	public void PubbOperaInput(String opera){
		if("".equals(opera)){
			JOptionPane.showMessageDialog (null, "Inserisci il nome dell utente da promuovere/retrocedere");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.pubblicaOpera(opera);
		}
		
	}
	/**
	 * Metodo che verifica se i valori dela form per la creazione di un opera è stata inserita correttamente;
	 * se si si va a richiamare la classe UserActionManager
	 * @param opera Stringa che rappresenta l'opera da dover creare
	 * @param autore Stringa che rappresenta l'autore da dover inserire
	 * @param epoca  Stringa che rappresenta l'epoca da dover inserire
	 */
	public void CreaOperaInput(String opera, String autore, String epoca){
		if("".equals(opera) || "".equals(autore) || "".equals(epoca)){
			JOptionPane.showMessageDialog (null, "Riempire tutti i campi");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.creaNuovaOpera(opera, autore, epoca);
		}
	}
	/**
	 * Metodo che verifica se l'email è stata inserita correttamente;
	 * se si si va a richiamare la classe UserActionManager
	 * @param username Stringa che rappresenta l'username 
	 * @param email    Stringa che rappresenta l'email da dover cambiare
	 */
	public void cambiaEmailInput(String username, String email){
		if(InputFormController.emailValidator(email)){
			JOptionPane.showMessageDialog (null, "Formato email non corretto");
		}
		else
		{
			UserActionManager controller= new UserActionManager();
			controller.cambioEmail(username, email);
		}
		
		
	}
	/**
	 * Metodo che verifica se i campi sono stati inseriti correttamente ;
	 * se si si va a richiamare UserActionManager
	 * @param username   Stringa  che rappresenta l'username
	 * @param vecchiapwd Stringa  che rappresenta la vecchia password
	 * @param nuovapwd   Stringa  che rappresenta la nuova password 
	 */
	public void cambiaPasswordInput(String username, String vecchiapwd, String nuovapwd){
		if("".equals(vecchiapwd) || "".equals(nuovapwd)){
			JOptionPane.showMessageDialog (null, "Riempire i campi");
		}
		else
		{
			UserActionManager controller= new UserActionManager();
			controller.cambioPwd(username, vecchiapwd, nuovapwd);
		}
	}
	
	
	
}
