package data;

public class Page {
private int numpag;
private Immagine img;
private Trascrizione TEI;

public Page(int numpag, Immagine img, Trascrizione tEI) {
	this.numpag = numpag;
	this.img = img;
	TEI = tEI;
}
public int getNumpag() {
	return numpag;
}
public void setNumpag(int numpag) {
	this.numpag = numpag;
}
public Immagine getScan() {
	return img;
}
public void setScan(Immagine scan) {
	this.img = scan;
}
public Trascrizione getTEI() {
	return TEI;
}

public void setTei(Trascrizione TEI) {
	this.TEI = TEI;
}
@Override
public String toString() {
	return "Pagina numero: "+numpag+", "+img.toString()+" "+TEI.toString();
}
}
