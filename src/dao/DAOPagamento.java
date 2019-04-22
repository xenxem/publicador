package dao;
import modelo.Anuncio;
import modelo.Pagamento;
import java.util.ArrayList;

public interface DAOPagamento{
	
	public void cadastrar(Pagamento p);
	public void alterar(Pagamento p);
	public Pagamento consultar(Pagamento p);
	public ArrayList consultarTodos();
	public Pagamento ConsultarValor(Anuncio a);
	public ArrayList consultarTodos(Pagamento p);
	public void excluir(Pagamento p);
	public Pagamento consultarPagamentoGerado(Pagamento p);
	
}
