package dao;

import java.util.ArrayList;

import modelo.Anuncio;
import modelo.LiberaAnuncio;
import modelo.Usuario;

public interface DAOLiberarAnuncio {

	public void cadastrar(LiberaAnuncio la);
	public ArrayList consultarPendentes();
	public void liberarAnuncio(Anuncio a);
	public void bloquearAnuncio(Anuncio a);
	public Usuario consultarUsuario(Usuario u);
	public void mudarStatus(Anuncio a);
}
