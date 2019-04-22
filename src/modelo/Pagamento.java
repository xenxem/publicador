package modelo;

import java.sql.Timestamp;

public class Pagamento {

	int codigoPagamento;
	Timestamp data;
	int numeroParaIdentificacao;
	Anuncio anuncio;
	Valor valor;
	Conta conta;
	String periodo;
	
	
	public int getCodigoPagamento() {
		return codigoPagamento;
	}
	public void setCodigoPagamento(int codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public int getNumeroParaIdentificacao() {
		return numeroParaIdentificacao;
	}
	public void setNumeroParaIdentificacao(int numeroParaIdentificacao) {
		this.numeroParaIdentificacao = numeroParaIdentificacao;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public Valor getValor() {
		return valor;
	}
	public void setValor(Valor valor) {
		this.valor = valor;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public void setPeriodo(String periodo){
		this.periodo = periodo;
	}
	public String getPeriodo(){
		return periodo;
	}
	
	
}
