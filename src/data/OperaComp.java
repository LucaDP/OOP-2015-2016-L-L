package data;

import java.util.ArrayList;
/**
 * Classe che rappresenta l'opera con delle pagine
 * @author Luca
 *
 */
public class OperaComp extends OperaGen {
	private int pagTot;
	private ArrayList<Page> pagine; 
	/**
	 * Costruttore della classe OperaComp
	 * @param autore autore dell'opera
	 * @param nomeOpera nome dell'opera
	 * @param epoca epoca dell'opera
	 * @param pubblicata opera pubblicata si/no
	 * @param pagTot pagine totali dell opera
	 * @param pagine pagine che formano l'opera
	 */
	public OperaComp(String autore, String nomeOpera, String epoca, Boolean pubblicata, int pagTot, ArrayList<Page> pagine) {
		super(autore, nomeOpera, epoca, pubblicata);
		this.pagTot = pagTot;
		this.pagine=pagine;
	}
	/**
	 * Metodo che ritorna il numero delle pagine dell'opera
	 * @return int 
	 */
	public int getPagTot() {
		return pagTot;
	}
	/**
	 * Metodo che permette di settare il numero di pagine totali
	 * @param pagTot numero delle pagine totali
	 */
	public void setPagTot(int pagTot) {
		this.pagTot = pagTot;
	}
	/**
	 * ritorna una collezione di pagine
	 * @return ArrayList<Page>
	 */
	public ArrayList<Page> getPagine() {
		return pagine;
	}
	/**
	 * Metodo che permette settare le pagine di un opera
	 * @param pagine collezione di pagine da inserire
	 */
	public void setPagine(ArrayList<Page> pagine) {
		this.pagine=pagine;
	}
	/**
	 * Metodo che permette di inserire nuove pagine
	 * @param nuovapagina nuova pagina da inserire
	 */
	public void addPagina(Page nuovapagina){
		pagine.add(nuovapagina);
	}
	/**
	 * Metodo che stampa le info sull'opera
	 */
	@Override
	public String toString() {
		return super.toString() + "   Numero di pagine: "+pagTot;
	}
	

}
