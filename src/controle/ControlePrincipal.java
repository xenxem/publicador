package controle;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.GregorianCalendar ;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import modelo.*;
import dao.*;



 public class ControlePrincipal extends HttpServlet{
   
	  	
	 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando = request.getParameter("comando");
		String comando2 = request.getParameter("comando2");
				
		
		//evitando NullPointerException
		if(comando2==null)
			comando2 = "";
		
		if(comando.equals("detalhes") || comando2.equals("detalhes") ){
			
			int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));
			int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
			
			//fábrica de dao
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);			
			
			//Anúncio
			DAOAnuncio daoAnuncio = df.getDAOAnuncio();			
			Anuncio anuncio = new Anuncio();
			anuncio.setCodigoAnuncio(codigoAnuncio);
			anuncio = daoAnuncio.consultar(anuncio);
			
			
			/* 
			 * Para contar qntas vezes um anúncio foi acessado
			 * Caso o usuário ainda não tenha 
			 * recebido um cookie, configura um para controle de acesso ao anúncio
			 * 			 
			 */
			
			
			
		
						
			//Usuário
			DAOUsuario daoUsuario = df.getDAOUsuario();
			Usuario u = new Usuario();
			u.setCodigoUsuario(codigoUsuario);
			u = daoUsuario.consultarPorCodigo(u);
			anuncio.setUsuario(u);
					
			
			//Categoria			
			DAOCategoria daoCategoria = df.getDAOCategoria();
			Categoria categoria = new Categoria();
			categoria.setCodigoCategoria(anuncio.getCategoria().getCodigoCategoria());
			categoria.setNivelCategoria(anuncio.getCategoria().getNivelCategoria());
			categoria = daoCategoria.consultar(categoria);
			anuncio.setCategoria(categoria);
			
			//Mensagem
			DAOMensagem daoMensagem = df.getDAOMensagem();
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
			
			
			
			//anúncio com dados
			request.setAttribute("anuncio", anuncio);
			
			String msg = (String) request.getAttribute("msg");
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaDetalhes.jsp?msg="+msg);			
			rd.forward(request,response);
			
		}else if(comando.equals("cadastroUsuario")){
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaContaUsuario.jsp");			
			rd.forward(request,response);
		}
		
	}  	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		String comando = request.getParameter("comando");
		String codigoUf = request.getParameter("codigoUf");
		String codigoCidade = request.getParameter("codigoCidade");
		
		
		
		if(comando==null){			
			comando = "";
		}
		
		
		
		if(comando.equals("Entrar")){
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			//setando e-mail e senha
			Usuario u = new Usuario();			
			u.setEmail(email);
			u.setSenha(senha);
			
					
			//verificando se email do usuário já está cadastrado na base			
			DAOUsuario daoUsuario = df.getDAOUsuario();
			u = daoUsuario.consultar(u);
				
			if(u==null){
				String telaAtual = request.getParameter("telaAtual");
					
				if(telaAtual.equals("TelaDetalhes.jsp")){
					request.setAttribute("msg", "naoCadastrado");
					doGet(request,response);	
				}else
					response.sendRedirect("visao/"+telaAtual+"?msg=naoCadastrado");					
			}else{					
				if(u.getStatusValidacao().equals("H")){
						
					//se não tiver cria uma sessão para o usuário
					HttpSession sessao = request.getSession(true);
							
					//tempo em segundos						 						
					sessao.setMaxInactiveInterval(60 * 15);							
					sessao.setAttribute("usuario", u);
							
					String telaAtual = request.getParameter("telaAtual");
							
					if(telaAtual.equals("TelaDetalhes.jsp")){
							doGet(request,response);	
					}else{							
						if(codigoCidade!=null && !codigoCidade.equals("")){								
							//uf
							Uf uf = new Uf();			 
							uf.setCodigoUf(codigoUf);
							request.setAttribute("uf",uf);
									
							//cidade
							Cidade cidade = new Cidade();			 			 
							cidade.setUf(uf);
							cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
							request.setAttribute("cidade", cidade);							 
									
							//lista de uf 
							DAOUf daoUf = df.getDAOUf();							 						 					 
							ArrayList listaUf = daoUf.consultarTodos();							 
							request.setAttribute("listaUf", listaUf);
									 
							//lista de cidades
							DAOCidade daoCidade = df.getDAOCidade();							 						 
							ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);							
							request.setAttribute("listaCidade", listaCidade);		
						}						
								
						//response.encodeRedirectURL("/visao/index.jsp");
						//response.sendRedirect("visao/index.jsp");		
								
						RequestDispatcher rd = request.getRequestDispatcher("/visao/"+telaAtual);			
						rd.forward(request,response);
					}					
				}else{
					String telaAtual = request.getParameter("telaAtual");						
					if(telaAtual.equals("TelaDetalhes.jsp")){
						request.setAttribute("msg", "naoHabilitado");
						doGet(request,response);
					}else{	
						RequestDispatcher rd = request.getRequestDispatcher("/visao/"+telaAtual+"?msg=naoHabilitado");			
						rd.forward(request,response);
					}						
				}
			}
		}else if(comando.trim().equals("Pesquisar")){
			
			String titulo = request.getParameter("titulo");			
			String codigoCategoria = request.getParameter("codigoCategoria");
			String nivelCategoria = request.getParameter("nivelCategoria");
			String codigoCategoriaAux = request.getParameter("codigoCategoriaAux");
			String nivelCategoriaAux = request.getParameter("nivelCategoriaAux");						
			String criterio = request.getParameter("criterio");
			
			String proxima = request.getParameter("proxima");
			String anterior = request.getParameter("anterior");
			String operacao = request.getParameter("operacao");

			
			//para paginação
			int a=0;
			int p=0;
			int pg=0;
			int totalRegistroPagina=3;
			
			
			if(operacao==null)
				operacao="";
			
					
			if(operacao.equals("Próximo>>")){
				p = Integer.parseInt(proxima) + 1;			
				pg = p * totalRegistroPagina;				
				request.setAttribute("paginaAtual",""+p);
				request.setAttribute("totalRegistros",""+totalRegistroPagina);
			}else			
				if(operacao.equals("<<Anterior")){
					a = Integer.parseInt(anterior) - 1;
					pg = a * totalRegistroPagina;						
					request.setAttribute("paginaAtual",""+a);				
				}
			
			
			
			
			//título
			Anuncio anuncio = new Anuncio();			
			anuncio.setTitulo(titulo);
		
			//uf
			Uf uf = new Uf();
			uf.setCodigoUf(codigoUf);
			anuncio.setUf(uf);
			
			//cidade
			Cidade cidade = new Cidade();			
			cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
			anuncio.setCidade(cidade);
				
			Categoria categoria = new Categoria();
			
			//categoria
			if(codigoCategoria.equals("")){				
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoriaAux));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoriaAux));			
			}else{
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));				
			}
														
			anuncio.setCategoria(categoria);
			
			//pesquisa anúncios
			DAOAnuncio daoAnuncio = df.getDAOAnuncio();			
			ArrayList anuncioListaPesquisados = daoAnuncio.consultarTodosPesquisa(anuncio,criterio,pg);			
			request.setAttribute("anuncioListaPesquisados", anuncioListaPesquisados);			
						 
			 //cidade			 			 			 
			cidade.setUf(uf);
			 
			//lista uf		 
			DAOUf daoUf = df.getDAOUf();			 
			ArrayList listaUf = new ArrayList();			 
			listaUf = daoUf.consultarTodos();			 
			request.setAttribute("listaUf", listaUf);
			request.setAttribute("uf",uf);
			 
			//lista cidade
			DAOCidade daoCidade = df.getDAOCidade();			 
			ArrayList listaCidade = new ArrayList();			 
			listaCidade = daoCidade.consultarUfCidade(cidade);			
			request.setAttribute("listaCidade", listaCidade);
			request.setAttribute("cidade",cidade);
			
			request.setAttribute("criterio", criterio);
			request.setAttribute("anuncio", anuncio);
			 
			RequestDispatcher rd = request.getRequestDispatcher("/visao/index.jsp");			
			rd.forward(request,response);
		   
		}else if(!codigoUf.trim().equals("Estado")){
			
			//uf
			Uf uf = new Uf();			 
			uf.setCodigoUf(codigoUf);	
			request.setAttribute("uf",uf);
			 
			//cidade
			Cidade cidade = new Cidade();			 			 
			cidade.setUf(uf);
			 
			//lista uf		 
			DAOUf daoUf = df.getDAOUf();			 
			ArrayList listaUf = new ArrayList();			 
			listaUf = daoUf.consultarTodos();			 
			request.setAttribute("listaUf", listaUf);
			 
			//cidade
			DAOCidade daoCidade = df.getDAOCidade();			 
			ArrayList listaCidade = new ArrayList();			 
			listaCidade = daoCidade.consultarUfCidade(cidade);	 
			
			//anuncio
			Anuncio anuncio = new Anuncio();			
			String titulo = request.getParameter("titulo");			
			anuncio.setTitulo(titulo);
			anuncio.setUf(uf);
			anuncio.setCidade(cidade);
			
			//categoria
			String codigoCategoria = request.getParameter("codigoCategoria");
			String nivelCategoria = request.getParameter("nivelCategoria");
			String codigoCategoriaAux = request.getParameter("codigoCategoriaAux");
			String nivelCategoriaAux = request.getParameter("nivelCategoriaAux");
			String criterio = request.getParameter("criterio");
			
			Categoria categoria = new Categoria();
			
			
			if(codigoCategoria.equals("")){				
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoriaAux));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoriaAux));				
			}else{
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));				
			}
			
			anuncio.setCategoria(categoria);
			
						
			request.setAttribute("anuncio", anuncio);
			request.setAttribute("listaCidade", listaCidade);	
			request.setAttribute("criterio", criterio);
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/index.jsp");			
			rd.forward(request,response);			
			
		}		
	}   	  	    
}