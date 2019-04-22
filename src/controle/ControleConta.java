package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Banco;
import modelo.Conta;

import dao.DAOBanco;
import dao.DAOConta;
import dao.DAOContaPSQL;
import dao.DAOFactory;
import dao.DAOFactoryPSQL;
import dao.DAOPagamento;


 public class ControleConta extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando = request.getParameter("comando").trim();
		String numeroConta = request.getParameter("numeroConta");
		String agencia = request.getParameter("agencia");
		String codigoBanco = request.getParameter("codigoBanco");
		String status = request.getParameter("status");
		String codigoConta = request.getParameter("codigoconta");
	   		
		DAOFactory daoF = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOConta daoC = daoF.getDAOConta();
		
		Conta c = new Conta();
		
		//INCLUSÃO
		if (comando.equals("Cadastrar")){
			
			Banco b = new Banco();
			b.setCodigoBanco(Integer.parseInt(codigoBanco));
			c.setBanco(b);
			
			c.setAgencia(agencia);
			c.setNumeroConta(numeroConta);
			
			
				
			if (status != null){
					
				c.setStatus("A");
					
				Conta cA = daoC.consultarAtiva();	
					 
				cA.setStatus(null);
					
				daoC.alterar(cA);
					
				daoC.cadastrar(c);
				request.setAttribute("operacao", "    Cadastrar    ");
					
			}else{
					
				daoC.cadastrar(c);
				request.setAttribute("operacao", "    Cadastrar    ");
					
			}
						
						
		//ALTERAÇÃO
		}else if (comando.equals("Alterar")){
		
			Banco b = new Banco();
			b.setCodigoBanco(Integer.parseInt(codigoBanco));
			c.setBanco(b);
			
			c.setAgencia(agencia);
			c.setNumeroConta(numeroConta);
		
			c.setCodigoConta(Integer.parseInt(codigoConta));					
			
			if (status != null){
					
				c.setStatus("A");
					
				Conta cA = daoC.consultarAtiva();	
					 
				cA.setStatus(null);
					
				daoC.alterar(cA);
					
				daoC.alterar(c);
				request.setAttribute("operacao", "    Cadastrar    ");
					
			}else{
					
				Conta cA = daoC.consultarAtiva();
					
				if (cA.getCodigoConta() == c.getCodigoConta()){
					
					c.setStatus("A");
					daoC.alterar(c);
					request.setAttribute("operacao", "    Cadastrar    ");
						
				}else{
						
					daoC.alterar(c);
					request.setAttribute("operacao", "    Cadastrar    ");
						
				}
					
			}
				
			
			
		//VISUALIZAÇÃO	
		}else if (comando.equals("editar")){
			
			c.setCodigoConta(Integer.parseInt(codigoConta));
				
			c = daoC.consultar(c);
					
			request.setAttribute("conta", c);
			request.setAttribute("operacao", "    Alterar    ");
			
			
		//EXCLUSÃO	
		} else if (comando.equals("excluir")){
			
			c.setCodigoConta(Integer.parseInt(codigoConta));
				
			c = daoC.consultar(c);
					
			daoC.excluir(c);
					
			request.setAttribute("operacao", "    Cadastrar    ");
			request.setAttribute("ativa", "");
					
		}
		
			
		ArrayList listaConta = daoC.consultarTodos();
		
		DAOBanco daoB = daoF.getDAOBanco();
		ArrayList listaBanco = daoB.consultarTodos();
		
		DAOPagamento daoP = daoF.getDAOPagamento();
		ArrayList listaPagamento = daoP.consultarTodos();
					
		request.setAttribute("listaConta", listaConta);
		request.setAttribute("listaBanco", listaBanco);
		request.setAttribute("listaPagamento", listaPagamento);
			
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaConta.jsp");
		rD.forward(request, response);	
			 
		
	}   	  	    
}