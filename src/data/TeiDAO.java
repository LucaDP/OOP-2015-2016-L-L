package data;

public interface TeiDAO {
	public Boolean pubbTei(int numeropagina, String nomeopera, String revisore );
	public Boolean uploadTei(int numeropagina, String nomeopera, String testo, String trascrittore);
}
