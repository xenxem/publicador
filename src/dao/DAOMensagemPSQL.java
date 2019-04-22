package dao;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DAOMensagemPSQL implements DAOMensagem{
	
		Connection conn;
		
		public DAOMensagemPSQL(){
			 conn = DAOFactoryPSQL.getConnection();			
		}
	
	
		public void cadastrar(Mensagem m){
		 try{	
			 	
			 	
				PreparedStatement ps = conn.
				prepareStatement("Insert Into " +
						"mensagem(data,codigousuario,codigoanuncio,descricaomensagem,tipo,ordem)" +
						"values(?,?,?,?,?,?)");			
				ps.setTimestamp(1, m.getData());				
				ps.setInt(2, m.getUsuario().getCodigoUsuario());
				ps.setInt(3, m.getAnuncio().getCodigoAnuncio());
				ps.setString(4, m.getDescricaoMensagem());
				ps.setString(5, m.getTipo());
				ps.setInt(6, m.getOrdem());
				ps.executeUpdate();
				
				
				
		   }catch(Exception e){
			 e.printStackTrace();
		   }
		}
		
		public void excluir(Mensagem m){
		  try{
			    
			
			    PreparedStatement ps = conn.
				prepareStatement("Delete From mensagem Where codigomensagem=?");			
				ps.setInt(1, m.getCodigoMensagem());			
				ps.executeUpdate();
				
				
				
		  	}catch(Exception e){
		  		e.printStackTrace();
		  	}			
		}
		
		public void excluirPerguntaResposta(Mensagem m){
			  try{
				    
				
				    PreparedStatement ps = conn.
					prepareStatement("Delete From mensagem Where ordem=?");			
					ps.setInt(1, m.getOrdem());			
					ps.executeUpdate();
					
					
					
			  	}catch(Exception e){
			  		e.printStackTrace();
			  	}			
			}
		
		public void alterar(Mensagem m){
		  try{
			  	
				PreparedStatement ps = conn.
				prepareStatement("Update mensagem set " +					
						"descricaomensagem=? " +
						"Where codigomensagem=?");			
				ps.setString(1, m.getDescricaoMensagem());
				ps.setInt(2, m.getCodigoMensagem());
				
				ps.executeUpdate();
				
		    }catch(Exception e){
		    	e.printStackTrace();
		    }				
		}
		
		public void alterarOrdem(Mensagem m){
			  try{
				  	
					PreparedStatement ps = conn.
					prepareStatement("Update mensagem set " +					
							"ordem=? " +
							"Where codigomensagem=?");			
					ps.setInt(1, m.getOrdem());
					ps.setInt(2, m.getCodigoMensagem());
					
					ps.executeUpdate();
					
			    }catch(Exception e){
			    	e.printStackTrace();
			    }				
			}

		
		public Mensagem consultar(Mensagem m){
			try{
				
				PreparedStatement ps = conn.
				prepareStatement("Select codigomensagem," +
						"descricaomensagem," +
						"data," +
						"tipo," +
						"codigousuario," +						
						"codigoanuncio " +						
						"From mensagem " +					
						"Where codigomensagem=?");			
				ps.setInt(1, m.getCodigoMensagem());
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					m.setCodigoMensagem(rs.getInt("codigomensagem"));
					m.setDescricaoMensagem(rs.getString("descricaomensagem"));
					m.setData(rs.getTimestamp("data"));
					m.setTipo(rs.getString("tipo"));
					
					
					//usuario
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));					
					m.setUsuario(u);
					
					//anuncio
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(rs.getInt("codigoanuncio"));					
					m.setAnuncio(a);			
				}
				
				return m;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarTodos(){
		  try{	
				ArrayList listaComentario = new ArrayList();
				
				PreparedStatement ps = conn.
				prepareStatement("Select * From mensagem");			
				
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Mensagem m = new Mensagem();
					
					m.setCodigoMensagem(rs.getInt("codigomensagem"));
					m.setDescricaoMensagem(rs.getString("descricaomensagem"));
					m.setData(rs.getTimestamp("data"));
					m.setTipo(rs.getString("tipo"));
					
					
					//usuario
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					m.setUsuario(u);
					
					//anuncio
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					m.setAnuncio(a);
					
					listaComentario.add(m);
				}
				
				return listaComentario;
				
		  }catch(Exception e){
			  e.printStackTrace();
			  return null;
		  }
				
		}
		
		public ArrayList consultarMensagens(Mensagem m){
			try{
				ArrayList listaMensagem = new ArrayList();
												
				PreparedStatement ps = conn.
				prepareStatement("Select m.codigomensagem, " +
						"m.codigoanuncio, " +
						"m.codigousuario, " +
						"m.data, " +
						"m.tipo, " +
						"m.descricaomensagem," +
						"u.apelido, " +
						"u.codigousuario  " +
						"From mensagem m, usuario u  " +
						"Where m.codigoanuncio=? " +
						"And u.codigousuario = m.codigousuario " +
						"Order By m.ordem Asc, m.codigomensagem Asc");			
				
				ps.setInt(1,m.getAnuncio().getCodigoAnuncio());				
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Mensagem m2 = new Mensagem();
					
					m2.setCodigoMensagem(rs.getInt("codigomensagem"));
					m2.setDescricaoMensagem(rs.getString("descricaomensagem"));
					m2.setData(rs.getTimestamp("data"));
					m2.setTipo(rs.getString("tipo"));
					
					
					
					//usuario
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					u.setApelido(rs.getString("apelido"));
					m2.setUsuario(u);
					
					//anuncio
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					m2.setAnuncio(a);
					
					listaMensagem.add(m2);
				}
				
				return listaMensagem;	
				
			 }catch(Exception e){
				 e.printStackTrace();	
				 return null;
			 }
		}
		
		public Mensagem consultarUltima(Mensagem m){
		  try{
				
				PreparedStatement ps = conn.
				prepareStatement("Select Max(codigomensagem) As codigomensagem" +
						" From mensagem Where tipo='P'" +
						"And codigousuario=?" +
						"And codigoanuncio=?");
				ps.setInt(1, m.getUsuario().getCodigoUsuario());
				ps.setInt(2,m.getAnuncio().getCodigoAnuncio());
				
				ResultSet rs = ps.executeQuery();
				
				Mensagem m2 = new Mensagem();
				
				if(rs.next()){
					m2.setCodigoMensagem(rs.getInt("codigomensagem"));							
				}
				
				return m2;
			}catch(Exception e){				
				e.printStackTrace();
				return null;
			}
			
		}
		
		public ArrayList consultarMensagensUsuario(Mensagem m){
			try{
				ArrayList listaMensagem = new ArrayList();
				
				//Query para exibir no grid mensagens sem resposta
				PreparedStatement ps = conn.
				prepareStatement("Select a.*, " +
						"b.apelido, " +
						"b.*, " +
						"c.titulo " +
						"From mensagem a, usuario b, anuncio c " +
						"where a.codigousuario = b. codigousuario	" +
						"And a.codigoanuncio = c.codigoanuncio	" +
						"And exists( Select * " +
						"			 from anuncio " +
						"			 where a.codigoanuncio = anuncio.codigoanuncio " +
						"			 And anuncio.codigousuario = ?" +
						"			) " +
						"Order By a.ordem Asc, a.codigomensagem Asc");				
				ps.setInt(1,m.getUsuario().getCodigoUsuario());				
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Mensagem m2 = new Mensagem();
					
					m2.setCodigoMensagem(rs.getInt("codigomensagem"));
					m2.setDescricaoMensagem(rs.getString("descricaomensagem"));
					m2.setData(rs.getTimestamp("data"));
					m2.setTipo(rs.getString("tipo"));
					m2.setOrdem(rs.getInt("ordem"));
										
					//usuario
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					u.setApelido(rs.getString("apelido"));
					m2.setUsuario(u);
					
					//anuncio
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					a.setTitulo(rs.getString("titulo"));					
					m2.setAnuncio(a);
					
					listaMensagem.add(m2);
				}
				
				return listaMensagem;	
				
			 }catch(Exception e){
				 e.printStackTrace();	
				 return null;
			 }
		}		
}