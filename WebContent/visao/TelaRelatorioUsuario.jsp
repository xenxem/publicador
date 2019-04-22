<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório de Usuários</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>

<%
	String usuario = (String)request.getAttribute("usuario");
	String gestor = (String)request.getAttribute("gestor");
	String dono = (String)request.getAttribute("dono");
	String habilitado = (String)request.getAttribute("habilitado");
	String desabilitado = (String)request.getAttribute("desabilitado");
	
	if (usuario == null){
		usuario = new String();
		usuario = "";
	}
	if (gestor == null){
		gestor = new String();
		gestor = "";
	}
	if (dono == null){
		dono = new String();
		dono = "";
	}
	if (habilitado == null){
		habilitado = new String();
		habilitado = "";
	}
	if (desabilitado == null){
		desabilitado = new String();
		desabilitado = "";
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
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados 
        On-Line </b></p></td>
  </tr>
  <tr class="color-header"> 
    <td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b></b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Opções</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
<br>    
<form name="FormRelatorioUsuario" action="/publicadoranuncio/ControleRelatorioUsuario" method="get">
	<table border="0" cellspacing="0" class="color-border" align="center">
		<tr>
			<td>		
				<table width="100%" height="100%" border="0" align=center" cellspacing="1" class="color-header">				
				  <tr> 
                	  <td  colspan="3" class="color-title" align="left"><p class="label" > 
                    	  <b class="form-title">Perfil</b></p></td>
                  </tr>
					 
					  <tr> 
					  	<td width="77" align="right"><p class="label">Usuário</p></td>
						<td align="left"><input class="caixa-texto" type="checkbox" name="usuario" <%=usuario %>></td>
					  </tr>		
						
					  <tr>	
					  	<td height="20" align="right"><p class="label">Gestor</p></td>
					  	<td align="left"><input class="caixa-texto" type="checkbox" name="gestor" <%=gestor %>></td>
					  </tr>
					    	 
					  <tr>  	 
					  	<td height="20" align="right"><p class="label">Dono</p></td>
					  	<td align="left"><input class="caixa-texto" type="checkbox" name="dono" <%=dono %>></td>
					  </tr>  	
					   					    	 							
				</table>		
				
				<table width="100%" height="100%" border="0" align="center" cellspacing="1" class="color-header">				
					  <tr class="color-title">					
                  <td  colspan="3" >
                  		<span class="label"><b>Status</b></span></td>
					  </tr>
					  
						<tr>
					    	<td width="77" align="right"><p class="label">Habilitado</p></td>
							<td align="left"><input class="caixa-texto" type="checkbox" name="habilitado" <%=habilitado %>></td>
				        </tr>	 					    	 

						<tr>	 
							<td height="20" align="right"><p class="label">Desabilitado</p></td>
							<td align="left"><input class="caixa-texto" type="checkbox" name="desabilitado" <%=desabilitado %>></td>
						</tr>	 					    	 

				</table>
			
		<tr>
			<td>
				<table border="0" cellspacing="0" width="100%" height="100%" align="center" class="color-buttons">
					<tr> 
						<td height="30" colspan="3" align="center">
							<input type="submit" name="comando" class="label" value="   Gerar   "> 
						  	<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormRelatorioUsuario);">
						</td>
					</tr>				
				</table>
			</td>		
		</tr>
	</table>		
</form>

<% 	String tamanho = (String) request.getAttribute("tamanho");

    if (tamanho != null){
    	out.print("<table width=\"85%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
    	out.print("<tr valign=\"top\" class=\"color-border\" >");
		out.print("<td>");					
		out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
		out.println("<tr class=\"color-header\">");
		out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" align=\"middle\" width=\"40\" height=\"40\"></td><td align=\"center\"><span class=\"label\">Não foi encontrado registro !</span></td>");
		out.println("</tr>");
		out.println("</table>");					
		out.print("</td>");
		out.print("</tr>");
		out.print("</table><br>");
    }
%>

 <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
      <%@ include file="/visao/TelaMenuPrincipal.jsp" %>
    </td>
  
 <tr class="color-buttons"> 
    <td height="30" colspan="3">
	
	</td>
  </tr>
</table>

<center>
	<%@ include file="/visao/rodape.htm" %>
</center>
 
</body>
</html>