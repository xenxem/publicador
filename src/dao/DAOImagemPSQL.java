package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class DAOImagemPSQL implements DAOImagem{
		
		public void cadastrar(Imagem i){
		try{
			Connection conn = DAOFactoryPSQL.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			ps = conn.prepareStatement("Select Max(ordem) As ordem From imagem Where codigoanuncio=?"); 
			ps.setInt(1,i.getAnuncio().getCodigoAnuncio());
			rs = ps.executeQuery();
			
			int soma=1;
			
			if(rs.next()){
				soma+= rs.getInt("ordem");				
			}		
			
			ps = conn.
			prepareStatement("Insert Into imagem(imgnome,destaque,codigoanuncio,ordem)" +
					"values(?,?,?,?)");			
			ps.setString(1, i.getImgNome());
			ps.setString(2, i.getDestaque());
			ps.setInt(3, i.getAnuncio().getCodigoAnuncio());
			ps.setInt(4, soma);
			ps.executeUpdate();
			
		}catch(Exception e){
			System.out.println("falha em cadastrar imagem");
		}
		}
		
		public void excluir(Imagem i){
		try{	
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Delete From imagem Where codigoimagem=?");			
			ps.setInt(1,i.getCodigoImagem());
					
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("falha em excluir imagem");
		}
			
		}
		
		public void alterar(Imagem i){
		try{
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Update imagem " +
					"Set imgnome =?," +				
					"destaque=?" +
					"Where codigoimagem=?");			
			ps.setString(1,i.getImgNome().trim());			
			ps.setString(2,i.getDestaque());
			ps.setInt(3,i.getCodigoImagem() );
					
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println("falha em alterar imagem");
		}
				
		}
		
		public Imagem consultar(Imagem i){
		try{	
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From imagem " +					
						"Where codigoimagem=?");			
			ps.setInt(1, i.getCodigoImagem());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				i.setCodigoImagem(rs.getInt("codigoimagem"));
				i.setImgNome(rs.getString("imgnome"));
				i.setDestaque(rs.getString("destaque"));
											
			}
			
			return i;
		}catch(Exception e){
			System.out.println("falha em consultar imagem");
			return null;
		}
				
		}
		
		public ArrayList consultarImagemAnuncio(Imagem i){
			try{	
				
				ArrayList listaImagem = new ArrayList();
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From imagem  " +					
							"Where codigoanuncio=? Order By destaque Desc");			
				ps.setInt(1, i.getAnuncio().getCodigoAnuncio());
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){						
					Imagem img = new Imagem();					
					img.setCodigoImagem(rs.getInt("codigoimagem"));
					img.setImgNome(rs.getString("imgnome"));
					img.setDestaque(rs.getString("destaque"));
					
					Anuncio anuncio = new Anuncio();
					anuncio.setCodigoAnuncio(rs.getInt("codigoanuncio"));					
					img.setAnuncio(anuncio);
					
					listaImagem.add(img);		
					
				}
				
				return listaImagem;
				
			}catch(Exception e){
				System.out.println("falha em consultar imagem");
				return null;
			}
					
			}
		
		public ArrayList consultarTodos(){
		try{	
			ArrayList listaImagem = new ArrayList();
			
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select * From imagem");			
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				Imagem i = new Imagem();
				
				i.setCodigoImagem(rs.getInt("codigoimagem"));
				i.setImgNome(rs.getString("imgnome"));			
				i.setDestaque(rs.getString("destaque"));				
				
				listaImagem.add(i);
			}
			
			return listaImagem;
			
		}catch(Exception e){
			System.out.println("falha em consultar todas as imagens");
			return null;
		}
				
		}
		
}
