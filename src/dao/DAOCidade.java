package dao;
import modelo.Cidade;
import java.util.ArrayList;

public interface DAOCidade{
	
	public void cadastrar(Cidade c);
	public void alterar(Cidade c);
	public Cidade consultar(Cidade c);
	public ArrayList consultarUfCidade(Cidade c);
	public ArrayList consultarTodos();
	
	
}
