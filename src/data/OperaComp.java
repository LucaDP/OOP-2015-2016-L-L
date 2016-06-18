package data;

import java.util.ArrayList;

public class OperaComp extends OperaGen {
	int pagTot;
	ArrayList<Page> pagine; 
	
	public OperaComp(String autore, String nomeOpera, String epoca, Boolean pubblicata, int pagTot, ArrayList<Page> pagine) {
		super(autore, nomeOpera, epoca, pubblicata);
		this.pagTot = pagTot;
		this.pagine=pagine;
	}
	
	public int getPagTot() {
		return pagTot;
	}
	public void setPagTot(int pagTot) {
		this.pagTot = pagTot;
	}
	public ArrayList<Page> getPagine() {
		return pagine;
	}
	public void setPagine(ArrayList<Page> pagine) {
		this.pagine=pagine;
	}
	public void addPagina(Page nuovapagina){
		pagine.add(nuovapagina);
	}

	@Override
	public String toString() {
		return super.toString() + "   Numero di pagine: "+pagTot;
	}
	

}
