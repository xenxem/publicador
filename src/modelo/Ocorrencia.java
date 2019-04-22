package modelo;

import java.sql.Timestamp;

public class Ocorrencia {
	
	int codigoOcorrencia;
	Timestamp data;
	String tipoOcorrencia;
	Anuncio anuncio;
	public int getCodigoOcorrencia() {
		return codigoOcorrencia;
	}
	public void setCodigoOcorrencia(int codigoOcorrencia) {
		this.codigoOcorrencia = codigoOcorrencia;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public String getTipoOcorrencia() {
		return tipoOcorrencia;
	}
	public void setTipoOcorrencia(String tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	
	
}
