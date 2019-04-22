package dao;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOAnuncioAcompanhamentoPSQL implements DAOAnuncioAcompanhamento{
		
		Connection conn;
		
		
		public DAOAnuncioAcompanhamentoPSQL(){
			try{
				conn = DAOFactoryPSQL.getConnection();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	
		public void cadastrar(AnuncioAcompanhamento a){
			try{
				PreparedStatement ps;
				ps = conn.prepareStatement("Insert Into acompanha(codigousuario,codigoanuncio,data)" +
						"Values(?,?,?)");
				ps.setInt(1, a.getUsuario().getCodigoUsuario());
				ps.setInt(2, a.getAnuncio().getCodigoAnuncio());
				ps.setTimestamp(3, a.getData());			
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		
		public void excluir(AnuncioAcompanhamento a){
			try{
				PreparedStatement ps;
				ps = conn.prepareStatement("Delete From acompanha Where codigoanuncio=? And codigousuario=?");
				ps.setInt(1,a.getAnuncio().getCodigoAnuncio());
				ps.setInt(2,a.getUsuario().getCodigoUsuario());				
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void alterar(AnuncioAcompanhamento a){
			try{
				
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		
		public AnuncioAcompanhamento consultar(AnuncioAcompanhamento a){
			try{
				
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement("Select * From acompanha " +
						"Where codigousuario=? " +
						"And codigoanuncio=?");
				ps.setInt(1, a.getUsuario().getCodigoUsuario());
				ps.setInt(2, a.getAnuncio().getCodigoAnuncio());							
				rs = ps.executeQuery();
				
				
				
				if(rs.next()){
				
					AnuncioAcompanhamento anuncioAcompanha = new AnuncioAcompanhamento();
					
					Anuncio anuncio = new Anuncio();
					anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					anuncioAcompanha.setAnuncio(anuncio);
					
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					anuncioAcompanha.setUsuario(u);
					
					anuncioAcompanha.setData(rs.getTimestamp("data"));
					
					return anuncioAcompanha;
				}else
					return null;
								
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}				
		}
		
		public AnuncioAcompanhamento consultarAnuncio(AnuncioAcompanhamento a){
			try{
				
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement("Select a.*, " +
						"b.titulo, " +
						"b.descricaoanuncio, " +
						"b.codigocategoria," +
						"b.nivelcategoria," +
						"b.codigouf," +
						"b.codigousuario As codigousuariocadastrador," +
						"b.status," +
						"d.descricaocategoria," +
						"c.descricaocidade " +						
						"From acompanha a Left Join anuncio b On a.codigoanuncio = b.codigoanuncio " +
						"Left Join cidade c On b.codigocidade = c.codigocidade " +
						"Left Join categoria d On b.codigocategoria = d.codigocategoria " +
						"And b.nivelcategoria = d.nivelcategoria " +						
						"Where a.codigoanuncio=? ");
				ps.setInt(1, a.getAnuncio().getCodigoAnuncio());							
				rs = ps.executeQuery();
				
				
				
				if(rs.next()){
				
					AnuncioAcompanhamento anuncioAcompanha = new AnuncioAcompanhamento();
					
					Anuncio anuncio = new Anuncio();
					anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					anuncio.setTitulo(rs.getString("titulo"));
					anuncio.setDescricaoAnuncio(rs.getString("descricaoanuncio"));
					anuncio.setStatus(rs.getString("status"));
					
					Uf uf = new Uf();
					uf.setCodigoUf(rs.getString("codigouf"));
					anuncio.setUf(uf);
					
					Cidade cidade = new Cidade();
					cidade.setDescricaoCidade(rs.getString("descricaocidade"));
					anuncio.setCidade(cidade);
					
					Categoria categoria = new Categoria();
					categoria.setDescricaoCategoria(rs.getString("descricaocategoria"));
					anuncio.setCategoria(categoria);
					
					anuncioAcompanha.setAnuncio(anuncio);
					
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuariocadastrador"));					
					anuncioAcompanha.setUsuario(u);
					
					anuncioAcompanha.setData(rs.getTimestamp("data"));
					
					return anuncioAcompanha;
				}else
					return null;
								
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}				
		}
		
		
		public ArrayList consultarAcompanhamentoIndividual(AnuncioAcompanhamento a){
			try{
				
				ArrayList listaAcompanha =  new ArrayList();
				
				
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.
				prepareStatement("Select a.codigousuario, b.status, b.codigoanuncio, b.titulo, b.descricaoanuncio, c.data " + 
								 "From acompanha a, anuncio b, " +
								 " ( " +
								    "Select o.codigoanuncio, Max(data) As data " + 
								    "From ocorrencia o " + 
								    "Where Exists " + 
									      "(" +
									       " Select codigoanuncio " +
									       " From acompanha a " +
									       " Where codigousuario=? " +
									       " And a.codigoanuncio = o.codigoanuncio " +
									       ")" +
								  " Group By codigoanuncio " +
								  ") As c" + 
								  " Where a.codigousuario=? " +
								  " And a.codigoanuncio = b.codigoanuncio " +
								  " And a.codigoanuncio = c.codigoanuncio " +
								  " And b.codigoanuncio = c.codigoanuncio ");
				ps.setInt(1, a.getUsuario().getCodigoUsuario());
				ps.setInt(2, a.getUsuario().getCodigoUsuario());
				rs = ps.executeQuery();
				
				
				ArrayList listaOcorrencia = new ArrayList();
				
				while(rs.next()){
				
					AnuncioAcompanhamento anuncioAcompanha = new AnuncioAcompanhamento();
					
					//Anúncio
					Anuncio anuncio = new Anuncio();
					anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					anuncio.setTitulo(rs.getString("titulo"));
					anuncio.setDescricaoAnuncio(rs.getString("descricaoanuncio"));
					anuncio.setStatus(rs.getString("status"));
					
					//Ocorrência
					Ocorrencia o = new Ocorrencia();
					o.setData(rs.getTimestamp("data"));
					listaOcorrencia.add(o);
					
					anuncio.setOcorrencia(listaOcorrencia);					
					anuncioAcompanha.setAnuncio(anuncio);
					
					//Usuário
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					anuncioAcompanha.setUsuario(u);
					
					anuncioAcompanha.setData(rs.getTimestamp("data"));
					
					listaAcompanha.add(anuncioAcompanha);		
				
				}
				
				return listaAcompanha;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}	
		}
		
		public ArrayList consultarTodos(){
			try{
				
				PreparedStatement ps;
				ResultSet rs;
				
				ps = conn.prepareStatement("Select * From acompanha ");
				
				
				
				
				
				return null;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}	
		}
		
}
