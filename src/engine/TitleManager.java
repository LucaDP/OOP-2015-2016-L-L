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

import UIPackage.BackOffice;
import UIPackage.Opera;
import UIPackage.Ricerca;

import data.*;

public class TitleManager {
	public static TitleManager instance;
	
	private TitleManager(){
		
	}
	/**
	 * se è stato già istanziato un oggetto TitleManager si utilizza quello, altrimenti si istanzia un nuovo oggetto TitleManager
	 * @return TitleManager instance
	 */
	public static TitleManager getInstance(){
		if(instance==null){
			instance= new TitleManager();
			
		}
		return instance;
	}
	
	public void searchOpera(Ricerca finestra) throws Exception{
		String ricerca=finestra.textField.getText();
		Boolean a= !(finestra.operenonpubb.isSelected());
		//System.out.println(a);
		if(finestra.comboBox.getItemCount()!=0){
			finestra.comboBox.removeAllItems();
		}
		
		if("".equals(ricerca)){
			JOptionPane.showMessageDialog (null, "inserisci qualcosa prima di fare la ricerca");	
		}
		else{
			
			titleDAO<OperaGen> b= new operaDAO();
			ArrayList<OperaGen> listaopere= new ArrayList<OperaGen>();
			listaopere.addAll(b.selectOpera(ricerca, a));
			if(listaopere.size()==0){
				JOptionPane.showMessageDialog (null, "la ricerca non ha prodotto alcun risultato");
			}
			else{
				for (OperaGen object: listaopere) {
					finestra.comboBox.addItem(object.getNomeOpera());
				}
			}
			
			
		}
	}
		
		
	
	
	
	public void viewOpera(Ricerca finestra, String permesso, String username) throws Exception{
		finestra.setTitle("");
		String nomeopera= (String)(finestra.comboBox.getSelectedItem());
		
		//System.out.println(nomeopera);
		
		OperaGen a;
		if(nomeopera==null){
		
			JOptionPane.showMessageDialog (null, "non hai selezionato alcuna opera");
		}
		else{
			pagesDAO b= new pageDAO();
			/*a è uguale o a opera gen o a opera comp*/
			if(b.selectPages(nomeopera) instanceof OperaComp){
				a=new OperaComp(null,null,null,null,0, null);
				a=b.selectPages(nomeopera);
				//System.out.println(a.getPubblicata()+"ECCOLAAAAA");
				if(!(a.getPubblicata())){
					BufferedImage img=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
					Immagine immaginevuota=new Immagine(img,"","",false);
					Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
					Page perupload=new Page(((OperaComp)a).getPagTot()+1, immaginevuota, trascrizionevuota);
					((OperaComp)a).getPagine().add(perupload);
					((OperaComp)a).setPagTot(((OperaComp)a).getPagTot()+1);
				}
				//System.out.println(((OperaComp)a).getPagTot()+"ECCO LE PAGINE");
				Opera Frame= new Opera(a, permesso, username);
				Frame.setVisible(true);
				
				TitleManager.getInstance().showOperaPage(a, Frame, 0, permesso, username);
			}else{
				a=b.selectPages(nomeopera);
				Opera Frame= new Opera(a, permesso, username);
				Frame.setVisible(true);
				TitleManager.getInstance().showOperaPage(a, Frame, 0, permesso, username);
				
			}
			
			
		
			
			
			
			
			
			
		}
		
		
	}
	
	/**********************************************************************************************/
	public void pubbopera(BackOffice finestra) throws Exception{
		String nomeoperadapubb= (String) finestra.comboBox_2.getSelectedItem();
		
		
		
		if(nomeoperadapubb==null ){
			JOptionPane.showMessageDialog (null, "Il campo opera è vuoto");
		}
		else{
			 operaDAO b= new operaDAO();
		     //b.creareopera(titoloopera,autore,epoca);
		     b.pubbopera(nomeoperadapubb);
		     JOptionPane.showMessageDialog (null, "OPERA PUBBLICATA");
	}
}
/**********************************************************************************************/
	/*******************DA SISTEMARE**************/
	public void searchOperaBO(BackOffice finestra) throws Exception{
		String ricerca=finestra.textField_5.getText();
		
		if(finestra.comboBox_2.getItemCount()!=0){
			finestra.comboBox_2.removeAllItems();
		}
		
		if("".equals(ricerca)){
			JOptionPane.showMessageDialog (null, "inserisci qualcosa prima di fare la ricerca");	
		}
		else{
			
			titleDAO<OperaGen> b= new operaDAO();
			ArrayList<OperaGen> listaopere= new ArrayList<OperaGen>();
			listaopere.addAll(b.selectOpera(ricerca,false));
			if(listaopere.size()==0){
				JOptionPane.showMessageDialog (null, "la ricerca non ha prodotto alcun risultato");
			}
			else{
				for (OperaGen object: listaopere) {
					finestra.comboBox_2.addItem(object.getNomeOpera());
				}
			}
			
			
		}
	}
	
	public void viewAdmin(Ricerca finestra, String username, String permesso) throws Exception{
		BackOffice frame = new BackOffice();
		frame.setVisible(true);
	}
/**********************************************************************************************/
	public void createopera(BackOffice finestra) throws Exception{
		String titoloopera= finestra.textField_2.getText();
		String autore= finestra.textField_3.getText();
		String epoca= finestra.textField_4.getText();
	    
		
		
		if(titoloopera==null ){
			
			JOptionPane.showMessageDialog (null, "Inserisci il nome di un opera");
		}
		else{
			 operaDAO b= new operaDAO();
		     b.creareopera(titoloopera,autore,epoca);
		     JOptionPane.showMessageDialog (null, "TITOLO OPERA INSERITO NEL DATABASE");
	
		}
	}
	
	
	
	/**PASSARE OPERA COMP? MEGLIO OPERAGEN NO? A e B sottoclasse di A, con instance of su B faccio in modo di non applicare i metodi (get tot page etc etc che OperaGen non ha) su Oggetti di tipo A**/
	public static void showPrev(OperaGen a, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.currpage.setText(Integer.toString(i-1));
		if(a instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)a).getPagTot()));
		
		TitleManager.getInstance().showOperaPage(((OperaComp)a), Frame, i-2, permesso, username);
		}
	}
	
	
	
	public static void showNext(OperaGen a, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());	
		System.out.println(a.getClass());
		System.out.println("showNext PREINOF/n");
		if(a instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)a).getPagTot()));
		System.out.println("showNext INOF/n");
		System.out.println(((OperaComp)a).getNomeOpera());
		System.out.println(((OperaComp)a).getPagTot());
		System.out.println(((OperaComp)a).getPagine().isEmpty());
		
		TitleManager.getInstance().showOperaPage(((OperaComp)a), Frame, i, permesso, username);
		
		}
	}
	
	
	
	
	
	/*prende opera gen*/
	public static void showOperaPage(OperaGen a,Opera Frame, int i, String permesso, String username){
		
		
	System.out.println("in show opera è un oggetto di tipo:" +a.getClass());
		
		/*se a è un istanza di operaComp allora fai questo, altrimenti altrimenti se è un opera Gen questa roba non la fa*/
		if(a instanceof OperaComp){
			//Frame.currpage.setText(Integer.toString(i+1));
			//Frame.totpage.setText(Integer.toString(((OperaComp)a).getPagTot()));
			Frame.prev.setEnabled(true);
			Frame.next.setEnabled(true);
			Frame.RevisioneTei.setEnabled(false);
			Frame.RevisioneImg.setEnabled(false);
			Frame.EditTei.setEnabled(false);
			Frame.tei.setEditable(false);
			Frame.UploadImg.setEnabled(false);
			Frame.tei.setContentType("text/html");
		/*setta img e tei*/
			
			Frame.img.setIcon(new ImageIcon(((OperaComp)a).getPagine().get(i).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
			Frame.tei.setText(((OperaComp)a).getPagine().get(i).getTEI().getTesto());
			//System.out.println(((OperaComp)a).getPagine().get(i).getTEI().getTesto());
		
		
		/*Abilita i bottoni a seconda del permesso e dello stato "pubblicato" del tei o dell'immagine*/
			if((!(((OperaComp)a).getPagine().get(i).getTEI().getPubblicato())) && (permesso.equals("rt") || permesso.equals("ad")) && a.getPubblicata()==true){
				Frame.RevisioneTei.setEnabled(true);
			}
			if((!(((OperaComp)a).getPagine().get(i).getScan().getPubblicata())) && (permesso.equals("ri") || permesso.equals("ad")) && a.getPubblicata()==false ){
				Frame.RevisioneImg.setEnabled(true);
			}
		
			if((!(((OperaComp)a).getPagine().get(i).getTEI().getPubblicato())) && (permesso.equals("tr") || permesso.equals("ad"))  && a.getPubblicata()==true){
				Frame.EditTei.setEnabled(true);
				Frame.tei.setEditable(true);
				Frame.tei.setContentType("text/plain");
				Frame.tei.setText(((OperaComp)a).getPagine().get(i).getTEI().getTesto());
			}
			if((!(((OperaComp)a).getPagine().get(i).getScan().getPubblicata())) && (permesso.equals("ac") || permesso.equals("ad")) && a.getPubblicata()==false ){
				Frame.UploadImg.setEnabled(true);
			}
		
		
		
			Frame.currpage.setText(Integer.toString(((OperaComp)a).getPagine().get(i).getNumpag()));
			int totalpages=((OperaComp)a).getPagTot();
			//System.out.println(totalpages);
			Frame.totpage.setText(Integer.toString(totalpages));
			/*gestione prev e next*/
			if(i==totalpages-1){
				Frame.next.setEnabled(false);
			}
			if(totalpages==1){
				Frame.next.setEnabled(false);
			}
			if(i==0){
				Frame.prev.setEnabled(false);
			}
		}
		else{
			ArrayList<Page> primapagina=new ArrayList<Page>();
			a = new OperaComp(a.getAutore(), a.getNomeOpera(), a.getEpoca(),false, 1, primapagina);
			
			BufferedImage imgdefault=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
			Immagine nuovaimg=new Immagine(imgdefault, username, "", false);
			Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
			Page paginainiziale= new Page(i, nuovaimg, trascrizionevuota);
			((OperaComp)a).getPagine().add(paginainiziale);
			((OperaComp)a).getPagine().get(0);
			Frame.tei.setText("nessuna trascrizione");
			Frame.img.setIcon(new ImageIcon(((OperaComp)a).getPagine().get(0).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
			Frame.prev.setEnabled(false);
			Frame.next.setEnabled(false);
			Frame.currpage.setText("1");
			Frame.totpage.setText("1");
			if(permesso.equals("ri") || permesso.equals("ad")){
				Frame.RevisioneImg.setEnabled(true);
			}
			if(permesso.equals("ac") || permesso.equals("ad")){
				Frame.UploadImg.setEnabled(true);	
			}
			
		}
		
	}
	
	public void abilitaRevisione(Opera Frame, OperaGen a, String permesso, String username ){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(true);
		Frame.ConfermaTei.setEnabled(true);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		Frame.tei.setContentType("text/plain");
		Frame.tei.setText(((OperaComp)a).getPagine().get(i-1).getTEI().getTesto());
	}
	
	
	public void pubblicaTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		operaDAO b= new operaDAO();
		/**TEI PUBBLICATO**/
		/*sia nel db che sull'oggetto in locale*/
		int i=Integer.parseInt(Frame.currpage.getText());

		if(b.pubbTei(i, a.getNomeOpera())/*db*/){
			/*locale*/
			((OperaComp)a).getPagine().get(i-1).getTEI().setPubblicato(true);
			Frame.tei.setContentType("text/html");
			Frame.ConfermaTei.setEnabled(false);
			Frame.RifiutaTei.setEnabled(false);
			TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
		}
		else{
		/**altrimenti**/
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	
	public void respingiTei(Opera Frame, OperaGen a, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.tei.setContentType("text/html");
		Frame.ConfermaTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(false);
		Frame.RevisioneTei.setEnabled(true);
		TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
	}
	
	public void modificaTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		operaDAO b= new operaDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		String temp= Frame.tei.getText();
		
		
		if(b.uploadTei(i, a.getNomeOpera(), temp)){
			/*modifica in locale*/
			((OperaComp)a).getPagine().get(i-1).getTEI().setTesto(temp);
			//System.out.println(((OperaComp)a).getPagine().get(i-1).getTEI().getTesto());
			JOptionPane.showMessageDialog (null, "Tei caricato correttamente");
			Frame.tei.setContentType("text/html");
			TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
		}
		else{
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
			
		
	}
	
	public void uploadImg(Opera Frame, OperaGen a, String permesso, String username){
		//OperaComp opera=new OperaComp(a.getAutore(), a.getNomeOpera(), a.getEpoca(), a.getPubblicata(), 2, new ArrayList<Page>() );
		operaDAO b=new operaDAO();
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
				
				if(b.uploadScan(a.getNomeOpera(), inputStream, Integer.parseInt(Frame.currpage.getText()),username)){
					
					
					BufferedImage imgdefault=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
					Immagine nuovaimg=new Immagine(imgdefault,username,"",false);
					Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
					
					Page nuovapagina= new Page(numpag, nuovaimg, trascrizionevuota );
					Immagine caricata=new Immagine(importa, username,"sconosciuto",false);
					Page paginauploadata= new Page(numpag, caricata, trascrizionevuota );
					

					if(a instanceof OperaComp){
						JOptionPane.showMessageDialog(null, "Caricata");
						((OperaComp)a).getPagine().get(numpag-1).setScan(caricata);
						((OperaComp)a).getPagine().get(numpag-1).setTei(trascrizionevuota);
						
						
						if(numpag==((OperaComp)a).getPagTot()){
							((OperaComp)a).addPagina(new Page(numpag+1, nuovaimg, trascrizionevuota));
							((OperaComp)a).setPagTot(((OperaComp)a).getPagTot()+1);
						}
						TitleManager.getInstance().showOperaPage(a, Frame, numpag-1, permesso, username);
						}
					else{
						JOptionPane.showMessageDialog(null, "Opera inizializzata, riconsultare l'opera per continuare");
						//ArrayList<Page> pagine=new ArrayList<Page>();
						//Page pag=paginauploadata;
						//pagine.add(pag);
						//Page newpag=nuovapagina;
						//pagine.add(newpag);
						//opera.addPagina(paginauploadata);
						//opera.addPagina(nuovapagina);
						//System.out.println(opera.getNomeOpera());
						//System.out.println(opera.getPagTot());
						//System.out.println(opera.getPagine().isEmpty());
						
						Frame.dispose();
						
						//TitleManager.getInstance().showOperaPage(opera, Frame, 0, permesso, username);
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
	
	public void abilitaRevisioneImg(Opera Frame, OperaGen a, String permesso, String username ){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaImg.setEnabled(true);
		Frame.ConfermaImg.setEnabled(true);
		Frame.RevisioneImg.setEnabled(false);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		
	}
	public void respingiImg(Opera Frame, OperaGen a, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		//Frame.tei.setContentType("text/html");
		Frame.ConfermaImg.setEnabled(false);
		Frame.RifiutaImg.setEnabled(false);
		Frame.RevisioneImg.setEnabled(true);
		TitleManager.showOperaPage(a, Frame, i-1, permesso, username);
		JOptionPane.showMessageDialog (null, "immagine rifiutata");
	}
	public void pubblicaImg(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		operaDAO b= new operaDAO();
		/**TEI PUBBLICATO**/
		/*sia nel db che sull'oggetto in locale*/
		int i=Integer.parseInt(Frame.currpage.getText());

		if(b.pubbImg(i, a.getNomeOpera(),username)){
			/*locale*/
			((OperaComp)a).getPagine().get(i-1).getScan().setPubblicata(true);
			//Frame.tei.setContentType("text/html");
			
			Frame.ConfermaImg.setEnabled(false);
			Frame.RifiutaImg.setEnabled(false);
			
			TitleManager.showOperaPage(a, Frame, i-1, permesso, username);
			JOptionPane.showMessageDialog (null, "OK");
			Frame.RevisioneImg.setEnabled(false);
		}
		else{
		/**altrimenti**/
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	
	
	
	
}	
