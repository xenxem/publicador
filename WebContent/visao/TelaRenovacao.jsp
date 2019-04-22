<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="modelo.Pagamento" %>
<%@page import="modelo.Anuncio" %>
<%@page import="java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Renovação</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>
<%
	ArrayList listaAnuncio = (ArrayList) request.getAttribute("listaAnuncio");	
			
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Renovação</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
	<table align="center" cellspacing="1" cellpadding="2" class="color-border" >
 			<tr>
 				<td class="color-title" colspan="6">
 					<p class="label" align="center"><b>Anúncios Para Renovação</b></p>
 				</td> 			
 			</tr>
 			<tr class="color-header">
		 	<td ><span class="label">No.</span></td>		 	
		 	<td width="100" align="center"><span class="label">Cod.</span></td>
		 	<td width="100" align="center"><span class="label">Título</span></td>
		 	<td width="100" align="center"><span class="label">Descrição</span></td>		 	
		 	<td>&nbsp;</td>		 		 	
		 	</tr>        
       
 			
       	<%	
        		
            if (listaAnuncio != null){
            	
            	            	
        		for(int i=0; i < listaAnuncio.size(); i++){
        			
        		   Anuncio a = (Anuncio) listaAnuncio.get(i);       			
        		   
        		   if(a.getStatus().equals("L") || a.getStatus().equals("P")){
	        		   out.println("<tr class=\"color-row\">");
	        	       out.println("<td align=\"center\"><p class=\"label\">"+ (i+1) +"</p></td>");        	                	        
	        	       out.println("<td align=\"center\"><p class=\"label\">"+ a.getCodigoAnuncio()+"</p></td>");
	        	       out.println("<td align=\"center\"><p class=\"label\">"+a.getTitulo()+"</p></td>");
	        	       out.println("<td align=\"center\"><p class=\"label\">"+a.getDescricaoAnuncio()+"</p></td>");
	        	       out.println("<td width=\"1\" align=\"center\" class=\"label\">");
	            	   out.println("<a href=\"/publicadoranuncio/ControleRenovacao?comando=renovacao&codigoAnuncio="+a.getCodigoAnuncio() +"&comando=renovacao\" onclick=\"return confirmaRenovacao();\"s><img src=\"/publicadoranuncio/visao/imagem/verificar.png\" alt=\"Renovar anúncio \" border=\"0\"></a></td>");
        		   }       	     
        		}
            }  
                      
        %>  
         <tr class="color-buttons"> 
	       <td height="30" colspan="6">&nbsp; </td>
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
	function confirmaRenovacao(){	
		if(confirm("Deseja realmente renovar este anúncio?")==1)
			return true;
		else
			return false;	
	}
</script>
</body>
</html>