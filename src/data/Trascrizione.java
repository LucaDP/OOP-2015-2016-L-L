package data;

/**
 * Classe che rappresenta la Trascrizione 
 * @author  loris
 *
 */
public class Trascrizione {

	
private String testo;
private String trascrittore;
private String revisore;

private Boolean pubblicato;
public Trascrizione(){
	
}
/**
 * Metodo che rappresenta la trascrizione con i suoi parametri
 * @param testo         Stringa che rappresenta l'oggetto testo
 * @param trascrittore  Stringa che rappresenta l'oggetto trascrittore
 * @param revisore      Stringa che rappresenta l'oggetto revisore
 * @param pubblicato    booleano che rappresenta se la trascrizione è pubblicata o meno 
 */
public Trascrizione(String testo, String trascrittore, String revisore, Boolean pubblicato) {
	this.testo = testo;
	this.trascrittore = trascrittore;
	this.revisore = revisore;
	this.pubblicato = pubblicato;
}
/**
 * Metodo per prendere il testo da una data trascrizine
 * @return String
 */
public String getTesto() {
	return testo;
}
/**
 * Metodo per settare un testo in una nuova trascrizione
 * @param testo Stringa che rappresenta il nuovo testo
 */
public void setTesto(String testo) {
	this.testo = testo;
}
/**
 * Metodo per prendere il trascrittore da una data trascrizine
 * @return String
 */
public String getTrascrittore() {
	return trascrittore;
}
/**
 * Metodo per settare un trascrittore in una nuova trascrizione
 * @param trascrittore Stringa che rappresenta il nuovo trascrittore
 */
public void setTrascrittore(String trascrittore) {
	this.trascrittore = trascrittore;
}
/**
 * Metodo per prendere il revisore da una data trascrizine
 * @return  String
 */
public String getRevisore() {
	return revisore;
}
/**
 * Metodo per settare un revisore in una nuova trascrizione
 * @param revisore Stringa che rappresenta il nuovo revisore
 */
public void setRevisore(String revisore) {
	this.revisore = revisore;
}
/**
 * Metodo per prendere il boolean pubblicato da una data trascrizine
 * @return String
 */
public Boolean getPubblicato() {
	return pubblicato;
}
/**
 * Metodo per settare un boolean pubblicato in una nuova trascrizione
 * @param pubblicato Stringa che rappresenta il nuovo boolean pubblicato
 */
public void setPubblicato(Boolean pubblicato) {
	this.pubblicato = pubblicato;
}
/**
 * Metodo che forma una string con all'interno il trascrittore e il revisore , da mandare a video
 */
@Override
public String toString() {
	return "trascritta da: " + trascrittore + ", revisionata da: " + revisore;
			
}

}
