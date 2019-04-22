package dao;
import modelo.Usuario;
import java.util.ArrayList;

public interface DAOUsuario{
	
	public void cadastrar(Usuario u);
	public void alterar(Usuario u);
	public Usuario consultar(Usuario u);
	public ArrayList consultarTodos();
	public ArrayList consultarPendentes(Usuario u);
	public Usuario consultarEmail(Usuario u);
	public Usuario consultarApelido(Usuario u);	
	public ArrayList consultarAnuncios(Usuario u);
	public Usuario consultarPorCodigo(Usuario u);
}