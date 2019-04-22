package dao;

import java.util.ArrayList;

import modelo.Anuncio;

public interface DAORelatorioMaisVisitados {
	public ArrayList consultar(Anuncio a, String status);
}
