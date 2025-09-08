package model;
import enums.TipoAresta;
import java.util.*;

public class Grafo {
    private final Map<Filme, ArrayList<Aresta>> adjacencia = new HashMap<>();

    public void adicionarVertice(Filme filme) {
        adjacencia.putIfAbsent(filme, new ArrayList<>());
    }

    public void adicionarAresta(Filme origem, Filme destino, TipoAresta tipo, int peso ) {
        adicionarVertice(origem);
        adicionarVertice(destino);

        // Adiciona origem → destino
        if (!existeAresta(origem, destino)) {
            Aresta aresta = new Aresta(destino, tipo, peso);
            adjacencia.get(origem).add(aresta);
        }

        // Adiciona destino → origem
        if (!existeAresta(destino, origem)) {
            Aresta aresta2 = new Aresta(origem, tipo, peso);
            adjacencia.get(destino).add(aresta2);
        }
    }

    public boolean existeAresta(Filme origem, Filme destino) {
        if(adjacencia.get(origem) == null) {
            return false;
        }
        return adjacencia.get(origem)
                .stream()
                .anyMatch(a -> a.getDestino().equals(destino));
    }

    public Filme buscaFilme (String nome){
        for(Filme f : adjacencia.keySet()){
            if(f.getNome().equalsIgnoreCase(nome)) {
                return f;
            }
        }
        return null;
    }

    public void listarFilmes(){
        for(Filme filme : adjacencia.keySet()){
            System.out.println(filme.getNome() + " (" + filme.getAno() + ")\n");
        }
    }

    public ArrayList<Aresta> getArestasTipo(Filme filme, TipoAresta tipo) {
        ArrayList<Aresta> arestas = new ArrayList<>();
        for(Aresta aresta : adjacencia.get(filme)) {
            if(aresta.getTipo().equals(tipo)) {
                arestas.add(aresta);
            }
        }
        return arestas;
    }

    public Map<Filme, Integer> dijkstra(Filme origem) {
        Map<Filme, Integer> distancias = new HashMap<>();
        PriorityQueue<Map.Entry<Filme, Integer>> fila = new PriorityQueue<>(Map.Entry.comparingByValue());

        // Inicializa todas as distâncias como infinito
        for (Filme f : adjacencia.keySet()) {
            distancias.put(f, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);

        fila.add(new AbstractMap.SimpleEntry<>(origem, 0));

        while (!fila.isEmpty()) {
            Filme atual = fila.poll().getKey();

            // percorre vizinhos por genero e ator
            for (Aresta aresta : adjacencia.get(atual)) {
                    Filme vizinho = aresta.getDestino();
                    int peso = aresta.getPeso();

                    // distancia acumulada
                    Integer distAtual = distancias.get(atual);
                    if (distAtual == null) continue; // segurança extra

                    int novaDistancia = distAtual + (100 - peso);

                    Integer distVizinho = distancias.get(vizinho);
                    if (distVizinho == null || novaDistancia < distVizinho) {
                        distancias.put(vizinho, novaDistancia);
                        fila.add(new AbstractMap.SimpleEntry<>(vizinho, novaDistancia));
                    }
            }
        }
        return distancias;
    }

    @Override
    public String toString() { //Impressão do grafo

        StringBuilder sb = new StringBuilder(); //usa para concatenar em loop
        sb.append("\n======== GRAFO ========\n"); //cabeçalho com quebra de linha antes de depois
        for(Filme filme : adjacencia.keySet()){ //percorre todos os vértices(filmes) do map FORA DE ORDEM
            sb.append("\nVértice : ")
                    .append(filme.getNome()).append(" \n    Arestas: ");
            for(TipoAresta tipo : TipoAresta.values()){//percorre cada tipo de aresta
                List<Aresta> relacionadas = getArestasTipo(filme, tipo); //pega as arestas do tipo para o film
                if(tipo.equals(TipoAresta.ARESTA_GENERO) && !relacionadas.isEmpty())
                    sb.append("\n       Relacionados por Genero:");
                if(tipo.equals(TipoAresta.ARESTA_ATOR) && !relacionadas.isEmpty())
                    sb.append("\n       Relacionados por Ator:");
                if(tipo.equals(TipoAresta.ARESTA_DUPLA) && !relacionadas.isEmpty())
                    sb.append("\n       Relacionados por Genero e Ator:");
                for(Aresta aresta : relacionadas){//percorre cada aresta daquele tipo
                    sb.append(" ")//para mostrar ligação
                            .append(aresta + ",");//usa tostring da aretsa}
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}