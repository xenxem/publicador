package dao;

import java.util.ArrayList;

import modelo.Banco;

public interface DAOBanco {
	
	public void cadastrar(Banco b);
	public void excluir (Banco b);
	public void alterar(Banco b);
	public Banco consultar(Banco b);
	public ArrayList consultarTodos();
	
}
