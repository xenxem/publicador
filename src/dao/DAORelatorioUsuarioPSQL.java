package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Usuario;

public class DAORelatorioUsuarioPSQL implements DAORelatorioUsuario {

	public ArrayList consultar(String perfil, String status){

		try{
			
			Connection conn = DAOFactoryPSQL.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			String query = "Select email, apelido, nome "+
						   "From usuario ";	
			
			if (!perfil.equals("nullnullnull") && !perfil.equals("ononon")){
			
				if (perfil.equals("onnullnull")){
					query = query + "Where perfil = 'U' ";
				}else if (perfil.equals("nullonnull")){
					query = query + "Where perfil = 'G' ";
				}else if (perfil.equals("nullnullon")){
					query = query + "Where perfil = 'D' ";
				}else if (perfil.equals("ononnull")){
					query = query + "Where perfil = 'U' Or perfil = 'G' ";
				}else if (perfil.equals("onnullon")){
					query = query + "Where perfil = 'U' Or perfil = 'D' ";
				}else if (perfil.equals("nullonon")){
					query = query + "Where perfil = 'G' Or perfil = 'D' ";
				}
				
			}	
			
			if (!status.equals("nullnull") && !status.equals("onon")){
				
				if (!perfil.equals("nullnullnull") && !perfil.equals("ononon"))
					query = query + "And ";
				else
					query = query + "Where ";
					
				if (status.equals("onnull")){
					query = query + "statusvalidacao = 'H' ";
				}else if (status.equals("nullon")){
					query = query + "statusvalidacao = 'D' ";
				}else if (status.equals("onon")){
					query = query + "statusvalidacao = 'H' Or statusvalidacao = 'D' ";
				}
				
			}
			
			query = query + "Order By email";
			
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery() ;
			
			ArrayList relUsuario = new ArrayList();
			
			while (rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setNome(rs.getString("nome"));
				
				relUsuario.add(usuario);
				
			}
			
			return relUsuario;
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
