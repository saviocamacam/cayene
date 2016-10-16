package cayeneN;

import java.util.LinkedList;

public class TipoCaracteristica {
	private String nomeCaracteristica;
	private Double menorValor;
	private Double maiorValor;
	private LinkedList<Caracteristica> exemplares;
	
	public TipoCaracteristica(int i) {
		this.setNomeCaracteristica("" + i);
		this.menorValor = 99999999.0;
		this.maiorValor = -9999999.0;
		exemplares = new LinkedList<>();
	}

	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	public void setNomeCaracteristica(String nomeCaracteristica) {
		this.nomeCaracteristica = nomeCaracteristica;
	}

	public LinkedList<Caracteristica> getExemplares() {
		return exemplares;
	}

	public void setExemplares(LinkedList<Caracteristica> exemplares) {
		this.exemplares = exemplares;
	}

	public void verificaOrdem() {
		Caracteristica c = exemplares.getLast();
		if(c.getValor() < menorValor)
			menorValor = c.getValor();

		if(c.getValor() > maiorValor)
			maiorValor = c.getValor();
	}

	public Double getMenorValor() {
		return menorValor;
	}

	public void setMenorValor(Double menorValor) {
		this.menorValor = menorValor;
	}

	public Double getMaiorValor() {
		return maiorValor;
	}

	public void setMaiorValor(Double maiorValor) {
		this.maiorValor = maiorValor;
	}

}
