package cayeneN;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		int opcaoMenu = 0;
		scanner = new Scanner(System.in);
		List<String> linhasBrutasTreino = null;
		List<String> linhasBrutasTeste = null;
		
		String nomeArquivo = "";
		int k = 0;
		CaiyeneN cayene = null;
		
		do {
			System.out.println("(1) TREINAR");
			System.out.println("(2) TESTAR");
			
			System.out.print("Opcao: ");
			
			opcaoMenu = scanner.nextInt();
			
			if(opcaoMenu == 1) {
				System.out.print("> Nome do arquivo de treino: ");
				//nomeArquivo = scanner.next();
				nomeArquivo = "treino.data";
				linhasBrutasTreino = GerenciadorArquivo.lerArquivo(nomeArquivo);
				
				System.out.println("Proporcao: 25, 50 ou 100?");
				Double proporcao = scanner.nextDouble();
				//Double proporcao = 25.0;
				cayene = new CaiyeneN(proporcao, linhasBrutasTreino);
				
				System.out.print("K? ");
				k = scanner.nextInt();
				//k = 3;
				cayene.crossValidation(k);
			}
			
			if(opcaoMenu == 2) {
				System.out.print("> Nome do arquivo de treino: ");
				//nomeArquivo = scanner.next();
				nomeArquivo = "treino.data";
				linhasBrutasTreino = GerenciadorArquivo.lerArquivo(nomeArquivo);
				
				System.out.print("> Nome do arquivo de teste: ");
				//nomeArquivo = scanner.next();
				nomeArquivo = "teste.data";
				linhasBrutasTeste = GerenciadorArquivo.lerArquivo(nomeArquivo);
				
				System.out.println("Proporcao: 25, 50 ou 100?");
				Double proporcao = scanner.nextDouble();
				//Double proporcao = 25.0;
				
				System.out.print("K? ");
				k = scanner.nextInt();
				//k = 3;
				cayene = new CaiyeneN(proporcao, linhasBrutasTreino, linhasBrutasTeste);
				cayene.testar(k);
			}
			
		} while (opcaoMenu != 7);

	}

}
