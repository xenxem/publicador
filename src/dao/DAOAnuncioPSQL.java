package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;






public class DAOAnuncioPSQL implements DAOAnuncio{
	
		
	
	public void cadastrar(Anuncio a){
	try{
		
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Insert Into anuncio " +
					"(titulo," +
					"descricaoanuncio," +
					"status," +
					"acessos," +
					"datainicio," +
					"datafim," +					
					"codigousuario," +
					"codigocategoria," +
					"nivelcategoria," +
					"codigouf," +
					"codigocidade) " +
					"Values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, a.getTitulo());
			ps.setString(2, a.getDescricaoAnuncio());
			ps.setString(3, a.getStatus());
			ps.setInt(4, a.getAcessos());			
			ps.setTimestamp(5, a.getDataInicio());
			ps.setTimestamp(6, a.getDataFim());
			ps.setInt(7, a.getUsuario().getCodigoUsuario());
			ps.setInt(8, a.getCategoria().getCodigoCategoria());
			ps.setInt(9,a.getCategoria().getNivelCategoria()); 
			ps.setString(10, a.getUf().getCodigoUf());
			ps.setInt(11,a.getCidade().getCodigoCidade());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	public void excluir(Anuncio a){
	try{	
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Delete From anuncio Where codigoanuncio=?");
			ps.setInt(1,a.getCodigoAnuncio());
			ps.executeUpdate();
			
		}catch(Exception e){
			System.out.print("falha em excluir anuncio");				
		}	
		
	}
	
	public void alterar(Anuncio a){
	try{	
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Update anuncio " +
						"Set titulo=?, " +
						"descricaoanuncio=?, " +												
						"codigocategoria=?, " +
						"nivelcategoria=?," +					
						"codigouf=?," +
						"codigocidade=?" +						
						"Where codigoanuncio=? And codigousuario=?");				
				ps.setString(1,a.getTitulo());
				ps.setString(2,a.getDescricaoAnuncio());				
				ps.setInt(3,a.getCategoria().getCodigoCategoria());
				ps.setInt(4,a.getCategoria().getNivelCategoria());				
				ps.setString(5,a.getUf().getCodigoUf());
				ps.setInt(6,a.getCidade().getCodigoCidade());				
				ps.setInt(7,a.getCodigoAnuncio());
				ps.setInt(8,a.getUsuario().getCodigoUsuario());
				ps.executeUpdate();			
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("falha em alterar anuncio");			
		}		
			
	}
	
	public Anuncio consultar(Anuncio a){
	try{
		
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select *, c.descricaocategoria " +
					"From anuncio a, categoria c " +
					"where a.codigocategoria = c.codigocategoria " +
					"And a.nivelcategoria = c.nivelcategoria " +
					"And codigoanuncio=?");
			ps.setInt(1, a.getCodigoAnuncio());			
			ResultSet r = ps.executeQuery();
			
			if(r.next()){
				a.setCodigoAnuncio(r.getInt("codigoanuncio"));
				a.setTitulo(r.getString("titulo"));
				a.setDescricaoAnuncio(r.getString("descricaoanuncio"));
				a.setStatus(r.getString("status"));
				a.setAcessos(r.getInt("acessos"));
				
				Usuario usuario = new Usuario();
				usuario.setCodigoUsuario(r.getInt("codigousuario"));
				a.setUsuario(usuario);
				
				Categoria categoria = new Categoria();
				categoria.setCodigoCategoria(r.getInt("codigocategoria"));
				categoria.setNivelCategoria(r.getInt("nivelcategoria"));
				categoria.setDescricaoCategoria(r.getString("descricaocategoria"));
				a.setCategoria(categoria);
				
				Cidade cidade = new Cidade();
				cidade.setCodigoCidade(r.getInt("codigocidade"));
				a.setCidade(cidade);				
				
				Uf uf = new Uf();
				uf.setCodigoUf(r.getString("codigouf"));
				a.setUf(uf);
				
				a.setDataInicio(r.getTimestamp("datainicio"));
				a.setDataFim(r.getTimestamp("datafim"));			
				
			}
		
		return a;	
	
	}catch(Exception e){
		return null;
	}
		
		
}
	
	

	public ArrayList consultarTodos(){
	try{	
			ArrayList anuncioLista = new ArrayList();
			
			
			Connection conn = DAOFactoryPSQL.getConnection();
			
			
			PreparedStatement ps = conn.prepareStatement("Select * From anuncio Order By codigoanuncio Desc");
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
	
	public Anuncio consultarUltimo(Anuncio a){
		try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("select max(codigoanuncio) As codigoanuncio From anuncio Where codigousuario=?");			
			ps.setInt(1, a.getUsuario().getCodigoUsuario());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){				
				Anuncio anuncio = new Anuncio();
				anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));
				
				return anuncio;
			}else
				return null;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList consultarTodosPesquisa(Anuncio a, String criterio,int start){
		try{
			
			Connection conn = DAOFactoryPSQL.getConnection();			
			PreparedStatement ps;
			ResultSet rs;
									
			String query = "Select codigoanuncio," +
					"titulo," +
					"codigouf," +
					"codigousuario" +
					" From anuncio Where status='L' And ";
			
			/* título */
			if(!a.getTitulo().equals("")){
				
				if(!criterio.equals("nenhum")){
					query += " Upper(titulo) Like ?";
				}else{
					query += " Upper(titulo) similar to ?";
				}
				
				/* uf */
				if(!a.getUf().getCodigoUf().equals("")){
					query += " And codigouf = ?";
				
					/* cidade */
					if(a.getCidade().getCodigoCidade()!=0){
						query += " And codigocidade = ?";
						
						/* categoria */
						if(a.getCategoria().getCodigoCategoria()!=0){
							query += " And codigocategoria = ? And nivelcategoria = ?";
						}
						/* categoria */	
					}else if(a.getCategoria().getCodigoCategoria()!=0){
						query += " And codigocategoria = ? And nivelcategoria = ?";
					}
					/* categoria */
				}else if(a.getCategoria().getCodigoCategoria()!=0){
					query += " And codigocategoria = ? And nivelcategoria = ?";
				}
				/* uf */
			}else if(!a.getUf().getCodigoUf().equals("")){
				query += " codigouf = ?";
				
				/* cidade */
				if(a.getCidade().getCodigoCidade()!=0){
					query += " And codigocidade = ?";
					
					/* categoria */
					if(a.getCategoria().getCodigoCategoria()!=0){
						query += " And codigocategoria = ? And nivelcategoria = ?";
					}
					/* categoria */
				}else if(a.getCategoria().getCodigoCategoria()!=0){					
					query += " And codigocategoria = ? And nivelcategoria = ?";
				}
				/*categoria */
			}else if(a.getCategoria().getCodigoCategoria()!=0){
				
				query += " codigocategoria = ? And nivelcategoria = ? Or codigocategoria In " +
						"(Select codigocategoria From func_recursive_table(?)) ";
			}
			
			query +=" Limit 3 Offset " + start;
			
			ps = conn.prepareStatement(query);
			
			/* título */
			if(!a.getTitulo().equals("")){
				
				if(criterio.equals("meio")){
					ps.setString(1, "%"+a.getTitulo().toUpperCase()+"%");
				}else if(criterio.equals("inicio")){
					ps.setString(1, a.getTitulo().toUpperCase()+"%");
				}else if(criterio.equals("fim")){
					ps.setString(1,"%"+a.getTitulo().toUpperCase());
				}else{
					ps.setString(1,"%["+a.getTitulo().toUpperCase()+"]%");
				}
				
				/* uf */
				if(!a.getUf().getCodigoUf().equals("")){
					ps.setString(2, a.getUf().getCodigoUf());
					
					/* cidade */
					if(a.getCidade().getCodigoCidade()!=0){
						ps.setInt(3, a.getCidade().getCodigoCidade());
						
						/* categoria */
						if(a.getCategoria().getCodigoCategoria()!=0){
							ps.setInt(4, a.getCategoria().getCodigoCategoria());
							ps.setInt(5, a.getCategoria().getNivelCategoria());
						}	
					/* categoria */	
					}else if(a.getCategoria().getCodigoCategoria()!=0){
						ps.setInt(3, a.getCategoria().getCodigoCategoria());
						ps.setInt(4, a.getCategoria().getNivelCategoria());
					}
					/* categoria */
				}else if(a.getCategoria().getCodigoCategoria()!=0){
					ps.setInt(2, a.getCategoria().getCodigoCategoria());
					ps.setInt(3, a.getCategoria().getNivelCategoria());
				}
				/* uf */
			}else if(!a.getUf().getCodigoUf().equals("")){
				ps.setString(1, a.getUf().getCodigoUf());
				
				/* cidade */
				if(a.getCidade().getCodigoCidade()!=0){
					ps.setInt(2, a.getCidade().getCodigoCidade());
					
					/* categoria */
					if(a.getCategoria().getCodigoCategoria()!=0){
						ps.setInt(3, a.getCategoria().getCodigoCategoria());
						ps.setInt(4, a.getCategoria().getNivelCategoria());
					}
					/* categoria */
				}else if(a.getCategoria().getCodigoCategoria()!=0){
					
					ps.setInt(2, a.getCategoria().getCodigoCategoria());
					ps.setInt(3, a.getCategoria().getNivelCategoria());
				}				
				/* categoria */
			}else if(a.getCategoria().getCodigoCategoria()!=0){
				ps.setInt(1, a.getCategoria().getCodigoCategoria());
				ps.setInt(2, a.getCategoria().getNivelCategoria());
				ps.setInt(3, a.getCategoria().getCodigoCategoria());
				
			}
			
			rs = ps.executeQuery();
			
			ArrayList anuncioListaPesquisados = new ArrayList();
			
		
			
			while(rs.next()){
				
				Anuncio anuncio = new Anuncio();
				anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));
				anuncio.setTitulo(rs.getString("titulo"));
				
				//uf
				Uf uf = new Uf();
				uf.setCodigoUf(rs.getString("codigouf"));
				anuncio.setUf(uf);
				
				//usuario
				Usuario usuario = new Usuario();
				usuario.setCodigoUsuario(rs.getInt("codigousuario"));
				anuncio.setUsuario(usuario);
				
				
				//imagem
				PreparedStatement ps2 = conn.
				prepareStatement("Select * from imagem Where codigoanuncio=?");
				ps2.setInt(1, anuncio.getCodigoAnuncio());
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
				
				anuncio.setImagem(vetorImagem);				
				anuncioListaPesquisados.add(anuncio);				
			}
			
			return anuncioListaPesquisados;		
			
			
		}catch(Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer consultarQtdImagem(Anuncio a){
		try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select codigoanuncio, Count(*) As qtd " +
					"From imagem Where codigoanuncio=?" +
					"Group By codigoanuncio");			
			ps.setInt(1, a.getCodigoAnuncio());
			ResultSet rs = ps.executeQuery();
			
			Integer qtd=null;
			
			
			
			if(rs.next()){
				qtd = new Integer(rs.getInt("qtd"));
			}
			
			return qtd;
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList consultarAnunciosRenovacao(Anuncio a){
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select codigoanuncio, titulo, descricaoanuncio, status " +
							"From anuncio " +
							"Where (Date(current_timestamp) - Date(datainicio)) >= 25 " +
							"And codigousuario = ? ");
			ps.setInt(1, a.getUsuario().getCodigoUsuario());
			ResultSet rs = ps.executeQuery();
			
			ArrayList listaAnuncio = new ArrayList();
			
			while(rs.next()){
			
				Anuncio a2 = new Anuncio();
				a2.setCodigoAnuncio(rs.getInt("codigoanuncio"));
				a2.setStatus(rs.getString("status"));
				a2.setTitulo(rs.getString("titulo"));
				a2.setDescricaoAnuncio(rs.getString("descricaoanuncio"));				
				listaAnuncio.add(a2);				
			}
			
			
			return listaAnuncio;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public void renovarAnuncio(Anuncio a){
		try{	
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("Update anuncio " +
							"Set " +
							"datainicio=?, " +												
							"datafim=? " +													
							"Where codigoanuncio=? And codigousuario=?");				
					
					ps.setTimestamp(1,a.getDataInicio());				
					ps.setTimestamp(2,a.getDataFim());									
					ps.setInt(3,a.getCodigoAnuncio());
					ps.setInt(4,a.getUsuario().getCodigoUsuario());
					
					ps.executeUpdate();			
			
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("falha em alterar anuncio");			
			}		
				
		}
	
}

