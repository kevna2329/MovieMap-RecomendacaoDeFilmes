import model.*;
import service.Recomendador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        LerArquivo dados = new LerArquivo("/resources/filmes_brasileiros.csv"); // lê o arquivo csv
        Grafo grafo = dados.getDados(); // cria o grafo

        /*System.out.println("=================================");
        System.out.println("FILMES DISPONIVEIS");
        System.out.println("=================================");*/

        //System.out.println(grafo);
        //grafo.listarFilmes();
        Scanner input = new Scanner(System.in);

        Recomendador recomendador = new Recomendador(grafo);
        Filme origem = grafo.getAdjacencia().get("Aquarius");

        // Exibe
        for(Filme filme : recomendador.recomendarPorDijkstra(origem)){
            System.out.println("============================================\n");
            System.out.println(filme);
        }







        /*int opcao;
        do {
            System.out.println("Digite o filme: ");
            String filme = input.nextLine();
            System.out.println("Você gostou:");
            System.out.println("1. Atores");
            System.out.println("2. Genero");
            System.out.println("3. Os dois");
            opcao = input.nextInt();
            switch (opcao) {
                case 1:{
                    //recomendarFilmeAtor(filme)
                }
                case 2:{
                    //recomendarFilmeGenero(filme)

                } case 3:{
                    //recomendarFilmes(filme)
                }
            }
        }while (opcao != 0);*/
    }
}