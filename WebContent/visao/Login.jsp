
<%@page import="modelo.Anuncio"%>
<br>
<form name="FormLogin" action="/publicadoranuncio/ControlePrincipal" method="post" >
<table  align="center" cellspacing="1" border="0" cellpadding="0" class="color-border" >
	<tr >
		<td>
		<table width="100%" height="100%" align="center"  cellspacing="0" cellpadding="0" class="color-header">
          <tr > 
            <td align="center" class="color-title" ><strong>Conta</strong></td>
          </tr>
          <tr> 
            <td align="center">e-mail ou apelido</td>
          </tr>
          <tr> 
            <td align="center"><input type="text" class="caixa-texto" name="email" title="e-mail"></td>
          </tr>
          <tr> 
            <td align="center">senha</td>
          </tr>
          <tr> 
            <td align="center"><input type="password" class="caixa-texto" name="senha" title="e-mail"></td>
          </tr>
          <tr> 
            <td align="center"> 
           
                  <table width="100%" height="100%" align="center"  cellspacing="1" cellpadding="0" class="color-buttons">
                      <tr> 
                        <td align="center"> <input type="submit" class="label" name="comando" value="Entrar"> 
                          <% 
								modelo.Cidade c3 = (modelo.Cidade) request.getAttribute("cidade");
								modelo.Uf uf3 = (modelo.Uf) request.getAttribute("uf");
													
								if(c3==null){
										c3 = new modelo.Cidade();
										c3.setCodigoCidade(0);
								}
													
								if(uf3==null){
										uf3 = new modelo.Uf();
										uf3.setCodigoUf("");
								}
												
										
						    %>
                          <input type="hidden" name="codigoCidade"> 
                          <input type="hidden" name="codigoUf" value=<%=uf3.getCodigoUf()%>>
                          <%
                          	String telaAtual = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1,request.getRequestURI().length());
                          %>
                          <input type="hidden" name="telaAtual" value="<%=telaAtual%>">
                                           
                          
                          <%                        	
	                          Anuncio a2 = (Anuncio) request.getAttribute("anuncio");
	                          
                          	  if(a2!=null){
		                       	out.println("<input type=\"hidden\" name=\"codigoAnuncio\" value="+a2.getCodigoAnuncio()+">");
		                       	out.println("<input type=\"hidden\" name=\"comando2\" value=\"detalhes\">");
		                        	
		                       	if(a2.getUsuario()!=null)
		                      		out.println("<input type=\"hidden\" name=\"codigoUsuario\" value="+a2.getUsuario().getCodigoUsuario()+">");
                          	  }
                           %>
                        </td>
                      </tr>
            	</table>
          
            </td>
          </tr>
        </table>
		</td>
	</tr>	
</table>

</form>

<%
String msg = request.getParameter("msg");

if(msg!=null){
	
	//não cadastrado
	if(msg.equals("naoCadastrado")){
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td class=\"color-title\" align=\"center\"><span class=\"label\"><b>Aviso</b></span></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class=\"label\" align=\"justify\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" width=\"20\" height=\"18\" align=\"bottom\">");					
		out.println("Nome da conta ou senha inválida.");
		out.println("Para se cadastrar, <a href=\"/publicadoranuncio/ControlePrincipal?comando=cadastroUsuario\">Clique aqui</a>");						
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
	
	//não habilitado
	}else if(msg.equals("naoHabilitado")){
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td class=\"color-title\" align=\"center\"><span class=\"label\"><b>Aviso</b></span></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class=\"label\" align=\"justify\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" width=\"20\" height=\"18\" align=\"bottom\">");					
		out.println("Sua conta precisa ser habilitada.");
		out.println("Verifique a mensagem recebida no e-mail informado para cadastro neste site.");
		out.println("Ao encontrá-la, clique no link para validação.");
		out.println("<a href=\"#\">Contato</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
	}
}



%>



