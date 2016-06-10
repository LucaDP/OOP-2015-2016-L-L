package engine;


import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
			
			Opera Frame= new Opera(a);
			Frame.setVisible(true);
			switch(permesso){
				case "ad":		
								break;
								
								
								
				case "ua":		for(int k = 0; k < a.getPagine().size(); k++) {   
									System.out.println(a.getPagine().get(k));
								}  
				System.out.println(a.getPagTot());
								Frame.img.setIcon(new ImageIcon(a.getPagine().get(0).getScan().getPagina().getScaledInstance(475, 565, java.awt.Image.SCALE_SMOOTH)));
								Frame.currpage.setText(Integer.toString(a.getPagine().get(0).getNumpag()));
								
								Frame.ConfermaImg.setEnabled(false);
								Frame.ConfermaTei.setEnabled(false);
								Frame.EditTei.setEnabled(false);
								Frame.RevisioneImg.setEnabled(false);
								Frame.RevisioneTei.setEnabled(false);
								Frame.RifiutaImg.setEnabled(false);
								Frame.RifiutaTei.setEnabled(false);
								Frame.UploadImg.setEnabled(false);
								break;
								
								
				case "ac":		
								Frame.ConfermaImg.setEnabled(false);
								Frame.ConfermaTei.setEnabled(false);
								Frame.EditTei.setEnabled(false);
								Frame.RevisioneImg.setEnabled(false);
								Frame.RevisioneTei.setEnabled(false);
								Frame.RifiutaImg.setEnabled(false);
								Frame.RifiutaTei.setEnabled(false);
								
								break;
								
				case "ri":		
								Frame.ConfermaImg.setEnabled(false);
								Frame.ConfermaTei.setEnabled(false);
								Frame.EditTei.setEnabled(false);
								Frame.RevisioneTei.setEnabled(false);
								Frame.RifiutaImg.setEnabled(false);
								Frame.RifiutaTei.setEnabled(false);
								Frame.UploadImg.setEnabled(false);
								
								break;
								
				case "tr":		
								Frame.ConfermaImg.setEnabled(false);
								Frame.ConfermaTei.setEnabled(false);
								Frame.RevisioneImg.setEnabled(false);
								Frame.RevisioneTei.setEnabled(false);
								Frame.RifiutaImg.setEnabled(false);
								Frame.RifiutaTei.setEnabled(false);
								Frame.UploadImg.setEnabled(false);
								
								break;
								
				case "rt":		
								Frame.ConfermaImg.setEnabled(false);
								Frame.ConfermaTei.setEnabled(false);
								Frame.RevisioneImg.setEnabled(false);
								Frame.RifiutaImg.setEnabled(false);
								Frame.EditTei.setEnabled(false);
								Frame.RifiutaTei.setEnabled(false);
								Frame.UploadImg.setEnabled(false);
								Frame.setVisible(true);
								break;
								
				default	 : 		break;
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
	
	
}
