package dao;
import modelo.Imagem;
import java.util.ArrayList;

public interface DAOImagem{
	
	public void cadastrar(Imagem i);
	public void alterar(Imagem i);
	public void excluir(Imagem i);	
	public Imagem consultar(Imagem i);
	public ArrayList consultarTodos();
	public ArrayList consultarImagemAnuncio(Imagem i);
	
}
