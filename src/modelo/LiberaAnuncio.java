package modelo;
import java.sql.Timestamp;

public class LiberaAnuncio {

	Usuario usuario;
	Anuncio anuncio;
	Timestamp data;
	String tipo;
	String periodo;
	
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
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public String getTipo(){
		return tipo;
	}
	public void setPeriodo(String periodo){
		this.periodo = periodo;
	}
	public String getPeriodo(){
		return periodo;
	}
}