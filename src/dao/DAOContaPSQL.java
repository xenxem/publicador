package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class DAOContaPSQL implements DAOConta{
		
		public void cadastrar(Conta c){
			
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Insert Into conta(numeroconta,agencia,codigobanco,status)" +
					"values(?,?,?,?)");			
				ps.setString(1, c.getNumeroConta());
				ps.setString(2, c.getAgencia());
				ps.setInt(3, c.getBanco().getCodigoBanco());
				ps.setString(4, c.getStatus());
			
				ps.executeUpdate();
				
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void excluir(Conta c){
			
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Delete From conta Where codigoconta=?");			
				ps.setInt(1, c.getCodigoConta());
			
				ps.executeUpdate();
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		public void alterar(Conta c){
			
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Update conta set " +					
						"numeroconta=?," +
						"agencia=?," +
						"codigobanco=?, " +
						"status=? " + 
						"Where codigoconta=?");			
				ps.setString(1, c.getNumeroConta());
				ps.setString(2, c.getAgencia());
				ps.setInt(3, c.getBanco().getCodigoBanco());
				ps.setString(4, c.getStatus());
				ps.setInt(5, c.getCodigoConta());
			
				ps.executeUpdate();
				
			}catch (Exception e){
				e.printStackTrace();				
			}
				
		}
		
		public Conta consultar(Conta c){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From conta " +					
								"Where codigoconta=?");			
				ps.setInt(1, c.getCodigoConta());
			
				ResultSet rs = ps.executeQuery();
			
				if(rs.next()){
					c.setCodigoConta(rs.getInt("codigoconta"));
					c.setNumeroConta(rs.getString("numeroconta"));
					c.setAgencia(rs.getString("agencia"));
					
					Banco b = new Banco();
					b.setCodigoBanco(rs.getInt("codigobanco"));
					c.setBanco(b);
					
					c.setStatus(rs.getString("status"));
				}
				return c;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}				
		}
		
		public ArrayList consultarTodos(){
			
			ArrayList listaConta = new ArrayList();
			
			try{	
			
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From conta Order By status Asc");			
			
				ResultSet rs = ps.executeQuery();
			
				while(rs.next()){
				
					Conta c = new Conta();
				
					c.setCodigoConta(rs.getInt("codigoconta"));
					c.setNumeroConta(rs.getString("numeroconta"));
					c.setAgencia(rs.getString("agencia"));
					
					Banco b = new Banco();
					b.setCodigoBanco(rs.getInt("codigobanco"));
					c.setBanco(b);
					
					c.setStatus(rs.getString("status"));
				
					listaConta.add(c);
				}
				
				return listaConta;
				
			}catch (Exception e){				
				e.printStackTrace();
				return null;
			}			
		}
		
		public Conta consultarAtiva(){
			
			Conta c = new Conta();
			
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From conta Where status='A'");			
							
				ResultSet rs = ps.executeQuery();
			
				if(rs.next()){
					c.setCodigoConta(rs.getInt("codigoconta"));
					c.setNumeroConta(rs.getString("numeroconta"));
					c.setAgencia(rs.getString("agencia"));
					
					Banco b = new Banco();
					b.setCodigoBanco(rs.getInt("codigobanco"));
					c.setBanco(b);
					
					c.setStatus(rs.getString("status"));				
				}
				return c;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
				
			}		
		}
		
		
		
}
