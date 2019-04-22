<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório de Pagamentos</title>
<link rel="stylesheet" href="/publicadoranuncio/visao/anuncio.css" type="text/css">
<script src="/publicadoranuncio/visao/funcoes.js"></script>

</head>
<body>

<%
	String inicio = (String)request.getAttribute("inicio");
	String fim = (String)request.getAttribute("fim");
	String data = (String)request.getAttribute("data");
	String idDeposito = (String)request.getAttribute("idDeposito");
	String valor = (String)request.getAttribute("valor");
	
	if (inicio == null){
		inicio = new String();
		inicio = "";
		fim = new String();
		fim = "";
	}
	
	if (data == null){
		data = new String();
		data = "";
	}
	
	if (idDeposito == null){
		idDeposito = new String();
		idDeposito = "";
	}
	
	if (valor == null){
		valor = new String();
		valor = "";
	}
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
	<td width="60%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Opções</b></font></td>
	<td width="20%" align="center" background="/publicadoranuncio/visao/imagem/fundometal.png"> <font class="label"><b>Funções</b></font> </td>
  </tr>
  <tr bgcolor="ffffff" class="color-row"> 
    <td align="center" valign="top" class="label" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
    <td valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
<br>    
<form name="FormRelatorioPagamento" action="/publicadoranuncio/ControleRelatorioPagamento" method="get"  onsubmit="return validaPeriodo(this);">
	<table border="0" cellspacing="0" class="color-border" align="center">
		<tr>
			<td>		
				<table width="100%" height="100%" border="0" align="center" cellspacing="1" class="color-header">				
					  <tr class="color-title">					
                  <td  colspan="4" >
                  		<span class="label"><b>Período</b></span></td>
					  </tr>
					  							 
							 <tr> 
						     	<td align="right"><p class="label">Início</p></td>
								<td align="left"><input class="caixa-texto" type="text" title="Inicio" name="inicio" maxlength="10" size="8" onkeypress="mascaraData(this);" value=<%=inicio %>></td>
								<td></td>
							 </tr>
							 
							 <tr>							
								<td align="right"><p class="label">Fim</p></td>
								<td align="left"><input class="caixa-texto" type="text" title="Fim" name="fim" maxlength="10" size="8" onkeypress="mascaraData(this);" value=<%=fim %>></td>
								<td></td>
					  		 </tr>			    	 
					  			
				
			
				  <tr class="color-title">					
                  	<td  colspan="4" >
                  		<span class="label"><b>Ordenar Por</b></span>
                  	</td>
				  </tr>
					 
						<tr>
							 <td align="right"><p class="label">Data</p></td>
							 <td align="left"><input class="caixa-texto" type="radio" name="ordenacao" value="data" <%=data %>></td>
							 <td></td>
						</tr>	 
                             
                        <tr>     
                             <td align="right"><p class="label">Id. Depósito</p></td>
                             <td align="left"><input class="caixa-texto" type="radio" name="ordenacao" value="idDeposito" <%=idDeposito %>></td>
                             <td></td>
                        </tr>
                             
                        <tr>
                             <td align="right"><p class="label">Valor</p></td>
                             <td align="left"><input class="caixa-texto" type="radio" name="ordenacao" value="valor" <%=valor %>></td>
                             <td width="30"></td> 
                        </tr>     							 				    	 
					    	 										
				</table>
					
		<tr>
			<td>
				<table border="0" cellspacing="0" width="100%" height="100%" align="center" class="color-buttons">
					<tr> 
						<td height="30" colspan="3" align="center">
							<input type="submit" name="comando" class="label" value="   Gerar   "> 
						  	<input type="button" name="limpar" class="label" value="   Limpar   " onclick="limpaForm(document.FormRelatorioPagamento);">
						</td>
					</tr>				
				</table>
			</td>		
		</tr>
	</table>		
</form>

<% 	String tamanho = (String) request.getAttribute("tamanho");

    if (tamanho != null){
    	out.print("<table width=\"85%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
    	out.print("<tr valign=\"top\" class=\"color-border\" >");
		out.print("<td>");					
		out.println("<table border=\"0\" cellspacing=\"1\" cellpadding=\"0\" width=\"100%\">");
		out.println("<tr class=\"color-header\">");
		out.println("<td align=\"center\" width=\"1\"><img src=\"/publicadoranuncio/visao/imagem/exclamacao.png\" align=\"middle\" width=\"40\" height=\"40\"></td><td align=\"center\"><span class=\"label\">Não foi encontrado registro !</span></td>");
		out.println("</tr>");
		out.println("</table>");					
		out.print("</td>");
		out.print("</tr>");
		out.print("</table><br>");
    }	

%>

 <td align="center" valign="top" background="/publicadoranuncio/visao/imagem/fundo.png" class="label"> 
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

    //Preenche a data com '/' 
	function mascaraData(campoData){
	
		var data = campoData.value;
		var ok = false;
		
		if (data.length == 2){
			data = data + '/';
			ok = true;			
		}else if (data.length == 5){
			data = data + '/';
			ok = true;		
		}
		
		if (ok){
			if (campoData.name == "inicio")
					document.forms[0].inicio.value = data;
				else
					document.forms[0].fim.value = data;
		}		
		
	}
	
	function validaPeriodo(f){

		var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/;
		var aRet = true;
			
		if (f["inicio"].value.length != 0 || f["fim"].value.length != 0){
		
			if (f["inicio"] && f["inicio"].value.match(expReg) && f["inicio"].value != ''){
			    var dia = f["inicio"].value.substring(0,2);
			    var mes = f["inicio"].value.substring(3,5);
			    var ano = f["inicio"].value.substring(6,10);
			    
			    if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){ 
			    	alert("Data inicial inválida");
			      	aRet = false;
				}else 
				if ((ano % 4) != 0 && mes == 2 && dia > 28){
	      			alert("Data inicial inválida"); 
	        		aRet = false;
	        	}else 
	        	if ((ano % 4) == 0 && mes == 2 && dia > 29){
	        		alert("Data inicial inválida");
	          		aRet = false;
	          	}else{
	          		var now = new Date();
	          		if (ano > now.getFullYear()){
	          			alert("Data inicial maior que a data atual");
	          			aRet = false;
	          		}else if (ano == now.getFullYear()){
	          			if (mes > (now.getMonth() + 1)){
	          				alert("Data inicial maior que a data atual");
	          				aRet = false;
	          			}else if (mes == (now.getMonth() + 1)){
	          				if (dia > now.getDate()){
	          					alert("Data inicial maior que a data atual");
	          					aRet = false;
	          				} 
	          			}
	          		}
	          	}	
	  		}else{ 
	    		alert("Data inicial inválida");
	    		aRet = false;
	    	}
	    		
	    	if (aRet == true){	
	    		
	    		if (f["fim"].value.length == 0){
					alert("Preencha a data final");
		    		aRet = false;
				}else if (f["fim"] && f["fim"].value.match(expReg) && f["fim"].value != ''){
						    var dia = f["fim"].value.substring(0,2);
						    var mes = f["fim"].value.substring(3,5);
						    var ano = f["fim"].value.substring(6,10);
						    
						    if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){ 
						    	alert("Data final inválida");
						      	aRet = false;
							}else 
							if ((ano % 4) != 0 && mes == 2 && dia > 28){
				      			alert("Data final inválida"); 
				        		aRet = false;
				        	}else 
				        	if ((ano % 4) == 0 && mes == 2 && dia > 29){
				        		alert("Data final inválida");
				          		aRet = false;
			          		}else{
				          		var now = new Date();
				          		if (ano > now.getFullYear()){
				          			alert("Data final maior que a data atual");
				          			aRet = false;
				          		}else if (ano == now.getFullYear()){
				          			if (mes > (now.getMonth() + 1)){
				          				alert("Data final maior que a data atual");
				          				aRet = false;
				          			}else if (mes == (now.getMonth() + 1)){
				          				if (dia > now.getDate()){
				          					alert("Data final maior que a data atual");
				          					aRet = false;
				          				} 
				          			}
				          		}
				          	}		
				  	  }else{ 
				    	 alert("Data final inválida");
				    	 aRet = false;
				      }
		    	
	    	}
	    	
	    	if (aRet == true){
	    		if (f["fim"].value.substring(6,10) < f["inicio"].value.substring(6,10)){
	    			alert("Data final menor que data inicial");
	    			aRet = false;
	    			 
	    		}else if (f["fim"].value.substring(6,10) == f["inicio"].value.substring(6,10)){
	    			
	    			if (f["fim"].value.substring(3,5) < f["inicio"].value.substring(3,5)){
	    				alert("Data final menor que data inicial");
	    				aRet = false;
	    					
	    			}else if (f["fim"].value.substring(3,5) == f["inicio"].value.substring(3,5)){
	    				
	    				if (f["fim"].value.substring(0,2) < f["inicio"].value.substring(0,2)){
	    					alert("Data final menor que data inicial");
	    					aRet = false;
	    				}		
	    			}	
	    		}  		
	    	}	
	    	
    	}else
    		aRet = true;
    		
    	return aRet;
		
	}
</script>
 
</body>
</html>