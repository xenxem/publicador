package dao;
import modelo.Ocorrencia;
import java.util.ArrayList;

public interface DAOOcorrencia{
	
	public void cadastrar(Ocorrencia o);
	public void alterar(Ocorrencia o);
	public Ocorrencia consultar(Ocorrencia o);
	public ArrayList consultarTodos();
	
	
}
