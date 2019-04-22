package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import modelo.Pagamento;
import modelo.Usuario;
import modelo.Valor;

public class DAORelatorioPagamentoPSQL implements DAORelatorioPagamento{

	public ArrayList consultar(String inicio, String fim, String ordenacao){
		
		try{
			
			Connection conn = DAOFactoryPSQL.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			String query = "Select data, numeroparaidentificacao, v.valor "+
						   "From pagamento p, valor v "+
						   "Where p.codigovalor = v.codigovalor ";
						   	
			
			if (inicio.length() != 0 && fim.length() != 0){
				
				if (inicio.equals(fim)){
					
					query = query + "And data >= ? And data < ? ";
					
					String dia = inicio.substring(0,2);
					String mes = inicio.substring(3,5);
					String ano = inicio.substring(6,10);
					
					if (ordenacao.equals("data"))
						query = query + "Order By data";
					else if (ordenacao.equals("idDeposito"))
						query = query + "Order By numeroparaidentificacao";
					else
						query = query + "Order By valor";
					
					ps = conn.prepareStatement(query);
					
					ps.setTimestamp(1, Timestamp.valueOf(ano+"-"+mes+"-"+dia+" 00:00:00"));
					int d = Integer.parseInt(dia) + 1;
					
					if (d < 10)
						dia = "0"+d;
					else
						dia = ""+d;

					ps.setTimestamp(2, Timestamp.valueOf(ano+"-"+mes+"-"+dia+" 00:00:00"));
					
				}else{
					
					query = query + "And data >= ? And data < ? ";
					
					String diaInicio = inicio.substring(0,2);
					String mesInicio = inicio.substring(3,5);
					String anoInicio = inicio.substring(6,10);
					
					String diaFim = fim.substring(0,2);
					String mesFim = fim.substring(3,5);
					String anoFim = fim.substring(6,10);
					
					if (ordenacao.equals("data"))
						query = query + "Order By data";
					else if (ordenacao.equals("idDeposito"))
						query = query + "Order By numeroparaidentificacao";
					else
						query = query + "Order By valor";
					
					ps = conn.prepareStatement(query);
					
					ps.setTimestamp(1, Timestamp.valueOf(anoInicio+"-"+mesInicio+"-"+diaInicio+" 00:00:00"));
					int dFim = Integer.parseInt(diaFim) + 1;
					
					if (dFim < 10)
						diaFim = "0"+dFim;
					else
						diaFim = ""+dFim;
					
					ps.setTimestamp(2, Timestamp.valueOf(anoFim+"-"+mesFim+"-"+diaFim+" 00:00:00"));
					
				}	
				
			}else{	
				
				if (ordenacao.equals("data"))
					query = query + "Order By data";
				else if (ordenacao.equals("idDeposito"))
					query = query + "Order By numeroparaidentificacao";
				else
					query = query + "Order By valor";
				
				ps = conn.prepareStatement(query);
				
			}	
				
			rs = ps.executeQuery();
			
			ArrayList relPagamento = new ArrayList();
			
			while (rs.next()){
				
				Pagamento pagamento = new Pagamento();
				pagamento.setNumeroParaIdentificacao(rs.getInt("numeroparaidentificacao"));
				pagamento.setData(rs.getTimestamp("data"));
				
				Valor valor = new Valor();
				valor.setValor(rs.getFloat("valor"));
				
				pagamento.setValor(valor);
				
				if (inicio.length() != 0)
					pagamento.setPeriodo(inicio+" a "+fim);
				else
					pagamento.setPeriodo("Não definido");
				
				relPagamento.add(pagamento);
				
			}
			
			return relPagamento;
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
