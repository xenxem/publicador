package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.*;
import modelo.*;
import dao.*;

 public class ControleMenuPrincipal extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}  	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String comando = request.getParameter("comando");
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		
		//Usuário logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		u.setCodigoUsuario(u.getCodigoUsuario());
		
		DAOUsuario daoUsuario = df.getDAOUsuario();
		
		if(comando.equals("conta")){
				
			DAOConta daoC = df.getDAOConta();
			DAOPagamento daoP = df.getDAOPagamento();
			
			DAOBanco daoB = df.getDAOBanco();
			ArrayList listaBanco = daoB.consultarTodos();
			request.setAttribute("listaBanco", listaBanco);
			
			ArrayList listaConta = daoC.consultarTodos();
			request.setAttribute("listaConta", listaConta);
			
			ArrayList listaPagamento = daoP.consultarTodos();
			request.setAttribute("listaPagamento", listaPagamento);
		
			request.setAttribute("operacao", "    Cadastrar    ");
			
			RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaConta.jsp");
			rD.forward(request, response);				
			
		}else if(comando.equals("anuncio")){
						
						
			DAOUf daoUf = df.getDAOUf();			
			ArrayList listaUf = daoUf.consultarTodos();			
			request.setAttribute("listaUf", listaUf);
			
			DAOAnuncio daoAnuncio = df.getDAOAnuncio();
			
			//exibição controlada de anúncios, de acordo com código
			ArrayList listaAnuncio = daoUsuario.consultarAnuncios(u);
			request.setAttribute("listaAnuncio", listaAnuncio);				
						
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("anuncioVencido")){
		
			DAOAnuncioVencido daoAVencido = df.getDAOAnuncioVencido();
			
			ArrayList listaAVencido = daoAVencido.consultarTodos();
			
			request.setAttribute("listaAVencido", listaAVencido);
			
			RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaAnuncioVencido.jsp");
			rD.forward(request, response);	
			
		}else if(comando.equals("banco")){
			
			DAOBanco daoB = df.getDAOBanco();
			ArrayList listaBanco = daoB.consultarTodos();
			
			DAOConta daoC = df.getDAOConta();
			ArrayList listaConta = daoC.consultarTodos();
				
			request.setAttribute("listaBanco", listaBanco);
			request.setAttribute("listaConta", listaConta);
				
			RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaBanco.jsp");
			rD.forward(request, response);		
			
		}else if (comando.equals("liberarAnuncio")){
			
			DAOLiberarAnuncio daoLiberarAnuncio = df.getDAOLiberarAnuncio();
			ArrayList listaAnuncio = daoLiberarAnuncio.consultarPendentes();			
			
			request.setAttribute("listaAnuncio", listaAnuncio);
			request.setAttribute("operacao", "Iniciar");
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaLiberarAnuncio.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("categoria")){
			
			DAOCategoria daoCategoria = df.getDAOCategoria();
			ArrayList listaCategoria = daoCategoria.consultarTodos();
			sessao.setAttribute("listaCategoria", listaCategoria);
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncioCategoria.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("cadastroUsuario")){
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaContaUsuario.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("valor")){
			
			DAOValor daoValor = df.getDAOValor();
			DAOPagamento daoP = df.getDAOPagamento();
			
			ArrayList listaValor = new ArrayList();
			ArrayList listaPagamento = new ArrayList();
			
			listaValor = daoValor.consultarTodos();
			listaPagamento = daoP.consultarTodos();
			
			request.setAttribute("listaValor", listaValor);
			request.setAttribute("listaPagamento", listaPagamento);
			request.setAttribute("operacao", "  Cadastrar  ");
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAlterarValor.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("sair")){		
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/LogOut.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("acompIndividual")){
			
			DAOAnuncioAcompanhamento daoAcompanha = df.getDAOAnuncioAcompanhamento();
			
			AnuncioAcompanhamento anuncioA = new AnuncioAcompanhamento();			
			anuncioA.setUsuario(u);			
			ArrayList listaAcompanha = daoAcompanha.consultarAcompanhamentoIndividual(anuncioA);
						
			request.setAttribute("listaAcompanha", listaAcompanha);			
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncioAcompanhamento.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("responderAnuncio")){
			
			Mensagem m = new Mensagem();			
			m.setUsuario(u);
			
			DAOMensagem daoMensagem = df.getDAOMensagem();
			ArrayList listaMensagem = daoMensagem.consultarMensagensUsuario(m);
			request.setAttribute("listaMensagem", listaMensagem);
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaResponder.jsp");
			rd.forward(request, response);
			
		}else if (comando.equals("relatorioUsuario")){
		
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaRelatorioUsuario.jsp");
			rd.forward(request, response);
			
		}else if (comando.equals("relatorioPagamento")){
		
			request.setAttribute("data", "checked");
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaRelatorioPagamento.jsp");
			rd.forward(request, response);
			
		}else if (comando.equals("relatorioMaisVisitados")){
			
			DAOUf daoUf = df.getDAOUf();
			
			ArrayList listaUf = daoUf.consultarTodos();			
			request.setAttribute("listaUf", listaUf);
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaRelatorioMaisVisitados.jsp");
			rd.forward(request, response);
			
		}else if (comando.equals("relatorioLibBloq")){
			
			DAORelatorioLibBloq daoRelatorioLibBloq = df.getDAORelatorioLibBloq();
			
			ArrayList listaGestor = daoRelatorioLibBloq.consultarGestor();
			request.setAttribute("listaGestor", listaGestor);
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaRelatorioLibBloq.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("acompCategoria")){
			
			DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
			AcompanhaCategoria ac = new AcompanhaCategoria();
			ac.setUsuario(u);
						
			ArrayList listaAcompanha = daoAcompanha.consultarTodos(ac);		
			
			if(listaAcompanha!=null){				
				for(int i=0; i < listaAcompanha.size(); i++){
					
					AcompanhaCategoria ac2 = (AcompanhaCategoria) listaAcompanha.get(i);
					
					//data atual
					GregorianCalendar data1 = new GregorianCalendar();					
					long dataAtual = data1.getTimeInMillis();
					
					//data de cadastro
					GregorianCalendar data2 = new GregorianCalendar();
					data2.setTime(ac2.getData());
					
					//long dataCadastro = data2.getTimeInMillis();					
					//int dias = (int)(dataAtual - dataCadastro) (24 * 60 * 60 * 1000);
								
					if(data1.getTime().compareTo(data2.getTime())!= -1){
						daoAcompanha.excluir(ac2);
						listaAcompanha = daoAcompanha.consultarTodos(ac);
					}					
				}				
			}
			
			
			request.setAttribute("listaAcompanha", listaAcompanha);	
			
			DAOUf daoUf = df.getDAOUf();			
			ArrayList listaUf = daoUf.consultarTodos();			
			request.setAttribute("listaUf", listaUf);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAcompanhaCategoria.jsp");
			rd.forward(request, response);
		}else if(comando.equals("pagamento")){
			
			
			DAOPagamento daoPagamento = df.getDAOPagamento();
			Pagamento p = new Pagamento();
			Anuncio a = new Anuncio();
			a.setUsuario(u);
			p.setAnuncio(a);
			ArrayList listaPagamento = daoPagamento.consultarTodos(p);
			request.setAttribute("listaPagamento", listaPagamento);			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaPagamento.jsp");
			rd.forward(request, response);
			
		}else if(comando.equals("renovar")){		
			
			DAOAnuncio daoAnuncio = df.getDAOAnuncio();
			Anuncio a = new Anuncio();
			a.setUsuario(u);	
			
			ArrayList listaAnuncio = daoAnuncio.consultarAnunciosRenovacao(a);
			
			request.setAttribute("listaAnuncio", listaAnuncio);
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaRenovacao.jsp");
			rd.forward(request, response);
			
		}
	}   	  	    
}
	