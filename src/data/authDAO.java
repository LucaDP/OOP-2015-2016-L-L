package data;
/**
 * 
 * @author Luca
 *
 */
public interface authDAO {
	Utente access(String a) throws Exception;
	Boolean check(String a, String b) throws Exception;
	Boolean signin(String a, String b, String c) throws Exception;
	
}
