package service;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import enums.TipoAresta;
import model.Aresta;
import model.Filme;
import model.Grafo;

public class Recomendador {
    private final Grafo grafo;
    private int indiceAtual;

    public Recomendador(Grafo grafo){
        this.grafo = grafo;
    }

    public ArrayList<Filme> recomendarPorDijkstra(Filme origem) {
        Map<Filme, Integer> distancias = grafo.dijkstra(origem);
        // Ordena por menor distância (mais similaridade)
        return  distancias.entrySet().stream()
                .filter(e -> !e.getKey().equals(origem))
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean mostrarFilmes(ArrayList<Filme> filmes){
        int amostras = 5;
        if(filmes == null || filmes.isEmpty()){
            return false;
        }

        if(indiceAtual >= filmes.size()){
            return false;
        }

        System.out.println("\n===== Recomendações =====");
        for(int i = indiceAtual; i < indiceAtual + amostras && i<filmes.size() ; i++){
            System.out.println(filmes.get(i));
        }
        indiceAtual += amostras;
        return true;
    }

    public void resetIndiceAtual(){
        this.indiceAtual = 0;
    }

    public ArrayList<Filme> recomendarPorTipo(Filme origem, TipoAresta tipo){
        ArrayList<Filme> recomendados = new ArrayList<>();

            for(Aresta a : grafo.getArestasTipo(origem, TipoAresta.ARESTA_DUPLA)){
                recomendados.add(a.getDestino());
            }
            for(Aresta a : grafo.getArestasTipo(origem, tipo)){
                recomendados.add(a.getDestino());
            }
        return recomendados;
    }

    public void executarRecomendacao(Scanner input, TipoAresta tipo) {
        System.out.println("Digite o nome do filme: ");
        Filme filmeEscolhido = grafo.buscaFilme(input.nextLine());
            if(filmeEscolhido == null){ // caso não ache o filme
                    System.out.println("\u001B[31mERRO: [Nome inválido / Filme não encontrado].\u001B[0m");
                    System.out.println("1. Tentar novamente");
                    System.out.println("2. Voltar ao menu");
                    System.out.println("Digite sua escolha: ");
                    String escolha = input.nextLine();
                    switch (escolha) {
                        case "1" -> {
                            executarRecomendacao(input, tipo);
                            return;
                        }
                        case "2" -> {
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
            }else {
                resetIndiceAtual();

                ArrayList<Filme> recomendados =
                        (tipo == null)
                                ? recomendarPorDijkstra(filmeEscolhido)
                                : recomendarPorTipo(filmeEscolhido, tipo);

                    boolean continuar = true;
                    while(continuar){
                    if (!mostrarFilmes(recomendados)) {
                        System.out.println("Não há mais filmes a serem exibidos com este filtro!");
                        return;
                    } else {
                        String opcao = "";
                        boolean entradaValida = false;
                        while(!entradaValida) {
                            System.out.println("\n1. Ver mais recomendações");
                            System.out.println("2. Voltar ao menu");
                            System.out.println("Digite sua escolha: ");
                            opcao = input.nextLine();
                            switch (opcao) {
                                case "1" -> entradaValida = true;
                                case "2" -> {
                                    entradaValida = true;
                                    continuar = false;
                                }
                                default -> System.out.println("Opção inválida!");
                            }
                        }
                    }
                    }
            }
    }

    public void exibirFilme(Scanner input) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Digite o nome do filme: ");
            Filme busca = grafo.buscaFilme(input.nextLine());
            if (busca == null) {
                boolean entradaValida = false;
                while (!entradaValida) {
                    System.out.println("\u001B[31mERRO: [Nome inválido / Filme não encontrado].\u001B[0m");
                    System.out.println("1. Tentar novamente");
                    System.out.println("2. Voltar ao menu");
                    System.out.println("Digite sua escolha: ");
                    String escolha = input.nextLine();
                    switch (escolha) {
                        case "1" -> entradaValida = true;
                        case "2" -> {
                            entradaValida = true;
                            continuar = false;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                }

            } else {
                boolean entradaValida = false;
                System.out.println("\n" + busca);
                while (!entradaValida) {
                    System.out.println("\n" + "1. Vizualizar outro filme");
                    System.out.println("2. Voltar ao menu");
                    System.out.println("Digite sua escolha: ");
                    String opcao = input.nextLine();
                    switch (opcao) {
                        case "1" -> entradaValida = true;
                        case "2" -> {
                            entradaValida = true;
                            continuar = false;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                }
            }
        }
    }
}
