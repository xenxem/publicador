package dao;
import modelo.Anuncio;


import java.util.ArrayList;

public interface DAOAnuncio {
	
	public void cadastrar(Anuncio a);
	public void alterar(Anuncio a);
	public Anuncio consultar(Anuncio a);
	public ArrayList consultarTodos();
	public void excluir(Anuncio a);
	public Anuncio consultarUltimo(Anuncio a);
	public ArrayList consultarTodosPesquisa(Anuncio a,String criterio,int start);
	public Integer consultarQtdImagem(Anuncio a);
	public ArrayList consultarAnunciosRenovacao(Anuncio a);
	public void renovarAnuncio(Anuncio a);
	
	
}
