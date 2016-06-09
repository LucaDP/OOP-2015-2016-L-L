/**
 * Listener
 */
package Listener;

import UIPackage.Login;
import UIPackage.Ricerca;
import engine.Manager;
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
	 */
	public void login(String username, String password, Login finestra ){
		if(username.length()==0 || password.length()==0){
			Manager a=Manager.getInstance();
			a.alert(1);
		}
		else{
			Manager a=Manager.getInstance();
			a.loginManager(username , password, finestra);
		}
	}
	
	/**
	 * metodo che richiama l'istanza di Manager al fine di eseguire la procedura di registrazione
	 * @param username
	 * @param password
	 * @param email
	 * @param finestra
	 * @return void
	 */
	public void signin(String username, String password, String email, Login finestra){
		if(username.length()==0 || password.length()==0 || email.length()==0){
			Manager a=Manager.getInstance();
			a.alert(1);
		}
		else{
			Manager a=Manager.getInstance();
			a.signinManager(username , password, email, finestra);
			
		}
		
	}
	
	public void freeAccess(Login finestra){
		Manager a=Manager.getInstance();
		a.freeLogin(finestra);
		
	}
	
	public void search(String o, Ricerca finestra){
		TitleManager a= TitleManager.getInstance();
		a.searchOpera(o, finestra);
	}

}
