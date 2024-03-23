
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.ArrayList" %>   
<%@page import="modelo.Categoria"%>
<%@page import="modelo.Usuario"%>



<html>
<head>
<link rel="stylesheet" type="text/css" href="jsTree.css">
</head>


<%
	Usuario u = (Usuario) session.getAttribute("usuario");

	ArrayList listaCategoria = (ArrayList) session.getAttribute("listaCategoria");
	ArrayList listaCategoria2 = (ArrayList) session.getAttribute("listaCategoria");			

	
	if(listaCategoria==null){
		out.print("<form name=\"jump\" action=\"/publicadoranuncio/ControleArvore\" method=\"post\">");
		out.print("<input type=\"hidden\" name=\"categoria\" value=\"sim\">");
		out.print("</form>");
		out.print("<script>document.jump.submit();</script>");
	}
		
	String parametroExtra = (String ) request.getAttribute("parametroExtra");
	
	if(parametroExtra==null){
		parametroExtra = "";
	}

		
	int i = 0;
	int nivel_ant = 0;
	int nivel_categoria_prox=0;
	String sts_publicado="";
	int nivelCategoria=0;
	int codigoCategoria=0;
		
	String operacao = (String) request.getAttribute("operacao");
		
	if(operacao==null){
		operacao="";
	}
		
		
	String TreeView = "var arrNodes =\n";
	
	if(listaCategoria!=null){
		
		
			
		if (operacao.equals("IC"))
			TreeView += "[\n ['', ['javascript:_foo()']";
			
		else
			TreeView += "[\n ['', ['javascript:_foo()']";
		
		if (listaCategoria.size() == 0)
			TreeView += "]";
	
		for(int j=0; j < listaCategoria.size(); j++)
		{
			Categoria c = (Categoria) listaCategoria.get(j);
			
			codigoCategoria = c.getCodigoCategoria();  
			nivelCategoria = c.getNivelCategoria();			
			String titulo = c.getDescricaoCategoria();
			int codigoCategoriaPai = c.getCodigoCategoriaPai();
			int nivelCategoriaPai = c.getNivelCategoriaPai();
			
			
			
			if ((i+1) < listaCategoria2.size())
			{	
				Categoria c2 = new Categoria();
				c2 = (Categoria) listaCategoria2.get((i+1)); 			
				nivel_categoria_prox =  c2.getNivelCategoria();
				
				
				
			}
			else
			{
				nivel_categoria_prox=0;
				
			}
			
	
			if (i==0){
				TreeView += ",[\n";
				
			}		
			
			
			TreeView += "['"+titulo+"', ['javascript:parent.window.setCodigos("+codigoCategoria+","+nivelCategoria+",\""+titulo+"\")',,'folder']";
			
			
			if (nivel_categoria_prox == nivelCategoria){
				TreeView += "],\n";
				
			}else if (nivel_categoria_prox > nivelCategoria){
				TreeView += ",\n[";
				
				
			}else if (nivel_categoria_prox < nivelCategoria)
			{
				
				
				int x = (nivelCategoria - nivel_categoria_prox);
				
				if (x > 1)
				{
						
					x = (x - 1) * 2;
					
					for(int z=0; z < x; z++)
						TreeView += "]\n";
					
				}
				
				TreeView += "]\n]\n]";
			
				
				
				if(nivel_categoria_prox != 0){
					TreeView += ",";
					
				}
			}
			
			i++;
		}
		
		TreeView += "]\n";
		
	}
			
%>


<script src="jsTree.js" type="text/javascript" language="JavaScript"></script>

<script language="JavaScript" type="text/javascript">
	
	var newNodeCount = 0;

	jst_context_menu = '';
	
	function _foo(){}
	
	
	<% 
		if(listaCategoria!=null) 
			out.print(TreeView);
		
		//matando a sessao		
		if(u==null){
			session.invalidate();
		}
	%>
	
	var jst_container = "document.getElementById('treeContainer')";
	var jst_image_folder_user = "imagem/";
	

	
</script>

<body onLoad="renderTree();" bgcolor="#ddddd5">
	
<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0" align="center" >
 <tr>
	<td>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="buttn">
		<tr>
			<td><div id="treeContainer"></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>
</body>
</html>
