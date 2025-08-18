package model;

import enums.TipoAresta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LerArquivo {
    String caminhoArquivo;

    public LerArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Grafo getDados(){
        Grafo grafo = new Grafo();
        InputStream inputStream = LerArquivo.class.getResourceAsStream(caminhoArquivo);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            // Lendo número de filmes
            int nVertices = Integer.parseInt(br.readLine().trim());
            // Lista para armazenar os filmes
            ArrayList<Filme> filmes = new ArrayList<>();

            // Lendo os filmes
            for (int i = 0; i < nVertices; i++) {
                String linha = br.readLine();
                String[] partes = linha.split(";");
                String nome = partes[0];
                int ano = Integer.parseInt(partes[1]);
                String genero = partes[2];
                String descricao = partes[3];
                ArrayList<String> atores = new ArrayList<>();
                atores.add(partes[4]);

                Filme f = new Filme(nome, ano, genero, descricao, atores);
                filmes.add(f);
                grafo.adicionarVertice(f);
            }

            // Lista para armazenar as arestas (ligações entre filmes)
            int nArestas = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < nArestas; i++)  {
                String linha = br.readLine();
                String[] partes = linha.split(";");
                String origem = partes[0];
                String destino = partes[1];
                String tipo = partes[2]; //tipo aresta
                String speso = partes[3];
                int peso = Integer.parseInt(speso);
                Filme filmeOrigem  = null;
                Filme filmeDestino = null;
                for(Filme f: filmes){
                    if (f.getNome().equalsIgnoreCase(origem) ){
                        filmeOrigem = new Filme(f.getNome(),f.getAno(),f.getGenero(), f.getDescricao(), f.getAtores());
                    }
                    if (f.getNome().equalsIgnoreCase(destino)){
                        filmeDestino =  new Filme(f.getNome(),f.getAno(),f.getGenero(), f.getDescricao(), f.getAtores());
                    }
                }
                if(tipo.equalsIgnoreCase("ARESTA_GENERO")){
                    grafo.adicionarAresta(grafo, filmeOrigem, filmeDestino, TipoAresta.ARESTA_GENERO, peso);
                }else{
                    grafo.adicionarAresta(grafo, filmeOrigem, filmeDestino, TipoAresta.ARESTA_ATOR, peso);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }
}


