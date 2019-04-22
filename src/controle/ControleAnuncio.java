package controle;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;


import modelo.*;
import dao.*;


 public class ControleAnuncio extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}  	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//parâmetros
		String titulo = request.getParameter("titulo");
		String descricaoAnuncio = request.getParameter("descricaoAnuncio");
		String codigoCategoria = request.getParameter("codigoCategoria");
		String nivelCategoria = request.getParameter("nivelCategoria");
		String descricaoCategoria = request.getParameter("descricaoCategoria");
		String codigoAnuncio = request.getParameter("codigoAnuncio");
		String codigoUf = request.getParameter("codigoUf");
		String codigoCidade = request.getParameter("codigoCidade");		
		String operacao = request.getParameter("operacao");
		String popula = request.getParameter("popula");
		
		//verificando se usuário está logado
		HttpSession sessao = request.getSession(true);
		Usuario u = (Usuario) sessao.getAttribute("usuario");
				
				
					/*
					Enumeration e = request.getParameterNames();
					
					while(e.hasMoreElements()){
						String parametro = (String) e.nextElement();
						String value = request.getParameter(parametro);
						System.out.println("Nome "+ parametro + " valor "+ value);
					}*/
			
			//fábrica
			DAOFactory df = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
			
			//daos
			DAOAnuncio daoAnuncio = df.getDAOAnuncio();
			DAOCidade daoCidade = df.getDAOCidade();
			DAOUf daoUf = df.getDAOUf();
			DAOImagem daoImagem = df.getDAOImagem();
			DAOOcorrencia daoOcorrencia = df.getDAOOcorrencia();
			DAOPagamento daoPagamento = df.getDAOPagamento();
			DAOValor daoValor = df.getDAOValor();
			DAOConta daoConta = df.getDAOConta();
			DAOBanco daoBanco = df.getDAOBanco();
			DAOUsuario daoUsuario = df.getDAOUsuario();
			DAOLiberarAnuncio daoLiberaAnuncio = df.getDAOLiberarAnuncio();
			
			//modelos
			Anuncio anuncio = new Anuncio();
			Uf uf = new Uf();
			Cidade cidade = new Cidade();
			Imagem imagem = new Imagem();
			
			Usuario usuario = new Usuario();		
			usuario.setCodigoUsuario(u.getCodigoUsuario());
			
			Categoria categoria = new Categoria();			
			Ocorrencia ocorrencia = new Ocorrencia();
			Pagamento pagamento = new Pagamento();
			Valor valor = new Valor();
			Conta conta = new Conta();
			Banco banco = new Banco();			
			LiberaAnuncio liberaAnuncio = new LiberaAnuncio();
			
			
			//Locale locale = new Locale("pt","BR"); 
			GregorianCalendar calendar = new GregorianCalendar();			
			
			//SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);			
			//String dataInicio = formatador.format(calendar.getTime()); 			
			//System.out.println(dataInicio);
		
			
			if(popula!=null && popula.equalsIgnoreCase("populaCombo")){
							
				//setando código de anúncio se não for nulo				
				if(codigoAnuncio!=null){
					anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
				}
				
				anuncio.setTitulo(titulo);
				anuncio.setDescricaoAnuncio(descricaoAnuncio);
				
				//categoria			
				categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
				categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));
				categoria.setDescricaoCategoria(descricaoCategoria);
				anuncio.setCategoria(categoria);			
							
				//populando a combo cidade e marcando a uf selecionada				
				uf.setCodigoUf(codigoUf);							
								
				cidade.setUf(uf);
				
				anuncio.setUf(uf);
				anuncio.setCidade(cidade);		
				
				
				
				request.setAttribute("anuncio", anuncio);				
				request.setAttribute("operacao", operacao);
				
				ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
				request.setAttribute("listaCidade", listaCidade);
				
				imagem.setAnuncio(anuncio);					
				ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);					
				request.setAttribute("listaImagem",listaImagem);	
	
				
			}else{		
				if(operacao.equalsIgnoreCase("cadastrar")){
				
					//cadastrando um anúncio				
					anuncio.setTitulo(titulo);
					anuncio.setDescricaoAnuncio(descricaoAnuncio);
					anuncio.setAcessos(0);
					anuncio.setStatus("P");					
					anuncio.setDataInicio(new Timestamp(calendar.getTimeInMillis()));
					
					//instância de calendar
					Calendar c = Calendar.getInstance();		
					
					//acrescentando número de dias à data
					//c.add(c.DATE, 30);												
					//anuncio.setDataFim(new Timestamp(c.getTimeInMillis()));
					
					//categoria
					categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
					categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));					
					anuncio.setCategoria(categoria);
					
					//uf
					uf.setCodigoUf(codigoUf);
					anuncio.setUf(uf);
					
					//cidade
					cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
					anuncio.setCidade(cidade);
					
					//usuário
					anuncio.setUsuario(usuario);			
					
					//cadastrando um anúncio
					daoAnuncio.cadastrar(anuncio);
					
					//cadastrando em pagamento
					anuncio = daoAnuncio.consultarUltimo(anuncio);				
					pagamento.setAnuncio(anuncio);				
					
					//número para identificaão
					Random rnd = new Random();					
					pagamento.setNumeroParaIdentificacao((int)Math.round((rnd.nextDouble()* 1500)));
					
					//verificando conta ativa
					conta = daoConta.consultarAtiva();				
					pagamento.setConta(conta);				
					
					//consultando valor
					valor = daoValor.consultarAtivo();					
					
					//valor e data
					pagamento.setValor(valor);				
					pagamento.setData(new Timestamp(Calendar.getInstance().getTimeInMillis()));			
					daoPagamento.cadastrar(pagamento);
								
					//gerando ocorrência					
					ocorrencia.setAnuncio(anuncio);
					ocorrencia.setTipoOcorrencia("C");
					ocorrencia.setData(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					daoOcorrencia.cadastrar(ocorrencia);
					
					//limpando dados do anúncio
					anuncio = null;
					request.setAttribute("anuncio",anuncio);
				
				}else if(operacao.trim().equalsIgnoreCase("alterar")){
						
						//anuncio
						anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
						anuncio.setTitulo(titulo);					
						anuncio.setDescricaoAnuncio(descricaoAnuncio);
						anuncio.setStatus("P");
						
						//Mudando status para pendente
						liberaAnuncio.setAnuncio(anuncio);
						daoLiberaAnuncio.mudarStatus(anuncio);
						
						
						//uf e cidade
						uf.setCodigoUf(codigoUf);
						anuncio.setUf(uf);
						cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
						anuncio.setCidade(cidade);					
						
						//categoria
						categoria.setCodigoCategoria(Integer.parseInt(codigoCategoria));
						categoria.setNivelCategoria(Integer.parseInt(nivelCategoria));					
						anuncio.setCategoria(categoria);
						
						//usuário
						anuncio.setUsuario(usuario);
												
						//alterando anúncio
						daoAnuncio.alterar(anuncio);												
						
						//gerando ocorrência
						ocorrencia.setAnuncio(anuncio);
						ocorrencia.setTipoOcorrencia("A");
						ocorrencia.setData(new Timestamp(Calendar.getInstance().getTimeInMillis()));
						daoOcorrencia.cadastrar(ocorrencia);		
						
										
				}else if (operacao.equalsIgnoreCase("editar")){			
						
						//carregando os dados do anúncio no formulário				
						anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
						anuncio = daoAnuncio.consultar(anuncio);				
						request.setAttribute("anuncio",anuncio);
						
						//uf
						uf.setCodigoUf(anuncio.getUf().getCodigoUf());					
						cidade.setUf(uf);
						
						//imagens
						imagem.setAnuncio(anuncio);					
						ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);					
						request.setAttribute("listaImagem",listaImagem);		
						
						//cidade
						ArrayList listaCidade = daoCidade.consultarUfCidade(cidade);
						request.setAttribute("listaCidade", listaCidade);					
						request.setAttribute("operacao","Alterar");
						
				}else if (operacao.equalsIgnoreCase("excluir")) {
						
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
				
					
				}else if (operacao.equalsIgnoreCase("incluirFoto")){
					
					request.setAttribute("operacao", "incluirFotos");
					
					if(codigoAnuncio!=null){
						
						anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
								
						imagem.setAnuncio(anuncio);					
						ArrayList listaImagem = daoImagem.consultarImagemAnuncio(imagem);					
						request.setAttribute("listaImagem",listaImagem);		
						
						anuncio.setCodigoAnuncio(Integer.parseInt(codigoAnuncio));
						request.setAttribute("anuncio", anuncio);
					}
				}
				
			}		
			
					
			//populando uf			
			ArrayList listaUf = daoUf.consultarTodos();			
			request.setAttribute("listaUf", listaUf);	

			//exibição controlada de anúncios, de acordo com código
			ArrayList listaAnuncio = daoUsuario.consultarAnuncios(usuario);
			request.setAttribute("listaAnuncio", listaAnuncio);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/visao/TelaAnuncio.jsp");
			rd.forward(request, response);
					
		}
	
 	
 }
  