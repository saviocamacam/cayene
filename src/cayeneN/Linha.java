package cayeneN;

import java.util.LinkedList;

public class Linha {
	private LinkedList<Caracteristica> caracteristicas;
	private String classeReal;
	private String classeSugerida;
	private Double distancia;
	
	public Linha(String classe) {
		this.setClasseReal(classe);
		this.caracteristicas = new LinkedList<>();
	}

	public String getClasseReal() {
		return classeReal;
	}

	public void setClasseReal(String classe) {
		this.classeReal = classe;
	}

	public LinkedList<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(LinkedList<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void converteCaracteristicas(String[] vetorCaracteristicas) {
		int i;
		for (i = 0 ; i < vetorCaracteristicas.length-1 ; i++) {
			caracteristicas.add(new Caracteristica("" + i, vetorCaracteristicas[i]));
		}
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public String getClasseSugerida() {
		return classeSugerida;
	}

	public void setClasseSugerida(String classeSugerida) {
		this.classeSugerida = classeSugerida;
	}
}
