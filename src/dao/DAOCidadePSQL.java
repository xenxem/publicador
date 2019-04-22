package dao;
import modelo.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOCidadePSQL implements DAOCidade{
		
		public void cadastrar(Cidade c){
			
		  try{
			  
		  
			PreparedStatement ps = DAOFactoryPSQL.getConnection().prepareStatement("");
		
			ps.executeUpdate();
			
		  }catch(Exception e){
			  
		  }
		  
		}
		
		public void excluir(Cidade c){
			try{
				
				
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Delete From cidade where=codigocidade=?");
			ps.setInt(1,c.getCodigoCidade());
			ps.executeUpdate();
		
			}catch(Exception e){
				
			}
			
		}
		
		public void alterar(Cidade c){
			
			try{
				
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update cidade set " +
					"descricaocidade=?" +
					"Where codigocidade=?");
			ps.setString(1,c.getDescricaoCidade());
			ps.setInt(2,c.getCodigoCidade());
			ps.executeUpdate();
			
			}catch(Exception e){
				
			}
				
		}
		
		public Cidade consultar(Cidade c){
			
			try{
				
			
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From cidade " +
						"Where codigocidade=?");
				ps.setInt(1,c.getCodigoCidade());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){				
					c.setCodigoCidade(rs.getInt("codigocidade"));
					c.setDescricaoCidade(rs.getString("descricaocidade"));
					Uf uf = new Uf();
					uf.setCodigoUf(rs.getString("codigouf"));
					c.setUf(uf);					
					return c;
				}else
					return null;
				
			
			}catch(Exception e){
				return null;
			}
		}
		
		public ArrayList consultarUfCidade(Cidade c){
			try{
				
			
			ArrayList listaCidade = new ArrayList();
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From cidade Where codigouf=?");
			ps.setString(1, c.getUf().getCodigoUf().trim());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Cidade c2 = new Cidade();				
				c2.setCodigoCidade(rs.getInt("codigocidade"));
				c2.setDescricaoCidade(rs.getString("descricaocidade"));
				Uf uf = new Uf();
				uf.setCodigoUf(rs.getString("codigouf"));
				c2.setUf(uf);				
				listaCidade.add(c2);				
			}			
			
			return listaCidade;
			
			}catch(Exception e){
				System.out.println("erro consultar todos cidade");
				return null;
			}
		}
		
		public ArrayList consultarTodos(){
			try{
				
			
			ArrayList listaCidade = new ArrayList();
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From cidade Order By descricaocidade");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Cidade c2 = new Cidade();				
				c2.setCodigoCidade(rs.getInt("codigocidade"));
				c2.setDescricaoCidade(rs.getString("descricaocidade"));
				Uf uf = new Uf();
				uf.setCodigoUf(rs.getString("codigouf"));
				c2.setUf(uf);				
				listaCidade.add(c2);				
			}			
			
			return listaCidade;
			
			}catch(Exception e){
				System.out.println("erro consultar todos");
				return null;
			}
		}
		
}