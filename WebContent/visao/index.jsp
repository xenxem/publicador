<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ page import="modelo.Usuario" %>
<%@ page import="modelo.Cidade" %>
<%@ page import="modelo.Uf" %>
<%@ page import="modelo.Categoria" %>
<%@ page import="modelo.Anuncio" %>
<%@ page import="modelo.Imagem" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Locale"%>

<%! ArrayList listaImagem; %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página Principal</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>
<script>

	//código passados ao se clicar em gategorias
	function setCodigos(c,n,t){
		document.FormPesquisa.codigoCategoria.value = c;
		document.FormPesquisa.nivelCategoria.value = n;			
		document.getElementById("descricaoCategoria").innerHTML = t;		
	}
</script>

</head>
<body>

<%
Anuncio a = (Anuncio) request.getAttribute("anuncio");

	//evitando NullPointerException
	if(a==null){
	
		a = new Anuncio();
		
		a.setCodigoAnuncio(0);
		a.setTitulo("");
		a.setDescricaoAnuncio("");
		
		Categoria c = new Categoria();
		c.setCodigoCategoria(0);
		c.setNivelCategoria(0);
		a.setCategoria(c);
		
		Usuario u2 = new Usuario();
		u2.setCodigoUsuario(0);
		a.setUsuario(u2);
		
		Uf uf = new Uf();
		uf.setCodigoUf("");
		a.setUf(uf);
		
		Cidade c2 = new Cidade();
		c2.setCodigoCidade(0);
		a.setCidade(c2);
	
	}
	
%>

<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td height="35" colspan="3" cellpadding="0" cellspacing="0" align="left">
		<%@ include file="/visao/cabecalho.jsp" %>
	</td>
  </tr>  
</table>


<table border="0" cellspacing="1"  cellpadding="2" width="100%"  height="100%" align="center" class="color-border">
  <tr  class="color-title"> 
          <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados On-Line </b></p></td>
   </tr>
   <tr class="color-header">		 
    <td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Formulário de Pesquisa</b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Anúncios</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
   </tr>
     
   <tr bgcolor="ffffff" class="color-row">         
    
    <td  align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png">      
      <form name="FormPesquisa" action="/publicadoranuncio/ControlePrincipal" method="post">
      <table  align="center" cellspacing="1" border="0" cellpadding="0" class="color-border" >
	  <tr>
	  <td>
	    <table class="color-header">
	      <tr align="left"> 
            <td>&nbsp; </td>
            <td align="left" class="label">
            	Digite texto para pesquisa  
            </td>
            <td> <span class="label"></span> </td>
          </tr>	
          <tr align="left"> 
            <td>&nbsp; </td>
            <td align="left"> <input type="text" size="36" name="titulo" class="caixa-texto" value="<%=a.getTitulo()%>"> 
            </td>
            <td> </td>
          </tr>
          <tr> 
            <td align="left">&nbsp;</td>
            <td align="left"> 
            <% 
            	String criterio = (String) request.getAttribute("criterio");
            	
            	out.print("<select name=\"criterio\" class=\"label\" onchange=\"setCriterio(this.options[this.selectedIndex].index);\">");
					if(criterio!=null){
						
	            		if(criterio.equals("nenhum")){
	            			
	            	        out.println("<option value=\"nenhum\" selected>===Critério===</option>");
	 			            out.println("<option value=\"meio\">Qualquer parte da frase</option>");
	 			            out.println("<option value=\"inicio\">Início da frase</option>");
	 			            out.println("<option value=\"fim\">Fim da frase</option>");
	 			            
	            		}else if(criterio.equals("meio")){
	            			
	            	        out.println("<option value=\"nenhum\">===Critério===</option>");
	 			            out.println("<option value=\"meio\" selected>Qualquer parte da frase</option>");
	 			            out.println("<option value=\"inicio\">Início da frase</option>");
	 			            out.println("<option value=\"fim\">Fim da frase</option>");
	 			            
	            		}else if(criterio.equals("inicio")){
	            			
	            	        out.println("<option value=\"nenhum\">===Critério===</option>");
	 			            out.println("<option value=\"meio\">Qualquer parte da frase</option>");
	 			            out.println("<option value=\"inicio\" selected>Início da frase</option>");
	 			            out.println("<option value=\"fim\">Fim da frase</option>");
	 			            
	            		}else if(criterio.equals("fim")){
	            			
	            	        out.println("<option value=\"nenhum\">===Critério===</option>");
	 			            out.println("<option value=\"meio\">Qualquer parte da frase</option>");
	 			            out.println("<option value=\"inicio\">Início da frase</option>");
	 			            out.println("<option value=\"fim\" selected>Fim da frase</option>");
	            		}
					}else{						
			            out.println("<option value=\"nenhum\">===Critério===</option>");
			            out.println("<option value=\"meio\">Qualquer parte da frase</option>");
			            out.println("<option value=\"inicio\">Início da frase</option>");
			            out.println("<option value=\"fim\">Fim da frase</option>");			            						
					}
				out.print("</select>");
            		
            %>           	              	
            </td>
            <td></td>
          </tr>
          <tr> 
            <td align="left"> </td>
           
            <td> </td>
            <td> </td>
          </tr>
          
          <tr> 
            <td align="left">&nbsp;</td>
            <td align="left"> 
              <%
				Uf uf = (Uf) request.getAttribute("uf");											
			  %>
              <%if(uf==null){ %>
              <select name="codigoUf" onChange="FormPesquisa.submit();"  class="label">
                <option value="">===Estado===</option>
                <option value="AC" >Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="DF">Distrito Federal</option>
                <option value="MA">Maranhão</option>
                <option value="MG">Minas Gerais</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MT">Mato Grosso</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="PR">Paraná</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="SC">Santa Catarina</option>
                <option value="SE">Sergipe</option>
                <option value="SP">São Paulo</option>
                <option value="TO">Tocantins</option>
                <option value="AP">Amapá</option>
                <option value="CE">Ceará</option>
                <option value="ES">Espiríto Santo</option>
                <option value="GO">Goiás</option>
              </select> 
              <%}else { %>
              <select name="codigoUf" onChange="FormPesquisa.submit();"  class="label">
                <option value="">===Estado===</option>
                <%
					ArrayList listaUf = (ArrayList) request.getAttribute("listaUf");
									  
					if(listaUf != null){
							  			
						for(int i=0; i < listaUf.size(); i++){
							Uf uf2 = new Uf();								  			
							uf2 = (Uf) listaUf.get(i);
								  			
							if(uf2.getCodigoUf().equals(uf.getCodigoUf())){
								out.println("<option selected value="+uf2.getCodigoUf()+">"+uf2.getDescricaoUf()+"</option>");
							}else{
								out.println("<option value="+uf2.getCodigoUf()+">"+uf2.getDescricaoUf()+"</option>");								  				
							}
							  				
						}
					}										
										
				%>
              </select> 
              <%}%>
            </td>
            <td></td>
          </tr>
          <tr> 
            <td align="left">&nbsp;</td>
            <td align="left"> <select name="codigoCidade" onchange="setCodigo(this.options[this.selectedIndex].value);"  class="label">
                <option value="0">===Cidade===</option>
                <%
					ArrayList listaCidade = (ArrayList) request.getAttribute("listaCidade");
								  		
					Cidade c = (Cidade) request.getAttribute("cidade");
								  		
					if(c==null){
						c = new Cidade();
						c.setCodigoCidade(0);								  			
					}
								  		
								  		
					if(listaCidade != null){
						for(int i=0; i < listaCidade.size(); i++){
							Cidade c2 = new Cidade();								  			
							c2 = (Cidade) listaCidade.get(i);
							if(c.getCodigoCidade()== c2.getCodigoCidade()){
								out.println("<option selected value="+c2.getCodigoCidade()+">"+c2.getDescricaoCidade()+"</option>");
							}else{
								out.println("<option value="+c2.getCodigoCidade()+">"+c2.getDescricaoCidade()+"</option>");
							}
						}
					}
				 %>
              </select> </td>
            <td></td>
          </tr>
          
          <tr> 
          	<tr>
          		<td></td>
          		<td class="label" align="left">
					 Categoria         		
          		</td>
          		
          	</tr>
          	<tr>
          		<td>          			
          		</td>
          		<td align="left" class="label">
          			<div class="div" style="width:148pt; background:#ddddd5;" id="descricaoCategoria"></div>
          		</td>
          	</tr>
          	<tr>	
	            <td align="left">&nbsp;</td>
	            <td align="left"> <iframe  src="/publicadoranuncio/visao/Arvoren.jsp" width="200" style="border-style:solid; border-color:#666666; border-width:1px;"  frameborder="0"></iframe>	
	            </td>
	            <td></td>
          	</tr>
          	<tr>
	            <td align="left">&nbsp;</td>
	            <td align="left">&nbsp;</td>
	            <td></td>
          	</tr>
          	<tr> 
            	<td align="left">&nbsp;</td>
            	<td align="center"> 
            	<input type="submit" name="comando" class="label" value="Pesquisar" onclick="return verificaCampos(document.FormPesquisa);"> 
              	<input type="button" name="limpar" class="label" value="  Limpar  " onclick="limpaForm(document.FormPesquisa);"> 
            </td>
            <td ></td>
          </tr>
        </table> 
    </td>
    </tr>
    </table>           
        <input type="hidden" name="codigoCategoria">
        <input type="hidden" name="nivelCategoria">
        <input type="hidden" name="codigoCategoriaAux" value=<%=a.getCategoria().getCodigoCategoria()%>>
        <input type="hidden" name="nivelCategoriaAux" value=<%=a.getCategoria().getNivelCategoria()%>>     
	</form>
 
	</td>
          
    <td align="center" valign="middle" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" >    
	      <%	
	      
	      	ArrayList anuncioListaPesquisados = (ArrayList) request.getAttribute("anuncioListaPesquisados");
	      
	      	if(anuncioListaPesquisados!=null){
	      		
	      		//anúncio encontrados
		      	if(anuncioListaPesquisados.size()>=1){	 
		      		
			      		out.print("<table width=\"80%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
			      		out.print("<tr valign=\"top\" class=\"color-border\" >");
			      		out.print("<td>");
			      		
			      			      			
			      		out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
			      		out.println("<tr class=\"color-title\">");
			      		out.println("<td colspan=\"3\">");
			      		out.println("<font class=\"label\"><b>Anúncios Encontrados("+anuncioListaPesquisados.size()+")</b></font>");
			      		out.println("</td>");
			      		out.println("</tr>");      			
				      	out.println("<tr class=\"color-header\">"); 
			  		    out.println("<td> <font class=\"label\">Foto</font></td>");	      
			  		    out.println("<td> <font class=\"label\">UF</font></td>");
			  		  out.println("<td> <font class=\"label\">T&iacute;tulo</font></td>");
			  		    out.println("</tr>");
			      		
			      		String imgDestaque="";
			      		
			      		//se tem imagem verifica destaque
			      		for(int i=0; i < anuncioListaPesquisados.size(); i++){
			      			
			      			Anuncio a2 = (Anuncio) anuncioListaPesquisados.get(i);			
			      		
			      			for(int x=0; x < a2.getImagem().size(); x++){
			      				
			      				Imagem img = (Imagem) a2.getImagem().get(x);
			      				
			      				if(img.getDestaque().equals("S")){
			      					imgDestaque = img.getImgNome();
			      				}			      				
			      			}
			      			
							out.println("<tr class=\"color-row\">");			
							
							if(!imgDestaque.equals("")){
								out.println("<td width=\"1\"><img style=\"border-width:1px;border-style:solid;border-color:blue;\" src=\"/publicadoranuncio/visao/imagem/"+imgDestaque+"\"  width=\"70\" height=\"65\"></td>");
							}else{
								out.println("<td height=\"40\"><font><a href=\"/publicadoranuncio/ControlePrincipal?comando=detalhes&codigoAnuncio="+a2.getCodigoAnuncio()+"&codigoUsuario="+a2.getUsuario().getCodigoUsuario()+"\"></a></td>");
							}
							
							out.println("<td>"+a2.getUf().getCodigoUf()+"</td>");
							out.println("<td><a href=\"/publicadoranuncio/ControlePrincipal?comando=detalhes&codigoAnuncio="+a2.getCodigoAnuncio()+"&codigoUsuario="+a2.getUsuario().getCodigoUsuario()+"\" title=\"Exibir detalhes do anúncio\">"+a2.getTitulo()+"</a></td>");							
							out.println("</tr>");
							   	
							imgDestaque="";
			      		}
			      		
			      		
				      	out.println("<tr class=\"color-buttons\">"); 
				      	out.println("<td height=\"30\" colspan=\"3\">&nbsp;</td>");
				      	out.println("</tr>");
				      	out.println("</table>");	
			      		      		
			      		out.print("</td>");
			      		out.print("</tr>");
			      		out.print("</table>");		
			      		out.print("</td>");
				}else{
					
					//mensagem de anúncio não encontrados	
					out.print("<table width=\"85%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
		      		out.print("<tr valign=\"top\" class=\"color-border\" >");
		      		out.print("<td>");		      		
					
					out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
		      		out.println("<tr class=\"color-header\">");		      		
		      		out.println("<td ><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" align=\"middle\" ></td><td>Nenhum registro foi encontrado. Você deve refinar mais a sua pesquisa</td>");		    
			      	out.println("</tr>");
			      	out.println("</table>");	
		      	
					
					out.print("</td>");
		      		out.print("</tr>");
		      		out.print("</table>");		
		      		out.print("</td>");
					
				}
		      	
	      	}
	      		      		
	      %>
	      
	          
	     
	     <%--     
		 <table cellpadding="10" cellspacing="0" border="0" width="70%">
		  	<tr>
		   		<td valign="top" align="center"	style="background:url(/publicadoranuncio/visao/imagem/img2_fundo.jpg) no-repeat;">
		   			<img src="/publicadoranuncio/visao/imagem/vectra.bmp" width="85" height="65">
		   		</td>
		   		<td width="200">
		   			<a href="#"></a>
		  		</td>
		  	</tr>
		 </table>
	     
	     <br>
	     <table cellpadding="10" border="0" width="70%" cellspacing="0">	
	     	<tr>	 
	     		<td  width="200">
	     			<a href="#"></a>
	     		</td>
	     		<td valign="top" align="center"	style="background:url(/publicadoranuncio/visao/imagem/img2_fundo.jpg) no-repeat;">
	     			<img src="/publicadoranuncio/visao/imagem/cavalo_mangalarga.jpeg" width="85" height="65">
	     		</td>
	     	</tr>
	     </table>   
	        --%>   
	</td> 		     
          
    <td valign="top" align="center" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <p class='label'> 
        <%if(u==null){%>
        <%@ include file="/visao/Login.jsp" %>
        <%} else {%>
        <%@ include file="/visao/TelaMenuPrincipal.jsp" %>
        <%}%>      
	</td>
   </tr>   
   <tr class="color-buttons"> 
      <td height="30" colspan="3" align="center" valign="middle" >
       <%
	    	String paginaAtual = (String) request.getAttribute("paginaAtual");
       		String totalRegistros = (String) request.getAttribute("totalRegistros");
	      	
       		if(totalRegistros==null)
       			totalRegistros="3";
       		
       		       			
	      	if(paginaAtual==null)
	      		paginaAtual="0";     			      	
				      
	   %>	   
      <table align="center" border="0" cellpadding="0" cellspaging="0">
	      <tr>
	      <td width="120">&nbsp;</td>
	      <td align="center" width="150" valign="middle">
	      <form name="FormProximo" action="/publicadoranuncio/ControlePrincipal" method="post">
	      		 <input type="hidden" name="titulo" value="<%=a.getTitulo()%>">
	      		 <input type="hidden" name="codigoCidade" value=<%=a.getCidade().getCodigoCidade() %>>
	      		 <input type="hidden" name="codigoUf" value="<%=a.getUf().getCodigoUf() %>">	
	      		 <input type="hidden" name="criterio" value="<%=criterio%>">
	      		 <input type="hidden" name="codigoCategoria">
			     <input type="hidden" name="nivelCategoria">
			     <input type="hidden" name="codigoCategoriaAux" value=<%=a.getCategoria().getCodigoCategoria()%>>
			     <input type="hidden" name="nivelCategoriaAux" value=<%=a.getCategoria().getNivelCategoria()%>>
			     <input type="hidden" name="anterior" value=<%=paginaAtual %>>
			     <input type="hidden" name="proxima" value=<%=paginaAtual %>>
			     <input type="hidden" name="comando" value="Pesquisar">
			     <%			     
			     	if(Integer.parseInt(paginaAtual) >= 1)
			     		out.print("<input type=\"submit\" name=\"operacao\" class=\"label\" value=\"<<Anterior\">");			     	
			     %>
	      </form>
	      </td>
	      <td align="center" width="150" valign="middle">	    
	      <form name="FormAnterior" action="/publicadoranuncio/ControlePrincipal" method="post">
	      		<input type="hidden" name="titulo" value="<%=a.getTitulo()%>">
	      		 <input type="hidden" name="codigoCidade" value=<%=a.getCidade().getCodigoCidade() %>>
	      		 <input type="hidden" name="codigoUf" value="<%=a.getUf().getCodigoUf() %>">	
	      		 <input type="hidden" name="criterio" value="<%=criterio%>">
	      		 <input type="hidden" name="codigoCategoria">
			     <input type="hidden" name="nivelCategoria">
			     <input type="hidden" name="codigoCategoriaAux" value=<%=a.getCategoria().getCodigoCategoria()%>>
			     <input type="hidden" name="nivelCategoriaAux" value=<%=a.getCategoria().getNivelCategoria()%>>
			     <input type="hidden" name="anterior" value=<%=paginaAtual %>>
			     <input type="hidden" name="proxima" value=<%=paginaAtual %>>
			     <input type="hidden" name="comando" value="Pesquisar">
			     <%
			 		if(anuncioListaPesquisados!=null){					 			
			      		if(anuncioListaPesquisados.size() >= Integer.parseInt(totalRegistros))			      			
			     				out.print("<input type=\"submit\" name=\"operacao\" class=\"label\" value=\"Próximo>>\">");
			     	}
			     %>
	      </form>	      
	      </td>
	      </tr>
	      </table>           	      
      </td>
   </tr>
</table>
<center>
	<%@ include file="/visao/rodape.htm" %>
</center>





</body>
</html>
<script> 

	//código da cidade
	function setCodigo(codigo){
		document.FormLogin.codigoCidade.value = codigo;			
	}
	
	//caso algum dos elementos abaixo esteja preenchido retorna true	
	function verificaCampos(f){
	
		
		if(f.titulo.value.length!=0){ 
			return true;
		}
			
		if(f.codigoUf.options[f.codigoUf.selectedIndex].text!="===Estado==="){
			return true;
		}
		
		if(f.codigoCidade.options[f.codigoCidade.selectedIndex].text!="===Cidade==="){
			return true;
		}
		
		if(f.codigoCategoria.value!=""){
			return true;
		}
		
		if(f.codigoCategoriaAux.value!=0){
			return true;
		}
	
		alert('Escolha uma das opções para pesquisa.');		
		return false;
	}
</script>

