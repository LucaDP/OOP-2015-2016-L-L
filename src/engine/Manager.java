package engine;

import UIPackage.*;
import javax.swing.*;
import data.*;

public class Manager {
	public static Manager instance;
	private Manager(){
		
	}
	/*se è stato già istanziato un oggetto Manager si utilizza quello, altrimenti si istanzia un nuovo oggetto Manager*/
	public static Manager getInstance(){
		if(instance==null){
			instance= new Manager();
		}
		return instance;
	}
	
	public static void loginManager(String username, String password){
		
		DAO b= new accessDAO();
		String pwd=b.access(username);
		
		
		if(password.equals(pwd)){
			
			Ricerca Frame= new Ricerca();
			Frame.setVisible(true);
			}	
		else{
			JOptionPane.showMessageDialog (null, "Dati non corretti", "Title", JOptionPane.ERROR_MESSAGE);
			Login Frame= new Login();
			Frame.setVisible(true);
		}
	}
	
	
	
	public static void signinManager(String username, String password, String email){
		DAO b= new accessDAO();
		Boolean reg = b.registration(username, password, email);
		if(reg){
			JOptionPane.showMessageDialog (null, "Registrazione effettuata, effettua il login", "Title", JOptionPane.INFORMATION_MESSAGE);
			Login Frame= new Login();
			Frame.setVisible(true);
		}
		else{
			JOptionPane.showMessageDialog (null, "Username e/o email già esistenti, rieseguire la procedura", "Title", JOptionPane.ERROR_MESSAGE);
			Login Frame= new Login();
			Frame.setVisible(true);
		}
		
	}
	
	
	
	
	public static void alert(int i){
		switch(i){
		
			case 1: 	JOptionPane.showMessageDialog (null, "Riempire tutti i campi", "Title", JOptionPane.ERROR_MESSAGE);
						Login Frame= new Login();
						Frame.setVisible(true);
						break;
			default: 	break;
				}
	}

}
