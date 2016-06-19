package engine;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Gui.AnteprimaTei;
import Gui.Opera;
import Gui.Ricerca;
import data.*;
/**
 * Classe che gestisce tutte le operazioni riguardanti l'opera 
 * @author Luca
 *
 */
public class TitleManager {
	public static TitleManager instance;
	private TitleManager(){	
	}
	
	/**
	 * Metodo che istanzia un oggetto TitleManager se questi non è istanziato, altrimenti ritorna l'istanza dell'oggetto
	 * @return istanza di TitleManager
	 */
	public static TitleManager getInstance(){
		if(instance==null){
			instance= new TitleManager();
		}
		return instance;
	}
	
	/**
	 * Metodo che inizializza la consultazione dell'opera, questi richiama la classe PageDAO per prendere le pagine 
	 * delll'opera dal database.
	 * Questi riceve dal metodo selectPages o un operaGen oppure un operaComp e in entrambi i casi richiama il
	 *  metodo showOperaPage passandogli
	 * i parametri finestra, permesso, username e l'opera ricevuta.
	 * @param finestra finestra di ricerca
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void viewOpera(Ricerca finestra, String permesso, String username){
		String nomeopera= (String)(finestra.comboBox.getSelectedItem());
		OperaGen opera;
		if(nomeopera==null){
			JOptionPane.showMessageDialog (null, "non hai selezionato alcuna opera");
		}
		else{
			PagesDAO b= new PageDAO();
			if(b.selectPages(nomeopera) instanceof OperaComp){
				opera=new OperaComp(null,null,null,null,0, null);
				opera=b.selectPages(nomeopera);
				if((!(opera.getPubblicata())) && (permesso.equals("ac")||permesso.equals("ad"))){
					BufferedImage img=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
					Immagine immaginevuota=new Immagine(img,"","",false);
					Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
					Page perupload=new Page(((OperaComp)opera).getPagTot()+1, immaginevuota, trascrizionevuota);
					((OperaComp)opera).getPagine().add(perupload);
					((OperaComp)opera).setPagTot(((OperaComp)opera).getPagTot()+1);
				}
				Opera Frame= new Opera(opera, permesso, username);
				Frame.setVisible(true);
				TitleManager.getInstance().showOperaPage(opera, Frame, 0, permesso, username);
			}else{
				opera=b.selectPages(nomeopera);
				Opera Frame= new Opera(opera, permesso, username);
				Frame.setVisible(true);
				TitleManager.getInstance().showOperaPage(opera, Frame, 0, permesso, username);
			}
		}	
	}
	
	/**
	 * Questo metodo permette di passare da una pagina dell'opera alla successiva richiamando il metodo showOperaPage.
	 * @param opera opera che si sta visualizzando
	 * @param Frame finestra per la visualizzazione dell'opera
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void showPrev(OperaGen opera, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.currpage.setText(Integer.toString(i-1));
		if(opera instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)opera).getPagTot()));
		TitleManager.getInstance().showOperaPage(((OperaComp)opera), Frame, i-2, permesso, username);
		}
	}
	
	/**
	 * Questo metodo pemrette di passare da una pagina dell'opera alla precedente richiamanto il metodo showOperaPage
	 * @param opera opera che si sta visualizzando
	 * @param Frame finestra per la visualizzazione dell'opera
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void showNext(OperaGen opera, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());	
		if(opera instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)opera).getPagTot()));
		TitleManager.getInstance().showOperaPage(((OperaComp)opera), Frame, i, permesso, username);
		}
	}
	
	/**
	 * Metodo che imposta i contenuti dell'opera all'interno degli appositi spazi. Se riceve in input un 
	 * oggetto di tipo OperaGen, ossia un oggetto senza pagine, questi inizializza una prima pagina 'farlocca'.
	 * Se invece riceve un oggetto operaComp questi inserisce i contenuti della prima pagina negli 
	 * appositi spazi corredando il tutto con delle informazioni. Oltre a fare ciò questo metodo abilita i bottoni a 
	 * seconda del permesso dell'utente.
	 * @param opera opera da visualizzare
	 * @param Frame finestra per visualizzare l'opera
	 * @param pagina numero di pagina da visualizzare
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void showOperaPage(OperaGen opera,Opera Frame, int pagina, String permesso, String username){
		Frame.setTitle(" "+opera.toString());
		if(opera instanceof OperaComp){
			Frame.prev.setEnabled(true);
			Frame.next.setEnabled(true);
			Frame.RevisioneTei.setEnabled(false);
			Frame.RevisioneImg.setEnabled(false);
			Frame.EditTei.setEnabled(false);
			Frame.tei.setEditable(false);
			Frame.UploadImg.setEnabled(false);
			Frame.tei.setContentType("text/html");
			Frame.infotei.setText(((OperaComp)opera).getPagine().get(pagina).getTEI().toString());
			Frame.infoimg.setText(((OperaComp)opera).getPagine().get(pagina).getScan().toString());
			Frame.img.setIcon(new ImageIcon(((OperaComp)opera).getPagine().get(pagina).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
			Frame.tei.setText(((OperaComp)opera).getPagine().get(pagina).getTEI().getTesto());
			
			if((!(((OperaComp)opera).getPagine().get(pagina).getTEI().getPubblicato())) && (permesso.equals("rt") || permesso.equals("ad")) && opera.getPubblicata()==true){
				Frame.RevisioneTei.setEnabled(true);
			}
			if((!(((OperaComp)opera).getPagine().get(pagina).getScan().getPubblicata())) && (permesso.equals("ri") || permesso.equals("ad")) && opera.getPubblicata()==false ){
					Frame.RevisioneImg.setEnabled(true);	
			}
			if((!(((OperaComp)opera).getPagine().get(pagina).getTEI().getPubblicato())) && (permesso.equals("tr") || permesso.equals("ad"))  && opera.getPubblicata()==true){
				Frame.btnAnteprima.setEnabled(true);
				Frame.EditTei.setEnabled(true);
				Frame.tei.setEditable(true);
				Frame.tei.setContentType("text/plain");
				Frame.tei.setText(((OperaComp)opera).getPagine().get(pagina).getTEI().getTesto());
			}
			if((!(((OperaComp)opera).getPagine().get(pagina).getScan().getPubblicata())) && (permesso.equals("ac") || permesso.equals("ad")) && opera.getPubblicata()==false ){
				Frame.UploadImg.setEnabled(true);
			}
			Frame.currpage.setText(Integer.toString(((OperaComp)opera).getPagine().get(pagina).getNumpag()));
			int totalpages=((OperaComp)opera).getPagTot();
			
			Frame.totpage.setText(Integer.toString(totalpages));
			if(pagina==totalpages-1){
				Frame.next.setEnabled(false);
			}
			if(totalpages==1){
				Frame.next.setEnabled(false);
			}
			if(pagina==0){
				Frame.prev.setEnabled(false);
			}
		}
		else{
			ArrayList<Page> primapagina=new ArrayList<Page>();
			opera = new OperaComp(opera.getAutore(), opera.getNomeOpera(), opera.getEpoca(),false, 1, primapagina);
			BufferedImage imgdefault=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
			Immagine nuovaimg=new Immagine(imgdefault, username, "", false);
			Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
			Page paginainiziale= new Page(pagina, nuovaimg, trascrizionevuota);
			((OperaComp)opera).getPagine().add(paginainiziale);
			((OperaComp)opera).getPagine().get(0);
			Frame.tei.setText("nessuna trascrizione");
			Frame.img.setIcon(new ImageIcon(((OperaComp)opera).getPagine().get(0).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
			Frame.prev.setEnabled(false);
			Frame.next.setEnabled(false);
			Frame.currpage.setText("1");
			Frame.totpage.setText("1");
			Frame.infotei.setText("nessuna info");
			Frame.infoimg.setText("nessuna info");
			if(permesso.equals("ac") || permesso.equals("ad")){
				Frame.UploadImg.setEnabled(true);	
			}	
		}	
	}
	
	/**
	 * Questo metodo permette al revisore delle trascrizioni di non accettare il tei visualizzato. 
	 * Esso reimposta il contntType del JTextPanel su "text/html".
	 * @param Frame da cui visualizzare il testo
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void respingiTei(Opera Frame, OperaGen opera, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.tei.setContentType("text/html");
		Frame.ConfermaTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(false);
		Frame.RevisioneTei.setEnabled(true);
		TitleManager.getInstance().showOperaPage(opera, Frame, i-1, permesso, username);
	}
	
	/**
	 * Questo metodo permette al revisore delle immagini di non accettare l'immagine visualizzata.
	 * @param Frame da cui visualizzare l'immagine
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void respingiImg(Opera Frame, OperaGen opera, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.ConfermaImg.setEnabled(false);
		Frame.RifiutaImg.setEnabled(false);
		Frame.RevisioneImg.setEnabled(true);
		TitleManager.getInstance().showOperaPage(opera, Frame, i-1, permesso, username);
		JOptionPane.showMessageDialog (null, "immagine rifiutata");
	}
	
	/**
	 * Metodo che abilita i bottoni per avviare la revisione dell'immagine
	 * @param Frame finestra dove viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void abilitaRevisioneImg(Opera Frame, OperaGen opera, String permesso, String username ){
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaImg.setEnabled(true);
		Frame.ConfermaImg.setEnabled(true);
		Frame.RevisioneImg.setEnabled(false);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		
	}
	
	/**
	 * Metodo che abilita i bottoni per avviare la revisione del tei
	 * @param Frame finestra dover viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso	permesso dell'utente
	 * @param username	username dell'utente
	 */
	public void abilitaRevisioneTei(Opera Frame, OperaGen opera, String permesso, String username ){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(true);
		Frame.ConfermaTei.setEnabled(true);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		Frame.tei.setContentType("text/plain");
		Frame.tei.setText(((OperaComp)opera).getPagine().get(i-1).getTEI().getTesto());
	}
	
	/**
	 * Metdo che permette al revisore delle immagini, richiamando il metodo pubbimg della classe ImmagineDAO,  di 
	 * pubblicare un immagine
	 * @param Frame finestra dove viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void pubblicaImg(Opera Frame, OperaGen opera, String permesso, String username){
		ScanDAO dao= new ImmagineDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		if(dao.pubbImg(i, opera.getNomeOpera(),username)){
			((OperaComp)opera).getPagine().get(i-1).getScan().setPubblicata(true);
			Frame.ConfermaImg.setEnabled(false);
			Frame.RifiutaImg.setEnabled(false);
			TitleManager.getInstance().showOperaPage(opera, Frame, i-1, permesso, username);
			JOptionPane.showMessageDialog (null, "Immagine pubblicata");
			Frame.RevisioneImg.setEnabled(false);
		}
		else{
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	
	/**
	 * Metdo che permette all'acquisitore delle immagini, richiamando il metodo uploadImmagine della classe 
	 * ImmagineDAO,  di uploadare un immagine un immagine.
	 * @param Frame finestra dove viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username	username dell'utente
	 */
	public void uploadImg(Opera Frame, OperaGen opera, String permesso, String username){
			ScanDAO dao=new ImmagineDAO();
			JFileChooser selectimagefile= new JFileChooser();
			File f= null;
			Image img= null;
			int numpag=Integer.parseInt(Frame.currpage.getText());
			BufferedImage importa= null;
			int returnval= selectimagefile.showOpenDialog(Frame);
			if(returnval==JFileChooser.APPROVE_OPTION){
				f=selectimagefile.getSelectedFile();
				try{
					importa= ImageIO.read(f);
					img= importa.getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH);
					InputStream inputStream = new FileInputStream(f);
					if(dao.uploadImmagine(opera.getNomeOpera(), inputStream, Integer.parseInt(Frame.currpage.getText()),username)){
						BufferedImage imgdefault=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
						Immagine nuovaimg=new Immagine(imgdefault,username,"",false);
						Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
						Page nuovapagina= new Page(numpag, nuovaimg, trascrizionevuota );
						Immagine caricata=new Immagine(importa, username,"sconosciuto",false);
						Page paginauploadata= new Page(numpag, caricata, trascrizionevuota );
						if(opera instanceof OperaComp){
							JOptionPane.showMessageDialog(null, "Caricata");
							((OperaComp)opera).getPagine().get(numpag-1).setScan(caricata);
							((OperaComp)opera).getPagine().get(numpag-1).setTei(trascrizionevuota);
							if(numpag==((OperaComp)opera).getPagTot()){
								((OperaComp)opera).addPagina(new Page(numpag+1, nuovaimg, trascrizionevuota));
								((OperaComp)opera).setPagTot(((OperaComp)opera).getPagTot()+1);
							}
							TitleManager.getInstance().showOperaPage(opera, Frame, numpag-1, permesso, username);
							}
						else{
							JOptionPane.showMessageDialog(null, "Opera inizializzata, riconsultare l'opera per continuare");
							Frame.dispose();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Non caricata");
					}
				}catch(Exception h){
					JOptionPane.showMessageDialog(null, h);
				}
			}
	}
	
	/**
	 * Metodo che permette al trascrittore di uplodare il testo scritto grazie al metodo uploadTei della 
	 * classe TrascrizioneDAO
	 * @param Frame finestra dove viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void modificaTei(Opera Frame, OperaGen opera, String permesso, String username){
		TeiDAO dao= new TrascrizioneDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		String temp= Frame.tei.getText();
		if(dao.uploadTei(i, opera.getNomeOpera(), temp, username)){
			((OperaComp)opera).getPagine().get(i-1).getTEI().setTesto(temp);
			JOptionPane.showMessageDialog (null, "Tei caricato correttamente");
			Frame.tei.setContentType("text/html");
			TitleManager.getInstance().showOperaPage(opera, Frame, i-1, permesso, username);
		}
		else{
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	
	/**
	 * Metodo che permette al revisore delle trascrizioni di pubblicare il tei grazie al metodo pubbTei 
	 * della classe TrascrizioneDAO
	 * @param Frame finestra dove viene visualizzata l'opera
	 * @param opera opera visualizzata
	 * @param permesso permesso dell'utente
	 * @param username username dell'utente
	 */
	public void pubblicaTei(Opera Frame, OperaGen opera, String permesso, String username){
		TeiDAO dao= new TrascrizioneDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		if(dao.pubbTei(i, opera.getNomeOpera(), username)){
			((OperaComp)opera).getPagine().get(i-1).getTEI().setPubblicato(true);
			Frame.tei.setContentType("text/html");
			Frame.ConfermaTei.setEnabled(false);
			Frame.RifiutaTei.setEnabled(false);
			JOptionPane.showMessageDialog (null, "TEI pubblicato");
			TitleManager.getInstance().showOperaPage(opera, Frame, i-1, permesso, username);
		}
		else{
			JOptionPane.showMessageDialog (null, "TEI non pubblicato, riprova");
		}
	}
	
	/**
	 * Metodo che permette al trascrittore di visualizzare un anteprima del tei che sta scrivendo
	 * @param Frame
	 */
	public void anteprimaTei(Opera Frame){
		AnteprimaTei Finestra = new AnteprimaTei();
		Finestra.setVisible(true);
		Finestra.anteprima.setText(Frame.tei.getText());
	}
	
	
	
	
}	
