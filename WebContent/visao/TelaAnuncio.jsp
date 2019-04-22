<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Imagem"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%! ArrayList listaImagem; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anúncio</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

<script>
	//código passados ao se clicar em gategorias
	function setCodigos(c,n,t){
		document.FormAnuncio.codigoCategoria.value = c;
		document.FormAnuncio.nivelCategoria.value = n;		
		document.getElementById("descricaoCategoria").innerHTML = t;
	}		
</script>
</head>
<body>
<%
	//para listar anúncios
	ArrayList listaAnuncio = (ArrayList) request.getAttribute("listaAnuncio");

	Anuncio a = (Anuncio) request.getAttribute("anuncio");
	
	if(a==null){
		
		a = new Anuncio();
		
		a.setCodigoAnuncio(0);
		a.setTitulo("");
		a.setDescricaoAnuncio("");
		
		Categoria c = new Categoria();
		c.setCodigoCategoria(0);
		c.setNivelCategoria(0);
		c.setDescricaoCategoria("");		
		a.setCategoria(c);
		
		Usuario u2 = new Usuario();
		u2.setCodigoUsuario(0);
		a.setUsuario(u2);
		
		Uf uf = new Uf();
		uf.setCodigoUf("");
		a.setUf(uf);
		
		Cidade c2 = new Cidade();
		c2.setCodigoCidade(0);
		a.setCidade(c2);
		
		}
	

	String operacao = (String) request.getAttribute("operacao");
	if(operacao==null){
		operacao="Cadastrar";
	}

	 
%>


<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" cellpadding="0" cellspacing="0" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>

<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="color-border">
<tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados On-Line </b></p></td>
</tr>
<tr class="color-header">		          
 	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Fotos</b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Anúncios Cadastrados</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
<%if(operacao.equals("Alterar") || operacao.equals("incluirFotos")){ 

	

%>
	
	<table border="0" cellspacing="1" cellpadding="0" align="center" width="100%" class="color-title">
	<tr>
		<td align="center" class="label">
			<table border="0" cellspacing="0" cellpadding="2" align="center" width="100%" class="color-header">
			<tr>
	 			<td align="center" class="color-header">
	 				<p class="label"><font color="#000000">Anúncio selecionado</font></p>
	 			</td>
			</tr>
			<tr>
				<td align="center" class="label">
					<font color="#000000"><%=a.getCodigoAnuncio()%></font>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	
 	<table border="0"  cellspacing="0" cellpadding="2" align="center"  width="100%">
 	<tr>
 		<td align="center"> 			 			
 		</td>
 	</tr> 			
 	<%
		 listaImagem = (ArrayList) request.getAttribute("listaImagem");
 			
		 	if(listaImagem!=null){		 			 		
		 			
		 		for(int i=0; i<listaImagem.size(); i++){		 			
		 			Imagem imagem = new Imagem();
		 			imagem = (Imagem) listaImagem.get(i);	
		 					 			
			 		out.println("<tr align=\"center\">");
			 		
			 		if(imagem.getDestaque().equals("S")){
			 			if(listaImagem.size()==1){
			 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a><br><span class=\"label\">Destaque</span></td>");
					 		out.println("<td><a href=\"/publicadoranuncio/ControleImagem?operacao="+operacao+"&codigoImagem="+imagem.getCodigoImagem()+"&comando=excluir&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\"><img src=\"/publicadoranuncio/visao/imagem/lixeira.png\" alt=\"Excluir\"></a></td>");			 				
			 			}else{
			 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a><br><span class=\"label\">Destaque</span></td>");
			 			}			 							 		
		 			}else{
		 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a><br><span class=\"label\"><a href=\"/publicadoranuncio/ControleImagem?codigoImagem="+imagem.getCodigoImagem()+"&comando=destaque&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\">Mudar para destaque</a></span></td>");
				 		out.println("<td><a href=\"/publicadoranuncio/ControleImagem?operacao="+operacao+"&codigoImagem="+imagem.getCodigoImagem()+"&comando=excluir&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\"><img src=\"/publicadoranuncio/visao/imagem/lixeira.png\" alt=\"Excluir\"></a></td>");
		 			}			 		
			 		out.println("</tr>");
		 		}
		 	}else{
		 		
		 		out.println("<tr><td><p class=\"label\" align=\"center\">Fotos: Nenhuma</p></td></tr>");
		 	}
 	%>		
	</table>
	<%}
		if(!operacao.equals("incluirFotos")){
			if(listaImagem!=null){
				if(listaImagem.size() < 4){
					out.println("<a href=\"/publicadoranuncio/ControleAnuncio?operacao=incluirFoto&codigoAnuncio="+a.getCodigoAnuncio()+"\"><span class=\"color-header\"><br>Incluir Fotos</b></span></a><br>");
				}
			}else{
				out.println("<a href=\"/publicadoranuncio/ControleAnuncio?operacao=incluirFoto&codigoAnuncio="+a.getCodigoAnuncio()+"\"><span class=\"color-header\"><br>Incluir Fotos</b></span></a><br>");				
			}
		}
	%>	         
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" > 

<%
	
if(!operacao.equalsIgnoreCase("incluirFotos")){ %>
	<br>
	<form name="FormAnuncio" action="/publicadoranuncio/ControleAnuncio" method="post">
	<table class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; 
                      <b class="form-title">Anúncio </b></p></td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td width="274" colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td  width="77" align="right"><font class="label">Título</font></td>
                  <td align="left"> <input type="text" name="titulo" title="Título" class="caixa-texto" size="41" maxlength="40" value="<%=a.getTitulo()%>"> 
                  </td>
                </tr>
                <tr> 
                  <td  valign="top" align="right"><font class="label">Descrição</font></td>
                  <td align="left"><textarea name="descricaoAnuncio"   class="caixa-texto" title="Descrição" wrap="hard" rows=5 cols="42" onkeypress="javascript:limitaQtdeCaracter(this);"><%=a.getDescricaoAnuncio() %></textarea></td>
                </tr>
                <tr> 
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Uf</font></td>
                  <td align="left"> 
                  	<select name="codigoUf" title="Estado" class="text" onchange="setOperacao('populaCombo');">
                      <option value="">===Estado===</option>
                      <%
	                    	ArrayList listaUf = (ArrayList) request.getAttribute("listaUf");                    	
	                    	
	                    	Uf uf = (Uf) request.getAttribute("uf");
	                    	
	                    	if(uf==null){                    		
	                    		uf = new Uf();
	                    		uf.setCodigoUf("");                     		
	                    	}
	                    	
	                    	
	                    	if(listaUf!=null){
	                    		for(int i=0; i < listaUf.size(); i++){	                    			
	                    			Uf uf2 = new Uf();                   			
	                    			uf2 = (Uf) listaUf.get(i);	                    			
			                    	if(a.getUf().getCodigoUf().equals(uf2.getCodigoUf())){
			                    	  out.println("<option selected value=" + uf2.getCodigoUf() + ">"+ uf2.getDescricaoUf() +"</option>");
			                    	}else{
			                    		out.println("<option value=" + uf2.getCodigoUf() + ">" + uf2.getDescricaoUf()+ "</option>");	
			                    	}
	                    		}
	                    	}
	                    %>
                    </select> </td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Cidade</font></td>
                  <td align="left" > <select name="codigoCidade" title="Cidade" class="text" onchange="setCodigo(this.options[this.selectedIndex].value);" >
                      <option value="">===Cidade===</option>
                      <%
	                    	ArrayList listaCidade = (ArrayList) request.getAttribute("listaCidade");	                    	
	               			if(listaCidade!=null){	               				
	               				for(int i=0; i < listaCidade.size(); i++){	               					
	               					Cidade c2 = new Cidade();	
	               					c2 = (Cidade) listaCidade.get(i);	               				
			                    	if(c2.getCodigoCidade()== a.getCidade().getCodigoCidade()){
			                    	  out.println("<option selected value=" + c2.getCodigoCidade() + ">"+ c2.getDescricaoCidade()+" </option>");
			                    	}else{
			                    		out.println("<option value=" + c2.getCodigoCidade() + ">"+c2.getDescricaoCidade()+"</option>");	
			                    	}
	               				}
	               			}
	                    %>
                    </select> </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" ><div class="div" style="width:170pt; background:#ddddd5;" id="descricaoCategoria"><%=a.getCategoria().getDescricaoCategoria() %></div></td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label"></font></td>
                  <td valign="top" align="left" ><iframe src="/publicadoranuncio/visao/Arvoren.jsp" width="230" height="125" style="border-style:solid; border-color:#666666; border-width:1px;"  frameborder="0"></iframe></td>
                </tr>
              </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					<td align="center">
					
						<input type="submit" class="label" name="comando" value=<%=operacao %> onclick="return validacao(document.FormAnuncio);"> 
						<input type="button" class="label" name="limpar" value="   Limpar   " onclick="limpaForm(document.FormAnuncio);">
						
					   
						<input type="hidden" size="3" name="operacao"  value=<%=operacao %>>												
						<input type="hidden" name="codigoCategoria" value=<%=a.getCategoria().getCodigoCategoria()%>>
						<input type="hidden" name="nivelCategoria" value=<%=a.getCategoria().getNivelCategoria()%>>						
						<input type="hidden" name="codigoAnuncio" value=<%=a.getCodigoAnuncio()%>>
						<input type="hidden" name="descricaoCategoria" value="<%=a.getCategoria().getDescricaoCategoria() %>">
						<input type="hidden" name="popula">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>

<%}

	if(operacao.equalsIgnoreCase("incluirFotos")){
%>

	<br>
	<form action="/publicadoranuncio/ControleImagem" name="FormImagem" method="post" enctype="multipart/form-data">
	<table border="0" cellpadding="0" cellspacing="0" align="center">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
                <tr class="color-title"> 
                  <td align="center"> <p class="label">&nbsp; <b class="form-title"> 
                      Inclusão de Imagens (Até 4 imagens) </b></p></td>
                </tr>
                <tr class="color-header"> 
                  <th > <font class="label">Arquivo</font></th>
                </tr>
                <tr class="color-row" > 
                  <td height="20" align="center" > <input type="file" class="caixa-texto" value="Procurar" size="30" name="arquivo"> 
                  </td>
                </tr>
                <tr class="color-header"> 
                  <td align="left" >
                  	<p class="label">
                      		Anúncio selecionado n.º: &nbsp<b><%=a.getCodigoAnuncio()%></b>
                  	</p>
                  </td>
                </tr>                
                <tr class="color-buttons" > 
                  <td align="center" height="30"> 
                  	 <input type="submit" class="label" name="comando" value="   Enviar   " onclick="return verificaCodigo(document.FormImagem);"> 
                     <input type="hidden" name="codigoAnuncio" value=<%=a.getCodigoAnuncio()%>>   
                     <% session.setAttribute("codigoA",""+a.getCodigoAnuncio()); %>                  
                  </td>
                </tr>
              </table>
	</td>
	</tr>
	
	</table>
	</form>
<%}%>
	
	<table border="0" cellpadding="0" cellspacing="0" align="center" width="90%">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
	    <tr class="color-title"> 
	    	<%
	    		if(!operacao.equals("incluirFotos")){
	    			out.print("<td colspan=\"7\" align=\"center\"> <p class=\"label\" >&nbsp; <b class=\"form-title\"> Anúncios Publicados </b></p></td>");
	    		}
	    		else{
	    			out.print("<td colspan=\"6\" align=\"center\"> <p class=\"label\" >&nbsp; <b class=\"form-title\"> Anúncios Publicados </b></p></td>");	    			
	    		}
	    	%>
	          
	    </tr>
	    <tr class="color-header"> 
	      <td> <font class="label">No.</font></td>
	      <td> <font class="label">Código</font></td>
	      <td> <font class="label">Data</font></td>
	      <td> <font class="label">T&iacute;tulo</font></td>
	      <td> <font class="label">Descri&ccedil;&atilde;o</font></td>
	      
	      <%if(!operacao.equals("incluirFotos")){ %>
	      	<td>&nbsp;</td>
	      	<td>&nbsp;</td>
	      <%}else{%>	      	      
	      	<td>&nbsp;</td>
	      <%} %>
	    </tr>
	      <%	
	        	
	      		Locale locale = new Locale("pt","BR"); 	
	  			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
			
	        	
	        	if(listaAnuncio!=null){
		        	for(int i=0; i < listaAnuncio.size(); i++){
		        		
		        		Anuncio a2 = new Anuncio();		        		
		        		a2 = (Anuncio) listaAnuncio.get(i);
		        		
		        		
			        	if(a2.getStatus().equals("L")){	
			        		//formatando a data e hora
			        		String dataInicio = formatador.format(a2.getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
					        out.println("<td> <p class=\"label\">"+ a2.getCodigoAnuncio()+"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ a2.getTitulo() +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ a2.getDescricaoAnuncio().replaceAll("\r\n","<br>")+"</p></td>");
					        
					        if(!operacao.equals("incluirFotos")){				        	
						        out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=editar&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
						        out.print("<img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"editar\"></a></td>");
						        out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=excluir&codigoAnuncio="+a2.getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\">");
						        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
					        }
					        
					        if(operacao.equals("incluirFotos")){
					        	
					        		Vector vetorImagem = (Vector) a2.getImagem();
				        			
				        			if(vetorImagem.size() < 4){	
							        	out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=incluirFoto&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
							        	out.print("<img src=\"/publicadoranuncio/visao/imagem/movie.png\" width=\"24\" height=\"24\" alt=\"Incluir fotos\"></a></td>");
		        						out.println("</tr>");
				        			}else{				        						        				
				        				out.println("<td></td>");
		        						out.println("</tr>");			        				
				        			}
					        }
			        	}else if(a2.getStatus().equals("P")){
			        		//formatando a data e hora
			        		String dataInicio = formatador.format(a2.getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
					        out.println("<td> <p class=\"label\">"+ a2.getCodigoAnuncio()+"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ a2.getTitulo()+"</p></td>");
					        out.println("<td> <p class=\"label\"><font color=\"blue\">Este anúcio será analisado para que possa ser publicado novamente</font></p></td>");
					        
					        if(!operacao.equals("incluirFotos")){				        	
						       
					        	 out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=editar&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
							     out.print("<img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"editar\"></a></td>");
							     out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=excluir&codigoAnuncio="+a2.getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\">");
							     out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
					        }
					        
					        if(operacao.equals("incluirFotos")){
					        	
					        		Vector vetorImagem = (Vector) a2.getImagem();
				        			
				        			if(vetorImagem.size() < 4){	
				        				out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=incluirFoto&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
							        	out.print("<img src=\"/publicadoranuncio/visao/imagem/movie.png\" width=\"24\" height=\"24\" alt=\"Incluir fotos\"></a></td>");
		        						out.println("</tr>");
				        			}else{				        						        				
				        				out.println("<td></td>");
		        						out.println("</tr>");			        				
				        			}
					        }
			        	}else if(a2.getStatus().equals("B")) {
			        		//formatando a data e hora
			        		String dataInicio = formatador.format(a2.getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
					        out.println("<td> <p class=\"label\">"+ a2.getCodigoAnuncio()+"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\">Bloqueado</p></td>");
					        out.println("<td> <p class=\"label\"><font color=\"red\">Este anúncio foi bloqueado por estar em desacordo com as normas do site. Verifique seu conteúdo (texto, fotos, etc.)</font></p></td>");
					        
					        if(!operacao.equals("incluirFotos")){				        	
						        out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=editar&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
						        out.print("<img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"editar\"></a></td>");
						        out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=excluir&codigoAnuncio="+a2.getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\">");
						        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
					        }
					        
					        if(operacao.equals("incluirFotos")){
					        	
					        		Vector vetorImagem = (Vector) a2.getImagem();
				        			
				        			if(vetorImagem.size() < 4){	
							        	out.println("<td><a href=\"/publicadoranuncio/ControleAnuncio?operacao=incluirFoto&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
							        	out.print("<img src=\"/publicadoranuncio/visao/imagem/movie.png\" width=\"24\" height=\"24\" alt=\"Incluir fotos\"></a></td>");
		        						out.println("</tr>");
				        			}else{		
				        						        				
				        				out.println("<td></td>");
		        						out.println("</tr>");			        				
				        			}
					        }
			        	}
		        	}
	        	}
	        %>
	        
	     <tr class="color-buttons"> 
	       <td height="30" colspan="7">&nbsp; </td>
	     </tr>
	     </table>
		</td>
	</tr>
	</table>    
	<td valign="top" align="center" background="/publicadoranuncio/visao/imagem/fundo.png">
	  	<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
	</td>
<!-- </tr> -->
   <tr class="color-buttons"> 
      <td height="30" colspan="3">&nbsp; </td>
 </tr>
</table>


<center>
	<%@ include file="/visao/rodape.htm" %>
</center>


<script>
	
	
	
	
	//setando código da cidade em campo hidden 
	function setCodigo(codigo){
		document.FormAnuncio.codigoCidade.value = codigo;			
	}
	
	//setando operação para combo
	function setOperacao(op){				
		document.FormAnuncio.popula.value = op;			
		document.FormAnuncio.submit();
	}
	
	//verificando código do anúncio para cadastro da imagem e campo de arquivo
	function verificaCodigo(f){
	
		if(f.codigoAnuncio.value >=1){
			if(f.arquivo.value.toUpperCase().lastIndexOf('.JPG')!=-1
					|| f.arquivo.value.toUpperCase().lastIndexOf('.BMP')!=-1
						|| f.arquivo.value.toUpperCase().lastIndexOf('.PNG')!=-1
							|| f.arquivo.value.toUpperCase().lastIndexOf('.JPEG')!=-1){						
				return true;	
			
			}else{
				alert('Escolha uma imagem válida para envio!\nSão aceitas imagens do tipo: JPG; PNG OU BMP');
				return false;
			}
		}else{		
			alert('Você precisa selecionar um anúncio primeiro!');
			return false;
		}
	}
	
	
	
	
	//se usuário clicou no botão anterior do browser
	if(history.length > 0) 
		{ history.go(+1); } 
	
</script>
<% 
	String erro =  request.getParameter("erro");
	if(erro!=null){
		out.print("<script>alert('Tamanho máximo permitido para imagem:1048576KB.');</script>");
	}
	
	String msgImagem = request.getParameter("msg");
	
	if(msgImagem!=null)
		if(msgImagem.equals("numeroMaximoFotos"))
			out.println("<script>alert('Máximo de 4 fotos por anúncio');</script>");
		
%>

</body>
</html>







