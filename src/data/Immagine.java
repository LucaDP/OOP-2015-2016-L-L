package data;


import java.awt.image.BufferedImage;
/**
 * Classe che rappresenta l'immagine
 * @author Luca
 *
 */
public class Immagine {
private BufferedImage pagina;
private String acquisitore;
private String revisore;
private Boolean pubblicata;

/**
 * Costruttore della classe Immagine
 * @param pagina pagina relativa all'immagine
 * @param acquisitore username dell'acquisitore dell'immagine
 * @param revisore username del revisoore dell'immagine
 * @param pubblicata booleano che indica se l'immagine è pubblicata o meno
 */
public Immagine(BufferedImage pagina, String acquisitore, String revisore, boolean pubblicata) {
	this.pagina = pagina;
	this.acquisitore = acquisitore;
	this.revisore = revisore;
	this.pubblicata = pubblicata;
}
/**
 * Metodo per prendere la BufferdImage
 * @return BufferedImage
 */
public BufferedImage getPagina() {
	return pagina;
}
/**
 * Metodo per settare la BufferedImage
 * @param pagina la nuova immagine
 */
public void setPagina(BufferedImage pagina) {
	this.pagina = pagina;
}

/**
 * Metodo per prendere il nome dell'acquisitore
 * @return String 
 */
public String getAcquisitore() {
	return acquisitore;
}
/**
 * Metodo per settare l'acquisitore
 * @param acquisitore nome dell'acquisitore
 */
public void setAcquisitore(String acquisitore) {
	this.acquisitore = acquisitore;
}
/**
 * Metodo per prendere il nome del revisore
 * @return String
 */
public String getRevisore() {
	return revisore;
}
/**
 * Metodo per settare il revisore
 * @param revisore username del revisore
 */
public void setRevisore(String revisore) {
	this.revisore = revisore;
}
/**
 * Metodo per controllare se l'Immagine è pubblicata o meno 
 * @return boolean
 */
public boolean getPubblicata() {
	return pubblicata;
}
/**
 * Metodo per settare l'attributo pubblicata
 * @param pubblicata pubblicata true/false
 */
public void setPubblicata(Boolean pubblicata) {
	this.pubblicata = pubblicata;
}
/**
 * Metodo che stampa le info sull'acquisitore e il revisore
 */
@Override
public String toString() {
	return "acquisita da: " + acquisitore + ", revisionata da: " + revisore;
			
}

}
