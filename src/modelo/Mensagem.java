package modelo;
import java.sql.Timestamp;

public class Mensagem {
	
	int codigoMensagem;
	Usuario usuario;
	Anuncio anuncio;
	Timestamp data;
	String descricaoMensagem;
	String tipo;
	int ordem;
	
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}
	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}
	public int getCodigoMensagem() {
		return codigoMensagem;
	}
	public void setCodigoMensagem(int codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}
	
}

