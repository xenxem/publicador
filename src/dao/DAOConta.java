package dao;
import modelo.Conta;
import java.util.ArrayList;

public interface DAOConta{
	
	public void cadastrar(Conta c);
	public void excluir (Conta c);
	public void alterar(Conta c);
	public Conta consultar(Conta c);
	public ArrayList consultarTodos();
	public Conta consultarAtiva();
	
}
