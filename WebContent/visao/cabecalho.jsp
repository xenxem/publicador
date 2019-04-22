<%@ page import="modelo.Usuario" %> 
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Locale"%>

<script>
function moveRelogio(){
    momentoAtual = new Date()
    hora = momentoAtual.getHours()
    minuto = momentoAtual.getMinutes()
    segundo = momentoAtual.getSeconds()

    horaImprimivel = hora + " : " + minuto + " : " + segundo

	document.getElementById("hra").innerHTML = horaImprimivel
	
    setTimeout("moveRelogio()",1000)
}
</script> 
  


<%  
	Usuario u = (Usuario) session.getAttribute("usuario");	

	
	Calendar calendario = Calendar.getInstance();
	String saudacao = "";
	
	if(calendario.getTime().getHours()>=18){
		saudacao = "Boa noite";
	}else if(calendario.getTime().getHours()>=13){
		saudacao = "Boa tarde";
	}else if(calendario.getTime().getHours()>=0){
		saudacao = "Bom dia";
	}
	
		
	
%>				 
		

<table align="left"  cellspacing="1" width="100%" class="labem" > 
<tr>
<td  height="0" >
	<table align="left"  cellspacing="0" cellpadding="10" > 
	<tr>							
	  <td></td>	
	</tr>	
	</table>
</td>
</tr>	
<tr>
	<td class="color-border">
		<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
        <tr>  
          <td   background="/publicadoranuncio/visao/imagem/fundometal.png">&nbsp;</td>
          <td align="left"   background="/publicadoranuncio/visao/imagem/fundometal.png">&nbsp;</td>
          <td align="center"   background="/publicadoranuncio/visao/imagem/fundometal.png">&nbsp;</td>
          <td align="center"   background="/publicadoranuncio/visao/imagem/fundometal.png">&nbsp;</td>
          <td align="center"  background="/publicadoranuncio/visao/imagem/fundometal.png">&nbsp;</td>
          <td    background="/publicadoranuncio/visao/imagem/fundometal.png" align="center">&nbsp;</td>
          <td height="30" align="center"  background="/publicadoranuncio/visao/imagem/fundometal.png" class="text_bold">&nbsp;</td>
        </tr>
        
      </table>	
	</td>
</tr>
<tr> 
	<td colspan="2" align="right" class="color-title" background="/publicadoranuncio/visao/imagem/imagem_fundo_azul.jpg">
		<table align="right" cellpadding="10">		  
		<tr> 
		  <td id="hra" class="label">
		  	&nbsp; 		
		  </td>
		  <td class="label">
		  	<%=saudacao %> 
		  	<% 	
		  		if(u!=null){
		  			out.println(", <b>"+u.getApelido().toUpperCase()+"</b>");
		  		}
		  	%>		  	
		  </td>	  			
          <td>
          	<a href="#">
          		<font color="white">
          			Política do site
          		</font>
          	</a> 
          </td>                  
          <td align="center"  > 
            <a href="/publicadoranuncio/visao/index.jsp">
            	<font color="white">
            		Página Inicial
            	</font>
            </a> 
          </td>
          <td align="center" >
	         <a href="/publicadoranuncio/ControlePrincipal?comando=cadastroUsuario">
	         	<font color="white">
	         	<%
	         		
	         	
	         		if(u==null){
	         			out.println("Cadastre-se");
	         		}else{
	         			out.println("Alterar Cadastro");
	         		}
	         	%>	          		
	          	</font>
	         </a> 
          </td>
          <td    align="center"> 
             <a href="#">
             	<font color="white">
             		Ajuda
             	</font>
             </a>
          </td>
          <td height="30" align="center" class="text_bold"> 
            <%
			   if(u!=null){%>
            <%@ include file="/visao/TimeOut.jsp" %>
            <%}%>
          </td>
        </tr>		
		</table>	
	</td>
</tr>
</table>
<script>
	moveRelogio();
</script>



