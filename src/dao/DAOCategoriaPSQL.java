package dao;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOCategoriaPSQL implements DAOCategoria{
		
		public void cadastrar(Categoria c){
		try{	
				Connection conn = DAOFactoryPSQL.getConnection();
				PreparedStatement ps;
				ResultSet r;
				
				//gravação de categoria filha
				if(c.getCodigoCategoria()!=0){					
					 
					
					//nivel categoria filha
					int nivel = (c.getNivelCategoria()+1);					
				
					//última ordem da filha
					ps = conn.prepareStatement("Select Max(ordemexibicao) As ordem From categoria " +
							"Where codigocategoriapai=?" +
							"And nivelcategoriapai=? ");
					ps.setInt(1,c.getCodigoCategoria());
					ps.setInt(2,c.getNivelCategoria());					
					r = ps.executeQuery();
					
					int ordem=0;
					//ordem de exibição
					
					if(r.next()){
						ordem = (r.getInt("ordem")+1);
					}
					
					
					ps = conn.
					prepareStatement("Insert into categoria" +
									 "(nivelcategoria,descricaocategoria,ordemexibicao," +
									 "codigocategoriapai,nivelcategoriapai)" +
									 "values(?,?,?,?,?)");				
					ps.setInt(1, nivel);
					ps.setString(2, c.getDescricaoCategoria());
					ps.setInt(3, ordem);
					ps.setInt(4, c.getCodigoCategoria());
					ps.setInt(5, c.getNivelCategoria());
				
				}else{		
					
					//última ordem do nível raiz
					ps = conn.prepareStatement("Select max(ordemexibicao) As ordem From categoria");										
					r = ps.executeQuery();
					
					
					//ordem de exibição
					if(r.next()){
						c.setOrdemExibicao(r.getInt("ordem")+1);
					}
					
					
					ps = conn.
					prepareStatement("Insert into categoria" +
									 "(nivelcategoria,descricaocategoria,ordemexibicao)" +
									 "values(?,?,?)");					
					ps.setInt(1, c.getNivelCategoria());
					ps.setString(2, c.getDescricaoCategoria());
					ps.setInt(3,c.getOrdemExibicao());					
				}
			
				ps.executeUpdate();
				
				
				//deletando todos os registro de categoria ordenação
				PreparedStatement ps2 = DAOFactoryPSQL.getConnection().
				prepareStatement("Delete From categoriaordenacao");				
				ps2.executeUpdate();
								
				//ordenando categorias
				Categoria c2 = new Categoria();				
				c2.setCodigoCategoriaPai(0);
				c2.setNivelCategoriaPai(0);				
				ordenarCategoria(c2);
				
			}catch(Exception e){
				System.out.println("falha em cadastrar categoria");
				e.printStackTrace();
			}
		}
		
		public void excluir(Categoria c){
		try{
			Connection conn = DAOFactoryPSQL.getConnection();
			try{
				
				PreparedStatement ps;
				
				conn.setAutoCommit(false);
				
				ps = conn.
				prepareStatement("Delete From categoria Where codigocategoria=? And nivelcategoria=?");
				ps.setInt(1, c.getCodigoCategoria());
				ps.setInt(2, c.getNivelCategoria());				
				
				ps.executeUpdate();
				
				ps = conn.
				prepareStatement("Delete From categoriaordenacao Where codigocategoria=? And nivelcategoria=?");
				ps.setInt(1, c.getCodigoCategoria());
				ps.setInt(2, c.getNivelCategoria());				
				
				ps.executeUpdate();
				
				conn.commit();
				
			}catch(SQLException e){
				conn.rollback();
				System.out.println("falha em excluir categoria");
				e.printStackTrace();
			}
		  }catch(Exception e){
			e.printStackTrace();	
		  }			
		}
		
		public void alterar(Categoria c){
		try{
			Connection conn = DAOFactoryPSQL.getConnection();
			
			try{
				
				PreparedStatement ps;
			
				conn.setAutoCommit(false);
				
				ps = conn.prepareStatement("Update categoria set descricaocategoria=?" +
						"Where codigocategoria=? And nivelcategoria=?");
				ps.setString(1, c.getDescricaoCategoria());
				ps.setInt(2, c.getCodigoCategoria());
				ps.setInt(3, c.getNivelCategoria());
				ps.executeUpdate();
				
				ps = conn.prepareStatement("Update categoriaordenacao set descricaocategoria=?" +
				"Where codigocategoria=? And nivelcategoria=?");
				ps.setString(1, c.getDescricaoCategoria());
				ps.setInt(2, c.getCodigoCategoria());
				ps.setInt(3, c.getNivelCategoria());
				ps.executeUpdate();
				
				conn.commit();
				
			}catch(SQLException e){
				conn.rollback();
				System.out.println("falha em alterar categoria");
				e.printStackTrace();
			}
		  }catch(Exception e){
			e.printStackTrace();
		  }
		}
		
		public Categoria consultar(Categoria c){
			try{	
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("select * from categoria where  codigocategoria=?");
					
					ps.setInt(1, c.getCodigoCategoria());					
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){
						c.setCodigoCategoria(rs.getInt("codigocategoria"));
						c.setDescricaoCategoria(rs.getString("descricaocategoria"));
						c.setNivelCategoria(rs.getInt("nivelcategoria"));
						c.setCodigoCategoriaPai(rs.getInt("codigocategoriapai"));
						c.setNivelCategoriaPai(rs.getInt("nivelcategoriapai"));
						c.setOrdemExibicao(rs.getInt("ordemexibicao"));					
					}
					
					return c;
					
			}catch(Exception e){
				System.out.println("falha em consultar categoria");
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarPorDescricao(Categoria c){
			try{	
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("Select * " +
							"From categoria " +
							"Where  Upper(descricaocategoria) like ?");
					
					ps.setString(1, "%"+c.getDescricaoCategoria().toUpperCase()+"%");					
					ResultSet rs = ps.executeQuery();
					
					ArrayList listaCategoria = new ArrayList();
					
					while(rs.next()){
						Categoria c2 = new Categoria();
						c2.setCodigoCategoria(rs.getInt("codigocategoria"));
						c2.setDescricaoCategoria(rs.getString("descricaocategoria"));
						c2.setNivelCategoria(rs.getInt("nivelcategoria"));
						c2.setCodigoCategoriaPai(rs.getInt("codigocategoriapai"));
						c2.setNivelCategoriaPai(rs.getInt("nivelcategoriapai"));
						c2.setOrdemExibicao(rs.getInt("ordemexibicao"));
						listaCategoria.add(c2);
					}
					
					return listaCategoria;
					
			}catch(Exception e){
				System.out.println("falha em consultar categoria");
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarTodos(){
			try{
			
				ArrayList listaCategoria = new ArrayList();
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection()
				.prepareStatement("Select * " +
						"From categoriaordenacao " +
						"Order By ordemarvore");
				
				ResultSet rs = ps.executeQuery();					
				
							
				while(rs.next()){
				
					Categoria categoria = new Categoria();
					
					categoria.setCodigoCategoria(rs.getInt("codigocategoria"));
					categoria.setNivelCategoria(rs.getInt("nivelcategoria"));
					categoria.setDescricaoCategoria(rs.getString("descricaocategoria"));
					categoria.setCodigoCategoriaPai(rs.getInt("codigocategoriapai"));
					categoria.setNivelCategoriaPai(rs.getInt("nivelcategoriapai"));
					categoria.setOrdemExibicao(rs.getInt("ordemarvore"));
					
					listaCategoria.add(categoria);				
					
				}			
				
				return listaCategoria;
			
			}catch(Exception e){
				System.out.println("falha em consultar todas as categorias");
				e.printStackTrace();
				return null;
			}
				
		}
		
		
		
		public void ordenarCategoria(Categoria c){
			try{
				
				//pegando uma conexao
				Connection conn = DAOFactoryPSQL.getConnection();
				
				//na primeira vez pega as categorias do nível mais alto		
				PreparedStatement ps = conn.
				prepareStatement("select * from categoria Where coalesce(codigocategoriapai,0)=? And " +
						"coalesce(nivelcategoriapai,0)=?" +
						"Order By ordemexibicao");
				ps.setInt(1, c.getCodigoCategoriaPai());
				ps.setInt(2, c.getNivelCategoriaPai());			
				ResultSet rs = ps.executeQuery();			
				
				while(rs.next()){
				
					Categoria c1 = new Categoria();
					
					c1.setCodigoCategoria(rs.getInt("codigocategoria"));
					c1.setNivelCategoria(rs.getInt("nivelcategoria"));
					c1.setDescricaoCategoria(rs.getString("descricaocategoria"));
					c1.setCodigoCategoriaPai(rs.getInt("codigocategoriapai"));
					c1.setNivelCategoriaPai(rs.getInt("nivelcategoriapai"));
					c1.setOrdemExibicao(rs.getInt("ordemexibicao"));
					
									
					if(c1.getCodigoCategoriaPai()!=0){
						
						//inserindo em categoria ordenação
						PreparedStatement ps2 = conn.
						prepareStatement("Insert into categoriaordenacao" +
										 "(codigocategoria,nivelcategoria,descricaocategoria," +
										 "codigocategoriapai,nivelcategoriapai)" +
										 "values(?,?,?,?,?)");				
						ps2.setInt(1, c1.getCodigoCategoria());
						ps2.setInt(2, c1.getNivelCategoria());
						ps2.setString(3, c1.getDescricaoCategoria());
						ps2.setInt(4, c1.getCodigoCategoriaPai());
						ps2.setInt(5, c1.getNivelCategoriaPai());				
						ps2.executeUpdate();
					
					}else{
						PreparedStatement ps2 = conn.
						prepareStatement("Insert into categoriaordenacao" +
										 "(codigocategoria,nivelcategoria,descricaocategoria)"+
										 "values(?,?,?)");				
						ps2.setInt(1, c1.getCodigoCategoria());
						ps2.setInt(2, c1.getNivelCategoria());
						ps2.setString(3, c1.getDescricaoCategoria());									
						ps2.executeUpdate();
					}
					
					//setando código para os filhos
					Categoria c2 = new Categoria();
					c2.setCodigoCategoriaPai(c1.getCodigoCategoria());
					c2.setNivelCategoriaPai(c1.getNivelCategoria());
					//recursividade
					ordenarCategoria(c2);
				}			
			}catch(Exception e){
				System.out.println("falha em ordernar categoria");
				e.printStackTrace();
			}
		}
		
		public boolean temFilho(Categoria c){
			try{
					
					PreparedStatement ps = DAOFactoryPSQL.getConnection().
					prepareStatement("Select codigocategoria " +
							"From categoria Where codigocategoriapai=? And nivelcategoriapai=?");
					ps.setInt(1, c.getCodigoCategoria());
					ps.setInt(2, c.getNivelCategoria());
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){						
						return true;
					}				
				return false;
				
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean participaDeRelacionamento(Categoria c){
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select codigocategoria " +
						"From anuncio Where codigocategoria=? And nivelcategoria=?");
				ps.setInt(1, c.getCodigoCategoria());
				ps.setInt(2, c.getNivelCategoria());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){					
					return true;
				}
				
				return false;
				
			}catch(Exception e){
				
				e.printStackTrace();
				return true;
			}
			
		}
}
