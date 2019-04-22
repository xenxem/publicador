package controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.AcompanhaCategoria;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Uf;
import modelo.Usuario;

import dao.DAOAcompanhaCategoria;
import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAOUf;


/**
 * Servlet implementation class for Servlet: ControleAcompanhaCategoria
 *
 */
 public class ControleAcompanhaCategoria extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		//Usuário logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		u.setCodigoUsuario(u.getCodigoUsuario());
		
		String operacao = request.getParameter("operacao");
		String codigoUf = request.getParameter("codigoUf");
		String popula = request.getParameter("popula");		
		String codigoCidade = request.getParameter("codigoCidade");
		
		
		Uf uf = new Uf();
		Cidade cidade = new Cidade();
				
		AcompanhaCategoria acompCategoria = new AcompanhaCategoria();
		
		//Evitando NullPointException
		if(operacao==null)
			operacao="";
		
		String msg ="";
		
		GregorianCalendar gCalendar = new GregorianCalendar();
		
		if(operacao.equals("editarCategoria")){
			
			DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
			
			int codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
			int nivelCategoria = Integer.parseInt(request.getParameter("nivelCategoria"));
			
			Categoria categoria = new Categoria();
			categoria.setCodigoCategoria(codigoCategoria);
			categoria.setNivelCategoria(nivelCategoria);			
			acompCategoria.setCategoria(categoria);
			
			int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
			Usuario usuario = new Usuario();
			usuario.setCodigoUsuario(codigoUsuario);
			acompCategoria.setUsuario(usuario);
			
			acompCategoria = daoAcompanha.consultar(acompCategoria);
			request.setAttribute("acompCategoria", acompCategoria);
			
			if(acompCategoria.getUf().getCodigoUf()!=null){				
								
				
				uf.setCodigoUf(acompCategoria.getUf().getCodigoUf());
				request.setAttribute("uf", uf);
				
				cidade.setUf(uf);			
				DAOCidade daoCidade = df.getDAOCidade();
				ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
				request.setAttribute("listaCidade", listaCidade);
				cidade.setCodigoCidade(acompCategoria.getCidade().getCodigoCidade());
				request.setAttribute("cidade", cidade);			
				
			}		
			
			request.setAttribute("operacao", "Alterar");
			
		}else if(operacao.equals("Alterar")){
			
			DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
			
			//dados da novo acompanhamento
			String periodo = request.getParameter("periodo");
			acompCategoria.setPeriodo(periodo);			
			acompCategoria.setData(new Timestamp(gCalendar.getTimeInMillis()));
			acompCategoria.setUsuario(u);
			
			int codigoCategoriaAnt = Integer.parseInt(request.getParameter("codigoCategoriaAnt"));
			int nivelCategoriaAnt = Integer.parseInt(request.getParameter("nivelCategoriaAnt"));			
			int codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
			int nivelCategoria = Integer.parseInt(request.getParameter("nivelCategoria"));
			
			//dados da novacategoria			
			Categoria cat1 = new Categoria();
			cat1.setCodigoCategoria(codigoCategoria);
			cat1.setNivelCategoria(nivelCategoria);			
			acompCategoria.setCategoria(cat1);
			
			AcompanhaCategoria resultadoAc = new AcompanhaCategoria();
			
			resultadoAc = daoAcompanha.consultar(acompCategoria);
			
			if(resultadoAc==null){
			
				//dados da categoria anterior
				Categoria cat2 = new Categoria();
				cat2.setCodigoCategoria(codigoCategoriaAnt);
				cat2.setNivelCategoria(nivelCategoriaAnt);
				
				AcompanhaCategoria acompAnt = new AcompanhaCategoria();
				acompAnt.setCategoria(cat2);
				acompAnt.setUsuario(u);					
				
				//uf
				Uf uf2 = new Uf();
				uf2.setCodigoUf(codigoUf);
				acompCategoria.setUf(uf2);			
				
				//cidade
				Cidade c2 = new Cidade();
				
				if(codigoCidade.equals("")){
					c2.setCodigoCidade(0);				
				}else{
					c2.setCodigoCidade(Integer.parseInt(codigoCidade));				
				}			
				
				acompCategoria.setCidade(c2);
							
				//excluindo anterior
				daoAcompanha.excluir(acompAnt);
					
				//cadastrando nova
				daoAcompanha.cadastrar(acompCategoria);
					
				msg="Categoria alterada com sucesso!";
			
			}else{
				msg="Esta categoria já encontra-se cadastrada para companhamento!";
			}
		}else if(operacao.equals("Cadastrar")){
			
			DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
			
			String periodo = request.getParameter("periodo");
			acompCategoria.setPeriodo(periodo);
			
			if(periodo.equals("D")){
				
				gCalendar.add(gCalendar.HOUR_OF_DAY, 24);
				acompCategoria.setData(new Timestamp(gCalendar.getTimeInMillis()));
				
			}else 
				if(periodo.equals("S")){
				
				gCalendar.add(gCalendar.DATE,7);
				acompCategoria.setData(new Timestamp(gCalendar.getTimeInMillis()));
				
			}else{
				gCalendar.add(gCalendar.DATE,30);
				acompCategoria.setData(new Timestamp(gCalendar.getTimeInMillis()));
			}
			
			
			int codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
			int nivelCategoria = Integer.parseInt(request.getParameter("nivelCategoria"));
			Categoria categoria = new Categoria();
			categoria.setCodigoCategoria(codigoCategoria);
			categoria.setNivelCategoria(nivelCategoria);			
			acompCategoria.setCategoria(categoria);
			
			AcompanhaCategoria ac = new AcompanhaCategoria();
			acompCategoria.setUsuario(u);
			acompCategoria.setCategoria(categoria);			
			ac = daoAcompanha.consultar(acompCategoria);
		
			
			if(ac!=null){
				msg = "Esta categoria já encontra-se cadastrada para companhamento!";
			}else{
				
				Uf uf2 = new Uf();
				uf2.setCodigoUf(codigoUf);
				acompCategoria.setUf(uf2);			
				
				Cidade c2 = new Cidade();
				
				if(codigoCidade.equals("")){
					c2.setCodigoCidade(0);				
				}else{
					c2.setCodigoCidade(Integer.parseInt(codigoCidade));				
				}
				
				acompCategoria.setCidade(c2);				
				
				daoAcompanha.cadastrar(acompCategoria);
				msg="Categoria cadastrada com sucesso!";
			}
					
			popula=null;
			
		}else if(operacao.equals("excluirCategoria")){
			
			DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
			AcompanhaCategoria ac = new AcompanhaCategoria();
			
			ac.setUsuario(u);
			
			int codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
			int nivelCategoria = Integer.parseInt(request.getParameter("nivelCategoria"));
			Categoria c  = new Categoria();
			c.setCodigoCategoria(codigoCategoria);
			c.setNivelCategoria(nivelCategoria);
			ac.setCategoria(c);
			
			daoAcompanha.excluir(ac);
			
		}
		
		if(popula!=null){			
			
			uf.setCodigoUf(codigoUf);
			request.setAttribute("uf", uf);		
				
			cidade.setUf(uf);
			
			DAOCidade daoCidade = df.getDAOCidade();
			ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
			request.setAttribute("listaCidade", listaCidade);
			
		}
		
		DAOUf daoUf = df.getDAOUf();			
		ArrayList listaUf = daoUf.consultarTodos();			
		request.setAttribute("listaUf", listaUf);
					
		
		DAOAcompanhaCategoria daoAcompanha = df.getDAOAcompanhaCategoria();
				
		AcompanhaCategoria ac = new AcompanhaCategoria();
		ac.setUsuario(u);
					
		ArrayList listaAcompanha = daoAcompanha.consultarTodos(ac);
		
		
		request.setAttribute("listaAcompanha", listaAcompanha);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAcompanhaCategoria.jsp?msg="+msg);
		rd.forward(request, response);	
	}   	  	    
}