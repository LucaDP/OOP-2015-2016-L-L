package data;

public class OperaGen {
	public String autore;
	public String nomeOpera;
	public String epoca;
	
	public OperaGen(String autore, String nomeOpera, String epoca){
		autore= this.autore;
		nomeOpera=this.autore;
		epoca=this.epoca;	
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
		return this.autore + this.epoca + this.nomeOpera + "ciaoneee";
		
	}

}
