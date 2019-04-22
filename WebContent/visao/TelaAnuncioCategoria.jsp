<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categoria</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
<script>
	//código passados ao se clicar em gategorias
	function setCodigos(c,n){
		document.FormCategoria.codigoCategoria.value = c;
		document.FormCategoria.nivelCategoria.value = n;	
		
	}
</script>


</head>
<body>
<%
	
	Categoria categoria = (Categoria) request.getAttribute("categoria");
	
	if(categoria==null){
		
		categoria = new Categoria();
		categoria.setCodigoCategoria(0);
		categoria.setNivelCategoria(0);
		categoria.setDescricaoCategoria("");
		categoria.setCodigoCategoriaPai(0);
		categoria.setNivelCategoriaPai(0);
		categoria.setOrdemExibicao(0);	
		
	}
	

	String operacao = (String) request.getAttribute("operacao");
	if(operacao==null){
		operacao="cadastrar";
	}

	 
%>


<table  width="100%" border="0" cellspacing="0" cellpadding="5" align="center">
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
	<td width="66%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Categoria</b></font></td>
	<td width="14%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" > 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    	<br>
    	<form name="FormCategoria" action="/publicadoranuncio/ControleCagetoria" method="post">
        <table class="color-border" cellspacing="0" align="center">
          <tr> 
            <td> <table  border="0" width="100%" height="100%" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td width="274"  colspan="3" class="color-title"><p class="label" >&nbsp; 
                      <b class="form-title"> Categoria </b></p></td>
                </tr>
                <tr> 
                  <td width="77" align="right"  valign="top">&nbsp;</td>
                  <td colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td height="44" valign="top" align="right"><font class="label">Categoria</font></td>
                  <td colspan="2" valign="top"> <iframe src="/publicadoranuncio/visao/Arvoren.jsp" width="200" style="border-style:solid; border-color:#666666; border-width:1px;"  frameborder="0"></iframe> 
                  </td>
                </tr>
                <tr> 
                  <td align="right">&nbsp;</td>
                  <td colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Descrição</font></td>
                  <td colspan="2"><input type="text"  class="caixa-texto" title="Descrição " name="descricaoCategoria"  maxlength="30"  size="37" onKeyPress="javascript:limitaQtdeCaracter(this);" value=<%=categoria.getDescricaoCategoria()%>></td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td colspan="2">&nbsp;</td>
                </tr>
              </table>
          </td>
          </tr>
          <tr> 
            <td height="30" colspan="4" align="center"> <table class="color-buttons" width="100%" height="100%">
                <tr> 
                  <td align="center"> 
                  	<input type="submit" class="label" name="comando" value=" Cadastrar " onClick="return validacao(document.FormCategoria);">
                  	<input type="submit" class="label" name="comando" value="  Alterar  " onClick="return validaAlteracao(document.FormCategoria);"> 
                  	<input type="submit" class="label" name="comando" value="  Excluir  " onClick="return validaExclusao(document.FormCategoria);">
                  	<input type="submit" class="label" name="comando" value=" Consultar " onClick="return validaConsulta(document.FormCategoria);">
                     
                    <input type="hidden" size="3" name="operacao"  value=<%=operacao %>>	
                    <input type="hidden" name="codigoCategoria"> 
                    <input type="hidden" name="nivelCategoria">
                    <input type="hidden" name="descricaoCategoria">                    
                  </td>
                </tr>
              </table></td>
          </tr>
        </table>
      </form>
      <br>
      <table align="center" cellspacing="1" cellpadding="2" class="color-border" >
      	<tr>
		  	<td class="color-title" colspan="4">
		    	<p class="label" align="center"><b>Categorias Encontradas</b></p>
			</td> 			
		</tr>
		<tr class="color-header">
		  <td><span class="label">No.</span></td>		 	
		  <td width="100" align="center"><span class="label">Codigo</span></td>
		  <td width="100" align="center"><span class="label">Nivel</span></td>
		  <td width="100" align="center"><span class="label">Descrição</span></td>		 	
	    </tr>
      	<%	
       	
       		ArrayList listaCategoria = (ArrayList) request.getAttribute("listaCategoria");
        		
            if (listaCategoria != null){
       
		      	
		    	
		       
 			
            	
            	            	
        		for(int i=0; i < listaCategoria.size(); i++){
        			
        		   Categoria c = (Categoria) listaCategoria.get(i);       			
        		        			
        		   out.println("<tr class=\"color-row\">");
        	       out.println("<td align=\"center\"><p class=\"label\">"+ (i+1) +"</p></td>");        	                	        
        	       out.println("<td align=\"center\"><p class=\"label\">"+ c.getCodigoCategoria()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+c.getNivelCategoria()+"</p></td>");
        	       out.println("<td align=\"center\"><p class=\"label\">"+c.getDescricaoCategoria()+"</p></td>");
        	       out.println("</tr>");       	  
  	        	
        	     
        		}
            }  
                      
        
            
 		%> 
 		 <tr class="color-buttons"> 
         	<td height="30" colspan="4">&nbsp; </td>
         </tr>            
      </table>
      <%
			String msg = request.getParameter("msg");
      
			if(msg!=null){
				if(msg.equals("sucesso")){
						out.println("<script>alert('Categoria excluída com sucesso');</script>");
				}
			
				if(msg.equals("temFilho")){
					out.println("<script>alert('Categoria possui filhos');</script>");
				}
				
				if(msg.equals("participa")){
					out.println("<script>alert('Categoria possui relacionamento');</script>");
				}
			}
	  %>
      
    </td>
	  
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
      <%@ include file="/visao/TelaMenuPrincipal.jsp" %>
    </td>
  </tr>
 <tr class="color-buttons"> 
    <td height="30" colspan="3">&nbsp;</td>
  </tr>
</table>


<center>
	<%@ include file="/visao/rodape.htm" %>
</center>

<script>	
	
	
	//vefificando se categoria foi escolhida para
	//alteração, exclusão ou consulta
	
	//alteração
	function validaAlteracao(f){		
		
		if(f.codigoCategoria.value==""){
			alert('Escolha uma categoria para "Alteração"');
			return false;
		}		
		
		if(f.descricaoCategoria.value.length==0){
			alert("Preencha o campo "+f.descricaoCategoria.title);
			f.descricaoCategoria.focus();
			return false;
		}			
	}
	
	//exclusão
	function validaExclusao(f){		
		if(f.codigoCategoria.value==""){
			alert('Escolha  uma categoria para "Exlusão"');
			return false;			
		}		
	}
	
	//consulta
	function validaConsulta(f){
		if(f.descricaoCategoria.value.length==0){
			alert('Preencha o campo '+f.descricaoCategoria.title +' para "Consultar"');
			f.descricaoCategoria.focus();
			return false;
		}	
	}
</script>




</body>
</html>