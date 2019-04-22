package controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Anuncio;
import modelo.AnuncioAcompanhamento;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.Mensagem;
import modelo.Usuario;

import dao.DAOAnuncio;
import dao.DAOAnuncioAcompanhamento;
import dao.DAOCategoria;
import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAOImagem;
import dao.DAOMensagem;
import dao.DAOUsuario;


/**
 * Servlet implementation class for Servlet: ControleDetalhes
 *
 */
 public class ControleDetalhes extends HttpServlet{
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String comando = request.getParameter("comando");	
		
		//c�digo do an�ncio e de seu anunciante
		int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));
		int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOMensagem daoMensagem = df.getDAOMensagem();
		
		/*
		 *  verificando se usu�rio est� logado
		 *  Usu�rio s� pode enviar mensagem ou cadastrar o an�ncio para acompanhamento 
		 *  se estiver logado
		 *
		*/		
		HttpSession sessao = request.getSession(false);
		Usuario usuarioCadastradorMsg = (Usuario) sessao.getAttribute("usuario");
		
		Calendar c = Calendar.getInstance();
		
		String msg="";
		
		if(usuarioCadastradorMsg!=null){
			
			if(comando.trim().equalsIgnoreCase("Fazer uma pergunta ao anunciante")){				
			
				request.setAttribute("operacao", "exibirCaixaMensagem");
				
			}else if(comando.trim().equalsIgnoreCase("Acompanhar este an�ncio")){				
				
				DAOAnuncioAcompanhamento daoAcompanha = df.getDAOAnuncioAcompanhamento();				
				AnuncioAcompanhamento acompanha = new AnuncioAcompanhamento();
				
				
				Anuncio a = new Anuncio();
				a.setCodigoAnuncio(codigoAnuncio);
				acompanha.setAnuncio(a);
				
				Usuario u = new Usuario();
				u.setCodigoUsuario(usuarioCadastradorMsg.getCodigoUsuario());
				acompanha.setUsuario(u);
				
				acompanha.setData(new Timestamp(c.getTimeInMillis()));
				
				AnuncioAcompanhamento acompanha2 = new AnuncioAcompanhamento();
				acompanha2 = daoAcompanha.consultar(acompanha);
				
				if(acompanha2==null){
					daoAcompanha.cadastrar(acompanha);
					msg="An�ncio cadastrado com sucesso";
				}else{
					msg = "Este an�ncio j� encontra-se cadastrado para acompanhamento";
				}
				
				
				
			}else if(comando.trim().equalsIgnoreCase("Enviar mensagem")){
				
				//par�metro
				String descricaoMensagem = request.getParameter("descricaoMensagem");
				
				//Usu�rio
				Usuario usuario = new Usuario();
				usuario.setCodigoUsuario(usuarioCadastradorMsg.getCodigoUsuario());
				
				//An�ncio
				Anuncio anuncio = new Anuncio();
				anuncio.setCodigoAnuncio(codigoAnuncio);
				
				//Mensagem
				Mensagem mensagem = new Mensagem();
				mensagem.setAnuncio(anuncio);
				mensagem.setUsuario(usuario);
				mensagem.setTipo("P");
				mensagem.setData(new Timestamp(c.getTimeInMillis()));
				mensagem.setDescricaoMensagem(descricaoMensagem);				
				daoMensagem.cadastrar(mensagem);
				
				//consultando �ltima mensagem do an�cio/usuario
				mensagem = daoMensagem.consultarUltima(mensagem);				
				mensagem.setOrdem(mensagem.getCodigoMensagem());
				daoMensagem.alterarOrdem(mensagem);
				
			}
				
		}else{
			msg="Usu�rio n�o est� logado";
		}
		
				
								
		
		//An�ncio
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
		Anuncio anuncio = new Anuncio();
		anuncio.setCodigoAnuncio(codigoAnuncio);
		anuncio = daoAnuncio.consultar(anuncio);
		
		//Usu�rio anunciante
		DAOUsuario daoUsuario = df.getDAOUsuario();
		Usuario usuarioAnunciante = new Usuario();
		usuarioAnunciante.setCodigoUsuario(codigoUsuario);
		usuarioAnunciante = daoUsuario.consultarPorCodigo(usuarioAnunciante);
		anuncio.setUsuario(usuarioAnunciante);
				
		
		//Categoria			
		DAOCategoria daoCategoria = df.getDAOCategoria();
		Categoria categoria = new Categoria();
		categoria.setCodigoCategoria(anuncio.getCategoria().getCodigoCategoria());
		categoria.setNivelCategoria(anuncio.getCategoria().getNivelCategoria());
		categoria = daoCategoria.consultar(categoria);
		anuncio.setCategoria(categoria);
		
		//Mensagem		
		Mensagem mensagem = new Mensagem();
		mensagem.setAnuncio(anuncio);			
		ArrayList listaMensagem = daoMensagem.consultarMensagens(mensagem);			
		anuncio.setMensagem(listaMensagem);
		
		//Cidade
		DAOCidade daoCidade = df.getDAOCidade();
		Cidade cidade = new Cidade();			
		cidade.setCodigoCidade(anuncio.getCidade().getCodigoCidade());
		cidade = daoCidade.consultar(cidade);
		anuncio.setCidade(cidade);
		
		//Imagem
		DAOImagem daoImagem = df.getDAOImagem();
		Imagem imagem = new Imagem();
		imagem.setAnuncio(anuncio);
		ArrayList listaImagem = (ArrayList) daoImagem.consultarImagemAnuncio(imagem);
		
		//atribuindo imagens da ArrayList ao Vetor
		if(listaImagem!=null){
			Vector vImagem = new Vector();	
			for(int i=0; i < listaImagem.size(); i++){
				vImagem.add(listaImagem.get(i));
			}
			anuncio.setImagem(vImagem);
		}		
		
		//an�ncio com dados
		request.setAttribute("anuncio", anuncio);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaDetalhes.jsp?msg="+msg);
		rd.forward(request, response);
		
	}   	  	    
}