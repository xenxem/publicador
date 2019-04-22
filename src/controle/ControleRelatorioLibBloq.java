package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import dao.DAOFactory;
import dao.DAORelatorioLibBloq;

 public class ControleRelatorioLibBloq extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String liberacao = (String)request.getParameter("liberacao");
		String bloqueio = (String)request.getParameter("bloqueio");
		String gestor = (String)request.getParameter("gestor");
		String inicio = (String)request.getParameter("inicio");
		String fim = (String)request.getParameter("fim");
				
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAORelatorioLibBloq daoRelatorioLibBloq = df.getDAORelatorioLibBloq();
		
		String tipo = liberacao + bloqueio;
		
		ArrayList relLibBloq = daoRelatorioLibBloq.consultar(tipo, gestor, inicio, fim);
		
		if (relLibBloq != null && relLibBloq.size() > 0){
			try{
				
				JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(relLibBloq);
				
				JasperFillManager.fillReportToFile("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelLibBloq.jasper", new HashMap(), jr);
				JasperViewer.viewReport("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelLibBloq.jrprint", false, false);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}else{
			
			request.setAttribute("tamanho", "0");
			
		}
		
		if (liberacao != null)
			request.setAttribute("liberacao", "checked");
					
		if (bloqueio != null)
			request.setAttribute("bloqueio", "checked");
		
		if (!gestor.equals("Gestor"))
			request.setAttribute("codigoGestor", gestor);
		
		request.setAttribute("inicio", inicio);
		request.setAttribute("fim", fim);
		
		ArrayList listaGestor = daoRelatorioLibBloq.consultarGestor();
		request.setAttribute("listaGestor", listaGestor);
		
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaRelatorioLibBloq.jsp");
		rD.forward(request, response);
		
	}   	  	    
}