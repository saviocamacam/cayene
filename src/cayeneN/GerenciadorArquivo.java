package cayeneN;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GerenciadorArquivo {

	public GerenciadorArquivo() {

	}
	
	public static LinkedList<Linha> lerArquivo(String nomeArquivo) {
		LinkedList<Linha> conjuntoEntrada = new LinkedList<>();
		List<String> linhas = new ArrayList<>();
		try {
			linhas = Files.readAllLines(Paths.get(nomeArquivo, ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String linha : linhas) {
			String[] vetorCaracteristicas = linha.substring(0).split(", ");
			Linha linhaConvertida = new Linha(vetorCaracteristicas[vetorCaracteristicas.length-1]);
			linhaConvertida.converteCaracteristicas(vetorCaracteristicas);
			conjuntoEntrada.add(linhaConvertida);
		}
		return conjuntoEntrada;
	}

}
