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
@Override
public String toString() {
	return "Page [numpag=" + numpag + ", img=" + img + ", TEI=" + TEI + "]";
}
public void setTEI(Trascrizione TEI) {
	TEI = TEI;
}
}
