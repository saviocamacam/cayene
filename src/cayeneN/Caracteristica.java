package cayeneN;

public class Caracteristica {
	private String nomeCaracteristica;
	private String conteudo;
	private Double valor;
	private Double valorNormalizado;
	
	public Caracteristica(String nomeCaracteristica,String conteudo) {
		this.setNomeCaracteristica(nomeCaracteristica);
		this.setConteudo(conteudo);
		this.setValor(Double.valueOf(conteudo));
	}

	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	public void setNomeCaracteristica(String nomeCaracteristica) {
		this.nomeCaracteristica = nomeCaracteristica;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorNormalizado() {
		return valorNormalizado;
	}

	public void setValorNormalizado(Double valorNormalizado) {
		this.valorNormalizado = valorNormalizado;
	}

}
