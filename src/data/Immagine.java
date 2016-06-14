package data;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Immagine {
private BufferedImage pagina;
private String acquisitore;
private String revisore;
private Boolean pubblicata;

public Immagine(){
	
}
public Immagine(BufferedImage pagina, String acquisitore, String revisore, Boolean pubblicata) {
	this.pagina = pagina;
	this.acquisitore = acquisitore;
	this.revisore = revisore;
	this.pubblicata = pubblicata;
}
public BufferedImage getPagina() {
	return pagina;
}
public void setPagina(BufferedImage pagina) {
	this.pagina = pagina;
}

public String toString() {
	return "Immagine [acquisitore=" + acquisitore + ", revisore=" + revisore + ", pubblicata=" + pubblicata + "]";
}
public String getAcquisitore() {
	return acquisitore;
}
public void setAcquisitore(String acquisitore) {
	this.acquisitore = acquisitore;
}
public String getRevisore() {
	return revisore;
}
public void setRevisore(String revisore) {
	this.revisore = revisore;
}
public Boolean getPubblicata() {
	return pubblicata;
}
public void setPubblicata(Boolean pubblicata) {
	this.pubblicata = pubblicata;
}

}
