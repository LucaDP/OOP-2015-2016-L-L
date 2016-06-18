package data;
/**
 * 
 * @author Luca
 *
 */
public interface UtenzaDAO {
	Utente access(String username);
	boolean check(String username);
	boolean signin(String username, String password, String email);
	boolean creaOperatore(String username, String password, String email, String permesso);
	boolean promuoviUtente(String username, String permesso);
	boolean cambioemail(String username, String email);
	boolean cambiopwd(String username, String npwd);
	
}
