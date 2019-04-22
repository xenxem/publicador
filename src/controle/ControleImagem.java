package controle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import dao.*;
import modelo.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


 public class ControleImagem extends HttpServlet{  
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//parâmtros
		String comando = request.getParameter("comando");
		String arquivo = request.getParameter("arquivo");
		int codigoImagem = Integer.parseInt(request.getParameter("codigoImagem"));
		int codigoAnuncio = Integer.parseInt(request.getParameter("codigoAnuncio"));
		String operacao = request.getParameter("operacao");
		
		//fábrica 
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		//uf
		DAOUf daoUf = df.getDAOUf();
		
		//Cidade
		DAOCidade daoCidade = df.getDAOCidade();
		
		//imagem
		DAOImagem daoImagem = df.getDAOImagem();		
		Imagem imagem = new Imagem();
		imagem.setCodigoImagem(codigoImagem);
				
		//anúncio
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
		Anuncio anuncio = new Anuncio();		
		anuncio.setCodigoAnuncio(codigoAnuncio);
		
		
		if(comando.equals("excluir")){
			try
			{	
				
				System.out.println(this.getServletContext().getServletContextName());
				
				//excluído arquivo			
				File arq = new File("C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/imagem/"+arquivo);
				
				//se sucesso na exclusão excluí da base
				if(arq.delete()){					
					daoImagem.excluir(imagem);				
				}
				
				
				//área de fotos
				request.setAttribute("operacao", operacao);				
				request.setAttribute("anuncio",anuncio);											
				
			}
			catch(Exception e) {			
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp?erro=excluirarquivo");
				rd.forward(request, response);
				
			}
			
		}else if(comando.equals("destaque")){
			
			
			//para localizar o destaque atual
			imagem.setAnuncio(anuncio);
			
			//localizando a foto destaque atual e mudando seu status para N
			ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);
			
			for(int i=0; i < listaImagem.size(); i++){
				Imagem img = (Imagem) listaImagem.get(i);
				if(img.getDestaque().equals("S")){					
					img.setDestaque("N");				
					daoImagem.alterar(img);
				}
			}		
			
			//salvando a nova foto destaque
			imagem.setDestaque("S");
			imagem.setImgNome(arquivo);			
			daoImagem.alterar(imagem);	
			
			request.setAttribute("operacao", "Alterar");			
		}
		
		anuncio = daoAnuncio.consultar(anuncio);
		request.setAttribute("anuncio",anuncio);
		
		//imagens do anúncio
		imagem.setAnuncio(anuncio);			
		ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);
		request.setAttribute("listaImagem", listaImagem);
		
		//populando uf		
		ArrayList listaUf = daoUf.consultarTodos();				
		request.setAttribute("listaUf", listaUf);	
		
		//setando uf do anúncio
		Uf uf = new Uf();
		uf.setCodigoUf(anuncio.getUf().getCodigoUf());
		
		//setando uf para cidade
		Cidade cidade = new Cidade();
		cidade.setUf(uf);
		
		//verificando as cidades desta uf
		ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
		request.setAttribute("listaCidade", listaCidade);
		
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		
		
		
		DAOUsuario daoUsuario = df.getDAOUsuario();				
		//exibição controlada de anúncios, de acordo com código
		ArrayList listaAnuncio = daoUsuario.consultarAnuncios(usuario);
		request.setAttribute("listaAnuncio", listaAnuncio);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp");
		rd.forward(request, response);	
	} 
	 
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//verificando o tamnho do arquivo		
		int tamanho = (1 * 1024 * 1024);
		
		//Fábrica
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		HttpSession sessao = request.getSession(false);
		
		//Usuário
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");		
		DAOUsuario daoUsuario = df.getDAOUsuario();	
		
		//Anúncio
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();		
		String codigoA = (String) sessao.getAttribute("codigoA");
		Anuncio a = new Anuncio();
		a.setCodigoAnuncio(Integer.parseInt(codigoA));		
		Integer qtd  = daoAnuncio.consultarQtdImagem(a);
		System.out.println("ok");
		//Escapando da NullPointerException
		if(qtd==null)
			qtd=new Integer(0);
		
		if(qtd.intValue() <= 3 ){
		
			if(request.getContentLength() <= tamanho){				
					//Cria um request do tipo Multipart, para receber arquivos. (Tamanho máximo 1MB).		
					MultipartRequest mr = new MultipartRequest(request,"C:/Users/26213947/workspace/publicadoranuncio/WebContent/visao/imagem", 1 * 1024 * 1024, new DefaultFileRenamePolicy());
																				
					File arquivo = mr.getFile("arquivo");					
							
					//parâmetros
					String codigoAnuncio = mr.getParameter("codigoAnuncio");
					String destaque = mr.getParameter("destaque");					
														
					
									
					DAOUf daoUf = df.getDAOUf();
					DAOImagem daoImagem = df.getDAOImagem();
							
					Anuncio anuncio = new Anuncio();
							
					anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
							
					Imagem imagem = new Imagem();	
							
					//arquivo é renomeado automaticamente caso ele exista
					imagem.setImgNome(""+arquivo.getAbsoluteFile().getName());				
							
					imagem.setAnuncio(anuncio);								
							
					ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);
							
							
					if(listaImagem.size()==0){
						imagem.setDestaque("S");					
					}else{
						imagem.setDestaque("N");
					}
							
					daoImagem.cadastrar(imagem);		
							
					//populando uf				
					//ArrayList listaUf = daoUf.consultarTodos();				
					//request.setAttribute("listaUf", listaUf);	
					
									
					request.setAttribute("operacao", "incluirFotos");				
					
					request.setAttribute("anuncio",anuncio);
					request.setAttribute("listaImagem", listaImagem);			
						
					//exibição controlada de anúncios, de acordo com código
					ArrayList listaAnuncio = daoUsuario.consultarAnuncios(usuario);
					request.setAttribute("listaAnuncio", listaAnuncio);			
					
					RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp");
					rd.forward(request, response);	
							
				}else{
					
					request.setAttribute("operacao", "incluirFotos");			
					
					//exibição controlada de anúncios, de acordo com código							
					ArrayList listaAnuncio = daoUsuario.consultarAnuncios(usuario);
					request.setAttribute("listaAnuncio", listaAnuncio);								
		
					RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp?erro=erro");
					rd.forward(request, response);
					
				}	
			}else{
				request.setAttribute("operacao", "incluirFotos");		
				
				Anuncio a2 = new Anuncio();
				a2.setCodigoAnuncio(Integer.parseInt(codigoA));
				Imagem i = new Imagem();
				i.setAnuncio(a2);
				
				DAOImagem daoI = df.getDAOImagem();
				ArrayList listaImagem = daoI.consultarImagemAnuncio(i);
				request.setAttribute("listaImagem", listaImagem);
				
				//exibição controlada de anúncios, de acordo com código							
				ArrayList listaAnuncio = daoUsuario.consultarAnuncios(usuario);
				request.setAttribute("listaAnuncio", listaAnuncio);								
	
				RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp?msg=qtdMaximaImagem");
				rd.forward(request, response);

			
			}

	}
	
	
}