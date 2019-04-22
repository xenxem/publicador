package dao;
import modelo.Uf;
import java.util.ArrayList;

public interface DAOUf{
	
	public void cadastrar(Uf u);
	public void alterar(Uf u);
	public Uf consultar(Uf u);
	public ArrayList consultarTodos();
	
	
}
