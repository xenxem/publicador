package controle;

import java.io.IOException;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;
import dao.*;
import modelo.Usuario;
import modelo.Mail;



/**
 * Servlet implementation class for Servlet: ControleUsuario
 *
 */
 public class ControleUsuario extends HttpServlet{
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando = request.getParameter("comando");
		String nome = request.getParameter("nome");
		String apelido = request.getParameter("apelido");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
		
				
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOUsuario daoUsuario = df.getDAOUsuario();
		Usuario usuario = new Usuario();
		
		usuario.setCodigoUsuario(codigoUsuario);
		usuario.setNome(nome);
		usuario.setApelido(apelido);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		
		
		String msg ="";			
			
		if(comando.equals("Cadastrar")){
			
			Usuario u  = daoUsuario.consultarApelido(usuario);
			 
			if(u==null){				
				u = daoUsuario.consultarEmail(usuario);				
				if(u==null){					
									
						new Mail(email,Mail.SIMPLES);					
						usuario.setStatusValidacao("D");
						usuario.setPerfil("U");
						Random rnd = new Random();
						usuario.setNumeroValidacao((int)Math.round((rnd.nextDouble()* 1500)));
						daoUsuario.cadastrar(usuario);
						msg = "sucesso";
					
				}else{					
					msg = "E-mail já existente na base!";									
				}				
			}else{				
				msg = "Apelido já existente na base!";
			}			
		}else 
			if(comando.trim().equals("Alterar")){
			
			Usuario u = daoUsuario.consultarApelido(usuario);
			
			if(u!=null){				
				msg = "Apelido já existente na base!";
			}else{
				msg = "sucesso";
				daoUsuario.alterar(usuario);
				HttpSession sessao = request.getSession(true);
				usuario = (Usuario) sessao.getAttribute("usuario");
				
				usuario.setCodigoUsuario(codigoUsuario);
				usuario.setNome(nome);
				usuario.setApelido(apelido);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				
				sessao.setAttribute("usuario", usuario);
				request.setAttribute("operacao", "Alteracao");
			}			
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaContaUsuario.jsp?msg="+msg);
		rd.forward(request, response);
		
	}   	  	    
}