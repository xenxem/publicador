package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Categoria;
import dao.*;

/**
 * Servlet implementation class for Servlet: ControleCagetoria
 *
 */
 public class ControleCagetoria extends HttpServlet{
   
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}  	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println("<<<<<<<<<<<<<<<<<<  ControleCategoria  >>>>>>>>>>>>>>>>>");
		
		String codigoCategoria = request.getParameter("codigoCategoria");
		String nivelCategoria = request.getParameter("nivelCategoria");
		
		String comando = request.getParameter("comando");
		String descricaoCategoria = request.getParameter("descricaoCategoria");	
		
		Categoria categoria = new Categoria();
		
		if(codigoCategoria.equals("")){			
			//para cadastro de um pai
			categoria.setCodigoCategoria(0);
			categoria.setNivelCategoria(1);			
		}else{			
			//para cadastro de um filho			
			categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
			categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));
		}		
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);		
		DAOCategoria daoCategoria = df.getDAOCategoria();
		
		String msg = "";
		
		if(comando.trim().equalsIgnoreCase("cadastrar")){		
			
			categoria.setDescricaoCategoria(descricaoCategoria);		
			daoCategoria.cadastrar(categoria);
			
		}else if(comando.trim().equalsIgnoreCase("alterar")){
			
			categoria.setDescricaoCategoria(descricaoCategoria);		
			daoCategoria.alterar(categoria);			
			
		}else if(comando.trim().equalsIgnoreCase("excluir")){
									
			if(daoCategoria.temFilho(categoria)){
				System.out.println("ok");
				msg = "temFilho";
			}else{
				if(daoCategoria.participaDeRelacionamento(categoria)){				
					msg = "participa";
				}else{
					daoCategoria.excluir(categoria);
					msg = "sucesso";
				}
			}
			
		}else{
			//consultar
			categoria.setDescricaoCategoria(descricaoCategoria);		
			ArrayList listaCategoria = daoCategoria.consultarPorDescricao(categoria);
			request.setAttribute("listaCategoria", listaCategoria);
			
		}
		
		//garantindo a exibi��o atualizada de categoria
		HttpSession sessao = request.getSession(true);
		ArrayList listaCategoria = daoCategoria.consultarTodos();
		sessao.setAttribute("listaCategoria", listaCategoria);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncioCategoria.jsp?msg="+msg);
		rd.forward(request, response);
	}   	  	    
}