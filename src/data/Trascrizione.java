package data;


public class Trascrizione {

	
private String testo;
private String trascrittore;
private String revisore;

private Boolean pubblicato;
public Trascrizione(){
	
}

public Trascrizione(String testo, String trascrittore, String revisore, Boolean pubblicato) {
	this.testo = testo;
	this.trascrittore = trascrittore;
	this.revisore = revisore;
	this.pubblicato = pubblicato;
}
public String getTesto() {
	return testo;
}
public void setTesto(String testo) {
	this.testo = testo;
}
public String getTrascrittore() {
	return trascrittore;
}
public void setTrascrittore(String trascrittore) {
	this.trascrittore = trascrittore;
}
public String getRevisore() {
	return revisore;
}
public void setRevisore(String revisore) {
	this.revisore = revisore;
}


public Boolean getPubblicato() {
	return pubblicato;
}
public void setPubblicato(Boolean pubblicato) {
	this.pubblicato = pubblicato;
}
@Override
public String toString() {
	return "Trascrizione [testo=" + testo + ", trascrittore=" + trascrittore + ", revisore=" + revisore
			+ ", pubblicato=" + pubblicato + "]";
}

}
