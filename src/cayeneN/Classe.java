package cayeneN;

import java.util.LinkedList;

public class Classe {
	private String nomeClasse;
	private LinkedList<Linha> linhas;
	private int incidencia = 1;
	
	public Classe(String classe) {
		this.setNomeClasse(classe);
		this.linhas = new LinkedList<>();
	}

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String classe) {
		this.nomeClasse = classe;
	}

	public LinkedList<Linha> getLinhas() {
		return linhas;
	}

	public void setLinhas(LinkedList<Linha> linhas) {
		this.linhas = linhas;
	}

	public int getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.nomeClasse.equals(((Classe) o).getNomeClasse());
	}

}
