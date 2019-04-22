package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

public class Anuncio {

	int codigoAnuncio;
	String titulo;
	String descricaoAnuncio;
	String status;
	int acessos;
	Timestamp dataInicio;
	Timestamp dataFim;
	Usuario usuario;
	Categoria categoria;
	Uf uf;
	Cidade cidade;
	Vector imagem;
	ArrayList ocorrencia;
	ArrayList pagamento;
	ArrayList mensagem;
	
	Timestamp dataNova;
	
	public Timestamp getDataNova() {
		return dataNova;
	}

	public void setDataNova(Timestamp dataNova) {
		this.dataNova = dataNova;
	}

	public ArrayList getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(ArrayList ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public ArrayList getPagamento() {
		return pagamento;
	}

	public void setPagamento(ArrayList pagamento) {
		this.pagamento = pagamento;
	}

	public ArrayList getMensagem() {
		return mensagem;
	}

	public void setMensagem(ArrayList mensagem) {
		this.mensagem = mensagem;
	}

	public int getCodigoAnuncio() {
		return codigoAnuncio;
	}
	
	public void setCodigoAnuncio(int codigoAnuncio) {
		this.codigoAnuncio = codigoAnuncio;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricaoAnuncio() {
		return descricaoAnuncio;
	}
	
	public void setDescricaoAnuncio(String descricaoAnuncio) {
		this.descricaoAnuncio = descricaoAnuncio;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getAcessos() {
		return acessos;
	}
	
	public void setAcessos(int acessos) {
		this.acessos = acessos;
	}
	
	public Timestamp getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Timestamp getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Timestamp dataFim) {
		this.dataFim = dataFim;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Uf getUf() {
		return uf;
	}
	
	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Vector getImagem() {
		return imagem;
	}

	public void setImagem(Vector imagem) {
		this.imagem = imagem;
	}	
	
}
