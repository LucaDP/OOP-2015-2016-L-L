package data;
/**
 * interfaccia che definisce le firme dei metodi per le operazioni sulle trascrizioni
 * @author Luca
 *
 */
public interface TeiDAO {
	/**
	 * firma del metodo per la pubblicazione del tei
	 * @param numeropagina  numero della pagina
	 * @param nomeopera nome dell'opera
	 * @param revisore  username del revisore
	 * @return boolean
	 */
	public boolean pubbTei(int numeropagina, String nomeopera, String revisore );
	/**
	 * firma del metodo per l'upload della trascrizione
	 * @param numeropagina numero della pagina
	 * @param nomeopera nome dell'opera
	 * @param testo testo da pubblicare
	 * @param trascrittore username del trascrittore
	 * @return boolean
	 */
	public boolean uploadTei(int numeropagina, String nomeopera, String testo, String trascrittore);
}
