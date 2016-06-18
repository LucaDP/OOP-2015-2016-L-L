/**
 * Listener
 */
package Listener;

import UIPackage.BackOffice;
import UIPackage.GestioneProfilo;
import UIPackage.Login;
import UIPackage.Opera;
import UIPackage.Ricerca;

import data.OperaGen;
import engine.*;



public class ViewListener {
	public static ViewListener instance;

	public ViewListener(){
		
	}
	
	public static ViewListener getInstance(){
		if(instance==null){
			instance= new ViewListener();
		}
		return instance;
	}
	/************************************************TEXT INPUT***********************************************************************/
	public void login(Login finestra ) throws Exception{
		InputFormController controller=new InputFormController();
		controller.LogInInput(finestra,finestra.textField.getText(),finestra.passwordField.getText());
	}

	public void signin(Login finestra) throws Exception{
		InputFormController controller=new InputFormController();
		controller.SignInInput(finestra, finestra.textField_1.getText(), finestra.passwordField_1.getText(),finestra.textField_2.getText());
	}
	
	public void search(Ricerca finestra) throws Exception{
		InputFormController controller=new InputFormController();
		controller.RicercaUtentiInputOutput(finestra, finestra.textField.getText(), !(finestra.operenonpubb.isSelected()));
	}
	
	/******************************BACKOFFICE************************************************+**/
	
	public void viewAdmin(Ricerca finestra, String username, String permesso) throws Exception{
		AdminActionManager controller= new AdminActionManager();
		controller.backOffice();	
	}
	public void creazioneoperatore(BackOffice finestra)throws Exception{
		   InputFormController controller= new InputFormController();
		   controller.CreaOperatoreInput(finestra.operatorname.getText(), finestra.passwordoperatore.getText(), finestra.Email.getText(), (String)finestra.ruolo.getSelectedItem());
	}

	public void promozioneutenza(BackOffice finestra)throws Exception{
		   InputFormController controller= new InputFormController();
		   controller.PromozioneOperatoreInput(finestra.usernamepromuovere.getText(), (String)finestra.ruolo1.getSelectedItem());
	}
	public void searchBO(BackOffice finestra) throws Exception{
		InputFormController controller=new InputFormController();
		controller.RicercaAdminInputOutput(finestra, finestra.nomeoperdaapubb.getText());
	}
	
	public void creazioneopera(BackOffice finestra)throws Exception{
		InputFormController controller= new InputFormController();
		controller.CreaOperaInput(finestra.nomeopera.getText(), finestra.autore.getText(), finestra.epoca.getText());
	}
	 
	public void pubblicareopera(BackOffice finestra)throws Exception{
		InputFormController controller= new InputFormController();
		controller.PubbOperaInput((String)finestra.elencooperedapubb.getSelectedItem());
	}
	/************************************************/
	
	/**********************GESTIONE PROFILO**********/
	public void gestioneProfilo(String username){
		UserActionManager controller= new UserActionManager();
		controller.apriGestioneProfilo(username);
	}
	public void cambiaEmail(GestioneProfilo finestra, String username){
		InputFormController controller= new InputFormController();
		controller.cambiaEmailInput(username, finestra.nuovaEmail.getText());
		
	}
	public void cambiaPassword(GestioneProfilo finestra, String username){
		InputFormController controller= new InputFormController();
		controller.cambiaPasswordInput(username, finestra.vecchiaPwd.getText(), finestra.nuovaPwd.getText());
	}
	
	
	
	/******************FINESTRA ADMIN********************************/
	
	
	/******ACCESSO LIBERO*****/
	public void freeAccess(Login finestra){
		UserActionManager controller=new UserActionManager();
		controller.AccessoUtenteBase();
		finestra.dispose();
	}
	
	public void view(Ricerca finestra, String username, String permesso) throws Exception{
		TitleManager a= TitleManager.getInstance();
		a.viewOpera(finestra, permesso, username);
	}
	
	
	 
	
	
	
	
	
	
	
	

   /**PAGINA VISUALIZZAZIONE OPERA** TITLE MANAGER Gestione operazioni su opera visualizzata*/
	
   public void nextPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.showNext(a, Frame, permesso, username); 
   }
   public void prevPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.showPrev(a, Frame, permesso, username); 
   }
   
   public void revisioneTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.abilitaRevisioneTei(Frame, a, permesso ,username);
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
