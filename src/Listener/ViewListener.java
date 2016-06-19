/**
 * Listener
 */
package Listener;

import Gui.BackOffice;
import Gui.GestioneProfilo;
import Gui.Login;
import Gui.Opera;
import Gui.Ricerca;
import data.OperaGen;
import engine.*;


/**
 * 
 * @author loris
 *
 */
public class ViewListener {
	public static ViewListener instance;

	private ViewListener(){
		
	}
	/**
	 * Metodo per istanziare oggetto viewlistener . Se l'oggetto è già istanziato ritorna l'oggetto, altrimenti lo crea
	 * @return
	 */
	public static ViewListener getInstance(){
		if(instance==null){
			instance= new ViewListener();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param finestra
	 * Metodo che passa i dati presenti nella finestra all'input form controller che si trova nell'engine
	 */
	public void login(Login finestra ){
		InputFormController controller=new InputFormController();
		controller.LogInInput(finestra,finestra.textField.getText(),finestra.passwordField.getText());
	}
    /**
     * 
     * @param finestra
     * Metodo che passa i dati presenti nella form di login all'inputform controller che si trova nell'engine
     */
	public void signin(Login finestra){
		InputFormController controller=new InputFormController();
		controller.SignInInput(finestra, finestra.textField_1.getText(), finestra.passwordField_1.getText(),finestra.textField_2.getText());
	}
	/**
	 * 
	 * @param finestra
	 * Metodo che passa i valori della barra di ricerca nell'inputform controller che si trova nell'engine
	 */
	public void search(Ricerca finestra){
		InputFormController controller=new InputFormController();
		controller.RicercaUtentiInputOutput(finestra, finestra.textField.getText(), !(finestra.operenonpubb.isSelected()));
	}
	
	
	/**
	 *  Metodo che passa all'inputform controller che si trova nell'engine parametri per la visione del bottone amministrazione
	 * @param finestra
	 * @param username
	 * @param permesso
	 */
	public void viewAdmin(Ricerca finestra, String username, String permesso){
		AdminActionManager controller= new AdminActionManager();
		controller.backOffice();	
	}
	/**
	 * Metodo che passa i valori della form per la creazione di un collaboratore  nell'inputformcontroller che si trova nell'engine
	 * @param finestra
	 */
	public void creazioneoperatore(BackOffice finestra){
		   InputFormController controller= new InputFormController();
		   controller.CreaOperatoreInput(finestra.operatorname.getText(), finestra.passwordoperatore.getText(), finestra.Email.getText(), (String)finestra.ruolo.getSelectedItem());
	}
    /**
     * Metodo che passa i valori della form per la promozione di un utenza nell'input form controller che si trova nell'engine
     * @param finestra
     */
	public void promozioneutenza(BackOffice finestra){
		   InputFormController controller= new InputFormController();
		   controller.PromozioneOperatoreInput(finestra.usernamepromuovere.getText(), (String)finestra.ruolo1.getSelectedItem());
	}
	/**
	 * Metodo che passa il valore della form per la ricerca di un utente nell'inputformcontroller che si trova nell'engine
	 * @param finestra
	 */
	public void searchBO(BackOffice finestra) {
		InputFormController controller=new InputFormController();
		controller.RicercaAdminInputOutput(finestra, finestra.nomeoperdaapubb.getText());
	}
	/**
	 * Metodo che passa i valori presenti nella form per la creazione di un opera nell'inputform controller che si trova nell'engine
	 * @param finestra
	 */
	public void creazioneopera(BackOffice finestra){
		InputFormController controller= new InputFormController();
		controller.CreaOperaInput(finestra.nomeopera.getText(), finestra.autore.getText(), finestra.epoca.getText());
	}
	 
	/**
	 * Metodo che passa il valore della form per la pubblicazione di un opera nell'inputform controller che si trova nell'engine
	 * @param finestra
	 */
	public void pubblicareopera(BackOffice finestra){
		InputFormController controller= new InputFormController();
		controller.PubbOperaInput((String)finestra.elencooperedapubb.getSelectedItem());
	}
	
	/**
	 * Metodo che passa il valore dell'utente connesso nell'inputform controller che si trova nell'engine
	 * @param username
	 */
	public void gestioneProfilo(String username){
		UserActionManager controller= new UserActionManager();
		controller.apriGestioneProfilo(username);
	}
	/**
	 * Metodo che passa due valori per modificare l'email nell'inputformcontroller che si trova nell'engine
	 * @param finestra
	 * @param username
	 */
	public void cambiaEmail(GestioneProfilo finestra, String username){
		InputFormController controller= new InputFormController();
		controller.cambiaEmailInput(username, finestra.nuovaEmail.getText());
		
	}
	/**
	 * Metodo che passa due valori presenti nella form che si trova in gestione profilo nell'input form controller che si trova nell'engine
	 * @param finestra
	 * @param username
	 */
	public void cambiaPassword(GestioneProfilo finestra, String username){
		InputFormController controller= new InputFormController();
		controller.cambiaPasswordInput(username, finestra.vecchiaPwd.getText(), finestra.nuovaPwd.getText());
	}
	
	
	/**
	 * Metodo che passa la finestra di login nell'input form controller per l'accesso senza login
	 * @param finestra oggetto login
	 */
	public void freeAccess(Login finestra){
		UserActionManager controller=new UserActionManager();
		controller.AccessoUtenteBase();
		finestra.dispose();
	}
	
    /**
     * Metodo passa la finestra di ricerca , username e permesso per passarlo al TitleManager
     * @param finestra si intende l'intende l'interfaccia grafica della ricerca
     * @param username Stringa che rappresenta l'username
     * @param permesso Stringa che rappresenta il permesso 
     */
	public void view(Ricerca finestra, String username, String permesso){
		TitleManager a= TitleManager.getInstance();
		a.viewOpera(finestra, permesso, username);
	}
   
   /**
    * Metodo che passa oggetti per poter andare avanti nella pagine
    * @param Frame   interfaccia grafica dell'opera
    * @param a       oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void nextPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.showNext(a, Frame, permesso, username); 
   }
   /**
    * Metodo che passa oggetti per poter andare indietro nella pagine
    * @param Frame    interfaccia grafica dell'opera
    * @param a        oggetto opera gen
    * @param permesso Stringa che rappresenta il permesso dell'utente
    * @param username Stringa che rappresenta l'username dell'utente
    */
   public void prevPage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.showPrev(a, Frame, permesso, username); 
   }
   
   /**
    * Metodo che permette di rivisionare il tei
    * @param Frame      interfaccia grafica dell'opera
    * @param a          oggetto opera gen
    * @param permesso   Stringa che rappresenta il permesso dell'utente
    * @param username   Stringa che rappresenta l'username dell'utente
    */
   public void revisioneTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.abilitaRevisioneTei(Frame, a, permesso ,username);
   }
   /**
    * Metodo che conferma il tei
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void confermaTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.pubblicaTei(Frame, a, permesso, username);  
   }
  
   /**
    * Metodo che rifiuta il tei
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void rifiutaTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.respingiTei(Frame, a, permesso, username);
   }
   
   /**
    * Metodo che permette di modificare il tei
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void editTei(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.modificaTei(Frame, a, permesso, username);
   }
   
   /**
    * Metodo che permette di uploadare l'immagine
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void uploadImage(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b=TitleManager.getInstance();
	   b.uploadImg(Frame, a, permesso, username);
   }
   
   /**
    * Metodo che permette di revisionare le immagini
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void revisioneImg(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.abilitaRevisioneImg(Frame, a, permesso ,username);
   }
   /**
    * Metodo che permette di rifiutare le immagini
    * @param Frame      interfaccia grafica dell'opera
    * @param a          oggetto opera gen
    * @param permesso   Stringa che rappresenta il permesso dell'utente
    * @param username   Stringa che rappresenta l'username dell'utente
    */
   public void rifiutaImg(Opera Frame, OperaGen a, String permesso, String username){
	   TitleManager b= TitleManager.getInstance();
	   b.respingiImg(Frame, a, permesso, username);
   }
   /**
    * Metodo che permette di confermare immagini
    * @param Frame     interfaccia grafica dell'opera
    * @param a         oggetto opera gen
    * @param permesso  Stringa che rappresenta il permesso dell'utente
    * @param username  Stringa che rappresenta l'username dell'utente
    */
   public void confermaImg(Opera Frame, OperaGen a, String permesso, String username) {
	   TitleManager b= TitleManager.getInstance();
	   b.pubblicaImg(Frame, a, permesso, username);   
   }
   
   /**
    * Metodo che permette di vedere le anteprime delle scrittura tei
    * @param Frame   interfaccia grafica dell'opera
    */
   public void anteprima(Opera Frame){
	   TitleManager controller= TitleManager.getInstance();
	   controller.anteprimaTei(Frame);
	   
   }
}
