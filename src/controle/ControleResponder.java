package controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.Mensagem;
import modelo.Usuario;
import dao.DAOAnuncio;
import dao.DAOCategoria;
import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAOImagem;
import dao.DAOMensagem;
import dao.DAOUsuario;


/**
 * Servlet implementation class for Servlet: ControleResponder
 *
 */
 public class ControleResponder extends HttpServlet{
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Fábrica
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);	
						
		//Usuário logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		u.setCodigoUsuario(u.getCodigoUsuario());
		
		String operacao = request.getParameter("operacao");
			
		//Variáveis
		String msg ="";				
		
		int codigoMensagem = Integer.parseInt(request.getParameter("codigoMensagem"));
		DAOMensagem daoMensagem = df.getDAOMensagem();
		Mensagem mensagem = new Mensagem();
		mensagem.setCodigoMensagem(codigoMensagem);
		
		int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));
		Anuncio a = new Anuncio();
		a.setCodigoAnuncio(codigoAnuncio);
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
				
		if(operacao.equals("responderAnuncio")){
			
			mensagem = daoMensagem.consultar(mensagem);
			DAOUsuario daoUsuario = df.getDAOUsuario();
			Usuario usuario = new Usuario();
			usuario.setCodigoUsuario(mensagem.getUsuario().getCodigoUsuario());
			usuario = daoUsuario.consultarPorCodigo(usuario);
			mensagem.setUsuario(usuario);
			
			
			a = daoAnuncio.consultar(a);
			mensagem.setAnuncio(a);
			
			request.setAttribute("mensagem", mensagem);
			request.setAttribute("operacao", "exibirCaixaMensagem");
			
			
		}else if(operacao.equals("excluir")) {
			
			int ordem = Integer.parseInt(request.getParameter("ordem"));
			mensagem.setOrdem(ordem);
			daoMensagem.excluirPerguntaResposta(mensagem);
			
		}else if(operacao.equals("Enviar Resposta")){
			
			String descricaoMensagem = request.getParameter("descricaoMensagem");
			mensagem.setDescricaoMensagem(descricaoMensagem);
			Calendar c = Calendar.getInstance();
			mensagem.setData(new Timestamp(c.getTimeInMillis()));
			mensagem.setTipo("R");
			mensagem.setOrdem(codigoMensagem);
			mensagem.setUsuario(u);
			mensagem.setAnuncio(a);
			daoMensagem.cadastrar(mensagem);
		}
		
		Mensagem m = new Mensagem();			
		m.setUsuario(u);
		m.setCodigoMensagem(codigoMensagem);
		
		ArrayList listaMensagem = daoMensagem.consultarMensagensUsuario(m);
		request.setAttribute("listaMensagem", listaMensagem);
		
				
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaResponder.jsp");
		rd.forward(request, response);

	}   	  	    
}