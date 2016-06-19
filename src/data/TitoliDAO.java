package data;

import java.util.ArrayList;
/**
 * interfaccia che definisce le firme dei metodi per le operazioni sui titoli nel database
 * @author Luca
 *
 */
public interface TitoliDAO{
	/**
	 * firma del metodo per la pubblicaizone di un'opera
	 * @param opera nome opera
	 * @return boolean 
	 */
	public boolean pubblicaOpera(String opera);
	/**
	 * firma del metodo per la pubblicazione di un'opera 
	 * @param opera nome opera
	 * @param autore nome autore
	 * @param epoca epoca dell'opera
	 * @return boolean
	 */
	public boolean creaNuovaOpera(String opera, String autore, String epoca);
	/**
	 * firma del metodo per la selezione di opera/e
	 * @param opera nome opera
	 * @param pubblicata pubblicata si/no
	 * @return ArrayList
	 */
	public ArrayList<OperaGen> selectOpera(String opera, boolean pubblicata);
}
