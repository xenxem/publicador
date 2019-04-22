package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.LiberaAnuncio;
import modelo.Ocorrencia;
import modelo.Pagamento;
import modelo.Uf;
import modelo.Usuario;

public class DAOLiberarAnuncioPSQL implements DAOLiberarAnuncio{

	public void cadastrar(LiberaAnuncio la){
		
		Connection conn = DAOFactoryPSQL.getConnection();
				
		try{
			
			PreparedStatement ps = conn.prepareStatement("Insert Into liberaranuncio(codigousuario,codigoanuncio,data,tipo) " +
				"values(?,?,?,?)");			
			ps.setInt(1, la.getUsuario().getCodigoUsuario());
			ps.setInt(2, la.getAnuncio().getCodigoAnuncio());
			ps.setTimestamp(3, la.getData());
			ps.setString(4, la.getTipo());
					
			ps.executeUpdate();
			
						
		}catch(Exception e){
			
			//e.printStackTrace();
			System.out.print("falha em cadastrar liberaranuncio");
		}
		
	}
	
	public ArrayList consultarPendentes(){
		
		try{
		
			ArrayList anuncioLista = new ArrayList();
			ArrayList numeroId = new ArrayList();
			ArrayList dataCriacao = new ArrayList();
						
			Connection conn = DAOFactoryPSQL.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select titulo, a.codigoanuncio, status, datainicio, "+
														 "numeroparaidentificacao, a.codigousuario, codigocategoria, nivelcategoria, "+
														 "codigouf, codigocidade, o.data, p.codigoanuncio, o.codigoanuncio "+
					                                     "From anuncio a, pagamento p, ocorrencia o " +
					                                     "Where a.codigoanuncio = p.codigoanuncio And a.codigoanuncio = o.codigoanuncio "+
					                                     "And (status='P' Or status='B') And tipoocorrencia = 'C' " +
					                                     "Order By numeroparaidentificacao");
			ResultSet r = ps.executeQuery();
			
			while(r.next()){
				Anuncio a = new Anuncio();
				Pagamento p = new Pagamento();
				Ocorrencia o = new Ocorrencia();
				
				a.setTitulo(r.getString("titulo"));
				a.setCodigoAnuncio(r.getInt("codigoanuncio"));
				a.setStatus(r.getString("status"));
				a.setDataInicio(r.getTimestamp("datainicio"));
				p.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
				o.setData(r.getTimestamp("data"));
				
				numeroId.add(p);
				a.setPagamento(numeroId);
				
				dataCriacao.add(o);
				a.setOcorrencia(dataCriacao);
				
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
			e.printStackTrace();
			System.out.print("falha em consultar pendentes anuncio");
			return null;
		}		
		
	}
	
	public void liberarAnuncio(Anuncio a){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update anuncio Set status=?, datainicio=?, datafim=? Where codigoanuncio=?");
			ps.setString(1, a.getStatus());
			ps.setTimestamp(2, a.getDataInicio());
			ps.setTimestamp(3, a.getDataFim());
			ps.setInt(4, a.getCodigoAnuncio());
			ps.executeUpdate();	
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.print("falha em liberar anuncio");
		}
		
	}
	
	public void bloquearAnuncio(Anuncio a){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update anuncio Set status=? Where codigoanuncio=?");
			ps.setString(1, a.getStatus());
			ps.setInt(2, a.getCodigoAnuncio());
			ps.executeUpdate();	
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.print("falha em bloquear anuncio");
		}
		
	}
	
	public Usuario consultarUsuario(Usuario u){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select nome From usuario Where codigousuario=?");
			ps.setInt(1, u.getCodigoUsuario());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				u.setNome(rs.getString("nome"));
			}
			
			return u; 
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("falha em consultar usuario");
			return null;
		}
		
	}
	
	public void mudarStatus(Anuncio a){		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update anuncio Set status=? Where codigoanuncio=?");
			ps.setString(1, a.getStatus());
			ps.setInt(2, a.getCodigoAnuncio());
			ps.executeUpdate();	
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.print("falha em mudar status anuncio");
		}
		
	}
		
}
