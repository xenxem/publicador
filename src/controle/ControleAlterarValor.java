package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Valor;

import dao.DAOFactory;
import dao.DAOPagamento;
import dao.DAOValor;

 public class ControleAlterarValor extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;

   public ControleAlterarValor() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String comando = (String) request.getParameter("comando");
		String valor = (String) request.getParameter("valor");
		String codigoValor = (String) request.getParameter("codigovalor");
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOValor daoValor = df.getDAOValor();
		DAOPagamento daoP = df.getDAOPagamento();
		
		Valor v = new Valor();
		
		//INCLUSÃO
		if (comando != null && comando.trim().equals("Cadastrar")){
		
			Valor vA = daoValor.consultarAtivo();
			vA.setStatus(null);
			
			daoValor.alterar(vA);
			
			v.setCodigoValor(Integer.parseInt(codigoValor));
			v.setValor(Float.parseFloat(valor));
			v.setStatus("A");
			
			daoValor.cadastrar(v);
			
			request.setAttribute("operacao", "  Cadastrar  ");
			
		//ALTERAÇAO	
		}else if (comando != null && comando.trim().equals("Alterar")){

			v.setCodigoValor(Integer.parseInt(codigoValor));
			v.setValor(Float.parseFloat(valor));
			
			Valor vA = daoValor.consultarAtivo();
			if (vA.getCodigoValor() == v.getCodigoValor()){
				v.setStatus("A");
			}
			
			daoValor.alterar(v);
			
			request.setAttribute("operacao", "  Cadastrar  ");
		
		//VISUALIZAÇÃO	
		}else if (comando != null && comando.trim().equals("editar")){
			
			v.setCodigoValor(Integer.parseInt(codigoValor));
			
			v = daoValor.consultar(v);
			
			
			request.setAttribute("valor", v);
			request.setAttribute("operacao", "   Alterar   ");
		
		//EXCLUSÃO	
		}else if (comando != null && comando.trim().equals("excluir")){
			
			v.setCodigoValor(Integer.parseInt(codigoValor));
			
			daoValor.excluir(v);
			
		}
		
		ArrayList listaValor = new ArrayList();
		ArrayList listaPagamento = new ArrayList();
		
		listaValor = daoValor.consultarTodos();
		listaPagamento = daoP.consultarTodos();
		
		request.setAttribute("listaValor", listaValor);
		request.setAttribute("listaPagamento", listaPagamento);
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAlterarValor.jsp");
		rd.forward(request, response);
	}   	  	    
}