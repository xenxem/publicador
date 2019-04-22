package dao;
import modelo.AcompanhaCategoria;
import java.util.ArrayList;

public interface DAOAcompanhaCategoria {
	
	public void cadastrar(AcompanhaCategoria ac);
	public void alterar(AcompanhaCategoria ac);
	public AcompanhaCategoria consultar(AcompanhaCategoria ac);
	public ArrayList consultarTodos();
	public ArrayList consultarTodos(AcompanhaCategoria ac);
	public void excluir(AcompanhaCategoria ac);
	
}
