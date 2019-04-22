package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Uf;

import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAORelatorioMaisVisitados;
import dao.DAOUf;

 public class ControleRelatorioMaisVisitados extends HttpServlet {
      	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigoUf = request.getParameter("codigoUf");
		String codigoCidade = request.getParameter("codigoCidade");		
		String codigoCategoria = request.getParameter("codigoCategoria");
		String nivelCategoria = request.getParameter("nivelCategoria");
		String operacao = request.getParameter("operacao");
		String popula = request.getParameter("popula");
		String comando = request.getParameter("comando");
		
		String liberado = request.getParameter("liberado");
		String pendente = request.getParameter("pendente");
		String bloqueado = request.getParameter("bloqueado");
		
		Uf uf = new Uf();
		
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		DAOUf daoUf = df.getDAOUf();
		
		if (popula != null && popula.equals("populaCombo")){
			
			if (!codigoUf.equals("Estado")){
				
				if(popula!=null && popula.equalsIgnoreCase("populaCombo")){
										
					Cidade cidade = new Cidade();
					
					DAOCidade daoCidade = df.getDAOCidade();
					
					//populando a combo cidade e marcando a uf selecionada				
					uf.setCodigoUf(codigoUf);
					uf = daoUf.consultar(uf);
									
					cidade.setUf(uf);
					
					ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
					request.setAttribute("listaCidade", listaCidade);
					request.setAttribute("uf", uf);
								
				}
			}else{
				uf.setDescricaoUf("Estado");
			}
		}else if (comando != null && comando.trim().equals("Gerar")){
			
			DAORelatorioMaisVisitados daoMaisVisitados = df.getDAORelatorioMaisVisitados();
			
			Anuncio anuncio = new Anuncio();
			Categoria categoria = new Categoria();
			Cidade cidade = new Cidade();

			uf.setCodigoUf(codigoUf);
			if (codigoCidade.equals("Cidade"))
				codigoCidade = "0";
			
			cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
			
			if(codigoCategoria.equals("")){			
				//para cadastro de um pai
				categoria.setCodigoCategoria(0);
				categoria.setNivelCategoria(1);			
			}else{			
				//para cadastro de um filho			
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));
			}		
						
			anuncio.setUf(uf);
			anuncio.setCidade(cidade);
			anuncio.setCategoria(categoria);
			
			String status = liberado + pendente + bloqueado;
			
			ArrayList relMaisVisitados = daoMaisVisitados.consultar(anuncio, status);
			
			if (relMaisVisitados != null && relMaisVisitados.size() > 0){
				try{
					
					JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(relMaisVisitados);
					
					JasperFillManager.fillReportToFile("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelMaisVisitados.jasper", new HashMap(), jr);
					JasperViewer.viewReport("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/RelMaisVisitados.jrprint", false, false);
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}else{
				
				request.setAttribute("tamanho", "0");
				
			}
			
		}
		
		if (liberado != null)
			request.setAttribute("liberado", "checked");
		if (pendente != null)
			request.setAttribute("pendente", "checked");
		if (bloqueado != null)
			request.setAttribute("bloqueado", "checked");
		
		ArrayList listaUf = daoUf.consultarTodos();			
		request.setAttribute("listaUf", listaUf);
				
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaRelatorioMaisVisitados.jsp");
		rD.forward(request, response);
	}   	  	    
}