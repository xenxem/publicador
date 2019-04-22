package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import modelo.Anuncio;
import modelo.LiberaAnuncio;
import modelo.Pagamento;
import modelo.Usuario;
import modelo.Valor;

public class DAORelatorioLibBloqPSQL implements DAORelatorioLibBloq{

	public ArrayList consultar(String tipo, String gestor, String inicio, String fim){
		
		try{
			
			Connection conn = DAOFactoryPSQL.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			String queryP = "Select nome, data, tipo, descricaoanuncio "+
						   "From anuncio a, usuario u, liberaranuncio l "+
						   "Where ";
			
			String query = queryP + "a.codigoanuncio = l.codigoanuncio "+
						   "And u.codigousuario = l.codigousuario "+
						   "Group By nome, data, tipo, descricaoanuncio "+
			               "Order By data";
			
			ps = conn.prepareStatement(query);
			
			if (!tipo.equals("onon") && !tipo.equals("nullnull")){
				
				
				if (tipo.equals("onnull")){
					//LIBERACAO
					if (gestor.equals("Gestor") && (inicio.length() == 0)){
						
						query = queryP + "tipo = 'L' "+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
					//LIBERACAO + GESTOR
					}else if (!gestor.equals("Gestor") && (inicio.length() == 0)){
						
						query = queryP + "tipo = 'L' "+
										"And u.codigousuario = ? "+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
						ps.setInt(1, Integer.parseInt(gestor));
					//LIBERACAO + PERIODO	
					}else if (gestor.equals("Gestor") && (inicio.length() != 0 && fim.length() != 0)){
						
						query = queryP + "tipo = 'L' "+
										"And data >= ? And data < ?"+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						
						String diaInicio = inicio.substring(0,2);
						String mesInicio = inicio.substring(3,5);
						String anoInicio = inicio.substring(6,10);
						
						String diaFim = fim.substring(0,2);
						String mesFim = fim.substring(3,5);
						String anoFim = fim.substring(6,10);
						
						ps = conn.prepareStatement(query);
						ps.setTimestamp(1, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
						
						int dFim = Integer.parseInt(diaFim) + 1;
						
						if (dFim < 10)
							diaFim = "0"+dFim;
						else
							diaFim = ""+dFim;
						
						ps.setTimestamp(2, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
					}	
					//LIBERACAO + GESTOR + PERÍODO
					else if (!gestor.equals("Gestor") && (inicio.length() != 0 && fim.length() != 0)){
						
						query = queryP + "tipo = 'L' "+
										"And u.codigousuario = ? "+
										"And data >= ? And data < ?"+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
						ps.setInt(1, Integer.parseInt(gestor));
						
						String diaInicio = inicio.substring(0,2);
						String mesInicio = inicio.substring(3,5);
						String anoInicio = inicio.substring(6,10);
						
						String diaFim = fim.substring(0,2);
						String mesFim = fim.substring(3,5);
						String anoFim = fim.substring(6,10);
						
						ps.setTimestamp(2, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
						
						int dFim = Integer.parseInt(diaFim) + 1;
						
						if (dFim < 10)
							diaFim = "0"+dFim;
						else
							diaFim = ""+dFim;
						
						ps.setTimestamp(3, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
									
					}
					
				}else{
					//BLOQUEIO
				    if (gestor.equals("Gestor") && (inicio.length() == 0)){
						
						query = queryP + "tipo = 'B' "+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
					//BLOQUEIO + GESTOR
					}else if (!gestor.equals("Gestor") && (inicio.length() == 0)){
						
						query = queryP + "tipo = 'B' "+
										"And u.codigousuario = ? "+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
						ps.setInt(1, Integer.parseInt(gestor));
					//BLOQUEIO + PERIODO	
					}else if (gestor.equals("Gestor") && (inicio.length() != 0 && fim.length() != 0)){
						
						query = queryP + "tipo = 'B' "+
										"And data >= ? And data < ?"+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						
						String diaInicio = inicio.substring(0,2);
						String mesInicio = inicio.substring(3,5);
						String anoInicio = inicio.substring(6,10);
						
						String diaFim = fim.substring(0,2);
						String mesFim = fim.substring(3,5);
						String anoFim = fim.substring(6,10);
						
						ps = conn.prepareStatement(query);
						ps.setTimestamp(1, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
						
						int dFim = Integer.parseInt(diaFim) + 1;
						
						if (dFim < 10)
							diaFim = "0"+dFim;
						else
							diaFim = ""+dFim;
						
						ps.setTimestamp(2, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
					//BLOQUEIO + GESTOR + PERÍODO
					}else if (!gestor.equals("Gestor") && (inicio.length() != 0 && fim.length() != 0)){
						
						query = queryP + "tipo = 'B' "+
										"And u.codigousuario = ? "+
										"And data >= ? And data < ?"+
										"And a.codigoanuncio = l.codigoanuncio "+
						                "And u.codigousuario = l.codigousuario "+
						                "Group By nome, data, tipo, descricaoanuncio "+
						                "Order By data";
						ps = conn.prepareStatement(query);
						ps.setInt(1, Integer.parseInt(gestor));
						
						String diaInicio = inicio.substring(0,2);
						String mesInicio = inicio.substring(3,5);
						String anoInicio = inicio.substring(6,10);
						
						String diaFim = fim.substring(0,2);
						String mesFim = fim.substring(3,5);
						String anoFim = fim.substring(6,10);
						
						ps.setTimestamp(2, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
						
						int dFim = Integer.parseInt(diaFim) + 1;
						
						if (dFim < 10)
							diaFim = "0"+dFim;
						else
							diaFim = ""+dFim;
						
						ps.setTimestamp(3, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
									
					}
				}
			}else{
				//GESTOR
				if (!gestor.equals("Gestor") && (inicio.length() == 0)){
					
					query = queryP + "u.codigousuario = ? "+
									 "And a.codigoanuncio = l.codigoanuncio "+
					                 "And u.codigousuario = l.codigousuario "+
					                 "Group By nome, data, tipo, descricaoanuncio "+
					                 "Order By data";
					ps = conn.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(gestor));
				//PERIODO	
				}else if (gestor.equals("Gestor") && (inicio.length() != 0)){
					
					query = queryP + "data >= ? And data < ?"+
									 "And a.codigoanuncio = l.codigoanuncio "+
					                 "And u.codigousuario = l.codigousuario "+
					                 "Group By nome, data, tipo, descricaoanuncio "+
					                 "Order By data";
	
					String diaInicio = inicio.substring(0,2);
					String mesInicio = inicio.substring(3,5);
					String anoInicio = inicio.substring(6,10);
					
					String diaFim = fim.substring(0,2);
					String mesFim = fim.substring(3,5);
					String anoFim = fim.substring(6,10);
					
					ps = conn.prepareStatement(query);
					ps.setTimestamp(1, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
					
					int dFim = Integer.parseInt(diaFim) + 1;
					
					if (dFim < 10)
						diaFim = "0"+dFim;
					else
						diaFim = ""+dFim;
					
					ps.setTimestamp(2, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
				//GESTOR + PERIODO	
				}else if (!gestor.equals("Gestor") && (inicio.length() != 0)){
					
					query = queryP + "u.codigousuario = ? "+ 
									 "And data >= ? And data < ?"+
									 "And a.codigoanuncio = l.codigoanuncio "+
					                 "And u.codigousuario = l.codigousuario "+
					                 "Group By nome, data, tipo, descricaoanuncio "+
					                 "Order By data";

					String diaInicio = inicio.substring(0,2);
					String mesInicio = inicio.substring(3,5);
					String anoInicio = inicio.substring(6,10);
					
					String diaFim = fim.substring(0,2);
					String mesFim = fim.substring(3,5);
					String anoFim = fim.substring(6,10);
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(gestor));
					ps.setTimestamp(2, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
					
					int dFim = Integer.parseInt(diaFim) + 1;
					
					if (dFim < 10)
						diaFim = "0"+dFim;
					else
						diaFim = ""+dFim;
					
					ps.setTimestamp(3, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
					
				}
				
			}

			rs = ps.executeQuery();
			
			ArrayList relLibBloq = new ArrayList();
			
			while (rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				
				Anuncio anuncio = new Anuncio();
				anuncio.setDescricaoAnuncio(rs.getString("descricaoanuncio"));
				
				LiberaAnuncio liberaAnuncio = new LiberaAnuncio();
				
				liberaAnuncio.setData(rs.getTimestamp("data"));
				liberaAnuncio.setTipo(rs.getString("tipo"));
				liberaAnuncio.setAnuncio(anuncio);
				liberaAnuncio.setUsuario(usuario);
				if (inicio.length() != 0)
					liberaAnuncio.setPeriodo(inicio+" a "+fim);
				else
					liberaAnuncio.setPeriodo("Não definido");
				relLibBloq.add(liberaAnuncio);
				
			}
			
			return relLibBloq;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Falha em consulta relatorio liberação/bloqueio");
			return null;
		}
	}
	
	public ArrayList consultarGestor(){
		
		ArrayList listaGestor = new ArrayList();
		
		try{	
		
			PreparedStatement ps = DAOFactoryPSQL.getConnection().
			prepareStatement("Select codigousuario, nome From usuario Where perfil = 'G' Order By nome Asc");			
		
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
			
				Usuario gestor = new Usuario();
			
				gestor.setCodigoUsuario(rs.getInt("codigousuario"));
				gestor.setNome(rs.getString("nome"));
				
				listaGestor.add(gestor);
			}
			
			return listaGestor;
			
		}catch (Exception e){				
			e.printStackTrace();
			System.out.println("Falha em consultar gestor");
			return null;
		}			
	}
	
}
