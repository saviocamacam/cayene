package cayeneN;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivo {

	public GerenciadorArquivo() {

	}
	
	public static List<String> lerArquivo(String nomeArquivo) {
		
		List<String> linhas = new ArrayList<>();
		try {
			linhas = Files.readAllLines(Paths.get(nomeArquivo, ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linhas;
	}
}
