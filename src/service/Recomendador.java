package service;
import enums.TipoAresta;
import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Recomendador {
    private Grafo grafo;

    public Recomendador(Grafo grafo){
        this.grafo = grafo;
    }

    /*public ArrayList<Filme> recomendacaoGeral(Filme filme){
        ArrayList<Aresta> relacoes = new ArrayList<>();

        for(TipoAresta tipo : TipoAresta.values()) {
            for (Aresta aresta : filme.getRelacionados(tipo)) {
                relacoes.add(aresta);
            }
        }

        Similaridade similaridade = new Similaridade(filme,  relacoes);
            return similaridade.calcularSimilaridade();
    }*/

    public Filme[] recomendarPorDijkstra(Filme origem) {
        Map<Filme, Integer> distancias = grafo.dijkstra(origem);
        ArrayList<Filme> temp = new ArrayList<>();
        Filme [] top5 = new Filme[5];
        // Ordena por menor distância (mais similaridade)
        temp =  distancias.entrySet().stream()
                .filter(e -> !e.getKey().equals(origem))
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
        for(int i = 0; i < 5; i++){
            top5[i] = temp.get(i);
        }
        return top5;
    }





}
