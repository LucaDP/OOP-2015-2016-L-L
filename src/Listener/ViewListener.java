/**
 * Listener
 */
package Listener;

import UIPackage.BackOffice;
import UIPackage.ImgEdit;
import UIPackage.Login;
import UIPackage.Ricerca;
import Engine.accessManager;

import java.awt.event.ActionListener;

import Engine.TitleManager;

/**
 * 
 * @author Luca
 *
 */
public class ViewListener {
	public static ViewListener instance;
	
	private ViewListener(){
		
	}
	
	/**
	 * se è stato già istanziato un oggetto ViewListener si utilizza quello, altrimenti si
	 * istanzia un nuovo oggetto ViewListener
	 * @return ViewListener instance
	 */
	public static ViewListener getInstance(){
		if(instance==null){
			instance= new ViewListener();
		}
		return instance;
	}
	
	/**
	 * metodo che richiama l'istanza di Manager al fine di eseguire la procedura di login
	 * @param username
	 * @param password
	 * @param finestra
	 * @return void
	 * @throws Exception 
	 */
	public void login(Login finestra ) throws Exception{
		
		accessManager a=accessManager.getInstance();
		a.loginManager(finestra);
		
	}
	
	/**
	 * metodo che richiama l'istanza di Manager al fine di eseguire la procedura di registrazione
	 * @param username
	 * @param password
	 * @param email
	 * @param finestra
	 * @return void
	 * @throws Exception 
	 */
	public void signin(Login finestra) throws Exception{
		accessManager a=accessManager.getInstance();
		a.signinManager(finestra);
		
	}
	/**
	 * Metodo di accesso libero al sistema
	 * @param finestra
	 * @return void
	 */
	public void freeAccess(Login finestra){
		accessManager a=accessManager.getInstance();
		a.freeLogin(finestra);
		
	}
	/**
	 * Metodo per indicare al controller di ricercare un titolo
	 * @param ricerca
	 * @param finestra
	 * @return void
	 * @throws Exception 
	 */
	 
	public void search(Ricerca finestra) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.searchOpera(finestra);
	}
	
	public void searchBO(BackOffice finestra) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.searchOperaBO(finestra);
	}
	
	public void view(Ricerca finestra, String username, String permesso) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.viewOpera(finestra,  permesso);
		
	}
	public void viewAdmin(Ricerca finestra, String username, String permesso) throws Exception{
		TitleManager b= TitleManager.getInstance();
		b.viewAdmin(finestra, username, permesso);
		
	}

	
   public void creazioneopera(BackOffice finestra)throws Exception{
	   TitleManager b= TitleManager.getInstance();
	   b.createopera(finestra);
   }
   public void creazioneoperatore(BackOffice finestra)throws Exception{
	   accessManager b= accessManager.getInstance();
	   b.createoperatore(finestra);
   }
/**********************************************************************************************/
   public void promozioneutenza(BackOffice finestra)throws Exception{
	   accessManager b= accessManager.getInstance();
	   b.promoutenza(finestra);
   }
/**********************************************************************************************/
   public void pubblicareopera(BackOffice finestra)throws Exception{
	   TitleManager b= TitleManager.getInstance();
	   b.pubbopera(finestra);
   }

}


