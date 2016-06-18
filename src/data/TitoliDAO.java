package data;

import java.util.ArrayList;

public interface TitoliDAO{
	public Boolean pubblicaOpera(String opera);
	public Boolean creaNuovaOpera(String opera, String autore, String epoca);
	public ArrayList<OperaGen> selectOpera(String opera, Boolean pubblicata) throws Exception;
}
