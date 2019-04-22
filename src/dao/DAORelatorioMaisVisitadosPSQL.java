package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Anuncio;
import modelo.Categoria;
import modelo.Usuario;

public class DAORelatorioMaisVisitadosPSQL implements DAORelatorioMaisVisitados{
	
	public ArrayList consultar(Anuncio a, String status){
		try{
			
			Connection conn = DAOFactoryPSQL.getConnection();
			PreparedStatement ps = null;
			ResultSet rs;
			
			String query = "Select descricaocategoria, descricaoanuncio, status, acessos "+
						   "From anuncio a, categoria c "+
						   "Where a.codigocategoria = c.codigocategoria ";	

			if (!status.equals("nullnullnull")){
				if (status.equals("onnullnull")){
					query = query + "And status = 'L' ";
				}else if (status.equals("nullonnull")){
					query = query + "And status = 'P' ";
				}else if (status.equals("nullnullon")){
					query = query + "And status = 'B' ";
				}else if (status.equals("ononnull")){
					query = query + "And status = 'L' Or status = 'P' And a.codigocategoria = c.codigocategoria ";
				}else if (status.equals("onnullon")){
					query = query + "And status = 'L' Or status = 'B' And a.codigocategoria = c.codigocategoria ";
				}else if (status.equals("nullonon")){
					query = query + "And status = 'P' Or status = 'B' And a.codigocategoria = c.codigocategoria ";
				}
			}	
			
			if (a.getCategoria().getCodigoCategoria() != 0 || a.getCidade().getCodigoCidade() != 0 || !a.getUf().getCodigoUf().equals("Estado")){
				
				if (a.getCategoria().getCodigoCategoria() != 0 && a.getCidade().getCodigoCidade() == 0 && a.getUf().getCodigoUf().equals("Estado")){
					
					query = query + "And a.codigocategoria = ? Order By acessos Desc";
					ps = conn.prepareStatement(query);
					ps.setInt(1, a.getCategoria().getCodigoCategoria());
					
				}else 
				if (!a.getUf().getCodigoUf().equals("Estado") && a.getCategoria().getCodigoCategoria() == 0 && a.getCidade().getCodigoCidade() == 0){
					
					query = query + "And codigouf = ? Order By acessos Desc";
					ps = conn.prepareStatement(query);
					ps.setString(1, a.getUf().getCodigoUf());
					
				}else
				if (a.getCategoria().getCodigoCategoria() != 0 && !a.getUf().getCodigoUf().equals("Estado") && a.getCidade().getCodigoCidade() == 0){
					
					query = query + "And a.codigocategoria = ? And codigouf = ? Order By acessos Desc";
					ps = conn.prepareStatement(query);
					ps.setInt(1, a.getCategoria().getCodigoCategoria());
					ps.setString(2, a.getUf().getCodigoUf());
					
				}else
				if (a.getCidade().getCodigoCidade() != 0 && !a.getUf().getCodigoUf().equals("Estado") && a.getCategoria().getCodigoCategoria() == 0){
					
					query = query + "And codigocidade = ? And codigouf = ? Order By acessos Desc";
					ps = conn.prepareStatement(query);
					ps.setInt(1, a.getCidade().getCodigoCidade());
					ps.setString(2, a.getUf().getCodigoUf());
					
				}else
				if (a.getCategoria().getCodigoCategoria() != 0 && a.getCidade().getCodigoCidade() != 0 && !a.getUf().getCodigoUf().equals("Estado")){
					
					query = query + "And a.codigocategoria = ? And codigocidade = ? And codigouf = ? Order By acessos Desc";
					ps = conn.prepareStatement(query);
					ps.setInt(1, a.getCategoria().getCodigoCategoria());
					ps.setInt(2, a.getCidade().getCodigoCidade());
					ps.setString(3, a.getUf().getCodigoUf());
					
				}
					
			}else{ 
				query = query + "Order By acessos Desc";
				ps = conn.prepareStatement(query);
			}
			
			rs = ps.executeQuery() ;
			
			ArrayList relAnuncio = new ArrayList();
			
			while (rs.next()){
				
				Anuncio anuncio = new Anuncio();
				Categoria categoria = new Categoria();
				
				categoria.setDescricaoCategoria(rs.getString("descricaocategoria"));
				
				anuncio.setCategoria(categoria);
				anuncio.setDescricaoAnuncio(rs.getString("descricaoanuncio"));
				anuncio.setStatus(rs.getString("status"));
				anuncio.setAcessos(rs.getInt("acessos"));
				
				relAnuncio.add(anuncio);
				
			}
			
			return relAnuncio;
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}

