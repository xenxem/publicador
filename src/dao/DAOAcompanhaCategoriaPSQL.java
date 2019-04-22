package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DAOAcompanhaCategoriaPSQL implements DAOAcompanhaCategoria{
		
		Connection conn;
		
		DAOAcompanhaCategoriaPSQL(){
			
			conn = DAOFactoryPSQL.getConnection();
		}
	
		public void cadastrar(AcompanhaCategoria ac){
			try{
				PreparedStatement ps;
				
				String sql;
				sql = "Insert Into acompanhacategoria" +
						"(codigousuario," +						
						"codigocategoria," +
						"nivelcategoria," +
						"periodo," +
						"data ";
				
				if(ac.getUf().getCodigoUf().equals("")){
					sql += ") Values(?,?,?,?,?)";						
				}else{
					if(ac.getCidade().getCodigoCidade()==0){
						sql+=",codigouf) " +						 						
							  "Values(?,?,?,?,?,?)";						
					}else{
						sql+= ",codigouf," +
							  "codigocidade) " +						 						
						  "Values(?,?,?,?,?,?,?)";						
					}					
				}
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ac.getUsuario().getCodigoUsuario());				
				ps.setInt(2, ac.getCategoria().getCodigoCategoria());
				ps.setInt(3, ac.getCategoria().getNivelCategoria());
				ps.setString(4,ac.getPeriodo());
				ps.setTimestamp(5,ac.getData());
				
				if(!ac.getUf().getCodigoUf().equals("")){
					ps.setString(6,ac.getUf().getCodigoUf());
					
					if(ac.getCidade().getCodigoCidade()!=0)	
						ps.setInt(7, ac.getCidade().getCodigoCidade());
				}
				
				ps.executeUpdate();
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		public void excluir(AcompanhaCategoria ac){
			try{
				PreparedStatement ps;
				
				String sql;
				sql = "Delete " +
					  "From acompanhacategoria " +
					  "Where codigousuario=? " +
					  "And codigocategoria=? " +
					  "And nivelcategoria=?";				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ac.getUsuario().getCodigoUsuario());
				ps.setInt(2,ac.getCategoria().getCodigoCategoria());
				ps.setInt(3,ac.getCategoria().getNivelCategoria());
				ps.executeUpdate();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void alterar(AcompanhaCategoria ac){
			try{
				PreparedStatement ps;
				
				String sql;
				sql = "Update acompanhacategoria " +
						"Set codigocategoria=?," +
						"nivelcategoria=?," +
						"periodo=?," +
						"data=?," +					
						"codigouf=?," +						
						"codigocidade=? " +
						"Where codigousuario=? " +
						"And codigocategoria=? " +
						"And nivelcategoria=?";
				
				ps = conn.prepareStatement(sql);				
				ps.setInt(1, ac.getCategoria().getCodigoCategoria());
				ps.setInt(2, ac.getCategoria().getNivelCategoria());
				ps.setString(3,ac.getPeriodo());
				ps.setTimestamp(4,ac.getData());				
				ps.setString(5,ac.getUf().getCodigoUf());
				ps.setInt(6, ac.getCidade().getCodigoCidade());
				ps.setInt(7, ac.getUsuario().getCodigoUsuario());
				ps.setInt(8, ac.getCategoria().getCodigoCategoria());
				ps.setInt(9, ac.getCategoria().getNivelCategoria());
				ps.executeUpdate();
			
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		
		public AcompanhaCategoria consultar(AcompanhaCategoria ac){
			try{
				PreparedStatement ps;
				ResultSet rs;
				
				
				String sql;
				sql = " SELECT  a.data, "+  
						"a.periodo, " + 
						"b.codigousuario, " +  
						"b.apelido, " + 					  
						"d.codigocategoria, " + 
						"d.nivelcategoria," +
						"d.descricaocategoria, " + 
						"e.codigouf, " + 
						"e.descricaouf, " + 
						"f.codigocidade, " + 
						"f.descricaocidade " +  
						"FROM acompanhacategoria a INNER JOIN usuario b ON a.codigousuario = b.codigousuario " +
						"INNER JOIN categoria d ON a.codigocategoria = d.codigocategoria " +
						"AND a.nivelcategoria = d.nivelcategoria " +
						"LEFT JOIN uf e On a.codigouf = e.codigouf LEFT JOIN cidade f " +
						"ON a.codigocidade = f.codigocidade  " +
						"WHERE a.codigousuario=?   " +
						"AND a.codigocategoria=? " +
						"AND a.nivelcategoria =?";				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ac.getUsuario().getCodigoUsuario());
				ps.setInt(2,ac.getCategoria().getCodigoCategoria());
				ps.setInt(3,ac.getCategoria().getNivelCategoria());
				rs = ps.executeQuery();
				
				if(rs.next()){
					ac.setData(rs.getTimestamp("data"));
					ac.setPeriodo(rs.getString("periodo"));
					
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					u.setApelido(rs.getString("apelido"));
					ac.setUsuario(u);
										
					Categoria c = new Categoria();
					c.setCodigoCategoria(rs.getInt("codigocategoria"));
					c.setNivelCategoria(rs.getInt("nivelcategoria"));
					c.setDescricaoCategoria(rs.getString("descricaocategoria"));
					ac.setCategoria(c);
					
					
					Uf uf = new Uf();
					uf.setCodigoUf(rs.getString("codigouf"));
					uf.setDescricaoUf("descricaouf");
					ac.setUf(uf);
					
					Cidade cidade = new Cidade();
					cidade.setCodigoCidade(rs.getInt("codigocidade"));
					cidade.setDescricaoCidade(rs.getString("descricaocidade"));
					ac.setCidade(cidade);
					
					ac.setPeriodo(rs.getString("periodo"));				
					
					return ac;
					
				}else								
					return null;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarTodos(AcompanhaCategoria ac){
			try{
				
				ArrayList listaAcompanha = new ArrayList();
				
				PreparedStatement ps;
				ResultSet rs;
				
				String sql;
				sql = "( " +
						 "Select a.codigousuario, a.codigouf, a.codigocidade, a.periodo, a.codigocategoria, a.nivelcategoria, " +
						 "a.data, b.codigousuario As codigoanunciante, b.codigoanuncio, b.titulo, b.descricaoanuncio, b.status, " + 
						 "b.datainicio, b.datafim, c.apelido, d.descricaocategoria, e.descricaocidade " +
						 "From acompanhacategoria a, anuncio b, usuario c, categoria d, cidade e " +
						 "Where a.codigocategoria = b.codigocategoria " +  
						 "And a.nivelcategoria = b.nivelcategoria " +
						 "And b.codigousuario = c.codigousuario " +
						 "And d.codigocategoria = a.codigocategoria " +
						 "And d.nivelcategoria = a.nivelcategoria " +
						 "And b.codigocidade = e.codigocidade " +
						 "And a.codigouf = b.codigouf " +
						 "And a.codigocidade = b.codigocidade " +
						 "And a.codigousuario <> b.codigousuario " +
						 "And a.codigousuario = ? " +
						 "Order By a.codigocategoria " +
						") " +
						"Union " +
						"( " +
						" Select a.codigousuario, a.codigouf, a.codigocidade, a.periodo, a.codigocategoria, a.nivelcategoria,  " +
						" a.data, b.codigousuario As codigoanunciante, b.codigoanuncio, b.titulo, b.descricaoanuncio, b.status,  " + 
						" b.datainicio, b.datafim, c.apelido, d.descricaocategoria, e.descricaocidade " +
						" From acompanhacategoria a, anuncio b, usuario c, categoria d, cidade e " +
						" Where a.codigocategoria = b.codigocategoria " +  
						" And a.nivelcategoria = b.nivelcategoria " +
						" And b.codigousuario = c.codigousuario " +
						" And d.codigocategoria = a.codigocategoria " +
						" And d.nivelcategoria = a.nivelcategoria " +
						" And b.codigocidade = e.codigocidade " +
						" And a.codigouf = b.codigouf " +
						" And a.codigocidade Is Null " +
						" And a.codigousuario <> b.codigousuario " +
						" And a.codigousuario = ? " +
						" Order By a.codigocategoria " +	
						") " +
						"Union " + 
						"( " +
						" Select a.codigousuario, a.codigouf, a.codigocidade, a.periodo, a.codigocategoria, a.nivelcategoria,  " +
						" a.data, b.codigousuario As codigoanunciante, b.codigoanuncio, b.titulo, b.descricaoanuncio, b.status, " + 
						" b.datainicio, b.datafim, c.apelido, d.descricaocategoria, e.descricaocidade " +
						" From acompanhacategoria a, anuncio b, usuario c, categoria d, cidade e " +
						" Where a.codigocategoria = b.codigocategoria " +  
						" And a.nivelcategoria = b.nivelcategoria " +
						" And b.codigousuario = c.codigousuario " +
						" And d.codigocategoria = a.codigocategoria " +
						" And d.nivelcategoria = a.nivelcategoria " +
						" And b.codigocidade = e.codigocidade " +
						" And a.codigocidade Is Null " + 
						" And a.codigouf Is Null " +
						" And a.codigousuario <> b.codigousuario " +
						" And a.codigousuario = ? " +
						" Order By a.codigocategoria " +
						")";				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ac.getUsuario().getCodigoUsuario());
				ps.setInt(2,ac.getUsuario().getCodigoUsuario());
				ps.setInt(3,ac.getUsuario().getCodigoUsuario());
				rs = ps.executeQuery();
				
				while(rs.next()){
					
					AcompanhaCategoria acomp = new AcompanhaCategoria();
					
					acomp.setData(rs.getTimestamp("data"));
					acomp.setPeriodo(rs.getString("periodo"));
					
					Usuario u = new Usuario();
					u.setCodigoUsuario(rs.getInt("codigousuario"));
					u.setApelido("apelido");
					acomp.setUsuario(u);
					
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
					a.setTitulo(rs.getString("titulo"));
					a.setDescricaoAnuncio(rs.getString("descricaoanuncio"));
					a.setStatus(rs.getString("status"));
					a.setDataInicio(rs.getTimestamp("datainicio"));
					a.setDataFim(rs.getTimestamp("datafim"));
					Usuario u2 = new Usuario();
					u2.setCodigoUsuario(rs.getInt("codigoanunciante"));
					a.setUsuario(u2);
					acomp.setAnuncio(a);					
					
					Categoria c = new Categoria();
					c.setCodigoCategoria(rs.getInt("codigocategoria"));
					c.setNivelCategoria(rs.getInt("nivelcategoria"));
					c.setDescricaoCategoria(rs.getString("descricaocategoria"));
					acomp.setCategoria(c);
					
					Uf uf = new Uf();
					uf.setCodigoUf(rs.getString("codigouf"));
					uf.setDescricaoUf("descricaouf");
					ac.setUf(uf);
					
					Cidade cidade = new Cidade();
					cidade.setCodigoCidade(rs.getInt("codigocidade"));
					cidade.setDescricaoCidade(rs.getString("descricaocidade"));
					acomp.setCidade(cidade);
					
					acomp.setPeriodo(rs.getString("periodo"));	
					
					listaAcompanha.add(acomp);
					
				}
								
				return listaAcompanha;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		

		public ArrayList consultarTodos(){
			try{
				
				ArrayList listaAcompanha = new ArrayList();
				
				PreparedStatement ps;
				ResultSet rs;
				
				String sql;
				sql = "Select  a.data, " +
					  "a.periodo," +
					  "b.codigousuario, " +
					  "b.apelido," +
					  "c.codigoanuncio," +
					  "c.titulo," +
					  "c.descricaoanuncio," +
					  "c.status," +
					  "c.datainicio," +
					  "c.datafim," +
					  "d.codigocategoria," +
					  "d.nivelcategoria," +
					  "e.codigouf," +
					  "e.descricaouf," +
					  "f.codigocidade," +
					  "f.descricaocidade " +
					  "From acompanhacategoria a Left Join usuario b On a.codigousuario = b.codigousuario " +
					  "Left Join anuncio c On a.codigoanuncio = c.codigoanuncio Left Join categoria d " +
					  "On a.codigocategoria = d.codigocategoria And a.nivelcategoria = d.nivelcategoria Left Join " +
					  "uf e On e.codigouf = a.codigouf Left Join cidade f On a.codigocidade = f.codigocidade" +
					  "Where codigousuario=? " +
					  "And codigoanuncio=? " +					  
					  "Order By d.codigocategoria";				
				ps = conn.prepareStatement(sql);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
						
				}
				
				
				return listaAcompanha;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		
		
		
}
