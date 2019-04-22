<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Renovação de Anúncio</title>
<link rel="stylesheet" href="anuncio.css" type="text/css">
<script src="funcoes.js"></script>

</head>
<body class="color-row" >

<table width="100%">
<tr align="left">
<td>
	<jsp:include page="cabecalho.jsp"></jsp:include>
</td>
</tr>
<tr>
    <td>
    	<br>		
		<table align="center" width="50%" class="buttn" cellspacing="0">
        <tr> 
          <td colspan="3" class="color-border"><span class="form-title"><strong>Renova&ccedil;&atilde;o 
            de An&uacute;ncio</strong></span></td>
          <td class="FundoBranco">&nbsp;</td>
          <td class="FundoBranco">&nbsp;</td>
        </tr>
        <tr> 
          <td class="color-title">No. ordem</td>
          <td class="color-title">Titulo do anúncio</td>
          <td class="color-title">Descrição</td>
          <td width="1" class="FundoBranco">&nbsp;</td>
          <td width="1" class="FundoBranco">&nbsp;</td>
        </tr>
        <tr> 
          <td class="color-row"><span >xxxxxxx</span></td>
          <td class="color-row"><span >xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</span></td>
          <td class="color-row"><span >xxxxxxx</span></td>
          <td align="right" class="FundoBranco"><a href=""><img src="imagem/validar.png" alt="Renovar" border="0"></a></td>
          <td align="center" class="FundoBranco"><a href=""><img src="imagem/lixeira.png" alt="Excluir" border="0"></a></td>
        </tr>
        <tr> 
          <td class="color-rowalert"><span>xxxxxxx</span></td>
          <td class="color-rowalert"><span>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</span></td>
          <td class="color-rowalert"><span>xxxxxxx</span></td>
          <td align="right" class="FundoBranco"><a href=""><img src="imagem/validar.png" alt="Renovar" border="0"></a></td>
          <td align="center" class="FundoBranco"><a href=""><img src="imagem/lixeira.png" alt="Excluir" border="0"></a></td>
        </tr>
      </table>			      
		
    </td>
</tr>
<tr>
<td height="200">
	<jsp:include page="rodape.htm"></jsp:include>
</td>
</tr>
</table>
</body>
</html>
