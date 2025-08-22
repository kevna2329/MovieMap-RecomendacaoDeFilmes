package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


//pra criar as arestas depois apagar
public class Organizar {
    public static int atoresEmComum(Filme f1, Filme f2) {
        int cont = 0;
        ArrayList<String> nomes = new ArrayList<>();
        for (String g : f1.getAtores()) {
            // se f.getAtores() retorna UMA string grande, faça o split
            String[] atores = g.split(",");
            for (String ator : atores) {
                for (String h : f2.getAtores()) {
                    // se f.getAtores() retorna UMA string grande, faça o split
                    String[] atores2 = h.split(",");

                    for (String ator2 : atores2) {
                        if (ator.trim().equalsIgnoreCase(ator2.trim()) && !f1.getNome().equalsIgnoreCase(f2.getNome())) {
                            cont++;

                        }

                    }
                }

            }
            nomes.add(f1.getNome());
        }

        return cont;
    }
    public static void main(String[] args) throws IOException {

        LerArquivo dados = new LerArquivo("/resources/filmes_brasileiros.csv"); // lê o arquivo csv
        Grafo grafo = dados.getDados(); // cria o grafo

        /*System.out.println("=================================");
        System.out.println("FILMES DISPONIVEIS");
        System.out.println("=================================");*/

        //System.out.println(grafo);
        //grafo.listarFilmes();

        Map<String, Filme> filmes = grafo.returFilmes();
        ArrayList<String> nomes = new ArrayList<>();
        int cont = 0;
        for (Filme f : filmes.values()) {
            for (Filme g : filmes.values()) {
                int emComum = atoresEmComum(f, g);
                if (emComum > 0) {
                    System.out.printf("%s;%s;ARESTA_ATOR;%d\n", f.getNome(),g.getNome(),emComum) ;
                }
            }
        }


    }
}
