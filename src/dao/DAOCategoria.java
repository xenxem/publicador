package dao;
import modelo.Categoria;
import java.util.ArrayList;

public interface DAOCategoria{
	
	public void cadastrar(Categoria c);
	public void alterar(Categoria c);
	public Categoria consultar(Categoria c);
	public ArrayList consultarTodos();
	public void ordenarCategoria(Categoria c);
	public boolean temFilho(Categoria c);
	public void excluir(Categoria c);
	public boolean participaDeRelacionamento(Categoria c);
	public ArrayList consultarPorDescricao(Categoria c);
	
}
