package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Anuncio;
import modelo.AnuncioAcompanhamento;
import modelo.Imagem;
import modelo.Usuario;
import dao.DAOAnuncio;
import dao.DAOAnuncioAcompanhamento;
import dao.DAOFactory;
import dao.DAOImagem;


/**
 * Servlet implementation class for Servlet: ControleAcompanha
 *
 */
 public class ControleAcompanha extends HttpServlet{
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");
		int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));
		
		//Usuário logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		u.setCodigoUsuario(u.getCodigoUsuario());
		
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		DAOAnuncioAcompanhamento daoAcompanha = df.getDAOAnuncioAcompanhamento();
		
		//Usuário
		AnuncioAcompanhamento anuncioA = new AnuncioAcompanhamento();
		anuncioA.setUsuario(u);
		
		//Anúncio
		Anuncio anuncio = new Anuncio();
		anuncio.setCodigoAnuncio(codigoAnuncio);
		anuncioA.setAnuncio(anuncio);
			
		
		if(operacao.equalsIgnoreCase("visualizar")){			
				
				AnuncioAcompanhamento ac = new AnuncioAcompanhamento();
				ac = daoAcompanha.consultarAnuncio(anuncioA);					
				request.setAttribute("anuncioAcompanha", ac);		
				request.setAttribute("operacao", "visualizar");		
				
				DAOImagem daoImagem = df.getDAOImagem();
				Imagem img = new Imagem();
				img.setAnuncio(anuncio);
				ArrayList listaImagem = daoImagem.consultarImagemAnuncio(img);
				request.setAttribute("listaImagem", listaImagem);			
			
		}else if(operacao.equalsIgnoreCase("excluir")){			
				daoAcompanha.excluir(anuncioA);
		}
		
		ArrayList listaAcompanha = daoAcompanha.consultarAcompanhamentoIndividual(anuncioA);		
		request.setAttribute("listaAcompanha", listaAcompanha);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncioAcompanhamento.jsp");
		rd.forward(request, response);
	
		
	}   	  	    
}