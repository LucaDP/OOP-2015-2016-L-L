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
	
	public static TitleManager getInstance(){
		if(instance==null){
			instance= new TitleManager();
			
		}
		return instance;
	}
	
	
	public void viewOpera(Ricerca finestra, String permesso, String username) throws Exception{
		String nomeopera= (String)(finestra.comboBox.getSelectedItem());
		OperaGen a;
		if(nomeopera==null){
			JOptionPane.showMessageDialog (null, "non hai selezionato alcuna opera");
		}
		else{
			PagesDAO b= new PageDAO();
			/******VEDERE SE RISOLVO***/
			//Object x=b.selectPages(nomeopera);
			if(b.selectPages(nomeopera) instanceof OperaComp){
				a=new OperaComp(null,null,null,null,0, null);
				a=b.selectPages(nomeopera);
		;
				if((!(a.getPubblicata())) && (permesso.equals("ac")||permesso.equals("ad"))){
					BufferedImage img=new BufferedImage(475,575,BufferedImage.TYPE_INT_RGB);
					Immagine immaginevuota=new Immagine(img,"","",false);
					Trascrizione trascrizionevuota= new Trascrizione("nessuna trascrizione","sconosciuto","sconosciuto", false);
					Page perupload=new Page(((OperaComp)a).getPagTot()+1, immaginevuota, trascrizionevuota);
					((OperaComp)a).getPagine().add(perupload);
					((OperaComp)a).setPagTot(((OperaComp)a).getPagTot()+1);
				}
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
	

	public void showPrev(OperaGen a, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.currpage.setText(Integer.toString(i-1));
		if(a instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)a).getPagTot()));
		TitleManager.getInstance().showOperaPage(((OperaComp)a), Frame, i-2, permesso, username);
		}
	}
	

	public void showNext(OperaGen a, Opera Frame, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());	
		if(a instanceof OperaComp){
		Frame.totpage.setText(Integer.toString(((OperaComp)a).getPagTot()));
		TitleManager.getInstance().showOperaPage(((OperaComp)a), Frame, i, permesso, username);
		}
	}
	

	public  void showOperaPage(OperaGen a,Opera Frame, int i, String permesso, String username){
		Frame.setTitle(" "+a.toString());
		if(a instanceof OperaComp){
			
			Frame.prev.setEnabled(true);
			Frame.next.setEnabled(true);
			Frame.RevisioneTei.setEnabled(false);
			Frame.RevisioneImg.setEnabled(false);
			Frame.EditTei.setEnabled(false);
			Frame.tei.setEditable(false);
			Frame.UploadImg.setEnabled(false);
			Frame.tei.setContentType("text/html");
			
			Frame.img.setIcon(new ImageIcon(((OperaComp)a).getPagine().get(i).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
			Frame.tei.setText(((OperaComp)a).getPagine().get(i).getTEI().getTesto());

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
			
			Frame.totpage.setText(Integer.toString(totalpages));
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
			
			if(permesso.equals("ac") || permesso.equals("ad")){
				Frame.UploadImg.setEnabled(true);	
			}
			
		}
		
	}

	/****** MODIFICA FINESTRA OPERA***///
	public void respingiTei(Opera Frame, OperaGen a, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.tei.setContentType("text/html");
		Frame.ConfermaTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(false);
		Frame.RevisioneTei.setEnabled(true);
		TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
	}
	
	public void respingiImg(Opera Frame, OperaGen a, String permesso, String username){
		int i=Integer.parseInt(Frame.currpage.getText());
		
		Frame.ConfermaImg.setEnabled(false);
		Frame.RifiutaImg.setEnabled(false);
		Frame.RevisioneImg.setEnabled(true);
		TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
		JOptionPane.showMessageDialog (null, "immagine rifiutata");
	}

	public void abilitaRevisioneImg(Opera Frame, OperaGen a, String permesso, String username ){
		//int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaImg.setEnabled(true);
		Frame.ConfermaImg.setEnabled(true);
		Frame.RevisioneImg.setEnabled(false);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		
	}
	
	public void abilitaRevisioneTei(Opera Frame, OperaGen a, String permesso, String username ){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(true);
		Frame.ConfermaTei.setEnabled(true);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		Frame.tei.setContentType("text/plain");
		Frame.tei.setText(((OperaComp)a).getPagine().get(i-1).getTEI().getTesto());
	}
	
	
	
	
	
	/******************CHE USANO IL DAO********************************************************/
	
	public void pubblicaImg(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		ScanDAO dao= new ImmagineDAO();
		
		
		int i=Integer.parseInt(Frame.currpage.getText());

		if(dao.pubbImg(i, a.getNomeOpera(),username)){
			
			((OperaComp)a).getPagine().get(i-1).getScan().setPubblicata(true);
			
			
			Frame.ConfermaImg.setEnabled(false);
			Frame.RifiutaImg.setEnabled(false);
			
			TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
			JOptionPane.showMessageDialog (null, "Immagine pubblicata");
			Frame.RevisioneImg.setEnabled(false);
		}
		else{
		
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	public void uploadImg(Opera Frame, OperaGen a, String permesso, String username){
			
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
					
					if(dao.uploadImmagine(a.getNomeOpera(), inputStream, Integer.parseInt(Frame.currpage.getText()),username)){
						
						
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
	public void modificaTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		TeiDAO dao= new TrascrizioneDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		String temp= Frame.tei.getText();
		
		
		if(dao.uploadTei(i, a.getNomeOpera(), temp, username)){
			
			((OperaComp)a).getPagine().get(i-1).getTEI().setTesto(temp);
			
			JOptionPane.showMessageDialog (null, "Tei caricato correttamente");
			Frame.tei.setContentType("text/html");
			TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
		}
		else{
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
			
		
	}
	
	public void pubblicaTei(Opera Frame, OperaGen a, String permesso, String username) throws Exception{
		 TeiDAO dao= new TrascrizioneDAO();
	
		
		int i=Integer.parseInt(Frame.currpage.getText());

		if(dao.pubbTei(i, a.getNomeOpera(), username)){
			
			((OperaComp)a).getPagine().get(i-1).getTEI().setPubblicato(true);
			Frame.tei.setContentType("text/html");
			Frame.ConfermaTei.setEnabled(false);
			Frame.RifiutaTei.setEnabled(false);
			JOptionPane.showMessageDialog (null, "TEI pubblicato");
			TitleManager.getInstance().showOperaPage(a, Frame, i-1, permesso, username);
		}
		else{
		
			JOptionPane.showMessageDialog (null, "TEI non pubblicato, riprova");
		}
	}
	
	
	
	
	
}	
