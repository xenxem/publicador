<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Valor" %>
<%@ page import="modelo.Pagamento" %>
<%@ page import="java.util.*" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Valor</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
</head>
<body>

<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" cellpadding="0" cellspacing="0" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>

<%	
	ArrayList listaValor = (ArrayList) request.getAttribute("listaValor");
	String operacao = (String) request.getAttribute("operacao");
	Valor v = (Valor) request.getAttribute("valor");

	if (v == null){
		
		v = new Valor();
		v.setCodigoValor(0);
		v.setValor(0);
	}
	
	if (operacao == null){
		operacao = "  Cadastrar  ";
	}
				
%>


<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="color-border">
  <tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> Alteração de Valor 
        On-Line </b></p></td>
  </tr>
  <tr class="color-header"> 
    <td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b></b></font></td>
	<td width="66%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> 
      <font class="label"><b>Cadastro de Valores</b></font></td>
	<td width="14%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
    <form name="FormAlterarValor" action="/publicadoranuncio/ControleAlterarValor" method="post">
        <table class="color-border" cellspacing="0" align="center">
          <tr> 
            <td> 
            	<table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  align="left" colspan="4" class="color-title"><p class="label" > 
                      <b>Valor</b></p></td>
                </tr>
                
                <tr> 
                  <td colspan="4"></td>
                </tr>
                <tr> 
                 <td  align="right"><font class="label">Valor do Anúncio</font></td>
                  <td colspan="2" align="left" ><input type="text" name="valor" title="Valor" class="caixa-texto" size="20" maxlength="15" value="<%=v.getValor()%>" > 
                  </td>
                  <td align="left" >&nbsp;</td>
                </tr>
                
              </table>
            </td>
          </tr>
          <tr> 
            <td height="30" colspan="4" align="center"> 
            <table class="color-buttons" width="100%" height="100%">
                <tr> 
                  <td align="center">
                  	              	          			       			                  			
               		<input type="submit" class="label" name="comando" value=<%=operacao %> onclick="return validacaoValor(document.FormAlterarValor);">		
                  		
                  	<input type="hidden" name="codigovalor" value=<%=v.getCodigoValor() %>>
                  	
                  	<%                  	
 						out.print("<script>");
                  		out.print("document.FormAlterarValor.valor.value = document.FormAlterarValor.valor.value.replace('.',',');");
                  		out.print("</script>");                 	
                  	%>
                  	                             
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
     </form>
    
    <table border="0" cellpadding="0" cellspacing="0" align="center">
	<tr valign="top" class="color-border" background="/publicadoranuncio/visao/imagem/fundo.png">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr class="color-title">
				
				<td colspan="7" align="center"> <p class="label" >&nbsp; <b class="form-title"> Valores </b></p></td>						
								
					
				</tr>
				<tr class="color-header"> 
	      		<td align="center"> <font class="label">No.</font></td>
			    <td align="center"> <font class="label">Valor</font></td>
			    <td align="center"> <font class="label">Status</font></td>
			    			 		
			 	<%
					if(listaValor != null){
						
						if(listaValor.size()==1){
							out.print("<td >&nbsp;</td><td >&nbsp;</td>");													
						}else{
							out.print("<td>&nbsp;</td><td >&nbsp;</td>");	
						}
					}
				
				%>
			 	</tr>     
	 		<%
	 			
	            if (listaValor != null){
	                        	
	            	ArrayList listaPagamento = (ArrayList) request.getAttribute("listaPagamento");
	            	
	        		for(int i=0; i < listaValor.size(); i++){
	        			
	        			Valor v2 = (Valor) listaValor.get(i);
	        			String s = new String();
	        			String descr = new String();
	        			boolean achouPag = false;
	        			
	        			out.println("<tr class=\"color-row\">");
	        	        out.println("<td align=\"center\" height=\"20\" <p class=\"label\">"+ (i+1) +"</td></p>");
	        	        
	        	        if (listaPagamento != null){ 
		        	        
	        	        	for (int m = 0; m < listaPagamento.size(); m++){
		        	        	
		        	        	Pagamento p = (Pagamento) listaPagamento.get(m);
		        	        	
		        	        	if (p.getValor().getCodigoValor() == v2.getCodigoValor())
		        	        		achouPag = true;
		        	        
		        	        }
	        	        }
	        	        
	        	        out.println("<td align=\"center\"><p class=\"label\">"+v2.getValor()+"</p></td>");
	        	        	        	        	        	        
	        	        if (v2.getStatus() == null)
	        	        	s = "";
	        	        else
	        	        	s = "A";
	        	        
	        	        out.println("<td align=\"center\"><p class=\"label\">"+s+"</p></td>");
	        	        out.println("<td align=\"center\" class=\"label\">");
	        	       	        	        
	        	        if (!achouPag){
	        	        	
	        	        	out.println("<a href=\"/publicadoranuncio/ControleAlterarValor?codigovalor="+v2.getCodigoValor()+"&comando=editar\"><img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"Editar\" border=\"0\"></a></td>");
	        	        		
	        	        	if (listaValor.size() != 1 && s != "A"){
	        	        		out.println("<td align=\"center\" class=\"label\"><a href=\"/publicadoranuncio/ControleAlterarValor?codigovalor="+v2.getCodigoValor()+"&comando=excluir\" onclick=\"return confirmaExclusao();\"s><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\" border=\"0\"></a></td>");
	        	        	}else{
	        	        		if(listaValor.size() > 1)
	        	        			out.println("<td align=\"center\" class=\"label\"></td>");	        	        	
	        	        	}
	        	        	out.println("</tr>");
	        	        }else{
	        	        	out.println("<td align=\"center\" class=\"label\">");
	        	        }
	        			
	        			
	        		}
	            }  
	        %>
	        <%  if(listaValor!=null){
	        		if(listaValor.size()==1){
	        			out.print("<tr class=\"color-buttons\">"); 
			       		out.print("<td height=\"30\" colspan=\"6\">&nbsp; </td>");
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
    
    </td>
     
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    
      	<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
      
    </td>
  </tr>
 <tr class="color-buttons"> 
    <td height="30" colspan="3"></td>
  </tr>
</table>

<center>
	<%@ include file="/visao/rodape.htm" %>
</center>

<script>
	function validacaoValor(f){
			
		if(f.valor.value.length > 0){				
			
			if(f.valor.value.indexOf(',')==-1){
			
				resultado = isNaN(f.valor.value);		
									
				if(resultado){
					alert("Preencha o campo " + f.valor.title + " com um número válido!");
					f.valor.focus();
					f.valor.select();			
					return false;	
				}else{					
					if(f.valor.value > 0)
						return true;
					else{
						alert("Preencha o campo " + f.valor.title + " com um número válido!");
						f.valor.focus();
						f.valor.select();
						return false;
					}				
				}			
			}else{
			
				f.valor.value = f.valor.value.replace(',','.');
				
				resultado = isNaN(f.valor.value)
				
				if(resultado){
					alert("Preencha o campo " + f.valor.title + " com um número válido!");
					f.valor.focus();
					f.valor.select();			
					return false;				
				}else{
					if(f.valor.value > 0)
						return true;
					else{
						alert("Preencha o campo " + f.valor.title + " com um número válido!");
						f.valor.focus();
						f.valor.select();
					    return false;
					} 
				}			
			}		
		}else{
			alert("Preencha o campo " + f.valor.title + " com um número válido!");
			f.valor.focus();
			return false;			
		}
	
	}
</script>

</body>
</html>