package modelo;

public class Imagem {		
	int codigoImagem;
	String imgNome;		
	String destaque;
	int ordem;
	Anuncio anuncio;
	
	public String getImgNome() {
		return imgNome;
	}
	public void setImgNome(String imgNome) {
		this.imgNome = imgNome;
	}
	
	public String getDestaque() {
		return destaque;
	}
	public void setDestaque(String destaque) {
		this.destaque = destaque;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public int getCodigoImagem() {
		return codigoImagem;
	}
	public void setCodigoImagem(int codigoImagem) {
		this.codigoImagem = codigoImagem;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	
	
	
}
