package data;
/**
 * Classe che rappresenta gli utenti del sistema
 * @author loris
 *
 */
public class Utente {
private String username;
private String password;
private String email;
private String permessi;
/**
 * Costruttore che incapsula tutti gli oggetti in utente
 * @param username Stringa che rappresenta l'oggetto username
 * @param password Stringa che rappresenta l'oggetto password
 * @param email    Stringa che rappresenta l'oggetto email
 * @param permessi Stringa che rappresenta l'oggetto permessi
 */
public Utente(String username, String password, String email, String permessi) {
	super();
	this.username = username;
	this.password = password;
	this.email = email;
	this.permessi = permessi;
}
/**
 *Metodo per prendere l'username da un dato utente
 * @return Stringa che rappresenta l'username
 */
public String getUsername() {
	return username;
}
/**
 * Metodo per settare un username
 * @param username  Stringa che rappresenta l'username da settare
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * Metodo per prendere la password da un dato utente
 * @return  String
 */
public String getPassword() {
	return password;
}
/**
 * Metodo per settare una password
 * @param password Stringa che rappresenta la password da settare
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * Metodo per prendere l'email da un dato utente
 * @return String
 */
public String getEmail() {
	return email;
}
/**
 * Metodo per settare una nuova email
 * @param email Stringa che rappresenta la nuova email
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * Metodo per prendere il permesso da un dato utente
 * @return String
 */
public String getPermessi() {
	return permessi;
}
/**
 * Metodo per settare un nuovo permesso
 * @param permessi  nuovo permesso
 */
public void setPermessi(String permessi) {
	this.permessi = permessi;
}

}
