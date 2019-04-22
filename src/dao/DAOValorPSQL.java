package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;


public class DAOValorPSQL implements DAOValor{
		
		public void cadastrar(Valor v){
		 try{	
			PreparedStatement ps = DAOFactoryPSQL.
			getConnection().prepareStatement("Insert Into valor(valor,status) values(?,?)");
			ps.setDouble(1, v.getValor());
			ps.setString(2, v.getStatus());
			ps.executeUpdate();			
			
		 }catch(Exception e){
			 e.printStackTrace();			 
		 }
		}
		
		public void excluir(Valor v){
			try{	
				PreparedStatement ps = DAOFactoryPSQL.
				getConnection().prepareStatement("Delete From valor Where codigovalor=?");
				ps.setInt(1, v.getCodigoValor());
				ps.executeUpdate();			
				
			 }catch(Exception e){
				 e.printStackTrace();			 
			 }
		}
		
		public void alterar(Valor v){
			try{	
				PreparedStatement ps = DAOFactoryPSQL.
				getConnection().prepareStatement("Update valor " +
						"Set valor=?, " +
						"status=? "+
						"Where codigovalor=?");
				ps.setDouble(1, v.getValor());
				ps.setString(2, v.getStatus());
				ps.setInt(3, v.getCodigoValor());
				
				ps.executeUpdate();			
				
			 }catch(Exception e){
				 e.printStackTrace();			 
			 }
		}
		
		public Valor consultar(Valor v){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From valor Where codigovalor=?");
				ps.setInt(1, v.getCodigoValor());
				ResultSet r = ps.executeQuery();
				
				if(r.next()){
					
					v.setCodigoValor(r.getInt("codigovalor"));				
					v.setValor(r.getFloat("valor"));
					v.setStatus(r.getString("status"));
					return v;
					
				}else 
					return null;
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
					
		}
		
		public ArrayList consultarTodos(){
			
			ArrayList listaValor = new ArrayList();
			
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From valor");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Valor v = new Valor();
					
					v.setCodigoValor(rs.getInt("codigovalor"));				
					v.setValor(rs.getFloat("valor"));
					v.setStatus(rs.getString("status"));
					
					listaValor.add(v);	
				}	
				
				return listaValor;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		
		}
		
		public Valor consultarAtivo(){
		
			Valor v = new Valor();
			
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From valor Where status='A'");			
							
				ResultSet rs = ps.executeQuery();
			
				if(rs.next()){
					
					v.setCodigoValor(rs.getInt("codigovalor"));
					v.setValor(rs.getFloat("valor"));
					v.setStatus(rs.getString("status"));
									
				}
				
				return v;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
				
			}		
			
		}
		
		
}