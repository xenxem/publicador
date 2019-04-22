<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="modelo.Pagamento" %>
<%@page import="modelo.Anuncio" %>
<%@page import="java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagamento</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>
<%
	ArrayList listaPagamento = (ArrayList) request.getAttribute("listaPagamento");	
			
%>

<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" cellpadding="0" cellspacing="0" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>

<table border="0" cellspacing="1" cellpadding="2" height="100%" width="100%" align="center" class="color-border">
  <tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados 
        On-Line </b></p></td>
  </tr>
  <tr class="color-header"> 
    <td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b></b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Pagamentos</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
	<table align="center" cellspacing="1" cellpadding="2" class="color-border" >
 			<tr>
 				<td class="color-title" colspan="9">
 					<p class="label" align="center"><b>Dados Para Depósitos</b></p>
 				</td> 			
 			</tr>
 			<tr class="color-header">
		 	<td ><span class="label">No.</span></td>
		 	<td align="center"><span class="label">Título</span></td>		 	
		 	<td align="center"><span class="label">Banco</span></td>
		 	<td align="center"><span class="label">Agência</span></td>
		 	<td align="center"><span class="label">Conta</span></td>
		 	<td align="center"><span class="label">valor</span></td>
		 	<td align="center"><span class="label">Id. Depósito</span></td>
		 	<td align="center"><span class="label">Data</span></td>
		 	<td>&nbsp;</td>		 	
		 	</tr>        
       
 			
       	<%	
       		
       		Locale locale = new Locale("pt","BR"); 	
			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
	
			       	
       	
            if (listaPagamento != null){
            	
            	            	
        		for(int i=0; i < listaPagamento.size(); i++){
        			
        		   Pagamento p = (Pagamento) listaPagamento.get(i);       			
        		   
        		   
        		   out.println("<tr class=\"color-row\">");
        	       out.println("<td align=\"center\"><p class=\"label\">"+ (i+1) +"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+ p.getAnuncio().getTitulo()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+ p.getConta().getBanco().getDescricaoBanco()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+p.getConta().getAgencia()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+p.getConta().getNumeroConta()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+p.getValor().getValor()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+p.getNumeroParaIdentificacao()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+formatador.format(p.getData())+"</p></td>");
        	       out.println("<td align=\"center\" class=\"label\">");       	       
            	   out.println("<a href=\"/publicadoranuncio/ControlePagamento?codigoPagamento="+p.getCodigoPagamento() +"&comando=excluir\" onclick=\"return confirmaExclusao();\"s><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\" border=\"0\"></a>");
        	       out.println("</td>");
        	       
        		}
            }else{       	
	       		Pagamento pgto = (Pagamento) request.getAttribute("pgto");
	       		
	       		if(pgto!=null){            	
	            	
	            	out.println("<tr class=\"color-row\">");
	     	        out.println("<td align=\"center\"><p class=\"label\">1</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+ pgto.getAnuncio().getTitulo()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+ pgto.getConta().getBanco().getDescricaoBanco()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+pgto.getConta().getAgencia()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+pgto.getConta().getNumeroConta()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+pgto.getData()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+pgto.getValor().getValor()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+pgto.getNumeroParaIdentificacao()+"</p></td>");
	     	        out.println("<td align=\"center\"><p class=\"label\">"+formatador.format(pgto.getData())+"</p></td>");
	            	
	            }
            }
                      
        %>  
         <tr class="color-buttons"> 
	       <td height="30" colspan="9">&nbsp; </td>
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

</body>
</html>