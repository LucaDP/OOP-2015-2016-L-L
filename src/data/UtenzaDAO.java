package data;
/**
 * Interfaccia che definisce le firme dei metodi per le operazioni sull' utenza
 * @author loris
 *
 */
public interface UtenzaDAO {
	/**
	 * Firma del metodo per l'accesso nel sistema
	 * @param username Stringa che rappresenta un username di un utente
	 * @return         ritorna un utente
	 */
	Utente access(String username);
	/**
	 * Firma del metodo utilizzato per fare controlli sull'utenza
	 * @param username Stringa che rappresenta l'username dell'utente
	 * @return         booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean check(String username);
	/**
	 * Firma del metodo per la registrazione
	 * @param username Stringa che rappresenta l'username da registrare nel database
	 * @param password Stringa che rappresenta la password da registrare nel database
	 * @param email    Stringa che rappresenta l'email da registrare nel database
	 * @return         booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean signin(String username, String password, String email);
	/**
	 * Firma del metodo per la creazione di un collaboratore
	 * @param username  Stringa che rappresenta l'username da registrare per il collaboratore nel database
	 * @param password  Stringa che rappresenta la password da registrare per il collaboratore nel database
	 * @param email     Stringa che rappresenta l'email da registrare per il collaboratore nel database
	 * @param permesso  Stringa che rappresenta il permesso da registrare per il collaboratore nel database
	 * @return          booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean creaOperatore(String username, String password, String email, String permesso);
	/**
	 * Firma del metodo per la promozione di un utente 
	 * @param username Stringa che rappresenta l'username dell'utente da promuovere nel database
	 * @param permesso Stringa che rappresenta il permesso da dare all'utente nel database
	 * @return         booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean promuoviUtente(String username, String permesso);
	/**
	 * Firma del metodo per il cambiamento dell'email
	 * @param username   Stringa che rappresenta l'username dell'utente da cambiare nel database
	 * @param email      Stringa che rappresenta l'email dell'utente da cambiare  nel database
	 * @return           booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean cambioemail(String username, String email);
	/**
	 * Firma del metodo per il cambiamento della password per un utente
	 * @param username  Stringa che rappresenta l'username dell'utente da cui cambiare la password  nel database
	 * @param npwd      Stringa che rappresenta la nuova password dell'utente da cambiare  nel database
	 * @return          booleano che ci permette di capire l'esito dell'operazione
	 */
	boolean cambiopwd(String username, String npwd);
	
}
