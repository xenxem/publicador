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
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=acompIndividual" title="Acompanhamento individual de anúncios">Acompanhamento Anúncio</a></td>
	</tr>
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=acompCategoria" title="Acompanhamento de anúncios por categoria">Acompanhamento por Categoria </a></td>
	</tr>
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=cadastroUsuario" title="Alteração de conta">Alterar Cadastro</a></td>
	</tr>	
	<tr>		
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=anuncio" title="Cadastro e publicação de anúncios">Anuncio</a></td>
	</tr>
	<%
	
		if( u2.getPerfil().equals("G") ||u2.getPerfil().equals("D")){ 
			out.println("<tr>");		
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=anuncioVencido\" title=\"Anúcios com data de publicação vencida\">Anúncios Vencidos</a></td>");
			out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=banco\" title=\"Bancos cadastrados\">Banco</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=categoria\" title=\"Cadastro de categorias\">Categoria</a></td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=conta\" title=\"Cadastro de contas bancárias\">Conta Bancária</a></td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=liberarAnuncio\" title=\"Liberação e bloqueio de anúncios\">Liberar Anúncio</a></td>");
			out.println("</tr>");			
		}
	%>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=pagamento" title="Dados para depósito">Pagamento</a></td>
	</tr>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=renovar" title="Renovação de Anúncios">Renovação</a></td>
	<tr>
		<td align="left"><a href="/publicadoranuncio/ControleMenuPrincipal?comando=responderAnuncio" title="Responder mensagens">Responder</a></td>
	</tr>
	
	<%
	
		if( u2.getPerfil().equals("G") ||u2.getPerfil().equals("D")){ 
	
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=valor\" title=\"Valor da publicação\">Valor</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioUsuario\">Relatório de Usuários</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioPagamento\">Relatório de Pagamentos</a></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioMaisVisitados\">Relatório de Anúncios Mais Visitados</a></td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\"><a href=\"/publicadoranuncio/ControleMenuPrincipal?comando=relatorioLibBloq\">Relatório de Liberação e Bloqueio</a></td>"); 
			out.println("</tr>");
			
		}
	%>
	
	<tr>
		<td align="left" style="border-style:solid; border-top-width:1pt; border-top-color:#666666; border-left-color:#ddddd5; border-right-color:#ddddd5; border-bottom-color:#ddddd5; "><a href="/publicadoranuncio/ControleMenuPrincipal?comando=sair" title="Sair da aplicação">Sair</a></td>		
	</tr>			
</table>
</td>
</tr>
</table>
		
</body>
</html>