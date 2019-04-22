package dao;

import java.util.ArrayList;

public interface DAORelatorioLibBloq {

	public ArrayList consultar(String tipo, String gestor, String inicio, String fim);
	public ArrayList consultarGestor();
	
}
