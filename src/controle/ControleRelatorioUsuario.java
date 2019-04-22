package controle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.DAORelatorioUsuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.view.*;

import modelo.Usuario;

 public class ControleRelatorioUsuario extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String usuario = (String) request.getParameter("usuario");
		String gestor = (String) request.getParameter("gestor");
		String dono = (String) request.getParameter("dono");
		
		String habilitado = (String) request.getParameter("habilitado");
		String desabilitado = (String) request.getParameter("desabilitado");
		
		String perfil = usuario + gestor + dono;
		String status = habilitado + desabilitado;
		
		DAOFactory daoF = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAORelatorioUsuario daoRelUsuario = daoF.getDAORelatorioUsuario();
		
		ArrayList relUsuario = daoRelUsuario.consultar(perfil, status);
		
		if (relUsuario != null && relUsuario.size() > 0){
			try{
				
				JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(relUsuario);
				
				JasperFillManager.fillReportToFile("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelUsuario.jasper", new HashMap(), jr);
				JasperViewer.viewReport("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelUsuario.jrprint", false, false);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}else{
			
			request.setAttribute("tamanho", "0");
			
		}
		
		if (usuario != null)
			request.setAttribute("usuario", "checked");
		if (gestor != null)
			request.setAttribute("gestor", "checked");
		if (dono != null)
			request.setAttribute("dono", "checked");
		if (habilitado != null)
			request.setAttribute("habilitado", "checked");
		if (desabilitado != null)
			request.setAttribute("desabilitado", "checked");
		
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaRelatorioUsuario.jsp");
		rD.forward(request, response);	
		
	}   	
	
}