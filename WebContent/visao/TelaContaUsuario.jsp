<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Usuario" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conta Usuário</title>
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
			
	if(u==null){
		u = new Usuario();
		u.setCodigoUsuario(0);
		u.setNome("");
		u.setApelido("");
		u.setSenha("");
		u.setEmail("");				
	}					
%>


<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center" class="color-border">
  <tr  class="color-title"> 
    <td colspan="3"> <p class="label">&nbsp; <b class="form-title"> CRMaster Classificados 
        On-Line </b></p></td>
  </tr>
  <tr class="color-header"> 
    <td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b></b></font></td>
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> 
      <font class="label"><b>Cadastro do Usuário</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
    <br>
    <form name="FormUsuario" action="/publicadoranuncio/ControleUsuario" method="post" onsubmit="return validaFormUsuario(this);">
        <table class="color-border" cellspacing="0" align="center">
          <tr> 
            <td> 
            	<table  border="0" align="center" cellspacing="1"  class="color-header">
                <tr> 
                  <td  colspan="4" class="color-title"><p class="label" >&nbsp; 
                      <b class="form-title"> Dados da Conta</b></p></td>
                </tr>
                <tr> 
                  <td width="50" align="right"  valign="top">&nbsp;</td>
                  <td colspan="2" align="left">&nbsp;</td>
                  <td width="23" align="left">&nbsp;</td>
                </tr>
                <tr> 
                  <td colspan="4"></td>
                </tr>
                <tr> 
                  <td  align="right"><font class="label">Nome</font></td>
                  <td colspan="2" align="left" ><input type="text" name="nome" title="Nome" class="caixa-texto" size="35" maxlength="30" value="<%=u.getNome()%>" > 
                  </td>
                  <td align="left" >&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Apelido</font></td>
                  <td colspan="2" align="left"><input type="text" name="apelido" title="Apelido" class="caixa-texto" size="25" maxlength="20" value="<%=u.getApelido()%>"></td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">E-mail</font></td>
                  <td colspan="2" align="left"> <input type="text"  class="caixa-texto" title="E-mail" name="email" <%if(u.getCodigoUsuario()!=0)out.println("readonly style=\"background:#ddddd5;\""); %>  size="35" value="<%=u.getEmail()%>" > 
                  </td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Senha</font></td>
                  <td colspan="2" align="left"><input type="password" class="caixa-texto" name="senha" title="Senha" size="8" maxlength="6" value="<%=u.getSenha()%>"> <span class="label">(6 dígitos)</span></td>
                  <td align="left">&nbsp;</td>
                </tr>
                <tr> 
                  <td align="right"><font class="label">Confirmar senha</font></td>
                  <td colspan="2" align="left"><input type="password" class="caixa-texto" name="senha2" title="Senha" size="8" maxlength="6" value="<%=u.getSenha()%>"> <span class="label">(6 dígitos)</span></td>
                  <td align="left">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td height="30" colspan="4" align="center"> 
            <table class="color-buttons" width="100%" height="100%">
                <tr> 
                  <td align="center">
                  	<%
                  		String operacao= (String) request.getAttribute("operacao");
                  		
                  		if(operacao==null)
                  			operacao ="Cadastro";
                  		
                  			
                  		if(u.getCodigoUsuario()!=0){                  			
                  			                  			
                  			out.println("<input type=\"submit\" class=\"label\" name=\"comando\" value=\" Alterar \">");
                  		}else{
                  			out.println("<input type=\"submit\" class=\"label\" name=\"comando\" value=\"Cadastrar\">");		
                  		}
                  		
                  	%>
                  	
                  	<input type="hidden" name="codigoUsuario" value=<%=u.getCodigoUsuario() %>>                             
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
     		
	     	out.print("<table width=\"85%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
			out.print("<tr valign=\"top\" class=\"color-border\" >");
			out.print("<td>");					
			out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
			out.println("<tr class=\"color-header\">");
			if(msg.equals("sucesso")){
				if(operacao.equals("Alteracao")){

					out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/sucesso.bmp\" align=\"middle\"></td><td align=\"center\"><span class=\"label\">Alteração realizada com sucesso!</span></td>");
					
					//para limpar formulário após alteração
					//out.println("<script>");
					//out.println("document.FormUsuario.nome.value=\"\"");
					//out.println("document.FormUsuario.apelido.value=\"\"");
					//out.println("document.FormUsuario.email.value=\"\"");
					//out.println("document.FormUsuario.senha.value=\"\"");
					//out.println("document.FormUsuario.codigoUsuario.value=\"0\"");
					//out.println("</script>");
					
				}else{
					out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/sucesso.bmp\" align=\"middle\" ></td><td align=\"center\"><span class=\"label\">Cadastro realizado com sucesso!</span></td>");
				}
			}else{
				out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" align=\"middle\" ></td><td align=\"center\"><span class=\"label\">"+msg+"</span></td>");
			}
			out.println("</tr>");
			out.println("</table>");					
			out.print("</td>");
			out.print("</tr>");
			out.print("</table><br>");
     	}
     	
     	
      %>
    </td>
	  
    <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png"> 
      <% if(u!=null){
    	 
    	  if(u.getCodigoUsuario()!=0){%>    	  
      	<%@ include file="/visao/TelaMenuPrincipal.jsp" %>
      <%	}
      	  }      
      %>
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
	//validando campos obrigatórios form conta usuário	
	
	function validaFormUsuario(f){		
		with(f){
			for(i=0; i < elements.length; i++){
				if(elements[i].type=="text" && elements[i].name!="nome"){							
					if(elements[i].value==""){					
						alert("Preencha o campo "+ elements[i].title);
						elements[i].focus();					
						return false;
					}					
					if(elements[i].name=="email"){
						elements[i].focus();
						elements[i].select();												
						
						//validando e-mail, se for falso interrompe 
						if(ValidaEmail(elements[i].value)==false)
							return ValidaEmail(elements[i].value);							
					}					
				}else if(elements[i].type=="password"){
					if(elements[i].value==""){
						alert("Preencha o campo "+ elements[i].title);
						elements[i].focus();
						return false;
					}else if(elements[i].value.length < 6){
						alert("Senha deve ser de 6 dígitos");
						elements[i].focus();
						elements[i].select();
						return false;
					}else if(elements[i].name=="senha2"){
						if(elements[(i-1)].value != elements[i].value){
							alert("As senhas são diferentes");
							elements[i].focus();
							elements[i].select();
							return false;
						}
					}
				}				
			}  
		}
	  return true;
	}	
</script>

</body>
</html>