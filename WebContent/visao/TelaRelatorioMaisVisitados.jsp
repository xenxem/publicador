<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Categoria"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório de Anúncios Mais Visitados</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

<script>

	//código passado ao se clicar em categoria
	function setCodigos(c,n){
		document.FormRelatorioMaisVisitados.codigoCategoria.value = c;
		document.FormRelatorioMaisVisitados.nivelCategoria.value = n;	
	}
</script>

</head>
<body>
<%	Anuncio a = (Anuncio) request.getAttribute("anuncio"); 
	String liberado = (String)request.getAttribute("liberado");
	String pendente = (String)request.getAttribute("pendente");
	String bloqueado = (String)request.getAttribute("bloqueado");
	
	if (a == null){
		
		a = new Anuncio();
		
		Categoria categoria = new Categoria();
		
		categoria.setCodigoCategoria(0);
		categoria.setNivelCategoria(0);
		a.setCategoria(categoria);
		
		Cidade cidade = new Cidade();
		cidade.setCodigoCidade(0);
		a.setCidade(cidade);
		
		Uf uf = new Uf();
		uf.setCodigoUf("0");
		a.setUf(uf);
		
	}
	
	if (liberado == null){
		liberado = new String();
		liberado = "";
	}
	if (pendente == null){
		pendente = new String();
		pendente = "";
	}
	if (bloqueado == null){
		bloqueado = new String();
		bloqueado = "";
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
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
<br>   
<form name="FormRelatorioMaisVisitados" action="/publicadoranuncio/ControleRelatorioMaisVisitados" method="get">
	<table border="0" cellspacing="0" class="color-border" align="center">
		<tr>
			<td>		
				<table width="100%" height="100%" border="0" align="center" cellspacing="1" class="color-header">				
				  <tr> 
                	  <td  colspan="3" class="color-title" align="left"><p class="label" > 
                    	  <b class="form-title">Status</b></p></td>
                  </tr>
					  
						<tr>
							<td align="right"><p class="label">Liberado</p></td>
							<td align="left"><input class="caixa-texto" type="checkbox" name="liberado" <%=liberado %>></td>
						</tr>					    	 
						
						<tr>
							 <td align="right"><p class="label">Pendente</p></td>
							 <td align="left"><input class="caixa-texto" type="checkbox" name="pendente" <%=pendente %>></td>
						</tr>	 
					    	 
					    <tr>	 
					    	 <td align="right"><p class="label">Bloqueado</p></td>
					    	 <td align="left"><input class="caixa-texto" type="checkbox" name="bloqueado" <%=bloqueado %>></td>
					    </tr>
			
               <tr>
                <td align="right"><font class="label">Uf</font></td>
                  <td align="left"> <select name="codigoUf" title="Estado" class="text" onchange="setOperacao('populaCombo');">
                      <option value="Estado">===Estado===</option>
                      <%
	                    	ArrayList listaUf = (ArrayList) request.getAttribute("listaUf");                    	
	                    	
	                    	Uf uf = (Uf) request.getAttribute("uf");
	                    	
	                    	if(listaUf!=null){
	                    		
	                    		for(int i=0; i < listaUf.size(); i++){	                    			
	                    			Uf uf2 = new Uf();                   			
	                    			uf2 = (Uf) listaUf.get(i);	                    			
	                    				if (uf != null && !uf.getDescricaoUf().equals("Estado")){
		                    				out.println("<option selected value=" + uf.getCodigoUf() + ">" + uf.getDescricaoUf()+ "</option>");
		                    				uf = null;
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
                      <option value="Cidade">===Cidade===</option>
                      <%
	                    	ArrayList listaCidade = (ArrayList) request.getAttribute("listaCidade");	                    	
	               			if(listaCidade!=null){	               				
	               				for(int i=0; i < listaCidade.size(); i++){	               					
	               					Cidade c2 = new Cidade();	
	               					c2 = (Cidade) listaCidade.get(i);	               				
			                    		out.println("<option value=" + c2.getCodigoCidade() + ">"+c2.getDescricaoCidade()+"</option>");		
	               				}
	               			}
	                    %>
                    </select> </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" ><iframe src="/publicadoranuncio/visao/Arvoren.jsp" width="230" height="125" style="border-style:solid; border-color:#666666; border-width:1px;"  frameborder="0"></iframe></td>
                </tr>
                	<input type="hidden" name="popula">
                	<input type="hidden" name="codigoCategoria">
                	<input type="hidden" name="nivelCategoria"> 
               
            </table>
		<tr>
			<td>
				<table border="0" cellspacing="0" width="100%" height="100%" align="center" class="color-buttons">
					<tr> 
						<td height="30" colspan="3" align="center">
							<input type="submit" name="comando" class="label" value="   Gerar   "> 
						  	<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormRelatorioMaisVisitados);">
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

 <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
      <%@ include file="/visao/TelaMenuPrincipal.jsp" %>
    </td>
  
 <tr class="color-buttons"> 
    <td height="30" colspan="3">
	
	</td>
  </tr>

<center>
	<%@ include file="/visao/rodape.htm" %>
</center>

<script>

	function setCodigo(codigo){
		document.FormRelatorioMaisVisitados.codigoCidade.value = codigo;			
	}
	
	function setOperacao(op){				
			document.FormRelatorioMaisVisitados.popula.value = op;			
			document.FormRelatorioMaisVisitados.submit();
	}

</script>
 
</body>
</html>