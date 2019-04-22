<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Imagem"%>
<%@ page import="modelo.Pagamento"%>
<%@ page import="modelo.Ocorrencia"%>
<%@ page import="modelo.Valor"%>
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
<title>Liberação de Anúncio</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
</head>
<body>
<%
	//para listar anúncios
	ArrayList listaAnuncio = (ArrayList) request.getAttribute("listaAnuncio");

	Anuncio a = (Anuncio) request.getAttribute("anuncio");
	Pagamento p = (Pagamento) request.getAttribute("idDeposito");
	Valor v = (Valor) request.getAttribute("valor");
	
	if (p == null){
		
		p = new Pagamento();
		p.setNumeroParaIdentificacao(0);
		
	}
	
	if (v == null){
		
		v = new Valor();
		v.setValor(0);
		
	}
	
	if(a==null){
		
		a = new Anuncio();
		
		a.setCodigoAnuncio(0);
		a.setTitulo("");
		a.setDescricaoAnuncio("");
		
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

<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="color-border">
<tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados On-Line </b></p></td>
</tr>
<tr class="color-header">		          
 	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Fotos</b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Anúncios Para Liberação</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
<%
	if(operacao.equals("Visualizar")){ 

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
	
 	<table border="0" cellspacing="25" cellpadding="2" align="center" >
 	
 	<%
		 listaImagem = (ArrayList) request.getAttribute("listaImagem");
 			
		 	if(listaImagem!=null){		 			
		 		for(int i=0; i<listaImagem.size(); i++){		 			
		 			Imagem imagem = new Imagem();
		 			imagem = (Imagem) listaImagem.get(i);	
		 					 			
			 		out.println("<tr align=\"center\">");
			 		
			 		if(listaImagem.size()==1){
			 			out.println("<td><a href=\"#\"> <img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"javascript:abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a></td>");			 				
			 		}else{
			 			out.println("<td><a href=\"#\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"javascript:abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a></td>");
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
	<form name="FormLiberarAnuncio" action="/publicadoranuncio/ControleLiberarAnuncio" method="post">
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
	        <td align="right"><font class="label">Id. Depósito</font></td>
	       <%if (p.getNumeroParaIdentificacao() == 0){
	    	   out.print("<td align=\"left\"> <div class=\"div\" style=\"width:55pt;\">"+"</div></td>");
	         }else{
	           out.print("<td align=\"left\"> <div class=\"div\" style=\"width:55pt;\">"+p.getNumeroParaIdentificacao()+"</div></td>"); 
	         }
	       
	       %>
	    </tr>
	     <tr> 
	        <td align="right"><font class="label">Valor</font></td>
	       <%if (v.getValor() == 0){
	    	   out.print("<td align=\"left\"> <div class=\"div\" style=\"width:30pt;\">"+"</div></td>");
	         }else{
	           out.print("<td align=\"left\"> <div class=\"div\" style=\"width:30pt;\">"+"R$ "+v.getValor()+"</div></td>"); 
	         }
	       
	       %>
	    </tr>
	    <tr> 
	        <td align="right"><font class="label">Título</font></td>
	         <td align="left"> <div class="div" style="width:180pt;"><%=a.getTitulo()%></div></td>    
	          
     
	    </tr>
	    
	    <tr> 
	        <td  valign="top" align="right"><font class="label">Descrição</font></td>
            <td align="left"><div class="div" style="width:180pt;"><%=a.getDescricaoAnuncio()%></div></td>
	    </tr>
	    	    
	    <tr> 
	            <td align="right"><font class="label">Uf</font></td>
                 
	                    
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
					    	
					    	String status = (String) request.getAttribute("status");
					    					    	
					    	out.print("<tr>"); 		  	
					    	out.print("<td height=\"30\" colspan=\"4\" align=\"center\">");
					    	out.print("<table class=\"color-buttons\" width=\"100%\" height=\"100%\">");
					    	out.print("<tr>");
							out.print("<td align=\"center\">");
					    	out.print("<input type=\"submit\" class=\"label\" name=\"comando\" value=\"   Liberar   \">");
					    	if (status != null && status.equals("P"))
					    		out.print("<input type=\"submit\" class=\"label\" name=\"comando\" value=\"  Bloquear  \">");
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
	    		<td colspan="7" align="center"> <p class="label" >&nbsp; <b class="form-title"> Anúncios Pendentes </b></p></td>	
	          
	    </tr>
	    <tr class="color-header"> 
	      <td> <font class="label">No.</font></td>
	      <td> <font class="label">Identificação de Depósito</font></td>
	      <td> <font class="label">Status</font></td>
	      <td> <font class="label">Data de Cadastro</font></td>
	      <td> <font class="label">Título</font></td>
	    
	      <td>&nbsp;</td>	      	      	      
	     
	    </tr>
	    
	      <%
				Locale locale = new Locale("pt","BR"); 	
	    		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
	    		String s = new String();
	    		
				if(listaAnuncio!=null){
	        		   		
	        		for(int i=0; i < listaAnuncio.size(); i++){
		        		
		        		Anuncio a2 = new Anuncio();		        		
		        		a2 = (Anuncio) listaAnuncio.get(i);
		        		
		        		Pagamento pagamento = (Pagamento) a2.getPagamento().get(i);
		        		
		        		//formatando a data e hora
		        		Ocorrencia ocorrencia = (Ocorrencia) a2.getOcorrencia().get(i);
		        		String dataCadastro = formatador.format(ocorrencia.getData()); 
		        		
		        		if (a2.getStatus().equals("B"))
		        			s = "Bloqueado";
		        		else
		        			s = "Pendente"; 
		        		
				        out.println("<tr class=\"color-row\">"); 
				        out.println("<td height=\"20\" <p class=\"label\">"+(i+1)+"</td></p>");
				        out.println("<td> <p class=\"label\">"+pagamento.getNumeroParaIdentificacao()+"</p></td>");
				        out.println("<td> <p class=\"label\">"+s+"</p></td>");
				        out.println("<td> <p class=\"label\">"+dataCadastro+"</p></td>");
				        out.println("<td> <p class=\"label\">"+a2.getTitulo()+"</p></td>");			        
				        out.println("<td><a href=\"/publicadoranuncio/ControleLiberarAnuncio?operacao=visualizar&codigoAnuncio="+a2.getCodigoAnuncio()+"\">");
					    out.print("<img src=\"/publicadoranuncio/visao/imagem/preview.gif\" alt=\"Visualizar\"></a></td>");
	        		}
		        	
				}
							
	        %>
	        
	     <tr class="color-buttons"> 
	       <td height="30" colspan="7">&nbsp; </td>
	     </tr>
	     <%
	     		Usuario usuario = (Usuario) request.getAttribute("usuario");
				
				if (usuario != null)
					out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/sucesso.bmp\" align=\"middle\" width=\"40\" height=\"40\"></td><td align=\"center\"><span class=\"label\">"+usuario.getNome()+" está usando este anuncio</span></td>");
		 %>		
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