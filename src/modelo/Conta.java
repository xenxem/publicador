package modelo;

public class Conta {

	int codigoConta;
	String numeroConta;
	String agencia;
	Banco banco;
	String status;
	
	public int getCodigoConta() {
		return codigoConta;
	}
	public void setCodigoConta(int codigoConta) {
		this.codigoConta = codigoConta;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
