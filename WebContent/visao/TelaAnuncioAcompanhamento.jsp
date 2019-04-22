<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.AnuncioAcompanhamento"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Ocorrencia"%>
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
<title>Acompanhamento de Anúncio</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
</head>
<body>
<%
	//para listar anúncios
	ArrayList listaAcompanha = (ArrayList) request.getAttribute("listaAcompanha");

	AnuncioAcompanhamento anuncioAcompanha = (AnuncioAcompanhamento) request.getAttribute("anuncioAcompanha");
	
	
	
	if(anuncioAcompanha==null){		
		
		anuncioAcompanha = new AnuncioAcompanhamento();
		
		Anuncio a = new Anuncio();		
		a.setCodigoAnuncio(0);
		a.setTitulo("");
		a.setDescricaoAnuncio("");
		
		Categoria c = new Categoria();
		c.setCodigoCategoria(0);
		c.setNivelCategoria(0);
		c.setDescricaoCategoria("");		
		a.setCategoria(c);		
				
		Uf uf = new Uf();
		uf.setCodigoUf("");
		a.setUf(uf);
		
		Cidade c2 = new Cidade();
		c2.setCodigoCidade(0);
		c2.setDescricaoCidade("");
		a.setCidade(c2);
		anuncioAcompanha.setAnuncio(a);
		
		Usuario u2 = new Usuario();
		u2.setCodigoUsuario(0);
		u2.setApelido("");
		u2.setEmail("");		
		anuncioAcompanha.setUsuario(u2);
		
	}
	
	
	String operacao = (String) request.getAttribute("operacao");
	
	if(operacao==null){
		operacao="";
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Acompanhamento Individual de Anúncios</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
<%if(operacao.equalsIgnoreCase("Visualizar")){%>
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
					<font color="#000000"><%=anuncioAcompanha.getAnuncio().getCodigoAnuncio() %></font>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>

 	<table border="0" cellspacing="25" cellpadding="2" align="center" >
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
			 		out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" align=\"middle\" style=\"border-width:2px;border-style:solid;border-color:blue;\" onclick=\"abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a></td>");
			 		out.println("</tr>");
		 		}
		 	}else{		 		
		 		out.println("<tr><td><p class=\"label\" align=\"center\">Fotos: Nenhuma</p></td></tr>");
		 	}
		 	
 	%>		
	</table>
<%}%>
	</td>          
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" > 
	<br>
	<form name="FormAnuncio" action="/publicadoranuncio/ControleAcompanha" method="post">
	<table class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; 
                      <b class="form-title"> Dados do Anúncio Acompanhado</b></p></td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td width="274" colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td  width="77" align="right"><font class="label">Título</font></td>
                  <td align="left"> <div class="div" style="width:200px;"><%=anuncioAcompanha.getAnuncio().getTitulo() %></div></td>
                </tr>
                <tr> 
                  <td  valign="top" align="right"><font class="label">Descrição</font></td>
                  <td align="left"><div class="div" style="width:200px;"><%=anuncioAcompanha.getAnuncio().getDescricaoAnuncio() %></div></td>
                </tr>
                <tr> 
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Uf</font></td>
                  <td align="left"><div class="div" style="width:9px;"><%=anuncioAcompanha.getAnuncio().getUf().getCodigoUf() %> </div>  </td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Cidade</font></td>
                  <td align="left" ><div class="div" style="width:200px;"><%=anuncioAcompanha.getAnuncio().getCidade().getDescricaoCidade() %></div></td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" ><div class="div" style="width:200px;"><%=anuncioAcompanha.getAnuncio().getCategoria().getDescricaoCategoria() %></div></td>
                </tr>                
              </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					<td align="center">									
						
						<% 
							if(anuncioAcompanha.getAnuncio().getCodigoAnuncio()!=0){
								out.println("<input type=\"button\" name=\"c\" class=\"label\" value=\"Fazer uma pergunta ao anunciante\" onclick=\"javascript:document.jump2.submit();\">");
							}
						 %>
												
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>

<form name="jump2" action="/publicadoranuncio/ControleDetalhes" method="post">
	<input type="hidden" name="codigoAnuncio" value=<%=anuncioAcompanha.getAnuncio().getCodigoAnuncio() %>>
	<input type="hidden" name="codigoUsuario" value=<%=anuncioAcompanha.getUsuario().getCodigoUsuario() %>>
	<input type="hidden" name="comando" value="Fazer uma pergunta ao anunciante">
</form>
	


	
	<table border="0"  cellpadding="0" cellspacing="0" align="center" width="90%">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
	    <tr class="color-title"> 
	    	<td colspan="7" align="center"> <p class="label" >&nbsp; <b class="form-title"> Anúncios em Acompanhamento </b></p></td>    		
	    </tr>
	    <tr class="color-header"> 
	      <td> <font class="label">No.</font></td>
	      <td> <font class="label">Última Atualização</font></td>
	      <td> <font class="label">Código</font></td>
	      <td> <font class="label">T&iacute;tulo</font></td>
	      <td> <font class="label">Descri&ccedil;&atilde;o</font></td>
	      <td>&nbsp;</td>
	      <td>&nbsp;</td>	      	
	    </tr>
	      <%	
	        	
	      		Locale locale = new Locale("pt","BR"); 	
	  			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
			
	        	
	        	if(listaAcompanha!=null){
	        		
	        		int y=0;
	        		
		        	for(int i=0; i < listaAcompanha.size(); i++){
		        		
		        		AnuncioAcompanhamento a2 = new AnuncioAcompanhamento();		        		
		        		a2 = (AnuncioAcompanhamento) listaAcompanha.get(i);
		        		
		        		
		        			
		        		ArrayList listaA= (ArrayList) a2.getAnuncio().getOcorrencia();
		        		 
		        		Ocorrencia o= (Ocorrencia) listaA.get(y);
		        		y++;
		        		

		        		
				        //formatando a data e hora		        		
				        String dataOcorrencia = formatador.format(o.getData());
				        	
			        	if(a2.getAnuncio().getStatus().equals("L")){	
			        	
						    out.println("<tr class=\"color-row\">"); 
						    out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
						    out.println("<td> <p class=\"label\">"+ dataOcorrencia+"</p></td>");
						    out.println("<td> <p class=\"label\">"+ a2.getAnuncio().getCodigoAnuncio()+"</p></td>");					    
						    out.println("<td> <p class=\"label\">"+ a2.getAnuncio().getTitulo() +"</p></td>");
						    out.println("<td> <p class=\"label\">"+ a2.getAnuncio().getDescricaoAnuncio()+"</p></td>");			        				        	
							out.println("<td><a href=\"/publicadoranuncio/ControleAcompanha?operacao=visualizar&codigoAnuncio="+a2.getAnuncio().getCodigoAnuncio()+"\">");
							out.print("<img src=\"/publicadoranuncio/visao/imagem/preview.gif\" alt=\"Visualizar\"></a></td>");
							out.println("<td><a href=\"/publicadoranuncio/ControleAcompanha?operacao=excluir&codigoAnuncio="+a2.getAnuncio().getCodigoAnuncio()+"&codigoUsuario="+a2.getUsuario().getCodigoUsuario()+"\" onclick=\"return confirmaExclusao();\">");
					        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
							out.println("</tr>");
							
			        	}else if(a2.getAnuncio().getStatus().equals("P")){
			        		
			        		out.println("<tr class=\"color-row\">"); 
						    out.println("<td height=\"20\" <p class=\"label\"><font color=\"blue\">"+ (i+1) +"</font></td></p>");
						    out.println("<td> <p class=\"label\"><font color=\"blue\">"+ dataOcorrencia+"</font></p></td>");
						    out.println("<td> <p class=\"label\"><font color=\"blue\">"+ a2.getAnuncio().getCodigoAnuncio()+"</font></p></td>");					    
						    out.println("<td> <p class=\"label\"></p></td>");
						    out.println("<td> <p class=\"label\"><font color=\"blue\">Este anúncio foi alterado e está aguardando liberação por parte do site</font></p></td>");			        				        	
							out.println("<td></td>");
							out.println("<td><a href=\"/publicadoranuncio/ControleAcompanha?operacao=excluir&codigoAnuncio="+a2.getAnuncio().getCodigoAnuncio()+"&codigoUsuario="+a2.getUsuario().getCodigoUsuario()+"\" onclick=\"return confirmaExclusao();\">");
					        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
							out.println("</tr>");
							
			        	}else{
			         		out.println("<tr class=\"color-row\">"); 
						    out.println("<td height=\"20\" <p class=\"label\"><font color=\"red\">"+ (i+1) +"</font></td></p>");
						    out.println("<td> <p class=\"label\"><font color=\"red\">"+ dataOcorrencia+"</font></p></td>");
						    out.println("<td> <p class=\"label\"><font color=\"red\">"+ a2.getAnuncio().getCodigoAnuncio()+"</font></p></td>");					    
						    out.println("<td> <p class=\"label\"></p></td>");
						    out.println("<td> <p class=\"label\"><font color=\"red\">Este anúncio foi bloqueado e está aguardando regularizaão para que possa ser liberado pelo site</font></p></td>");			        				        	
							out.println("<td></td>");
							out.println("<td><a href=\"/publicadoranuncio/ControleAcompanha?operacao=excluir&codigoAnuncio="+a2.getAnuncio().getCodigoAnuncio()+"&codigoUsuario="+a2.getUsuario().getCodigoUsuario()+"\" onclick=\"return confirmaExclusao();\">");
					        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
							out.println("</tr>");
			        
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
</tr>
<tr class="color-buttons"> 
      <td height="30" colspan="3">&nbsp; </td>
</tr>
</table>


<center>
	<%@ include file="/visao/rodape.htm" %>
</center>



</body>
</html>







