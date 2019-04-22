package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Cidade;
import modelo.Imagem;
import modelo.Ocorrencia;
import modelo.Pagamento;
import modelo.Uf;
import modelo.Usuario;

public class DAOAnuncioVencidoPSQL implements DAOAnuncioVencido{

	public ArrayList consultarTodos(){
		
		try{
			
			ArrayList anuncioLista = new ArrayList();
			ArrayList dataCriacao = new ArrayList();
						
			Connection conn = DAOFactoryPSQL.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("Select a.codigousuario, u.codigousuario, a.codigoanuncio, o.codigoanuncio, " +
														 "titulo, descricaoanuncio, datafim, email, data, codigocategoria, " +
														 "nivelcategoria, codigouf, codigocidade "+
														 "From anuncio a, usuario u, ocorrencia o " +
					                                     "Where a.codigousuario = u.codigousuario " +
					                                     "And a.codigoanuncio = o.codigoanuncio "+
					                                     "And status='P' And tipoocorrencia = 'C' "+
					                                     "And current_timestamp > datafim "+
					                                     "Order By datafim");
			ResultSet r = ps.executeQuery();
			
			while(r.next()){
				
				Anuncio a = new Anuncio();
				Usuario usuario = new Usuario();
				Ocorrencia ocorrencia = new Ocorrencia();
				
				a.setTitulo(r.getString("titulo"));
				a.setCodigoAnuncio(r.getInt("codigoanuncio"));
				a.setDescricaoAnuncio(r.getString("descricaoanuncio"));
				a.setDataFim(r.getTimestamp("datafim"));
																
				//usuário
				usuario.setCodigoUsuario(r.getInt("codigousuario"));
				usuario.setEmail(r.getString("email"));
				a.setUsuario(usuario);
				
				//ocorrencia
				ocorrencia.setData(r.getTimestamp("data"));
				dataCriacao.add(ocorrencia);
				a.setOcorrencia(dataCriacao);
				
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
				
				anuncioLista.add(a);

			}			
		
			return anuncioLista;
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("falha em consultar anuncio vencido");
			return null;
		}		
		
	}
	
	public Usuario consultarEmail(Usuario u){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From usuario " +					
							"Where codigousuario=?");			
			ps.setInt(1, u.getCodigoUsuario());
		
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()){
				
				u.setEmail(rs.getString("email"));
				
			}
			
			return u;
		
		}catch (Exception e){
			e.printStackTrace();
			System.out.print("falha em consultar consultar email");
			return null;
		}
		
	
		
	}
	
}
