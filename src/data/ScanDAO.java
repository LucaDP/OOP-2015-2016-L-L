package data;

import java.io.InputStream;
/**
 * interfaccia che definisce le fimre dei metodi per le operazioni sulle immagini
 * @author Luca
 *
 */
public interface ScanDAO {
	/**
	 * Firma del metodo per la pubblicazione delle immagini
	 * @param numeropagina numero della pagina	
	 * @param nomeopera nome dell'opera
	 * @param username	username del revisore immagini
	 * @return boolean
	 */
	public boolean pubbImg(int numeropagina, String nomeopera, String username );
	/**
	 * firma del metodo per l'upload delle immagini
	 * @param nomeopera nome dell'opera
	 * @param inputStream immagine
	 * @param numpag numero della pagina
	 * @param username username dell'acquisitore
	 * @return boolean 
	 */
	public boolean uploadImmagine(String nomeopera, InputStream inputStream, int numpag, String username );
}
