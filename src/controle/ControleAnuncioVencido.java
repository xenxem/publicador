package controle;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOAnuncio;
import dao.DAOAnuncioVencido;
import dao.DAOCategoria;
import dao.DAOCidade;
import dao.DAOFactory;
import dao.DAOImagem;
import dao.DAOOcorrencia;
import dao.DAOPagamento;
import dao.DAOUf;
import dao.DAOUsuario;
import dao.DAOValor;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.Ocorrencia;
import modelo.Pagamento;
import modelo.Uf;
import modelo.Usuario;
import modelo.Valor;

 public class ControleAnuncioVencido extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    public ControleAnuncioVencido() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");
		String codigoAnuncio = request.getParameter("codigoAnuncio");
		String comando = request.getParameter("comando");
		
		//verificando se usuário está logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		
		//fábrica
		DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		
		//dao
		DAOAnuncio daoAnuncio = df.getDAOAnuncio();
		
		//modelo
		Anuncio anuncio = new Anuncio();
		
		//VISUALIZAR ANUNCIO		
		if (operacao.equals("visualizar")){
			
			//dao
			DAOImagem daoImagem = df.getDAOImagem();
			DAOCidade daoCidade = df.getDAOCidade();
			DAOUf daoUf = df.getDAOUf();
			DAOAnuncioVencido daoAVencido = df.getDAOAnuncioVencido();
			DAOCategoria daoCategoria = df.getDAOCategoria();
			
			//modelo
			Imagem imagem = new Imagem();
			Uf uf = new Uf();
			Cidade cidade = new Cidade();
			Usuario usuario = new Usuario();
			Categoria categoria = new Categoria();
			
			//anúncio
			anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
			anuncio = daoAnuncio.consultar(anuncio);				
						
			//uf
			uf.setCodigoUf(anuncio.getUf().getCodigoUf());					
			uf = daoUf.consultar(uf);
						
			//cidade
			cidade.setCodigoCidade(anuncio.getCidade().getCodigoCidade());
			cidade = daoCidade.consultar(cidade);
						
			//imagens
			imagem.setAnuncio(anuncio);					
			ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);
			
			//usuario
			usuario.setCodigoUsuario(anuncio.getUsuario().getCodigoUsuario());
			usuario = daoAVencido.consultarEmail(usuario);
			
			//categoria
			categoria.setCodigoCategoria(anuncio.getCategoria().getCodigoCategoria());
			categoria = daoCategoria.consultar(categoria);
			
			//setando atributos
		    request.setAttribute("anuncio",anuncio);
		    request.setAttribute("uf", uf);
			request.setAttribute("cidade", cidade);
			request.setAttribute("email", usuario);
			request.setAttribute("categoria", categoria);
			request.setAttribute("listaImagem",listaImagem);
			
		//EXCLUIR ANUNCIO	
		}else if (operacao.equals("excluir") || (comando != null && comando.trim().equals("Excluir"))){
			
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
						System.out.println("falha em exclusão arquivo controle anúncio vencido");
					}
				}
			}
			
			//Excluindo anúncio
			daoAnuncio.excluir(anuncio);
			request.setAttribute("operacao", "Iniciar");
			
		}
		
		DAOAnuncioVencido daoAVencido = df.getDAOAnuncioVencido();
		
		ArrayList listaAVencido = daoAVencido.consultarTodos();
		
		request.setAttribute("listaAVencido", listaAVencido);
		
		RequestDispatcher rD = request.getRequestDispatcher("/visao/TelaAnuncioVencido.jsp");
		rD.forward(request, response);	
		
	}   	  	    
}