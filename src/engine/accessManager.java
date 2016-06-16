/**
 * engine
 */
package engine;

import UIPackage.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import data.*;
/**
 * 
 * @author Luca
 *
 */
public class accessManager {
	public static accessManager instance;
	private accessManager(){
		
	}
	/**
	 * se è stato già istanziato un oggetto Manager si utilizza quello, altrimenti si istanzia un nuovo oggetto Manager
	 * @return ViewListener instance
	 */
	public static accessManager getInstance(){
		if(instance==null){
			instance= new accessManager();
		}
		return instance;
	}
	/**
	 * Metodo che fa il check sulle credenziali di login fornite dall'utente. Per fare ciò si avvale di un oggetto accessDAO che altro non 
	 * è che l'oggetto addetto ad eseguire le queries al DB per ottenere le credenziali sulle quali effettuare il controllo
	 * @param username
	 * @param password
	 * @param finestra
	 * @return void
	 * @throws Exception 
	 */
	public void loginManager(Login finestra) throws Exception{
		Utente user= new Utente(null, null, null, null);
		String username=finestra.textField.getText();
		String password=finestra.passwordField.getText();
		if(username.length()==0 || password.length()==0){
			JOptionPane.showMessageDialog (null, "Campi vuoti");
		}
		else{
			authDAO b= new accessDAO();
			user= b.access(username);
		
		
		
			if(password.equals(user.getPassword())){
				switch(user.getPermessi()){
				
				case "ad":	Ricerca Frame= new Ricerca(user.getUsername(), user.getPermessi());
							Frame.setVisible(true);
							Frame.hellouser.setText("Salve amministratore "+ ""+user.getUsername());
							finestra.dispose();
							break;
				case "ri":
				case "ac":	Ricerca Frame2= new Ricerca(user.getUsername(), user.getPermessi());
							Frame2.setVisible(true);
							Frame2.btnAdmin.setVisible(false);
							Frame2.hellouser.setText("Salve "+ ""+user.getUsername());
							finestra.dispose();
							break;
				
				default: 	Ricerca Frame1= new Ricerca(user.getUsername(), user.getPermessi());
							Frame1.setVisible(true);
							Frame1.btnAdmin.setVisible(false);
							Frame1.operenonpubb.setVisible(false);
							Frame1.hellouser.setText("Salve "+ ""+user.getUsername());
							finestra.dispose();
							break;
				}
					
				}	
			else{
				JOptionPane.showMessageDialog (null, "Dati non corretti", "Title", JOptionPane.ERROR_MESSAGE);
			
			}
		}
	}
	
	
	/**
	 * Metodo che fa il check dei dati inseriti dall'utente in fase di registrazione. Per fare ciò si avvale di un oggetto accessDAO
	 * che verifica se le credenziali inserite dall'utente sono disponibili.
	 * @param username
	 * @param password
	 * @param email
	 * @param finestra
	 * @return void
	 * @throws Exception 
	 */
	public void signinManager(Login finestra) throws Exception{
		String username=finestra.textField_1.getText();
		String password=finestra.passwordField_1.getText(); 
		String email=finestra.textField_2.getText();
		if(username.length()==0 || password.length()==0 || email.length()==0){
			JOptionPane.showMessageDialog (null, "Campi vuoti");
		}
		else{
			if(!accessManager.emailValidator(email)){
				JOptionPane.showMessageDialog (null, "formato email non corretto");
				
			}
			else{
				authDAO b= new accessDAO();
				Boolean reg = b.check(username, email);
				if(!reg){
					if(b.signin(username, password, email)){
						JOptionPane.showMessageDialog (null, "Qualcosa è andato storto, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
					}
					else{
					
						JOptionPane.showMessageDialog (null, "Registrazione effettuata, effettua il login", "Title", JOptionPane.INFORMATION_MESSAGE);
						finestra.textField.setText(username);
						finestra.textField_1.setText(null);
						finestra.textField_2.setText(null);
						finestra.passwordField_1.setText(null);
					}
				}
				else{
					JOptionPane.showMessageDialog (null, "Username e/o email già esistenti, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
				}
			}
		}			
	}
	
	public void freeLogin(Login finestra){
		Ricerca Frame= new Ricerca(null, null);
		Frame.setVisible(true);
		Frame.btnAdmin.setEnabled(false);
		Frame.btnConsulta.setEnabled(false);
		finestra.dispose();
	}
	
	
	
	/**
	 * Metodo che viene richiamato ogni qual volta vi è il bisogno di mostrare un messaggio all'utenza.
	 * @param i
	 * @return void
	 */
	public void alert(int i){
		switch(i){
		
			case 1: 	JOptionPane.showMessageDialog (null, "Riempire tutti i campi", "Title", JOptionPane.ERROR_MESSAGE);
						break;
			default: 	break;
				}
	}
	/**
	 * Metodo per la validazione dell'email
	 * @param email
	 * @return Boolean
	 */
	public static Boolean emailValidator(String email){
		String emailinserita = email;
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(emailinserita);
		if (matcher.find()) {
			return true;
		} else {
		    return false;
		}
		
	}
	
	/***************************************************************************************************/
	public void createoperatore(BackOffice finestra) throws Exception{
		String nomeoperatore= finestra.operatorname.getText();
		String password= finestra.passwordoperatore.getText();
		
	    String email=finestra.textField_6.getText();
	    String ruolo= (String) finestra.ruolo.getSelectedItem();
		
		if(nomeoperatore==null ){
			JOptionPane.showMessageDialog (null, "Inserisci il nome di un operatore");
		}
		else{
			 accessDAO c= new accessDAO();
		     c.creareoperatore(nomeoperatore,password,email,ruolo);
		     JOptionPane.showMessageDialog (null, "NUOVO OPERATORE NEL BIBLIOMANAGER");
	
	}
	}

	/**********************************************************************************************/
	public void promoutenza(BackOffice finestra) throws Exception{
		String usernameutente= finestra.usernamepromuovere.getText();
		
	    String permesso= (String) finestra.comboBox.getSelectedItem();
		
		if(usernameutente==null ){
			JOptionPane.showMessageDialog (null, "Inserisci il nome di un operatore");
		}
		else{
			 accessDAO c= new accessDAO();
		     c.promutenza(usernameutente,permesso);
		     JOptionPane.showMessageDialog (null, "UTENTE PROMOSSO");
	
	}
	}

}
