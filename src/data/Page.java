package data;

/**
 * Classe che descrive la struttura di una pagina
 * @author Luca
 *
 */
public class Page {
private int numpag;
private Immagine img;
private Trascrizione TEI;
/**
 * Costruttore della classe Page
 * @param numpag numero della pagina
 * @param img Immagine associata alla pagina
 * @param TEI trascrizione associata alla pagina
 */
public Page(int numpag, Immagine img, Trascrizione TEI) {
	this.numpag = numpag;
	this.img = img;
	this.TEI = TEI;
}
/**
 * Metodo per prendere il nome della pagina
 * @return int numero di pagina
 */
public int getNumpag() {
	return numpag;
}
/**
 * Metodo per settare il numero della pagina
 * @param numpag numerod ella pagina
 */
public void setNumpag(int numpag) {
	this.numpag = numpag;
}
/**
 * Metodo per prendere L'Immagine 
 * @return Immagine  Immagine della pagina
 */
public Immagine getScan() {
	return img;
}
/**
 * Metodo per settare l'Immagine della pagina
 * @param scan nuova Immagine
 */
public void setScan(Immagine scan) {
	this.img = scan;
}
/**
 * Metodo per ottenere la Trascrizione legata alla pagina
 * @return Trascrizione 
 */
public Trascrizione getTEI() {
	return TEI;
}
/**
 * Metodo per settare la Trascrizione legata alla pagina
 * @param TEI Trascrizione
 */
public void setTei(Trascrizione TEI) {
	this.TEI = TEI;
}
/**
 * Metodo che riporta le informazioni della Pagina
 */
@Override
public String toString() {
	return "Pagina numero: "+numpag+", "+img.toString()+" "+TEI.toString();
}
}
