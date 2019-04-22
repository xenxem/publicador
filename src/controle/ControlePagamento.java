package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Anuncio;
import modelo.Pagamento;
import modelo.Usuario;

import dao.DAOFactory;
import dao.DAOPagamento;


/**
 * Servlet implementation class for Servlet: ControlePagamento
 *
 */
 public class ControlePagamento extends HttpServlet{
   
   
      	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Usuário logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		u.setCodigoUsuario(u.getCodigoUsuario());
		
		int codigoPagamento = Integer.parseInt(request.getParameter("codigoPagamento"));
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOPagamento daoPagamento = df.getDAOPagamento();
				
		Pagamento p = new Pagamento();
		p.setCodigoPagamento(codigoPagamento);
		daoPagamento.excluir(p);
			
		Anuncio a = new Anuncio();
		a.setUsuario(u);
		p.setAnuncio(a);
		
		ArrayList listaPagamento = daoPagamento.consultarTodos(p);
		request.setAttribute("listaPagamento", listaPagamento);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaPagamento.jsp");
		rd.forward(request, response);	
		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}   	  	    
}