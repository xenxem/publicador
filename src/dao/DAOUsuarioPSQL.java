package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DAOUsuarioPSQL implements DAOUsuario{
		
		public void cadastrar(Usuario u){
			try {
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Insert Into usuario" +
						"(nome," +
						"apelido," +
						"email," +
						"senha," +
						"numerovalidacao," +
						"statusvalidacao," +
						"perfil)" +
						"Values(?,?,?,?,?,?,?)");				
				ps.setString(1, u.getNome());
				ps.setString(2, u.getApelido());
				ps.setString(3, u.getEmail());
				ps.setString(4, u.getSenha());
				ps.setInt(5, u.getNumeroValidacao());
				ps.setString(6, u.getStatusValidacao());
				ps.setString(7, u.getPerfil());
				ps.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();				
			}
		}
		
		public void excluir(Usuario u){
		 try {
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Delete From usuario Where codigousuario=?");				
				ps.setInt(1, u.getCodigoUsuario());								
				ps.executeUpdate();
			
		 	}catch (SQLException e) {
		 		e.printStackTrace();			
		 	}
		}
		
		public void alterar(Usuario u){			
			try {
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Update usuario " +
						"Set nome=?," +
						"apelido=?," +
						"email=?," +
						"senha=?" +					
						"Where codigousuario=?");
				
				ps.setString(1, u.getNome());
				ps.setString(2, u.getApelido());
				ps.setString(3, u.getEmail());
				ps.setString(4, u.getSenha());
				ps.setInt(5, u.getCodigoUsuario());
								
				ps.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();			
			}				
		}
		
		public Usuario consultar(Usuario u){
			try {	
					
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("Select * from usuario Where (Upper(email)=? Or Upper(apelido)=?) And senha=?");
					
					ps.setString(1, u.getEmail().toUpperCase());
					ps.setString(2, u.getEmail().toUpperCase());
					ps.setString(3, u.getSenha());
					
					ResultSet rs = ps.executeQuery();
					
					
					if(rs.next()){
					
						u.setCodigoUsuario(rs.getInt("codigousuario"));
						u.setNome(rs.getString("nome"));
						u.setApelido(rs.getString("apelido"));
						u.setEmail(rs.getString("email"));
						u.setSenha(rs.getString("senha"));
						u.setNumeroValidacao(rs.getInt("numerovalidacao"));
						u.setStatusValidacao(rs.getString("statusvalidacao"));
						u.setPerfil(rs.getString("perfil"));
						
						return u;
					}
					 
				return null;
				
			} catch (SQLException e) {
				e.printStackTrace();					
				return null;
			}			
		}
		
		public ArrayList consultarTodos(){
			try {
			ArrayList listaUsuario = new ArrayList();
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * from usuario");
						
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Usuario u = new Usuario();
				
				u.setCodigoUsuario(rs.getInt("codigousuario"));
				u.setNome(rs.getString("nome"));
				u.setApelido(rs.getString("apelido"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setNumeroValidacao(rs.getInt("numerovalidacao"));
				u.setStatusValidacao(rs.getString("statusvalidacao"));
				u.setPerfil(rs.getString("perfil"));
				
				listaUsuario.add(u);
			}
			
			return listaUsuario;
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;				
			}				
		}
		
		public ArrayList consultarPendentes(Usuario u){
			try {
				ArrayList listaUsuario = new ArrayList();
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select apelido from usuario where codigousuario=?");
				ps.setInt(1, u.getCodigoUsuario());
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Usuario u2 = new Usuario();
					
					u2.setApelido(rs.getString("apelido"));
					
					listaUsuario.add(u);
				}
				
				return listaUsuario;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;					
			}		
		}
		
		public Usuario consultarApelido(Usuario u){
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select apelido From usuario Where Upper(apelido)=?" +
						"And codigousuario <> ?");
				ps.setString(1, u.getApelido().toUpperCase());
				ps.setInt(2, u.getCodigoUsuario());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					u.setApelido(rs.getString("apelido"));
					return u;
				}
				
				return null;			
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public Usuario consultarEmail(Usuario u){
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select email From usuario Where Upper(email)=?");
				ps.setString(1, u.getEmail().toUpperCase());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					u.setEmail(rs.getString("email"));
					return u;
				}
			
				return null;
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public ArrayList consultarAnuncios(Usuario u){
			try{	
					ArrayList anuncioLista = new ArrayList();
					
					
					Connection conn = DAOFactoryPSQL.getConnection();
					
					
					PreparedStatement ps = conn.prepareStatement("Select * From anuncio Where codigousuario=? Order By codigoanuncio Desc");
					ps.setInt(1,u.getCodigoUsuario());
					ResultSet r = ps.executeQuery();
					
				
					
					while(r.next()){
						Anuncio a = new Anuncio();				
						a.setCodigoAnuncio(r.getInt("codigoanuncio"));
						a.setTitulo(r.getString("titulo"));
						a.setDescricaoAnuncio(r.getString("descricaoanuncio"));
						a.setStatus(r.getString("status"));
						a.setAcessos(r.getInt("acessos"));
						a.setDataInicio(r.getTimestamp("datainicio"));
						a.setDataFim(r.getTimestamp("datafim"));
						
						//usuário
						Usuario usuario = new Usuario();
						usuario.setCodigoUsuario(r.getInt("codigousuario"));
						a.setUsuario(usuario);
						
						//categoria
						Categoria categoria = new Categoria();
						categoria.setCodigoCategoria(r.getInt("codigocategoria"));
						categoria.setNivelCategoria(r.getInt("nivelcategoria"));
						a.setCategoria(categoria);
						
						//uf
						Uf uf = new Uf();
						uf.setCodigoUf(r.getString("codigouf"));				
						a.setUf(uf);
						
						//cidade
						Cidade cidade = new Cidade();
						cidade.setCodigoCidade(r.getInt("codigocidade"));
						a.setCidade(cidade);
						
						
						PreparedStatement ps2 = conn.prepareStatement("Select * from imagem Where codigoanuncio=?");
						ps2.setInt(1, a.getCodigoAnuncio());
						ResultSet r2 = ps2.executeQuery();
						
						Vector vetorImagem = new Vector();
						
						//imagens
						while(r2.next()){					
							Imagem imagem = new Imagem();
							imagem.setCodigoImagem(r2.getInt("codigoimagem"));					
							imagem.setImgNome(r2.getString("imgnome"));
							
							//System.out.println(imagem.getImgNome());
							
							imagem.setDestaque(r2.getString("destaque"));
							imagem.setOrdem(r2.getInt("ordem"));
							vetorImagem.add(imagem);					
						}
						a.setImagem(vetorImagem);				
						anuncioLista.add(a);				
					}			
					return anuncioLista;
					
				}catch(Exception e){
					System.out.print("falha em consultar todos anuncio");
					return null;
				}				
			}
		
		public Usuario consultarPorCodigo(Usuario u){
			try {	
					
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("Select * from usuario Where codigousuario=?");
					
					ps.setInt(1, u.getCodigoUsuario());					
					ResultSet rs = ps.executeQuery();
					
					
					if(rs.next()){
					
						u.setCodigoUsuario(rs.getInt("codigousuario"));
						u.setNome(rs.getString("nome"));
						u.setApelido(rs.getString("apelido"));
						u.setEmail(rs.getString("email"));
						u.setSenha(rs.getString("senha"));
						u.setNumeroValidacao(rs.getInt("numerovalidacao"));
						u.setStatusValidacao(rs.getString("statusvalidacao"));
						u.setPerfil(rs.getString("perfil"));
						
						return u;
					}
					 
				return null;
				
			} catch (SQLException e) {
				e.printStackTrace();					
				return null;
			}			
		}

		
}


