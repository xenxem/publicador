package dao;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class DAOPagamentoPSQL implements DAOPagamento{
		
		public void cadastrar(Pagamento p){
			try{
				
								
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Insert Into pagamento" +
						"(numeroparaidentificacao," +
						"codigoanuncio," +
						"codigovalor," +
						"data," +
						"codigoconta," +
						"valor)" +
						"Values(?,?,?,?,?,?)");
				ps.setInt(1,p.getNumeroParaIdentificacao());
				ps.setInt(2,p.getAnuncio().getCodigoAnuncio());
				ps.setInt(3,p.getValor().getCodigoValor());
				ps.setTimestamp(4,p.getData());
				ps.setInt(5,p.getConta().getCodigoConta());
				ps.setDouble(6,p.getValor().getValor());				
				
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		
		public void excluir(Pagamento p){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Delete From pagamento " +
						"Where codigopagamento=?");
				ps.setInt(1,p.getCodigoPagamento());
				
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		
		public void alterar(Pagamento p){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Update pagamento " +
						"Set numeroparaidentificacao=?," +
						"codigoanuncio=?," +
						"codigovalor=?," +
						"data=?," +
						"codigoconta=?," +
						"valor=?" +
						" Where codigopagamento=?");
				ps.setInt(1,p.getNumeroParaIdentificacao());
				ps.setInt(2,p.getAnuncio().getCodigoAnuncio());
				ps.setInt(3,p.getValor().getCodigoValor());
				ps.setTimestamp(4,p.getData());
				ps.setInt(5,p.getConta().getCodigoConta());
				ps.setDouble(6,p.getValor().getValor());
				ps.setFloat(7,p.getCodigoPagamento());
				ps.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();				
			}
				
		}
		
		public Pagamento consultar(Pagamento p){
		try{	
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From pagamento Where codigopagamento=?");
				ps.setInt(1, p.getCodigoPagamento());
				ResultSet r = ps.executeQuery();
				
				if(r.next()){					
					p.setCodigoPagamento(r.getInt("codigopagamento"));
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(r.getInt("codigoanuncio"));
					p.setAnuncio(a);
					
					Conta c = new Conta();
					c.setCodigoConta(r.getInt("codigoconta"));
					p.setConta(c);
					
					Valor v = new Valor();
					v.setCodigoValor(r.getInt("codigovalor"));
					
					p.setValor(v);
					p.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
					p.setData(r.getTimestamp("data"));
					
					v.setValor(r.getFloat("valor"));
					p.setValor(v);
										
				}
				return p;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
				
		}
		
		public ArrayList consultarTodos(){
			try{	
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select * From pagamento");				
				ResultSet r = ps.executeQuery();
				
				ArrayList listaPagamento = new ArrayList();
				
				while(r.next()){
					Pagamento p = new Pagamento();
					
					p.setCodigoPagamento(r.getInt("codigopagamento"));
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(r.getInt("codigoanuncio"));
					p.setAnuncio(a);
					
					Conta c = new Conta();
					c.setCodigoConta(r.getInt("codigoconta"));
					p.setConta(c);
					
					Valor v = new Valor();
					v.setCodigoValor(r.getInt("codigovalor"));
					
					p.setValor(v);
					p.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
					p.setData(r.getTimestamp("data"));
					
					v.setValor(r.getFloat("valor"));
					
					listaPagamento.add(p);
										
				}
				return listaPagamento;
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}	
		}
		
		//marcelo
		public Pagamento ConsultarValor(Anuncio a){
			
			Pagamento p = new Pagamento();
			
			try{
				
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("Select numeroparaidentificacao, codigovalor From pagamento Where codigoanuncio=?");
				ps.setInt(1, a.getCodigoAnuncio());
				ResultSet r = ps.executeQuery();
				
				if(r.next()){					
									
					p.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
					
					Valor v = new Valor();
					v.setCodigoValor(r.getInt("codigovalor"));
					
					p.setValor(v);
										
				}
				
				return p;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
			
		}
		
		public ArrayList consultarTodos(Pagamento p){
			try{
				PreparedStatement ps = DAOFactoryPSQL.getConnection().
				prepareStatement("" +
						"Select " +
						"a.*, " +
						"b.*, " +
						"c.*, " +
						"d.*, " +
						"e.titulo, " +
						"e.descricaoanuncio, " +						
						"e.status As a_status, " +
						"e.datainicio," +
						"e.datafim " + 
						"From banco a, conta b, pagamento c, valor d, anuncio e " +
						"Where a.codigobanco = b.codigobanco " +
						"And c.codigoconta = b.codigoconta " +
						"And c.codigovalor = d.codigovalor " +
						"And c.codigoanuncio = e.codigoanuncio " +
						"And e.codigousuario = ?");	
				ps.setInt(1,p.getAnuncio().getUsuario().getCodigoUsuario());
				ResultSet r = ps.executeQuery();
				
				ArrayList listaPagamento = new ArrayList();
				
				while(r.next()){
					
					
					Pagamento p2 = new Pagamento();
					
					p2.setCodigoPagamento(r.getInt("codigopagamento"));
					p2.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
					p2.setData(r.getTimestamp("data"));
					
					Anuncio a = new Anuncio();
					a.setCodigoAnuncio(r.getInt("codigoanuncio"));
					a.setTitulo(r.getString("titulo"));
					a.setDescricaoAnuncio(r.getString("descricaoanuncio"));
					a.setStatus(r.getString("a_status"));
					a.setDataInicio(r.getTimestamp("datainicio"));
					a.setDataFim(r.getTimestamp("datafim"));					
					p2.setAnuncio(a);
					
					Conta c = new Conta();
					c.setCodigoConta(r.getInt("codigoconta"));
					c.setNumeroConta(r.getString("numeroconta"));
					c.setAgencia(r.getString("agencia"));
					c.setStatus(r.getString("status"));					
					Banco b = new Banco();
					b.setCodigoBanco(r.getInt("codigobanco"));
					b.setDescricaoBanco(r.getString("descricaobanco"));
					b.setNumero(r.getString("numero"));
					c.setBanco(b);
					p2.setConta(c);
					
					Valor v = new Valor();
					v.setCodigoValor(r.getInt("codigovalor"));
					v.setStatus(r.getString("status"));
					v.setValor(r.getFloat("valor"));
					p2.setValor(v);				
									
					listaPagamento.add(p2);
										
				}
				return listaPagamento;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public Pagamento consultarPagamentoGerado(Pagamento p){			
				try{	
						PreparedStatement ps = DAOFactoryPSQL.getConnection().
						prepareStatement("" +
								"Select b.titulo, " +
								"c.descricaobanco, " +
								"d.agencia, " +
								"d.numeroconta," +
								"a.numeroparaidentificacao, " +
								"e.valor," +
								"a.codigopagamento, " +
								"Max(a.data) As data " +
								"From pagamento a, anuncio b, banco c, conta d, valor e " +
								"Where a.codigoanuncio=? " +
								"And a.codigoanuncio = b.codigoanuncio " +
								"And a.codigoconta = d.codigoconta " +
								"And a.codigovalor = e.codigovalor " +
								"And c.codigobanco = d.codigobanco " +
								"Group By b.titulo, c.descricaobanco, d.agencia, d.numeroconta, e.valor," +
								"a.numeroparaidentificacao, a.codigopagamento");
						ps.setInt(1, p.getAnuncio().getCodigoAnuncio());
						ResultSet r = ps.executeQuery();
						
						Pagamento p2 = new Pagamento();
						
						if(r.next()){							
							
							p2.setCodigoPagamento(r.getInt("codigopagamento"));
							Anuncio a = new Anuncio();							
							a.setTitulo(r.getString("titulo"));
							p2.setAnuncio(a);							
							
							Conta c = new Conta();							
							c.setAgencia(r.getString("agencia"));
							c.setNumeroConta(r.getString("numeroconta"));
							
							Banco b = new Banco();
							b.setDescricaoBanco(r.getString("descricaobanco"));		
							c.setBanco(b);
							p2.setConta(c);						
							
							p2.setNumeroParaIdentificacao(r.getInt("numeroparaidentificacao"));
							p2.setData(r.getTimestamp("data"));
							Valor valor = new Valor();
							valor.setValor(r.getFloat("valor"));
							p2.setValor(valor);		
												
						}
						return p2;
						
					}catch(Exception e){
						e.printStackTrace();
						return null;
					}			
				
		}
		
}
