package controle;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOAnuncio;
import dao.DAOCategoria;
import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAOImagem;
import dao.DAOLiberarAnuncio;
import dao.DAOPagamento;
import dao.DAOUf;
import dao.DAOValor;
import dao.DAOUsuario;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.LiberaAnuncio;
import modelo.Pagamento;
import modelo.Uf;
import modelo.Usuario;
import modelo.Valor;

 public class ControleLiberarAnuncio extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	Vector listaAcesso = new Vector();
	 
	public ControleLiberarAnuncio() {
		super();
	}   	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando = request.getParameter("comando");
		String codigoAnuncio = request.getParameter("codigoAnuncio");
		String operacao = request.getParameter("operacao");
		
		//verificando se usuário está logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		
		//fábrica
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		//dao
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
		DAOPagamento daoPagamento = df.getDAOPagamento();
		DAOLiberarAnuncio daoLiberarAnuncio = df.getDAOLiberarAnuncio();
		
		//modelo
		Anuncio anuncio = new Anuncio();		
		
		//LIBERAR ANÚNCIO
		if (comando != null && comando.trim().equals("Liberar")){
			
		     	LiberaAnuncio la = new LiberaAnuncio();
				GregorianCalendar calendar = new GregorianCalendar();
				
				anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
				anuncio.setStatus("L");
				anuncio.setDataInicio(new Timestamp(calendar.getTimeInMillis()));
				
				//instância de calendar
				Calendar c = Calendar.getInstance();		
				
				//acrescentando número de dias à data
				c.add(c.DATE, 30);												
				anuncio.setDataFim(new Timestamp(c.getTimeInMillis()));
				
				daoLiberarAnuncio.liberarAnuncio(anuncio);
				
				la.setAnuncio(anuncio);
				la.setUsuario(u);
				la.setData(anuncio.getDataInicio());
				la.setTipo("L");
				
				daoLiberarAnuncio.cadastrar(la);
						
		//BLOQUEAR ANÚNCIO	
		}else if (comando != null && comando.trim().equals("Bloquear")){
			
			LiberaAnuncio la = new LiberaAnuncio();
			GregorianCalendar calendar = new GregorianCalendar();
			
			anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
			anuncio.setStatus("B");
			
			daoLiberarAnuncio.bloquearAnuncio(anuncio);
			
			la.setAnuncio(anuncio);
			la.setUsuario(u);
			la.setData(new Timestamp(calendar.getTimeInMillis()));
			la.setTipo("B");
			
			daoLiberarAnuncio.cadastrar(la);	
			
			boolean achouAnuncio = false;
			
			if (!listaAcesso.isEmpty()){
				
				for(int i = 0; !achouAnuncio && i < listaAcesso.size(); i++){
					
					Anuncio a2 = (Anuncio) listaAcesso.get(i);
					
					if (a2.getCodigoAnuncio() == Integer.parseInt(codigoAnuncio) &&
						u.getCodigoUsuario() == a2.getUsuario().getCodigoUsuario()){
						
						listaAcesso.remove(i);
						achouAnuncio = true;
						
					}
					
				}
				
			}
			
		//EXCLUIR ANÚNCIO
		}else if (comando != null && comando.trim().equals("Excluir")){
			
			DAOImagem daoImagem = df.getDAOImagem();
			
			Imagem imagem = new Imagem();
			
			anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
			imagem.setAnuncio(anuncio);
			
			
			//verificando as imagens do anúncio
			ArrayList listaImg = daoImagem.consultarImagemAnuncio(imagem);
			
			if(listaImg!=null){					
				
				//Excluindo arquivos e imagens
				for(int i=0; i < listaImg.size(); i++){
					
					Imagem img = new Imagem();
					img = (Imagem) listaImg.get(i);		
					try{
						File arq = new File("/anuncio/publicadoranuncio/WebContent/visao/imagem/"+img.getImgNome());
					
						if(arq.delete()){
							imagem.setCodigoImagem(img.getCodigoImagem());
							daoImagem.excluir(imagem);
						}
					}catch(Exception e){
						System.out.println("falha em exclusão arquivo controle anúncio");
					}
				}
			}	
			
		
			//Excluindo anúncio
			daoAnuncio.excluir(anuncio);
			
			request.setAttribute("operacao", "Iniciar");
			
		//VISUALIZAR ANÚNCIO	
		}else if (operacao.equals("visualizar")){
			
			boolean achouAnuncio = false;
			Usuario usu = new Usuario();
						
			if (!listaAcesso.isEmpty()){
				
				for(int i = 0; !achouAnuncio && i < listaAcesso.size(); i++){
					
					Anuncio a2 = (Anuncio) listaAcesso.get(i);
					
					if (a2.getCodigoAnuncio() == Integer.parseInt(codigoAnuncio) &&
						u.getCodigoUsuario() != a2.getUsuario().getCodigoUsuario()){
						
						usu.setCodigoUsuario(a2.getUsuario().getCodigoUsuario());
						usu = daoLiberarAnuncio.consultarUsuario(usu);
						achouAnuncio = true;
						
					}
					
				}
				
			}
			
			if (!achouAnuncio){
				
				Anuncio a = new Anuncio();
				a.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
				
				Usuario usuario = new Usuario();
				usuario.setCodigoUsuario(u.getCodigoUsuario());
				a.setUsuario(usuario);
				
				listaAcesso.addElement(a);
				
				//dao
				DAOImagem daoImagem = df.getDAOImagem();
				DAOCidade daoCidade = df.getDAOCidade();
				DAOUf daoUf = df.getDAOUf();
				DAOPagamento daoPag = df.getDAOPagamento();
				DAOValor daoValor = df.getDAOValor();
				DAOCategoria daoCategoria = df.getDAOCategoria();
				
				//modelo
				Imagem imagem = new Imagem();
				Uf uf = new Uf();
				Cidade cidade = new Cidade();
				Pagamento pagamento = new Pagamento();
				Valor valor = new Valor();
				Categoria categoria = new Categoria();
				
				//anúncio
				anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
				anuncio = daoAnuncio.consultar(anuncio);
				
				String status = new String();			
				if (anuncio.getStatus().equals("B"))
					status = "B";
				else 
					status = "P";
				
				//uf
				uf.setCodigoUf(anuncio.getUf().getCodigoUf());					
				uf = daoUf.consultar(uf);
							
				//cidade
				cidade.setCodigoCidade(anuncio.getCidade().getCodigoCidade());
				cidade = daoCidade.consultar(cidade);
							
				//imagens
				imagem.setAnuncio(anuncio);					
				ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);
				
				//pagamento
			    pagamento = daoPag.ConsultarValor(anuncio);
				
				//valor
			    valor.setCodigoValor(pagamento.getValor().getCodigoValor());
			    valor = daoValor.consultar(valor);
			    
			    //categoria
			    categoria.setCodigoCategoria(anuncio.getCategoria().getCodigoCategoria());
			    categoria = daoCategoria.consultar(categoria);
				
				//setando atributos
			    request.setAttribute("anuncio",anuncio);
			    request.setAttribute("uf", uf);
				request.setAttribute("cidade", cidade);
				request.setAttribute("idDeposito", pagamento);
				request.setAttribute("valor", valor);
				request.setAttribute("listaImagem",listaImagem);
				request.setAttribute("categoria", categoria);
				request.setAttribute("status", status);
				request.setAttribute("operacao", "Visualizar");
				
			}else{
				
				request.setAttribute("usuario", usu);
				
			}	
			
		}		

		ArrayList listaAnuncio = daoLiberarAnuncio.consultarPendentes();		
		
		request.setAttribute("listaAnuncio", listaAnuncio);

		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaLiberarAnuncio.jsp");
		rd.forward(request, response);
		
	}   	  	    
}