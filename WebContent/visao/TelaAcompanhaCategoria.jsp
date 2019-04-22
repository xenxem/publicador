<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.AcompanhaCategoria"%>
<%@ page import="modelo.Usuario"%>
<%@ page import="modelo.Anuncio"%>
<%@ page import="modelo.Categoria"%>
<%@ page import="modelo.Cidade"%>
<%@ page import="modelo.Uf"%>
<%@ page import="modelo.Imagem"%>
<%@ page import="java.util.ArrayList"%>

<%! ArrayList listaImagem; %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anúncio</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

<script>
	//código passados ao se clicar em gategorias
	function setCodigos(c,n,t){
		document.FormAcompanha.codigoCategoria.value = c;
		document.FormAcompanha.nivelCategoria.value = n;		
		document.getElementById("descricaoCategoria").innerHTML = t;				
	}		
</script>
</head>
<body>
<%
	//para listar anúncios
	ArrayList listaAcompanha = (ArrayList) request.getAttribute("listaAcompanha");

	AcompanhaCategoria acompCategoria = (AcompanhaCategoria) request.getAttribute("acompCategoria");
	
	if(acompCategoria==null){
		
		acompCategoria = new AcompanhaCategoria();
		
		Usuario usuario = new Usuario();
		usuario.setApelido("");
		usuario.setCodigoUsuario(0);
		usuario.setPerfil("");
		acompCategoria.setUsuario(usuario);
		
		Anuncio a = new Anuncio();
		a.setCodigoAnuncio(0);
		a.setDescricaoAnuncio("");
		a.setTitulo("");
		acompCategoria.setAnuncio(a);
		
		Categoria c = new Categoria();
		c.setCodigoCategoria(0);
		c.setNivelCategoria(0);
		c.setDescricaoCategoria("");
		acompCategoria.setCategoria(c);
		
		Uf uf = new Uf();
		uf.setCodigoUf("");
		uf.setDescricaoUf("");
		acompCategoria.setUf(uf);
		
		Cidade cidade = new Cidade();
		cidade.setCodigoCidade(0);
		cidade.setDescricaoCidade("");
		acompCategoria.setCidade(cidade);
		
		acompCategoria.setPeriodo("");
				
	}
	

	String operacao = (String) request.getAttribute("operacao");
	if(operacao==null){
		operacao="Cadastrar";
	}

	 
%>


<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>

<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="color-border">
<tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados On-Line </b></p></td>
</tr>
<tr class="color-header">		          
 	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Fotos</b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Acompanhamento por Categoria</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
</tr>    
<tr bgcolor="ffffff" class="color-row">         
    <td height="125" align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">	
    
	
	<table border="0" cellspacing="1" cellpadding="0" align="center" width="100%" class="color-title">
	<tr>
		<td align="center" class="label">
			<table border="0" cellspacing="0" cellpadding="2" align="center" width="100%" class="color-header">
			<tr>
	 			<td align="center" class="color-header">
	 				<p class="label"><font color="#000000">Categoria selecionada</font></p>
	 			</td>
			</tr>
			<tr>
				<td align="center" class="label">
				<font color="#000000">
				<%
	 				if(acompCategoria.getCategoria()!=null){ 
	 					out.println(acompCategoria.getCategoria().getCodigoCategoria());
	 				}
 				%>
 				</font>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	
 	<table border="0" cellspacing="25" cellpadding="2" align="center" >
 	<tr>
 		<td align="center"> 			
 		</td>
 	</tr>
 	<%
 		if(operacao.equals("ExibirAnuncio")){
 	
		    listaImagem = (ArrayList) request.getAttribute("listaImagem");
 			
		 	if(listaImagem!=null){
		 		
		 			 		
		 		out.println("<tr><td align=\"center\"><p class=\"label\" align=\"center\"><b>Fotos: "+listaImagem.size()+"</b></p></td></tr>");	
		 		for(int i=0; i<listaImagem.size(); i++){		 			
		 			Imagem imagem = new Imagem();
		 			imagem = (Imagem) listaImagem.get(i);	
		 					 			
			 		out.println("<tr align=\"center\">");
			 		
			 		if(imagem.getDestaque().equals("S")){
			 			if(listaImagem.size()==1){
			 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir(this);\"></a><br><span class=\"label\">Destaque</span></td>");
					 		out.println("<td><a href=\"/publicadoranuncio/ControleImagem?operacao="+operacao+"&codigoImagem="+imagem.getCodigoImagem()+"&comando=excluir&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\"><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\"></a></td>");			 				
			 			}else{
			 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir(this);\"></a><br><span class=\"label\"><b>Destaque</b></span></td>");
			 			}			 							 		
		 			}else{
		 				out.println("<td><a href=\"#\" title=\"Clique para ampliar\"><img src=\"/publicadoranuncio/visao/imagem/"+imagem.getImgNome()+"\" width=\"60\" height=\"60\" style=\"border-width:1px;border-style:solid;border-color:blue;\" onclick=\"abrir(this);\"></a><br><span class=\"label\"><a href=\"/publicadoranuncio/ControleImagem?codigoImagem="+imagem.getCodigoImagem()+"&comando=destaque&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\">Mudar para destaque</a></span></td>");
				 		out.println("<td><a href=\"/publicadoranuncio/ControleImagem?operacao="+operacao+"&codigoImagem="+imagem.getCodigoImagem()+"&comando=excluir&arquivo="+imagem.getImgNome()+"&codigoAnuncio="+imagem.getAnuncio().getCodigoAnuncio()+"\" onclick=\"return confirmaExclusao();\"><img src=\"/publicadoranuncio/visao/imagem/delete.png\" alt=\"Excluir\"></a></td>");
		 			}			 		
			 		out.println("</tr>");
		 		}
		 	}else{
		 		
		 		out.println("<tr><td><p class=\"label\" align=\"center\"><b>Fotos: Nenhuma</b></p></td></tr>");
		 	}
 		}
 	%>		
	</table>	
	</td>          
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" >

<%if(operacao.equals("ExibirAnuncio")){ %>
	<br>
	<table class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; 
                      <b class="form-title"> Acompanhamento por Categoria</b></p></td>
                </tr>
                <tr> 
                  <td>&nbsp;</td>
                  <td width="274" colspan="2">&nbsp;</td>
                </tr>
                <tr> 
                  <td  width="77" align="right"><font class="label">Título</font></td>
                  <td align="left"> <div class="div" style="width:170pt;" ><%=acompCategoria.getAnuncio().getTitulo()%></div> 
                  </td>
                </tr>
                <tr> 
                  <td  valign="top" align="right"><font class="label">Descrição</font></td>
                  <td align="left"><div class="div" style="width:170pt;"><%=acompCategoria.getAnuncio().getDescricaoAnuncio() %></div></td>
                </tr>
                <tr> 
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Uf</font></td>
                  <td align="left"><div class="div" style="width:170pt;"><%=acompCategoria.getUf().getDescricaoUf() %></div> </td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Cidade</font></td>
                  <td align="left" ><div class="div" style="width:170pt;"><%=acompCategoria.getCidade().getDescricaoCidade() %></div> </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" ><div class="div" style="width:170pt;"><%=acompCategoria.getCategoria().getDescricaoCategoria() %></div></td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label"></font></td>                  
                </tr>
              </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					<td align="center">					
						<input type="submit" class="label" name="comando" value="Fazer uma pergunta ao anunciante" title="Clique aqui para fazer uma pergunta ao anunciante" onclick="javascript:document.jump2.submit();">					
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	

<form name="jump2" action="/publicadoranuncio/ControleDetalhes" method="post">
	<input type="hidden" name="codigoAnuncio" value=<%=acompCategoria.getAnuncio().getCodigoAnuncio() %>>
	<input type="hidden" name="codigoUsuario" value=<%=acompCategoria.getUsuario().getCodigoUsuario() %>>
	<input type="hidden" name="comando" value="Fazer uma pergunta ao anunciante">
</form>
<%}%>

<%if(!operacao.equals("ExibirAnuncio")){%>
<br>
<form name="FormAcompanha" action="/publicadoranuncio/ControleAcompanhaCategoria" method="post">
	<table class="color-border" cellspacing="0" align="center">
	<tr>
	<td>
	    <table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="3" class="color-title" align="left"><p class="label" >&nbsp; 
                     <b class="form-title"> Acompanhamento por Categoria</b></p>
                  </td>                      
                </tr>
                 <tr> 
                  <td valign="top" align="right"><font class="label"></font></td>
                  <td valign="top" align="left" >&nbsp; </td>                  
                </tr>
                <tr> 
                  <td align="right"><font class="label">Uf</font></td>
                  <td align="left">
                  		<select name="codigoUf" title="Estado" class="text" onchange="setOperacao('populaCombo');">
                      <option value="">===Estado===</option>
                      <%
	                    	ArrayList listaUf = (ArrayList) request.getAttribute("listaUf");                    	
	                    	
	                    	Uf uf = (Uf) request.getAttribute("uf");
	                    	
	                    	if(uf==null){                    		
	                    		uf = new Uf();
	                    		uf.setCodigoUf("");                     		
	                    	}
	                    	
	                    	
	                    	if(listaUf!=null){
	                    		for(int i=0; i < listaUf.size(); i++){	                    			
	                    			Uf uf2 = new Uf();                   			
	                    			uf2 = (Uf) listaUf.get(i);	                    			
			                    	if(uf2.getCodigoUf().equals(uf.getCodigoUf())){
			                    	  out.println("<option selected value=" + uf2.getCodigoUf() + ">"+ uf2.getDescricaoUf() +"</option>");
			                    	}else{
			                    		out.println("<option value=" + uf2.getCodigoUf() + ">" + uf2.getDescricaoUf()+ "</option>");	
			                    	}
	                    		}
	                    	}
	                    %>
                    </select> 
                  </td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Cidade</font></td>
                  <td align="left" >
                  	<select name="codigoCidade" title="Cidade" class="text" onchange="setCodigo(this.options[this.selectedIndex].value);" >
                      <option value="">===Cidade===</option>
                      <%
	                    	ArrayList listaCidade = (ArrayList) request.getAttribute("listaCidade");
                      
                      		Cidade cidade = (Cidade) request.getAttribute("cidade");
                      		if(cidade==null){
                      			cidade = new Cidade();
                      			cidade.setCodigoCidade(0);
                      		}
                      			
                      		
	               			if(listaCidade!=null){	               				
	               				for(int i=0; i < listaCidade.size(); i++){	               					
	               					Cidade c2 = new Cidade();	
	               					c2 = (Cidade) listaCidade.get(i);	               				
			                    	if(c2.getCodigoCidade()== cidade.getCodigoCidade()){
			                    	  out.println("<option selected value=" + c2.getCodigoCidade() + ">"+ c2.getDescricaoCidade()+" </option>");
			                    	}else{
			                    		out.println("<option value=" + c2.getCodigoCidade() + ">"+c2.getDescricaoCidade()+"</option>");	
			                    	}
	               				}
	               			}
	                    %>
                    </select>                  
                  </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Período</font></td>
                  <td valign="top" align="left" >
						<select name="periodo" class="label">
							<option value="">===selecione um período===</option>
							<option value="D" <%
												if(acompCategoria.getPeriodo().equals("D"))
													out.println("selected"); %>>Durante um dia</option>
							<option value="S" <%
												if(acompCategoria.getPeriodo().equals("S"))
													out.println("selected"); %>>Durante uma semana</option>
							<option value="M" <%
												if(acompCategoria.getPeriodo().equals("M"))
													out.println("selected"); %>>Durante um mês</option>
						</select>              		
                  </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">Categoria</font></td>
                  <td valign="top" align="left" >
                  		 
                  		<div class="div" style="width:170pt; background:#ddddd5;" id="descricaoCategoria"><%=acompCategoria.getCategoria().getDescricaoCategoria()  %></div>
                  </td>
                </tr>
                <tr> 
                  <td valign="top" align="right"><font class="label">         
                  </font></td>
                  <td valign="top" align="left" > 
                  		<iframe src="/publicadoranuncio/visao/Arvoren.jsp" width="230" height="125" style="border-style:solid; border-color:#666666; border-width:1px;"  frameborder="0"></iframe>                 
                  </td>                  
                </tr>
              </table>
	</td>
	</tr>		
	<tr> 		  	
		<td height="30" colspan="4" align="center">
			<table class="color-buttons" width="100%" height="100%">
				<tr>
					<td align="center">					
						<input type="submit" class="label" name="operacao"  title="Clique aqui para cadastrar uma categoria de anúncio para acompanhamento" onclick="return validaAcompanha(document.FormAcompanha);" value="<%=operacao %>">
						<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormAcompanha);">						
						<input type="hidden" size="3" name="codigoUsuario"  value=<%=acompCategoria.getUsuario().getCodigoUsuario() %>>												
						<input type="hidden" name="codigoCategoria" value=<%=acompCategoria.getCategoria().getCodigoCategoria()%>>
						<input type="hidden" name="nivelCategoria" value=<%=acompCategoria.getCategoria().getNivelCategoria()%>>
						<input type="hidden" name="codigoCategoriaAnt" value=<%=acompCategoria.getCategoria().getCodigoCategoria()%>>
						<input type="hidden" name="nivelCategoriaAnt" value=<%=acompCategoria.getCategoria().getNivelCategoria()%>>
						<input type="hidden" name="popula">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
<%}%>	
	<table border="0" cellpadding="0" cellspacing="0" align="center" width="90%">
	<tr valign="top" class="color-border">
	<td>
		<table border="0" cellspacing="1" cellpadding="2" width="100%">
	    <tr class="color-title"> 
	    	<td colspan="7" align="center"> <p class="label" >&nbsp; <b class="form-title"> Anúncios Publicados </b></p></td>
	    </tr>
	    <tr class="color-title"> 
	      <td> <font class="label">No.</font></td>
	      <td> <font class="label">Código</font></td>
	      <td> <font class="label">Data</font></td>
	      <td> <font class="label">T&iacute;tulo</font></td>
	      <td> <font class="label">Descri&ccedil;&atilde;o</font></td>
	      <td>&nbsp;</td>
	      <td>&nbsp;</td>	      
	    </tr>
	      <%	        	
	      		
	      		Locale locale = new Locale("pt","BR"); 	
	  			SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' 'HH':'mm':'ss",locale);		
				String descricaoCategoria ="";
	        	
	        	if(listaAcompanha!=null){
		        	for(int i=0; i < listaAcompanha.size(); i++){     		
		        				        		
		        		AcompanhaCategoria acomp = (AcompanhaCategoria) listaAcompanha.get(i);		        		
		        		
		        		if(!acomp.getCategoria().getDescricaoCategoria().equals(descricaoCategoria)){
			        	
		        			out.println("<tr class=\"color-header\" height=\"30\">"); 
					        out.println("<td colspan=\"5\"> <p class=\"label\"><b>"+acomp.getCategoria().getDescricaoCategoria() +"</b></p></td>");
					        out.println("<td><a href=\"/publicadoranuncio/ControleAcompanhaCategoria?operacao=editarCategoria&codigoCategoria="+acomp.getCategoria().getCodigoCategoria()+"&nivelCategoria="+acomp.getCategoria().getNivelCategoria()+"&codigoUsuario="+acomp.getUsuario().getCodigoUsuario()+"\">");
						    out.print("<img src=\"/publicadoranuncio/visao/imagem/IEDIT.png\" alt=\"Editar categoria em acompanhamento\"></a></td>");
						    out.println("<td><a href=\"/publicadoranuncio/ControleAcompanhaCategoria?operacao=excluirCategoria&codigoCategoria="+acomp.getCategoria().getCodigoCategoria()+"&nivelCategoria="+acomp.getCategoria().getNivelCategoria()+"&codigoUsuario="+acomp.getUsuario().getCodigoUsuario()+"\" onclick=\"return confirmaExclusao();\">");
						    out.print("<img src=\"/publicadoranuncio/visao/imagem/delete.png\"  alt=\"Excluir categoria em acompanhamento\"></a></td>");
					        out.println("</tr>");
					        descricaoCategoria = acomp.getCategoria().getDescricaoCategoria();
		        		}
		        		
			        	if(acomp.getAnuncio().getStatus().equals("L")){	
			        		//formatando a data e hora
			        		
			        		String dataInicio = formatador.format(acomp.getAnuncio().getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ acomp.getAnuncio().getCodigoAnuncio() +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ acomp.getAnuncio().getTitulo() +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ acomp.getAnuncio().getDescricaoAnuncio()+"</p></td>");
					        
					        				        	
						    out.println("<td colspan=\"2\"><a href=\"/publicadoranuncio/ControlePrincipal?comando=detalhes&codigoAnuncio="+acomp.getAnuncio().getCodigoAnuncio()+"&codigoUsuario="+acomp.getAnuncio().getUsuario().getCodigoUsuario()+"\">");
						    out.print("<img src=\"/publicadoranuncio/visao/imagem/preview.png\" alt=\"Visualizar anúncio\"></a></td>");
						   
					        					        
					        
			        	}else if(acomp.getAnuncio().getStatus().equals("P")){
							
			        		String dataInicio = formatador.format(acomp.getAnuncio().getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ acomp.getAnuncio().getCodigoAnuncio() +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\"></p></td>");
					        out.println("<td> <p class=\"label\"><font color=\"blue\">Aguardando liberação</font></p></td>");					        
					        				        	
						    out.println("<td colspan=\"2\"></td>");
			        	}else{
					
			        		String dataInicio = formatador.format(acomp.getAnuncio().getDataInicio()); 
			        		
					        out.println("<tr class=\"color-row\">"); 
					        out.println("<td height=\"20\" <p class=\"label\">"+ (i+1) +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ acomp.getAnuncio().getCodigoAnuncio() +"</p></td>");
					        out.println("<td> <p class=\"label\">"+ dataInicio +"</p></td>");
					        out.println("<td> <p class=\"label\"></p></td>");
					        out.println("<td> <p class=\"label\"><font color=\"red\">Este anúncio foi bloqueado e está aguardando regularização</font></p></td>");					        
					        				        	
						    out.println("<td colspan=\"2\"></td>");
			        	}
			        }
	        	}
	        %>
	        
	     <tr class="color-buttons"> 
	       <td height="30" colspan="7">&nbsp; </td>
	     </tr>
	     </table>
		</td>
	</tr>
	</table>    
	<td valign="top" align="center" background="/publicadoranuncio/visao/imagem/fundo.png">
	  	<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
	</td>
</tr>
   <tr class="color-buttons"> 
      <td height="30" colspan="3">&nbsp; </td>
 </tr>
</table>


<center>
	<%@ include file="/visao/rodape.htm" %>
</center>


<script>
	
	
	
	
	//setando código da cidade em campo hidden 
	function setCodigo(codigo){
		document.FormAcompanha.codigoCidade.value = codigo;			
	}
	
	//setando operação para combo
	function setOperacao(op){				
		document.FormAcompanha.popula.value = op;					
		document.FormAcompanha.submit();
	}
	
	
	//se usuário clicou no botão anterior do browser
	if(history.length > 0) 
		{ history.go(+1); }
	
	<% 
		String msg = request.getParameter("msg");
	
		if(msg!=null){
			if(msg.equals("Esta categoria já encontra-se cadastrada para companhamento!"))
				out.println("alert(\'"+msg+"\');");
			
			if(msg.equals("Categoria cadastrada com sucesso!"))
				out.println("alert(\'"+msg+"\');");
			
			if(msg.equals("Categoria alterada com sucesso!"))
				out.println("alert(\'"+msg+"\');");
			
		}	
	%>	
	
	function validaAcompanha(f){
			 
			if(f.periodo.value==""){
				alert("Selecione um período!");
				f.periodo.focus();
				return false;
			}else
			  if(f.codigoCategoria.value==0){
				alert("Escolha uma categoria!");				
				return false;
			  }
			
	}
		
	
</script>
</body>
</html>







