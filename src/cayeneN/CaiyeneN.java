package cayeneN;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CaiyeneN {
	
	private Double proporcao;
	List<String> linhasBrutas;
	LinkedList<Linha> conjuntoEntrada;
	LinkedList<Linha> conjuntoTreino;
	LinkedList<Classe> conjuntoClasses;
	private int matrizConfusao[][];
	
	public CaiyeneN(Double proporcao, List<String> linhasBrutas) {
		this.setProporcao(proporcao);
		this.setLinhasBrutas(linhasBrutas);
		conjuntoEntrada = new LinkedList<>();
		conjuntoTreino = new LinkedList<>();
		conjuntoClasses = new LinkedList<>();
		converterLinhas();
	}
	
	public CaiyeneN(List<String> linhasBrutas) {
		this.setLinhasBrutas(linhasBrutas);
		conjuntoEntrada = new LinkedList<>();
		converterLinhas();
	}

	public Double getProporcao() {
		return proporcao;
	}
	
	public void setProporcao(Double proporcao) {
		this.proporcao = proporcao;
	}
	
	public void treinar(int k) {
		int i, posLinha = -1, posCol = -1;
		normalizarValores();
		randomizaLinhas();
		setMatrizConfusao();
		
		for (Linha linhaTreino : conjuntoTreino) {
			for (Linha linhaTeste : conjuntoEntrada) {
				linhaTeste.setDistancia(executaCalculo(linhaTreino, linhaTeste));
			}
			conjuntoEntrada.sort(new Comparator<Linha>() {
				@Override
				public int compare(Linha o1, Linha o2) {
					
					if(o1.getDistancia() > o2.getDistancia())
						return 1;
					else if (o1.getDistancia() == o2.getDistancia())
						return 0;
					else 
						return -1;
				}
			});
			
			LinkedList<Linha> vizinhosProximos = new LinkedList<>();
			
			for (i=0 ; i<k ; i++) {
				vizinhosProximos.add(conjuntoEntrada.get(i));
			}
			linhaTreino.setClasseSugerida(buscarClasseSugerida(vizinhosProximos));
		}
		
		for(Classe classeLinha : conjuntoClasses) {
			
			classeLinha.setLinhas(buscarLinhasPorClasse(classeLinha.getNomeClasse()));
			
			for(Classe classeColuna : conjuntoClasses) {
				int count = 0;
				
				for(Linha linha : classeLinha.getLinhas()) {
					if(linha.getClasseSugerida().equals(classeColuna.getNomeClasse()))
						count = count + 1;
				}
				posLinha = conjuntoClasses.indexOf(new Classe(classeLinha.getNomeClasse()));
				posCol = conjuntoClasses.indexOf(new Classe(classeColuna.getNomeClasse()));
				
				matrizConfusao[posLinha][posCol] = count;
				
				
				System.out.println("aqui");
			}
		}
		imprimeMatrizConfusao();
	}
	
	private void imprimeMatrizConfusao() {
		int i, j;
		for(i = 0 ; i < conjuntoClasses.size() ; i++) {
			for (j = 0 ; j < conjuntoClasses.size() ; j++) {
				System.out.print(matrizConfusao[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	private LinkedList<Linha> buscarLinhasPorClasse(String nomeClasse) {
		LinkedList<Linha> linhasPorClasse = new LinkedList<>();
		for (Linha linha : conjuntoTreino) {
			if(linha.getClasseReal().equals(nomeClasse))
				linhasPorClasse.add(linha);
		}
		return linhasPorClasse;
	}

	private String buscarClasseSugerida(LinkedList<Linha> vizinhosProximos) {
		LinkedList<Classe> conjuntoLocalClasse = new LinkedList<>();
		int incidencia = 0, pos = -1;
		for (Linha linha : vizinhosProximos) {
			
			if(!estaEmConjunto(conjuntoLocalClasse, linha.getClasseReal()))
				conjuntoLocalClasse.add(new Classe(linha.getClasseReal()));
			else {
				pos = conjuntoLocalClasse.indexOf(new Classe(linha.getClasseReal()));
				incidencia = conjuntoLocalClasse.get(pos).getIncidencia();
				conjuntoLocalClasse.get(conjuntoLocalClasse.indexOf(new Classe(linha.getClasseReal()))).setIncidencia(incidencia + 1);
			}
		}
		conjuntoLocalClasse.sort(new Comparator<Classe>() {

			@Override
			public int compare(Classe o1, Classe o2) {
				if(o1.getIncidencia() < o2.getIncidencia())
					return 1;
				else if (o1.getIncidencia() == o2.getIncidencia())
					return 0;
				else
					return -1;
			}
		});
		return conjuntoLocalClasse.getFirst().getNomeClasse();
	}

	private Double executaCalculo(Linha linhaTreino, Linha linhaTeste) {
		Double somatorio = 0.0, somaParcial = 0.0;
		Caracteristica cTreino = null;
		Caracteristica cTeste = null;
		int i;
		
		for (i = 0 ; i < linhaTreino.getCaracteristicas().size() ; i++) {
			cTreino = linhaTreino.getCaracteristicas().get(i);
			cTeste = linhaTeste.getCaracteristicas().get(i);;
			
			somaParcial = cTreino.getValorNormalizado() - cTeste.getValorNormalizado();
			somatorio = somatorio + Math.pow(somaParcial, 2);
			
		}
		return Math.sqrt(somatorio);
	}

	private void randomizaLinhas() {
		Random r = new Random();
		int n, contagem = 0;
		double tamanhoProporcao;
		
		while(!conjuntoEntrada.isEmpty()) {
			Linha linha = conjuntoEntrada.pop();
			for(Classe classe : conjuntoClasses) {
				if(classe.getNomeClasse().equals(linha.getClasseReal()))
					classe.getLinhas().add(linha);
			}
		}
		
		for (Classe classe : conjuntoClasses) {
			double quantidadeLinhas = classe.getLinhas().size();
			tamanhoProporcao = quantidadeLinhas * (proporcao / 100.0);
			
			contagem = 0;
			while(contagem < tamanhoProporcao ) {
				n = r.nextInt(classe.getLinhas().size());
				conjuntoTreino.add(classe.getLinhas().remove(n));
				contagem++;
			}
			
			while(!classe.getLinhas().isEmpty()) {
				conjuntoEntrada.add(classe.getLinhas().removeFirst());
			}
		}
	}

	public void normalizarValores() {
		LinkedList<TipoCaracteristica> conjuntoCaracteristicas = extrairCaracteristicas();
		
		for (Linha linha : conjuntoEntrada) {
			for (Caracteristica caracteristica : linha.getCaracteristicas()) {
				caracteristica.setValorNormalizado(calculaNorma(caracteristica.getValor(), conjuntoCaracteristicas.get(Integer.valueOf(caracteristica.getNomeCaracteristica())).getMaiorValor(), conjuntoCaracteristicas.get(Integer.valueOf(caracteristica.getNomeCaracteristica())).getMenorValor()));
			}
		}
	}
	
	private Double calculaNorma(Double valor, Double maiorValor, Double menorValor) {
		Double newMax = 1.0;
		Double newMin = 0.0;
		Double valorNorma = ((valor - menorValor)/(maiorValor - menorValor)) * (newMax - newMin) + newMin; 
		return valorNorma;
	}

	private LinkedList<TipoCaracteristica> extrairCaracteristicas() {
		int i;
		LinkedList<TipoCaracteristica> conjuntoCaracteristicas = new LinkedList<>();
		
		for(i = 0 ; i < conjuntoEntrada.get(0).getCaracteristicas().size() ; i++) {
			conjuntoCaracteristicas.add(new TipoCaracteristica(i));
		}
		
		
		for (String linha : linhasBrutas) {
			String[] vetorCaracteristicas = linha.substring(0).split(", ");
			
			for (i = 0 ; i < vetorCaracteristicas.length-1 ; i++) {
				conjuntoCaracteristicas.get(i).getExemplares().addLast(new Caracteristica("" + i, vetorCaracteristicas[i]));
				conjuntoCaracteristicas.get(i).verificaOrdem();
			}
			if(!estaEmConjunto(conjuntoClasses, vetorCaracteristicas[i]))
				conjuntoClasses.add(new Classe(vetorCaracteristicas[i]));
			
		}
		return conjuntoCaracteristicas;
	}

	private boolean estaEmConjunto(LinkedList<Classe> conjunto, String nomeClasse) {
		
		for(Classe classe : conjunto) {
			if(classe.getNomeClasse().equals(nomeClasse)) {
				return true;
			}
		}
		return false;
	}

	private void converterLinhas() {
		for (String linha : linhasBrutas) {
			String[] vetorCaracteristicas = linha.substring(0).split(", ");
			Linha linhaConvertida = new Linha(vetorCaracteristicas[vetorCaracteristicas.length-1]);
			linhaConvertida.converteCaracteristicas(vetorCaracteristicas);
			conjuntoEntrada.add(linhaConvertida);
		}
	}

	public List<String> getLinhasBrutas() {
		return linhasBrutas;
	}

	public void setLinhasBrutas(List<String> linhasBrutas) {
		this.linhasBrutas = linhasBrutas;
	}

	public int[][] getMatrizConfusao() {
		return matrizConfusao;
	}

	public void setMatrizConfusao() {
		this.matrizConfusao = new int[conjuntoClasses.size()][conjuntoClasses.size()];
	}

}
