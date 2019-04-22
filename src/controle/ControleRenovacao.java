package controle;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;

import modelo.Anuncio;
import modelo.Conta;
import modelo.Ocorrencia;
import modelo.Pagamento;
import modelo.Usuario;
import modelo.Valor;

import dao.DAOAnuncio;
import dao.DAOConta;
import dao.DAOFactory;
import dao.DAOOcorrencia;
import dao.DAOPagamento;
import dao.DAOValor;



 public class ControleRenovacao extends HttpServlet{
   
   
      	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
		Anuncio a = new Anuncio();
		a.setUsuario(u);
		
		String comando = request.getParameter("comando");
		
		
		String nomeArquivo = "TelaRenovacao.jsp";
		
		if(comando.equals("renovacao")){
			
			Calendar c = Calendar.getInstance();
			
			//consultando dados do anúncio
			int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));			
			a.setCodigoAnuncio(codigoAnuncio);		
			
			//ocorrencia
			Ocorrencia o = new Ocorrencia();
			o.setData(new Timestamp(c.getTimeInMillis()));
			o.setTipoOcorrencia("R");
			o.setAnuncio(a);
			DAOOcorrencia daoOcorrencia = df.getDAOOcorrencia();
			daoOcorrencia.cadastrar(o);
			
			Pagamento p = new Pagamento();
			
			//conta
			Conta conta = new Conta();
			DAOConta daoConta = df.getDAOConta();
			conta = daoConta.consultarAtiva();
			p.setConta(conta);
			
			//valor
			Valor v = new Valor();
			DAOValor daoValor = df.getDAOValor();
			v = daoValor.consultarAtivo();
			p.setValor(v);
			
			//anuncio
			p.setAnuncio(a);
			p.setData(new Timestamp(c.getTimeInMillis()));
			
			//gerando número para identificação
			Random rnd = new Random();
			p.setNumeroParaIdentificacao((int)Math.round((rnd.nextDouble()* 1500)));
			
			//cadastra um pagamento
			DAOPagamento daoPagamento = df.getDAOPagamento();
			daoPagamento.cadastrar(p);
			
			Pagamento p2 = new Pagamento();
			Anuncio a2 = new Anuncio();
			a2.setCodigoAnuncio(codigoAnuncio);
			p2.setAnuncio(a2);
			//consultando último pagamento gerado
			p2 = daoPagamento.consultarPagamentoGerado(p);	
			
			request.setAttribute("pgto", p2);
			nomeArquivo = "TelaPagamento.jsp";			
		}
		
		ArrayList listaAnuncio = daoAnuncio.consultarAnunciosRenovacao(a);
		request.setAttribute("listaAnuncio", listaAnuncio);
		RequestDispatcher rd = request.getRequestDispatcher("/visao/"+nomeArquivo);
		rd.forward(request, response);
		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}   	  	    
}