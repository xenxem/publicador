<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Principal</title>

<%

	modelo.Usuario u2 = (modelo.Usuario) session.getAttribute("usuario");
	

%>
</head>
<body class="color-row" >
<table>
<tr class="color-border">
<td>
<table border="0" align="center" class="color-header" >
	<tr>
	  	<td  align="left" class="color-title"> <div align="center"><strong><font size="2">Menu</font> </strong></div> </td>
	</tr>
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=acompIndividual" title="Acompanhamento individual de an�ncios">Acompanhamento An�ncio</a></td>
	</tr>
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=acompCategoria" title="Acompanhamento de an�ncios por categoria">Acompanhamento por Categoria </a></td>
	</tr>
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=cadastroUsuario" title="Altera��o de conta">Alterar Cadastro</a></td>
	</tr>	
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=anuncio" title="Cadastro e publica��o de an�ncios">Anuncio</a></td>
	</tr>
	<%
	
		if( u2.getPerfil().equals("G") ||u2.getPerfil().equals("D")){ 
			out.println("<tr>");		
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=anuncioVencido\" title=\"An�cios com data de publica��o vencida\">An�ncios Vencidos</a></td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=banco\" title=\"Bancos cadastrados\">Banco</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=categoria\" title=\"Cadastro de categorias\">Categoria</a></td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=conta\" title=\"Cadastro de contas banc�rias\">Conta Banc�ria</a></td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=liberarAnuncio\" title=\"Libera��o e bloqueio de an�ncios\">Liberar An�ncio</a></td>");
			out.println("</tr>");			
		}
	%>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=pagamento" title="Dados para dep�sito">Pagamento</a></td>
	</tr>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=renovar" title="Renova��o de An�ncios">Renova��o</a></td>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=responderAnuncio" title="Responder mensagens">Responder</a></td>
	</tr>
	
	<%
	
		if( u2.getPerfil().equals("G") ||u2.getPerfil().equals("D")){ 
	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=valor\" title=\"Valor da publica��o\">Valor</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioUsuario\">Relat�rio de Usu�rios</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioPagamento\">Relat�rio de Pagamentos</a></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioMaisVisitados\">Relat�rio de An�ncios Mais Visitados</a></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioLibBloq\">Relat�rio de Libera��o e Bloqueio</a></td>"); 
			out.println("</tr>");
			
		}
	%>
	
	<tr>
		<td align="left" style="border-style:solid; border-top-width:1pt; border-top-color:#666666; border-left-color:#ddddd5; border-right-color:#ddddd5; border-bottom-color:#ddddd5; "><a href="/publicadoranuncio/ControleMenuPrincipal?comando=sair" title="Sair da aplica��o">Sair</a></td>		
	</tr>			
</table>
</td>
</tr>
</table>
		
</body>
</html>