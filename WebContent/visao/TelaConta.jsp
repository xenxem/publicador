<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Conta" %>
<%@page import="modelo.Banco"%>
<%@page import="modelo.Pagamento"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conta Bancária</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>
<%
	ArrayList listaConta = (ArrayList) request.getAttribute("listaConta");

	Conta c = (Conta) request.getAttribute("conta");
	
	if(c==null){

		c = new Conta();
		c.setCodigoConta(0);
		c.setAgencia("");
		c.setNumeroConta("");
		c.setStatus("");
		
		Banco b = new Banco();
		b.setCodigoBanco(0);
		c.setBanco(b);
		
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Contas</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" background="/publicadoranuncio/visao/imagem/fundo.png" valign="top" class="label" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
    <form name="FormConta" action="/publicadoranuncio/ControleConta" method="post" onsubmit="return verificaStatus(this);">
	<table border="0" cellspacing="0" class="color-border" align="center">
		<tr>
			<td>		
				<table width="100%" height="100%"   border="0" align="center" cellspacing="1" class="color-header">				
					  <tr class="color-title">					
                  <td  colspan="3" >
                  		<span class="label"><b>Dados da conta bancária</b></span></td>
					  </tr>
					  <tr> 
						<td align="right"><p class="label">Banco</p></td>
						<td colspan="2">
							
							
						   <select name="codigoBanco" title="Banco" class="text">
								<option value="">===Banco===</option>
								<%
									ArrayList listaBanco = (ArrayList) request.getAttribute("listaBanco");
								
									Banco b = (Banco) request.getAttribute("banco");
									
									if (b == null){
										b = new Banco();
										b.setCodigoBanco(0);
									}
									
									if (listaBanco != null){
										
										for(int i = 0; i < listaBanco.size(); i++){
											
											Banco b2 = new Banco();
											b2 = (Banco) listaBanco.get(i);
											
											if (c.getBanco().getCodigoBanco() == (b2.getCodigoBanco())){
												out.println("<option selected value=" + b2.getCodigoBanco() + ">" + b2.getDescricaoBanco() + "</option>");
											}else{
												out.println("<option value=" + b2.getCodigoBanco() + ">" + b2.getDescricaoBanco() + "</option>");
											}
											
										}
										
									}
									
								%>						   
						   </select>
						</td>
					  </tr>
					  <tr> 
						<td  width="120" align="right"><p class="label"> Agência</p></td>
						<td colspan="2"><input class="caixa-texto" type="text" title="Agência" name="agencia" maxlength="10" size="15" value=<%=c.getAgencia()%>></td>
					  </tr>
					  
					  <tr> 
						<td height="20" align="right"><p class="label">Conta</p></td>
						<td colspan="2" valign="top"><input class="caixa-texto" type="text" title="Conta" name="numeroConta" maxlength="15" size="20" value=<%=c.getNumeroConta()%>></td>
					  </tr>		
					  <tr>
					    
					    <% 
					    			
					    		   if(c.getStatus()=="" || c.getStatus()==null) { 	
					    			   out.print("<td height=\"20\" align=\"right\"><p class=\"label\">Ativa</p></td>");
									   out.print("<td colspan=\"2\" align=\"left\">");
					    			   out.print("<input class=\"caixa-texto\" type=\"checkbox\" id=\"status\"  name=\"status\">");	   
						           }
						           					              
					    %>
					  </tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellspacing="0" width="100%" height="100%" align="center" class="color-buttons">
					<tr> 
						<td height="30" colspan="3" align="center">
							<input type="submit" name="comando" class="label" value=<%=operacao %> onclick="return validacao(document.FormConta);"> 
						  	<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormConta);">
						</td>
					</tr>				
				</table>
			</td>		
		</tr>
	</table>	
		<input name="codigoconta" type="hidden" value=<%=c.getCodigoConta()%>>
	</form>
	
	<table border="0" cellpadding="0" cellspacing="0" align="center">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr class="color-title">
				
				<td colspan="7" align="center"> <p class="label" >&nbsp; <b class="form-title"> Contas Bancárias </b></p></td>						
								
					
				</tr>
				<tr class="color-header"> 
	      		<td align="center"> <font class="label">No.</font></td>
			    <td align="center"> <font class="label">Banco</font></td>
			    <td align="center"> <font class="label">Agência</font></td>
			    <td align="center"> <font class="label">Conta</font></td>
			    <td align="center"> <font class="label">Status</font></td>
			 		
			 		
			 		<%
					if(listaConta != null){
						
						if(listaConta.size()==1){
							out.print("<td >&nbsp;</td>");													
						}else{
							out.print("<td>&nbsp;</td><td >&nbsp;</td>");	
						}
					}
				
				%>
			 	</tr>     
	 		<%
	 			
	            if (listaConta != null){
	                        	
	            	ArrayList listaPagamento = (ArrayList) request.getAttribute("listaPagamento");
	            	
	        		for(int i=0; i < listaConta.size(); i++){
	        			
	        			Conta c2 = (Conta) listaConta.get(i);
	        			String s = new String();
	        			String descr = new String();
	        			boolean achouPag = false;
	        			
	        			out.println("<tr class=\"color-row\">");
	        	        out.println("<td align=\"center\" height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
	        	        
	        	        for (int j = 0; j < listaBanco.size(); j++){
	        	        	
	        	        	Banco bE = (Banco) listaBanco.get(j);
	        	        	
	        	        	if (bE.getCodigoBanco() == c2.getBanco().getCodigoBanco()){
	        	        		descr = bE.getDescricaoBanco();
	        	        	}
	        	        	
	        	        }   
	        	        
	        	        if (listaPagamento != null){ 
		        	        
	        	        	for (int m = 0; m < listaPagamento.size(); m++){
		        	        	
		        	        	Pagamento p = (Pagamento) listaPagamento.get(m);
		        	        	
		        	        	if (p.getConta().getCodigoConta() == c2.getCodigoConta())
		        	        		achouPag = true;
		        	        
		        	        }
	        	        }
	        	        
	        	        out.println("<td align=\"center\"><p class=\"label\">"+descr+"</p></td>");
	        	        out.println("<td align=\"center\"><p class=\"label\">"+c2.getAgencia()+"</p></td>");
	        	        out.println("<td align=\"center\"><p class=\"label\">"+c2.getNumeroConta()+"</p></td>");
	        	        
	        	        if (c2.getStatus() == null)
	        	        	s = "";
	        	        else
	        	        	s = "A";
	        	        
	        	        out.println("<td align=\"center\"><p class=\"label\">"+s+"</p></td>");
	        	        out.println("<td align=\"center\" class=\"label\">");
	        	       	        	        
	        	        if (!achouPag){
	        	        	
	        	        	out.println("<a href=\"/publicadoranuncio/ControleConta?codigoconta="+c2.getCodigoConta()+"&comando=editar\"><img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"Editar\" border=\"0\"></a></td>");
	        	        		
	        	        	if (listaConta.size() != 1 && s != "A"){
	        	        		out.println("<td align=\"center\" class=\"label\"><a href=\"/publicadoranuncio/ControleConta?codigoconta="+c2.getCodigoConta()+"&comando=excluir\" onclick=\"return confirmaExclusao();\"s><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\" border=\"0\"></a></td>");
	        	        	}else{
	        	        		if(listaConta.size() > 1)
	        	        			out.println("<td align=\"center\" class=\"label\"></td>");	        	        	
	        	        	}
	        	        	out.println("</tr>");
	        	        }else{
	        	        	out.println("<td align=\"center\" class=\"label\">");
	        	        }
	        			
	        			
	        		}
	            }  
	                      
	        %>
	        
	        <%  
	        	if(listaConta!=null){
	        		if(listaConta.size()==1){
	        			out.print("<tr class=\"color-buttons\">"); 
			       		out.print("<td height=\"30\" colspan=\"7\">&nbsp; </td>");
			    		out.print("</tr>");
	        		}else{
	        			out.print("<tr class=\"color-buttons\">"); 
			       		out.print("<td height=\"30\" colspan=\"7\">&nbsp; </td>");
			    		out.print("</tr>");
	        		}
		    		
	        	}
		    %>
	 	 </table>
     
	</table> 
    
	  
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
 
 <script>
 	
 	//confirmando inclusão de conta ativa
 	function verificaStatus(f){
 		
		if (isNaN(f.agencia.value)){
 			alert("Valor inválido para o campo Agência !");
 			return false;
 		}
 		
 		if (isNaN(f.numeroConta.value)){
 			alert("Valor inválido para o campo Conta !");
 			return false;
 		}
 		
 		if (f.status.checked==true){
 			if(confirm('Essa operação inativará a conta atualmente ativa.\n'+'Confirma a inclusão?')==1){
 				return true;
 			}else{
 				return false;
 			} 
 		}
 		
 	}
 	
 </script>
 
</body>
</html>