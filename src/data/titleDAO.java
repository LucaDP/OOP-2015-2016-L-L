package data;

import java.util.ArrayList;

public interface titleDAO<T>{
	public ArrayList<T> selectOpera(String opera, Boolean pubblicata) throws Exception;
}
