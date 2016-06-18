package data;

public class OperaGen {
	private String autore;
	private String nomeOpera;
	private String epoca;
	private Boolean pubblicata;
	
	
	public OperaGen(String autore, String nomeOpera, String epoca, Boolean pubblicata) {
		super();
		this.autore = autore;
		this.nomeOpera = nomeOpera;
		this.epoca = epoca;
		this.pubblicata = pubblicata;
	}

	public Boolean getPubblicata() {
		return pubblicata;
	}

	public void setPubblicata(Boolean pubblicata) {
		this.pubblicata = pubblicata;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getNomeOpera() {
		return nomeOpera;
	}

	public void setNomeOpera(String nomeOpera) {
		this.nomeOpera = nomeOpera;
	}

	public String getEpoca() {
		return epoca;
	}

	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	
	public String toString(){
		return  " Opera: "+this.nomeOpera+"   Autore: "+this.autore+"   Epoca: "+this.epoca+"   Pubblicata: "+ (this.pubblicata ? "si": "no");
		
	}

}
