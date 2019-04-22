<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Mensagem"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Imagem"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%! ArrayList listaImagem; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalhes</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
</head>
<body>

<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	Anuncio a = (Anuncio) request.getAttribute("anuncio");
	
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
		
		a.setImagem(null);
				
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> 
      <font class="label"><b>Detalhes do Anúncio Pesquisado</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
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
 	<tr>
 		<td align="center"> 	
 		</td>
 	</tr>
 	<%
		 
 			
		 	
 			if(a.getImagem()!=null){		 			
		 	
		 		for(int i=0; i < a.getImagem().size(); i++){		 			
		 			Imagem imagem = new Imagem();
		 			imagem = (Imagem) a.getImagem().get(i);	
		 					 			
			 		out.println("<tr align=\"center\">");		 		
			 		out.println("<td><a href=\"#\" title=\"Clique para empliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir('/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"');\"></a></td>");					
		 		}
		 	}			
 	%>		
	</table>

	</td>          
    <td align="center" valign="middle" class="label" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
	<form name="FormMensagem" action="/publicadoranuncio/ControleDetalhes" method="post" onsubmit="return validacao(this)">	
	<table width="50%" class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table width="100%" border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; 
                      <b class="form-title"> Detalhes do An&uacute;ncio</b></p></td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td width="274" colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td  width="77" align="right"><font class="label">Título</font></td>
                  <td align="left">  
                  	<span class="label"><div class="div"><%=a.getTitulo() %></div>
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr> 
                  <td  valign="top" align="right"><font class="label">Descrição</font></td>
                  <td align="left">
                  	<div class="div"><%=a.getDescricaoAnuncio().replaceAll("\n\r","<br>") %></div>
                  </td>
                  <td>&nbsp;</td>
                  	
                </tr>
                <tr> 
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Uf</font></td>
                  <td align="left"> 
                  		<div class="div" style="width:20pt;"><%=a.getUf().getCodigoUf() %></div>
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Cidade</font></td>
                  <td align="left" >                  		
                  		<div class="div"><%=a.getCidade().getDescricaoCidade() %></div>
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" ><div class="div"><%=a.getCategoria().getDescricaoCategoria() %></div><br></td>
                  <td>&nbsp;</td>
                </tr>
              </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					
                  <td align="center" height="20" colspan="3"> 
                    	<input type="hidden" size="3" name="operacao"  value=<%=operacao %>>												
						<input type="hidden" name="codigoAnuncio" value=<%=a.getCodigoAnuncio()%>>
						<input type="hidden" name="codigoUsuario" value=<%=a.getUsuario().getCodigoUsuario() %>>
						<%
							if(operacao.equals("exibirCaixaMensagem")){
								out.println("<div class=\"label\" align=\"center\">Mensagem </div>");
								out.println("<textarea rows=\"5\" wrap=\"hard\" class=\"caixa-texto\" cols=\"60\" title=\"Mensagem\" name=\"descricaoMensagem\" onkeypress=\"limitaQtdeCaracter(this);\"></textarea><br>");
								out.println("<input type=\"submit\"  name=\"comando\" class=\"label\" title=\"Clique aqui para enviar a mensagem\"  value=\"Enviar mensagem\">");
								out.println("<input type=\"button\"  name=\"comando\" class=\"label\" title=\"Clique aqui para cancelar\" onclick=\"javascript:history.go(-1);\"  value=\"        Cancelar        \">");
							}else{
								if(u!=null){
                    				if(u.getCodigoUsuario()!=a.getUsuario().getCodigoUsuario()){
                    					out.println("<input type=\"submit\"  name=\"comando\" class=\"label\" title=\"Clique aqui para fazer uma pergunta\"    value=\"Fazer uma pergunta ao anunciante\"><br>");
        								out.println("<input type=\"submit\"  name=\"comando\" class=\"label\" title=\"Clique aqui para receber notícias sobre este anúncio\"  value=\"Acompanhar este anúncio\">");
                    				}
                    			}else{
                    				out.println("<input type=\"submit\"  name=\"comando\" class=\"label\" title=\"Clique aqui para fazer uma pergunta\"    value=\"Fazer uma pergunta ao anunciante\"><br>");
    								out.println("<input type=\"submit\"  name=\"comando\" class=\"label\" title=\"Clique aqui para receber notícias sobre este anúncio\"  value=\"Acompanhar este anúncio\">");
                    			}
							}
                    	
                    			
						%>
					</td>
				</tr>
			</table>						
	  	</td>
	</tr>	
	</table>
	
	
	</form>
	
	<%
	      
	   	
	      
	   	if(a.getMensagem()!=null){     		
	   		
	   		Locale locale = new Locale("pt","BR"); 	
  			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);
	   		
	   		//Mensagens cadastradas
		   	if(a.getMensagem().size()>=1){	      		
		   		out.print("<table width=\"90%\" border=\"1\" style=\"border-color:#666666;\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
		   		out.print("<tr valign=\"top\" class=\"color-border\" >");
		   		out.print("<td>");
			      		
			      			      			
		   		out.println("<table border=\"0\" cellspacing=\"0\" cellpadding=\"10\" width=\"100%\">");
		   		out.println("<tr class=\"color-title\">");
		   		out.println("<td colspan=\"2\">");
		   		out.println("<font class=\"label\"><b>Mensagens cadastradas("+a.getMensagem().size()+")</b></font>");
		   		out.println("</td>");
		   		out.println("</tr>");      			
		     	out.println("<tr class=\"color-header\">"); 
			    //out.println("<td> <font class=\"label\">Data</font></td>");	      
			    out.println("<td> <font class=\"label\">Usuário</font></td>");
			    out.println("<td> <font class=\"label\">Mensagem</font></td>");
			    out.println("</tr>");
			      		
			      		
			      		
		   		//Lista de mensagens
		   		for(int i=0; i < a.getMensagem().size(); i++){			      			
		   			Mensagem m = (Mensagem) a.getMensagem().get(i);
		   			
			      	
			      	
		   			out.println("<tr class=\"color-row\">");
					//out.println("<td>"+formatador.format(m.getData())+"</td>");
					if(m.getTipo().equals("P")){
						out.println("<td bgcolor=\"#EEE8AA\">"+m.getUsuario().getApelido().toUpperCase() +" pergunta</td>");
						out.println("<td bgcolor=\"#EEE8AA\">"+m.getDescricaoMensagem().replaceAll("\r\n","<br>")+"</td>");					
					}else{
						out.println("<td><font color=\"green\">"+m.getUsuario().getApelido().toUpperCase() +" responde</font></td>");
						out.println("<td><font color=\"green\">"+m.getDescricaoMensagem().replaceAll("\r\n","<br>")+"</font></td>");
					}
					out.println("</tr>");						
			    }
			      		
			      		
				out.println("<tr class=\"color-buttons\">"); 
				out.println("<td height=\"30\" colspan=\"2\">&nbsp;</td>");
				out.println("</tr>");
				out.println("</table>");	
			      		      		
			    out.print("</td>");
			    out.print("</tr>");
			    out.print("</table>");		
			    out.print("</td>");
			}else{
					
				//sem mensagem	
				out.print("<table width=\"85%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
		    	out.print("<tr valign=\"top\" class=\"color-border\" >");
		    	out.print("<td>");		      		
					
				out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
		      	out.println("<tr class=\"color-header\">");		      		
		      	out.println("<td width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" align=\"middle\" ></td><td>Nenhuma mensagem para este anúncio</td>");		    
			    out.println("</tr>");
			    out.println("</table>");	
		      	
					
				out.print("</td>");
		      	out.print("</tr>");
		      	out.print("</table><br>");		
		      		
					
			}
		      	
	 	}
	
		String msg2 = request.getParameter("msg");
		
		if(msg2!=null){
			if(msg2.equals("Usuário não está logado")){
				out.println("<script>");
				out.println("alert('Você precisa fazer o login');");
				out.println("document.FormLogin.email.focus();");
				out.println("</script>");			
			}
		}
		
		if(msg2!=null){
			if(msg2.equals("Este anúncio já encontra-se cadastrado para acompanhamento")){
				out.println("<script>");
				out.println("alert('Este anúncio já encontra-se cadastrado para acompanhamento');");				
				out.println("</script>");			
			}
		}
		
		if(msg2!=null){
			if(msg2.equals("Anúncio cadastrado com sucesso")){
				out.println("<script>");
				out.println("alert('Anúncio cadastrado com sucesso');");				
				out.println("</script>");			
			}
		}
	 %>	
    <td valign="top" align="center" class="label" background="/publicadoranuncio/visao/imagem/fundo.png">
    <%
    	if(u!=null){
    		if(u.getCodigoUsuario()!=0){%>    	    
	 				<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
	<%		}
        }else{%>
    	  <%@ include file="/visao/Login.jsp" %>
    <%}%>  	
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







