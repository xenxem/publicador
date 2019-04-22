package controle;
import dao.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.DAOCategoria;

/**
 * Servlet implementation class for Servlet: ControleArvore
 *
 */
 public class ControleArvore extends HttpServlet{
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		DAOCategoria daoCategoria = df.getDAOCategoria();
		ArrayList listaCategoria = daoCategoria.consultarTodos();	
		
		HttpSession sessao = request.getSession(true);
		
		sessao.setAttribute("listaCategoria", listaCategoria);	
				
		response.encodeRedirectURL("/visao/Arvoren.jsp");
		response.sendRedirect("visao/Arvoren.jsp");		
				
	}   	  	    
}