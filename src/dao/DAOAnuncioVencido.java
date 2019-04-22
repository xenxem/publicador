package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import modelo.Anuncio;
import modelo.Ocorrencia;
import modelo.Usuario;

public interface DAOAnuncioVencido {

	public ArrayList consultarTodos();
	public Usuario consultarEmail(Usuario u);
	
}
