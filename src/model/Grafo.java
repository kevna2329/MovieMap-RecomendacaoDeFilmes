package model;
import enums.TipoAresta;

import java.net.StandardSocketOptions;
import java.util.*;

public class Grafo {
    private Map<String, Filme> adjacencia;

    public Grafo(){
        this.adjacencia = new HashMap<>();
    }
    // Adiciona um vértice (se ainda não existir)
    public void adicionarVertice(Filme filme) {
        adjacencia.putIfAbsent(filme.getNome(), filme);
    }

    // Adiciona uma aresta (não direcionada por padrão)
    public Grafo adicionarAresta(Grafo grafo, Filme origem, Filme destino, TipoAresta tipo, int peso ) {
        adicionarVertice(origem);
        adicionarVertice(destino);

        // Adiciona origem → destino
        if (!existeAresta(origem, destino, tipo)) {
            Aresta aresta = new Aresta(destino, tipo, peso);
            adjacencia.get(origem.getNome()).addAresta(aresta, tipo);
        }

        // Adiciona destino → origem (não direcionado)
        if (!existeAresta(destino, origem, tipo)) {
            Aresta aresta2 = new Aresta(origem, tipo, peso);
            adjacencia.get(destino.getNome()).addAresta(aresta2, tipo); //ajeitar
        }

        return grafo;
    }

    public boolean existeAresta(Filme origem, Filme destino, TipoAresta tipo) {
        if(adjacencia.get(origem.getNome()) == null) {
            return false;
        }
        return adjacencia.get(origem.getNome())
                .getRelacionados(tipo)
                .stream()
                .anyMatch(a -> a.getDestino().equals(destino));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //usa para concatenar em loop
        sb.append("\n======== GRAFO =======\n"); //cabeça~lho com quebra de linha antes de depois
        for(Filme filme : adjacencia.values()){ //percorre todos os vértices(filmes) do map FORA DE ORDEM
            sb.append("\nVértice : ")
            .append(filme.getNome()).append(" \n    Arestas: ");
            for(TipoAresta tipo : TipoAresta.values()){//percorre cada tipo de aresta
                List<Aresta> relacionadas = filme.getRelacionados(tipo); //pega as arestas do tipo para o film
                    if(tipo.equals(TipoAresta.ARESTA_GENERO) && !relacionadas.isEmpty())
                            sb.append("\n       Relacionados por Genero:");
                if(tipo.equals(TipoAresta.ARESTA_ATOR) && !relacionadas.isEmpty())
                        sb.append("\n       Relacionados por Ator:");
                    for(Aresta aresta : relacionadas){//percorre cada aresta daquele tipo
                        sb.append(" ")//para mostrar ligação
                                .append(aresta.getDestino().getNome()+",");//usa tostring da aretsa}
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}