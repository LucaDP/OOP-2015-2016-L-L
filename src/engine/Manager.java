/**
 * engine
 */
package engine;

import UIPackage.*;

import java.awt.Component;

import javax.swing.*;
import data.*;
/**
 * 
 * @author Luca
 *
 */
public class Manager {
	public static Manager instance;
	private Manager(){
		
	}
	/**
	 * se è stato già istanziato un oggetto Manager si utilizza quello, altrimenti si istanzia un nuovo oggetto Manager
	 * @return ViewListener instance
	 */
	public static Manager getInstance(){
		if(instance==null){
			instance= new Manager();
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
	 */
	public void loginManager(String username, String password, Login finestra){
		
		DAO b= new accessDAO();
		String pwd=b.access(username);
		
		
		if(password.equals(pwd)){
			
			Ricerca Frame= new Ricerca();
			Frame.setVisible(true);
			finestra.dispose();
			}	
		else{
			JOptionPane.showMessageDialog (null, "Dati non corretti", "Title", JOptionPane.ERROR_MESSAGE);
			
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
	 */
	public void signinManager(String username, String password, String email, Login finestra){
		DAO b= new accessDAO();
		Boolean reg = b.registration(username, password, email);
		if(reg){
			JOptionPane.showMessageDialog (null, "Registrazione effettuata, effettua il login", "Title", JOptionPane.INFORMATION_MESSAGE);
			
			finestra.textField.setText(username);
			finestra.textField_1.setText(null);
			finestra.textField_2.setText(null);
			finestra.passwordField_1.setText(null);
		}
		else{
			JOptionPane.showMessageDialog (null, "Username e/o email già esistenti, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void freeLogin(Login finestra){
		Ricerca Frame= new Ricerca();
		Frame.setVisible(true);
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

}
