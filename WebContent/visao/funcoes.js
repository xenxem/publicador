function abrir(imagem){			
	//alert(imagem.src.substring(imagem.src.lastIndexOf('/')+1,imagem.src.length));	
	image = new Image();
	image.src = imagem;
	image.width= image.width+9;
	image.height= image.height+81;
	janela = window.open("","j","width="+image.width+",height="+image.height);
	janela.document.write("<img alt='Clique na foto para fechar' onclick='javascript:window.close();' src="+imagem+">");
	janela.document.write("<br><a href='javascript:window.close();' title='Clique aqui para fechar'><font size=2><center>Fechar</center></font></a>");
	image = null;		
}






function validacao(f){
	with(f){
		for(i=0; length; i++){			
			 			
			if(elements[i].type=="text" 
					|| elements[i].type=="select-one" 
							||  elements[i].type=="textarea" 
									|| elements[i].type=="password"){
											
				if(elements[i].type=="text" &  elements[i].value==""){
										
					if(elements[i].name.indexOf('legenda')==-1){
							
						alert("Preencha o campo "+ elements[i].title);
						elements[i].focus();
						return false;
					}
													
				}else if(elements[i].type=="textarea" 
						&  elements[i].value==""){
						
						alert("Preencha o campo "+ elements[i].title);
						elements[i].focus();
						return false;							
													
				}else if(elements[i].type=="select-one"){						
						
						if(elements[i].selectedIndex==0){
							alert("Preencha o campo "+ elements[i].title);
							elements[i].focus();							
							return false;
						}
																
				}else if(elements[i].type=="password" &  elements[i].value==""){
						
						alert("Preencha o campo "+ elements[i].title);
						elements[i].focus();
						return false;
				}
				
					
			}
			//obrigano usuário a selecionar uma categoria ao cadastrar anúncio
			if(name=="FormAnuncio" && elements[i].name=="codigoCategoria"){				
					if(elements[i].value==0){
						alert("Escolha uma categoria.");
						return false;
					}					
			}else if(name=="FormAcompanha" && elements[i].name=="codigoCategoria"){				
					if(elements[i].value==0){
						alert("Escolha uma categoria.");
						return false;
					}					
			}
				
		}	
		
		
	}		
}


function limitaQtdeCaracter(campo){
	
		
	if(campo.name=="descricaoAnuncio"){
		if(campo.value.length==250){	
			alert('Quantidade máxima de caracteres permitidos = 250');
			campo.value = campo.value.substring(0,240);
			campo.focus();
		}
	}
	
	
	
	if(campo.name=="descricaoMensagem"){	
		if(campo.value.length==150){	
			alert('Quantidade máxima de caracteres permitidos = 150');	
			campo.value = campo.value.substring(0,140);
			campo.focus();
		}
	}
	
}

function verfonte(){
 if (event.button==2||event.button==3){
	alert('Você precisa fazer o login para utilizar o sistema!');
  }
	  
}

//document.onmousedown=verfonte

function EsqueciSenha(){
	win = window.open('/visao/EsqueciASenha.jsp','senha','resizable=yes,toolbar=no,status=yes,scrollbars=1,width=420,height=120,left=20,top=50');
	win.focus();
}

function limpaForm(f){

	if(f.name=="FormAcompanha"){
		document.getElementById("descricaoCategoria").innerHTML = "";
	}
	
	if(f.name=="FormAnuncio"){
	
			f.operacao.value = "limpaAnuncio";
			f.submit();
	}else{
		for(i=0; i < f.length; i++){				
				if(f.elements[i].type=="text" 
					||	f.elements[i].type=="textarea" 
								|| f.elements[i].type=="select-one" 
											||	f.elements[i].type=="hidden"
														|| f.elements[i].type=="checkbox"){			
					
					if(f.elements[i].type=="select-one"){				
					
						f.elements[i].options[0].selected = true;
						
					}else if (f.elements[i].type=="text"){
					
						f.elements[i].value = "";
						
					}else if( f.elements[i].type=="textarea"){
						
						f.elements[i].value = "";
						
					}else if(f.elements[i].type=="hidden" 
								&& f.elements[i].name!="codigoconta" 
											&& f.elements[i].name!="codigobanco"){
												
						if(f.elements[i].name=="codigoCategoriaAux"){
							f.elements[i].value=0;						
												
						}else if(f.elements[i].name=="nivelCategoriaAux"){
							f.elements[i].value =0;
						
						}else{
							f.elements[i].value ="";
						}
												
					}else if(f.elements[i].type=="checkbox"){
						f.elements[i].checked = false;
					}										
				}
		}
	}

}

function eNumero(c){
	if(c.value.length > 0){				
		regExp = /[0-9]/;
				
		resultado = regExp.exec(c.value);
				
		if(resultado==null){
			alert("Preencha o campo " + c.title + " com um número válido!");				
		}		
	}
}

function confirmaExclusao(){
	if(confirm('Deseja realmente excluir este registro?')==1){
		return true;
	}else{
		return false;
	}
}

function LogOut(){
	top.location.replace("/publicadoranuncio/visao/LogOut.jsp");
}

function ValidaEmail(mail){	
	er = new RegExp(/^[A-Za-z0-9_\-\.]+@[A-Za-z0-9_\-\.]{2,}\.[A-Za-z0-9]{2,}(\.[A-Za-z0-9])?/);
	
	r = er.exec(mail); 
	
	if(r==null){
		alert("E-mail informado inválido!\n"+mail);
		return false;
	}	
  return true;	
}
