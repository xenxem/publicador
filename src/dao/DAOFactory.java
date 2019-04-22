package dao;

public abstract class DAOFactory {

	//definição de constantes	
	public static final int POSTGRESQL = 1;
	public static final int MYSQL = 2;
	public static final int ORACLE = 3;
	
	//métodos abstratos	
	public abstract DAOAnuncio getDAOAnuncio();
	public abstract DAOCategoria getDAOCategoria();		
	public abstract DAOCidade getDAOCidade();
	public abstract DAOImagem getDAOImagem();
	public abstract DAOUsuario getDAOUsuario();
	public abstract DAOPagamento getDAOPagamento();
	public abstract DAOOcorrencia getDAOOcorrencia();
	public abstract DAOAcompanhaCategoria getDAOAcompanhaCategoria();
	public abstract DAOAnuncioAcompanhamento getDAOAnuncioAcompanhamento();
	public abstract DAOMensagem getDAOMensagem();
	public abstract DAOUf getDAOUf();	
	public abstract DAOValor getDAOValor();
	public abstract DAOConta getDAOConta();
	public abstract DAOBanco getDAOBanco();
	public abstract DAOLiberarAnuncio getDAOLiberarAnuncio();
	public abstract DAOAnuncioVencido getDAOAnuncioVencido();
	public abstract DAORelatorioUsuario getDAORelatorioUsuario();
	public abstract DAORelatorioPagamento getDAORelatorioPagamento();
	public abstract DAORelatorioMaisVisitados getDAORelatorioMaisVisitados();
	public abstract DAORelatorioLibBloq getDAORelatorioLibBloq();
	
	//verifica e devolve instância do banco
	public static DAOFactory getDAOFactory (int i){
		switch(i)
		{
			case POSTGRESQL:
			 	return new DAOFactoryPSQL();
			case MYSQL:
				//return new DAOFactoryMySql();
			case ORACLE:
				//return new DAOFactoryOracle;
			default:
				return null; 
		}
	}
	
	
}
