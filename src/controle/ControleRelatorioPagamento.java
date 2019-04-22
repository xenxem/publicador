package controle;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.DAOFactory;
import dao.DAORelatorioPagamento;
import dao.DAORelatorioUsuario;

 public class ControleRelatorioPagamento extends HttpServlet {
   	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inicio = (String) request.getParameter("inicio");
		String fim = (String) request.getParameter("fim");
		String ordenacao = (String) request.getParameter("ordenacao");
		
		DAOFactory daoF = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAORelatorioPagamento daoRelPagamento = daoF.getDAORelatorioPagamento();
		
		ArrayList relPagamento = daoRelPagamento.consultar(inicio, fim, ordenacao);
		
		if (relPagamento != null && relPagamento.size() > 0){
			try{
				
				JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(relPagamento);
				
				JasperFillManager.fillReportToFile("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelPagamento.jasper", new HashMap(), jr);
				JasperViewer.viewReport("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelPagamento.jrprint", false, false);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}else{
			
			request.setAttribute("tamanho", "0");
			
		}
		
		request.setAttribute("inicio", inicio);
		request.setAttribute("fim", fim);
		
		request.setAttribute("data", "");
		request.setAttribute("idDeposito", "");
		request.setAttribute("valor", "");
		
		if (ordenacao.equals("data"))
			request.setAttribute("data", "checked");
		else if (ordenacao.equals("idDeposito"))
			request.setAttribute("idDeposito", "checked");
		else
			request.setAttribute("valor", "checked");
		
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaRelatorioPagamento.jsp");
		rD.forward(request, response);	
		
	}   	
		  	    
}