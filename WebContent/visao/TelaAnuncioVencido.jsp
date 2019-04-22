<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Imagem"%>
<%@ page import="modelo.Ocorrencia"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Locale"%>
<%! ArrayList listaImagem; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anúncios Vencidos</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
</head>
<body>
<%
	//para listar anúncios
	ArrayList listaAVencido = (ArrayList) request.getAttribute("listaAVencido");

	Anuncio a = (Anuncio) request.getAttribute("anuncio");
	Usuario usu = (Usuario) request.getAttribute("email");
	
	Locale locale = new Locale("pt","BR"); 	
	SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
	
	if (usu == null){
		
		usu = new Usuario();
		usu.setEmail("");
		
	}
	
	if(a==null){
		
		a = new Anuncio();
		
		a.setCodigoAnuncio(0);
		a.setTitulo("");
		a.setDescricaoAnuncio("");
		a.setDataFim(null);
		
		Categoria c = new Categoria();
		c.setCodigoCategoria(0);
		c.setNivelCategoria(0);
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
	
	if (operacao == null){
		operacao = "Iniciar";
	}
%>


<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" cellpadding="0" cellspacing="0" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>

<table border="0" cellspacing="1" cellpadding="5" width="100%" align="center" class="color-border">
<tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados On-Line </b></p></td>
</tr>
<tr class="color-header">		          
 	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Fotos</b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Anúncios Vencidos</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
<%if(operacao.equals("Visualizar")){ 

%>
	
 	<table border="0" cellspacing="25" cellpadding="2" align="center" >
 	<tr>
 		<td align="center">
 			<p class="label"><b>Código<br> anúncio selecionado:&nbsp; <%=a.getCodigoAnuncio()%></b></p>
 		</td>
 	</tr>
 	<%
		 listaImagem = (ArrayList) request.getAttribute("listaImagem");
 			
		 	if(listaImagem!=null){
		 		out.println("<tr><td align=\"center\"><p class=\"label\" align=\"center\"><b>Fotos: "+listaImagem.size()+"</b></p></td></tr>");	
		 		for(int i=0; i<listaImagem.size(); i++){		 			
		 			Imagem imagem = new Imagem();
		 			imagem = (Imagem) listaImagem.get(i);	
		 					 			
			 		out.println("<tr align=\"center\">");
			 		
			 		if(listaImagem.size()==1){
			 			out.println("<td><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:2px;border-style:solid;border-color:pink;\"><br><span class=\"label\"></span></td>");			 				
			 		}else{
			 			out.println("<td><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:5px;border-style:solid;border-color:#333399;\"><br><span class=\"label\"><b></b></span></td>");
			 		}			 							 			 				
		 						 		
			 		out.println("</tr>");
		 		}
		 	}else{
		 		
		 		out.println("<tr><td><p class=\"label\" align=\"center\"><b>Fotos: Nenhuma</b></p></td></tr>");
		 	}
 	%>		
	</table>
	<%}
			 
		%>
	</td>          
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png"> 

	<br>
	<form name="FormAnuncioVencido" action="/publicadoranuncio/ControleAnuncioVencido" method="post">
	<table class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table  border="0" align="center" cellspacing="1"  class="color-header">
	    <tr> 
	        <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; <b class="form-title"> Dados do Anúncio </b></p></td>
	    </tr>
	    <tr> 
			<td>&nbsp;</td>
	        <td width="274" colspan="2">&nbsp;</td>
	    </tr>
	    <tr> 
	        <td  width="77" align="right"><font class="label">Usuário</font></td>
	       <%if (u.getEmail() == ""){
	    	   	out.print("<td align=\"left\"> <div class=\"div\" style=\"width:150pt;\">"+"</div></td>");
	         }else{
	        	out.println("<td align=\"left\"> <div class=\"div\" style=\"width:150pt;\">"+usu.getEmail()+"</div></td>"); 
	         }
	       
	       %>
	    </tr>
	    <tr> 
	        <td  width="77" align="right"><font class="label">Vencimento</font></td>
	       <%if (a.getDataFim() == null){
	    	   out.print("<td align=\"left\"> <div class=\"div\" style=\"width:80pt;\">"+"</div></td>");
	         }else{
	           String dataFim = formatador.format(a.getDataFim());
	           out.println("<td align=\"left\"> <div class=\"div\" style=\"width:80pt;\">"+dataFim+"</div></td>");	            
	         }
	       
	       %>
	    </tr>
	    <tr> 
	        <td  width="77" align="right"><font class="label">Título</font></td>
	        <td align="left"> <div class="div" style="width:180pt;"><%=a.getTitulo() %></div></td>
            
	    </tr>
	    
	    <tr> 
	        <td  valign="top" align="right"><font class="label">Descrição</font></td>
	        <td align="left"> <div class="div" style="width:180pt;"><%=a.getDescricaoAnuncio() %></div></td>
	    </tr>
	    
	    
	    <tr> 
	    		<td align="right"><font class="label">UF</font></td>
	                    <%
	                    	                   	
	                    	Uf uf2 = (Uf) request.getAttribute("uf");
	                    	
	                        if (uf2 != null){
	                        	
	                        	out.println("<td align=\"left\"> <div class=\"div\" style=\"width:90pt;\">"+uf2.getDescricaoUf()+"</div></td>");
	                        		                        	
	                        }else{
		                    	out.print("<td align=\"left\"> <div class=\"div\" style=\"width:90pt;\">"+"</div></td>");	
		                    }
	                    
	                    %>
	                
	    </tr>
	    <tr> 
	            <td align="right"><font class="label">Cidade</font></td>
	               <%
	               
	               		Cidade c2 = (Cidade) request.getAttribute("cidade");
	               		
	                    if (c2 != null){
	                    	
	                    	out.println("<td align=\"left\"> <div class=\"div\" style=\"width:90pt;\">"+c2.getDescricaoCidade()+"</div></td>");
	                    		                    	
	                    }else{
	                    	out.print("<td align=\"left\"> <div class=\"div\" style=\"width:90pt;\">"+"</div></td>");	
	                    }
	               			
	               %>
	            
	     </tr>
	     <tr> 
	        <td  valign="top" align="right"><font class="label">Categoria</font></td>
	        <% 
	        	
	        	Categoria cat = (Categoria) request.getAttribute("categoria");
	        
	        	if (cat != null){
	        		out.println("<td align=\"left\"><div class=\"div\" style=\"width:90pt;\">"+cat.getDescricaoCategoria()+"</div></td>");
	        	}else{
	        		out.print("<td align=\"left\"> <div class=\"div\" style=\"width:90pt;\">"+"</div></td>");
	        	}
	        	
	        %>
	    </tr>
	     <tr> 
	     	<td>&nbsp;</td>
	      	<td colspan="2">&nbsp;</td>
	     </tr>
	    </table>
	</td>
	</tr>		
	
				
					
					    <%if (!a.getTitulo().equals("")){
					    	
					    	out.print("<tr>"); 		  	
					    	out.print("<td height=\"30\" colspan=\"4\" align=\"center\">");
					    	out.print("<table class=\"color-buttons\" width=\"100%\" height=\"100%\">");
					    	out.print("<tr>");
							out.print("<td align=\"center\">");
					    	out.print("<input type=\"submit\" class=\"label\" name=\"comando\" value=\"   Excluir   \" onclick=\"return confirmaExclusao();\">");
							out.print("</td>");
							out.print("</tr>");
							out.print("</table>");
							out.print("</td>");
							out.print("</tr>");
							
					    }%>
						
				   <input type="hidden" size="3" name="operacao"  value=<%=operacao %>>												
				   <input type="hidden" name="codigoCategoria" value=<%=a.getCategoria().getCodigoCategoria()%>>
				   <input type="hidden" name="nivelCategoria" value=<%=a.getCategoria().getNivelCategoria()%>>						
				   <input type="hidden" name="codigoAnuncio" value=<%=a.getCodigoAnuncio()%>>
				   <input type="hidden" name="popula">
						
			
	</table>
	</form>

	
	<table border="0" cellpadding="0" cellspacing="0" align="center" width="90%">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
	    <tr class="color-title">
	    		<td colspan="8" align="center"> <p class="label" >&nbsp; <b class="form-title"> Anúncios Vencidos </b></p></td>	
	          
	    </tr>
	    <tr class="color-header"> 
	      <td> <font class="label">No.</font></td>
	      <td> <font class="label">Código</font></td>
	      <td> <font class="label">Data de Vencimento</font></td>
	      <td> <font class="label">Data de Cadastro</font></td>
	      <td> <font class="label">Usuário</font></td>
	      <td> <font class="label">T&iacute;tulo</font></td>
	    	    
	      <td>&nbsp;</td>
	      <td>&nbsp;</td>	 
	      	      	     
	    </tr>
	      <%	
	        	
				if(listaAVencido!=null){
		                			        		
	        		for(int i=0; i < listaAVencido.size(); i++){
		        		
		        		Anuncio a2 = new Anuncio();		        		
		        		a2 = (Anuncio) listaAVencido.get(i);
		        		
		        		Ocorrencia ocorrencia = (Ocorrencia) a2.getOcorrencia().get(i);
		        		
		        		//formatando a data e hora
		        		String dataCadastro = formatador.format(ocorrencia.getData()); 
		        		String dataFim = formatador.format(a2.getDataFim());
		        		
				        out.println("<tr class=\"color-row\">"); 
				        out.println("<td height=\"20\" <p class=\"label\">"+(i+1)+"</td></p>");
				        out.println("<td> <p class=\"label\">"+ a2.getCodigoAnuncio()+"</p></td>");
				        out.println("<td> <p class=\"label\">"+ dataFim +"</p></td>");
				        out.println("<td> <p class=\"label\">"+ dataCadastro +"</p></td>");
				        out.println("<td> <p class=\"label\">"+ a2.getUsuario().getEmail() +"</p></td>");
				        out.println("<td> <p class=\"label\">"+ a2.getTitulo() +"</p></td>");			        
				        out.println("<td><a href=\"/publicadoranuncio/ControleAnuncioVencido?operacao=visualizar&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
					    out.print("<img src=\"/publicadoranuncio/visao/imagem/preview.gif\" alt=\"Visualizar\"></a></td>");
					    out.println("<td><a href=\"/publicadoranuncio/ControleAnuncioVencido?operacao=excluir&codigoAnuncio="+a2.getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\">");
				        out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
	        		}
		        	
	        	}
	        %>
	        
	     <tr class="color-buttons"> 
	       <td height="30" colspan="8">&nbsp; </td>
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