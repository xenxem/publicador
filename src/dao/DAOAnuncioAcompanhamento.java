package dao;
import modelo.AnuncioAcompanhamento;
import java.util.ArrayList;

public interface DAOAnuncioAcompanhamento {
	
	public void cadastrar(AnuncioAcompanhamento a);
	public void alterar(AnuncioAcompanhamento a);
	public void excluir(AnuncioAcompanhamento a);
	public AnuncioAcompanhamento consultar(AnuncioAcompanhamento a);
	public ArrayList consultarTodos();
	public ArrayList consultarAcompanhamentoIndividual(AnuncioAcompanhamento a);
	public AnuncioAcompanhamento consultarAnuncio(AnuncioAcompanhamento a);
	
}
