package engine;


import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
		System.out.println(a);
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
		
		
	
	
	
	public void viewOpera(Ricerca finestra, String permesso) throws Exception{
		String nomeopera= (String)(finestra.comboBox.getSelectedItem());
		System.out.println(nomeopera);
		OperaComp a;
		if(nomeopera==null){
		
			JOptionPane.showMessageDialog (null, "non hai selezionato alcuna opera");
		}
		else{
			pagesDAO b= new pageDAO();
			a=b.selectPages(nomeopera);
			
			Opera Frame= new Opera(a, permesso);
			
			Frame.setVisible(true);
			
			TitleManager.showOperaPage(a, Frame, 0, permesso);
			
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
	public static void showPrev(OperaComp a, Opera Frame, String permesso){
		int i=Integer.parseInt(Frame.currpage.getText());
		TitleManager.showOperaPage(a, Frame, i-2, permesso);
	}
	
	public static void showNext(OperaComp a, Opera Frame, String permesso){
		int i=Integer.parseInt(Frame.currpage.getText());
		TitleManager.showOperaPage(a, Frame, i, permesso);
	}
	
	public static void showOperaPage(OperaComp a,Opera Frame, int i, String permesso){
		Frame.prev.setEnabled(true);
		Frame.next.setEnabled(true);
		Frame.RevisioneTei.setEnabled(false);
		Frame.RevisioneImg.setEnabled(false);
		Frame.EditTei.setEnabled(false);
		Frame.tei.setEditable(false);
		Frame.UploadImg.setEnabled(false);
		Frame.tei.setContentType("text/html");
		/*setta img e tei*/
		Frame.img.setIcon(new ImageIcon(a.getPagine().get(i).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
		Frame.tei.setText(a.getPagine().get(i).getTEI().getTesto());
		System.out.println(a.getPagine().get(i).getTEI().getTesto());
		
		
		/*Abilita i bottoni a seconda del permesso e dello stato "pubblicato" del tei o dell'immagine*/
		if((!(a.getPagine().get(i).getTEI().getPubblicato())) && (permesso.equals("rt") || permesso.equals("ad"))){
			Frame.RevisioneTei.setEnabled(true);
		}
		if((!(a.getPagine().get(i).getScan().getPubblicata())) && (permesso.equals("ri") || permesso.equals("ad")) ){
			Frame.RevisioneImg.setEnabled(true);
		}
		
		if((!(a.getPagine().get(i).getTEI().getPubblicato())) && (permesso.equals("tr") || permesso.equals("ad")) ){
			Frame.EditTei.setEnabled(true);
			Frame.tei.setEditable(true);
			Frame.tei.setContentType("text/plain");
			Frame.tei.setText(a.getPagine().get(i).getTEI().getTesto());
		}else{
			/*Frame.EditTei.setEnabled(false);
			String tei=Frame.tei.getText();
			Frame.tei.setEditable(true);
			Frame.tei.setContentType("text/html");
			Frame.tei.setText(tei);*/
			
		}
		if((!(a.getPagine().get(i).getScan().getPubblicata())) && (permesso.equals("ac") || permesso.equals("ad")) ){
			Frame.UploadImg.setEnabled(true);
		}
		
		
		
		Frame.currpage.setText(Integer.toString(a.getPagine().get(i).getNumpag()));
		int totalpages=a.getPagTot();
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
	
	public void abilitaRevisione(Opera Frame, OperaComp a){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.RevisioneTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(true);
		Frame.ConfermaTei.setEnabled(true);
		Frame.prev.setEnabled(false);
		Frame.next.setEnabled(false);
		Frame.tei.setContentType("text/plain");
		Frame.tei.setText(a.getPagine().get(i-1).getTEI().getTesto());
	}
	
	
	public void pubblicaTei(Opera Frame, OperaComp a, String permesso) throws Exception{
		operaDAO b= new operaDAO();
		/**TEI PUBBLICATO**/
		/*sia nel db che sull'oggetto in locale*/
		int i=Integer.parseInt(Frame.currpage.getText());

		if(b.pubbTei(i, a.getNomeOpera())/*db*/){
			/*locale*/
			a.getPagine().get(i-1).getTEI().setPubblicato(true);
			Frame.tei.setContentType("text/html");
			Frame.ConfermaTei.setEnabled(false);
			Frame.RifiutaTei.setEnabled(false);
			TitleManager.showOperaPage(a, Frame, i-1, permesso);
		}
		else{
		/**altrimenti**/
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
	}
	
	public void respingiTei(Opera Frame, OperaComp a, String permesso){
		int i=Integer.parseInt(Frame.currpage.getText());
		Frame.tei.setContentType("text/html");
		Frame.ConfermaTei.setEnabled(false);
		Frame.RifiutaTei.setEnabled(false);
		Frame.RevisioneTei.setEnabled(true);
		TitleManager.showOperaPage(a, Frame, i-1, permesso);
	}
	
	public void modificaTei(Opera Frame, OperaComp a, String permesso) throws Exception{
		operaDAO b= new operaDAO();
		int i=Integer.parseInt(Frame.currpage.getText());
		String temp= Frame.tei.getText();
		
		
		if(b.uploadTei(i, a.getNomeOpera(), temp)){
			/*modifica in locale*/
			a.getPagine().get(i-1).getTEI().setTesto(temp);
			System.out.println(a.getPagine().get(i-1).getTEI().getTesto());
			JOptionPane.showMessageDialog (null, "Tei caricato correttamente");
			Frame.tei.setContentType("text/html");
			TitleManager.showOperaPage(a, Frame, i-1, permesso);
		}
		else{
			JOptionPane.showMessageDialog (null, "qualcosa è andato storto, riprova");
		}
			
		
	}
	
	public void uploadImg(Opera Frame, OperaComp a, String permesso){
		operaDAO b=new operaDAO();
		JFileChooser selectimagefile= new JFileChooser();
		File f= null;
		Image img= null;
		BufferedImage importa= null;
		int returnval= selectimagefile.showOpenDialog(Frame);
		if(returnval==JFileChooser.APPROVE_OPTION){
			f=selectimagefile.getSelectedFile();
			try{
				importa= ImageIO.read(f);
				img= importa.getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH);
				InputStream inputStream = new FileInputStream(f);
				if(b.uploadScan(a.getNomeOpera(), inputStream, Integer.parseInt(Frame.currpage.getText()))){
					JOptionPane.showMessageDialog(null, "Caricata");
				}
				else{
					JOptionPane.showMessageDialog(null, "Non caricata");
				}
				
			}catch(Exception h){
				JOptionPane.showMessageDialog(null, "Deve essere un file immagine");
				return;
			}
		}
	
	}
}	
