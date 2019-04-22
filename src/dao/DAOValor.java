package dao;
import java.util.ArrayList;

import modelo.Valor;


public interface DAOValor{
	
	public void cadastrar(Valor v);
	public void alterar(Valor v);
	public Valor consultar(Valor v);
	public void excluir(Valor v);
	public ArrayList consultarTodos();
	public Valor consultarAtivo();
}
