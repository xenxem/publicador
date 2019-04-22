package dao;
import modelo.Mensagem;
import java.util.Vector;
import java.util.ArrayList;

public interface DAOMensagem{
	
	public void cadastrar(Mensagem m);
	public void alterar(Mensagem m);
	public Mensagem consultar(Mensagem m);
	public ArrayList consultarTodos();
	public ArrayList consultarMensagens(Mensagem m);
	public Mensagem consultarUltima(Mensagem m);
	public void alterarOrdem(Mensagem m);
	public ArrayList consultarMensagensUsuario(Mensagem m);
	public void excluir(Mensagem m);
	public void excluirPerguntaResposta(Mensagem m);
	
	
}
