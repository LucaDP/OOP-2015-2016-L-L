package Listener;

import engine.Manager;

/*Singleton design pattern*/
public class ViewListener {
	public static ViewListener instance;
	
	private ViewListener(){
		
	}
	/*se è stato già istanziato un oggetto ViewListener si utilizza quello, altrimenti si istanzia un nuovo oggetto ViewListener*/
	public static ViewListener getInstance(){
		if(instance==null){
			instance= new ViewListener();
		}
		return instance;
	}
	
	public static void login(String username, String password){
		if(username.length()==0 || password.length()==0){
			Manager a=Manager.getInstance();
			a.alert(1);
		}
		else{
			Manager a=Manager.getInstance();
			a.loginManager(username , password);
		}
	}
	
	public static void signin(String username, String password, String email){
		if(username.length()==0 || password.length()==0 || email.length()==0){
			Manager a=Manager.getInstance();
			a.alert(1);
		}
		else{
			Manager a=Manager.getInstance();
			a.signinManager(username , password, email);
			
		}
		
	}

}
