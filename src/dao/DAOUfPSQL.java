package dao;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOUfPSQL implements DAOUf{
		
		public void cadastrar(Uf u){
			try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().prepareStatement("");
		
			ps.executeUpdate();
			
			}catch(Exception e){
				
			}
		}
		
		public void excluir(Uf u){
			
		}
		
		public void alterar(Uf u){
				
		}
		
		public Uf consultar(Uf u){			
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From uf " +					
								"Where codigouf=?");				
				ps.setString(1, u.getCodigoUf());			
				ResultSet rs = ps.executeQuery();
			
				if(rs.next()){					
					u.setDescricaoUf(rs.getString("descricaouf"));					
				}
				
				return u;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}				
		}
		
		public ArrayList consultarTodos(){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * from uf Order By descricaouf");
				ResultSet rs = ps.executeQuery();
				
				ArrayList listaUf = new ArrayList();
				
				while(rs.next()){
					Uf u = new Uf();					
					u.setCodigoUf(rs.getString("codigouf"));
					u.setDescricaoUf(rs.getString("descricaouf"));
					listaUf.add(u);					
				}
				
				return listaUf;
				
			}catch(Exception e){
				System.out.println("falha em constultar todos uf");
				return null;
			}
				
		}
		
}

