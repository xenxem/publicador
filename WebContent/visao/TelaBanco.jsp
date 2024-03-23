<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
    
<%@page import="modelo.Banco" %>
<%@page import="modelo.Conta" %>
<%@page import="java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bancos</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>
<%
	ArrayList listaBanco = (ArrayList) request.getAttribute("listaBanco");	
	
	Banco b = (Banco) request.getAttribute("banco");
	
	if(b==null){

		b = new Banco();
		b.setCodigoBanco(0);
		b.setNumero("");
		b.setDescricaoBanco("");
		
	}
	
	String operacao = (String) request.getAttribute("operacao");
	
	if (operacao == null)
		operacao = "   Cadastrar   ";
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Bancos</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">
    <br> 
    <form name="FormBanco" action="/publicadoranuncio/ControleBanco" method="post" onsubmit="return validaCampos(this)";>
	<table border="0" cellspacing="0" class="color-border" align="center">
		<tr>
			<td>		
				<table width="100%" height="100%"   border="0" align="center" cellspacing="1" class="color-header">				
					  <tr class="color-title"> 
						
                  <td  colspan="3" class="colot-title"><p class="label"><b>Dados do Banco</b></td>
					  </tr>
					  <tr> 
						<td  width="120" align="right"><p class="label">Número</p></td>
						<td colspan="2"><input type="text" class="caixa-texto" title="Numero" name="numero" maxlength="10" size="15" value=<%=b.getNumero()%>></td>
					  </tr>
					  <tr> 
						<td align="right"  valign="top"><p class="label">Descrição</p></td>
						<td colspan="2">
							<input type="text" title="Descrição" class="caixa-texto" name="descricao" maxlength="20" size="25" value="<%=b.getDescricaoBanco()%>"></td>
					  </tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellspacing="0" width="100%" height="100%" align="center" class="color-buttons">
					<tr> 
						<td height="30" colspan="3" align="center">
							<input type="submit" name="comando" class="label" value=<%=operacao %> onclick="return validacao(document.FormBanco);"> 
						  	<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormBanco);">
						</td>
					</tr>				
				</table>
			</td>		
		</tr>
	</table>	
		<input type="hidden" name="codigobanco" value=<%=b.getCodigoBanco()%>>
	</form>
	
	<table align="center" cellspacing="1" cellpadding="2" class="color-border" >
 			<tr>
 				<td class="color-title" colspan="5">
 					<p class="label" align="center"><b>Bancos</b></p>
 				</td> 			
 			</tr>
 			<tr class="color-header">
		 	<td ><span class="label">No.</span></td>
		 	<td width="50" align="center" ><span class="label">Número</span></td>
		 	<td width="100" align="center"><span class="label">Descrição</span></td>
		 	<td>&nbsp;</td>
		 	<td>&nbsp;</td>
		 	</tr>        
       
 				
       	<%	
        		
            if (listaBanco != null){
            	
            	ArrayList listaConta = (ArrayList) request.getAttribute("listaConta");
            	
        		for(int i=0; i < listaBanco.size(); i++){
        			
        			Banco b2 = (Banco) listaBanco.get(i);
        			boolean achouConta = false;
        		        			
        			out.println("<tr class=\"color-row\">");
        	        out.println("<td align=\"center\"><p class=\"label\">"+ (i+1) +"</p></td>");
        	        
        	        if (listaConta != null){
        	        	
        	        	for(int j=0; j < listaConta.size(); j++){
        	        		
        	        		Conta c = (Conta) listaConta.get(j);
        	        		
        	        		if (c.getBanco().getCodigoBanco() == b2.getCodigoBanco())
        	        			achouConta = true;
        	        		
        	        	}
        	        	
        	        }
        	        
        	        out.println("<td align=\"center\"><p class=\"label\">"+b2.getNumero()+"</p></td>");
        	        out.println("<td align=\"center\"><p class=\"label\">"+b2.getDescricaoBanco()+"</p></td>");
        	       
        	        
        	        if (!achouConta){
        	        	
        	        	out.println("<td width=\"1\" align=\"center\" class=\"label\">");
        	        	out.println("<a href=\"/publicadoranuncio/ControleBanco?codigobanco="+b2.getCodigoBanco()+"&comando=editar\"><img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"Editar\" border=\"0\"></a></td>");
        	        	out.println("<td width=\"1\" align=\"center\" class=\"label\">");
            	        out.println("<a href=\"/publicadoranuncio/ControleBanco?codigobanco="+b2.getCodigoBanco()+"&comando=excluir\" onclick=\"return confirmaExclusao();\"s><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\" border=\"0\"></a></td>");
  	        	
        	        }else{
        	        	out.println("<td align=\"center\" class=\"label\">");
        	        	out.println("<td align=\"center\" class=\"label\">");
        	        }
        		}
            }  
                      
        %>  
         <tr class="color-buttons"> 
	       <td height="30" colspan="5">&nbsp; </td>
	     </tr>    
 	 </table>
      <br>

	  
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
      <%@ include file="/visao/TelaMenuPrincipal.jsp" %>
    </td>

 <tr class="color-buttons"> 
    <td height="30" colspan="3">&nbsp;</td>
  </tr>
</table>

<center>
	<%@ include file="/visao/rodape.htm" %>
</center>

<script>

	function validaCampos(f){
	
		if (isNaN(f.numero.value)){
 			alert("Valor inválido para o campo Número !");
 			return false;
 		}
 		
 		if (!isNaN(f.descricao.value)){
 			alert("Valor inválido para o campo Descrição !");
 			return false;
 		}
	
	}

</script>

</body>
</html>