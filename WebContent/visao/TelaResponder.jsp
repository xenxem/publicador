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
<title>Responder</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>

<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");

	Mensagem m = (Mensagem) request.getAttribute("mensagem");
	
	if(m==null){
		
		m = new Mensagem();
		
		Anuncio a = new Anuncio();
		a.setCodigoAnuncio(0);
		a.setTitulo("");		
		m.setAnuncio(a);
		
		Usuario u2 = new Usuario();
		u2.setCodigoUsuario(0);
		u2.setApelido("");		
		m.setUsuario(u2);	
				
		m.setTipo("");
		m.setDescricaoMensagem("");
				
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
 	
	<td width="80%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> 
      <font class="label"><b>Mensagens recebidas</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td align="center" valign="middle" class="label" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
	<form name="FormMensagem" action="/publicadoranuncio/ControleResponder" method="post" onsubmit="return validacao(this)">	
	<table width="50%" class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table width="100%" border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left">
                  	<p class="label" >&nbsp; <b class="form-title">Anúncio</b></p>
                  </td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td width="274" colspan="3">&nbsp;</td>
                </tr>
                <tr> 
                  <td  align="right"><font class="label">Título</font></td>
                  <td align="left">  
                  	<div class="div" style="width:240px;"><%=m.getAnuncio().getTitulo() %></div>
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr> 
                  
                  <% 
                  	if(operacao.equals("exibirCaixaMensagem")){
                  		out.println("<td  align=\"right\"><font class=\"label\">Mensagem</font></td>");
                        out.println("<td align=\"left\">");
                  	 	out.println("<div class=\"div\" style=\"width:240px;background:#ddddd5;\">"+m.getDescricaoMensagem().replaceAll("\r\n","<br>")+"</div>");
                  	 	out.println("</td>");
                  	 	out.println("<td>&nbsp;</td>");
                  	}
                  %>
                  
                </tr>               
                <tr> 
                  <td colspan="3"></td>
                </tr>                
       </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					
                  <td align="center" height="20" colspan="3"> 
                    												
                    	<input type="hidden" name="codigoMensagem" value=<%=m.getCodigoMensagem() %>>											
						<input type="hidden" name="codigoAnuncio" value=<%=m.getAnuncio().getCodigoAnuncio() %>>
						<input type="hidden" name="codigoUsuario" value=<%=m.getUsuario().getCodigoUsuario() %>>
						
						<%
							if(operacao.equals("exibirCaixaMensagem")){
								out.println("<div class=\"label\" align=\"center\">Enviar resposta a <font color=\"blue\">"+m.getUsuario().getApelido()+"</font></div>");
								out.println("<textarea rows=\"5\" wrap=\"hard\" class=\"caixa-texto\" cols=\"60\" title=\"Mensagem\" name=\"descricaoMensagem\" onkeypress=\"limitaQtdeCaracter(this);\"></textarea><br>");
								out.println("<input type=\"submit\"  name=\"operacao\" class=\"label\" title=\"Clique aqui para enviar a mensagem\"  value=\"Enviar Resposta\">");
								out.println("<input type=\"button\"  name=\"comando\" class=\"label\" title=\"Clique aqui para cancelar\" onclick=\"javascript:history.go(-1);\"  value=\"        Cancelar        \">");
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
	  	ArrayList listaMensagem = (ArrayList) request.getAttribute("listaMensagem");    
	   	
	      
	   	if(listaMensagem!=null){
	   		
	   		
	   		
	   		Locale locale = new Locale("pt","BR"); 	
  			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);
	   		
	   		//Mensagens cadastradas
		   	if(listaMensagem.size()>=1){
		   		
		   		
		   					
		   		
		   		out.print("<table width=\"90%\" border=\"1\" cellpadding=\"0\" style=\"border-color:#666666;\"cellspacing=\"0\" align=\"center\">");
		   		out.print("<tr valign=\"top\" class=\"color-border\" >");
		   		out.print("<td>");
			      		
			      			      			
		   		out.println("<table border=\"0\" cellspacing=\"0\" cellpadding=\"10\" width=\"100%\">");
		   		out.println("<tr class=\"color-title\">");
		   		out.println("<td colspan=\"7\">");
		   		out.println("<font class=\"label\"><b>Mensagens Recebidas</b></font>");
		   		out.println("</td>");
		   		out.println("</tr>");		   		
		     	out.println("<tr class=\"color-header\">");
		     	out.println("<td> <font class=\"label\">Nº</font></td>");
		     	out.println("<td> <font class=\"label\">Data</font></td>");
			    out.println("<td> <font class=\"label\">Usuário</font></td>");
			    out.println("<td> <font class=\"label\">Mensagem</font></td>");
			    out.println("<td> <font class=\"label\">Título</font></td>");
			    out.println("<td> <font class=\"label\"></font></td>");
			    out.println("<td> <font class=\"label\"></font></td>");
			    out.println("</tr>");
			  	  
			     
			    for(int i=0; i < listaMensagem.size(); i++){
			    	Mensagem m2 = (Mensagem) listaMensagem.get(i);
			    	
			    	String data = formatador.format(m2.getData());
			    				    	
			    	out.println("<tr class=\"color-row\">");
			    	
					if(m2.getTipo().equals("P")){
						
						out.println("<td bgcolor=\"#EEE8AA\">"+(i+1)+"</td>");
						out.println("<td bgcolor=\"#EEE8AA\">"+data+"</td>");
						out.println("<td bgcolor=\"#EEE8AA\">"+m2.getUsuario().getApelido()+" fala</td>");							
						
						out.println("<td bgcolor=\"#EEE8AA\">"+m2.getDescricaoMensagem().replaceAll("\r\n","<br>")+"</td>");
						out.println("<td nowrap bgcolor=\"#EEE8AA\">"+m2.getAnuncio().getTitulo()+"</td>");
						out.println("<td bgcolor=\"#EEE8AA\"><a href=\"/publicadoranuncio/ControleResponder?operacao=responderAnuncio&codigoMensagem="+m2.getCodigoMensagem()+"&codigoAnuncio="+m2.getAnuncio().getCodigoAnuncio()+"\">");
						out.print("<img src=\"/publicadoranuncio/visao/imagem/preview.gif\" alt=\"Responder\"></a></td>");
						out.println("<td bgcolor=\"#EEE8AA\"><a href=\"/publicadoranuncio/ControleResponder?codigoAnuncio="+m2.getAnuncio().getCodigoAnuncio()+"&operacao=excluir&codigoMensagem="+m2.getCodigoMensagem()+"&ordem="+m2.getOrdem()+"\" onclick=\"return confirmaExclusao();\">");
						out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir\"></a></td>");
						
					}else{
						
					    out.println("<td>"+(i+1)+"</td>");
					    out.println("<td><font color=\"green\">"+data+"</font></td>");
						out.println("<td> <font color=\"green\">"+m2.getUsuario().getApelido()+" fala</font></td>");						
						out.println("<td><font color=\"green\">"+m2.getDescricaoMensagem()+"</font></td>");
						out.println("<td colspan=\"3\"><font color=\"green\">"+m2.getAnuncio().getTitulo()+"</font></td>");
						//out.println("<td></td>");
						//out.println("<td></td>");
						
					}
					out.println("</tr>");
			    }
			      		
				out.println("<tr class=\"color-buttons\">"); 
				out.println("<td height=\"30\" colspan=\"7\">&nbsp;</td>");
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
	 %>	
	 
    <td valign="top" align="center" class="label" background="/publicadoranuncio/visao/imagem/fundo.png">
    
<%
    	if(u!=null){
    		if(u.getCodigoUsuario()!=0){    		
%>    	    
			<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
<%			}
        }else{
%>
    	  	<%@ include file="/visao/Login.jsp" %>
<%
    	}
%>  	
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







