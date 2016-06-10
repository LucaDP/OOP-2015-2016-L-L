/**
 * Listener
 */
package Listener;

import UIPackage.Login;
import UIPackage.Ricerca;
import engine.accessManager;
import engine.TitleManager;

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
	
	public void view(Ricerca finestra, String username, String permesso) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.viewOpera(finestra, permesso);
		
	}
	

}
