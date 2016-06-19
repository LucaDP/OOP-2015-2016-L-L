package data;
/**
 * Classe che rappresenta l'opera senza pagine, di cui si hanno solo le info generali
 * @author Luca
 *
 */
public class OperaGen {
	private String autore;
	private String nomeOpera;
	private String epoca;
	private Boolean pubblicata;
	
	/**
	 * Costruttore della classe operaGen
	 * @param autore autore dell'opera
	 * @param nomeOpera nome dell'opera
	 * @param epoca epoca dell'opera
	 * @param pubblicata pubblicata si/no
	 */
	public OperaGen(String autore, String nomeOpera, String epoca, Boolean pubblicata) {
		super();
		this.autore = autore;
		this.nomeOpera = nomeOpera;
		this.epoca = epoca;
		this.pubblicata = pubblicata;
	}
   /**
    * Metodo per sapere se un'opera è pubblicata o meno
    * @return boolean
    */
	public boolean getPubblicata() {
		return pubblicata;
	}
	/**
	 * Metodo per settare l'attributo pubblicata a true o false
	 * @param pubblicata pubblicata true/false
	 */
	public void setPubblicata(Boolean pubblicata) {
		this.pubblicata = pubblicata;
	}
	/**
	 * Metodo per ottenere il nome dell'autore
	 * @return String
	 */
	public String getAutore() {
		return autore;
	}
	/**
	 * Metodo per settare il nome dell'autore
	 * @param autore nome dell'autore
	 */
	public void setAutore(String autore) {
		this.autore = autore;
	}
	/**
	 * Metodo per prendere il nome dell'opera
	 * @return String 
	 */
	public String getNomeOpera() {
		return nomeOpera;
	}
	/**
	 * Metodo per settare il nome dell'opera
	 * @param nomeOpera
	 */
	public void setNomeOpera(String nomeOpera) {
		this.nomeOpera = nomeOpera;
	}
	/**
	 * Metodo per prendere l'epoca dell'opera
	 * @return String
	 */
	public String getEpoca() {
		return epoca;
	}
	/**
	 * Metodo per settare l'epoca dell'opera
	 * @param epoca epoca dell'opera
	 */
	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	/**
	 * Metodo per stampare le info dell'opera
	 */
	@Override
	public String toString(){
		return  " Opera: "+this.nomeOpera+"   Autore: "+this.autore+"   Epoca: "+this.epoca+"   Pubblicata: "+ (this.pubblicata ? "si": "no");
		
	}

}
