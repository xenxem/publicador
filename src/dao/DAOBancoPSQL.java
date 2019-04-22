package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Banco;

public class DAOBancoPSQL implements DAOBanco{
	
	public void cadastrar(Banco b){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Insert Into banco(numero,descricaobanco)" +
				"values(?,?)");			
			ps.setString(1, b.getNumero());
			ps.setString(2, b.getDescricaoBanco());
		
			ps.executeUpdate();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void excluir(Banco b){
		
		try{
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Delete From banco Where codigobanco=?");			
			ps.setInt(1, b.getCodigoBanco());
		
			ps.executeUpdate();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void alterar(Banco b){
		
		try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update banco set " +					
					"numero=?," +
					"descricaobanco=?" +
					"Where codigobanco=?");			
			ps.setString(1, b.getNumero());
			ps.setString(2, b.getDescricaoBanco());
			ps.setInt(3, b.getCodigoBanco());
		
			ps.executeUpdate();
			
		}catch (Exception e){
			e.printStackTrace();
		}
			
	}
	
	public Banco consultar(Banco b){
		
		try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From banco " +					
							"Where codigobanco=?");			
			ps.setInt(1, b.getCodigoBanco());
		
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()){
				b.setCodigoBanco(rs.getInt("codigobanco"));
				b.setNumero(rs.getString("numero"));
				b.setDescricaoBanco(rs.getString("descricaobanco"));
			}
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return b;
			
	}
	
	public ArrayList consultarTodos(){
		
		ArrayList listaBanco = new ArrayList();
		
		try{	
		
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From banco");			
		
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
			
				Banco b = new Banco();
			
				b.setCodigoBanco(rs.getInt("codigobanco"));
				b.setNumero(rs.getString("numero"));
				b.setDescricaoBanco(rs.getString("descricaobanco"));
			
				listaBanco.add(b);
			}
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return listaBanco;
			
	}
}
