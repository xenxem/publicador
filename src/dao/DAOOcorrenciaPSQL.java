package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class DAOOcorrenciaPSQL implements DAOOcorrencia{
		
		public void cadastrar(Ocorrencia o){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Insert Into ocorrencia(data,tipoocorrencia,codigoanuncio)" +
						"values(?,?,?)");			
				ps.setTimestamp(1, o.getData());
				ps.setString(2, o.getTipoOcorrencia());
				ps.setInt(3, o.getAnuncio().getCodigoAnuncio());	
				
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void excluir(Ocorrencia o){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Delete From ocorrencia Where codigoocorrencia=?");			
				ps.setString(1,o.getTipoOcorrencia());
						
				ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		public void alterar(Ocorrencia o){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Update  ocorrencia" +
						"Set data =?" +										
						"Where codigoocorrencia=?");			
				ps.setTimestamp(1,o.getData());			
				ps.setInt(2,o.getCodigoOcorrencia());		
						
				ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}
		
		public Ocorrencia consultar(Ocorrencia o){
			try{	
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From ocorrencia " +					
						"Where codigoocorrencia=?");			
			ps.setInt(1, o.getCodigoOcorrencia());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				o.setCodigoOcorrencia(rs.getInt("codigoocorrencia"));
				o.setData(rs.getTimestamp("data"));
				o.setTipoOcorrencia(rs.getString("tipoocorrencia"));
				
				Anuncio a = new Anuncio();
				a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
				o.setAnuncio(a);							
			}
			
			return o;
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarTodos(){
			try{
			
			ArrayList listaOcorrencia = new ArrayList();
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From ocorrencia");			
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Ocorrencia o = new Ocorrencia();
				
				o.setCodigoOcorrencia(rs.getInt("codigoOcorrencia"));
				o.setData(rs.getTimestamp("data"));
				o.setTipoOcorrencia(rs.getString("tipoocorrencia"));
				Anuncio a = new Anuncio();
				a.setCodigoAnuncio(rs.getInt("codigoanuncio"));
				o.setAnuncio(a);				
				
				listaOcorrencia.add(o);
			}
			
			return listaOcorrencia;
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			
				
		}
		
}
