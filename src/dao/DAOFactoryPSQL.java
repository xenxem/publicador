package dao;
import java.sql.*;

public class DAOFactoryPSQL extends DAOFactory{
	

	/* 
	 * definição de constantes para conexão
	 * 	 
	 */
	public static final String URL = "jdbc:postgresql:publicadoranuncio";
	public static final String USUARIO = "postgres";
	public static final String SENHA = "post";	
	public static final String DRIVER = "org.postgresql.Driver";
	
	/* 
	 * devolvedo uma conexão 
	 * 
	 */
	public static Connection getConnection() {
		try{
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL,USUARIO,SENHA);
		}
		catch (Exception e){
			System.out.println("Falha na conexão de banco" + e.getMessage());
			return null;
		}
	}
	
	public DAOAnuncio getDAOAnuncio(){
        return new DAOAnuncioPSQL();
	}
	
	public DAOCategoria getDAOCategoria(){
        return new DAOCategoriaPSQL();
	}
	
	
	public DAOUf getDAOUf(){
        return new DAOUfPSQL();
	}
	 
	public DAOCidade getDAOCidade(){
        return new DAOCidadePSQL();
	}
	
	     
	public DAOUsuario getDAOUsuario(){
    	return new DAOUsuarioPSQL();
    }
	
	public DAOPagamento getDAOPagamento(){
    	return new DAOPagamentoPSQL();
    }
	
	public DAOOcorrencia getDAOOcorrencia(){
    	return new DAOOcorrenciaPSQL();
    }
	
	public DAOMensagem getDAOMensagem(){
    	return new DAOMensagemPSQL();
    }
	
	public DAOAcompanhaCategoria getDAOAcompanhaCategoria(){
    	return new DAOAcompanhaCategoriaPSQL();
    }
	
	public DAOAnuncioAcompanhamento getDAOAnuncioAcompanhamento(){
    	return new DAOAnuncioAcompanhamentoPSQL();
    }

	
	public DAOConta getDAOConta(){
    	return new DAOContaPSQL();
    }
	
	public DAOValor getDAOValor(){
    	return new DAOValorPSQL();
    }
	
	public DAOImagem getDAOImagem(){
    	return new DAOImagemPSQL();
    }
	
	public DAOBanco getDAOBanco(){
    	return new DAOBancoPSQL();
    }
	
	public DAOLiberarAnuncio getDAOLiberarAnuncio(){
    	return new DAOLiberarAnuncioPSQL();
    }
	
	public DAOAnuncioVencido getDAOAnuncioVencido(){
		return new DAOAnuncioVencidoPSQL();
	}
	
	public DAORelatorioUsuario getDAORelatorioUsuario(){
		return new DAORelatorioUsuarioPSQL();
	}
	
	public DAORelatorioPagamento getDAORelatorioPagamento(){
		return new DAORelatorioPagamentoPSQL();
	}
	
	public DAORelatorioMaisVisitados getDAORelatorioMaisVisitados(){
		return new DAORelatorioMaisVisitadosPSQL();
	}
	
	public DAORelatorioLibBloq getDAORelatorioLibBloq(){
		return new DAORelatorioLibBloqPSQL();
	}
	
}
