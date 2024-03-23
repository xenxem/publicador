package modelo;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


public class Mail {
	
	public static final int  HTML = 1;
	public static final int  SIMPLES = 2;
	
	/*
	public Mail(){

		SimpleEmail email = new SimpleEmail();

		try {
			//email.setDebug(true);
			//email.setHostName("smtpauth.rio.rj.gov.br");
			//email.setAuthentication("carlospereira","ANGELS");
			
			email.setHostName("smt.gmail.com");
			email.setAuthentication("carlos.paula","ANGEL2000");
			
			//email.setSSL(true);
			
			//pode ser qualquer um e-mail
			email.addTo("salzinharj@gmail.com");
			
			//aqui precisa ser o e-mail que voc� fara a autentica�o
			email.setFrom("carlos.paula@gmail.com"); 
			email.setSubject("CRMaster On-Line (Cadastro)");
			email.setMsg("<a href='http://www.rio.rj.gov.br/'>Prefeitura do Rio</a>");
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();			
		} 

	}*/	
	
	public Mail(String mail, int formato){		
		
		switch(formato){
			case 1:
			
				 /// Cria a mensagem
				
				 HtmlEmail emailHTML = new HtmlEmail();				 
				 emailHTML.setDebug(true);
				 emailHTML.setHostName("smtpauth.rio.rj.gov.br");			
				 emailHTML.setAuthentication("carlospereira@rio.rj.gov.br","ANGELS");
				 
				 try {
						 //email.addTo("mamelo.alo@gmail.com", "Marcelo Alo Mello");
						 emailHTML.addTo("carlos.paula@gmail.com","Carlos");		
						 emailHTML.setFrom("carlospereira@rio.rj.gov.br", "Carlos Pereira");
						 emailHTML.setSubject("Testando envio de e-mail");
					 
						 // incorpora a mensagem e obtem o cid (content ID)
						 URL url = new URL("file:///c:/temp/servidor.jpg");
						 String cid = emailHTML.embed(url, "Sorria voc� acabou de receber uma mensagem enviada por Carlos Atrav�s do Java");
						 
						 // seta a mensagem em  
						 emailHTML.setHtmlMsg("<html>The apache logo - <img src='" + cid + "': /></html>");
						 
						 // seta a mensagem correspondente sem  
						 emailHTML.setTextMsg("Seu e-mail n�o suporta HTML");
						 
						 // envia o e-mail 
						 emailHTML.send();
						 break;
				 	} catch (EmailException e) {				
				 		e.printStackTrace();
						
				 	} catch (MalformedURLException e) {
				 		e.printStackTrace();
				 		break;
				 	}
				
				
			case 2:				
				System.out.println("caso 2");	
				
				
							
				
				SimpleEmail emailSimples = new SimpleEmail();
				//emailSimples.setDebug(true);
				try {
					//emailSimples.setDebug(true);
					//emailSimples.setHostName("smtpauth.rio.rj.gov.br");
					//emailSimples.setAuthentication("carlospereira","ANGELS");
					
					
					emailSimples.setHostName("smtp.gmail.com");					
					emailSimples.setSmtpPort(465);					
					emailSimples.setAuthentication("carlos.paula","ANGEL2000");
					
					
					
					
					//pode ser qualquer um e-mail
					emailSimples.addTo("carlos.paula@gmail.com");
					
					//aqui precisa ser o e-mail que voc� fara a autentica�o
					emailSimples.setFrom("carlos.paula@gmail.com"); 
					emailSimples.setSubject("CRMaster On-Line");
					emailSimples.setMsg("<a href='http://www.rio.rj.gov.br/'>Prefeitura do Rio</a>");
					emailSimples.send();
					break;
				} catch (EmailException e) {
					e.printStackTrace();
					break;
				} 	
				
				
		}
	}

}
