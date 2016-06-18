package data;

import java.io.InputStream;

public interface ScanDAO {
	public Boolean pubbImg(int numeropagina, String nomeopera, String username );
	public Boolean uploadImmagine(String nomeopera, InputStream inputStream, int numpag, String username );
}
