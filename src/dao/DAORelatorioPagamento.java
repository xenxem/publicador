package dao;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface DAORelatorioPagamento {
	
	public ArrayList consultar(String inicio, String fim, String ordenacao);

}
