import model.LerArquivo;
import model.*;

import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {

        Grafo grafo = new Grafo();

        LerArquivo lerArquivo = new LerArquivo();
        lerArquivo.lerArquivo(grafo);

        System.out.println("=================================");
        System.out.println("FILMES DISPONIVEIS");
        System.out.println("=================================");

        System.out.println(grafo);
        Scanner input = new Scanner(System.in);


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