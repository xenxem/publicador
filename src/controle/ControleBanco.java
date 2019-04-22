package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Banco;
import dao.DAOBanco;
import dao.DAOConta;
import dao.DAOFactory;

public class ControleBanco extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
  
	public ControleBanco() {
		super();
	}   	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String numero = request.getParameter("numero");
		String descricao = request.getParameter("descricao");
		String comando = request.getParameter("comando");
		String codigoBanco = request.getParameter("codigobanco").trim();
		
		DAOFactory daoF = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOBanco daoB = daoF.getDAOBanco();
		
		Banco b = new Banco();
		
		//INCLUSÃO
		if (comando.equals("Cadastrar")){
			
			b.setNumero(numero);
			b.setDescricaoBanco(descricao);
			
			try{
				
					daoB.cadastrar(b);
				
			}catch(Exception e){
				
			}
						
		//ALTERAÇÃO
		}else if (comando.equals("Alterar")){
		    if (numero != null){
		    	b.setNumero(numero);
		    }
			b.setDescricaoBanco(descricao);
			
			b.setCodigoBanco(Integer.parseInt(codigoBanco));					
			
			try{
		
                	daoB.alterar(b);
					request.setAttribute("operacao", "    Cadastrar    ");
				
			}catch(Exception e){
				
			}
			
		//VISUALIZAÇÃO
		}else if (comando.equals("editar")){
			
			b.setCodigoBanco(Integer.parseInt(codigoBanco));
			
			try{
				
				b = daoB.consultar(b);
					
				request.setAttribute("banco", b);
				request.setAttribute("operacao", "    Alterar    ");
				
				
			}catch (Exception e){
				
			}
			
		//EXCLUSÃO 	
		} else if (comando.equals("excluir")){
			
			b.setCodigoBanco(Integer.parseInt(codigoBanco));
			
			try{
		
					b = daoB.consultar(b);
					
					daoB.excluir(b);
				
			}catch(Exception e){
				
			}
			
		}
		
		try{
			
			ArrayList listaBanco = daoB.consultarTodos();
			
			DAOConta daoC = daoF.getDAOConta();
			ArrayList listaConta = daoC.consultarTodos();
			
			request.setAttribute("listaBanco", listaBanco);
			request.setAttribute("listaConta", listaConta);
			
			RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaBanco.jsp");
			rD.forward(request, response);
			
		}catch (Exception e){
		}
			 
		
	}   	  	    
}