package cayeneN;

import java.util.LinkedList;

public class Linha {
	private LinkedList<Caracteristica> caracteristicas;
	private String classe;
	
	public Linha(String classe) {
		this.setClasse(classe);
		this.caracteristicas = new LinkedList<>();
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public LinkedList<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(LinkedList<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void converteCaracteristicas(String[] vetorCaracteristicas) {
		String nomeCaracteristica = "";
		int i = 1;
		for (String s : vetorCaracteristicas) {
			if(s != "")
				caracteristicas.add(new Caracteristica(nomeCaracteristica + i, s));
			i++;
		}
	}
}
