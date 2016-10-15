package cayeneN;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		int opcaoMenu = 0;
		scanner = new Scanner(System.in);
		LinkedList<Linha> linhas;
		String nomeArquivo = "";
		
		do {
			System.out.println("(1) LER ARQUIVO DE TREINO");
			System.out.println("(2) SEPARAR CONJUNTO TREINAMENTO");
			System.out.print("Opcao: ");
			
			opcaoMenu = scanner.nextInt();
			
			if(opcaoMenu == 1) {
				System.out.print("Nome do arquivo: ");
				nomeArquivo = scanner.next();
				linhas = GerenciadorArquivo.lerArquivo(nomeArquivo);
			}
			if(opcaoMenu == 2) {
				System.out.println("Proporcao: 25, 50 ou 100?");
				int proporcao = scanner.nextInt();
				
			}
			
		} while (opcaoMenu != 7);

	}

}
