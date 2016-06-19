package data;
/**
 * interfaccia che deinisce le firme dei metodi per le operazioni sulle pagine
 * @author Luca
 *
 */
public interface PagesDAO {
	/**
	 * firma del metodo  per la selezione delle pagine di un opera
	 * @param nomeopera  nome dell'opera
	 * @return OperaGen
	 */
	public OperaGen selectPages(String nomeopera);
	
}
