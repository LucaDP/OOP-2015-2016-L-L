/**
 * Listener
 */
package Listener;

import UIPackage.BackOffice;
import UIPackage.Login;
import UIPackage.Opera;
import UIPackage.Ricerca;
import data.OperaComp;
import data.OperaGen;
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
		a.viewOpera(finestra, permesso, username);
		
	}
	
	public void searchBO(BackOffice finestra) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.searchOperaBO(finestra);
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
	
   public void nextPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager.showNext(a, Frame, permesso, username); 
   }
   public void prevPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager.showPrev(a, Frame, permesso, username); 
   }
   
   public void revisioneTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.abilitaRevisione(Frame, a, permesso ,username);
   }
   public void confermaTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
	   TitleManager b= TitleManager.getInstance();
	   b.pubblicaTei(Frame, a, permesso, username);
	   
   }
   
   public void rifiutaTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.respingiTei(Frame, a, permesso, username);
   }
   
   public void editTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
	   TitleManager b= TitleManager.getInstance();
	   b.modificaTei(Frame, a, permesso, username);
   }
   
   public void uploadImage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.uploadImg(Frame, a, permesso, username);
   }
   public void revisioneImg(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.abilitaRevisioneImg(Frame, a, permesso ,username);
   }
   public void rifiutaImg(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.respingiImg(Frame, a, permesso, username);
   }
   public void confermaImg(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
	   TitleManager b= TitleManager.getInstance();
	   b.pubblicaImg(Frame, a, permesso, username);
	   
   }

}

