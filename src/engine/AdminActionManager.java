package engine;

import javax.swing.JOptionPane;

import UIPackage.BackOffice;
import data.OperaGenDAO;
import data.TitoliDAO;

import data.UtenteDAO;
import data.UtenzaDAO;



public class AdminActionManager extends UserActionManager {
	
	@Override
	protected boolean CreaUtente(String username, String password, String permesso) throws Exception{
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
	
	protected boolean CreaUtente(String username, String password, String email, String ruolo) throws Exception{
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
	
	protected boolean PromozioneUtente(String username, String permesso) throws Exception{
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
	
	public void creaNuovaOpera(String nomeopera, String autore, String epoca){
		TitoliDAO b= new OperaGenDAO();
	     if(b.creaNuovaOpera(nomeopera,autore,epoca)){
	    	 JOptionPane.showMessageDialog (null, "Opera inserita nel database");
	     }else{
	    	 JOptionPane.showMessageDialog (null, "Creazione opera non riuscita");
	     }	
	}
	
	public void pubblicaOpera(String nomeopera){
		TitoliDAO b= new OperaGenDAO();
	     if(!(b.pubblicaOpera(nomeopera))){
	    	 JOptionPane.showMessageDialog (null, "Opera pubblicata");
	     }else{
	    	 JOptionPane.showMessageDialog (null, "Opera non pubblicata, riprovare");
	     }
	}
	
	public void backOffice(){
		BackOffice frame = new BackOffice();
		frame.setVisible(true);
	}

}
