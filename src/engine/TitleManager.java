package engine;


import java.util.ArrayList;

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
	
	public void searchOpera(String a, Ricerca finestra){
		titleDAO<OperaGen> b= new operaDAO();
		ArrayList<OperaGen> listaopere= new ArrayList<OperaGen>();
		listaopere.addAll(b.findOpera(a));
		if(listaopere.isEmpty()){
			System.out.println("VUOTA");
		}
		else{
			for (OperaGen object: listaopere) {
					
					finestra.comboBox.addItem(object.getNomeOpera());
			}
		}
		
		
		
	}
	
}
