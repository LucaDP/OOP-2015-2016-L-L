package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import UIPackage.BackOffice;
import UIPackage.GestioneProfilo;
import UIPackage.Login;
import UIPackage.Ricerca;
import data.OperaGen;

public class InputFormController {
	public void LogInInput(Login finestra, String username, String password)throws Exception{
		
		
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
	
	public void SignInInput(Login finestra, String username, String password, String email) throws Exception{
		
		
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
	
	
	public void RicercaUtentiInputOutput(Ricerca finestra, String ricerca, Boolean pubblicata) throws Exception{
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
	
	
	public void RicercaAdminInputOutput(BackOffice finestra, String ricerca) throws Exception{
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
	
	
	public void CreaOperatoreInput(String nomeoperatore, String password, String email, String ruolo) throws Exception{
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
	
	public void PromozioneOperatoreInput(String username, String ruolo) throws Exception{
		
		if("".equals(username)){
			JOptionPane.showMessageDialog (null, "Inserisci il nome dell utente da promuovere/retrocedere");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.PromozioneUtente(username, ruolo);
		}
		
		
	}
	
	public void PubbOperaInput(String opera){
		if("".equals(opera)){
			JOptionPane.showMessageDialog (null, "Inserisci il nome dell utente da promuovere/retrocedere");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.pubblicaOpera(opera);
		}
		
	}
	
	public void CreaOperaInput(String opera, String autore, String epoca){
		if("".equals(opera) || "".equals(autore) || "".equals(epoca)){
			JOptionPane.showMessageDialog (null, "Riempire tutti i campi");
		}
		else{
			AdminActionManager controller= new AdminActionManager();
			controller.pubblicaOpera(opera);
		}
	}
	
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
