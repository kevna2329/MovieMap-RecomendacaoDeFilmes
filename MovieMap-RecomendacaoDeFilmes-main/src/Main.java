import java.util.Scanner;

import enums.TipoAresta;
import model.Filme;
import model.Grafo;
import model.LerArquivo;
import service.Recomendador;

public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        LerArquivo dados = new LerArquivo("/resources/filmes_brasileiros.csv");
        Grafo grafo = dados.getDados();
        Recomendador recomendador = new Recomendador(grafo);
        boolean rodando = true;

        do {
            System.out.println("1. ============ MENU ============");
            System.out.println("1. Recomendar filmes similares");
            System.out.println("2. Recomendar filmes de mesmo gênero");
            System.out.println("3. Recomendar filmes de mesmos atores");
            System.out.println("4. Vizualizar filmes disponíveis");
            System.out.println("5. Vizualizar Grafo");
            System.out.println("6. Vizualizar informações de um filme");
            System.out.println("0. Fechar programa");
            System.out.println("Digite sua escolha: ");
            String opcao = input.nextLine();
            switch (opcao) {
                case "1" -> recomendador.executarRecomendacao(input,null);
                case "2" -> recomendador.executarRecomendacao(input, TipoAresta.ARESTA_GENERO);
                case "3" -> recomendador.executarRecomendacao(input, TipoAresta.ARESTA_ATOR);
                case "4" -> {
                    System.out.println("\n======= FILMES =======");
                    grafo.listarFilmes();
                }
                case "5" -> System.out.println(grafo);
                case "6" -> recomendador.exibirFilme(input);
                case "0" -> {
                    System.out.println("Encerrando programa...");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        } while (rodando);
    }
}